package com.quazzom.javis.administrator.vnc;

import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogConnectionVNC;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class ConnectionVNCToGetAuthenticationList {

  private SwingMediator swingMediator;
  private ComputerConnectionInformations computerInfos;
  private int connectionTimoutInSeconds;

  public ConnectionVNCToGetAuthenticationList(
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
    MutexConnectionVNC mutexConnection =
        new MutexConnectionVNC(jDialogConnectionVNC, swingMediator);
    jDialogConnectionVNC.setMutexConnectionVNCMonitor(mutexConnection);

    ConnectionVNC threadConnectionVNC =
        new ConnectionVNC(mutexConnection, computerInfos, connectionTimoutInSeconds);
    threadConnectionVNC.start();

    if (mutexConnection.isConnectionRunning()) {
      jDialogConnectionVNC.setVisible(true);
    }

    return mutexConnection.getListAuthentication();
  }
}
