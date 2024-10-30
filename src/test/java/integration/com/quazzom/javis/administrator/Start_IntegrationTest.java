package integration.com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.Start;

public class Start_IntegrationTest {

  // test if default handler is working
  public static void main(String[] args) {

    Start s = new Start();

    s.startProgram();

    throw new RuntimeException("foo");
  }
}
