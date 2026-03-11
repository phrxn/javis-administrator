package integration.com.quazzom.javis.administrator.persistence.sql_server;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.sql_server.ComputerPersistenceSQLServer;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.Computer.PowerStatus;
import com.quazzom.javis.administrator.vnc.Computer.TypeOfLoginSession;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IP;
import com.quazzom.javis.administrator.vnc.IPException;

public class ComputerPersistenceSQLServer_IntegrationTest {

  @Test
  public void testCreateComputer_createNewComputers()
      throws ComputerException, IPException, SQLException {

    Text textMock = Mockito.mock(Text.class);

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer firstComputer = new Computer();
    firstComputer.setUsername("first_user");
    firstComputer.setHostname("first_hostname");

    Computer secondComputer = new Computer();
    secondComputer.setUsername("second_user");
    secondComputer.setHostname("second_hostname");

    Computer thirdComputer = new Computer();
    thirdComputer.setUsername("third_user");
    thirdComputer.setHostname("third_hostname");

    assertDoesNotThrow(
        () -> {
          Computer firstComputerReturn = cps.createComputer(firstComputer);
          Computer secondComputerReturn = cps.createComputer(secondComputer);
          Computer thirdComputerReturn = cps.createComputer(thirdComputer);

          assertEquals(1, firstComputerReturn.getId());
          assertEquals(2, secondComputerReturn.getId());
          assertEquals(3, thirdComputerReturn.getId());
        });
  }

  @Test
  public void testCreateComputer_theComputerWithHostnameAndIPAlreadyExist()
      throws ComputerException, IPException, SQLException {

    Text textMock = Mockito.mock(Text.class);
    when(textMock.getText(
            "CREATE_COMPUTER_COMPUTER_ALREADY_EXIST_EXCEPTION", "HOSTNAME", "100.101.102.103"))
        .thenReturn("xxxx");

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer firstComputer = new Computer();
    firstComputer.setUsername("first_user");
    firstComputer.setHostname("hostname");
    firstComputer.setIP(new IP("100.101.102.103"));

    Computer secondComputer = new Computer();
    secondComputer.setUsername("second_user");
    secondComputer.setHostname("hostname");
    secondComputer.setIP(new IP("100.101.102.103"));

    PersistenceException pe =
        assertThrows(
            PersistenceException.class,
            () -> {
              cps.createComputer(firstComputer);
              cps.createComputer(secondComputer);
            });
    assertEquals("xxxx", pe.getMessage());
  }

  // --------------------------------------------------------------------

  @Test
  public void testDeleteComputer() throws ComputerException, IPException, PersistenceException {

    Text textMock = Mockito.mock(Text.class);

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer computerToDelete = new Computer();
    computerToDelete.setUsername("to delete u");
    computerToDelete.setHostname("to delete h");

    cps.createComputer(computerToDelete);

    boolean computerWasDelete = cps.deleteComputer(computerToDelete);

    assertTrue(computerWasDelete);
  }

  @Test
  public void testDeleteComputer_computerDoesntExist()
      throws ComputerException, IPException, PersistenceException {

    Text textMock = Mockito.mock(Text.class);

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer computerToDelete = new Computer();

    boolean computerWasDelete = cps.deleteComputer(computerToDelete);

    assertFalse(computerWasDelete);
  }

  // --------------------------------------------------------------------

