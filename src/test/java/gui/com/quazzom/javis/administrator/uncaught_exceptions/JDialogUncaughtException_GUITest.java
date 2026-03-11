package gui.com.quazzom.javis.administrator.uncaught_exceptions;

import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.uncaught_exceptions.InformationAboutUncaughtException;
import com.quazzom.javis.administrator.uncaught_exceptions.JDialogUncaughtException;

public class JDialogUncaughtException_GUITest {

  @Test
  public void testJDialogUncaughtException() throws InterruptedException {
    JDialogUncaughtException dialog = new JDialogUncaughtException();

    InformationAboutUncaughtException iaue =
        new InformationAboutUncaughtException(
            "Program name test",
            "version test",
            "Thread name xxx",
            "Thread id 00",
            "aa\nbb\nc\nd\ne\nfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
                + "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
                + "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
                + "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\ng\nh"
                + "aa\nbb\nc\nd\ne\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz zzzzzzzzzzzzzzzzzz"
                + "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "
                + "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "
                + "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "
                + "n\n\nk\nl\nm\nn\no\np\nq");

    dialog.showUncaughtException(iaue);
    dialog.setVisible(true);

    while (dialog.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
