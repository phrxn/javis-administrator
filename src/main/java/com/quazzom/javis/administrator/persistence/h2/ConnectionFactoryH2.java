package com.quazzom.javis.administrator.persistence.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.persistence.ConnectionFactory;

public class ConnectionFactoryH2 implements ConnectionFactory {

  private String url;

  public ConnectionFactoryH2(GeneralConfiguration generalConfiguration) {
    if (generalConfiguration.getEnvironmentMode()
        == GeneralConfiguration.EnvironmentModeOptions.TEST) {
      url = "jdbc:h2:./db/javis_test";
    } else {
      url = "jdbc:h2:./db/javis";
    }
  }

  @Override
  public Connection getConnection() throws SQLException {

    String user = "sa";
    String password = "";

    Connection con = DriverManager.getConnection(url, user, password);
    con.setAutoCommit(false);
    return con;
  }
}
