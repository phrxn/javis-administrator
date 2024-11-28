package unit.com.quazzom.javis.administrator.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.CreatePropertiesToLanguageException;
import com.quazzom.javis.administrator.lang.CreatePropertiesToLanguageFromXMLFile;
import com.quazzom.javis.administrator.lang.LanguageIdiom;

public class CreatePropertiesToLanguageFromXMLFileTest_UnitTest {

  @Test
  public void testCreateProperties_en_us() throws CreatePropertiesToLanguageException {
    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile());
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals(
        "{KEY2=english_foo2, KEY1=english_foo1, KEY3=english_foo3}",
        propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_pt_br() throws CreatePropertiesToLanguageException {
    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.PT_BR, null, new FakeXMLLanguageFile());
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals(
        "{KEY2=portugues_foo2, KEY1=portugues_foo1, KEY3=portugues_foo3}",
        propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithoutTagsChildren()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us>text_en</en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals("{KEY1=text_en}", propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithAChildTag()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us><aa>text_en</aa></en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals("{KEY1=<aa>text_en</aa>}", propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithAChildTagThatHaveAnAttribute()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us><aa a=\"b\">text_en</aa></en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals("{KEY1=<aa a=\"b\">text_en</aa>}", propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithAChildTagThatHaveAChild()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us><aa a=\"b\">text_en</aa>text2_en<a><b>yyy</b></a></en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals(
        "{KEY1=<aa a=\"b\">text_en</aa>text2_en<a><b>yyy</b></a>}",
        propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithAChildTagThatHaveAChildWithSomeAttributes()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us><aa a=\"b\">text_en</aa>text2_en<a><b one=\"1\" two=\"2\">yyy</b></a></en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals(
        "{KEY1=<aa a=\"b\">text_en</aa>text2_en<a><b one=\"1\" two=\"2\">yyy</b></a>}",
        propertiesFromXMLFile.toString());
  }

  @Test
  public void testCreateProperties_TagLanguageWithABrChildTag()
      throws CreatePropertiesToLanguageException {

    String contentXML =
        "<language class=\"Clazz\">\r\n"
            + "  <text key=\"KEY1\">\r\n"
            + "    <en_us><aa a=\"b\">text_en</aa>text2_en<a><br /><b one=\"1\" two=\"2\">yyy</b></a></en_us>\r\n"
            + "  </text>\r\n"
            + "</language>";

    CreatePropertiesToLanguageFromXMLFile a =
        new CreatePropertiesToLanguageFromXMLFile(
            LanguageIdiom.EN_US, null, new FakeXMLLanguageFile(contentXML));
    Properties propertiesFromXMLFile = a.createProperties();

    assertEquals(
        "{KEY1=<aa a=\"b\">text_en</aa>text2_en<a><br></br><b one=\"1\" two=\"2\">yyy</b></a>}",
        propertiesFromXMLFile.toString());
  }

  // ----- private classes

  private class FakeXMLLanguageFile extends InputStream {

    private String fakeContent =
        "<language class=\"Clazz\">\r\n"
            + "    <text key=\"KEY1\">\r\n"
            + "        <en_us>english_foo1</en_us>\r\n"
            + "        <pt_br>portugues_foo1</pt_br>\r\n"
            + "    </text>\r\n"
            + "    <text key=\"KEY2\">\r\n"
            + "        <en_us>english_foo2</en_us>\r\n"
            + "        <pt_br>portugues_foo2</pt_br>\r\n"
            + "    </text>\r\n"
            + "    <text key=\"KEY3\">\r\n"
            + "        <en_us>english_foo3</en_us>\r\n"
            + "        <pt_br>portugues_foo3</pt_br>\r\n"
            + "    </text>\r\n"
            + "</language>\r\n"
            + "";

    private FakeXMLLanguageFile() {}

    private FakeXMLLanguageFile(String fakeContent) {
      this.fakeContent = fakeContent;
    }

    private int currentIndex = 0;

    @Override
    public int read() throws IOException {
      if (currentIndex < fakeContent.length()) {
        char c = fakeContent.charAt(currentIndex);
        currentIndex++;
        return (int) c; // Retorna o valor do byte como inteiro (ASCII ou Unicode)
      } else {
        return -1; // Retorna -1 quando o final do conteúdo for alcançado
      }
    }
  }
}
