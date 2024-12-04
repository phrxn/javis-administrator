package unit.com.quazzom.javis.administrator.rfb;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.rfb.ProtocolVersion;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;

public class ProtocolVersionTest_UnitTest {

  @Test
  public void testGetVersion() {

    ProtocolVersion pv = new ProtocolVersion(1, 1, null);
    assertEquals("001.001", pv.getVersion());

    pv = new ProtocolVersion(10, 10, null);
    assertEquals("010.010", pv.getVersion());
  }

  // -------------------------------------------------------------------

  @Test
  public void testDecode_exceptionReadLine() throws IOException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText("PROTOCOL_DECODE_EXCEPTION", "fooex")).thenReturn("xxxxx");

    BufferedReader bufferReadMock = Mockito.mock(BufferedReader.class);
    when(bufferReadMock.readLine()).thenThrow(new IOException("fooex"));

    ProtocolVersion pv = new ProtocolVersion(1, 1, theLanguageMock);
    pv.setBufferReader(bufferReadMock);

    RFBProtocolException vncProEx =
        assertThrows(
            RFBProtocolException.class,
            () -> {
              pv.decode(null);
            });

    assertEquals("xxxxx", vncProEx.getMessage());
  }

  @Test
  public void testDecode_invalidProtocolLine() throws IOException {

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText("PROTOCOL_SERVER_EXCEPTION", "RZF 001.001")).thenReturn("aaaaaa");

    BufferedReader bufferReadMock = Mockito.mock(BufferedReader.class);
    when(bufferReadMock.readLine()).thenReturn("RZF 001.001");

    ProtocolVersion pv = new ProtocolVersion(1, 1, theLanguageMock);
    pv.setBufferReader(bufferReadMock);

    RFBProtocolException vncProEx =
        assertThrows(
            RFBProtocolException.class,
            () -> {
              pv.decode(null);
            });

    assertEquals("aaaaaa", vncProEx.getMessage());
  }

  @Test
  public void testDecode_validSintax() throws IOException {

    BufferedReader bufferReadMock = Mockito.mock(BufferedReader.class);
    when(bufferReadMock.readLine()).thenReturn("RFB 001.001");

    ProtocolVersion pv = new ProtocolVersion(1, 1, null);
    pv.setBufferReader(bufferReadMock);

    assertDoesNotThrow(
        () -> {
          ProtocolVersion serverProtocol = pv.decode(null);
          assertEquals(1, serverProtocol.getMajor());
          assertEquals(1, serverProtocol.getMinor());
        });
  }
}
