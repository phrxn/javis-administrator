package com.quazzom.javis.administrator.gui.internal_frame;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class LoginInternalFrame extends RootInternalFrame {

  private static final long serialVersionUID = 1L;

  private SwingMediator swingMediator;

  private Text language;

  // -------------------- gui components --------------------
  private JButton jButtonLogin;
  private JButton jButtonCancel;

  private JLabel jLabelUsername;
  private JLabel jLabelUserPassword;
  private JLabel jLabelVersion;

  private JPanel jPanelRoot;

  private JPasswordField jPasswordFieldUserPassword;

  private JTextField jTextFiedUsername;

  private ButtonsControl buttonsControl;

  public LoginInternalFrame(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {

    super(
        "", // title
        false, // resizable
        false, // closable
        false, // maximizable
        false, // iconifiable
        swingMediator);

    this.swingMediator = swingMediator;
    this.language = LanguageFactory.getLanguage(LanguagePathToFile.LOGIN_INTERNAL_FRAME);
    this.buttonsControl = new ButtonsControl();

    jPanelRoot = new JPanel();
    jPanelRoot.setLayout(null);
    setContentPane(jPanelRoot);

    jLabelUsername = new JLabel();
    jLabelUsername.setBounds(21, 35, 89, 30);
    jPanelRoot.add(jLabelUsername);

    jLabelUserPassword = new JLabel();
    jLabelUserPassword.setBounds(21, 73, 89, 30);
    jPanelRoot.add(jLabelUserPassword);

    jTextFiedUsername = new JTextField();
    jTextFiedUsername.setBounds(120, 35, 226, 30);
    jTextFiedUsername.setColumns(10);
    jPanelRoot.add(jTextFiedUsername);

    jPasswordFieldUserPassword = new JPasswordField();
    jPasswordFieldUserPassword.setBounds(120, 73, 226, 30);
    jPanelRoot.add(jPasswordFieldUserPassword);

    jButtonLogin = new JButton();
    jButtonLogin.setBounds(120, 111, 89, 35);
    jButtonLogin.addActionListener(buttonsControl);
    jPanelRoot.add(jButtonLogin);

    jButtonCancel = new JButton();
    jButtonCancel.addActionListener(buttonsControl);
    jButtonCancel.setBounds(254, 111, 89, 35);
    jPanelRoot.add(jButtonCancel);

    jLabelVersion = new JLabel();
    jLabelVersion.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelVersion.setBounds(215, 0, 159, 14);
    jPanelRoot.add(jLabelVersion);

    setTitle(language.getText("TITLE"));
    setSize(390, 233);
    putClientProperty("dragMode", "fixed");

    addEventEnter();
    setLanguage();
  }

  private void addEventEnter() {
    getRootPane()
        .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("ENTER"), "ClickEnter");
    getRootPane().getActionMap().put("ClickEnter", buttonsControl);
  }

  private void setLanguage() {

    jButtonLogin.setText(language.getText("JBUTTON_LOGIN"));
    jButtonCancel.setText(language.getText("JBUTTON_CANCEL"));

    jLabelUsername.setText(language.getText("JLABEL_USERNAME"));
    jLabelUserPassword.setText(language.getText("JLABEL_USER_PASSWORD"));
    jLabelVersion.setText(
        language.getText(
            "JLABEL_VERSION", AdministratorSingleton.getInstance().getProgramVersion()));
  }

  public void setFocusOnJTextFiedUsername() {
    jTextFiedUsername.requestFocusInWindow();
  }

  private class ButtonsControl extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonCancel) || jButtonCancel.isFocusOwner()) {
        swingMediator.showExitProgram();
        return;
      }
      String userName = jTextFiedUsername.getText();
      String userPassword = new String(jPasswordFieldUserPassword.getPassword());
      swingMediator.makeLogin(userName, userPassword);
    }
  }
}
