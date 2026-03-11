package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.VNCAuthentication;

public class JDialogVNCAuthenticationInput extends JDialogDefault {

  private SwingMediator swingMediator;
  private VNCAuthentication vncAuthentication;
  private Text theLanguage;
  private JDialogYesCancelOption optionToReturn = JDialogYesCancelOption.CANCEL;

  private JButton jButtonOk;
  private JButton jButtonCancel;
  private JPasswordField jPasswordField;
  private JCheckBox jCheckBoxSaveCredentials;

  /**
   * @wbp.parser.constructor
   */
  public JDialogVNCAuthenticationInput(
      SwingMediator swingMediator, VNCAuthentication vncAuthentication) {
    this(
        swingMediator,
        vncAuthentication,
        LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_VNC_AUTHENTICATION_INPUT));
  }

  public JDialogVNCAuthenticationInput(
      SwingMediator swingMediator, VNCAuthentication vncAuthentication, Text theLanguage) {
    super(swingMediator.getJFrameAdministratorFrame(), true);
    this.swingMediator = swingMediator;
    this.vncAuthentication = vncAuthentication;
    this.theLanguage = theLanguage;
    startGUI();
  }

  void startGUI() {

    ButtonsEvent inputEvent = new ButtonsEvent();

    JPanel jPanelRoot = new JPanel();
    jPanelRoot.setBorder(new EmptyBorder(10, 10, 5, 10));
    jPanelRoot.setLayout(new BorderLayout());

    JPanel jPanelButtons = new JPanel();
    FlowLayout flowLayout = (FlowLayout) jPanelButtons.getLayout();
    flowLayout.setAlignment(FlowLayout.RIGHT);
    jPanelRoot.add(jPanelButtons, BorderLayout.SOUTH);

    jButtonOk = new JButton(theLanguage.getText("JBUTTON_OK"));
    jButtonOk.addActionListener(inputEvent);
    jButtonOk.addKeyListener(inputEvent);
    jPanelButtons.add(jButtonOk);

    jButtonCancel = new JButton(theLanguage.getText("JBUTTON_CANCEL"));
    jButtonCancel.addActionListener(inputEvent);
    jButtonCancel.addKeyListener(inputEvent);
    jPanelButtons.add(jButtonCancel);

    JPanel jPanelMainOnCenter = new JPanel();
    jPanelMainOnCenter.setBorder(
        new TitledBorder(
            new EtchedBorder(
                EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
            theLanguage.getText("jPanelMainOnCenterTitleBorder"),
            TitledBorder.LEADING,
            TitledBorder.TOP,
            null,
            Color.BLACK));
    jPanelRoot.add(jPanelMainOnCenter, BorderLayout.CENTER);
    jPanelMainOnCenter.setLayout(null);

    JLabel jLabelPassword = new JLabel(String.format("%s:", theLanguage.getText("jLabelPassword")));
    jLabelPassword.setBounds(10, 20, 112, 30);
    jLabelPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
    jPanelMainOnCenter.add(jLabelPassword);

    jPasswordField = new JPasswordField();
    jPasswordField.setBounds(127, 20, 261, 30);
    jPasswordField.addActionListener(inputEvent);
    jPasswordField.setText(vncAuthentication.getPassword());
    jPanelMainOnCenter.add(jPasswordField);

    jCheckBoxSaveCredentials = new JCheckBox();
    jCheckBoxSaveCredentials.setText(theLanguage.getText("jCheckBoxSaveCredentials"));
    jCheckBoxSaveCredentials.setBounds(10, 55, 378, 23);
    jCheckBoxSaveCredentials.setSelected(vncAuthentication.isToSalveCredential());
    jPanelMainOnCenter.add(jCheckBoxSaveCredentials);

    setContentPane(jPanelRoot);
    setSize(432, 178);
  }

  public JDialogYesCancelOption createJDialog() {

    startGUI();
    setLocationRelativeTo(swingMediator.getJFrameAdministratorFrame());
    setVisible(true);

    return optionToReturn;
  }

  private class ButtonsEvent extends KeyAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonOk) || e.getSource().equals(jPasswordField)) {
        buttonOk();
      } else if (e.getSource().equals(jButtonCancel)) {
        buttonCancel();
      }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (e.getSource().equals(jButtonOk)) {
          buttonOk();
        } else if (e.getSource().equals(jButtonCancel)) {
          buttonCancel();
        }
      }
    }

    private void buttonOk() {
      vncAuthentication.setPassword(new String(jPasswordField.getPassword()));
      vncAuthentication.setToSalveCredential(jCheckBoxSaveCredentials.isSelected());
      optionToReturn = JDialogYesCancelOption.YES;
      dispose();
    }

    private void buttonCancel() {
      dispose();
    }
  }
}
