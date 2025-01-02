package com.quazzom.javis.administrator.vnc;

import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ThreadConnectionVNCMonitor implements Runnable {

  private MutexConnectionVNCMonitor mutexConnectionVNCMonitor;
  private SwingMediator swingMediator;
  private ComputerConnectionInformations computerInfos;
  private int connectionTimoutInSeconds;
  private long startTime;

  private ThreadVNCConnection threadVNCConnection;

  private Text theLanguage;

  public ThreadConnectionVNCMonitor(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      SwingMediator swingMediator,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds) {
    this(
        mutexConnectionVNCMonitor,
        swingMediator,
        computerInfos,
        connectionTimoutInSeconds,
        LanguageFactory.getLanguage(LanguagePathToFile.THREAD_CONNECTION_VNC_MONITOR));
  }

  public ThreadConnectionVNCMonitor(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      SwingMediator swingMediator,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds,
      Text theLanguage) {
    this.mutexConnectionVNCMonitor = mutexConnectionVNCMonitor;
    this.swingMediator = swingMediator;
    this.computerInfos = computerInfos;
    this.connectionTimoutInSeconds = connectionTimoutInSeconds;
    this.startTime = System.currentTimeMillis();
    this.theLanguage = theLanguage;
  }

  public void start() {
    threadVNCConnection =
        new ThreadVNCConnection(mutexConnectionVNCMonitor, swingMediator, computerInfos);
    threadVNCConnection.start();
    Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    while (isAValidTimeout() && mutexConnectionVNCMonitor.isToContinueTheConnectionMonitor()) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    mutexConnectionVNCMonitor.stopConnection();
    threadVNCConnection.stop();
  }

  public boolean isAValidTimeout() {

    long elapsedTime = System.currentTimeMillis() - startTime;

    if ((elapsedTime / 1000) < connectionTimoutInSeconds) {
      return true;
    }

    swingMediator.showMessageToUser(
        JDialogType.ERROR,
        theLanguage.getText("timeoutTitle"),
        theLanguage.getText("timeoutMessage"));
    return false;
  }
}
