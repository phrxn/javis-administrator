package unit.com.quazzom.javis.administrator.vnc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerFilter;

public class ComputerFilterTest_UnitTest {

  @Test
  public void theStringsMatch_caseSensitive() {

    String source = "foo";

    ComputerFilter cf = new ComputerFilter();

    assertTrue(cf.theStringsMatch(source, "", true));
    assertTrue(cf.theStringsMatch(source, "f", true));
    assertTrue(cf.theStringsMatch(source, "fo", true));
    assertTrue(cf.theStringsMatch(source, "foo", true));
    assertTrue(cf.theStringsMatch(source, "oo", true));
    assertTrue(cf.theStringsMatch(source, "o", true));

    assertFalse(cf.theStringsMatch(source, "fooo", false));

    assertFalse(cf.theStringsMatch(source, "F", true));
    assertFalse(cf.theStringsMatch(source, "Fo", true));
    assertFalse(cf.theStringsMatch(source, "FO", true));
    assertFalse(cf.theStringsMatch(source, "Foo", true));
    assertFalse(cf.theStringsMatch(source, "FOo", true));
    assertFalse(cf.theStringsMatch(source, "FOO", true));
    assertFalse(cf.theStringsMatch(source, "FOOO", false));
  }

  @Test
  public void theStringsMatch_caseInsensitive() {

    String source = "foo";

    ComputerFilter cf = new ComputerFilter();

    assertTrue(cf.theStringsMatch(source, "", false));
    assertTrue(cf.theStringsMatch(source, "f", false));
    assertTrue(cf.theStringsMatch(source, "fo", false));
    assertTrue(cf.theStringsMatch(source, "foo", false));
    assertFalse(cf.theStringsMatch(source, "fooo", false));

    assertTrue(cf.theStringsMatch(source, "F", false));
    assertTrue(cf.theStringsMatch(source, "Fo", false));
    assertTrue(cf.theStringsMatch(source, "FO", false));
    assertTrue(cf.theStringsMatch(source, "Foo", false));
    assertTrue(cf.theStringsMatch(source, "FOo", false));
    assertTrue(cf.theStringsMatch(source, "FOO", false));
    assertFalse(cf.theStringsMatch(source, "FOOO", false));
  }

  @Test
  public void computerMatchWithString_caseSensitive() {

    Computer c = Mockito.mock(Computer.class);

    ComputerFilter cf = new ComputerFilter();

    when(c.getPowerStatusStr()).thenReturn("a");
    when(c.getSessionTypeStr()).thenReturn("a");
    when(c.getUsername()).thenReturn("a");
    when(c.getHostname()).thenReturn("a");
    when(c.getIPStr()).thenReturn("a");
    when(c.isManualStr()).thenReturn("a");
    when(c.getLoginDateStr()).thenReturn("a");
    when(c.getJavisClientVersion()).thenReturn("a");
    when(c.getDetails()).thenReturn("a");

    assertFalse(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("A");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("A");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("A");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("A");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("A");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("A");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("A");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("A");
    when(c.getDetails()).thenReturn("z");

    assertTrue(cf.computerMatchWithString(c, "A", true));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("z");
    when(c.getSessionTypeStr()).thenReturn("z");
    when(c.getUsername()).thenReturn("z");
    when(c.getHostname()).thenReturn("z");
    when(c.getIPStr()).thenReturn("z");
    when(c.isManualStr()).thenReturn("z");
    when(c.getLoginDateStr()).thenReturn("z");
    when(c.getJavisClientVersion()).thenReturn("z");
    when(c.getDetails()).thenReturn("A");

    assertTrue(cf.computerMatchWithString(c, "A", true));
  }

  @Test
  public void computerMatchWithString_caseInsensitive() {

    Computer c = Mockito.mock(Computer.class);

    ComputerFilter cf = new ComputerFilter();

    when(c.getPowerStatusStr()).thenReturn("a");
    when(c.getSessionTypeStr()).thenReturn("b");
    when(c.getUsername()).thenReturn("c");
    when(c.getHostname()).thenReturn("d");
    when(c.getIPStr()).thenReturn("e");
    when(c.isManualStr()).thenReturn("f");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("a");
    when(c.getUsername()).thenReturn("c");
    when(c.getHostname()).thenReturn("d");
    when(c.getIPStr()).thenReturn("e");
    when(c.isManualStr()).thenReturn("f");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("a");
    when(c.getHostname()).thenReturn("d");
    when(c.getIPStr()).thenReturn("e");
    when(c.isManualStr()).thenReturn("f");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("a");
    when(c.getIPStr()).thenReturn("e");
    when(c.isManualStr()).thenReturn("f");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("e");
    when(c.getIPStr()).thenReturn("a");
    when(c.isManualStr()).thenReturn("f");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("e");
    when(c.getIPStr()).thenReturn("f");
    when(c.isManualStr()).thenReturn("a");
    when(c.getLoginDateStr()).thenReturn("g");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("e");
    when(c.getIPStr()).thenReturn("f");
    when(c.isManualStr()).thenReturn("g");
    when(c.getLoginDateStr()).thenReturn("a");
    when(c.getJavisClientVersion()).thenReturn("h");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("e");
    when(c.getIPStr()).thenReturn("f");
    when(c.isManualStr()).thenReturn("g");
    when(c.getLoginDateStr()).thenReturn("h");
    when(c.getJavisClientVersion()).thenReturn("a");
    when(c.getDetails()).thenReturn("i");

    assertTrue(cf.computerMatchWithString(c, "A", false));

    // ------------------------------

    when(c.getPowerStatusStr()).thenReturn("b");
    when(c.getSessionTypeStr()).thenReturn("c");
    when(c.getUsername()).thenReturn("d");
    when(c.getHostname()).thenReturn("e");
    when(c.getIPStr()).thenReturn("f");
    when(c.isManualStr()).thenReturn("g");
    when(c.getLoginDateStr()).thenReturn("h");
    when(c.getJavisClientVersion()).thenReturn("i");
    when(c.getDetails()).thenReturn("a");

    assertTrue(cf.computerMatchWithString(c, "A", false));
  }
}
