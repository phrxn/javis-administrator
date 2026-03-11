package com.quazzom.javis.administrator.persistence;

import java.util.List;
import com.quazzom.javis.administrator.vnc.Computer;

public interface ComputerPersistence {

  /**
   * Save the computer and return a new computer with a valid ID
   *
   * @param c the computer to save
   * @return the new computer with a valid ID
   * @throws PersistenceException if any error occurs while saving the computer
   */
  public Computer createComputer(Computer c) throws PersistenceException;

  public boolean deleteComputer(Computer c) throws PersistenceException;

  public boolean updateComputer(Computer c) throws PersistenceException;

  public Computer findComputer(int id) throws PersistenceException;

  public List<Computer> findAll() throws PersistenceException;
}
