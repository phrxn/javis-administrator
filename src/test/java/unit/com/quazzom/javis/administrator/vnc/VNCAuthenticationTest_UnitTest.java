package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.vnc.VNCAuthentication;

public class VNCAuthenticationTest_UnitTest {

  @Test
  public void testClone() {

    VNCAuthentication vncAuthentication = new VNCAuthentication("a", false);
    VNCAuthentication vncNew = vncAuthentication.clone();

    assertEquals(vncAuthentication.getPassword(), vncNew.getPassword());
    assertEquals(vncAuthentication.isToSalveCredential(), vncNew.isToSalveCredential());

    vncNew.setPassword("b");
    vncNew.setToSalveCredential(true);

    assertNotEquals(vncAuthentication.getPassword(), vncNew.getPassword());
    assertNotEquals(vncAuthentication.isToSalveCredential(), vncNew.isToSalveCredential());
  }
}
