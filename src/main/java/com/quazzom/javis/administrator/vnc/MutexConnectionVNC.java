package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.SwingUtilitiesAdministrator;
import com.quazzom.javis.administrator.gui.dialog.JDialogConnectionVNC;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class MutexConnectionVNC {

  private JDialogConnectionVNC jDialogConnectionVNC;
  private Object mutex = new Object();
  private boolean isConnectionRunning = true;
  private List<RFBAuthenticationTypes> listRFBAuthentication;
  private SwingUtilitiesAdministrator sua = new SwingUtilitiesAdministrator();

  // threads flags
  private boolean isThreadVNCConnectionStopped = false;
  private boolean isThreadConnectionVNCMonitorStopped = false;

  private SwingMediator swingMediator;

  public MutexConnectionVNC(
      JDialogConnectionVNC jDialogConnectionVNC, SwingMediator swingMediator) {
    this.jDialogConnectionVNC = jDialogConnectionVNC;
    this.swingMediator = swingMediator;
    listRFBAuthentication = new ArrayList<>();
  }

  public void stopConnection() {
    synchronized (mutex) {
      if (!isConnectionRunning) {
        return;
      }
      isConnectionRunning = false;

      // necessary because this method can be called by other threads, in addition
      // to EventDispatchThread
      sua.invokeLater(
          () -> {
            jDialogConnectionVNC.dispose();
          });
    }
  }

  public boolean isConnectionRunning() {
    boolean theReturn = false;
    synchronized (mutex) {
      theReturn = isConnectionRunning;
    }
    return theReturn;
  }

  public void setListAuthenticationAndStop(List<RFBAuthenticationTypes> list) {
    synchronized (mutex) {
      if (!isConnectionRunning) {
        return;
      }
      this.listRFBAuthentication = list;
      stopConnection();
    }
  }

  public List<RFBAuthenticationTypes> getListAuthentication() {
    return listRFBAuthentication;
  }

  public void stopByError(JDialogType type, String title, String messageToShow) {
    synchronized (mutex) {
      if (!isConnectionRunning) {
        return;
      }
      swingMediator.showMessageToUser(type, title, messageToShow);
      stopConnection();
    }
  }

  public boolean allThreadsUsedInTheConnectionWereStopped() {
    return (isThreadVNCConnectionStopped && isThreadConnectionVNCMonitorStopped);
  }
}
