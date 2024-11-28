package unit.com.quazzom.javis.administrator.lang;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.Language;
import com.quazzom.javis.administrator.lang.TextKeyDoesntExist;
import com.quazzom.javis.administrator.lang.TextNotFoundException;

public class Language_UnitTest {

  @Test
  public void testgetText_keyDoesntExist() {

    // create a language with empty properties
    Language language = new Language();

    TextNotFoundException tnfe =
        assertThrows(
            TextNotFoundException.class,
            () -> {
              language.getText("Z");
            });

    assertEquals("The key Z doesn't exist", tnfe.getMessage());
  }

  @Test
  public void testgetText_keyExist() {

    // create a language with empty properties
    Properties p = new Properties();
    p.put("Key", "the value");
    Language language = new Language(p);

    String value;

    assertDoesNotThrow(
        () -> {
          language.getText("Key");
        });

    value = language.getText("Key");

    assertEquals("the value", value);
  }

  @Test
  public void testgetText_simpleFormatting() {

    // create a language with empty properties
    Properties p = new Properties();
    p.put("Key", "the value %d %s %c");
    Language language = new Language(p);

    String value;

    assertDoesNotThrow(
        () -> {
          language.getText("Key", 10, "foobar", 'x');
        });

    value = language.getText("Key", 10, "foobar", 'x');

    assertEquals("the value 10 foobar x", value);
  }

  // ---------------------------------------------------------

  @Test
  public void testContainsKey_keyDoesntExist() {
    Properties p = new Properties();
    p.put("Key", "Value");

    Language language = new Language(p);

    assertTrue(language.containsKey("Key"));
  }

  @Test
  public void testContainsKey_keyExist() {
    Properties p = new Properties();
    p.put("Key", "Value");

    Language language = new Language(p);

    assertFalse(language.containsKey("KeyXXXXXX"));
  }

  // ---------------------------------------------------------

  @Test
  public void testContainsAllTheseKeys_allKeysExist() {
    Properties p = new Properties();
    p.put("Key1", "Value");
    p.put("Key2", "Value");

    Language language = new Language(p);

    List<String> validKeys = new ArrayList<>();
    validKeys.add("Key1");
    validKeys.add("Key2");

    assertDoesNotThrow(
        () -> {
          language.containsAllTheseKeys(validKeys);
        });
  }

  @Test
  public void testContainsAllTheseKeys_oneKeyDoesntExist() {
    Properties p = new Properties();
    p.put("Key1", "Value");
    p.put("Key2", "Value");

    Language language = new Language(p);

    List<String> validKeys = new ArrayList<>();
    validKeys.add("Key1");
    validKeys.add("Key2");
    validKeys.add("Key3");

    TextKeyDoesntExist textKeyExc =
        assertThrows(
            TextKeyDoesntExist.class,
            () -> {
              language.containsAllTheseKeys(validKeys);
            });

    assertEquals("The key Key3 doesn't exist", textKeyExc.getMessage());
  }
}
