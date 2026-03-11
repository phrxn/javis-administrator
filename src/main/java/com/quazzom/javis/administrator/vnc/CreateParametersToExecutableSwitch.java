package com.quazzom.javis.administrator.vnc;

import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class CreateParametersToExecutableSwitch implements CreateParametersToExecutable {

  private List<RFBAuthenticationTypes> listOfClientAuthentications;
  private SwingMediator swingMediator;

  public CreateParametersToExecutableSwitch(
      List<RFBAuthenticationTypes> listOfClientAuthentications, SwingMediator swingMediator) {
    this.listOfClientAuthentications = listOfClientAuthentications;
    this.swingMediator = swingMediator;
  }

  @Override
  public Optional<List<String>> createParameters(
      ComputerConnectionInformations computer, VNCProgramConfiguration vncProgramConfig) {

    CreateParametersToExecutable cpe;

    if (listOfClientAuthentications.contains(RFBAuthenticationTypes.VNC)) {
      cpe = new VNCAuthenticationCreateParametersToExecutable(swingMediator);
    } else if (listOfClientAuthentications.contains(RFBAuthenticationTypes.ULTRA_VNC_MSLOGON)) {
      cpe = new UltraVNCMslogonAuthenticationCreateParametersToExecutable(swingMediator);
    } else {
      cpe = new NoAuthenticationCreateParametersToExecutable(swingMediator);
    }

    Optional<List<String>> parametersToTheExecutableOptional =
        cpe.createParameters(computer, vncProgramConfig);

    // if any error occurred empty is returned
    if (parametersToTheExecutableOptional.isEmpty()) {
      return Optional.empty();
    }

    List<String> parametersToInteration;

    Parser parser = new Parser();

    try {
      if (computer.isOnlyView()) {
        parametersToInteration =
            parser.createCommandLine(vncProgramConfig.getParameterForNotInteraction());
      } else {
        parametersToInteration =
            parser.createCommandLine(vncProgramConfig.getParameterForInteraction());
      }
    } catch (GrammarException e) {
      // swingMediador show error swingMediator
      return Optional.empty();
    }

    List<String> executableLine;

    try {
      executableLine = parser.createCommandLine(vncProgramConfig.getExecutionLine());
    } catch (GrammarException e) {
      // sswingMediador show error swingMediator
      return Optional.empty();
    }

    executableLine.addAll(parametersToTheExecutableOptional.get());
    executableLine.addAll(parametersToInteration);

    return Optional.of(executableLine);
  }
}
