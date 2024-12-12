package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.io.parser.ParserUltraVNCMslogonAuthentication;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class UltraVNCMslogonAuthenticationCreateParametersToExecutable
    implements CreateParametersToExecutable {

  private SwingMediator swingMediator;
  private Text theLanguage;

  public UltraVNCMslogonAuthenticationCreateParametersToExecutable(SwingMediator swingMediator) {
    this(
        swingMediator,
        LanguageFactory.getLanguage(
            LanguagePathToFile.ULTRA_VNC_MSLOGON_AUTHENTICATION_CREATE_PARAMETERS_TO_EXECUTABLE));
  }

  public UltraVNCMslogonAuthenticationCreateParametersToExecutable(
      SwingMediator swingMediator, Text theLanguage) {
    this.swingMediator = swingMediator;
    this.theLanguage = theLanguage;
  }

  @Override
  public Optional<List<String>> createParameters(
      ComputerConnectionInformations computer, VNCProgramConfiguration vncProgramConfig) {

    List<String> parametersToConnection = new ArrayList<String>();

    Parser parser;

    UltraVNCMslogonAuthentication ultraAuthentication =
        AdministratorSingleton.getInstance().getUltraVNCMslogonAuthentication().clone();

    if (swingMediator.showJDialogUltraVNCMslogonAuthenticationInput(ultraAuthentication)
        == JDialogYesCancelOption.CANCEL) {
      return Optional.empty();
    }

    if (ultraAuthentication.isToSalveCredential()) {
      AdministratorSingleton.getInstance().setUltraVNCMslogonAuthentication(ultraAuthentication);
    } else {
      AdministratorSingleton.getInstance()
          .setUltraVNCMslogonAuthentication(new UltraVNCMslogonAuthentication("", "", false));
    }

    parser =
        new ParserUltraVNCMslogonAuthentication(
            computer.getHostName(),
            String.valueOf(computer.getPort()),
            ultraAuthentication.getUsername(),
            ultraAuthentication.getPassword());

    try {
      parametersToConnection =
          parser.createCommandLine(
              vncProgramConfig.getParametersToConnectionWithUltraVNCAuthentication());
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
