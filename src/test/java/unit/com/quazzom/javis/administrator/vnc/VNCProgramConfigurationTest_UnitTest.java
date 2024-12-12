package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.lang.LanguageIdiom;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProgramConfigurationTest_UnitTest {

  @Test
  public void testSetVncName_emptyStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setName("");
            });

    assertEquals("The name describing VNC cannot be empty", vncEx.getMessage());
  }

  @Test
  public void testSetVncName_sizeStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0; count < VNCProgramConfiguration.MAX_STRING_SIZE_VNC_NAME + 1; count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setName(sb.toString());
            });

    assertEquals("The name describing VNC cannot be longer than 64 characters", vncEx.getMessage());
  }

  // ------------------------------------------------

  @Test
  public void testSetExecutionLine_emptyStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setExecutionLine("");
            });

    assertEquals("The execution line cannot be empty", vncEx.getMessage());
  }

  @Test
  public void testSetExecutionLine_sizeStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count < VNCProgramConfiguration.MAX_STRING_SIZE_EXECUTION_LINE + 1;
        count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setExecutionLine(sb.toString());
            });

    assertEquals("The execution line cannot be longer than 256 characters", vncEx.getMessage());
  }

  @Test
  public void testSetExecutionLine_invalidSyntax() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setExecutionLine("\"");
            });

    assertTrue(vncEx.getMessage().contains("The execution line syntax is invalid: "));
  }

  // ------------------------------------------------

  @Test
  public void testSetPathToExecutable_emptyStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setPathToExecutable("", true);
            });

    assertEquals("The path to executable cannot be empty", vncEx.getMessage());
  }

  @Test
  public void testSetPathToExecutable_sizeStringExceptionFromLangFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count < VNCProgramConfiguration.MAX_STRING_SIZE_PATH_TO_EXECUTABLE + 1;
        count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setPathToExecutable(sb.toString(), true);
            });

    assertEquals("The path to executable cannot be longer than 256 characters", vncEx.getMessage());
  }

  @Test
  public void testSetPathToExecutable_invalidSyntax() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setPathToExecutable("\"", true);
            });

    assertTrue(vncEx.getMessage().contains("The path to executable syntax is invalid: "));
  }

  @Test
  public void testSetPathToExecutable_isToChecksumWithFalse()
      throws VNCProgramConfigurationException {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    vnc.setPathToExecutable("aaa", true);

    assertEquals("aaa", vnc.getPathToExecutable());

    vnc.setPathToExecutable("xxx", false);

    assertEquals("aaa", vnc.getPathToExecutable());
  }

  // ------------------------------------------------

  @Test
  public void testSetTimeoutInSecondsToConnection_invalidMinimumValue() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setTimeoutInSecondsToConnection(61);
            });

    assertEquals(
        "The minimum value for connection timeout must be 2 seconds and the maximum 60 seconds",
        vncEx.getMessage());
  }

  @Test
  public void testSetTimeoutInSecondsToConnection_invalidMaximumValue() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setTimeoutInSecondsToConnection(1);
            });

    assertEquals(
        "The minimum value for connection timeout must be 2 seconds and the maximum 60 seconds",
        vncEx.getMessage());
  }

  // ------------------------------------------------

  @Test
  public void testSetParametersToConnectionWithNoAuthentication_testWithoutHostFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithNoAuthentication("\\port");
            });

    assertEquals(
        "The syntax for the parameters for the connection without authentication is invalid: The command line must contain the format string \\host",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithNoAuthentication_testWithoutPortFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithNoAuthentication("\\host");
            });

    assertEquals(
        "The syntax for the parameters for the connection without authentication is invalid: The command line must contain the format string \\port",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithNoAuthentication_withHostAndPortFormat() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    assertDoesNotThrow(
        () -> {
          // also testing if the order of the parameters does not affect the string.
          // the order of the parameters cannot affect the string!!
          vnc.setParametersToConnectionWithNoAuthentication("\\host\\port");
          vnc.setParametersToConnectionWithNoAuthentication("\\port\\host");
        });
  }

  @Test
  public void testSetParametersToConnectionWithNoAuthentication_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count
            < VNCProgramConfiguration
                    .MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITHOUT_AUTHENTICATION
                + 1;
        count++) {
      sb.append("a");
    }
    sb.append("\\host\\port");

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithNoAuthentication(sb.toString());
            });
    assertEquals(
        "The maximum number of characters that the parameter list for the connection without authentication can have is 256",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithNoAuthentication_invalidSyntax() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithNoAuthentication("\\host\\port\"");
            });

    assertTrue(
        vncEx
            .getMessage()
            .contains(
                "The syntax for the parameters for the connection without authentication is invalid: "));
  }

  // ------------------------------------------------

  @Test
  public void testSetParametersToConnectionWithVNCAuthentication_testWithoutHostFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithVNCAuthentication("\\port\\password");
            });

    assertEquals(
        "The syntax for the parameters for the VNC authentication connection is invalid: The command line must contain the format string \\host",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithVNCAuthentication_testWithoutPortFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithVNCAuthentication("\\host\\password");
            });

    assertEquals(
        "The syntax for the parameters for the VNC authentication connection is invalid: The command line must contain the format string \\port",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithVNCAuthentication_testWithoutPasswordFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithVNCAuthentication("\\host\\port");
            });

    assertEquals(
        "The syntax for the parameters for the VNC authentication connection is invalid: The command line must contain the format string \\password",
        vncEx.getMessage());
  }

  @Test
  public void
      testSetParametersToConnectionWithVNCAuthentication_testwithHostPortAndPasswordFormatString() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    assertDoesNotThrow(
        () -> {
          // also testing if the order of the parameters does not affect the string.
          // the order of the parameters cannot affect the string!!
          vnc.setParametersToConnectionWithVNCAuthentication("\\host\\port\\password");
          vnc.setParametersToConnectionWithVNCAuthentication("\\host\\password\\port");
          vnc.setParametersToConnectionWithVNCAuthentication("\\port\\host\\password");
          vnc.setParametersToConnectionWithVNCAuthentication("\\port\\password\\host");
          vnc.setParametersToConnectionWithVNCAuthentication("\\password\\port\\host");
          vnc.setParametersToConnectionWithVNCAuthentication("\\password\\host\\port");
        });
  }

  @Test
  public void testSetParametersToConnectionWithVNCAuthentication_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count
            < VNCProgramConfiguration
                    .MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_VNC_AUTHENTICATION
                + 1;
        count++) {
      sb.append("a");
    }
    sb.append("\\host\\port\\password");

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithVNCAuthentication(sb.toString());
            });
    assertEquals(
        "The maximum number of characters that the parameter list for a VNC authenticated connection can have is 256",
        vncEx.getMessage());
  }

  @Test
  public void testSetParametersToConnectionWithVNCAuthentication_invalidSyntax() {
    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithVNCAuthentication("\\host\\port\\password\"");
            });

    assertTrue(
        vncEx
            .getMessage()
            .contains(
                "The syntax for the parameters for the VNC authentication connection is invalid: "));
  }

  // ------------------------------------------------

  @Test
  public void
      testSetParametersToConnectionWithUltraVNCAuthentication_testWithoutHostFormatString() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithUltraVNCAuthentication("\\port\\user\\password");
            });

    assertEquals(
        "The syntax for the parameters for connecting with UltraVNC Mslogon authentication is invalid: The command line must contain the format string \\host",
        vncEx.getMessage());
  }

  @Test
  public void
      testSetParametersToConnectionWithUltraVNCAuthentication_testWithoutPortFormatString() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithUltraVNCAuthentication("\\host\\user\\password");
            });

    assertEquals(
        "The syntax for the parameters for connecting with UltraVNC Mslogon authentication is invalid: The command line must contain the format string \\port",
        vncEx.getMessage());
  }

  @Test
  public void
      testSetParametersToConnectionWithUltraVNCAuthentication_testWithoutUserFormatString() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithUltraVNCAuthentication("\\host\\port\\password");
            });

    assertEquals(
        "The syntax for the parameters for connecting with UltraVNC Mslogon authentication is invalid: The command line must contain the format string \\user",
        vncEx.getMessage());
  }

  @Test
  public void
      testSetParametersToConnectionWithUltraVNCAuthentication_testWithoutPasswordFormatString() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithUltraVNCAuthentication("\\host\\port\\user");
            });

    assertEquals(
        "The syntax for the parameters for connecting with UltraVNC Mslogon authentication is invalid: The command line must contain the format string \\password",
        vncEx.getMessage());
  }

  @Test
  public void
      testSetParametersToConnectionWithUltraVNCAuthentication_testwithHostPortAndPasswordFormatString() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    assertDoesNotThrow(
        () -> {
          // also testing if the order of the parameters does not affect the string.
          // the order of the parameters cannot affect the string!!
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\host\\user\\port\\password");
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\host\\password\\port\\user");
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\user\\port\\host\\password");
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\port\\password\\user\\host");
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\password\\user\\port\\host");
          vnc.setParametersToConnectionWithUltraVNCAuthentication("\\user\\password\\host\\port");
        });
  }

  @Test
  public void testSetParametersToConnectionWithUltraVNCAuthentication_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count
            < VNCProgramConfiguration
                    .MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION
                + 1;
        count++) {
      sb.append("a");
    }
    sb.append("\\host\\user\\port\\password");

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParametersToConnectionWithUltraVNCAuthentication(sb.toString());
            });
    assertEquals(
        "The maximum number of characters that the parameter list for a mslogon Ultra VNC authenticated connection can have is 256",
        vncEx.getMessage());
  }

  // ------------------------------------------------

  @Test
  public void testSetParameterForInteraction_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count < VNCProgramConfiguration.MAX_STRING_SIZE_PARAMETER_FOR_INTERACTION + 1;
        count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParameterForInteraction(sb.toString());
            });
    assertEquals(
        "The parameter for the interaction cannot be longer than 64 characters",
        vncEx.getMessage());
  }

  @Test
  public void testSetParameterForNotInteraction_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0;
        count < VNCProgramConfiguration.MAX_STRING_SIZE_PARAMETER_FOR_NOT_INTERACTION + 1;
        count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setParameterForNotInteraction(sb.toString());
            });
    assertEquals(
        "The parameters for the not interaction cannot be longer than 64 characters",
        vncEx.getMessage());
  }

  // ------------------------------------------------

  @Test
  public void testSetCheckSum_stringSizeException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0; count < VNCProgramConfiguration.STRING_SIZE_CHECKSUM + 1; count++) {
      sb.append("a");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setCheckSum(sb.toString(), true);
            });
    assertEquals("Checksum must contain 64 characters", vncEx.getMessage());
  }

  @Test
  public void testSetCheckSum_stringInvalidFormatException() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sb = new StringBuilder();
    for (int count = 0; count < VNCProgramConfiguration.STRING_SIZE_CHECKSUM; count++) {
      sb.append("z");
    }

    VNCProgramConfigurationException vncEx =
        assertThrows(
            VNCProgramConfigurationException.class,
            () -> {
              vnc.setCheckSum(sb.toString(), true);
            });
    assertEquals(
        "The checksum must be a hexadecimal text containing characters from 0 to 9 and/or from 'a' to 'f' in lowercase or uppercase",
        vncEx.getMessage());
  }

  @Test
  public void testSetCheckSum_validCheckSumValue0to9_AtoF_and_a_to_f() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    String hexValue = "0123456789abcdefABCDEF";

    StringBuilder sb = new StringBuilder();
    sb.append(hexValue);

    while (sb.length() < VNCProgramConfiguration.STRING_SIZE_CHECKSUM) {
      sb.append("a");
    }

    assertDoesNotThrow(
        () -> {
          vnc.setCheckSum(sb.toString(), true);
        });
  }

  @Test
  public void testSetCheckSum_isToChecksumWithFalse() throws VNCProgramConfigurationException {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    VNCProgramConfiguration vnc = new VNCProgramConfiguration();

    StringBuilder sbA = new StringBuilder();
    while (sbA.length() < VNCProgramConfiguration.STRING_SIZE_CHECKSUM) {
      sbA.append("a");
    }

    vnc.setCheckSum(sbA.toString(), true);

    assertTrue(vnc.getCheckSum().contains("aaaa"));

    StringBuilder sbB = new StringBuilder();
    while (sbB.length() < VNCProgramConfiguration.STRING_SIZE_CHECKSUM) {
      sbB.append("b");
    }

    vnc.setCheckSum(sbB.toString(), false);

    assertTrue(vnc.getCheckSum().contains("aaaa"));
  }

  // ------------------------------------------------

  @Test
  public void testEquals() throws VNCProgramConfigurationException, TCPPortException {

    VNCProgramConfiguration vncProgramConfigurationA = new VNCProgramConfiguration();
    boolean isToUseChecksumA = true;
    vncProgramConfigurationA.setId(0);
    vncProgramConfigurationA.setName("a");
    vncProgramConfigurationA.setDefaultPortToAccess(1);
    vncProgramConfigurationA.setExecutionLine("b");
    vncProgramConfigurationA.setPathToExecutable("c", isToUseChecksumA);
    vncProgramConfigurationA.setTimeoutInSecondsToConnection(2);
    vncProgramConfigurationA.setParametersToConnectionWithNoAuthentication("\\host\\port --foo");
    vncProgramConfigurationA.setParametersToConnectionWithVNCAuthentication(
        "\\host\\port\\password --foo");
    vncProgramConfigurationA.setParametersToConnectionWithUltraVNCAuthentication(
        "\\host\\port\\user\\password --foo");
    vncProgramConfigurationA.setParameterForInteraction("interaction --foo");
    vncProgramConfigurationA.setParameterForNotInteraction("notInteraction --foo");
    StringBuilder sbChecksumA = new StringBuilder();
    for (int count = 0; count < 64; count++) {
      sbChecksumA.append("a");
    }
    vncProgramConfigurationA.setCheckSum(sbChecksumA.toString(), isToUseChecksumA);
    vncProgramConfigurationA.setToUseChecksum(isToUseChecksumA);

    VNCProgramConfiguration vncProgramConfigurationB = new VNCProgramConfiguration();
    boolean isToUseChecksum = true;
    vncProgramConfigurationB.setId(0);
    vncProgramConfigurationB.setName("a");
    vncProgramConfigurationB.setDefaultPortToAccess(1);
    vncProgramConfigurationB.setExecutionLine("b");
    vncProgramConfigurationB.setPathToExecutable("c", isToUseChecksum);
    vncProgramConfigurationB.setTimeoutInSecondsToConnection(2);
    vncProgramConfigurationB.setParametersToConnectionWithNoAuthentication("\\host\\port --foo");
    vncProgramConfigurationB.setParametersToConnectionWithVNCAuthentication(
        "\\host\\port\\password --foo");
    vncProgramConfigurationB.setParametersToConnectionWithUltraVNCAuthentication(
        "\\host\\port\\user\\password --foo");
    vncProgramConfigurationB.setParameterForInteraction("interaction --foo");
    vncProgramConfigurationB.setParameterForNotInteraction("notInteraction --foo");
    StringBuilder sbChecksumB = new StringBuilder();
    for (int count = 0; count < 64; count++) {
      sbChecksumB.append("a");
    }
    vncProgramConfigurationB.setCheckSum(sbChecksumB.toString(), isToUseChecksum);
    vncProgramConfigurationB.setToUseChecksum(isToUseChecksum);

    assertEquals(vncProgramConfigurationA, vncProgramConfigurationB);
  }
}
