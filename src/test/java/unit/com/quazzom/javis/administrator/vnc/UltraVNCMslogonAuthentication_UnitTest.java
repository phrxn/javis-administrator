package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.vnc.UltraVNCMslogonAuthentication;

public class UltraVNCMslogonAuthentication_UnitTest {

  @Test
  public void testClone() {

    UltraVNCMslogonAuthentication ultraVNC = new UltraVNCMslogonAuthentication("a", "b", true);
    UltraVNCMslogonAuthentication ultraNEW = ultraVNC.clone();

    assertEquals(ultraVNC.getUsername(), ultraNEW.getUsername());
    assertEquals(ultraVNC.getPassword(), ultraNEW.getPassword());
    assertEquals(ultraVNC.isToSalveCredential(), ultraNEW.isToSalveCredential());

    ultraNEW.setUsername("1");
    ultraNEW.setPassword("2");
    ultraNEW.setIsToSalveCredential(false);

    assertNotEquals(ultraVNC.getUsername(), ultraNEW.getUsername());
    assertNotEquals(ultraVNC.getPassword(), ultraNEW.getPassword());
    assertNotEquals(ultraVNC.isToSalveCredential(), ultraNEW.isToSalveCredential());
  }
}
