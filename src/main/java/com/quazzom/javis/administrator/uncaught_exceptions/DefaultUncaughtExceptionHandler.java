package com.quazzom.javis.administrator.uncaught_exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import com.quazzom.javis.administrator.Administrator;

public class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

  List<UncaughtExceptionListener> listUncaughtExceptionListeners;
  private Administrator administrator;

  public DefaultUncaughtExceptionHandler(Administrator administrator) {
    this.administrator = administrator;
    listUncaughtExceptionListeners = new ArrayList<UncaughtExceptionListener>();
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {

    InformationAboutUncaughtException iaue = createInformation(t, e);

    for (UncaughtExceptionListener uncaughtExceptionListener : listUncaughtExceptionListeners) {
      uncaughtExceptionListener.showUncaughtException(iaue);
    }
  }

  public void addUncaughtExceptionListener(UncaughtExceptionListener uel) {
    listUncaughtExceptionListeners.add(uel);
  }

  InformationAboutUncaughtException createInformation(Thread t, Throwable e) {

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);

    return new InformationAboutUncaughtException(
        administrator.getProgramName(),
        administrator.getProgramVersion(),
        t.getName(),
        String.valueOf(t.threadId()),
        sw.toString());
  }
}
