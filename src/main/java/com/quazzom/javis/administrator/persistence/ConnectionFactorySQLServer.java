package com.quazzom.javis.administrator.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.configuration.PropertyNotFoundException;
import com.quazzom.javis.administrator.configuration.SQLServerConnectionInformations;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ConnectionFactorySQLServer implements ConnectionFactory {

  public static final String pathToConnectionInformations = "config/SQLServerConnection.properties";
  public static final String pathToConnectionInformationsTest =
      "config/SQLServerConnection.properties";

  private SQLServerConnectionInformations sqlConnectionInfos;

  private Text theLanguage;

  private String pathToConfigurationFile;

  public ConnectionFactorySQLServer(GeneralConfiguration generalConfiguration) {
    theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.CONNECTION_FACTORY_SQL_SERVER);
    if (generalConfiguration.getEnvironmentMode()
        == GeneralConfiguration.EnvironmentModeOptions.TEST) {
      pathToConfigurationFile = pathToConnectionInformationsTest;
    } else {
      pathToConfigurationFile = pathToConnectionInformations;
    }
  }

  @Override
  public Connection getConnection() throws SQLException {

    try {
      loadConfigurationInformationFromFile();
    } catch (IOException e) {
      throw new SQLException(e.getMessage());
    }

    String connectionString = null;

    try {
      connectionString = createConnectionString(sqlConnectionInfos);
    } catch (PropertyNotFoundException e) {
      throw new SQLException(
          String.format(
              "%s. %s",
              theLanguage.getText("PROPERTY_DOESNT_EXIST_IN_FILE", pathToConfigurationFile),
              e.getMessage()));
    }

    Connection con = DriverManager.getConnection(connectionString);
    con.setAutoCommit(false);
    return con;
  }

  private void loadConfigurationInformationFromFile() throws IOException {

    Properties propertiesWithConnectionsInformation = new Properties();

    propertiesWithConnectionsInformation.load(new FileInputStream(pathToConfigurationFile));

    sqlConnectionInfos = new SQLServerConnectionInformations(propertiesWithConnectionsInformation);
  }

  public String createConnectionString(SQLServerConnectionInformations sqlConInfos)
      throws PropertyNotFoundException {

    String connectionString =
        String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;encrypt=%s;trustServerCertificate=%s;",
            sqlConInfos.getServer(),
            sqlConInfos.getPort(),
            sqlConInfos.getDatabaseName(),
            sqlConInfos.getUser(),
            sqlConInfos.getPassWord(),
            sqlConInfos.getEncrypt(),
            sqlConInfos.getTrustServerCertificate());

    return connectionString;
  }
}
