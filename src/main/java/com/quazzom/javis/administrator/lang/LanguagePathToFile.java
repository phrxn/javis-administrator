package com.quazzom.javis.administrator.lang;

public enum LanguagePathToFile {
  INVALID("-"),
  COPY_POPUP("administrator/gui/popup/CopyPopup.lang"),
  JDIALOG_UNCAUGHT_EXCEPTION("administrator/uncaught_exceptions/JDialogUncaughtException.lang"),
  OUTPUT_STREAM_UNCAUGHT_EXCEPTION(
      "administrator/uncaught_exceptions/OutputStreamUncaughtException.lang"),
  JDIALOG_MESSAGES("administrator/gui/dialog/JDialogMessages.lang"),
  JDIALOG_QUESTIONS("administrator/gui/dialog/JDialogQuestions.lang"),
  START("administrator/Start.lang"),
  AUTHENTICATION_DEMO("administrator/authentication/AuthenticationDemo.lang"),
  LOGIN_INTERNAL_FRAME("administrator/gui/internal_frame/LoginInternalFrame.lang"),
  SWING_EXIT_PROGRAM("administrator/gui/SwingExitProgram.lang"),
  SWING_MAKE_LOGIN("administrator/gui/SwingMakeLogin.lang"),
  JPANEL_MAIN_INTERNAL_FRAME_VNC("administrator/gui/panel/JPanelMainInternalFrameVNC.lang"),
  COMPUTER("administrator/vnc/Computer.lang"),
  VNC_COMPUTER_LIST_INTERNAL_FRAME(
      "administrator/gui/internal_frame/VNCComputerListInternalFrame.lang"),
  JTABLE_COMPUTERS("administrator/gui/table/JTableComputers.lang"),
  IP("administrator/vnc/IP.lang"),
  VNC_COMPUTER_LIST_INTERNAL_FRAME_CONTROLLER(
      "administrator/gui/controllers/VNCComputerListInternalFrameController.lang"),
  JDIALOG_COMPUTER("administrator/gui/dialog/JDialogComputer.lang"),
  JDIALOG_INTERACTION("administrator/gui/dialog/JDialogInteraction.lang"),
  COMPUTER_PERSISTENCE_SQL_SERVER(
      "administrator/persistence/sql_server/ComputerPersistenceSQLServer.lang"),
  COMPUTER_PERSISTENCE_IN_MEMORY(
      "administrator/persistence/memory/ComputerPersistenceInMemory.lang"),
  SQL_SERVER_CONNECTION_INFORMATIONS(
      "administrator/configuration/SQLServerConnectionInformations.lang"),
  CONNECTION_FACTORY_SQL_SERVER(
      "administrator/persistence/sql_server/ConnectionFactorySQLServer.lang"),
  PROTOCOL_VERSION("administrator/rfb/ProtocolVersion.lang"),
  PROTOCOL_VERSION_NEGOTIATOR("administrator/rfb/ProtocolVersionNegotiator.lang"),
  RFB_SESSION("administrator/rfb/RFBSession.lang"),
  SERVER_SECURITY_TYPE_PROTOCOL_VERSION3_3(
      "administrator/rfb/ServerSecurityTypeProtocolVersion3_3.lang"),
  SERVER_SERCURITY_TYPE_PROTOCOL_VERSIONS(
      "administrator/rfb/ServerSecurityTypeProtocolVersions.lang"),
  JDIALOG_VNC_COMPUTER("administrator/gui/dialog/JDialogVNCComputer.lang"),
  COMPUTER_CONNECTION_INFORMATIONS("administrator/vnc/ComputerConnectionInformations.lang"),
  TCP_PORT("administrator/net/TCPPort.lang"),
  VNC_PROGRAM_CONFIGURATION("administrator/vnc/VNCProgramConfiguration.lang"),
  VNC_CONFIGURATION_JDIALOG_CONTROLLER(
      "administrator/gui/controllers/VNCConfigurationJDialogController.lang"),
  VNC_CONFIGURATION_JDIALOG("administrator/gui/dialog/VNCConfigurationJDialog.lang"),
  JDIALOG_ULTRA_VNC_MS_LOGON_AUTHENTICATION_INPUT(
      "administrator/gui/dialog/JDialogUltraVNCMslogonAuthenticationInput.lang"),
  JDIALOG_VNC_AUTHENTICATION_INPUT("administrator/gui/dialog/JDialogVNCAuthenticationInput.lang"),
  GRAMMAR("administrator/io/parser/Grammar.lang"),
  FORMAT_FORMATTING_TOKENS_NO_AUTHENTICATION(
      "administrator/io/parser/FormatFormattingTokensNoAuthentication.lang"),
  FORMAT_FORMATTING_TOKENS_VNC_AUTHENTICATION(
      "administrator/io/parser/FormatFormattingTokensVNCAuthentication.lang"),
  FORMAT_FORMATTING_TOKENS_ULTRA_VNC_MSLOGON_AUTHENTICATION(
      "administrator/io/parser/FormatFormattingTokensUltraVNCMslogonAuthentication.lang"),
  VNC_AUTHENTICATION_NEGOTIATOR_NETWORK(
      "administrator/vnc/VNCAuthenticationNegotiatorNetwork.lang"),
  VNC_PROCESS_CREATOR_IN_SYSTEM("administrator/io/VNCProcessCreatorInSystem.lang"),
  NO_AUTHENTICATION_CREATEPARAMETERS_TO_EXECUTABLE(
      "administrator/vnc/NoAuthenticationCreateParametersToExecutable.lang"),
  ULTRA_VNC_MSLOGON_AUTHENTICATION_CREATE_PARAMETERS_TO_EXECUTABLE(
      "administrator/vnc/UltraVNCMslogonAuthenticationCreateParametersToExecutable.lang"),
  VNC_AUTHENTICATION_CREATE_PARAMETERS_TO_EXECUTABLE(
      "administrator/vnc/VNCAuthenticationCreateParametersToExecutable.lang"),
  VNC_PROCESS_CREATOR_DEMO("administrator/io/VNCProcessCreatorDemo.lang"),
  JDIALOG_CONNECTION_VNC("administrator/gui/dialog/JDialogConnectionVNC.lang"),
  THREAD_CONNECTION_VNC_TIME_OUT_MONITOR(
      "administrator/vnc/ThreadConnectionVNCTimeOutMonitor.lang"),
  THREAD_CONNECTION_VNC_TO_GET_AUTHENTICATION_LIST(
      "administrator/vnc/ThreadConnectionVNCToGetAuthenticationList.lang"),
  COMPUTER_PERSISTENCE_H2("administrator/persistence/h2/ComputerPersistenceH2.lang");

  private String path;

  private LanguagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
