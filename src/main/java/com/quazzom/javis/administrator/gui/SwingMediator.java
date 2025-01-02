package com.quazzom.javis.administrator.gui;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.gui.controllers.VNCConfigurationJDialogController;
import com.quazzom.javis.administrator.gui.dialog.JDialogComputer;
import com.quazzom.javis.administrator.gui.dialog.JDialogInteraction;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogUltraVNCMslogonAuthenticationInput;
import com.quazzom.javis.administrator.gui.dialog.JDialogVNCAuthenticationInput;
import com.quazzom.javis.administrator.gui.dialog.JDialogVNCComputer;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.gui.dialog.VNCProgramConfigurationJDialog;
import com.quazzom.javis.administrator.gui.internal_frame.RootInternalFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformations;
import com.quazzom.javis.administrator.vnc.UltraVNCMslogonAuthentication;
import com.quazzom.javis.administrator.vnc.VNCAuthentication;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class SwingMediator {

  private JFrame administratorFrame;
  private JDesktopPane jDesktopPane;
  private SwingCommons swingCommons;

  public SwingMediator(
      GeneralConfiguration generalConfiguration,
      JFrame administratorFrame,
      JDesktopPane jDesktopPane) {
    this.administratorFrame = administratorFrame;
    this.jDesktopPane = jDesktopPane;
    this.swingCommons = new SwingCommons();
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

  public void showJDialogVNCConfiguration(
      VNCConfigurationJDialogController vncConfigurationJDialogController,
      VNCProgramConfiguration vncConfiguration) {

    VNCProgramConfigurationJDialog vncConfigurationJDialog =
        new VNCProgramConfigurationJDialog(
            vncConfigurationJDialogController, this, vncConfiguration);
  }

  public JDialogYesCancelOption showJDialogVNCComputer(
      ComputerConnectionInformations computerInfos) {

    JDialogVNCComputer jDialogVNCComputer = new JDialogVNCComputer(this, computerInfos);

    return jDialogVNCComputer.createJDialog();
  }

  public JDialogYesCancelOption showJDialogVNCAuthenticationInput(
      VNCAuthentication vncAuthentication) {

    JDialogVNCAuthenticationInput jDialogVNCAuthenticationInput =
        new JDialogVNCAuthenticationInput(this, vncAuthentication);

    return jDialogVNCAuthenticationInput.createJDialog();
  }

  public JDialogYesCancelOption showJDialogUltraVNCMslogonAuthenticationInput(
      UltraVNCMslogonAuthentication ultraAuthentication) {

    JDialogUltraVNCMslogonAuthenticationInput jDialogUltra =
        new JDialogUltraVNCMslogonAuthenticationInput(this, ultraAuthentication);

    return jDialogUltra.createJDialog();
  }

  public JDialogYesCancelOption showYesCancelOptions(String message) {
    JDialogInteraction jdi = new JDialogInteraction(administratorFrame);
    return jdi.showYesCancelOptions(message);
  }

  public void showMessageToUser(JDialogType type, String title, String messageToShow) {

    Text language = LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_MESSAGES);

    SwingUtilitiesAdministrator sua = new SwingUtilitiesAdministrator();

    // necessary because this method can be called by other threads, in addition
    // to EventDispatchThread
    sua.invokeLater(
        () -> {
          swingCommons.showJDialogMessage(administratorFrame, type, title, messageToShow, language);
        });
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

  /* returns the absolute path to a file if the user selected a file, or an empty
   * string if the user did not select anything or canceled the operation */
  public String showSelectFileAbsolutePath() {

    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int result = fileChooser.showOpenDialog(administratorFrame);

    if (result == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }

    return "";
  }
}
