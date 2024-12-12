package com.quazzom.javis.administrator.vnc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;
import com.quazzom.javis.administrator.rfb.RFBSession;

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
    RFBSession rfbSession = new RFBSession(computer.getHostName(), computer.getPort());

    List<RFBAuthenticationTypes> listOfClientAuthentications;

    try {
      rfbSession.createConnection(vncProgramConfiguration.getTimeoutInSecondsToConnection() * 1000);
      listOfClientAuthentications = rfbSession.getListOfAutentication();
    } catch (RFBProtocolException | IOException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR, theLanguage.getText("RFB_SESSION_CONNECTION_ERROR"), e.getMessage());
      return Optional.empty();
    }

    return Optional.of(listOfClientAuthentications);
  }
}
