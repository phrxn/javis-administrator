package com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.vnc.UltraVNCMslogonAuthentication;
import com.quazzom.javis.administrator.vnc.VNCAuthentication;

public class Administrator {

  private static final String PROGRAM_NAME = "Javis administrator";
  private static final String PROGRAM_VERSION = "0.4.0-rc";

  private String strProgramName;
  private String strProgramVersion;

  private VNCAuthentication vncAuthentication;
  private UltraVNCMslogonAuthentication ultraVNCMslogonAuthentication;

  public Administrator() {
    this(PROGRAM_NAME, PROGRAM_VERSION);
  }

  public Administrator(String strProgramName, String strProgramVersion) {
    super();
    this.strProgramName = strProgramName;
    this.strProgramVersion = strProgramVersion;
    this.vncAuthentication = new VNCAuthentication("", false);
    this.ultraVNCMslogonAuthentication = new UltraVNCMslogonAuthentication("", "", false);
  }

  public String getProgramName() {
    return strProgramName;
  }

  public String getProgramVersion() {
    return strProgramVersion;
  }

  public VNCAuthentication getVncAuthentication() {
    return vncAuthentication;
  }

  public void setVncAuthentication(VNCAuthentication vncAuthentication) {
    this.vncAuthentication = vncAuthentication;
  }

  public UltraVNCMslogonAuthentication getUltraVNCMslogonAuthentication() {
    return ultraVNCMslogonAuthentication;
  }

  public void setUltraVNCMslogonAuthentication(
      UltraVNCMslogonAuthentication ultraVNCMslogonAuthentication) {
    this.ultraVNCMslogonAuthentication = ultraVNCMslogonAuthentication;
  }
}
