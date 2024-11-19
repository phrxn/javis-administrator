package com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.configuration.ConfigurationController;
import com.quazzom.javis.administrator.configuration.ConfigurationViewer;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingCommons;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.frame.AdministratorFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.lang.LanguageInMemory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.lang.TextKeyDoesntExist;
import com.quazzom.javis.administrator.uncaught_exceptions.CreatePermanentUnhandledExceptionHandler;
import com.quazzom.javis.administrator.uncaught_exceptions.DefaultUncaughtExceptionHandler;
import com.quazzom.javis.administrator.uncaught_exceptions.JDialogUncaughtException;
import com.quazzom.javis.administrator.uncaught_exceptions.OutputStreamUncaughtException;

public class Start implements ConfigurationViewer {

  private GeneralConfiguration generalConfiguration;
  private ConfigurationController configurationController;
  private AdministratorFrame administratorFrame;
  private SwingCommons swingCommons;
  private Text text;

  public Start() {
    administratorFrame = new AdministratorFrame();
    generalConfiguration = new GeneralConfiguration();
    configurationController = new ConfigurationController(generalConfiguration, this);
    swingCommons = new SwingCommons();
    this.text = new LanguageInMemory(LanguagePathToFile.START);
  }

  public void startProgram() {
    setStartDefaultUncaughtExceptionHandler();

    // If the configuration file isn't loaded there is no way to proceed, so return...
    if (!loadConfiguration()) return;

    configureLanguageFactory(generalConfiguration);

    if (!setNewDefaultUncaughtExceptionHandler()) return;

    administratorFrame.startGUIProgram(generalConfiguration);
  }

  private void setStartDefaultUncaughtExceptionHandler() {

    DefaultUncaughtExceptionHandler dueh =
        new DefaultUncaughtExceptionHandler(AdministratorSingleton.getInstance());

    dueh.addUncaughtExceptionListener(new JDialogUncaughtException());
    dueh.addUncaughtExceptionListener(new OutputStreamUncaughtException(System.out));

    Thread.setDefaultUncaughtExceptionHandler(dueh);
  }

  /**
   * @return <code>true</code> if the file was loaded, othewise <code>false</code> is returned.
   */
  private boolean loadConfiguration() {
    return configurationController.loadConfigurationFile();
  }

  private void configureLanguageFactory(GeneralConfiguration gc) {
    LanguageFactory.setLanguageIdiom(gc.getLanguageIdiom());
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
  }

  private boolean setNewDefaultUncaughtExceptionHandler() {
    CreatePermanentUnhandledExceptionHandler cpue =
        new CreatePermanentUnhandledExceptionHandler(generalConfiguration);

    try {
      cpue.create();
      return true;
    } catch (TextKeyDoesntExist e) {
      swingCommons.showJDialogMessage(
          administratorFrame,
          JDialogType.ERROR,
          text.getText("ERROR_SET_NEW_UNCAUGHT_EXCEPTION_HANDLER"),
          e.getMessage(),
          new LanguageInMemory(LanguagePathToFile.JDIALOG_MESSAGES));
    }
    return false;
  }

  @Override
  public void showErrorLoadingConfigurationFile(String errorMessage) {

    swingCommons.showJDialogMessage(
        administratorFrame,
        JDialogType.ERROR,
        text.getText("ERROR_LOAD_CONFIGURATION"),
        errorMessage,
        new LanguageInMemory(LanguagePathToFile.JDIALOG_MESSAGES));
  }
}
