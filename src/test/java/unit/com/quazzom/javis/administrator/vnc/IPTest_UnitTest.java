package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.IP;
import com.quazzom.javis.administrator.vnc.IPException;

public class IPTest_UnitTest {

  @Test
  public void setIP_emptyString() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP("");
        });
    assertEquals("000.000.000.000", ipToTest.getIP());
  }

  @Test
  public void setIP_emptyOnlyBlankCaracteres() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP("\t \n    \t   ");
        });
    assertEquals("000.000.000.000", ipToTest.getIP());
  }

  @Test
  public void setIP_stringOnlySpaces() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP("               ");
        });
    assertEquals("000.000.000.000", ipToTest.getIP());
  }

  @Test
  public void setIP_bigStringSize() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    when(theLanguageMock.getText("INVALID_IP_SIZE_EXCEPTION", IP.MAX_STRING_SIZE_IP))
        .thenReturn("invalid size");

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    IPException ipe =
        assertThrows(
            IPException.class,
            () -> {
              StringBuilder invalidSize = new StringBuilder();
              for (int count = 0; count < IP.MAX_STRING_SIZE_IP + 1; count++) {
                invalidSize.append("a");
              }

              ipToTest.setIP(invalidSize.toString());
            });
    assertEquals("invalid size", ipe.getMessage());
  }

  @Test
  public void setIP_StringWithADifferentSyntax() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    when(theLanguageMock.getText("STRING_IP_SYNTAX_EXCEPTION", "potato")).thenReturn("invalid");

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    IPException ipe =
        assertThrows(
            IPException.class,
            () -> {
              ipToTest.setIP("potato");
            });
    assertEquals("invalid", ipe.getMessage());
  }

  @Test
  public void setIP_nothingAddress() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP(IP.ANDRESS_NOTHING);
        });
  }

  @Test
  public void testSetIP_validAddresses() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP("127.0.0.1");
          ipToTest.setIP("192.169.50.10");
          ipToTest.setIP("45.123.12.34");
          ipToTest.setIP("128.1.1.1");
          ipToTest.setIP("145.100.100.100");
          ipToTest.setIP("223.255.255.255");
        });
  }

  @Test
  public void testSetIP() throws IPException {

    Text theLanguageMock = Mockito.mock(Text.class);

    IP ipToTest = new IP(theLanguageMock, IP.ANDRESS_NOTHING);

    assertDoesNotThrow(
        () -> {
          ipToTest.setIP("127.0.0.1");
        });

    assertEquals("127.0.0.1", ipToTest.getIP());
  }

  @Test
  public void testClone() throws IPException {

    Text theLanguage = Mockito.mock(Text.class);

    IP ipBase = new IP(theLanguage, IP.ANDRESS_NOTHING);

    IP ipClone = ipBase.clone();

    assertEquals(ipBase.getIP(), ipClone.getIP());

    // check if IP's share the same Text object!
    assertEquals(ipBase.getTheLanguage(), ipClone.getTheLanguage());
  }

  // --------

  @Test
  public void testEquals() throws IPException {
    IP ipNothing = new IP(IP.ANDRESS_NOTHING);
    IP ipNothing2 = new IP(IP.ANDRESS_NOTHING);
    IP ipNothing3 = new IP("");

    assertTrue(ipNothing.equals(ipNothing2));
    assertTrue(ipNothing.equals(ipNothing3));
    assertTrue(ipNothing2.equals(ipNothing3));

    IP ipSomeThing = new IP("100.101.102.103");
    IP ipSomeThing2 = new IP("100.101.102.103");

    assertTrue(ipSomeThing.equals(ipSomeThing2));

    assertFalse(ipNothing.equals(ipSomeThing));
  }
}
