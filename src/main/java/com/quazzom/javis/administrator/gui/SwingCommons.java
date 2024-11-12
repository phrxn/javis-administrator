package com.quazzom.javis.administrator.gui;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.quazzom.javis.administrator.Administrator;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.dialog.JDialogMessages;
import com.quazzom.javis.administrator.gui.dialog.JDialogQuestions;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;
import com.quazzom.javis.administrator.lang.Text;

public class SwingCommons {

  private Administrator administrator;
  private ImageIcon imageProgramIcon16x16;

  public SwingCommons() {
    this(AdministratorSingleton.getInstance());
  }

  public SwingCommons(Administrator administrator) {
    this.administrator = administrator;
    imageProgramIcon16x16 = SwingImages.getImageIcon(ImagePathToFile.ICON_JAVIS_16_X_16);
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
          new JDialogMessages(
              parentFrame,
              imageProgramIcon16x16.getImage(),
              type,
              title,
              messageToShow,
              jDialogTextLanguage);
        });
  }

  /**
   * @param parentFrame
   * @param jQuestionTextLanguage
   * @param message
   * @param options
   * @param defaultOption
   * @return the index of the chosen string from the 'options' array. The first value is 0
   */
  public int showJDialogQuestion(
      JFrame parentFrame,
      Text jQuestionTextLanguage,
      String message,
      String options[],
      int defaultOption) {

    RunnableFuture<Integer> getInt =
        new FutureTask<Integer>(
            new Callable<Integer>() {

              @Override
              public Integer call() throws Exception {
                JDialogQuestions jq = new JDialogQuestions(parentFrame, jQuestionTextLanguage);
                return jq.showChoose(
                    message, imageProgramIcon16x16.getImage(), options, defaultOption);
              }
            });

    invokeAndWait(getInt);

    try {
      return getInt.get();
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
