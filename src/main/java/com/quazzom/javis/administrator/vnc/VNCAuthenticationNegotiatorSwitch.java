package com.quazzom.javis.administrator.vnc;

import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration.ExecutionModeOptions;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class VNCAuthenticationNegotiatorSwitch implements VNCAuthenticationNegotiator {

  VNCAuthenticationNegotiator vncAuthenticationNegotiator;

  public VNCAuthenticationNegotiatorSwitch(
      GeneralConfiguration generalConfiguration,
      ComputerConnectionInformations computer,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator) {

    if (generalConfiguration.getExecutionMode() == ExecutionModeOptions.DEMO) {
      vncAuthenticationNegotiator = new VNCAuthenticationNegotiatorDemo();
    } else {
      vncAuthenticationNegotiator =
          new VNCAuthenticationNegotiatorNetwork(computer, vncProgramConfiguration, swingMediator);
    }
  }

  @Override
  public Optional<List<RFBAuthenticationTypes>> searchListOfAuthenticationTypesInVNCClient() {
    return vncAuthenticationNegotiator.searchListOfAuthenticationTypesInVNCClient();
  }
}
