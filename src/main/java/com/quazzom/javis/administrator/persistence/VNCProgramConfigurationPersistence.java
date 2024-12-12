package com.quazzom.javis.administrator.persistence;

import java.util.Optional;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public interface VNCProgramConfigurationPersistence {

  public boolean update(VNCProgramConfiguration vncProgram) throws PersistenceException;

  /**
   * if an {@link VNCProgramConfiguration} is not found, the implementor of this method must return
   * an empty optional
   */
  public Optional<VNCProgramConfiguration> findDefaultConfiguration() throws PersistenceException;
}
