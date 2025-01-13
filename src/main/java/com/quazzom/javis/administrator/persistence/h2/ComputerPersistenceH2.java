package com.quazzom.javis.administrator.persistence.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.persistence.ComputerPersistence;
import com.quazzom.javis.administrator.persistence.ConnectionFactory;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IP;
import com.quazzom.javis.administrator.vnc.IPException;

public class ComputerPersistenceH2 implements ComputerPersistence {

  private ConnectionFactory connectionFactory;
  private Text text;

  public ComputerPersistenceH2(ConnectionFactory connectionFactory) {
    this(
        connectionFactory, LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER_PERSISTENCE_H2));
  }

  public ComputerPersistenceH2(ConnectionFactory connectionFactory, Text text) {
    this.connectionFactory = connectionFactory;
    this.text = text;
  }

  @Override
  public Computer createComputer(Computer c) throws PersistenceException {

    String sql =
        "INSERT INTO computers "
            + "    (status_computer, session_type, username, hostname, ip, manual, login_date, client_version, details) "
            + "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? "
            + "WHERE NOT EXISTS (SELECT 1 FROM computers WHERE hostname = ? AND ip = ?);";

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql); ) {
      ps.setObject(1, c.getPowerStatus().value());
      ps.setObject(2, c.getSessionType().value());
      ps.setObject(3, c.getUsername());
      ps.setObject(4, c.getHostname());
      ps.setObject(5, c.getIP().getIP());
      ps.setObject(6, c.isManual());
      ps.setObject(7, c.getLoginDate());
      ps.setObject(8, c.getJavisClientVersion());
      ps.setObject(9, c.getDetails());
      ps.setObject(10, c.getHostname());
      ps.setObject(11, c.getIP().getIP());

      int insertResult = ps.executeUpdate();

      con.commit();

      // if 0 computer already exists!
      if (insertResult == 0) {
        throw new PersistenceException(
            text.getText(
                "CREATE_COMPUTER_COMPUTER_ALREADY_EXIST_EXCEPTION",
                c.getHostname(),
                c.getIP().getIP()));
      }

      String selectSql = "SELECT * FROM computers WHERE id = (SELECT MAX(id) FROM computers)";
      try (PreparedStatement selectPs = con.prepareStatement(selectSql);
          ResultSet rs = selectPs.executeQuery()) {
        if (rs.next()) {
          return insertObject(rs, c);
        }
      }

    } catch (SQLException e) {
      throw new PersistenceException(
          String.format("%s: %s", e.getClass().getName(), e.getMessage()));
    }
    throw new PersistenceException(text.getText("UNEXPECTED_ERROR_EXCEPTION"));
  }

  @Override
  public boolean deleteComputer(Computer c) throws PersistenceException {

    int dbReturnCode = 0;

    String sql = "DELETE FROM computers WHERE id=?";

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql); ) {

      ps.setInt(1, c.getId());

      dbReturnCode = ps.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    if (dbReturnCode > 0) return true;
    return false;
  }

  @Override
  public boolean updateComputer(Computer c) throws PersistenceException {

    String sql =
        "UPDATE computers SET status_computer=?, session_type=?, username=?, hostname=?, "
            + "ip=?, manual=?, login_date=?, client_version=?, details=? where id=?";

    int dbReturnCode = 0;

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, c.getPowerStatus().value());
      ps.setInt(2, c.getSessionType().value());
      ps.setString(3, c.getUsername());
      ps.setString(4, c.getHostname());
      ps.setString(5, c.getIPStr());
      ps.setBoolean(6, c.isManual());
      ps.setTimestamp(7, new java.sql.Timestamp(c.getLoginDate().getTime()));
      ps.setString(8, c.getJavisClientVersion());
      ps.setString(9, c.getDetails());
      ps.setInt(10, c.getId());

      dbReturnCode = ps.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    if (dbReturnCode > 0) return true;
    return false;
  }

  public Computer findComputer(int id) throws PersistenceException {

    String sql =
        "select id, status_computer, session_type, username, hostname, ip, manual, "
            + "login_date, client_version, details from computers where id=?";

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql); ) {

      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        Computer c = createObject(rs);
        return c;
      }
    } catch (PersistenceException | SQLException e) {
      throw new PersistenceException(e.getMessage());
    }
    throw new PersistenceException(text.getText("FIND_BY_ID_NOT_FOUND_EXCEPTION", id));
  }

  @Override
  public List<Computer> findAll() throws PersistenceException {

    String sql =
        "select id, status_computer, session_type, username, hostname, ip, manual, "
            + "login_date, client_version, details from computers";
    List<Computer> list = new ArrayList<Computer>();

    try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) list.add(createObject(rs));

    } catch (Exception e) {
      throw new PersistenceException(e.getMessage());
    }
    return list;
  }

  private Computer createObject(ResultSet rs) throws PersistenceException {

    int id;
    int powerStatus;
    int sessionType;
    String username;
    String hostname;
    String ip;
    boolean isManual;
    Date loginDate;
    String clientJavisVersion;
    String details;

    try {
      id = rs.getInt("id");
      powerStatus = rs.getInt("status_computer");
      sessionType = rs.getInt("session_type");
      username = rs.getString("username");
      hostname = rs.getString("hostname");
      ip = rs.getString("ip");
      isManual = rs.getBoolean("manual");
      loginDate = new Date(rs.getTimestamp("login_date").getTime());
      clientJavisVersion = rs.getString("client_version");
      details = rs.getString("details");
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    try {
      Computer c = new Computer();

      c = new Computer();
      c.setId(id);
      c.setPowerStatus(powerStatus);
      c.setSessionType(sessionType);
      c.setUsername(username);
      c.setHostname(hostname);
      c.setIP(new IP(ip));
      c.setManual(isManual);
      c.setLoginDate(loginDate);
      c.setJavisClientVersion(clientJavisVersion);
      c.setDetails(details);
      return c;
    } catch (Exception e) {
      throw new PersistenceException(e.getMessage());
    }
  }

  private Computer insertObject(ResultSet rs, Computer computer) throws PersistenceException {

    int id = 0;
    int powerStatus;
    int sessionType;
    String username;
    String hostname;
    String ip;
    boolean isManual;
    Date loginDate;
    String clientJavisVersion;
    String details;

    try {
      id = rs.getInt("id");
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    // if a new hostname and ip was created, the database will return all the information.
    try {
      powerStatus = rs.getInt("status_computer");
      sessionType = rs.getInt("session_type");
      username = rs.getString("username");
      hostname = rs.getString("hostname");
      ip = rs.getString("ip");
      isManual = rs.getBoolean("manual");
      loginDate = new Date(rs.getTimestamp("login_date").getTime());
      clientJavisVersion = rs.getString("client_version");
      details = rs.getString("details");
    } catch (SQLException e) {
      throw new PersistenceException(e.getMessage());
    }

    try {
      Computer c = new Computer();

      c.setId(id);
      c.setPowerStatus(powerStatus);
      c.setSessionType(sessionType);
      c.setUsername(username);
      c.setHostname(hostname);
      c.setIP(new IP(ip));
      c.setManual(isManual);
      c.setLoginDate(loginDate);
      c.setJavisClientVersion(clientJavisVersion);
      c.setDetails(details);
      return c;
    } catch (ComputerException e) {
      throw new PersistenceException(String.format("%s: %s", "SQLException", e.getMessage()));
    } catch (IPException e) {
      throw new PersistenceException(String.format("%s: %s", "IPException", e.getMessage()));
    }
  }
}
