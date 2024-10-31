package unit.com.quazzom.javis.administrator.configuration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.configuration.ConfigurationInvalidValueException;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;

public class GeneralConfigurationTest {

  @Test
  public void testSetExecutionMode_emptyString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setExecutionMode("");
            });

    assertEquals("The option EXECUTION_MODE cannot be empty", cive.getMessage());
  }

  @Test
  public void testSetExecutionMode_invalidString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setExecutionMode("FOOBAR");
            });

    assertEquals("FOOBAR is not a valid value for the EXECUTION_MODE option", cive.getMessage());
  }

  @Test
  public void testSetExecutionMode_ValidValues() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    assertDoesNotThrow(
        () -> {
          gc.setExecutionMode("NORMAL");
          gc.setExecutionMode("DEMO");
        });
  }

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void testSetEnvironmentMode_emptyString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setEnvironmentMode("");
            });

    assertEquals("The option ENVIRONMENT_MODE cannot be empty", cive.getMessage());
  }

  @Test
  public void testEnvironmentMode_invalidString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setEnvironmentMode("FOOBAR");
            });

    assertEquals("FOOBAR is not a valid value for the ENVIRONMENT_MODE option", cive.getMessage());
  }

  @Test
  public void testSetEnvironmentMode_ValidValues() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    assertDoesNotThrow(
        () -> {
          gc.setEnvironmentMode("PRODUCTION");
          gc.setEnvironmentMode("TEST");
        });
  }

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void testStorageType_emptyString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setStorageType("");
            });

    assertEquals("The option STORAGE cannot be empty", cive.getMessage());
  }

  @Test
  public void testStorageType_invalidString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setStorageType("FOOBAR");
            });

    assertEquals("FOOBAR is not a valid value for the STORAGE option", cive.getMessage());
  }

  @Test
  public void testStorageType_ValidValues() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    assertDoesNotThrow(
        () -> {
          gc.setStorageType("SQL_SERVER");
        });
  }

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void testAuthenticationType_emptyString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setAuthenticationType("");
            });

    assertEquals("The option AUTHENTICATION cannot be empty", cive.getMessage());
  }

  @Test
  public void testAuthenticationType_invalidString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setAuthenticationType("FOOBAR");
            });

    assertEquals("FOOBAR is not a valid value for the AUTHENTICATION option", cive.getMessage());
  }

  @Test
  public void testAuthenticationType_ValidValues() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    assertDoesNotThrow(
        () -> {
          gc.setAuthenticationType("ACTIVE_DIRECTORY");
          gc.setAuthenticationType("PROGRAM");
        });
  }

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void testLanguageType_emptyString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setLanguageType("");
            });

    assertEquals("The option LANGUAGE cannot be empty", cive.getMessage());
  }

  @Test
  public void testLanguageType_invalidString() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    ConfigurationInvalidValueException cive =
        assertThrows(
            ConfigurationInvalidValueException.class,
            () -> {
              gc.setLanguageType("FOOBAR");
            });

    assertEquals("FOOBAR is not a valid value for the LANGUAGE option", cive.getMessage());
  }

  @Test
  public void testLanguageType_ValidValues() {
    GeneralConfiguration gc = new GeneralConfiguration(null);

    assertDoesNotThrow(
        () -> {
          gc.setLanguageType("EN_US");
          gc.setLanguageType("PT_BR");
        });
  }

  // Loads from file and checks if all options are set.
  @Test
  public void testLoadConfigurationFromFile() {

    String validOptionsAndValues =
        "EXECUTION_MODE=NORMAL\n"
            + "ENVIRONMENT_MODE=PRODUCTION\n"
            + "STORAGE=SQL_SERVER\n"
            + "AUTHENTICATION=ACTIVE_DIRECTORY\n"
            + "LANGUAGE=EN_US\n";

    InputStream inputStream = new ByteArrayInputStream(validOptionsAndValues.getBytes());

    GeneralConfiguration gc = new GeneralConfiguration(inputStream);
    gc.setAllOptionsToInvalid();

    try {
      gc.loadConfigurationFromFile();
    } catch (IOException | ConfigurationInvalidValueException e) {
      fail(e.getMessage());
    }

    // check if all options are set.
    assertFalse(gc.isAnyOptionInValid());
  }

  @Test
  public void testPropertiesContainsTheOption_validKey() {
    Properties p = new Properties();
    p.setProperty("A", "B");

    assertDoesNotThrow(
        () -> {
          GeneralConfiguration gc = new GeneralConfiguration(null);
          gc.propertiesContainsTheOption(p, "A");
        });
  }

  @Test
  public void testPropertiesContainsTheOption_invalidKey() {
    Properties p = new Properties();
    p.setProperty("A", "B");

    IOException cive =
        assertThrows(
            IOException.class,
            () -> {
              GeneralConfiguration gc = new GeneralConfiguration(null);
              gc.propertiesContainsTheOption(p, "Z");
            });

    assertEquals("The configuration file doesn't contains the option: Z", cive.getMessage());
  }
}
