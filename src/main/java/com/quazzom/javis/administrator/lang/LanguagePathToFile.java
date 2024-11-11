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
  SWING_MAKE_LOGIN("administrator/gui/SwingMakeLogin.properties");

  private String path;

  private LanguagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
