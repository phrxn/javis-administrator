package com.quazzom.javis.administrator.vnc;

import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogConnectionVNC;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class ThreadSwingJDialogConnectionVNC {

  private SwingMediator swingMediator;
  private ComputerConnectionInformations computerInfos;
  private int connectionTimoutInSeconds;

  public ThreadSwingJDialogConnectionVNC(
      SwingMediator swingMediator,
      ComputerConnectionInformations computerInfos,
      int connectionTimoutInSeconds) {
    this.swingMediator = swingMediator;
    this.computerInfos = computerInfos;
    this.connectionTimoutInSeconds = connectionTimoutInSeconds;
  }

  public List<RFBAuthenticationTypes> getListRFBAuthenticationTypes() {

    JDialogConnectionVNC jDialogConnectionVNC =
        new JDialogConnectionVNC(swingMediator.getJFrameAdministratorFrame(), true, computerInfos);
    MutexConnectionVNCMonitor mutexConnection =
        new MutexConnectionVNCMonitor(jDialogConnectionVNC, swingMediator);
    jDialogConnectionVNC.setMutexConnectionVNCMonitor(mutexConnection);

    ThreadConnectionVNC threadConnectionVNC =
        new ThreadConnectionVNC(mutexConnection, computerInfos, connectionTimoutInSeconds);
    threadConnectionVNC.start();

    if (mutexConnection.isConnectionRunning()) {
      jDialogConnectionVNC.setVisible(true);
    }

    return mutexConnection.getListAuthentication();
  }
}
