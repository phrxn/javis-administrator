package unit.com.quazzom.javis.administrator.rfb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.rfb.ServerSecurityTypeProtocolVersion3_3;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;
import com.quazzom.javis.administrator.lang.LanguageIdiom;

public class ServerSecurityTypeProtocolVersion3_3Test_UnitTest {

  @Test
  public void testCreateList_languageExceptionFromFile() {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    ServerSecurityTypeProtocolVersion3_3 sstp = new ServerSecurityTypeProtocolVersion3_3();

    RFBProtocolException vncPE =
        assertThrows(
            RFBProtocolException.class,
            () -> {
              sstp.createList(-1);
            });

    assertEquals(
        "The authentication type used by the client computer's VNC program is not known to Javis, value: -1. Protocol version 3.3",
        vncPE.getMessage());
  }
}