  @Test
  public void testFindComputer_idDoesntExist() {

    Text textMock = Mockito.mock(Text.class);
    when(textMock.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", 1)).thenReturn("not found");

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    PersistenceException pe =
        assertThrows(
            PersistenceException.class,
            () -> {
              cps.findComputer(1);
            });

    assertEquals("not found", pe.getMessage());
  }

  @Test
  public void testFindComputer_idExist()
      throws ComputerException, IPException, PersistenceException {

    Text textMock = Mockito.mock(Text.class);
    when(textMock.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", 1)).thenReturn("not found");

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer computerToSave = new Computer();
    computerToSave.setPowerStatus(PowerStatus.INVALID);
    computerToSave.setSessionType(TypeOfLoginSession.CONSOLE_CONNECT);
    // names in db are saved in upper case
    computerToSave.setUsername("username1 before".toUpperCase());
    computerToSave.setHostname("hostaname1 before");
    computerToSave.setIP(new IP("111.112.113.114"));
    computerToSave.setManual(true);
    computerToSave.setLoginDate(new Date(1000));
    computerToSave.setJavisClientVersion("v1 before");
    computerToSave.setDetails("d1 before");

    cps.createComputer(computerToSave);

    Computer computerReturned = cps.findComputer(1);

    assertTrue(compareComputersAreComputerEquals(computerToSave, computerReturned));
  }

  // --------------------------------------------------------------------

  @Test
  public void testUpdateComputer() throws ComputerException, IPException, PersistenceException {

    Text textMock = Mockito.mock(Text.class);

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer computerToUpdate1 = new Computer();

    computerToUpdate1.setPowerStatus(PowerStatus.INVALID);
    computerToUpdate1.setSessionType(TypeOfLoginSession.CONSOLE_CONNECT);
    computerToUpdate1.setUsername("username1 before");
    computerToUpdate1.setHostname("hostaname1 before");
    computerToUpdate1.setIP(new IP("111.112.113.114"));
    computerToUpdate1.setManual(true);
    computerToUpdate1.setLoginDate(new Date(1000));
    computerToUpdate1.setJavisClientVersion("v1 before");
    computerToUpdate1.setDetails("d1 before");

    Computer computerToUpdate2 = new Computer();

    computerToUpdate2.setPowerStatus(PowerStatus.POWEROFF);
    computerToUpdate2.setSessionType(TypeOfLoginSession.REMOTE_CONNECT);
    computerToUpdate2.setUsername("username2 before");
    computerToUpdate2.setHostname("hostaname2 before");
    computerToUpdate2.setIP(new IP("121.122.123.124"));
    computerToUpdate2.setManual(true);
    computerToUpdate2.setLoginDate(new Date(2000));
    computerToUpdate2.setJavisClientVersion("v2 before");
    computerToUpdate2.setDetails("d2 before");

    Computer computerToUpdate3 = new Computer();

    computerToUpdate3.setPowerStatus(PowerStatus.POWEROFF);
    computerToUpdate3.setSessionType(TypeOfLoginSession.SESSION_REMOTE_CONTROL);
    computerToUpdate3.setUsername("username3 before");
    computerToUpdate3.setHostname("hostaname3 before");
    computerToUpdate3.setIP(new IP("131.132.133.134"));
    computerToUpdate3.setManual(true);
    computerToUpdate3.setLoginDate(new Date(3000));
    computerToUpdate3.setJavisClientVersion("v3 before");
    computerToUpdate3.setDetails("d3 before");

    // first create the computer in the database
    cps.createComputer(computerToUpdate1);
    cps.createComputer(computerToUpdate2);
    cps.createComputer(computerToUpdate3);

    Computer computerToUpdate1Updated = new Computer();

    computerToUpdate1Updated.setId(1);
    computerToUpdate1Updated.setPowerStatus(PowerStatus.POWEROFF);
    computerToUpdate1Updated.setSessionType(TypeOfLoginSession.CONSOLE_DISCONNECT);
    computerToUpdate1Updated.setUsername("username1 after".toUpperCase());
    computerToUpdate1Updated.setHostname("hostaname1 after");
    computerToUpdate1Updated.setIP(new IP("112.113.114.115"));
    computerToUpdate1Updated.setManual(false);
    computerToUpdate1Updated.setLoginDate(new Date(4000));
    computerToUpdate1Updated.setJavisClientVersion("v1 after");
    computerToUpdate1Updated.setDetails("d1 after");

    Computer computerToUpdate2Updated = new Computer();

    computerToUpdate2Updated.setId(2);
    computerToUpdate2Updated.setPowerStatus(PowerStatus.INVALID);
    computerToUpdate2Updated.setSessionType(TypeOfLoginSession.REMOTE_DISCONNECT);
    computerToUpdate2Updated.setUsername("username2 after".toUpperCase());
    computerToUpdate2Updated.setHostname("hostaname2 after");
    computerToUpdate2Updated.setIP(new IP("122.123.124.125"));
    computerToUpdate2Updated.setManual(false);
    computerToUpdate2Updated.setLoginDate(new Date(5000));
    computerToUpdate2Updated.setJavisClientVersion("v2 after");
    computerToUpdate2Updated.setDetails("d2 after");

    Computer computerToUpdate3Updated = new Computer();

    computerToUpdate3Updated.setId(3);
    computerToUpdate3Updated.setPowerStatus(PowerStatus.POWERON);
    computerToUpdate3Updated.setSessionType(TypeOfLoginSession.SESSION_LOGOFF);
    computerToUpdate3Updated.setUsername("username3 after".toUpperCase());
    computerToUpdate3Updated.setHostname("hostaname3 after");
    computerToUpdate3Updated.setIP(new IP("132.133.134.135"));
    computerToUpdate3Updated.setManual(false);
    computerToUpdate3Updated.setLoginDate(new Date(6000));
    computerToUpdate3Updated.setJavisClientVersion("v3 after");
    computerToUpdate3Updated.setDetails("d3 after");

    // first create the computer in the database
    cps.updateComputer(computerToUpdate1Updated);
    cps.updateComputer(computerToUpdate2Updated);
    cps.updateComputer(computerToUpdate3Updated);

    // find updated computers
    Computer computer1Return = cps.findComputer(1);
    Computer computer2Return = cps.findComputer(2);
    Computer computer3Return = cps.findComputer(3);

    assertTrue(compareComputersAreComputerEquals(computer1Return, computerToUpdate1Updated));
    assertTrue(compareComputersAreComputerEquals(computer2Return, computerToUpdate2Updated));
    assertTrue(compareComputersAreComputerEquals(computer3Return, computerToUpdate3Updated));
  }

  // --------------------------------------------------------------------

  @Test
  public void testFindAll() throws ComputerException, IPException, PersistenceException {

    Text textMock = Mockito.mock(Text.class);

    ComputerPersistenceSQLServer cps =
        new ComputerPersistenceSQLServer(new ConnectionFactorySQLServerTest(), textMock);

    Computer computer1 = new Computer();

    computer1.setPowerStatus(PowerStatus.INVALID);
    computer1.setSessionType(TypeOfLoginSession.CONSOLE_CONNECT);
    computer1.setUsername("username1 before");
    computer1.setHostname("hostaname1 before");
    computer1.setIP(new IP("111.112.113.114"));
    computer1.setManual(true);
    computer1.setLoginDate(new Date(1000));
    computer1.setJavisClientVersion("v1 before");
    computer1.setDetails("d1 before");

    Computer computer2 = new Computer();

    computer2.setPowerStatus(PowerStatus.POWEROFF);
    computer2.setSessionType(TypeOfLoginSession.REMOTE_CONNECT);
    computer2.setUsername("username2 before");
    computer2.setHostname("hostaname2 before");
    computer2.setIP(new IP("121.122.123.124"));
    computer2.setManual(true);
    computer2.setLoginDate(new Date(2000));
    computer2.setJavisClientVersion("v2 before");
    computer2.setDetails("d2 before");

    Computer computer3 = new Computer();

    computer3.setPowerStatus(PowerStatus.POWEROFF);
    computer3.setSessionType(TypeOfLoginSession.SESSION_REMOTE_CONTROL);
    computer3.setUsername("username3 before");
    computer3.setHostname("hostaname3 before");
    computer3.setIP(new IP("131.132.133.134"));
    computer3.setManual(true);
    computer3.setLoginDate(new Date(3000));
    computer3.setJavisClientVersion("v3 before");
    computer3.setDetails("d3 before");

    // first create the computer in the database
    cps.createComputer(computer1);
    cps.createComputer(computer2);
    cps.createComputer(computer3);

    List<Computer> listFromDatabase = cps.findAll();

    assertEquals(3, listFromDatabase.size());
  }

  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //
  //

  private static void clearAndResetComputerTable() throws SQLException {

    ConnectionFactorySQLServerTest cft = new ConnectionFactorySQLServerTest();

    Connection conn = cft.getConnection();
    Statement stmt = conn.createStatement();

    String sql = "TRUNCATE TABLE computers;";

    stmt.executeUpdate(sql);

    conn.commit();
  }

  @BeforeEach
  public void resetComputerTable() throws SQLException {
    clearAndResetComputerTable();
  }

  @AfterAll
  public static void clearTable() throws SQLException {
    clearAndResetComputerTable();
  }

  //

  private boolean compareComputersAreComputerEquals(Computer c1, Computer c2) {

    if (c1.getId() != c2.getId()) return false;
    if (c1.getPowerStatus() != c2.getPowerStatus()) return false;
    if (c1.getSessionType() != c2.getSessionType()) return false;
    if (!c1.getUsername().equals(c2.getUsername())) return false;
    if (!c1.getHostname().equals(c2.getHostname())) return false;
    if (!c1.getIP().equals(c2.getIP())) return false;
    if (c1.isManual() != c2.isManual()) return false;
    if (!c1.getLoginDate().equals(c2.getLoginDate())) return false;
    if (!c1.getJavisClientVersion().equals(c2.getJavisClientVersion())) return false;
    if (!c1.getDetails().equals(c2.getDetails())) return false;
    return true;
  }
}
