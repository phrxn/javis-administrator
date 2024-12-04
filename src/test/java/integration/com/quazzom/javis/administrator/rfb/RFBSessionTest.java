package integration.com.quazzom.javis.administrator.rfb;

import java.io.IOException;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.rfb.RFBSession;

public class RFBSessionTest {

  public RFBSessionTest() {
    testTimeout();
  }

  public static void main(String[] args) {
    new RFBSessionTest();
  }

  // use a connection with 80 only to test timeout
  public void testTimeout() {

    int timeOutInSecond = 2000;

    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);

    RFBSession rfbs = new RFBSession("www.google.com", 8080);

    long timeStart = System.currentTimeMillis();

    try {
      rfbs.createConnection(timeOutInSecond);
    } catch (IOException e) {
    }

    long timeEnd = System.currentTimeMillis();

    long timeDifference = (timeEnd - timeStart);

    if (timeDifference > timeOutInSecond)
      System.out.println("RFBSessionTest.testTimeout Timeout OK");
    else System.err.println("RFBSessionTest.testTimeout Timeout INVALID");
  }
}
