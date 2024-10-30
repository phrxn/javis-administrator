package com.quazzom.javis.administrator.lang;

public enum LanguagePathToFile {
  COPY_POPUP("administrator/gui/popup/CopyPopup.properties"),
  JDIALOG_UNCAUGHT_EXCEPTION(
      "administrator/uncaught_exceptions/JDialogUncaughtException.properties"),
  OUTPUT_STREAM_UNCAUGHT_EXCEPTION(
      "administrator/uncaught_exceptions/OutputStreamUncaughtException.properties");

  private String path;

  private LanguagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
