package com.quazzom.javis.administrator.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.VNCProgramConfigurationJDialog;
import com.quazzom.javis.administrator.io.Checksum;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistence;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistenceSwitch;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCConfigurationJDialogController {

  private SwingMediator swingMediator;

  private VNCProgramConfigurationPersistence vncProgramConfigurationPersistence;

  private Text theLanguage;

  public VNCConfigurationJDialogController(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    this.swingMediator = swingMediator;
    this.vncProgramConfigurationPersistence =
        new VNCProgramConfigurationPersistenceSwitch(generalConfiguration);
    theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_CONFIGURATION_JDIALOG_CONTROLLER);
  }

  public void showVNCConfigurationJDialog() {

    Optional<VNCProgramConfiguration> vncConf;

    try {
      vncConf = vncProgramConfigurationPersistence.findDefaultConfiguration();
    } catch (PersistenceException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("CREATE_VNC_CONFIGURATION_JDIALOG_ERROR"),
          e.getMessage());
      return;
    }

    VNCProgramConfiguration vncConfiguration = new VNCProgramConfiguration();

    if (!vncConf.isEmpty()) {
      vncConfiguration = vncConf.get();
    }

    swingMediator.showJDialogVNCConfiguration(this, vncConfiguration);
  }

  public boolean updateVNCProgramConfiguration(
      VNCProgramConfigurationJDialog vncProgramConfigurationJDialog) {

    VNCProgramConfiguration vncProgramConfiguration = new VNCProgramConfiguration();

    try {
      vncProgramConfigurationJDialog.fillVNCProgramConfiguration(vncProgramConfiguration);
    } catch (VNCProgramConfigurationException | TCPPortException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("UPDATE_PERSISTENCE_VNC_CONFIGURATION_ERROR"),
          e.getMessage());
      return false;
    }

    try {
      vncProgramConfigurationPersistence.update(vncProgramConfiguration);
    } catch (PersistenceException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("UPDATE_PERSISTENCE_VNC_CONFIGURATION_ERROR"),
          e.getMessage());
      return false;
    }
    return true;
  }

  public void setPathToExecutable(VNCProgramConfigurationJDialog vncProgramConfigurationJDialog) {

    String path = swingMediator.showSelectFileAbsolutePath();

    // if the path is empty the user canceled the operation
    if (path.isEmpty()) return;

    path = path.replace('\\', '/');

    Checksum checksum = new Checksum();
    String fileChecksum;

    try {
      fileChecksum = checksum.getFileChecksum(new File(path), Checksum.CHECK_SUM_ALGORITHM);
    } catch (NoSuchAlgorithmException | IOException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR, theLanguage.getText("CHECKSUM_FILE_ERROR"), e.getMessage());
      return;
    }

    if (path.contains(" ")) {
      path = String.format("\"%s\"", path);
    }

    vncProgramConfigurationJDialog.setPathToExecutable(path, fileChecksum);
  }
}
