package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.Computer.PowerStatus;
import com.quazzom.javis.administrator.vnc.Computer.TypeOfLoginSession;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IPException;

public class JDialogComputer extends JDialogDefault {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel();

  public enum TypeOfAction {
    CREATE_COMPUTER,
    UPDATE_COMPUTER;
  }

  private Computer computer;

  private JButton jButtonOk;
  private JButton jButtonCancel;
  private JComboBox<JComboBoxPowerStatusItem> jComboBoxPowerStatus;
  private JComboBox<JComboBoxSessionTypeItem> jComboBoxSessionType;
  private JPanel jPanelRoot;
  private JTextField jTextFieldJavisClientVersion;
  private JTextField jTextFieldUsername;
  private JTextField jTextFieldHostname;
  private JTextField jTextFieldIP;
  private JTextArea jTextAreaDetails;

  private SwingMediator swingMediator;

  private Text theLanguageToComputer;
  private Text theLanguageJDialog;

  private TypeOfAction typeOfAction;

  private VNCComputerListInternalFrameController vncComputerListInternalFrameController;

  public JDialogComputer(
      JFrame jFrameParent,
      SwingMediator swingMediator,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController) {
    this(
        null,
        TypeOfAction.CREATE_COMPUTER,
        jFrameParent,
        swingMediator,
        vncComputerListInternalFrameController,
        LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER),
        LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_COMPUTER));
  }

  public JDialogComputer(
      Computer computerToUpdate,
      JFrame jFrameParent,
      SwingMediator swingMediator,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController) {
    this(
        computerToUpdate,
        TypeOfAction.UPDATE_COMPUTER,
        jFrameParent,
        swingMediator,
        vncComputerListInternalFrameController,
        LanguageFactory.getLanguage(LanguagePathToFile.COMPUTER),
        LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_COMPUTER));
  }

  public JDialogComputer(
      JFrame jFrameParent,
      SwingMediator swingMediator,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController,
      Text theLanguageToComputer,
      Text theLanguageJDialog) {
    this(
        null,
        TypeOfAction.CREATE_COMPUTER,
        jFrameParent,
        swingMediator,
        vncComputerListInternalFrameController,
        theLanguageToComputer,
        theLanguageJDialog);
  }

  public JDialogComputer(
      Computer computerToUpdate,
      JFrame jFrameParent,
      SwingMediator swingMediator,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController,
      Text theLanguageToComputer,
      Text theLanguageJDialog) {

    this(
        computerToUpdate,
        TypeOfAction.UPDATE_COMPUTER,
        jFrameParent,
        swingMediator,
        vncComputerListInternalFrameController,
        theLanguageToComputer,
        theLanguageJDialog);
  }

  private JDialogComputer(
      Computer computer,
      TypeOfAction typeOfAction,
      JFrame jFrameParent,
      SwingMediator swingMediator,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController,
      Text theLanguageToComputer,
      Text theLanguageJDialog) {
    super(jFrameParent, true);

    this.computer = computer;
    this.typeOfAction = typeOfAction;
    this.swingMediator = swingMediator;
    this.vncComputerListInternalFrameController = vncComputerListInternalFrameController;
    this.theLanguageToComputer = theLanguageToComputer;
    this.theLanguageJDialog = theLanguageJDialog;

    if (typeOfAction == TypeOfAction.CREATE_COMPUTER) {
      try {
        this.computer = new Computer();
      } catch (ComputerException | IPException e) {
        throw new RuntimeException(e);
      }
    }

    startGUI(jFrameParent);
    fillTheComputerFields();
    setVisible(true);
  }

  private void startGUI(JFrame jFrameParent) {

    ButtonsControl buttonsControl = new ButtonsControl();

    jPanelRoot = new JPanel();
    jPanelRoot.setLayout(new BorderLayout());

    contentPanel.setLayout(null);
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

    JLabel jLabelPowerStatus = new JLabel(theLanguageJDialog.getText("STATUS"));
    jLabelPowerStatus.setBounds(5, 6, 215, 31);
    contentPanel.add(jLabelPowerStatus);

    jComboBoxPowerStatus = new JComboBox<JComboBoxPowerStatusItem>();
    for (Computer.PowerStatus powerStatus : Computer.PowerStatus.values()) {
      String powerStatusText = theLanguageToComputer.getText(powerStatus.getLanguagueID());
      JComboBoxPowerStatusItem jcbpwi = new JComboBoxPowerStatusItem(powerStatus, powerStatusText);
      jComboBoxPowerStatus.addItem(jcbpwi);
    }

    jComboBoxPowerStatus.setBounds(220, 6, 205, 31);
    contentPanel.add(jComboBoxPowerStatus);

    JLabel jLabelTypeOfLoginSession = new JLabel(theLanguageJDialog.getText("TYPE_OF_SESSION"));
    jLabelTypeOfLoginSession.setBounds(5, 42, 215, 31);
    contentPanel.add(jLabelTypeOfLoginSession);

    jComboBoxSessionType = new JComboBox<JComboBoxSessionTypeItem>();
    for (Computer.TypeOfLoginSession typeSession : Computer.TypeOfLoginSession.values()) {
      String typeSessionText = theLanguageToComputer.getText(typeSession.getLanguagueID());
      JComboBoxSessionTypeItem jcbsyi = new JComboBoxSessionTypeItem(typeSession, typeSessionText);
      jComboBoxSessionType.addItem(jcbsyi);
    }

    jComboBoxSessionType.setBounds(220, 42, 205, 31);
    contentPanel.add(jComboBoxSessionType);

    JLabel jLabelUserName = new JLabel(theLanguageJDialog.getText("USERNAME"));
    jLabelUserName.setBounds(5, 78, 215, 31);
    contentPanel.add(jLabelUserName);

    jTextFieldUsername = new JTextField();
    jTextFieldUsername.setBounds(220, 78, 205, 31);
    contentPanel.add(jTextFieldUsername);
    jTextFieldUsername.setColumns(10);

    JLabel jLabelHostName = new JLabel(theLanguageJDialog.getText("HOSTNAME"));
    jLabelHostName.setBounds(5, 114, 215, 31);
    contentPanel.add(jLabelHostName);

    jTextFieldHostname = new JTextField();
    jTextFieldHostname.setBounds(220, 114, 205, 31);
    contentPanel.add(jTextFieldHostname);
    jTextFieldHostname.setColumns(10);

    JLabel jLabelIP = new JLabel(theLanguageJDialog.getText("IP"));
    jLabelIP.setBounds(5, 150, 215, 31);
    contentPanel.add(jLabelIP);

    jTextFieldIP = new JTextField();
    jTextFieldIP.setBounds(220, 150, 205, 31);
    contentPanel.add(jTextFieldIP);
    jTextFieldIP.setColumns(10);

    JLabel jLabelClientVersion = new JLabel(theLanguageJDialog.getText("JAVIS_CLIENT_VERSION"));
    jLabelClientVersion.setBounds(5, 185, 215, 31);
    contentPanel.add(jLabelClientVersion);

    jTextFieldJavisClientVersion = new JTextField();

    jTextFieldJavisClientVersion.setBounds(220, 185, 205, 31);
    contentPanel.add(jTextFieldJavisClientVersion);

    JLabel jLabelDetails = new JLabel(theLanguageJDialog.getText("DETAILS"));
    jLabelDetails.setBounds(5, 220, 215, 31);
    contentPanel.add(jLabelDetails);

    jTextAreaDetails = new JTextArea();

    JScrollPane jScrollPaneDetails = new JScrollPane(jTextAreaDetails);
    jScrollPaneDetails.setBounds(5, 244, 420, 120);

    contentPanel.add(jScrollPaneDetails);

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

    jButtonOk = new JButton(theLanguageJDialog.getText("OK"));
    jButtonOk.addActionListener(buttonsControl);
    jButtonOk.setActionCommand("OK");
    buttonPane.add(jButtonOk);

    jButtonCancel = new JButton(theLanguageJDialog.getText("CANCEL"));
    jButtonCancel.addActionListener(buttonsControl);
    jButtonCancel.setActionCommand("Cancel");
    buttonPane.add(jButtonCancel);
    jPanelRoot.add(buttonPane, BorderLayout.SOUTH);

    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    jPanelRoot.add(contentPanel, BorderLayout.CENTER);
    setContentPane(jPanelRoot);
    getRootPane().setDefaultButton(jButtonOk);
    setSize(450, 434);
    setLocationRelativeTo(jFrameParent);
  }

  private void fillTheComputerFields() {

    if (typeOfAction == TypeOfAction.CREATE_COMPUTER) {
      return;
    }

    jComboBoxPowerStatus.setSelectedItem(
        new JComboBoxPowerStatusItem(computer.getPowerStatus(), ""));
    jComboBoxSessionType.setSelectedItem(
        new JComboBoxSessionTypeItem(computer.getSessionType(), ""));
    jTextFieldUsername.setText(computer.getUsername());
    jTextFieldHostname.setText(computer.getHostname());
    jTextFieldIP.setText(computer.getIPStr());
    jTextAreaDetails.setText(computer.getDetails());
  }

  private class JComboBoxPowerStatusItem {

    private PowerStatus powerStatus;
    private String textLanguage;

    public JComboBoxPowerStatusItem(PowerStatus powerStatus, String textLanguage) {
      this.powerStatus = powerStatus;
      this.textLanguage = textLanguage;
    }

    public PowerStatus getPowerStatus() {
      return powerStatus;
    }

    @Override
    public String toString() {
      return textLanguage;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      JComboBoxPowerStatusItem other = (JComboBoxPowerStatusItem) obj;
      if (powerStatus != other.powerStatus) return false;
      return true;
    }
  }

  private class JComboBoxSessionTypeItem {

    private TypeOfLoginSession typeOfLoginSession;
    private String textLanguage;

    public JComboBoxSessionTypeItem(TypeOfLoginSession typeOfLoginSession, String textLanguage) {
      this.typeOfLoginSession = typeOfLoginSession;
      this.textLanguage = textLanguage;
    }

    public TypeOfLoginSession getTypeOfLoginSession() {
      return typeOfLoginSession;
    }

    @Override
    public String toString() {
      return textLanguage;
    }

    @Override
    public boolean equals(Object obj) {

      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      JComboBoxSessionTypeItem other = (JComboBoxSessionTypeItem) obj;
      if (typeOfLoginSession != other.typeOfLoginSession) return false;
      return true;
    }
  }

  private class ButtonsControl extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonCancel)) {
        dispose();
        return;
      }
      if (e.getSource().equals(jButtonOk)) {

        // If it was not possible to fill in the computer, some information is invalid,
        // therefore, we cannot proceed
        if (!fillTheComputerToSendIt()) return;

        JDialogYesCancelOption checkConfirmation =
            swingMediator.showYesCancelOptions(theLanguageJDialog.getText("CONFIRM_BEFORE_OK"));

        if (checkConfirmation != JDialogYesCancelOption.YES) return;

        switch (typeOfAction) {
          case TypeOfAction.CREATE_COMPUTER:
            {
              createComputer();
              break;
            }
          case TypeOfAction.UPDATE_COMPUTER:
            {
              updateComputer();
              break;
            }
        }
        dispose();
      }
    }

    /**
     * Try fill the computer with the information provided in the GUI fields
     *
     * @return <code>true</code> if the computer was filled, <code>false</code> otherwise
     */
    private boolean fillTheComputerToSendIt() {
      PowerStatus powerStatus =
          ((JComboBoxPowerStatusItem) jComboBoxPowerStatus.getSelectedItem()).getPowerStatus();
      TypeOfLoginSession typeOfLoginSession =
          ((JComboBoxSessionTypeItem) jComboBoxSessionType.getSelectedItem())
              .getTypeOfLoginSession();

      String username = jTextFieldUsername.getText();
      String hostname = jTextFieldHostname.getText();
      String ip = jTextFieldIP.getText();
      String javisClientVersion = jTextFieldJavisClientVersion.getText();
      String details = jTextAreaDetails.getText();

      try {
        computer.setPowerStatus(powerStatus);
        computer.setSessionType(typeOfLoginSession);
        computer.setJavisClientVersion(javisClientVersion);
        computer.setUsername(username);
        computer.setHostname(hostname);
        computer.getIP().setIP(ip);
        computer.setDetails(details);
        return true;

      } catch (ComputerException | IPException e1) {
        String errorInvalidInformation = theLanguageJDialog.getText("ERROR_INVALID_INFORMATION");
        swingMediator.showMessageToUser(
            JDialogType.ERROR, errorInvalidInformation, e1.getMessage());
        return false;
      }
    }

    private void createComputer() {
      computer.setManual(true);
      computer.setLoginDate(new Date(0));
      vncComputerListInternalFrameController.createComputer(computer);
    }

    private void updateComputer() {
      vncComputerListInternalFrameController.updateComputer(computer);
    }
  }
}
