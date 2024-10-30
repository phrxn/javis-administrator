package com.quazzom.javis.administrator;

public class Administrator {

  private static final String PROGRAM_NAME = "Javis administrator";
  private static final String PROGRAM_VERSION = "0.0.1-SNAPSHOT";

  private String strProgramName;
  private String strProgramVersion;

  public Administrator() {
    this(PROGRAM_NAME, PROGRAM_VERSION);
  }

  public Administrator(String strProgramName, String strProgramVersion) {
    super();
    this.strProgramName = strProgramName;
    this.strProgramVersion = strProgramVersion;
  }

  public String getProgramName() {
    return strProgramName;
  }

  public String getProgramVersion() {
    return strProgramVersion;
  }
}
