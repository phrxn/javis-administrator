package com.quazzom.javis.administrator.gui;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.controllers.InternalFrameController;
import com.quazzom.javis.administrator.gui.controllers.LoginInternalFrameController;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.gui.dialog.JDialogComputer;
import com.quazzom.javis.administrator.gui.dialog.JDialogInteraction;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.gui.internal_frame.MainInternalFrame;
import com.quazzom.javis.administrator.gui.internal_frame.RootInternalFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;

public class SwingMediator {

  private JFrame administratorFrame;
  private JDesktopPane jDesktopPane;
  private SwingCommons swingCommons;

  private InternalFrameController vNCComputerListInternalFrameController;
  private InternalFrameController loginInternalFrameController;

  public SwingMediator(
      GeneralConfiguration generalConfiguration,
      JFrame administratorFrame,
      JDesktopPane jDesktopPane) {
    this.administratorFrame = administratorFrame;
    this.jDesktopPane = jDesktopPane;
    this.swingCommons = new SwingCommons();

    this.vNCComputerListInternalFrameController =
        new VNCComputerListInternalFrameController(generalConfiguration, this);
    this.loginInternalFrameController =
        new LoginInternalFrameController(generalConfiguration, this);
  }

  public JFrame getJFrameAdministratorFrame() {
    return this.administratorFrame;
  }

  public JDesktopPane getJDesktopPane() {
    return this.jDesktopPane;
  }

  public void addInternalFrame(JInternalFrame internalFrame) {
    this.jDesktopPane.add(internalFrame);
    displayAnInternalFrameThatAlreadyExist(internalFrame);
  }

  public void removeInternalFrame(RootInternalFrame rootInternalFrame) {
    jDesktopPane.remove(rootInternalFrame);
    jDesktopPane.repaint();
  }

  public boolean doesTheInternalFrameAlreadyExist(JInternalFrame internalFrame) {

    JInternalFrame[] allFrames = jDesktopPane.getAllFrames();

    for (JInternalFrame frame : allFrames) {
      if (frame == internalFrame) {
        displayAnInternalFrameThatAlreadyExist(internalFrame);
        return true;
      }
    }
    return false;
  }

  public Dimension getCenterOfScreenToInternalFrame(JInternalFrame jInternalFrameToCalCenter) {

    int x = (jDesktopPane.getWidth() - jInternalFrameToCalCenter.getWidth()) / 2;
    int y = (jDesktopPane.getHeight() - jInternalFrameToCalCenter.getHeight()) / 2;

    return new Dimension(x, y);
  }

  // -------------- show methods  --------------

  public void showLoginInternalFrame() {
    loginInternalFrameController.showInternalFrame();
  }

  public void showVNCComputerListInternalFrame() {
    vNCComputerListInternalFrameController.showInternalFrame();
  }

  public void showMainInternalFrame() {

    MainInternalFrame mainInternalFrame = new MainInternalFrame(this);

    jDesktopPane.add(mainInternalFrame);
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

  public JDialogYesCancelOption showYesCancelOptions(String message) {
    JDialogInteraction jdi = new JDialogInteraction(administratorFrame);
    return jdi.showYesCancelOptions(message);
  }

  public void showMessageToUser(JDialogType type, String title, String messageToShow) {

    Text language = LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_MESSAGES);

    swingCommons.showJDialogMessage(administratorFrame, type, title, messageToShow, language);
  }

  public void showExitProgram() {
    SwingExitProgram swingExitProgram = new SwingExitProgram(this);
    swingExitProgram.closeTheProgram();
  }

  // -------------- private methods  --------------

  private void displayAnInternalFrameThatAlreadyExist(JInternalFrame internalFrame) {

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

    // centralize the internalFrame in the jDesktopPane
    int desktopWidth = jDesktopPane.getWidth();
    int desktopHeight = jDesktopPane.getHeight();
    int frameWidth = internalFrame.getWidth();
    int frameHeight = internalFrame.getHeight();

    int x = (desktopWidth - frameWidth) / 2;
    int y = (desktopHeight - frameHeight) / 2;

    internalFrame.setLocation(x, y);
  }
}
