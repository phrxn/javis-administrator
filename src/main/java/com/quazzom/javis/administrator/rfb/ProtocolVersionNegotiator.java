package com.quazzom.javis.administrator.rfb;

import java.io.IOException;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ProtocolVersionNegotiator {

  private static final int MAJOR_VERSION = 3;
  private static final int MIN_MINOR_VERSION = 3;
  private static final int MAX_MINOR_VERSION = 8;

  private Text theLanguage;

  public ProtocolVersionNegotiator() {
    this.theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.PROTOCOL_VERSION_NEGOTIATOR);
  }

  public ProtocolVersionNegotiator(Text theLanguage) {
    this.theLanguage = theLanguage;
  }

  public void negotiate(RFBSession session) throws RFBProtocolException {

    ProtocolVersion serverVersion = new ProtocolVersion();
    serverVersion = serverVersion.decode(session.getInputStream());

    // check if the major server protocol is at least equal de javis minimum protocol
    if (!serverVersion.atLeast(MAJOR_VERSION, MIN_MINOR_VERSION)) {
      ProtocolVersion javisMinimumSupportedProtocol =
          new ProtocolVersion(MAJOR_VERSION, MIN_MINOR_VERSION);
      throw new RFBProtocolException(
          theLanguage.getText(
              "PROTOCOL_SERVER_MINIMUM_EXCEPTION",
              javisMinimumSupportedProtocol.getVersion(),
              serverVersion.getVersion()));
    }

    ProtocolVersion clientVersion =
        new ProtocolVersion(MAJOR_VERSION, Math.min(serverVersion.getMinor(), MAX_MINOR_VERSION));

    session.setProtocolVersion(clientVersion);
    try {
      clientVersion.encode(session.getOutputStream());
    } catch (IOException e) {
      throw new RFBProtocolException(
          theLanguage.getText("SENDING_JAVIS_PROTOCOL_EXCEPTION", e.getMessage()));
    }
  }
}
