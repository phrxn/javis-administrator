package com.quazzom.javis.administrator.vnc;

import java.io.IOException;
import java.util.List;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;
import com.quazzom.javis.administrator.rfb.RFBSession;

public class ThreadConnectionVNCToGetAuthenticationList implements Runnable {

  private MutexConnectionVNC mutexConnectionVNCMonitor;
  private RFBSession rfbSession;
  private Text theLanguage;
  private ComputerConnectionInformations computerInfos;

  public ThreadConnectionVNCToGetAuthenticationList(
      MutexConnectionVNC mutexConnectionVNCMonitor,
      ComputerConnectionInformations computerInfos) {
    this(
        mutexConnectionVNCMonitor,
        computerInfos,
        LanguageFactory.getLanguage(
            LanguagePathToFile.THREAD_CONNECTION_VNC_TO_GET_AUTHENTICATION_LIST));
  }

  public ThreadConnectionVNCToGetAuthenticationList(
      MutexConnectionVNC mutexConnectionVNCMonitor,
      ComputerConnectionInformations computerInfos,
      Text theLanguage) {
    this.mutexConnectionVNCMonitor = mutexConnectionVNCMonitor;
    this.computerInfos = computerInfos;
    this.theLanguage = theLanguage;
    rfbSession = new RFBSession(computerInfos.getHostName(), computerInfos.getPort());
  }

  public void start() {
    Thread t = new Thread(this, "ThreadConnectionVNCToGetAuthenticationList");
    t.start();
  }

  @Override
  public void run() {

    try {
      rfbSession.createConnection();
    } catch (IOException e) {
      mutexConnectionVNCMonitor.stopByError(
          JDialogType.ERROR,
          theLanguage.getText("connectionFailedTitle"),
          theLanguage.getText(
              "connectionFailedText", computerInfos.getHostName(), computerInfos.getPort()));
      return;
    }

    try {
      List<RFBAuthenticationTypes> list = rfbSession.getListOfAutentication();
      mutexConnectionVNCMonitor.setListAuthenticationAndStop(list);
    } catch (RFBProtocolException | IOException e) {
      mutexConnectionVNCMonitor.stopByError(
          JDialogType.ERROR,
          theLanguage.getText("connectionFailedTitle"),
          theLanguage.getText("listErrorText", e.getMessage()));
    }
  }

  public void stop() {
    rfbSession.closeConnection();
  }
}
