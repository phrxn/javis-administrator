package gui.com.quazzom.javis.administrator.gui.dialog;

import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.gui.dialog.JDialogMessages;

public class JDialogMessages_GUITest {

  @Test
  public void testJDialogMessages() throws InterruptedException {

    JDialogMessages jdm = new JDialogMessages();

    while (jdm.isVisible()) {
      Thread.sleep(1000);
    }
  }
}
