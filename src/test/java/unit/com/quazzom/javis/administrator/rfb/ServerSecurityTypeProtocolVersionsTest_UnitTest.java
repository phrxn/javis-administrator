package unit.com.quazzom.javis.administrator.rfb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.rfb.ServerSecurityTypeProtocolVersions;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;
import com.quazzom.javis.administrator.lang.LanguageIdiom;

public class ServerSecurityTypeProtocolVersionsTest_UnitTest {

  @Test
  public void testCreateList_languageExceptionFromFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    ServerSecurityTypeProtocolVersions sstp = new ServerSecurityTypeProtocolVersions();

    RFBProtocolException vncPE =
        assertThrows(
            RFBProtocolException.class,
            () -> {
              sstp.createList(new ArrayList<Byte>());
            });

    assertEquals(
        "None of the authentication types used by the VNC program on the client computer are known to Javis",
        vncPE.getMessage());
  }
}
