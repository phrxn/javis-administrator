package unit.com.quazzom.javis.administrator.lang;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.Language;
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

  @Test
  public void testRemoveCommentFromLanguageText_emptyString() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("");

    assertEquals("", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_trim() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText(" A ");

    assertEquals("A", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_onlyLanguageText() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("ABC");

    assertEquals("ABC", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_onlySeparator() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("\t");

    assertEquals("", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_CommentAndSeparator() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("OnlyCommit\t");

    assertEquals("", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_separatorAndLanguageText() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("\tA");

    assertEquals("A", formattedValue);
  }

  @Test
  public void testRemoveCommentFromLanguageText_commentAndSeparatorAndLanguageText() {
    Language language = new Language();

    String formattedValue = language.removeCommentFromLanguageText("Comment\tA");

    assertEquals("A", formattedValue);
  }
}
