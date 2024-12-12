package integration.com.quazzom.javis.administrator.persistence.sql_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.quazzom.javis.administrator.persistence.ConnectionFactory;

public class ConnectionFactorySQLServerTest implements ConnectionFactory {

  private static DataToAccess dataToAccess = null;

  public Connection getConnection() throws SQLException {

    if (dataToAccess == null) {
      dataToAccess = new DataToAccess();

	  String ipOrHost = JOptionPane.showInputDialog("[SQL Server - integration tests] Insert IP or host:");
	  String port = JOptionPane.showInputDialog("[SQL Server - integration tests] Insert port:");
	  String databaseName = JOptionPane.showInputDialog("[SQL Server - integration tests] Insert database name:");
      String user = JOptionPane.showInputDialog("[SQL Server - integration tests] Insert user:");
      String password = JOptionPane.showInputDialog("[SQL Server - integration tests] Insert password to user:");

	  dataToAccess.setIp(ipOrHost);
	  dataToAccess.setPort(port);
	  dataToAccess.setName(databaseName);
      dataToAccess.setUser(user);
      dataToAccess.setPassword(password);
    }

    Connection con;

    String sql =
        String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;encrypt=false;trustServerCertificate=false;",
            //
            dataToAccess.getIp(),
            dataToAccess.getPort(),
            dataToAccess.getName(),
            dataToAccess.getUser(),
            dataToAccess.getPassword());
    con = DriverManager.getConnection(sql);
    con.setAutoCommit(false);
    return con;
  }

  private class DataToAccess {

    private String ip = "";
    private String port = "";
    private String name = "";
    private String user = "";
    private String password = "";

    public String getIp() {
      return ip;
    }

    public void setIp(String ip) {
      this.ip = ip;
    }

    public String getPort() {
      return port;
    }

    public void setPort(String port) {
      this.port = port;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
