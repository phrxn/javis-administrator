package com.quazzom.javis.administrator.vnc;

import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ThreadConnectionVNCTimeOutMonitor implements Runnable {

  private MutexConnectionVNCMonitor mutexConnectionVNCMonitor;
  private ComputerConnectionInformations computerInfos;
  private int connectionTimoutInSeconds;
  private long startTime;

  private ThreadConnectionVNCToGetAuthenticationList threadVNCConnection;

  private Text theLanguage;

  public ThreadConnectionVNCTimeOutMonitor(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds) {
    this(
        mutexConnectionVNCMonitor,
        computerInfos,
        connectionTimoutInSeconds,
        LanguageFactory.getLanguage(LanguagePathToFile.THREAD_CONNECTION_VNC_TIME_OUT_MONITOR));
  }

  public ThreadConnectionVNCTimeOutMonitor(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds,
      Text theLanguage) {
    this.mutexConnectionVNCMonitor = mutexConnectionVNCMonitor;
    this.computerInfos = computerInfos;
    this.connectionTimoutInSeconds = connectionTimoutInSeconds;
    this.startTime = System.currentTimeMillis();
    this.theLanguage = theLanguage;
  }

  public void start() {
    threadVNCConnection = new ThreadConnectionVNCToGetAuthenticationList(mutexConnectionVNCMonitor, computerInfos);
    threadVNCConnection.start();
    Thread t = new Thread(this, "ThreadConnectionVNCMonitor");
    t.start();
  }

  @Override
  public void run() {
    while (true) {
      if (!mutexConnectionVNCMonitor.isConnectionRunning() || !isAValidTimeout()) {
        break;
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        stopMySelf();
        throw new RuntimeException(e);
      }
    }
    stopMySelf();
  }

  public boolean isAValidTimeout() {

    long elapsedTime = System.currentTimeMillis() - startTime;

    if ((elapsedTime / 1000) < connectionTimoutInSeconds) {
      return true;
    }

    mutexConnectionVNCMonitor.stopByError(
        JDialogType.ERROR,
        theLanguage.getText("timeoutTitle"),
        theLanguage.getText("timeoutMessage"));

    return false;
  }

  private void stopMySelf() {
    mutexConnectionVNCMonitor.stopConnection();
    threadVNCConnection.stop();
  }
}
