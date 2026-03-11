package com.quazzom.javis.administrator.uncaught_exceptions;

import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.lang.CopyPopupLanguage;
import com.quazzom.javis.administrator.lang.JDialogUncaughtExceptionLanguage;
import com.quazzom.javis.administrator.lang.Language;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.OutputStreamUncaughtExceptionLanguage;
import com.quazzom.javis.administrator.lang.TextKeyDoesntExist;

public class CreatePermanentUnhandledExceptionHandler {

  private GeneralConfiguration generalConfiguration;
  private Language languageToJDialog;
  private Language languageToOutput;
  private Language languageToCopyPopup;

  public CreatePermanentUnhandledExceptionHandler(GeneralConfiguration generalConfiguration) {
    this.generalConfiguration = generalConfiguration;
  }

  public void create() throws TextKeyDoesntExist {

    languageToJDialog =
        new JDialogUncaughtExceptionLanguage(
            generalConfiguration.getLanguageIdiom(), LanguagePathToFile.JDIALOG_UNCAUGHT_EXCEPTION);
    languageToOutput =
        new OutputStreamUncaughtExceptionLanguage(
            generalConfiguration.getLanguageIdiom(),
            LanguagePathToFile.OUTPUT_STREAM_UNCAUGHT_EXCEPTION);

    languageToCopyPopup =
        new CopyPopupLanguage(
            generalConfiguration.getLanguageIdiom(), LanguagePathToFile.COPY_POPUP);

    DefaultUncaughtExceptionHandler dueh =
        new DefaultUncaughtExceptionHandler(AdministratorSingleton.getInstance());

    dueh.addUncaughtExceptionListener(
        new JDialogUncaughtException(languageToJDialog, languageToCopyPopup));
    dueh.addUncaughtExceptionListener(
        new OutputStreamUncaughtException(System.out, languageToOutput));

    Thread.setDefaultUncaughtExceptionHandler(dueh);
  }
}
