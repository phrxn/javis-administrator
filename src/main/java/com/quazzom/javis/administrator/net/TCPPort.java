package com.quazzom.javis.administrator.net;

import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.lang.TextNotFoundException;

public class TCPPort {

  public static final int MAX_PORT_TCP_VALUE = 65535;

  private int portValue;
  private Text theLanguage;

  public TCPPort() {
    this.portValue = 0;
    this.theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.TCP_PORT);
  }

  public TCPPort(String portValue) throws TCPPortException {
    this(portValue, LanguageFactory.getLanguage(LanguagePathToFile.TCP_PORT));
  }

  public TCPPort(int portValue) throws TCPPortException {
    this(portValue, LanguageFactory.getLanguage(LanguagePathToFile.TCP_PORT));
  }

  public TCPPort(int portValue, Text theLanguage) throws TCPPortException {
    this.theLanguage = theLanguage;
    setPortValue(portValue);
  }

  public TCPPort(String portValue, Text theLanguage) throws TCPPortException {
    this.theLanguage = theLanguage;
    setPortValue(portValue);
  }

  public void setPortValue(String text) throws TCPPortException {
    try {
      int portValue = Integer.parseInt(text);
      setPortValue(portValue);
    } catch (NumberFormatException e) {
      throwInvalidPortValueException();
    }
  }

  public void setPortValue(int portValue) throws TCPPortException {
    if (portValue < 0 || portValue > MAX_PORT_TCP_VALUE) {
      throwInvalidPortValueException();
    }
    this.portValue = portValue;
  }

  public int getPortValue() {
    return portValue;
  }

  void throwInvalidPortValueException() throws TCPPortException, TextNotFoundException {
    throw new TCPPortException(
        theLanguage.getText("INVALID_PORT_VALUE_EXCEPTION", MAX_PORT_TCP_VALUE));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + portValue;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    TCPPort other = (TCPPort) obj;
    if (portValue != other.portValue) return false;
    return true;
  }
}
