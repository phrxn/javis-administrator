package com.quazzom.javis.administrator.vnc;

import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPort;
import com.quazzom.javis.administrator.net.TCPPortException;

public class ComputerConnectionInformations {

  private String hostName;
  private TCPPort tcpPort;
  private boolean isOnlyView;
  private Text theLanguage;

  public ComputerConnectionInformations(String hostName, int port, boolean isOnlyView)
      throws ComputerConnectionInformationsException, TCPPortException {
    this(
        hostName,
        port,
        isOnlyView,
        LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER_CONNECTION_INFORMATIONS));
  }

  public ComputerConnectionInformations(
      String hostName, int port, boolean isOnlyView, Text theLanguage)
      throws ComputerConnectionInformationsException, TCPPortException {
    this.hostName = hostName;
    this.tcpPort = new TCPPort(port);
    this.isOnlyView = isOnlyView;
    this.theLanguage = theLanguage;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) throws ComputerConnectionInformationsException {
    if (hostName.isEmpty()) {
      throw new ComputerConnectionInformationsException(
          theLanguage.getText("HOSTNAME_IS_AN_EMPTY_STRING_EXCEPTION"));
    }
    this.hostName = hostName;
  }

  public int getPort() {
    return tcpPort.getPortValue();
  }

  public void setPort(int port) throws ComputerConnectionInformationsException, TCPPortException {
    tcpPort.setPortValue(port);
  }

  public void setPort(String port)
      throws ComputerConnectionInformationsException, TCPPortException {
    tcpPort.setPortValue(port);
  }

  public boolean isOnlyView() {
    return isOnlyView;
  }

  public void setOnlyView(boolean isOnlyView) {
    this.isOnlyView = isOnlyView;
  }
}
