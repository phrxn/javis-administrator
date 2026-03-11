package integration.com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.Start;

public class Start_IntegrationTest {

  enum TheTest {
    RUNTIME_EXCEPTION
  }

  // test if default handler is working
  public static void main(String[] args) {
    new Start_IntegrationTest(TheTest.RUNTIME_EXCEPTION);
  }

  // ----------------------------------------------------------------------------------------
  public Start_IntegrationTest(TheTest test) {

    switch (test) {
      case RUNTIME_EXCEPTION:
        testRuntimeException();
        break;

      default:
        break;
    }
  }

  // test runtime exception (to test default start handler)
  private void testRuntimeException() {
    Start s = new Start();

    s.startProgram();

    throw new RuntimeException("foo");
  }
}
