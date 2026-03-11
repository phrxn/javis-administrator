package com.quazzom.javis.administrator.uncaught_exceptions;

public class InformationAboutUncaughtException {
  private String strProgramName;
  private String strProgramVersion;
  private String strThreadName;
  private String strThreadID;
  private String strStackTrace;

  public InformationAboutUncaughtException(
      String strProgramName,
      String strProgramVersion,
      String strThreadName,
      String strThreadID,
      String strStackTrace) {
    super();
    this.strProgramName = strProgramName;
    this.strProgramVersion = strProgramVersion;
    this.strThreadName = strThreadName;
    this.strThreadID = strThreadID;
    this.strStackTrace = strStackTrace;
  }

  public String getStrProgramName() {
    return strProgramName;
  }

  public void setStrProgramName(String strProgramName) {
    this.strProgramName = strProgramName;
  }

  public String getStrProgramVersion() {
    return strProgramVersion;
  }

  public void setStrProgramVersion(String strProgramVersion) {
    this.strProgramVersion = strProgramVersion;
  }

  public String getStrThreadName() {
    return strThreadName;
  }

  public void setStrThreadName(String strThreadName) {
    this.strThreadName = strThreadName;
  }

  public String getStrThreadID() {
    return strThreadID;
  }

  public void setStrThreadID(String strThreadID) {
    this.strThreadID = strThreadID;
  }

  public String getStrStackTrace() {
    return strStackTrace;
  }

  public void setStrStackTrace(String strStackTrace) {
    this.strStackTrace = strStackTrace;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    InformationAboutUncaughtException other = (InformationAboutUncaughtException) obj;
    if (strProgramName == null) {
      if (other.strProgramName != null) return false;
    } else if (!strProgramName.equals(other.strProgramName)) return false;
    if (strProgramVersion == null) {
      if (other.strProgramVersion != null) return false;
    } else if (!strProgramVersion.equals(other.strProgramVersion)) return false;
    if (strStackTrace == null) {
      if (other.strStackTrace != null) return false;
    } else if (!strStackTrace.equals(other.strStackTrace)) return false;
    if (strThreadID == null) {
      if (other.strThreadID != null) return false;
    } else if (!strThreadID.equals(other.strThreadID)) return false;
    if (strThreadName == null) {
      if (other.strThreadName != null) return false;
    } else if (!strThreadName.equals(other.strThreadName)) return false;
    return true;
  }
}
