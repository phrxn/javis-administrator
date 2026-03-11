package com.quazzom.javis.administrator.vnc;

import java.util.List;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.io.parser.ParserNoAuthentication;
import com.quazzom.javis.administrator.io.parser.ParserUltraVNCMslogonAuthentication;
import com.quazzom.javis.administrator.io.parser.ParserVNCAuthentication;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPort;
import com.quazzom.javis.administrator.net.TCPPortException;

public class VNCProgramConfiguration {

  public static final int MAX_STRING_SIZE_VNC_NAME = 64;
  public static final int MAX_STRING_SIZE_EXECUTION_LINE = 256;
  public static final int MAX_STRING_SIZE_PATH_TO_EXECUTABLE = 256;
  public static final int MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITHOUT_AUTHENTICATION = 256;
  public static final int MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_VNC_AUTHENTICATION = 256;
  public static final int MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION =
      256;
  public static final int MAX_STRING_SIZE_PARAMETER_FOR_INTERACTION = 64;
  public static final int MAX_STRING_SIZE_PARAMETER_FOR_NOT_INTERACTION = 64;

  public static final int STRING_SIZE_CHECKSUM = 64;

  public static final int MINIMUM_TIMEOUT_VALID_IN_SECONDS = 2;
  public static final int MAXIMUM_TIMEOUT_VALID_IN_SECONDS = 60;

  private Text theLanguage;

  private int id;
  private String name = "";
  private TCPPort defaultPortToAccess;
  private String executionLine = "";
  private String pathToExecutable = "";
  private int timeoutInSecondsToConnection;
  private String parametersToConnectionWithNoAuthentication = "";
  private String parametersToConnectionWithVNCAuthentication = "";
  private String parametersToConnectionWithUltraVNCAuthentication = "";
  private String parameterForInteraction = "";
  private String parameterForNotInteraction = "";
  private String checkSum = "";
  private boolean isToUseChecksum = true;

  public VNCProgramConfiguration() {
    this(LanguageFactory.getLanguage(LanguagePathToFile.VNC_PROGRAM_CONFIGURATION));
  }

