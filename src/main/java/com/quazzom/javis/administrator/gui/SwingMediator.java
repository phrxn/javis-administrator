package com.quazzom.javis.administrator.gui;

import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.internal_frame.LoginInternalFrame;
import com.quazzom.javis.administrator.gui.internal_frame.RootInternalFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class SwingMediator {

  private JFrame administratorFrame;
  private JDesktopPane jDesktopPane;
  private GeneralConfiguration generalConfiguration;
  private SwingCommons swingCommons;

  private LoginInternalFrame loginInternalFrame;

  public SwingMediator(
      GeneralConfiguration generalConfiguration,
      JFrame administratorFrame,
      JDesktopPane jDesktopPane) {
    this.generalConfiguration = generalConfiguration;
    this.administratorFrame = administratorFrame;
    this.jDesktopPane = jDesktopPane;
    this.swingCommons = new SwingCommons();
  }

  public void showLoginInternalFrame() {
    loginInternalFrame = new LoginInternalFrame(generalConfiguration, this);

    int x = (jDesktopPane.getWidth() - loginInternalFrame.getWidth()) / 2;
    int y = (jDesktopPane.getHeight() - loginInternalFrame.getHeight()) / 2;

    loginInternalFrame.setLocation(x, y);
    loginInternalFrame.setVisible(true);

    jDesktopPane.add(loginInternalFrame);

    loginInternalFrame.toFront();
    try {
      loginInternalFrame.setSelected(true);
    } catch (PropertyVetoException e) {
      throw new RuntimeException(e);
    }
    loginInternalFrame.requestFocusInWindow();
    loginInternalFrame.setFocusOnJTextFiedUsername();
  }

  public void showExitProgram() {
    SwingExitProgram swingExitProgram = new SwingExitProgram(administratorFrame);
    swingExitProgram.closeTheProgram();
  }

  public void removeInternalFrame(RootInternalFrame rootInternalFrame) {
    jDesktopPane.remove(rootInternalFrame);
    jDesktopPane.repaint();
  }

  public void makeLogin(String user, String password) {
    SwingMakeLogin swingMakeLogin = new SwingMakeLogin(generalConfiguration, this);
    if (swingMakeLogin.makeLogin(user, password)) {
      removeInternalFrame(loginInternalFrame);
      showMainInternalFrame();
    }
  }

  public void showMainInternalFrame() {}

  public void showMessageToUser(JDialogType type, String title, String messageToShow) {

    Text language = LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_MESSAGES);

    swingCommons.showJDialogMessage(administratorFrame, type, title, messageToShow, language);
  }
}
