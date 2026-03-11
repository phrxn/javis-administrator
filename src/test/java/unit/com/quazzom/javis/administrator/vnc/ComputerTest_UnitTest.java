package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.Computer.PowerStatus;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IP;
import com.quazzom.javis.administrator.vnc.IPException;

public class ComputerTest_UnitTest {

  @Test
  public void testsSimpleConstructor() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    assertDoesNotThrow(
        () -> {
          new Computer(text);
        });
  }

  @Test
  public void testSeId_invalidValue() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);
    when(text.getText("INVALID_ID_EXCEPTION", 0)).thenReturn("invalid id");

    Computer c = new Computer(text);

    ComputerException ce =
        assertThrows(
            ComputerException.class,
            () -> {
              // invalid value
              c.setId(Computer.MIN_ID_VALUE - 1);
            });
    assertEquals("invalid id", ce.getMessage());
  }

  @Test
  public void testSeId_validValue() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);
    when(text.getText("INVALID_ID_EXCEPTION", 0)).thenReturn("invalid id");

    Computer c = new Computer(text);

    assertDoesNotThrow(
        () -> {
          c.setId(1);
        });
  }

  @Test
  public void testSetPowerStatus_invalid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("POWERSTATUS_TYPE_EXCEPTION", -1)).thenReturn("invalid -1");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setPowerStatus(-1);
            });

    verify(text, times(1)).getText("POWERSTATUS_TYPE_EXCEPTION", -1);

    assertEquals("invalid -1", re.getMessage());
  }

  @Test
  public void testSetPowerStatus_valid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setPowerStatus(1);
        });

    assertEquals(Computer.PowerStatus.POWEROFF, computer.getPowerStatus());
  }

  @Test
  public void testSetSessionType_invalid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("SESSION_TYPE_EXCEPTION", -1)).thenReturn("invalidx -1");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setSessionType(-1);
            });

    verify(text, times(1)).getText("SESSION_TYPE_EXCEPTION", -1);

    assertEquals("invalidx -1", re.getMessage());
  }

  @Test
  public void testSetSessionType_valid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setSessionType(2);
        });

    assertEquals(Computer.TypeOfLoginSession.CONSOLE_CONNECT, computer.getSessionType());
  }

  @Test
  public void testSetUsername_emptyString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_USERNAME_EMPTY_EXCEPTION")).thenReturn("empty String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setUsername("");
            });

    verify(text, times(1)).getText("INVALID_USERNAME_EMPTY_EXCEPTION");

    assertEquals("empty String", re.getMessage());
  }

  @Test
  public void testSetUsername_stringOnlyWithBlankChars() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_USERNAME_EMPTY_EXCEPTION")).thenReturn("empty String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setUsername("    \n   \t \t \r \n");
            });

    verify(text, times(1)).getText("INVALID_USERNAME_EMPTY_EXCEPTION");

    assertEquals("empty String", re.getMessage());
  }

  @Test
  public void testSetUsername_bigString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_USERNAME_SIZE_EXCEPTION", 255)).thenReturn("big String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              StringBuilder sb = new StringBuilder();
              for (int count = 0; count < Computer.MAX_STRING_SIZE_USERNAME + 1; count++) {
                sb.append('a');
              }
              computer.setUsername(sb.toString());
            });

    verify(text, times(1)).getText("INVALID_USERNAME_SIZE_EXCEPTION", 255);

    assertEquals("big String", re.getMessage());
  }

  @Test
  public void testSetUsername_valid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setUsername("valid name");
        });
  }

  @Test
  public void testSetUsername_testTrim() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setUsername(" valid name ");
        });

    assertEquals("VALID NAME", computer.getUsername());
  }

  @Test
  public void testSetHostname_emptyString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_HOSTNAME_EMPTY_EXCEPTION")).thenReturn("empty String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setHostname("");
            });

    verify(text, times(1)).getText("INVALID_HOSTNAME_EMPTY_EXCEPTION");

    assertEquals("empty String", re.getMessage());
  }

  @Test
  public void testSetHostname_stringOnlyWithBlankChars() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_HOSTNAME_EMPTY_EXCEPTION")).thenReturn("empty String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              computer.setHostname("    \n   \t \t \r \n");
            });

    verify(text, times(1)).getText("INVALID_HOSTNAME_EMPTY_EXCEPTION");

    assertEquals("empty String", re.getMessage());
  }

  @Test
  public void testSetHostname_bigString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_HOSTNAME_SIZE_EXCEPTION", 64)).thenReturn("big String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              StringBuilder sb = new StringBuilder();
              for (int count = 0; count < Computer.MAX_STRING_SIZE_HOSTNAME + 1; count++) {
                sb.append('a');
              }
              computer.setHostname(sb.toString());
            });

    verify(text, times(1)).getText("INVALID_HOSTNAME_SIZE_EXCEPTION", 64);

    assertEquals("big String", re.getMessage());
  }

  @Test
  public void testSetHostname_valid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setHostname("valid hostname");
        });
  }

  @Test
  public void testSetHostname_testTrim() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setHostname("  valid hostname  ");
        });
    assertEquals("VALID HOSTNAME", computer.getHostname());
  }

  @Test
  public void testSetHostname_UpperCase() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setHostname("valid hostname");
        });
    assertEquals("VALID HOSTNAME", computer.getHostname());
  }

  //
  //
  //
  //
  //

  @Test
  public void testJavisClientVersion_emptyString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setJavisClientVersion("");
        });

    assertEquals("-", computer.getJavisClientVersion());
  }

  @Test
  public void testJavisClientVersion_stringOnlyWithBlankChars()
      throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setJavisClientVersion("   \t \t  \n \r   ");
        });

    assertEquals("-", computer.getJavisClientVersion());
  }

  @Test
  public void testJavisClientVersion_stringOnlyWithBlankCharacters()
      throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setJavisClientVersion(" \t \r    ");
        });

    assertEquals("-", computer.getJavisClientVersion());
  }

  @Test
  public void testJavisClientVersion_bigString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("JAVIS_CLIENT_VERSION_SIZE_EXCEPTION", 20)).thenReturn("big String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              StringBuilder sb = new StringBuilder();
              for (int count = 0; count < Computer.MAX_STRING_SIZE_CLIENT_VERSION + 1; count++) {
                sb.append('a');
              }
              computer.setJavisClientVersion(sb.toString());
            });

    verify(text, times(1)).getText("JAVIS_CLIENT_VERSION_SIZE_EXCEPTION", 20);

    assertEquals("big String", re.getMessage());
  }

  @Test
  public void testJavisClientVersion_valid() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    Computer computer = new Computer(text);

    assertDoesNotThrow(
        () -> {
          computer.setHostname("-");
        });
  }

  @Test
  public void testSetDetails_bigString() throws ComputerException, IPException {

    Text text = Mockito.mock(Text.class);

    when(text.getText("INVALID_DETAILS_SIZE_EXCEPTION", 255)).thenReturn("big String");

    Computer computer = new Computer(text);

    ComputerException re =
        assertThrows(
            ComputerException.class,
            () -> {
              StringBuilder sb = new StringBuilder();
              for (int count = 0; count < Computer.MAX_STRING_SIZE_DETAILS + 1; count++) {
                sb.append('a');
              }
              computer.setDetails(sb.toString());
            });

    verify(text, times(1)).getText("INVALID_DETAILS_SIZE_EXCEPTION", 255);

    assertEquals("big String", re.getMessage());
  }

  @Test
  public void testClone() throws ComputerException, IPException {

    Text theLanguage = Mockito.mock(Text.class);

    Computer computerBase = new Computer(theLanguage);
    computerBase.setId(5);
    computerBase.setPowerStatus(PowerStatus.POWEROFF);
    computerBase.setSessionType(Computer.TypeOfLoginSession.SESSION_LOCK);
    computerBase.setUsername("a");
    computerBase.setHostname("b");
    computerBase.setIP(new IP(IP.ANDRESS_NOTHING));
    computerBase.setManual(false);
    computerBase.setLoginDate(new Date(1));
    computerBase.setJavisClientVersion("-");
    computerBase.setDetails("c");

    Computer computerClone = computerBase.clone();

    assertEquals(computerBase.getId(), computerClone.getId());
    assertEquals(computerBase.getPowerStatus(), computerClone.getPowerStatus());
    assertEquals(computerBase.getSessionType(), computerClone.getSessionType());
    assertEquals(computerBase.getUsername(), computerClone.getUsername());
    assertEquals(computerBase.getHostname(), computerClone.getHostname());
    assertEquals(computerBase.getIP(), computerClone.getIP());
    assertEquals(computerBase.isManual(), computerClone.isManual());
    assertEquals(computerBase.getLoginDate(), computerClone.getLoginDate());
    assertEquals(computerBase.getJavisClientVersion(), computerClone.getJavisClientVersion());
    assertEquals(computerBase.getDetails(), computerClone.getDetails());
    assertEquals(computerBase.getTheLanguage(), computerClone.getTheLanguage());

    computerClone.setId(6);
    computerClone.setPowerStatus(PowerStatus.POWERON);
    computerClone.setSessionType(Computer.TypeOfLoginSession.CONSOLE_CONNECT);
    computerClone.setUsername("1");
    computerClone.setHostname("2");
    computerClone.setIP(new IP("192.168.51.100"));
    computerClone.setManual(true);
    computerClone.setLoginDate(new Date(2));
    computerClone.setJavisClientVersion("-");
    computerClone.setDetails("3");

    assertNotEquals(computerBase.getId(), computerClone.getId());
    assertNotEquals(computerBase.getPowerStatus(), computerClone.getPowerStatus());
    assertNotEquals(computerBase.getSessionType(), computerClone.getSessionType());
    assertNotEquals(computerBase.getUsername(), computerClone.getUsername());
    assertNotEquals(computerBase.getHostname(), computerClone.getHostname());
    assertNotEquals(computerBase.getIP(), computerClone.getIP());
    assertNotEquals(computerBase.isManual(), computerClone.isManual());
    assertNotEquals(computerBase.getLoginDate(), computerClone.getLoginDate());
    assertEquals(computerBase.getJavisClientVersion(), computerClone.getJavisClientVersion());
    assertNotEquals(computerBase.getDetails(), computerClone.getDetails());
    assertEquals(computerBase.getTheLanguage(), computerClone.getTheLanguage());
  }
}
