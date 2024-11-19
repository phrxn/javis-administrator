package unit.com.quazzom.javis.administrator.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.persistence.ComputerPersistenceInMemory;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IP;
import com.quazzom.javis.administrator.vnc.IPException;

public class ComputerPersistenceInMemoryTest_UnitTest {

  @Test
  public void testDefaultContructor() {
    new ComputerPersistenceInMemory();
  }

  @BeforeEach
  public void resetStaticComputerList() {
    ComputerPersistenceInMemory.resetStaticComputerList();
  }

  @Test
  public void testIfTheListIsCreatedOnlyonce() {

    int sizeBeforeFirstClassInstance = ComputerPersistenceInMemory.getListComputers().size();

    assertEquals(0, sizeBeforeFirstClassInstance);

    new ComputerPersistenceInMemory();

    int sizeAfterFirstClassInstance = ComputerPersistenceInMemory.getListComputers().size();

    assertEquals(3, sizeAfterFirstClassInstance);

    new ComputerPersistenceInMemory();

    int sizeAfterSecondClassInstance = ComputerPersistenceInMemory.getListComputers().size();

    // the list size MUST continue 3
    assertEquals(3, sizeAfterSecondClassInstance);
  }

  // ----------------------------------------------------------

  @Test
  public void testCreateComputer() throws ComputerException, IPException, PersistenceException {

    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory();

    Computer newComputer = new Computer(null);
    newComputer.setHostname("new hostname1");
    Computer newComputerWithId = cpim.createComputer(newComputer);
    newComputer.setHostname("new hostname2");
    Computer newComputerWithId2 = cpim.createComputer(newComputer);

    assertEquals(4, newComputerWithId.getId());
    assertEquals(5, newComputerWithId2.getId());
  }

  @Test
  public void testCreateComputer_tryCreateAComputerWithSameHostnameAndIP()
      throws ComputerException, IPException, PersistenceException {

    Text mockTheLanguage = Mockito.mock(Text.class);
    when(mockTheLanguage.getText(
            "CREATE_COMPUTER_COMPUTER_ALREADY_EXIST_EXCEPTION", "HOSTNAME", "100.100.100.100"))
        .thenReturn("xxxxx");

    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory(mockTheLanguage);

    Computer computer1 = new Computer();
    computer1.setHostname("hostname");
    computer1.setIP(new IP("100.100.100.100"));

    Computer computer2 = new Computer();
    computer2.setHostname("hostname");
    computer2.setIP(new IP("100.100.100.100"));

    assertDoesNotThrow(
        () -> {
          cpim.createComputer(computer1);
        });

    PersistenceException pe =
        assertThrows(
            PersistenceException.class,
            () -> {
              cpim.createComputer(computer2);
            });
    assertEquals("xxxxx", pe.getMessage());
  }

  // ----------------------------------------------------------

  @Test
  public void testDeleteComputer() throws ComputerException, IPException, PersistenceException {

    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory();

    Computer newComputer = new Computer(null);

    Computer newComputerWithId = cpim.createComputer(newComputer);

    assertEquals(4, newComputerWithId.getId());

    boolean computerWasDeleted = cpim.deleteComputer(newComputerWithId);

    assertTrue(computerWasDeleted);

    // since the computer was deleted the return MUST BE false
    computerWasDeleted = cpim.deleteComputer(newComputerWithId);
    assertFalse(computerWasDeleted);
  }

  @Test
  public void testFindAll() {
    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory();

    List<Computer> listOfComputers = cpim.findAll();

    assertEquals(listOfComputers.size(), 3);
    assertEquals(listOfComputers, ComputerPersistenceInMemory.getListComputers());
  }

  // ----------------------

  @Test
  public void testFind_theIdDoesntExist() {
    Text mockTheLanguage = Mockito.mock(Text.class);
    when(mockTheLanguage.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", -1)).thenReturn("xxxxx");

    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory(mockTheLanguage);

    PersistenceException pe =
        assertThrows(
            PersistenceException.class,
            () -> {
              cpim.findComputer(-1);
            });
    assertEquals("xxxxx", pe.getMessage());
  }

  @Test
  public void testFind_theIdExist() {
    Text mockTheLanguage = Mockito.mock(Text.class);
    when(mockTheLanguage.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", -1)).thenReturn("xxxxx");

    ComputerPersistenceInMemory cpim = new ComputerPersistenceInMemory(mockTheLanguage);

    assertDoesNotThrow(
        () -> {
          cpim.findComputer(1);
        });
  }
}
