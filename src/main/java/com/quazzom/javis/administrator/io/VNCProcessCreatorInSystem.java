package com.quazzom.javis.administrator.io;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCProcessCreatorInSystem implements VNCProcessCreator {

  private List<String> parametersToExecution;
  private VNCProgramConfiguration vncProgramConfiguration;
  private SwingMediator swingMediator;
  private Text theLanguage;

  public VNCProcessCreatorInSystem(
      List<String> parametersToExecution,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator) {
    this(
        parametersToExecution,
        vncProgramConfiguration,
        swingMediator,
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_PROCESS_CREATOR_IN_SYSTEM));

    this.parametersToExecution = parametersToExecution;
    this.vncProgramConfiguration = vncProgramConfiguration;
    this.swingMediator = swingMediator;
  }

  public VNCProcessCreatorInSystem(
      List<String> parametersToExecution,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator,
      Text theLanguage) {
    this.parametersToExecution = parametersToExecution;
    this.vncProgramConfiguration = vncProgramConfiguration;
    this.swingMediator = swingMediator;
    this.theLanguage = theLanguage;
  }

  public void createVNCProcess(
      List<String> parametersToExecution, VNCProgramConfiguration vncProgramConfiguration) {}

  @Override
  public void executeVNCProcess() {

    List<String> listPathToExecutable;

    try {
      Parser parser = new Parser();
      listPathToExecutable = parser.createCommandLine(vncProgramConfiguration.getExecutionLine());
    } catch (GrammarException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("NOT_POSSIBLE_EXECUTE_VNC_PROGRAM"),
          e.getMessage());
      return;
    }

    String pathToExecutable = listPathToExecutable.get(0);

    File executableFile = new File(pathToExecutable);
    if (!executableFile.exists()) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("NOT_POSSIBLE_EXECUTE_VNC_PROGRAM"),
          theLanguage.getText("VNC_PROGRAM_DOESNOT_EXIST", pathToExecutable));
      return;
    }

    if (vncProgramConfiguration.isToUseChecksum()) {
      checkTheCheckSum(executableFile);
    }

    ProcessBuilder processBuilder = new ProcessBuilder(parametersToExecution);
    try {
      processBuilder.start();
    } catch (IOException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("NOT_POSSIBLE_EXECUTE_VNC_PROGRAM"),
          e.getMessage());
      return;
    }
  }

  private void checkTheCheckSum(File theExecutable) {

    Checksum checksum = new Checksum();

    String theExecutableCheckSum;
    try {
      theExecutableCheckSum = checksum.getFileChecksum(theExecutable, Checksum.CHECK_SUM_ALGORITHM);
    } catch (NoSuchAlgorithmException | IOException e) {
      throw new RuntimeException(e);
    }

    if (!checksum.isChecksumValid(theExecutableCheckSum, vncProgramConfiguration.getCheckSum())) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("NOT_POSSIBLE_EXECUTE_VNC_PROGRAM"),
          theLanguage.getText("INVALID_VNC_PROGRAM_CHECKSUM", theExecutable.getAbsolutePath()));
      return;
    }
  }
}
