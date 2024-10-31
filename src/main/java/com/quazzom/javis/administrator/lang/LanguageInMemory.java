package com.quazzom.javis.administrator.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LanguageInMemory extends Language {

  private static Map<LanguagePathToFile, Properties> theLanguages;

  private static void addCopyPopup() {
    Properties propertiesCopyPopup = new Properties();
    propertiesCopyPopup.put("JMENUITEM_COPY", "Copy");
    propertiesCopyPopup.put("JMENUITEM_SELECTALL", "Select All");

    theLanguages.put(LanguagePathToFile.COPY_POPUP, propertiesCopyPopup);
  }

  private static void addJDialogUncaughtException() {

    Properties propertiesJDialogUncaughtException = new Properties();

    propertiesJDialogUncaughtException.put("JBUTTON_CLOSE", "Close");

    propertiesJDialogUncaughtException.put(
        "JLABEL_INFORMATION",
        "<html>%s has experienced an unhandled error, and may be acting inconsistently.<br>"
            + "It is advisable to restart the program to ensure that everything works as expected.<br><br>"
            + "This error may be reported to the developer.<br>"
            + "Please do the following:<br>"
            + "<i><font color=\"red\">Copy</font></i> and <i><font color=\"red\">paste</font></i> the "
            + "text below and email it to them "
            + "<font color=\"red\">with a brief explanation</font> of what you were doing before the error occurred.<br>");
    // --
    propertiesJDialogUncaughtException.put("JLABEL_TITLE_H1", "An unhandled error occurred!");
    // --
    propertiesJDialogUncaughtException.put("JLABEL_PROGRAM_NAME_INFO", "Program name:");
    propertiesJDialogUncaughtException.put("JLABEL_PROGRAM_NAME", "%s");
    // --
    propertiesJDialogUncaughtException.put("JLABEL_VERSION_INFO", "Program Version:");
    propertiesJDialogUncaughtException.put("JLABEL_VERSION", "%s");
    // --
    propertiesJDialogUncaughtException.put("JLABEL_THREAD_ID_INFO", "Thread ID:");
    propertiesJDialogUncaughtException.put("JLABEL_THREAD_ID", "%s");
    // --
    propertiesJDialogUncaughtException.put("JLABEL_THREAD_NAME_INFO", "Thread name:");
    propertiesJDialogUncaughtException.put("JLABEL_THREAD_NAME", "%s");

    theLanguages.put(
        LanguagePathToFile.JDIALOG_UNCAUGHT_EXCEPTION, propertiesJDialogUncaughtException);
  }

  private static void addOutputStreamUncaughtException() {
    Properties propertiesOutputStreamUncaughtException = new Properties();

    propertiesOutputStreamUncaughtException.put("PROGRAM", "Program: ");
    propertiesOutputStreamUncaughtException.put("VERSION", "Version: ");
    propertiesOutputStreamUncaughtException.put("THREAD_ID", "Thread ID: ");
    propertiesOutputStreamUncaughtException.put("THREAD_NAME", "Thread name: ");
    propertiesOutputStreamUncaughtException.put("STACK_TRACE", "Stack Trace: ");

    theLanguages.put(
        LanguagePathToFile.OUTPUT_STREAM_UNCAUGHT_EXCEPTION,
        propertiesOutputStreamUncaughtException);
  }

  private static void addJDialogMessages() {
    Properties propertiesJDialogMessages = new Properties();

    propertiesJDialogMessages.put("JBUTTON_CLOSE", "Close");

    theLanguages.put(LanguagePathToFile.JDIALOG_MESSAGES, propertiesJDialogMessages);
  }

  private static void addStart() {
    Properties propertiesStart = new Properties();

    propertiesStart.put(
        "ERROR_LOAD_CONFIGURATION", "TYPE_MESSAGE_ERROR\tCould not load configuration file");
    theLanguages.put(LanguagePathToFile.START, propertiesStart);
  }

  static {
    theLanguages = new HashMap<>();

    addCopyPopup();
    addJDialogUncaughtException();
    addOutputStreamUncaughtException();
    addJDialogMessages();
    addStart();
  }

  public LanguageInMemory(LanguagePathToFile languagePathToFile) {
    super(theLanguages.get(languagePathToFile));
  }
}
