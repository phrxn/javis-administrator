package com.quazzom.javis.administrator.persistence;

import java.util.List;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.vnc.Computer;

public class ComputerPersistenceSwitch implements ComputerPersistence {

  private ComputerPersistence computerPersistenceImp;

  public ComputerPersistenceSwitch(GeneralConfiguration generalConfiguration) {
    if (generalConfiguration.getExecutionMode() == GeneralConfiguration.ExecutionModeOptions.DEMO)
      computerPersistenceImp = new ComputerPersistenceInMemory();
    else if (generalConfiguration.getExecutionMode()
        == GeneralConfiguration.ExecutionModeOptions.NORMAL)
      computerPersistenceImp =
          new ComputerPersistenceSQLServer(new ConnectionFactorySQLServer(generalConfiguration));
  }

  @Override
  public Computer createComputer(Computer c) throws PersistenceException {
    return computerPersistenceImp.createComputer(c);
  }

  @Override
  public boolean deleteComputer(Computer c) throws PersistenceException {
    return computerPersistenceImp.deleteComputer(c);
  }

  @Override
  public boolean updateComputer(Computer c) throws PersistenceException {
    return computerPersistenceImp.updateComputer(c);
  }

  @Override
  public List<Computer> findAll() throws PersistenceException {
    return computerPersistenceImp.findAll();
  }

  @Override
  public Computer findComputer(int id) throws PersistenceException {
    return computerPersistenceImp.findComputer(id);
  }
}
