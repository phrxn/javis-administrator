package com.quazzom.javis.administrator.gui;

import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.controllers.InternalFrameController;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.gui.dialog.JDialogComputer;
import com.quazzom.javis.administrator.gui.dialog.JDialogInteraction;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.gui.internal_frame.LoginInternalFrame;
import com.quazzom.javis.administrator.gui.internal_frame.MainInternalFrame;
import com.quazzom.javis.administrator.gui.internal_frame.RootInternalFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;

public class SwingMediator {

  private JFrame administratorFrame;
  private JDesktopPane jDesktopPane;
  private GeneralConfiguration generalConfiguration;
  private SwingCommons swingCommons;

  private LoginInternalFrame loginInternalFrame;
  private InternalFrameController vNCComputerListInternalFrameController;

  public SwingMediator(
      GeneralConfiguration generalConfiguration,
      JFrame administratorFrame,
      JDesktopPane jDesktopPane) {
    this.generalConfiguration = generalConfiguration;
    this.administratorFrame = administratorFrame;
    this.jDesktopPane = jDesktopPane;
    this.swingCommons = new SwingCommons();

    this.vNCComputerListInternalFrameController =
        new VNCComputerListInternalFrameController(generalConfiguration, this);
  }

  public void showLoginInternalFrame() {
    loginInternalFrame = new LoginInternalFrame(generalConfiguration, this);

    int x = (jDesktopPane.getWidth() - loginInternalFrame.getWidth()) / 2;
    int y = (jDesktopPane.getHeight() - loginInternalFrame.getHeight()) / 2;

    loginInternalFrame.setLocation(x, y);

    jDesktopPane.add(loginInternalFrame);

    showInternalFrameThatAlreadyExist(loginInternalFrame);
    loginInternalFrame.setFocusOnJTextFiedUsername();
  }

  public JFrame getJFrameAdministratorFrame() {
    return this.administratorFrame;
  }

  public JDesktopPane getJDesktopPane() {
    return this.jDesktopPane;
  }

  public void addInternalFrame(JInternalFrame internalFrame) {
    this.jDesktopPane.add(internalFrame);
    showInternalFrameThatAlreadyExist(internalFrame);
  }

  public void showVNCComputerListInternalFrame() {
    vNCComputerListInternalFrameController.showInternalFrame();
  }

  public void showExitProgram() {
    SwingExitProgram swingExitProgram = new SwingExitProgram(this);
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

  public void showJDialogComputerCreate(
      VNCComputerListInternalFrameController vncComputerListInternalFrameController) {
    new JDialogComputer(administratorFrame, this, vncComputerListInternalFrameController);
  }

  public void showJDialogComputerUpdate(
      VNCComputerListInternalFrameController vncComputerListInternalFrameController,
      Computer computerToUpdate) {
    new JDialogComputer(
        computerToUpdate, administratorFrame, this, vncComputerListInternalFrameController);
  }

  public void showMainInternalFrame() {

    MainInternalFrame mainInternalFrame = new MainInternalFrame(this);

    jDesktopPane.add(mainInternalFrame);
  }

  public void showMessageToUser(JDialogType type, String title, String messageToShow) {

    Text language = LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_MESSAGES);

    swingCommons.showJDialogMessage(administratorFrame, type, title, messageToShow, language);
  }

  public boolean doesTheInternalFrameAlreadyExist(JInternalFrame internalFrame) {

    JInternalFrame[] allFrames = jDesktopPane.getAllFrames();

    for (JInternalFrame frame : allFrames) {
      if (frame == internalFrame) {
        showInternalFrameThatAlreadyExist(internalFrame);
        return true;
      }
    }
    return false;
  }

  public void showInternalFrameThatAlreadyExist(JInternalFrame internalFrame) {

    internalFrame.setVisible(true);
    internalFrame.toFront();
    try {
      internalFrame.setSelected(true);
    } catch (PropertyVetoException e) {
      throw new RuntimeException(e);
    }
    internalFrame.requestFocusInWindow();
    try {
      internalFrame.setIcon(false);
    } catch (PropertyVetoException e) {
      throw new RuntimeException(e);
    }

    // centralize internalFrame in the jDesktopPane
    int desktopWidth = jDesktopPane.getWidth();
    int desktopHeight = jDesktopPane.getHeight();
    int frameWidth = internalFrame.getWidth();
    int frameHeight = internalFrame.getHeight();

    int x = (desktopWidth - frameWidth) / 2;
    int y = (desktopHeight - frameHeight) / 2;

    internalFrame.setLocation(x, y);
  }

  public JDialogYesCancelOption showYesCancelOptions(String message) {
    JDialogInteraction jdi = new JDialogInteraction(administratorFrame);
    return jdi.showYesCancelOptions(message);
  }
}
