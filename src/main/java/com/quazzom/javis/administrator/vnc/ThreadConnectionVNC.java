package com.quazzom.javis.administrator.vnc;

public class ThreadConnectionVNC {

  private ThreadConnectionVNCTimeOutMonitor threadConnectionVNCTimeOutMonitor;

  public ThreadConnectionVNC(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds) {

    threadConnectionVNCTimeOutMonitor =
        new ThreadConnectionVNCTimeOutMonitor(
            mutexConnectionVNCMonitor, computerInfos, connectionTimoutInSeconds);
  }

  public void start() {
    threadConnectionVNCTimeOutMonitor.start();
  }
}
