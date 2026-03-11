package integration.com.quazzom.javis.administrator.persistence.h2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DatabaseFile {

  private String pathToDatabaseFile;

  public H2DatabaseFile(String pathToDatabaseFile) {
    this.pathToDatabaseFile = pathToDatabaseFile;
  }

  private Connection getConnection() throws SQLException {

    String url = String.format("jdbc:h2:%s", pathToDatabaseFile);
    String user = "sa";
    String password = "";

    Connection connection = DriverManager.getConnection(url, user, password);

    return connection;
  }

  public void createTheDataBase() throws SQLException {

    Connection connection = getConnection();
    Statement statement = connection.createStatement();

    String createComputersTableSQL =
        "CREATE TABLE IF NOT EXISTS computers ("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
            + "status_computer INT NOT NULL, "
            + "session_type INT NOT NULL, "
            + "username VARCHAR(255) NOT NULL, "
            + "hostname VARCHAR(64) NOT NULL, "
            + "ip VARCHAR(50) NOT NULL, "
            + "manual BIT NOT NULL, "
            + "login_date DATETIME NOT NULL, "
            + "client_version VARCHAR(20) NOT NULL, "
            + "details VARCHAR(255) NOT NULL"
            + ");";

    statement.executeUpdate(createComputersTableSQL);

    String createVncTableSQL =
        "CREATE TABLE IF NOT EXISTS vnc_program_configuration ("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
            + " name VARCHAR(64) NOT NULL, "
            + "default_port_to_access INT NOT NULL, "
            + "execution_line VARCHAR(256) NOT NULL, "
            + "path_to_executable VARCHAR(256) NOT NULL, "
            + "timeout_in_seconds_to_connection INT NOT NULL, "
            + "parameters_to_connection_with_no_authentication VARCHAR(256) NOT NULL, "
            + "parameters_to_connection_with_vnc_authentication VARCHAR(256) NOT NULL, "
            + "parameters_to_connection_with_ultra_vnc_authentication VARCHAR(256) NOT NULL, "
            + "parameter_for_interaction VARCHAR(64) NOT NULL, "
            + "parameter_for_not_interaction VARCHAR(64) NOT NULL, "
            + "checksum VARCHAR(64) NOT NULL, "
            + "is_to_use_checksum BIT NOT NULL"
            + ");";

    statement.executeUpdate(createVncTableSQL);

    String insertSQL =
        "INSERT INTO vnc_program_configuration "
            + "(name, default_port_to_access, execution_line, path_to_executable, "
            + "timeout_in_seconds_to_connection, parameters_to_connection_with_no_authentication, "
            + "parameters_to_connection_with_vnc_authentication, parameters_to_connection_with_ultra_vnc_authentication, "
            + "parameter_for_interaction, parameter_for_not_interaction, checksum, is_to_use_checksum) "
            + "VALUES "
            + "('empty', 5900, 'empty', 'empty', 2, '\\host\\port', '\\host\\port\\password', '\\host\\port\\password\\user', "
            + "'empty', 'empty', '0000000000000000000000000000000000000000000000000000000000000000', 1);";

    statement.executeUpdate(insertSQL);
  }

  public void createTableComputers() throws SQLException {

    Connection connection = getConnection();
    Statement statement = connection.createStatement();

    String createComputersTableSQL =
        "CREATE TABLE IF NOT EXISTS computers ("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
            + "status_computer INT NOT NULL, "
            + "session_type INT NOT NULL, "
            + "username VARCHAR(255) NOT NULL, "
            + "hostname VARCHAR(64) NOT NULL, "
            + "ip VARCHAR(50) NOT NULL, "
            + "manual BIT NOT NULL, "
            + "login_date DATETIME NOT NULL, "
            + "client_version VARCHAR(20) NOT NULL, "
            + "details VARCHAR(255) NOT NULL"
            + ");";

    statement.executeUpdate(createComputersTableSQL);
  }

  public void deleteTheDataBase(String pathToFile) throws SQLException {

    String suffix = ".mv.db";
    String thePathWithExtesion = String.format("%s%s", pathToFile, suffix);

    File databaseFile = new File(thePathWithExtesion);
    databaseFile.delete();
  }
}
