package integration.com.quazzom.javis.administrator.vnc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.vnc.VNCProgram;

public class ProcessVNCBuilderTest_IntegrationTest {

  private VNCProgram vncProgramTest =
      new VNCProgram() {

        @Override
        public String pathToExecutable() {
          return "src\\test\\java\\a.jar";
        }
      };

  @Test
  public void testLockProgram() throws InterruptedException {

    File file = new File(vncProgramTest.pathToExecutable()); // Caminho do arquivo a ser executado
    Process process = null;

    try {

      String checksum = getChecksum(file, "SHA-256");
      System.out.println("Checksum do arquivo: " + checksum);

      // Passo 3: Executar o arquivo
      if (file.exists() && file.canExecute()) {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", file.getAbsolutePath());

        process = processBuilder.start();

        System.out.println("Arquivo executado.");

        // Espera o processo terminar
        // process.waitFor();

        System.out.println("Processo concluído.");
      } else {
        System.out.println("O arquivo não é executável.");
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }

    /*
    ProcessVNCBuilder proc = new ProcessVNCBuilder(vncProgramTest);

    proc.lockProgramVNCExecutableFile();

    proc.executeVNCProgram();

    Thread.sleep(20000L);*/

    /*
    File file = new File(vncProgramTest.pathToExecutable()); // Caminho do arquivo a ser executado
    FileLock fileLock = null;
    Process process = null;

    try {
      // Passo 1: Travar o arquivo
      FileChannel channel =
          FileChannel.open(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
      fileLock = channel.lock(); // Bloqueio exclusivo do arquivo

      System.out.println("Arquivo travado.");

      // Passo 2: Fazer o checksum (MD5, por exemplo)
      String checksum = getChecksum(file, "MD5");
      System.out.println("Checksum do arquivo: " + checksum);

      try {
        // Passo 4: Liberar o bloqueio
        if (fileLock != null) {
          fileLock.release();
          System.out.println("Bloqueio liberado.");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      // Passo 3: Executar o arquivo
      if (file.exists() && file.canExecute()) {
        ProcessBuilder processBuilder = new ProcessBuilder(file.getAbsolutePath());
        process = processBuilder.start(); // Inicia o processo (arquivo executável)
        System.out.println("Arquivo executado.");

        // Espera o processo terminar
        process.waitFor();
        System.out.println("Processo concluído.");
      } else {
        System.out.println("O arquivo não é executável.");
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }

    /*
    ProcessVNCBuilder proc = new ProcessVNCBuilder(vncProgramTest);

    proc.lockProgramVNCExecutableFile();

    proc.executeVNCProgram();

    Thread.sleep(20000L);
    */

  }

  // Método para calcular o checksum de um arquivo
  public static String getChecksum(File file, String algorithm)
      throws NoSuchAlgorithmException, IOException {
    MessageDigest digest = MessageDigest.getInstance(algorithm);
    try (InputStream is = new FileInputStream(file);
        DigestInputStream dis = new DigestInputStream(is, digest)) {

      byte[] buffer = new byte[1024];
      while (dis.read(buffer) != -1) {}
    }

    byte[] hashBytes = digest.digest();
    StringBuilder sb = new StringBuilder();
    for (byte b : hashBytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
