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

    List<RFBAuthenticationTypes> listRFBAuthenticationTypes = null;

    JDialogConnectionVNC jDialogConnectionVNC =
        new JDialogConnectionVNC(swingMediator.getJFrameAdministratorFrame(), true, computerInfos);
    MutexConnectionVNCMonitor mutexConnection = new MutexConnectionVNCMonitor(jDialogConnectionVNC);
    jDialogConnectionVNC.setMutexConnectionVNCMonitor(mutexConnection);

    ThreadConnectionVNCMonitor threadConnectionVNCMonitor =
        new ThreadConnectionVNCMonitor(
            mutexConnection, swingMediator, computerInfos, connectionTimoutInSeconds);
    threadConnectionVNCMonitor.start();

    jDialogConnectionVNC.setVisible(true);

    listRFBAuthenticationTypes = mutexConnection.getListAuthentication();

    return listRFBAuthenticationTypes;
  }
}
