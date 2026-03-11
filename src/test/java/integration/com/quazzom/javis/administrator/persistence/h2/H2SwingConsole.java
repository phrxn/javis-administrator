package integration.com.quazzom.javis.administrator.persistence.h2;

import org.h2.tools.Console;

public class H2SwingConsole {
  public static void main(String[] args) {
    try {
      Console.main(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
