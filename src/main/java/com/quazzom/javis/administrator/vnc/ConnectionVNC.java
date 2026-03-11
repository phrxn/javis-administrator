package com.quazzom.javis.administrator.vnc;

public class ConnectionVNC {

  private ThreadConnectionVNCTimeOutMonitor threadConnectionVNCTimeOutMonitor;

  public ConnectionVNC(
      MutexConnectionVNC mutexConnectionVNCMonitor,
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
