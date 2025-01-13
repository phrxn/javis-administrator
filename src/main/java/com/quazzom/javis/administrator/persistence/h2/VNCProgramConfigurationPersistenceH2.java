package com.quazzom.javis.administrator.persistence.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.persistence.ConnectionFactory;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistence;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProgramConfigurationPersistenceH2
    implements VNCProgramConfigurationPersistence {

  private static final int DEFAULT_ID = 1;

  private ConnectionFactory connectionFactory;

  public VNCProgramConfigurationPersistenceH2(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Override
  public boolean update(VNCProgramConfiguration vncProgram) throws PersistenceException {

    String sql =
        "update vnc_program_configuration set"
            + ""
            + "    name=?, "
            + "    default_port_to_access=?, "
            + "    execution_line=?, "
            + "    path_to_executable=?, "
            + "    timeout_in_seconds_to_connection=?, "
            + "    parameters_to_connection_with_no_authentication=?, "
            + "    parameters_to_connection_with_vnc_authentication=?, "
            + "    parameters_to_connection_with_ultra_vnc_authentication=?, "
            + "    parameter_for_interaction=?,"
            + "    parameter_for_not_interaction=?, "
            + "    checksum=?, "
            + "    is_to_use_checksum=? "
            + "    where id=?";

    int dbReturnCode = 0;

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

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
      ps.setInt(13, DEFAULT_ID);

      dbReturnCode = ps.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    if (dbReturnCode > 0) return true;
    return false;
  }

  @Override
  public Optional<VNCProgramConfiguration> findDefaultConfiguration() throws PersistenceException {

    String sql =
        "select    id,"
            + "    name,"
            + "    default_port_to_access,"
            + "    execution_line,"
            + "    path_to_executable,"
            + "    timeout_in_seconds_to_connection,"
            + "    parameters_to_connection_with_no_authentication,"
            + "    parameters_to_connection_with_vnc_authentication,"
            + "    parameters_to_connection_with_ultra_vnc_authentication,"
            + "    parameter_for_interaction,"
            + "    parameter_for_not_interaction,"
            + "    checksum,"
            + "    is_to_use_checksum "
            + "    from vnc_program_configuration where id = ?";

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql); ) {

      ps.setInt(1, DEFAULT_ID);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        VNCProgramConfiguration vncProgramConfiguration = createObject(rs);
        return Optional.of(vncProgramConfiguration);
      }
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    return Optional.empty();
  }

  private VNCProgramConfiguration createObject(ResultSet rs) throws PersistenceException {

    VNCProgramConfiguration vncProgramConfiguration = new VNCProgramConfiguration();

    try {

      boolean isToUseChecksum = rs.getBoolean("is_to_use_checksum");

      vncProgramConfiguration.setId(DEFAULT_ID);
      vncProgramConfiguration.setName(rs.getString("name"));
      vncProgramConfiguration.setDefaultPortToAccess(rs.getInt("default_port_to_access"));
      vncProgramConfiguration.setExecutionLine(rs.getString("execution_line"));
      vncProgramConfiguration.setPathToExecutable(
          rs.getString("path_to_executable"), isToUseChecksum);
      vncProgramConfiguration.setTimeoutInSecondsToConnection(
          rs.getInt("timeout_in_seconds_to_connection"));
      vncProgramConfiguration.setParametersToConnectionWithNoAuthentication(
          rs.getString("parameters_to_connection_with_no_authentication"));
      vncProgramConfiguration.setParametersToConnectionWithVNCAuthentication(
          rs.getString("parameters_to_connection_with_vnc_authentication"));
      vncProgramConfiguration.setParametersToConnectionWithUltraVNCAuthentication(
          rs.getString("parameters_to_connection_with_ultra_vnc_authentication"));
      vncProgramConfiguration.setParameterForInteraction(rs.getString("parameter_for_interaction"));
      vncProgramConfiguration.setParameterForNotInteraction(
          rs.getString("parameter_for_not_interaction"));
      vncProgramConfiguration.setCheckSum(rs.getString("checksum"), isToUseChecksum);
      vncProgramConfiguration.setToUseChecksum(isToUseChecksum);
    } catch (VNCProgramConfigurationException | SQLException | TCPPortException e) {
      throw new PersistenceException(e.getMessage());
    }

    return vncProgramConfiguration;
  }
}
