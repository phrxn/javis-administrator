package com.quazzom.javis.administrator.gui;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.quazzom.javis.administrator.Administrator;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.dialog.JDialogMessages;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.Text;

public class SwingCommons {

  private Administrator administrator;

  public SwingCommons() {
    this(AdministratorSingleton.getInstance());
  }

  public SwingCommons(Administrator administrator) {
    this.administrator = administrator;
  }

  public String createTitle() {
    return String.format(
        "%s - %s", administrator.getProgramName(), administrator.getProgramVersion());
  }

  public void invokeAndWait(final Runnable runnable) {
    if (SwingUtilities.isEventDispatchThread()) {
      runnable.run();
    } else {
      try {
        SwingUtilities.invokeAndWait(runnable);
      } catch (final InterruptedException e) {
        throw new RuntimeException(e);
      } catch (final InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * A wrapper for {@link SwingUtilities#invokeLater(Runnable)}.
   *
   * @param runnable The runnable to invoke later.
   */
  public void invokeLater(final Runnable runnable) {
    SwingUtilities.invokeLater(runnable);
  }

  public void showJDialogMessage(
      JFrame parentFrame,
      JDialogType type,
      String title,
      String messageToShow,
      Text jDialogTextLanguage) {

    invokeAndWait(
        () -> {
          new JDialogMessages(parentFrame, type, title, messageToShow, jDialogTextLanguage);
        });
  }
}
