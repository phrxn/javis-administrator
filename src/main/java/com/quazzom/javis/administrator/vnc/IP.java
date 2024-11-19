package com.quazzom.javis.administrator.vnc;

import java.util.regex.Pattern;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class IP implements Cloneable {

  public static final String ANDRESS_NOTHING = "000.000.000.000";

  // max database column size, :) it's here as a reference
  public static final int MAX_STRING_SIZE_IP = 50;

  private String theIP;
  private Text theLanguage;

  public IP(String ip) throws IPException {
    this(LanguageFactory.getLanguage(LanguagePathToFile.IP), ip);
  }

  public IP(Text theLanguage, String ip) throws IPException {
    setIP(ip);
    this.theLanguage = theLanguage;
  }

  public String getIP() {
    return theIP;
  }

  public void setIP(String ip) throws IPException {
    ip = ip.trim();

    if (ip.isEmpty()) ip = ANDRESS_NOTHING;

    if (ip.length() > MAX_STRING_SIZE_IP)
      throw new IPException(theLanguage.getText("INVALID_IP_SIZE_EXCEPTION", MAX_STRING_SIZE_IP));

    if (ip.equals(ANDRESS_NOTHING)) {
      this.theIP = ip;
      return;
    }

    String regexToSyntax =
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    Pattern pattern = Pattern.compile(regexToSyntax);

    if (!pattern.matcher(ip).matches())
      throw new IPException(theLanguage.getText("STRING_IP_SYNTAX_EXCEPTION", ip));

    this.theIP = ip;
  }

  public Text getTheLanguage() {
    return this.theLanguage;
  }

  @Override
  public IP clone() {
    try {

      // here a shallow copy is enough, since all IP objects share the same theLanguage
      // and the String is immutable
      return (IP) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((theIP == null) ? 0 : theIP.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IP other = (IP) obj;
    if (theIP == null) {
      if (other.theIP != null) return false;
    } else if (!theIP.equals(other.theIP)) return false;
    return true;
  }
}
