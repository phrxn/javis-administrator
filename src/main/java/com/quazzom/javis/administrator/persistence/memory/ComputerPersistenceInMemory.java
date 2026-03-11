package com.quazzom.javis.administrator.persistence.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.persistence.ComputerPersistence;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.Computer.PowerStatus;
import com.quazzom.javis.administrator.vnc.Computer.TypeOfLoginSession;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IPException;

public class ComputerPersistenceInMemory implements ComputerPersistence {

  private static List<Computer> listComputer = new ArrayList<Computer>();
  private static boolean isListInitialized = false;
  private static int fakeCount = 1;
  private Text theLanguage;

  private static void startList() {
    try {
      Computer c1 = new Computer();
      c1.setId(fakeCount++);
      c1.setPowerStatus(PowerStatus.POWERON);
      c1.setSessionType(TypeOfLoginSession.SESSION_LIX_LOGON);
      c1.setUsername("MARY");
      c1.setHostname("PCSALES0001");
      c1.setManual(false);
      c1.setLoginDate(new Date(100000));
      c1.setJavisClientVersion("v1");
      c1.setDetails("Good Day!");
      listComputer.add(c1);

      Computer c2 = new Computer();
      c2.setId(fakeCount++);
      c2.setPowerStatus(PowerStatus.POWERON);
      c2.setSessionType(TypeOfLoginSession.SESSION_LIX_LOGON);
      c2.setUsername("JOHN.STUART");
      c2.setHostname("PCGAD030");
      c2.setManual(false);
      c2.setLoginDate(new Date(3000000));
      c2.setJavisClientVersion("v2");
      c2.setDetails("Good Day!");
      listComputer.add(c2);

      Computer c3 = new Computer();
      c3.setId(fakeCount++);
      c3.setPowerStatus(PowerStatus.POWERON);
      c3.setSessionType(TypeOfLoginSession.SESSION_LIX_LOGON);
      c3.setUsername("PAUL");
      c3.setHostname("PCLAB051");
      c3.setManual(false);
      c3.setLoginDate(new Date(3000000));
      c3.setJavisClientVersion("v3");
      c3.setDetails("Good Day!");
      listComputer.add(c3);

      isListInitialized = true;

    } catch (ComputerException | IPException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Computer> getListComputers() {
    return listComputer;
  }

  public static void resetStaticComputerList() {
    isListInitialized = false;
    listComputer.clear();
    fakeCount = 1;
  }

  public ComputerPersistenceInMemory() {
    this(LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER_PERSISTENCE_IN_MEMORY));
  }

  public ComputerPersistenceInMemory(Text theLanguage) {
    if (!isListInitialized) startList();
    this.theLanguage = theLanguage;
  }

  @Override
  public Computer createComputer(Computer c) throws PersistenceException {

    // check if a computer with this hostname and ip already exists
    for (Computer tmpComputer : listComputer) {
      if (c.getHostname().equals(tmpComputer.getHostname())
          && c.getIPStr().equals(tmpComputer.getIPStr())) {
        try {
          c.setId(tmpComputer.getId());
        } catch (ComputerException e) {
          throw new RuntimeException(e.getMessage());
        }
        updateComputer(c);
        throw new PersistenceException(
            theLanguage.getText(
                "CREATE_COMPUTER_COMPUTER_ALREADY_EXIST_EXCEPTION", c.getHostname(), c.getIPStr()));
      }
    }

    try {
      Computer newComputer = c.clone();
      newComputer.setId(fakeCount++);
      listComputer.add(newComputer);
      return newComputer;
    } catch (ComputerException e) {
      throw new PersistenceException(e.getMessage());
    }
  }

  @Override
  public boolean deleteComputer(Computer c) {
    return listComputer.remove(c);
  }

  @Override
  public boolean updateComputer(Computer c) {
    int computerIndexInList = listComputer.indexOf(c);

    Computer newComputer = c.clone();

    if (computerIndexInList == -1) return false;

    listComputer.set(computerIndexInList, newComputer);

    return true;
  }

  @Override
  public List<Computer> findAll() {
    return new ArrayList<Computer>(listComputer);
  }

  @Override
  public Computer findComputer(int id) throws PersistenceException {

    for (Computer c : listComputer) {
      if (c.getId() == id) return c.clone();
    }
    throw new PersistenceException(theLanguage.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", id));
  }
}
