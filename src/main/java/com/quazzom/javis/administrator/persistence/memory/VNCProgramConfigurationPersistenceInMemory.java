package com.quazzom.javis.administrator.persistence.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistence;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCProgramConfigurationPersistenceInMemory
    implements VNCProgramConfigurationPersistence {

  private static List<VNCProgramConfiguration> listVNCConfig =
      new ArrayList<VNCProgramConfiguration>();

  public static List<VNCProgramConfiguration> getListVNCconfig() {
    return listVNCConfig;
  }

  public static void resetStaticVNCconfigList() {
    listVNCConfig.clear();
  }

  @Override
  public boolean update(VNCProgramConfiguration vncProgram) throws PersistenceException {
    if (listVNCConfig.size() == 0) {
      return listVNCConfig.add(vncProgram);
    }
    listVNCConfig.set(0, vncProgram);
    return true;
  }

  @Override
  public Optional<VNCProgramConfiguration> findDefaultConfiguration() throws PersistenceException {
    if (listVNCConfig.size() > 0) {
      return Optional.of(listVNCConfig.get(0));
    }
    return Optional.empty();
  }
}
