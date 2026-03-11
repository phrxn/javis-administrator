package com.quazzom.javis.administrator.rfb;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ServerSecurityTypeProtocolVersions implements ServerSecurityType {

  public static final byte INVALID = 0;
  public static final byte NO_AUTHENTICATION = 1;
  public static final byte VNC_AUTHENTICATION = 2;
  public static final byte ULTRAVNC_MSLOGON = 17;

  private Text theLanguage;

  public ServerSecurityTypeProtocolVersions() {
    this(LanguageFactory.getLanguage(LanguagePathToFile.SERVER_SERCURITY_TYPE_PROTOCOL_VERSIONS));
  }

  public ServerSecurityTypeProtocolVersions(Text theLanguage) {
    this.theLanguage = theLanguage;
  }

  public List<RFBAuthenticationTypes> createList(List<Byte> types) throws RFBProtocolException {

    List<RFBAuthenticationTypes> listOfAuthenticationTypes =
        new ArrayList<RFBAuthenticationTypes>();

    if (types.contains(NO_AUTHENTICATION)) {
      listOfAuthenticationTypes.add(RFBAuthenticationTypes.NONE);
    }
    if (types.contains(VNC_AUTHENTICATION)) {
      listOfAuthenticationTypes.add(RFBAuthenticationTypes.VNC);
    }
    if (types.contains(ULTRAVNC_MSLOGON)) {
      listOfAuthenticationTypes.add(RFBAuthenticationTypes.ULTRA_VNC_MSLOGON);
    }

    // if the size is 0 it means that none of the methods offered by the server are valid!
    if (listOfAuthenticationTypes.size() == 0) {
      throw new RFBProtocolException(theLanguage.getText("UNKNOWN_AUTHENTICATION_TYPE_EXCEPTION"));
    }

    return listOfAuthenticationTypes;
  }

  @Override
  public List<RFBAuthenticationTypes> decode(InputStream in) throws RFBProtocolException {

    try {
      DataInputStream dataInput = new DataInputStream(in);
      byte typeCount = dataInput.readByte();

      // if the server returned 0 now it means there was an error
      // during the protocol definition phase
      if (typeCount == 0) {
        ServerMessageError errorMessage = new ServerMessageError();
        throw new RFBProtocolException(errorMessage.decodeMessageError(in));
      }

      List<Byte> types = new ArrayList<Byte>();
      for (int i = 0; i < typeCount; i++) {
        byte type = dataInput.readByte();
        types.add(type);
      }

      return createList(types);
    } catch (SocketTimeoutException ex) {
      throw new RFBProtocolException(theLanguage.getText("SOCKET_TIMEOUT_EXCEPTION"));
    } catch (Exception e) {
      throw new RFBProtocolException(e.getMessage());
    }
  }
}
