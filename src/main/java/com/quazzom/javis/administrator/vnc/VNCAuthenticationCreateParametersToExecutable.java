package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.ParserVNCAuthentication;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class VNCAuthenticationCreateParametersToExecutable implements CreateParametersToExecutable {

  private SwingMediator swingMediator;
  private Text theLanguage;

  public VNCAuthenticationCreateParametersToExecutable(SwingMediator swingMediator) {
    this(
        swingMediator,
        LanguageFactory.getLanguage(
            LanguagePathToFile.VNC_AUTHENTICATION_CREATE_PARAMETERS_TO_EXECUTABLE));
  }

  public VNCAuthenticationCreateParametersToExecutable(
      SwingMediator swingMediator, Text theLanguage) {
    this.swingMediator = swingMediator;
    this.theLanguage = theLanguage;
  }

  @Override
  public Optional<List<String>> createParameters(
      ComputerConnectionInformations computer, VNCProgramConfiguration vncProgramConfig) {

    List<String> parametersToConnection = new ArrayList<String>();

    ParserVNCAuthentication parser;

    VNCAuthentication vncAuthentication =
        AdministratorSingleton.getInstance().getVncAuthentication().clone();

    if (swingMediator.showJDialogVNCAuthenticationInput(vncAuthentication)
        == JDialogYesCancelOption.CANCEL) {
      return Optional.empty();
    }

    if (vncAuthentication.isToSalveCredential()) {
      AdministratorSingleton.getInstance().setVncAuthentication(vncAuthentication);
    } else {
      AdministratorSingleton.getInstance().setVncAuthentication(new VNCAuthentication("", false));
    }

    parser =
        new ParserVNCAuthentication(
            computer.getHostName(),
            String.valueOf(computer.getPort()),
            vncAuthentication.getPassword());

    try {
      parametersToConnection =
          parser.createCommandLine(
              vncProgramConfig.getParametersToConnectionWithVNCAuthentication());
    } catch (GrammarException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("GRAMMAR_ERROR_TITLE"),
          theLanguage.getText("GRAMMAR_ERROR", e.getMessage()));
      return Optional.empty();
    }

    return Optional.of(parametersToConnection);
  }
}
