package com.quazzom.javis.administrator.vnc;

import java.io.IOException;
import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;
import com.quazzom.javis.administrator.rfb.RFBProtocolException;
import com.quazzom.javis.administrator.rfb.RFBSession;

public class ThreadVNCConnection implements Runnable {

  private MutexConnectionVNCMonitor mutexConnectionVNCMonitor;
  private SwingMediator swingMediator;
  private RFBSession rfbSession;
  private Text theLanguage;
  private ComputerConnectionInformations computerInfos;

  public ThreadVNCConnection(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      SwingMediator swingMediator,
      ComputerConnectionInformations computerInfos) {
    this(
        mutexConnectionVNCMonitor,
        swingMediator,
        computerInfos,
        LanguageFactory.getLanguage(LanguagePathToFile.THREAD_VNC_CONNECTION));
  }

  public ThreadVNCConnection(
      MutexConnectionVNCMonitor mutexConnectionVNCMonitor,
      SwingMediator swingMediator,
      ComputerConnectionInformations computerInfos,
      Text theLanguage) {
    this.mutexConnectionVNCMonitor = mutexConnectionVNCMonitor;
    this.swingMediator = swingMediator;
    this.computerInfos = computerInfos;
    this.theLanguage = theLanguage;
    rfbSession = new RFBSession(computerInfos.getHostName(), computerInfos.getPort());
  }

  public void start() {
    Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {

    try {
      rfbSession.createConnection();
    } catch (IOException e) {
      if (!mutexConnectionVNCMonitor.isToContinueTheConnectionMonitor()) {
        return;
      }
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("connectionFailedTitle"),
          theLanguage.getText(
              "connectionFailedText", computerInfos.getHostName(), computerInfos.getPort()));
      mutexConnectionVNCMonitor.stopConnection();
      return;
    }

    try {
      List<RFBAuthenticationTypes> list = rfbSession.getListOfAutentication();
      mutexConnectionVNCMonitor.setListAuthenticationAndStop(list);
      return;
    } catch (RFBProtocolException | IOException e) {
      if (!mutexConnectionVNCMonitor.isToContinueTheConnectionMonitor()) {
        return;
      }
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("connectionFailedTitle"),
          theLanguage.getText("listErrorText", e.getMessage()));
    }
    mutexConnectionVNCMonitor.stopConnection();
  }

  public void stop() {
    rfbSession.closeConnection();
  }
}
