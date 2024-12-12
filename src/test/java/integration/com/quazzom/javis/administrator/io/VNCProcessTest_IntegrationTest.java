package integration.com.quazzom.javis.administrator.io;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProcessTest_IntegrationTest {

  public static void main(String[] args)
      throws NoSuchAlgorithmException, IOException, VNCProgramConfigurationException {

    /*
    VNCProcessCreatorInSystem vncProcess = new VNCProcessCreatorInSystem();

    VNCProgramConfiguration vncConfig = new VNCProgramConfiguration();
    vncConfig.setVncName("MAGA");
    vncConfig.setExecutionLine("\"C:\\Program Files\\uvnc bvba\\UltraVNC\\vncviewer.exe\"");
    vncConfig.setPathToExecutable("\"C:\\Program Files\\uvnc bvba\\UltraVNC\\vncviewer.exe\"");
    vncConfig.setCheckSum("2D05E45318F49BDF2F688CABF7B1D21D47187F225989083040FE9B50F54A9C83");

    System.out.print("password vnc: ");
    Scanner scanner = new Scanner(System.in);
    String password = scanner.nextLine();
    scanner.close();

    String parametersFormatted =
        String.format("-connect labsvr60::5900 -user david@labdomsp.local -password %s", password);

    vncProcess.createVNCProcess(vncConfig, parametersFormatted);
    */
  }
}
