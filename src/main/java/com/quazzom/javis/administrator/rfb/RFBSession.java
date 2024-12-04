package com.quazzom.javis.administrator.rfb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class RFBSession {

  private String hostOrIp;
  private int port;

  private InputStream inputStream = null;
  private OutputStream outputStream = null;

  private ProtocolVersion protocolVersion;

  private Socket connection = null;
  private boolean connectionCreated = false;

  private Text theLanguage;

  private final ProtocolVersionNegotiator protocolVersionNegotiator;
  private final SecurityTypeNegotiator securityTypeNegotiator;

  public RFBSession(String hostOrIp, int port) {
    this(hostOrIp, port, LanguageFactory.getLanguage(LanguagePathToFile.RFB_SESSION));
  }

  public RFBSession(String hostOrIp, int port, Text theLanguage) {
    this.hostOrIp = hostOrIp;
    this.port = port;
    this.theLanguage = theLanguage;
    protocolVersionNegotiator = new ProtocolVersionNegotiator();
    securityTypeNegotiator = new SecurityTypeNegotiator();
    connection = new Socket();
  }

  public void createConnection(int timeoutToReadInMilliseconds) throws IOException {
    connection.connect(new InetSocketAddress(hostOrIp, port), timeoutToReadInMilliseconds);
    connection.setSoTimeout(timeoutToReadInMilliseconds);
    inputStream = connection.getInputStream();
    outputStream = connection.getOutputStream();
    connectionCreated = true;
  }

  public List<RFBAuthenticationTypes> getListOfAutentication()
      throws IOException, RFBProtocolException {
    if (!connectionCreated) {
      throw new RFBProtocolException(theLanguage.getText("CONNECTION_NOT_CREATED_EXCEPTION"));
    }

    // negotiates which version of the RFB protocol will be used between Javis and the Server
    protocolVersionNegotiator.negotiate(this);

    // get the list of valid authentications methods
    List<RFBAuthenticationTypes> listOfServerAuthentications =
        securityTypeNegotiator.negotiate(this);

    connection.shutdownInput();
    connection.shutdownOutput();
    closeConnection();

    return listOfServerAuthentications;
  }

  public void closeConnection() {
    if (!connectionCreated) return;
    try {
      connection.close();
      connectionCreated = false;
    } catch (IOException e) {
    }
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public OutputStream getOutputStream() {
    return outputStream;
  }

  public void setProtocolVersion(ProtocolVersion protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  public ProtocolVersion getProtocolVersion() {
    return protocolVersion;
  }
}
