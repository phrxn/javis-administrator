package integration.com.quazzom.javis.administrator.persistence.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.quazzom.javis.administrator.persistence.ConnectionFactory;

public class ConnectionFactoryH2Test implements ConnectionFactory {

  private String pathToFile;

  public ConnectionFactoryH2Test(String pathToFile) {
    this.pathToFile = pathToFile;
  }

  public Connection getConnection() throws SQLException {

    Connection con;

    con = DriverManager.getConnection(String.format("jdbc:h2:%s", pathToFile), "sa", "");
    con.setAutoCommit(false);
    return con;
  }
}
