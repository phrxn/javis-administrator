package com.quazzom.javis.administrator.persistence;

import java.util.Optional;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.persistence.h2.ConnectionFactoryH2;
import com.quazzom.javis.administrator.persistence.h2.VNCProgramConfigurationPersistenceH2;
import com.quazzom.javis.administrator.persistence.memory.VNCProgramConfigurationPersistenceInMemory;
import com.quazzom.javis.administrator.persistence.sql_server.ConnectionFactorySQLServer;
import com.quazzom.javis.administrator.persistence.sql_server.VNCProgramConfigurationPersistenceSQLServer;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCProgramConfigurationPersistenceSwitch
    implements VNCProgramConfigurationPersistence {

  private VNCProgramConfigurationPersistence vncProgramConfigurationPersistence;

  public VNCProgramConfigurationPersistenceSwitch(GeneralConfiguration generalConfiguration) {
    if (generalConfiguration.getExecutionMode() == GeneralConfiguration.ExecutionModeOptions.DEMO)
      vncProgramConfigurationPersistence = new VNCProgramConfigurationPersistenceInMemory();
    else if (generalConfiguration.getExecutionMode()
        == GeneralConfiguration.ExecutionModeOptions.NORMAL) {
      if (generalConfiguration.getStorageType()
          == GeneralConfiguration.StorageTypeOptions.SQL_SERVER) {
        vncProgramConfigurationPersistence =
            new VNCProgramConfigurationPersistenceSQLServer(
                new ConnectionFactorySQLServer(generalConfiguration));
      } else {
        vncProgramConfigurationPersistence =
            new VNCProgramConfigurationPersistenceH2(new ConnectionFactoryH2(generalConfiguration));
      }
    }
  }

  @Override
  public boolean update(VNCProgramConfiguration vncProgram) throws PersistenceException {
    return vncProgramConfigurationPersistence.update(vncProgram);
  }

  @Override
  public Optional<VNCProgramConfiguration> findDefaultConfiguration() throws PersistenceException {
    return vncProgramConfigurationPersistence.findDefaultConfiguration();
  }
}
