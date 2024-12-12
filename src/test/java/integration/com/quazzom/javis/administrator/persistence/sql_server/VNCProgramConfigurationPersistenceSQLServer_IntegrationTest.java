package integration.com.quazzom.javis.administrator.persistence.sql_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.sql_server.VNCProgramConfigurationPersistenceSQLServer;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProgramConfigurationPersistenceSQLServer_IntegrationTest {

  @Test
  public void testFindDefaultConfiguration()
      throws VNCProgramConfigurationException,
          TCPPortException,
          SQLException,
          PersistenceException {

    createADefaultVNCProgramConfiguration();

    VNCProgramConfigurationPersistenceSQLServer vncProgramSQLServer =
        new VNCProgramConfigurationPersistenceSQLServer(new ConnectionFactorySQLServerTest());

    Optional<VNCProgramConfiguration> theReturn = vncProgramSQLServer.findDefaultConfiguration();

    if (theReturn.isEmpty()) {
      fail();
    }

    VNCProgramConfiguration fromDB = theReturn.get();

    VNCProgramConfiguration toCompare = getADefaultVNCProgramConfiguration();

    assertEquals(fromDB, toCompare);
  }

  @Test
  public void testUpdate()
      throws VNCProgramConfigurationException,
          TCPPortException,
          SQLException,
          PersistenceException {

    createADefaultVNCProgramConfiguration();

    VNCProgramConfiguration vncProgramConfiguration = getADefaultVNCProgramConfiguration();
    boolean isToUseChecksum = true;
    vncProgramConfiguration.setName("a1");
    vncProgramConfiguration.setDefaultPortToAccess(2);
    vncProgramConfiguration.setExecutionLine("b1");
    vncProgramConfiguration.setPathToExecutable("c1", isToUseChecksum);
    vncProgramConfiguration.setTimeoutInSecondsToConnection(3);
    vncProgramConfiguration.setParametersToConnectionWithNoAuthentication("\\host\\port --foo1");
    vncProgramConfiguration.setParametersToConnectionWithVNCAuthentication(
        "\\host\\port\\password --foo1");
    vncProgramConfiguration.setParametersToConnectionWithUltraVNCAuthentication(
        "\\host\\port\\user\\password --foo1");
    vncProgramConfiguration.setParameterForInteraction("interaction --foo1");
    vncProgramConfiguration.setParameterForNotInteraction("notInteraction --foo1");
    StringBuilder sbChecksum = new StringBuilder();
    for (int count = 0; count < 64; count++) {
      sbChecksum.append("b");
    }
    vncProgramConfiguration.setCheckSum(sbChecksum.toString(), isToUseChecksum);
    vncProgramConfiguration.setToUseChecksum(isToUseChecksum);

    VNCProgramConfigurationPersistenceSQLServer vncProgramSQLServer =
        new VNCProgramConfigurationPersistenceSQLServer(new ConnectionFactorySQLServerTest());

    // make de update
    boolean updated = vncProgramSQLServer.update(vncProgramConfiguration);
    assertTrue(updated);

    Optional<VNCProgramConfiguration> theReturn = vncProgramSQLServer.findDefaultConfiguration();

    if (theReturn.isEmpty()) {
      fail();
    }

    VNCProgramConfiguration fromDB = theReturn.get();

    assertEquals(fromDB, vncProgramConfiguration);
  }

  // ------------------------------------------------------------------------------------------------------------------------

  private VNCProgramConfiguration getADefaultVNCProgramConfiguration()
      throws VNCProgramConfigurationException, TCPPortException {
    VNCProgramConfiguration vncProgramConfiguration = new VNCProgramConfiguration();
    boolean isToUseChecksum = true;
    vncProgramConfiguration.setId(1);
    vncProgramConfiguration.setName("a");
    vncProgramConfiguration.setDefaultPortToAccess(1);
    vncProgramConfiguration.setExecutionLine("b");
    vncProgramConfiguration.setPathToExecutable("c", isToUseChecksum);
    vncProgramConfiguration.setTimeoutInSecondsToConnection(2);
    vncProgramConfiguration.setParametersToConnectionWithNoAuthentication("\\host\\port --foo");
    vncProgramConfiguration.setParametersToConnectionWithVNCAuthentication(
        "\\host\\port\\password --foo");
    vncProgramConfiguration.setParametersToConnectionWithUltraVNCAuthentication(
        "\\host\\port\\user\\password --foo");
    vncProgramConfiguration.setParameterForInteraction("interaction --foo");
    vncProgramConfiguration.setParameterForNotInteraction("notInteraction --foo");
    StringBuilder sbChecksum = new StringBuilder();
    for (int count = 0; count < 64; count++) {
      sbChecksum.append("a");
    }
    vncProgramConfiguration.setCheckSum(sbChecksum.toString(), isToUseChecksum);
    vncProgramConfiguration.setToUseChecksum(isToUseChecksum);

    return vncProgramConfiguration;
  }

  public void createADefaultVNCProgramConfiguration()
      throws VNCProgramConfigurationException, TCPPortException, SQLException {

    VNCProgramConfiguration vncProgram = getADefaultVNCProgramConfiguration();

    String sql =
        "INSERT INTO vnc_program_configuration"
            + "           (name"
            + "           ,default_port_to_access"
            + "           ,execution_line"
            + "           ,path_to_executable"
            + "           ,timeout_in_seconds_to_connection"
            + "           ,parameters_to_connection_with_no_authentication"
            + "           ,parameters_to_connection_with_vnc_authentication"
            + "           ,parameters_to_connection_with_ultra_vnc_authentication"
            + "           ,parameter_for_interaction"
            + "           ,parameter_for_not_interaction"
            + "           ,checksum"
            + "           ,is_to_use_checksum) "
            + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    ConnectionFactorySQLServerTest cft = new ConnectionFactorySQLServerTest();

    Connection conn = cft.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);

    ps.setString(1, vncProgram.getName());
    ps.setInt(2, vncProgram.getDefaultPortToAccess());
    ps.setString(3, vncProgram.getExecutionLine());
    ps.setString(4, vncProgram.getPathToExecutable());
    ps.setInt(5, vncProgram.getTimeoutInSecondsToConnection());
    ps.setString(6, vncProgram.getParametersToConnectionWithNoAuthentication());
    ps.setString(7, vncProgram.getParametersToConnectionWithVNCAuthentication());
    ps.setString(8, vncProgram.getParametersToConnectionWithUltraVNCAuthentication());
    ps.setString(9, vncProgram.getParameterForInteraction());
    ps.setString(10, vncProgram.getParameterForNotInteraction());
    ps.setString(11, vncProgram.getCheckSum());
    ps.setBoolean(12, vncProgram.isToUseChecksum());

    ps.executeUpdate();

    conn.commit();
  }

  //
  //
  //
  //

  private static void clearAndResetComputerTable() throws SQLException {

    ConnectionFactorySQLServerTest cft = new ConnectionFactorySQLServerTest();

    Connection conn = cft.getConnection();
    Statement stmt = conn.createStatement();

    String sql = "TRUNCATE TABLE vnc_program_configuration;";

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
}
