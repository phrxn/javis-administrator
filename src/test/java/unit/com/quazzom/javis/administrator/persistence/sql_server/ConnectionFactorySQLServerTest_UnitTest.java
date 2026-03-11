package unit.com.quazzom.javis.administrator.persistence.sql_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.configuration.PropertyNotFoundException;
import com.quazzom.javis.administrator.configuration.SQLServerConnectionInformations;
import com.quazzom.javis.administrator.persistence.sql_server.ConnectionFactorySQLServer;

public class ConnectionFactorySQLServerTest_UnitTest {

  @Test
  public void createConnectionString() throws PropertyNotFoundException {

    SQLServerConnectionInformations sqlCon = Mockito.mock(SQLServerConnectionInformations.class);
    GeneralConfiguration generalConfigurationMock = Mockito.mock(GeneralConfiguration.class);
    when(generalConfigurationMock.getEnvironmentMode())
        .thenReturn(GeneralConfiguration.EnvironmentModeOptions.TEST);

    ConnectionFactorySQLServer cfsqlserver =
        new ConnectionFactorySQLServer(generalConfigurationMock);

    when(sqlCon.getServer()).thenReturn("server");
    when(sqlCon.getPort()).thenReturn("port");
    when(sqlCon.getDatabaseName()).thenReturn("databaseName");
    when(sqlCon.getUser()).thenReturn("user");
    when(sqlCon.getPassWord()).thenReturn("password");
    when(sqlCon.getEncrypt()).thenReturn("a");
    when(sqlCon.getTrustServerCertificate()).thenReturn("b");

    String connectionStringFake = cfsqlserver.createConnectionString(sqlCon);

    assertEquals(
        "jdbc:sqlserver://server:port;databaseName=databaseName;user=user;password=password;encrypt=a;trustServerCertificate=b;",
        connectionStringFake);
  }
}
