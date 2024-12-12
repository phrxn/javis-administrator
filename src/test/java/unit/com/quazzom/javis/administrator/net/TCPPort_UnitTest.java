package unit.com.quazzom.javis.administrator.net;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPort;
import com.quazzom.javis.administrator.net.TCPPortException;

public class TCPPort_UnitTest {

  @Test
  public void testTcpChecker_emptyString() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertThrows(
        TCPPortException.class,
        () -> {
          tcpChecker.setPortValue("");
        });
  }

  @Test
  public void testTcpChecker_textString() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertThrows(
        TCPPortException.class,
        () -> {
          tcpChecker.setPortValue("ab");
        });
  }

  @Test
  public void testTcpChecker_stringIsANegativeValue() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertThrows(
        TCPPortException.class,
        () -> {
          tcpChecker.setPortValue("-1");
        });
  }

  @Test
  public void testTcpChecker_stringIsABigValue() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertThrows(
        TCPPortException.class,
        () -> {
          tcpChecker.setPortValue("65536");
        });
  }

  @Test
  public void testTcpChecker_stringIsAMinValue() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertDoesNotThrow(
        () -> {
          tcpChecker.setPortValue("0");
        });
  }

  @Test
  public void testTcpChecker_stringIsAMaxValue() throws TCPPortException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(any(), any())).thenReturn("");

    TCPPort tcpChecker = new TCPPort(0, theLanguageMock);

    assertDoesNotThrow(
        () -> {
          tcpChecker.setPortValue("65535");
        });
  }

  // ---------------------------------

  @Test
  public void testEquals() throws TCPPortException {
    TCPPort tcpPortA = new TCPPort(4);
    TCPPort tcpPortB = new TCPPort(4);

    assertEquals(tcpPortA, tcpPortB);
  }
}
