package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import com.quazzom.javis.administrator.gui.dialog.JDialogConnectionVNC;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class MutexConnectionVNCMonitor {

  private JDialogConnectionVNC jDialogConnectionVNC;
  private Object mutex = new Object();
  private boolean isToContinueTheConnectionMonitor = true;
  private List<RFBAuthenticationTypes> listRFBAuthentication;

  public MutexConnectionVNCMonitor(JDialogConnectionVNC jDialogConnectionVNC) {
    this.jDialogConnectionVNC = jDialogConnectionVNC;
    listRFBAuthentication = new ArrayList<>();
  }

  public void stopConnection() {
    synchronized (mutex) {

      // If the connection is already stopped, there is no reason to close the dialog again...
      if (!isToContinueTheConnectionMonitor) {
        return;
      }
      jDialogConnectionVNC.dispose();
      isToContinueTheConnectionMonitor = false;
    }
  }

  public boolean isToContinueTheConnectionMonitor() {
    return isToContinueTheConnectionMonitor;
  }

  public void setListAuthenticationAndStop(List<RFBAuthenticationTypes> list) {
    synchronized (mutex) {
      this.listRFBAuthentication = list;
      jDialogConnectionVNC.dispose();
      isToContinueTheConnectionMonitor = false;
    }
  }

  public List<RFBAuthenticationTypes> getListAuthentication() {
    return listRFBAuthentication;
  }
}
