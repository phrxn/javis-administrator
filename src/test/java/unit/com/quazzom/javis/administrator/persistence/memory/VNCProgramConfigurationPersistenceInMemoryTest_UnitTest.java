package unit.com.quazzom.javis.administrator.persistence.memory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.memory.VNCProgramConfigurationPersistenceInMemory;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProgramConfigurationPersistenceInMemoryTest_UnitTest {

  @Test
  public void testFindDefaultConfiguration_emptyList() {

    VNCProgramConfigurationPersistenceInMemory.resetStaticVNCconfigList();

    VNCProgramConfigurationPersistenceInMemory vncConfiP =
        new VNCProgramConfigurationPersistenceInMemory();

    assertDoesNotThrow(
        () -> {
          Optional<VNCProgramConfiguration> vncConfig = vncConfiP.findDefaultConfiguration();
          assertTrue(vncConfig.isEmpty());
        });
  }

  @Test
  public void testFindDefaultConfiguration_doesntEmptyList() throws PersistenceException {

    VNCProgramConfigurationPersistenceInMemory.resetStaticVNCconfigList();

    VNCProgramConfigurationPersistenceInMemory vncConfiP =
        new VNCProgramConfigurationPersistenceInMemory();

    VNCProgramConfiguration vncPrograConf = new VNCProgramConfiguration();
    vncConfiP.update(vncPrograConf);

    assertDoesNotThrow(
        () -> {
          Optional<VNCProgramConfiguration> vncConfig = vncConfiP.findDefaultConfiguration();
          assertFalse(vncConfig.isEmpty());

          VNCProgramConfiguration vncFind = vncConfig.get();

          assertEquals(vncPrograConf, vncFind);
        });
  }

  @Test
  public void testFindDefaultConfiguration_uniqueConfig() throws PersistenceException {

    VNCProgramConfigurationPersistenceInMemory.resetStaticVNCconfigList();

    VNCProgramConfigurationPersistenceInMemory vncConfiP =
        new VNCProgramConfigurationPersistenceInMemory();

    VNCProgramConfiguration vncPrograConf = new VNCProgramConfiguration();
    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);

    assertEquals(1, VNCProgramConfigurationPersistenceInMemory.getListVNCconfig().size());
  }

  @Test
  public void testUpdate_uniqueConfig() throws PersistenceException {

    VNCProgramConfigurationPersistenceInMemory.resetStaticVNCconfigList();

    VNCProgramConfigurationPersistenceInMemory vncConfiP =
        new VNCProgramConfigurationPersistenceInMemory();

    VNCProgramConfiguration vncPrograConf = new VNCProgramConfiguration();
    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);

    assertEquals(1, VNCProgramConfigurationPersistenceInMemory.getListVNCconfig().size());
  }

  @Test
  public void testUpdate()
      throws PersistenceException, VNCProgramConfigurationException, TCPPortException {

    VNCProgramConfigurationPersistenceInMemory.resetStaticVNCconfigList();

    VNCProgramConfigurationPersistenceInMemory vncConfiP =
        new VNCProgramConfigurationPersistenceInMemory();

    VNCProgramConfiguration vncPrograConf = new VNCProgramConfiguration();

    boolean isToUseChecksum = true;

    vncPrograConf.setId(10);
    vncPrograConf.setName("foo");
    vncPrograConf.setDefaultPortToAccess(8888);
    vncPrograConf.setExecutionLine("abc");
    vncPrograConf.setPathToExecutable("bar", isToUseChecksum);
    vncPrograConf.setParametersToConnectionWithNoAuthentication("a \\host b \\port");
    vncPrograConf.setParametersToConnectionWithVNCAuthentication("a \\host b \\port \\password c");
    vncPrograConf.setParametersToConnectionWithUltraVNCAuthentication(
        "a \\host b \\port \\password c \\user y");
    vncPrograConf.setParameterForInteraction("-a");
    vncPrograConf.setParameterForNotInteraction("-b");
    vncPrograConf.setCheckSum(
        "0000000000000000000000000000000000000000000000000000000000000000", isToUseChecksum);
    vncPrograConf.setToUseChecksum(isToUseChecksum);

    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);
    vncConfiP.update(vncPrograConf);

    VNCProgramConfigurationPersistenceInMemory.getListVNCconfig().add(vncPrograConf);

    Optional<VNCProgramConfiguration> vncProgram = vncConfiP.findDefaultConfiguration();

    assertEquals(vncPrograConf, vncProgram.get());
  }
}
