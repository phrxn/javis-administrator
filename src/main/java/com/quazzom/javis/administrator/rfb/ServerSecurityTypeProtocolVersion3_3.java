package com.quazzom.javis.administrator.rfb;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ServerSecurityTypeProtocolVersion3_3 implements ServerSecurityType {

  public static final int INVALID = 0;
  public static final int NO_AUTHENTICATION = 1;
  public static final int VNC_AUTHENTICATION = 2;
  public static final int ULTRAVNC_MSLOGON = -6;

  private Text theLanguage;

  public ServerSecurityTypeProtocolVersion3_3() {
    this(LanguageFactory.getLanguage(LanguagePathToFile.SERVER_SECURITY_TYPE_PROTOCOL_VERSION3_3));
  }

  public ServerSecurityTypeProtocolVersion3_3(Text theLanguage) {
    this.theLanguage = theLanguage;
  }

  @Override
  public List<RFBAuthenticationTypes> decode(InputStream in) throws RFBProtocolException {

    DataInputStream dataInput = new DataInputStream(in);
    int type;
    try {
      type = dataInput.readInt();
    } catch (SocketTimeoutException ex) {
      throw new RFBProtocolException(theLanguage.getText("SOCKET_TIMEOUT_EXCEPTION"));
    } catch (IOException e) {
      throw new RFBProtocolException(e.getMessage());
    }

    // if the server returned 0 now it means there was an error during the protocol definition phase
    if (type == 0) {
      ServerMessageError errorMessage = new ServerMessageError();
      throw new RFBProtocolException(errorMessage.decodeMessageError(in));
    }
    return createList(type);
  }

  public List<RFBAuthenticationTypes> createList(int type) throws RFBProtocolException {

    List<RFBAuthenticationTypes> listOfAuthenticationTypes =
        new ArrayList<RFBAuthenticationTypes>();

    switch (type) {
      case NO_AUTHENTICATION:
        listOfAuthenticationTypes.add(RFBAuthenticationTypes.NONE);
        break;
      case VNC_AUTHENTICATION:
        listOfAuthenticationTypes.add(RFBAuthenticationTypes.VNC);
        break;
      case ULTRAVNC_MSLOGON:
        listOfAuthenticationTypes.add(RFBAuthenticationTypes.ULTRA_VNC_MSLOGON);
        break;
      default:
        throw new RFBProtocolException(
            theLanguage.getText("UNKNOWN_AUTHENTICATION_TYPE_EXCEPTION", type));
    }

    return listOfAuthenticationTypes;
  }
}
