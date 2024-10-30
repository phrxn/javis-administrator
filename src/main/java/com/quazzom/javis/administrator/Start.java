package com.quazzom.javis.administrator;

import com.quazzom.javis.administrator.uncaught_exceptions.DefaultUncaughtExceptionHandler;
import com.quazzom.javis.administrator.uncaught_exceptions.JDialogUncaughtException;
import com.quazzom.javis.administrator.uncaught_exceptions.OutputStreamUncaughtException;

public class Start {

  public Start() {}

  public void startProgram() {
    setStartDefaultUncaughtExceptionHandler();
  }

  private void setStartDefaultUncaughtExceptionHandler() {

    DefaultUncaughtExceptionHandler dueh =
        new DefaultUncaughtExceptionHandler(AdministratorSingleton.getInstance());

    dueh.addUncaughtExceptionListener(new JDialogUncaughtException());
    dueh.addUncaughtExceptionListener(new OutputStreamUncaughtException(System.out));

    Thread.setDefaultUncaughtExceptionHandler(dueh);
  }
}
