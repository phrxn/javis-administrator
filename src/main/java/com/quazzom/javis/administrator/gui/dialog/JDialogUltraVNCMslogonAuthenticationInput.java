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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.UltraVNCMslogonAuthentication;

public class JDialogUltraVNCMslogonAuthenticationInput extends JDialogDefault {

  private SwingMediator swingMediator;
  private UltraVNCMslogonAuthentication ultraVNCMslogon;
  private Text theLanguage;
  private JDialogYesCancelOption optionToReturn = JDialogYesCancelOption.CANCEL;

  private JButton jButtonOk;
  private JButton jButtonCancel;
  private JTextField jTextFieldUsername;
  private JPasswordField jPasswordField;
  private JCheckBox jCheckBoxSaveCredentials;

  /**
   * @wbp.parser.constructor
   */
  public JDialogUltraVNCMslogonAuthenticationInput(
      SwingMediator swingMediator, UltraVNCMslogonAuthentication ultraVNCMslogon) {
    this(
        swingMediator,
        ultraVNCMslogon,
        LanguageFactory.getLanguage(
            LanguagePathToFile.JDIALOG_ULTRA_VNC_MS_LOGON_AUTHENTICATION_INPUT));
  }

  public JDialogUltraVNCMslogonAuthenticationInput(
      SwingMediator swingMediator,
      UltraVNCMslogonAuthentication ultraVNCMslogon,
      Text theLanguage) {
    super(swingMediator.getJFrameAdministratorFrame(), true);
    this.swingMediator = swingMediator;
    this.ultraVNCMslogon = ultraVNCMslogon;
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

    JLabel jLabelUsername = new JLabel(String.format("%s:", theLanguage.getText("jLabelUsername")));
    jLabelUsername.setBounds(10, 20, 112, 30);
    jLabelUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
    jPanelMainOnCenter.add(jLabelUsername);

    jTextFieldUsername = new JTextField();
    jTextFieldUsername.addActionListener(inputEvent);
    jTextFieldUsername.setBounds(127, 20, 261, 30);
    jTextFieldUsername.setText(ultraVNCMslogon.getUsername());
    jTextFieldUsername.setColumns(10);
    jPanelMainOnCenter.add(jTextFieldUsername);

    JLabel jLabelPassword = new JLabel(String.format("%s:", theLanguage.getText("jLabelPassword")));
    jLabelPassword.setBounds(10, 55, 112, 30);
    jLabelPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
    jPanelMainOnCenter.add(jLabelPassword);

    jPasswordField = new JPasswordField();
    jPasswordField.setBounds(127, 55, 261, 30);
    jPasswordField.addActionListener(inputEvent);
    jPasswordField.setText(ultraVNCMslogon.getPassword());
    jPanelMainOnCenter.add(jPasswordField);

    jCheckBoxSaveCredentials = new JCheckBox();
    jCheckBoxSaveCredentials.setBounds(10, 90, 378, 23);
    jCheckBoxSaveCredentials.setSelected(ultraVNCMslogon.isToSalveCredential());
    jCheckBoxSaveCredentials.setText(theLanguage.getText("jCheckBoxSaveCredentials"));

    jPanelMainOnCenter.add(jCheckBoxSaveCredentials);

    setContentPane(jPanelRoot);
    setSize(432, 210);
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
      if (e.getSource().equals(jButtonOk)
          || e.getSource().equals(jTextFieldUsername)
          || e.getSource().equals(jPasswordField)) {
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
      ultraVNCMslogon.setUsername(jTextFieldUsername.getText());
      ultraVNCMslogon.setPassword(new String(jPasswordField.getPassword()));
      ultraVNCMslogon.setIsToSalveCredential(jCheckBoxSaveCredentials.isSelected());
      optionToReturn = JDialogYesCancelOption.YES;
      dispose();
    }

    private void buttonCancel() {
      dispose();
    }
  }
}