  public VNCProgramConfiguration(Text theLanguage) {
    this.theLanguage = theLanguage;
    this.timeoutInSecondsToConnection = 3;
    defaultPortToAccess = new TCPPort();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws VNCProgramConfigurationException {
    if (name.isEmpty()) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("VNC_NAME_EMPTY_STRING_EXCEPTION"));
    }
    if (name.length() > MAX_STRING_SIZE_VNC_NAME) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("VNC_NAME_SIZE_STRING_EXCEPTION", MAX_STRING_SIZE_VNC_NAME));
    }
    this.name = name;
  }

  public int getDefaultPortToAccess() {
    return defaultPortToAccess.getPortValue();
  }

  public void setDefaultPortToAccess(int defaultPortToAccess) throws TCPPortException {
    this.defaultPortToAccess.setPortValue(defaultPortToAccess);
  }

  public void setDefaultPortToAccess(String defaultPortToAccess) throws TCPPortException {
    this.defaultPortToAccess.setPortValue(defaultPortToAccess);
  }

  public String getExecutionLine() {
    return executionLine;
  }

  public void setExecutionLine(String executionLine) throws VNCProgramConfigurationException {

    if (executionLine.length() > MAX_STRING_SIZE_EXECUTION_LINE) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "EXECUTION_LINE_SIZE_STRING_EXCEPTION", MAX_STRING_SIZE_EXECUTION_LINE));
    }

    List<String> commandLine;

    try {
      Parser parser = new Parser();
      commandLine = parser.createCommandLine(executionLine);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("EXECUTION_LINE_INVALID_SYNTAX_EXCEPTION", e.getMessage()));
    }

    if (commandLine.isEmpty()) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("EXECUTION_LINE_EMPTY_STRING_EXCEPTION"));
    }

    this.executionLine = executionLine;
  }

  public String getPathToExecutable() {
    return pathToExecutable;
  }

  public void setPathToExecutable(String pathToExecutable, boolean isToUseChecksum)
      throws VNCProgramConfigurationException {

    if (!isToUseChecksum) {
      return;
    }

    if (pathToExecutable.length() > MAX_STRING_SIZE_PATH_TO_EXECUTABLE) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PATH_TO_EXECUTABLE_SIZE_STRING_EXCEPTION", MAX_STRING_SIZE_PATH_TO_EXECUTABLE));
    }

    List<String> commandLine;

    try {
      Parser parser = new Parser();
      commandLine = parser.createCommandLine(pathToExecutable);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("PATH_TO_EXECUTABLE_INVALID_SYNTAX_EXCEPTION", e.getMessage()));
    }

    if (commandLine.size() == 0) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("PATH_TO_EXECUTABLE_EMPTY_STRING_EXCEPTION"));
    }

    this.pathToExecutable = pathToExecutable;
  }

  public int getTimeoutInSecondsToConnection() {
    return timeoutInSecondsToConnection;
  }

  public void setTimeoutInSecondsToConnection(String timeoutInSecondsToConnection)
      throws VNCProgramConfigurationException {

    int time = 0;

    try {
      time = Integer.valueOf(timeoutInSecondsToConnection);
    } catch (Exception e) {
      throwInvalidTimeoutValueException();
    }
    setTimeoutInSecondsToConnection(time);
  }

  public void setTimeoutInSecondsToConnection(int timeoutInSecondsToConnection)
      throws VNCProgramConfigurationException {
    if (timeoutInSecondsToConnection < MINIMUM_TIMEOUT_VALID_IN_SECONDS
        || timeoutInSecondsToConnection > MAXIMUM_TIMEOUT_VALID_IN_SECONDS) {
      throwInvalidTimeoutValueException();
    }

    this.timeoutInSecondsToConnection = timeoutInSecondsToConnection;
  }

  public String getParametersToConnectionWithNoAuthentication() {
    return parametersToConnectionWithNoAuthentication;
  }

  public void setParametersToConnectionWithNoAuthentication(
      String parametersToConnectionWithNoAuthentication) throws VNCProgramConfigurationException {

    if (parametersToConnectionWithNoAuthentication.length()
        > MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITHOUT_AUTHENTICATION) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITHOUT_AUTHENTICATION_SIZE_STRING_EXCEPTION",
              MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITHOUT_AUTHENTICATION));
    }

    try {
      Parser parser = new ParserNoAuthentication("a", "b");
      parser.createCommandLine(parametersToConnectionWithNoAuthentication);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITHOUT_AUTHENTICATION_INVALID_SYNTAX_EXCEPTION",
              e.getMessage()));
    }

    this.parametersToConnectionWithNoAuthentication = parametersToConnectionWithNoAuthentication;
  }

  public String getParametersToConnectionWithVNCAuthentication() {
    return parametersToConnectionWithVNCAuthentication;
  }

  public void setParametersToConnectionWithVNCAuthentication(
      String parametersToConnectionWithVNCAuthentication) throws VNCProgramConfigurationException {

    if (parametersToConnectionWithVNCAuthentication.length()
        > MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_VNC_AUTHENTICATION) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITH_VNC_AUTHENTICATION_SIZE_STRING_EXCEPTION",
              MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_VNC_AUTHENTICATION));
    }

    try {
      Parser parser = new ParserVNCAuthentication("a", "b", "c");
      parser.createCommandLine(parametersToConnectionWithVNCAuthentication);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITH_VNC_AUTHENTICATION_INVALID_SYNTAX_EXCEPTION",
              e.getMessage()));
    }

    this.parametersToConnectionWithVNCAuthentication = parametersToConnectionWithVNCAuthentication;
  }

  public String getParametersToConnectionWithUltraVNCAuthentication() {
    return parametersToConnectionWithUltraVNCAuthentication;
  }

  public void setParametersToConnectionWithUltraVNCAuthentication(
      String parametersToConnectionWithUltraVNCAuthentication)
      throws VNCProgramConfigurationException {

    if (parametersToConnectionWithUltraVNCAuthentication.length()
        > MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION_SIZE_STRING_EXCEPTION",
              MAX_STRING_SIZE_PARAMETERS_TO_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION));
    }

    try {
      Parser parser = new ParserUltraVNCMslogonAuthentication("a", "b", "c", "d");
      parser.createCommandLine(parametersToConnectionWithUltraVNCAuthentication);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETERS_FOR_CONNECTION_WITH_ULTRA_VNC_AUTHENTICATION_INVALID_SYNTAX_EXCEPTION",
              e.getMessage()));
    }

    this.parametersToConnectionWithUltraVNCAuthentication =
        parametersToConnectionWithUltraVNCAuthentication;
  }

  public String getParameterForInteraction() {
    return parameterForInteraction;
  }

  public void setParameterForInteraction(String parameterForInteraction)
      throws VNCProgramConfigurationException {
    if (parameterForInteraction.length() > MAX_STRING_SIZE_PARAMETER_FOR_INTERACTION) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETER_FOR_INTERACTION_SIZE_STRING_EXCEPTION",
              MAX_STRING_SIZE_PARAMETER_FOR_INTERACTION));
    }

    try {
      Parser parser = new Parser();
      parser.createCommandLine(parameterForInteraction);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETER_FOR_INTERACTION_INVALID_SYNTAX_EXCEPTION", e.getMessage()));
    }

    this.parameterForInteraction = parameterForInteraction;
  }

  public String getParameterForNotInteraction() {
    return parameterForNotInteraction;
  }

  public void setParameterForNotInteraction(String parameterForNotInteraction)
      throws VNCProgramConfigurationException {
    if (parameterForNotInteraction.length() > MAX_STRING_SIZE_PARAMETER_FOR_NOT_INTERACTION) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETER_FOR_NOT_INTERACTION_SIZE_STRING_EXCEPTION",
              MAX_STRING_SIZE_PARAMETER_FOR_NOT_INTERACTION));
    }

    try {
      Parser parser = new Parser();
      parser.createCommandLine(parameterForNotInteraction);
    } catch (GrammarException e) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText(
              "PARAMETER_FOR_NOT_INTERACTION_INVALID_SYNTAX_EXCEPTION", e.getMessage()));
    }

    this.parameterForNotInteraction = parameterForNotInteraction;
  }

  public String getCheckSum() {
    return checkSum;
  }

  public void setCheckSum(String checkSum, boolean isToUseChecksum)
      throws VNCProgramConfigurationException {

    if (!isToUseChecksum) {
      return;
    }

    if (checkSum.length() != STRING_SIZE_CHECKSUM) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("CHECKSUM_STRING_SIZE_EXCEPTION", STRING_SIZE_CHECKSUM));
    }
    checkSum = checkSum.toLowerCase();

    if (!checkSum.matches("[0-9a-fA-F]+")) {
      throw new VNCProgramConfigurationException(
          theLanguage.getText("CHECKSUM_INVALID_FORMAT_EXCEPTION", STRING_SIZE_CHECKSUM));
    }

    this.checkSum = checkSum;
  }

  public boolean isToUseChecksum() {
    return isToUseChecksum;
  }

  public void setToUseChecksum(boolean isToUseChecksum) {
    this.isToUseChecksum = isToUseChecksum;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    VNCProgramConfiguration other = (VNCProgramConfiguration) obj;
    if (checkSum == null) {
      if (other.checkSum != null) return false;
    } else if (!checkSum.equals(other.checkSum)) return false;
    if (defaultPortToAccess == null) {
      if (other.defaultPortToAccess != null) return false;
    } else if (!defaultPortToAccess.equals(other.defaultPortToAccess)) return false;
    if (executionLine == null) {
      if (other.executionLine != null) return false;
    } else if (!executionLine.equals(other.executionLine)) return false;
    if (id != other.id) return false;
    if (isToUseChecksum != other.isToUseChecksum) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (parameterForInteraction == null) {
      if (other.parameterForInteraction != null) return false;
    } else if (!parameterForInteraction.equals(other.parameterForInteraction)) return false;
    if (parameterForNotInteraction == null) {
      if (other.parameterForNotInteraction != null) return false;
    } else if (!parameterForNotInteraction.equals(other.parameterForNotInteraction)) return false;
    if (parametersToConnectionWithNoAuthentication == null) {
      if (other.parametersToConnectionWithNoAuthentication != null) return false;
    } else if (!parametersToConnectionWithNoAuthentication.equals(
        other.parametersToConnectionWithNoAuthentication)) return false;
    if (parametersToConnectionWithUltraVNCAuthentication == null) {
      if (other.parametersToConnectionWithUltraVNCAuthentication != null) return false;
    } else if (!parametersToConnectionWithUltraVNCAuthentication.equals(
        other.parametersToConnectionWithUltraVNCAuthentication)) return false;
    if (parametersToConnectionWithVNCAuthentication == null) {
      if (other.parametersToConnectionWithVNCAuthentication != null) return false;
    } else if (!parametersToConnectionWithVNCAuthentication.equals(
        other.parametersToConnectionWithVNCAuthentication)) return false;
    if (pathToExecutable == null) {
      if (other.pathToExecutable != null) return false;
    } else if (!pathToExecutable.equals(other.pathToExecutable)) return false;
    if (timeoutInSecondsToConnection != other.timeoutInSecondsToConnection) return false;
    return true;
  }

  // --------- private methods

  private void throwInvalidTimeoutValueException() throws VNCProgramConfigurationException {
    throw new VNCProgramConfigurationException(
        theLanguage.getText(
            "TIMEOUT_INVALID_VALUE_EXCEPTION",
            MINIMUM_TIMEOUT_VALID_IN_SECONDS,
            MAXIMUM_TIMEOUT_VALID_IN_SECONDS));
  }
}
