package com.quazzom.javis.administrator.lang;

public enum LanguagePathToFile {
  COPY_POPUP("administrator/gui/popup/CopyPopup.properties"),
  JDIALOG_UNCAUGHT_EXCEPTION(
      "administrator/uncaught_exceptions/JDialogUncaughtException.properties"),
  OUTPUT_STREAM_UNCAUGHT_EXCEPTION(
      "administrator/uncaught_exceptions/OutputStreamUncaughtException.properties"),
  JDIALOG_MESSAGES("administrator/gui/dialog/JDialogMessages.properties"),
  JDIALOG_QUESTIONS("administrator/gui/dialog/JDialogQuestions.properties"),
  START("administrator/Start.properties"),
  AUTHENTICATION_DEMO("administrator/authentication/AuthenticationDemo.properties"),
  LOGIN_INTERNAL_FRAME("administrator/gui/internal_frame/LoginInternalFrame.properties"),
  SWING_EXIT_PROGRAM("administrator/gui/SwingExitProgram.properties"),
  SWING_MAKE_LOGIN("administrator/gui/SwingMakeLogin.properties"),
  JPANEL_MAIN_INTERNAL_FRAME_VNC("administrator/gui/panel/JPanelMainInternalFrameVNC.properties"),
  COMPUTER("administrator/vnc/Computer.properties"),
  VNC_COMPUTER_LIST_INTERNAL_FRAME(
      "administrator/gui/internal_frame/VNCComputerListInternalFrame.properties"),
  JTABLE_COMPUTERS("administrator/gui/table/JTableComputers.properties"),
  IP("administrator/vnc/IP.properties"),
  VNC_COMPUTER_LIST_INTERNAL_FRAME_CONTROLLER(
      "administrator/gui/controllers/VNCComputerListInternalFrameController.properties"),
  JDIALOG_COMPUTER("administrator/gui/dialog/JDialogComputer.properties"),
  JDIALOG_INTERACTION("administrator/gui/dialog/JDialogInteraction.properties"),
  COMPUTER_PERSISTENCE_SQL_SERVER(
      "administrator/persistence/ComputerPersistenceSQLServer.properties"),
  COMPUTER_PERSISTENCE_IN_MEMORY(
      "administrator/persistence/ComputerPersistenceInMemory.properties"),
  SQL_SERVER_CONNECTION_INFORMATIONS(
      "administrator/configuration/SQLServerConnectionInformations.properties"),
  CONNECTION_FACTORY_SQL_SERVER("administrator/persistence/ConnectionFactorySQLServer.properties");

  private String path;

  private LanguagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
