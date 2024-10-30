package unit.com.quazzom.javis.administrator.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.LanguageFromFile;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.LanguageIdiom;

public class LanguageFromFile_UnitTest {

  @Test
  public void testCreatePathToLanguageFile() {
    LanguageFromFile languageFromFile = new LanguageFromFile(null);

    String pathCreatedToFile =
        languageFromFile.createPathToLanguageFile(
            LanguageIdiom.EN_US, LanguagePathToFile.COPY_POPUP);

    assertEquals("lang/EN_US/administrator/gui/popup/CopyPopup.properties", pathCreatedToFile);
  }
}
