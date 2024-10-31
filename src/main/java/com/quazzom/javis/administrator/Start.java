package com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.configuration.ConfigurationController;
import com.quazzom.javis.administrator.configuration.ConfigurationViewer;
import com.quazzom.javis.administrator.gui.SwingCommons;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.frame.AdministratorFrame;
import com.quazzom.javis.administrator.lang.LanguageInMemory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.uncaught_exceptions.DefaultUncaughtExceptionHandler;
import com.quazzom.javis.administrator.uncaught_exceptions.JDialogUncaughtException;
import com.quazzom.javis.administrator.uncaught_exceptions.OutputStreamUncaughtException;

public class Start implements ConfigurationViewer {

  private ConfigurationController configurationController;
  private AdministratorFrame administratorFrame;
  private SwingCommons swingCommons;
  private Text text;

  public Start() {
    administratorFrame = new AdministratorFrame();
    configurationController = new ConfigurationController(this);
    swingCommons = new SwingCommons();
    this.text = new LanguageInMemory(LanguagePathToFile.START);
  }

  public void startProgram() {
    setStartDefaultUncaughtExceptionHandler();

    // If the configuration file isn't loaded there is no way to proceed, so return...
    if (!loadConfiguration()) return;
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
