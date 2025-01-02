package com.quazzom.javis.administrator.vnc;

import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class VNCAuthenticationNegotiatorNetwork implements VNCAuthenticationNegotiator {

  private ComputerConnectionInformations computer;
  private VNCProgramConfiguration vncProgramConfiguration;
  private SwingMediator swingMediator;
  private Text theLanguage;

  public VNCAuthenticationNegotiatorNetwork(
      ComputerConnectionInformations computer,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator) {
    this(
        computer,
        vncProgramConfiguration,
        swingMediator,
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_AUTHENTICATION_NEGOTIATOR_NETWORK));
  }

  public VNCAuthenticationNegotiatorNetwork(
      ComputerConnectionInformations computer,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator,
      Text theLanguage) {
    this.computer = computer;
    this.vncProgramConfiguration = vncProgramConfiguration;
    this.swingMediator = swingMediator;
    this.theLanguage = theLanguage;
  }

  @Override
  public Optional<List<RFBAuthenticationTypes>> searchListOfAuthenticationTypesInVNCClient() {

    ThreadSwingJDialogConnectionVNC thread =
        new ThreadSwingJDialogConnectionVNC(
            swingMediator, computer, vncProgramConfiguration.getTimeoutInSecondsToConnection());

    List<RFBAuthenticationTypes> listOfClientAuthentications =
        thread.getListRFBAuthenticationTypes();

    if (listOfClientAuthentications.size() == 0) {
      return Optional.empty();
    }
    return Optional.of(listOfClientAuthentications);
  }
}
