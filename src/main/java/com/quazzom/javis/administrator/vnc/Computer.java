package com.quazzom.javis.administrator.vnc;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class Computer implements Cloneable {

  public static final int MIN_ID_VALUE = 1;
  public static final int MAX_STRING_SIZE_USERNAME = 255;
  public static final int MAX_STRING_SIZE_HOSTNAME = 64;

  // max database column size, :) it's here as a reference
  public static final int MAX_STRING_SIZE_CLIENT_VERSION = 20;
  public static final int MAX_STRING_SIZE_DETAILS = 255;

  public enum PowerStatus {
    INVALID(0x0, "INVALID"),
    POWEROFF(0x1, "POWEROFF"),
    POWERON(0x2, "POWERON");

    private int statusValue;
    private String languagueID;

    PowerStatus(int statusValue, String languagueID) {
      this.statusValue = statusValue;
      this.languagueID = languagueID;
    }

    public int value() {
      return statusValue;
    }

    public String getLanguagueID() {
      return languagueID;
    }
  };

  public enum TypeOfLoginSession {
    INVALID(0x1, "INVALID"),
    CONSOLE_CONNECT(0x2, "CONSOLE_CONNECT"),
    CONSOLE_DISCONNECT(0x3, "CONSOLE_DISCONNECT"),
    REMOTE_CONNECT(0x4, "REMOTE_CONNECT"),
    REMOTE_DISCONNECT(0x5, "REMOTE_DISCONNECT"),
    SESSION_LOGON(0x6, "SESSION_LOGON"),
    SESSION_LOGOFF(0x7, "SESSION_LOGOFF"),
    SESSION_LOCK(0x8, "SESSION_LOCK"),
    SESSION_UNLOCK(0x9, "SESSION_UNLOCK"),
    SESSION_REMOTE_CONTROL(0xa, "SESSION_REMOTE_CONTROL"),
    SESSION_CREATE(0xb, "SESSION_CREATE"),
    SESSION_TERMINATE(0xc, "SESSION_TERMINATE"),
    SESSION_LIX_LOGON(0xd, "SESSION_LIX_LOGON");

    int statusValue;
    private String languagueID;

    TypeOfLoginSession(int statusValue, String languagueID) {
      this.statusValue = statusValue;
      this.languagueID = languagueID;
    }

    public int value() {
      return statusValue;
    }

    public String getLanguagueID() {
      return languagueID;
    }
  };

  private int id;
  private PowerStatus powerStatus;
  private TypeOfLoginSession sessionType;
  private String username;
  private String hostname;
  private IP ip;
  private boolean isManual;
  private Date loginDate;
  private String javisClientVersion;
  private String details;

  private Text theLanguage;

  public Computer() throws ComputerException, IPException {
    this(
        1,
        PowerStatus.INVALID,
        TypeOfLoginSession.INVALID,
        "a",
        "a",
        new IP(IP.ANDRESS_NOTHING),
        false,
        new Date(0),
        "",
        "");
  }

  public Computer(Text theLanguage) throws ComputerException, IPException {
    this(
        1,
        PowerStatus.INVALID,
        TypeOfLoginSession.INVALID,
        "a",
        "a",
        new IP(IP.ANDRESS_NOTHING),
        false,
        new Date(0),
        "",
        "",
        theLanguage);
  }

  public Computer(
      int id,
      PowerStatus statusComputer,
      TypeOfLoginSession sessionType,
      String username,
      String hostname,
      IP ip,
      boolean isManual,
      Date loginDate,
      String javisClientVersion,
      String details)
      throws ComputerException {
    this(
        id,
        statusComputer,
        sessionType,
        username,
        hostname,
        ip,
        isManual,
        loginDate,
        javisClientVersion,
        details,
        LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER));
  }

  public Computer(
      int id,
      PowerStatus statusComputer,
      TypeOfLoginSession sessionType,
      String username,
      String hostname,
      IP ip,
      boolean isManual,
      Date loginDate,
      String javisClientVersion,
      String details,
      Text theLanguage)
      throws ComputerException {
    this.theLanguage = theLanguage;
    setId(id);
    setPowerStatus(statusComputer);
    setSessionType(sessionType);
    setUsername(username);
    setHostname(hostname);
    setIP(ip);
    setManual(isManual);
    setLoginDate(loginDate);
    setJavisClientVersion(javisClientVersion);
    setDetails(details);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) throws ComputerException {
    if (id < MIN_ID_VALUE)
      throw new ComputerException(theLanguage.getText("INVALID_ID_EXCEPTION", id));
    this.id = id;
  }

  public PowerStatus getPowerStatus() {
    return powerStatus;
  }

  public String getPowerStatusStr() {
    return theLanguage.getText(powerStatus.getLanguagueID());
  }

  public void setPowerStatus(int statusComputer) throws ComputerException {
    for (PowerStatus powerStatus : PowerStatus.values()) {
      if (powerStatus.value() == statusComputer) {
        setPowerStatus(powerStatus);
        return;
      }
    }
    throw new ComputerException(theLanguage.getText("POWERSTATUS_TYPE_EXCEPTION", statusComputer));
  }

  public void setPowerStatus(PowerStatus powerStatus) {
    this.powerStatus = powerStatus;
  }

  public TypeOfLoginSession getSessionType() {
    return sessionType;
  }

  public String getSessionTypeStr() {
    return theLanguage.getText(sessionType.getLanguagueID());
  }

  public void setSessionType(int sessionType) throws ComputerException {
    for (TypeOfLoginSession typeOfSession : TypeOfLoginSession.values()) {
      if (typeOfSession.value() == sessionType) {
        setSessionType(typeOfSession);
        return;
      }
    }
    throw new ComputerException(theLanguage.getText("SESSION_TYPE_EXCEPTION", sessionType));
  }

  public void setSessionType(TypeOfLoginSession sessionType) {
    this.sessionType = sessionType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) throws ComputerException {
    username = username.trim();
    if (username.isEmpty())
      throw new ComputerException(theLanguage.getText("INVALID_USERNAME_EMPTY_EXCEPTION"));
    if (username.length() > MAX_STRING_SIZE_USERNAME)
      throw new ComputerException(
          theLanguage.getText("INVALID_USERNAME_SIZE_EXCEPTION", MAX_STRING_SIZE_USERNAME));
    this.username = username.toUpperCase();
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) throws ComputerException {
    hostname = hostname.trim();
    if (hostname.isEmpty())
      throw new ComputerException(theLanguage.getText("INVALID_HOSTNAME_EMPTY_EXCEPTION"));
    if (hostname.length() > MAX_STRING_SIZE_HOSTNAME)
      throw new ComputerException(
          theLanguage.getText("INVALID_HOSTNAME_SIZE_EXCEPTION", MAX_STRING_SIZE_HOSTNAME));
    this.hostname = hostname.toUpperCase();
  }

  public IP getIP() {
    return ip;
  }

  public String getIPStr() {
    return ip.getIP();
  }

  public void setIP(IP ip) throws ComputerException {
    this.ip = ip;
  }

  public boolean isManual() {
    return isManual;
  }

  public String isManualStr() {
    if (isManual) return theLanguage.getText("YES");
    return theLanguage.getText("NO");
  }

  public void setManual(boolean isManual) {
    this.isManual = isManual;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public String getLoginDateStr() {

    if (loginDate.getTime() == 0) return "-";

    String timeFormatPatter = theLanguage.getText("TIME_FORMAT_PATTERN");
    SimpleDateFormat sdf = new SimpleDateFormat(timeFormatPatter);
    return sdf.format(loginDate);
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public String getJavisClientVersion() {
    if (javisClientVersion.isEmpty()) return "-";
    return javisClientVersion;
  }

  public void setJavisClientVersion(String clientVersion) throws ComputerException {

    clientVersion = clientVersion.trim();

    if (clientVersion.length() > MAX_STRING_SIZE_CLIENT_VERSION) {
      throw new ComputerException(
          theLanguage.getText(
              "JAVIS_CLIENT_VERSION_SIZE_EXCEPTION", MAX_STRING_SIZE_CLIENT_VERSION));
    }

    this.javisClientVersion = clientVersion;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) throws ComputerException {

    details = details.trim();

    if (details.length() > MAX_STRING_SIZE_DETAILS)
      throw new ComputerException(
          theLanguage.getText("INVALID_DETAILS_SIZE_EXCEPTION", MAX_STRING_SIZE_DETAILS));

    this.details = details;
  }

  public Text getTheLanguage() {
    return theLanguage;
  }

  @Override
  public String toString() {
    return "Computer [id="
        + id
        + ", statusComputer="
        + getPowerStatusStr()
        + ", sessionType="
        + getSessionTypeStr()
        + ", username="
        + username
        + ", hostname="
        + hostname
        + ", ip="
        + ip
        + ", isManual="
        + isManual
        + ", loginDate="
        + loginDate
        + ", clientVersion="
        + getJavisClientVersion()
        + ", details="
        + details
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Computer other = (Computer) obj;
    if (id != other.id) return false;
    return true;
  }

  @Override
  public Computer clone() {
    try {

      // DO NOT create a copy of `theLanguage`, as all computers share
      // the same object that comes from the LanguageFactory!

      Computer clone = (Computer) super.clone();

      clone.ip = this.ip.clone();
      clone.loginDate = (Date) this.loginDate.clone();

      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
