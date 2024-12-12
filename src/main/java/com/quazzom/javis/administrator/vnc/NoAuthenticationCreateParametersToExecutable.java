package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.io.parser.ParserNoAuthentication;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class NoAuthenticationCreateParametersToExecutable implements CreateParametersToExecutable {

  private SwingMediator swingMediator;
  private Text theLanguage;

  public NoAuthenticationCreateParametersToExecutable(SwingMediator swingMediator) {
    this(
        swingMediator,
        LanguageFactory.getLanguage(
            LanguagePathToFile.NO_AUTHENTICATION_CREATEPARAMETERS_TO_EXECUTABLE));
  }

  public NoAuthenticationCreateParametersToExecutable(
      SwingMediator swingMediator, Text theLanguage) {
    this.swingMediator = swingMediator;
    this.theLanguage = theLanguage;
  }

  @Override
  public Optional<List<String>> createParameters(
      ComputerConnectionInformations computer, VNCProgramConfiguration vncProgramConfig) {

    List<String> parametersToConnection = new ArrayList<String>();

    Parser parser =
        new ParserNoAuthentication(computer.getHostName(), String.valueOf(computer.getPort()));

    try {
      parametersToConnection =
          parser.createCommandLine(
              vncProgramConfig.getParametersToConnectionWithNoAuthentication());
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
