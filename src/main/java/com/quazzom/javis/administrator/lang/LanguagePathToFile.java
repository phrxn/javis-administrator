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
  COMPUTER_PERSISTENCE_SQL_SERVER("administrator/persistence/ComputerPersistenceSQLServer.lang"),
  COMPUTER_PERSISTENCE_IN_MEMORY("administrator/persistence/ComputerPersistenceInMemory.lang"),
  SQL_SERVER_CONNECTION_INFORMATIONS(
      "administrator/configuration/SQLServerConnectionInformations.lang"),
  CONNECTION_FACTORY_SQL_SERVER("administrator/persistence/ConnectionFactorySQLServer.lang");

  private String path;

  private LanguagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
