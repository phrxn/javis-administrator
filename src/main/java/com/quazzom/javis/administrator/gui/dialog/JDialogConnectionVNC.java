package com.quazzom.javis.administrator.gui.dialog;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformations;
import com.quazzom.javis.administrator.vnc.MutexConnectionVNC;

public class JDialogConnectionVNC extends JDialogDefault {

  private MutexConnectionVNC mutexConnectionVNCMonitor;

  private ComputerConnectionInformations computerInfos;

  private Text theLanguage;

  public JDialogConnectionVNC(
      JFrame jFrameParent, boolean isModal, ComputerConnectionInformations computerInfos) {
    this(
        jFrameParent,
        isModal,
        computerInfos,
        LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_CONNECTION_VNC));
  }

  public JDialogConnectionVNC(
      JFrame jFrameParent,
      boolean isModal,
      ComputerConnectionInformations computerInfos,
      Text theLanguage) {
    super(jFrameParent, isModal);
    this.computerInfos = computerInfos;
    this.theLanguage = theLanguage;
    startGUI();
  }

  private void startGUI() {

    JPanel jPanelRoot = new JPanel();
    jPanelRoot.setBorder(
        new CompoundBorder(
            new EmptyBorder(5, 5, 5, 5),
            new TitledBorder(
                new LineBorder(new Color(0, 0, 0)),
                theLanguage.getText("jPanelRootTitledBorder"),
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(0, 0, 0))));
    jPanelRoot.setLayout(null);

    JProgressBar progressBar = new JProgressBar();
    progressBar.setBounds(24, 55, 332, 21);
    progressBar.setIndeterminate(true);
    jPanelRoot.add(progressBar);

    JButton jButtonCancel = new JButton();
    jButtonCancel.setText(theLanguage.getText("jButtonCancel"));

    jButtonCancel.addActionListener(
        (e) -> {
          mutexConnectionVNCMonitor.stopConnection();
        });
    jButtonCancel.setBounds(109, 85, 160, 23);
    jPanelRoot.add(jButtonCancel);

    setSize(400, 160);
    setContentPane(jPanelRoot);

    JLabel jLabelTheComputer =
        new JLabel(theLanguage.getText("jLabelTheComputer", computerInfos.getHostName()));
    jLabelTheComputer.setBounds(24, 23, 332, 21);
    jPanelRoot.add(jLabelTheComputer);

    addWindowListener(new WindowsEvents());
  }

  public void setMutexConnectionVNCMonitor(MutexConnectionVNC mutexConnectionVNCMonitor) {
    this.mutexConnectionVNCMonitor = mutexConnectionVNCMonitor;
  }

  private class WindowsEvents extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
      mutexConnectionVNCMonitor.stopConnection();
    }
  }
}
