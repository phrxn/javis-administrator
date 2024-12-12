package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.controllers.VNCConfigurationJDialogController;
import com.quazzom.javis.administrator.gui.text_field.JTextFieldInteger;
import com.quazzom.javis.administrator.gui.text_field.JTextFieldTCPPort;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;
import com.quazzom.javis.administrator.vnc.VNCProgramConfigurationException;

public class VNCProgramConfigurationJDialog extends JDialogDefault {

  private VNCConfigurationJDialogController vncConfigurationJDialogController;
  private SwingMediator swingMediator;
  private Text theLanguague;

  // -- swing things --
  private JButton jButtonCancel;
  private JButton jButtonOk;
  private JButton jButtonPathToExecutable;
  private JCheckBox jCheckBoxIsToUseChecksum;
  private JLabel jLabelVncName;
  private JLabel jLabelExecutionLine;
  private JLabel jLabelDefaultPortToAccess;
  private JLabel jLabelPathToExecutable;
  private JLabel jLabelConnectionTimeoutInSeconds;
  private JLabel jLabelParametersToConnectionWithNoAuthentication;
  private JLabel jLabelParametersToConnectionWithVNCAuthentication;
  private JLabel jLabelParametersToConnectionWithUltraVNCAuthentication;
  private JLabel jLabelParameterForInteraction;
  private JLabel jLabelParameterForNotInteraction;
  private JLabel jLabelCheckSum;
  private JTextField jTextFieldVncName;
  private JTextField jTextFieldExecutionLine;
  private JTextField jTextFieldDefaultPortToAccess;
  private JTextField jTextFieldPathToExecutable;
  private JTextField jTextFieldChecksum;
  private JTextField jTextFieldConnectionTimeoutInSeconds;
  private JTextField jTextFieldParametersToConnectionWithNoAuthentication;
  private JTextField jTextFieldParametersToConnectionWithVNCAuthentication;
  private JTextField jTextFieldparametersToConnectionWithUltraVNCAuthentication;
  private JTextField jTextFieldParameterForInteraction;
  private JTextField jTextFieldParameterForNotInteraction;

  public VNCProgramConfigurationJDialog(
      VNCConfigurationJDialogController vncConfigurationJDialogController,
      SwingMediator swingMediator,
      VNCProgramConfiguration vncConfiguration) {
    super(swingMediator.getJFrameAdministratorFrame(), true);

    this.vncConfigurationJDialogController = vncConfigurationJDialogController;
    this.swingMediator = swingMediator;

    this.theLanguague = LanguageFactory.getLanguage(LanguagePathToFile.VNC_CONFIGURATION_JDIALOG);

    createGUI();
    setLanguageToText();
    setFieldsWithVNConfigurations(vncConfiguration);

    setVisible(true);
  }

  private Border createPanelBorder(String title) {
    return new TitledBorder(
        new LineBorder(new Color(0, 0, 0)),
        title,
        TitledBorder.LEADING,
        TitledBorder.TOP,
        null,
        new Color(0, 0, 0));
  }

  public void createGUI() {

    JButtonsAction jButtonsAction = new JButtonsAction(this);
    JCheckBoxItemEvent jCheckBoxItemEvent = new JCheckBoxItemEvent();

    JPanel jPanelRoot = new JPanel();
    jPanelRoot.setLayout(new BorderLayout(0, 0));
    getContentPane().add(jPanelRoot, BorderLayout.CENTER);

    JPanel jPanelButtons = new JPanel();
    jPanelButtons.setBorder(new EmptyBorder(5, 15, 5, 5));
    jPanelButtons.setLayout(new BorderLayout(0, 0));
    jPanelRoot.add(jPanelButtons, BorderLayout.SOUTH);

    JPanel jPanelDefaultButtons = new JPanel();
    jPanelDefaultButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 0));
    jPanelButtons.add(jPanelDefaultButtons, BorderLayout.EAST);

    JPanel JPanelConfigurationOnlyBorder = new JPanel();
    JPanelConfigurationOnlyBorder.setBorder(new EmptyBorder(10, 10, 0, 10));
    JPanelConfigurationOnlyBorder.setLayout(new BorderLayout(0, 0));
    jPanelRoot.add(JPanelConfigurationOnlyBorder, BorderLayout.CENTER);

    JPanel jPanelVNCConfiguration = new JPanel();
    jPanelVNCConfiguration.setPreferredSize(new Dimension(500, 180));
    jPanelVNCConfiguration.setBorder(createPanelBorder("Configurations to connection"));
    jPanelVNCConfiguration.setLayout(null);
    JPanelConfigurationOnlyBorder.add(jPanelVNCConfiguration, BorderLayout.NORTH);

    JPanel jPanelParametersToConnection = new JPanel();
    jPanelParametersToConnection.setBorder(createPanelBorder("Parameters for connections"));
    jPanelParametersToConnection.setLayout(null);
    JPanelConfigurationOnlyBorder.add(jPanelParametersToConnection, BorderLayout.CENTER);

    jButtonOk = new JButton();
    jButtonOk.addActionListener(jButtonsAction);
    jPanelDefaultButtons.add(jButtonOk);

    jButtonCancel = new JButton();
    jButtonCancel.addActionListener(jButtonsAction);
    jPanelDefaultButtons.add(jButtonCancel);

    jButtonPathToExecutable = new JButton();
    jButtonPathToExecutable.addActionListener(jButtonsAction);
    jButtonPathToExecutable.setBounds(453, 100, 100, 23);
    jPanelVNCConfiguration.add(jButtonPathToExecutable);

    jLabelVncName = new JLabel();
    jLabelVncName.setBounds(10, 29, 160, 23);
    jPanelVNCConfiguration.add(jLabelVncName);

    jLabelExecutionLine = new JLabel();
    jLabelExecutionLine.setBounds(10, 53, 160, 23);
    jPanelVNCConfiguration.add(jLabelExecutionLine);

    jLabelDefaultPortToAccess = new JLabel();
    jLabelDefaultPortToAccess.setBounds(10, 77, 160, 23);
    jPanelVNCConfiguration.add(jLabelDefaultPortToAccess);

    jLabelPathToExecutable = new JLabel();
    jLabelPathToExecutable.setBounds(10, 101, 160, 23);
    jPanelVNCConfiguration.add(jLabelPathToExecutable);

    jLabelConnectionTimeoutInSeconds = new JLabel();
    jLabelConnectionTimeoutInSeconds.setBounds(10, 29, 205, 23);
    jPanelParametersToConnection.add(jLabelConnectionTimeoutInSeconds);

    jLabelParametersToConnectionWithNoAuthentication = new JLabel();
    jLabelParametersToConnectionWithNoAuthentication.setBounds(10, 53, 205, 23);
    jPanelParametersToConnection.add(jLabelParametersToConnectionWithNoAuthentication);

    jLabelParametersToConnectionWithVNCAuthentication = new JLabel();
    jLabelParametersToConnectionWithVNCAuthentication.setBounds(10, 77, 205, 23);
    jPanelParametersToConnection.add(jLabelParametersToConnectionWithVNCAuthentication);

    jLabelParametersToConnectionWithUltraVNCAuthentication = new JLabel();
    jLabelParametersToConnectionWithUltraVNCAuthentication.setBounds(10, 101, 205, 23);
    jPanelParametersToConnection.add(jLabelParametersToConnectionWithUltraVNCAuthentication);

    jLabelParameterForInteraction = new JLabel();
    jLabelParameterForInteraction.setBounds(10, 125, 205, 23);
    jPanelParametersToConnection.add(jLabelParameterForInteraction);

    jLabelParameterForNotInteraction = new JLabel();
    jLabelParameterForNotInteraction.setBounds(10, 149, 205, 23);
    jPanelParametersToConnection.add(jLabelParameterForNotInteraction);

    jLabelCheckSum = new JLabel();
    jLabelCheckSum.setBounds(10, 125, 160, 23);
    jPanelVNCConfiguration.add(jLabelCheckSum);

    jTextFieldVncName = new JTextField();
    jTextFieldVncName.setBounds(180, 29, 375, 23);
    jPanelVNCConfiguration.add(jTextFieldVncName);

    jTextFieldExecutionLine = new JTextField();
    jTextFieldExecutionLine.setBounds(180, 53, 375, 23);
    jPanelVNCConfiguration.add(jTextFieldExecutionLine);

    jTextFieldDefaultPortToAccess = new JTextFieldTCPPort();
    jTextFieldDefaultPortToAccess.setBounds(180, 77, 100, 23);
    jPanelVNCConfiguration.add(jTextFieldDefaultPortToAccess);

    jTextFieldPathToExecutable = new JTextField();
    jTextFieldPathToExecutable.setBounds(180, 101, 265, 23);
    jPanelVNCConfiguration.add(jTextFieldPathToExecutable);

    jTextFieldChecksum = new JTextField();
    jTextFieldChecksum.setBounds(180, 125, 375, 23);
    jPanelVNCConfiguration.add(jTextFieldChecksum);

    jCheckBoxIsToUseChecksum = new JCheckBox();
    jCheckBoxIsToUseChecksum.addItemListener(jCheckBoxItemEvent);
    jCheckBoxIsToUseChecksum.setBounds(10, 150, 543, 23);
    jPanelVNCConfiguration.add(jCheckBoxIsToUseChecksum);

    jTextFieldConnectionTimeoutInSeconds = new JTextFieldInteger();
    jTextFieldConnectionTimeoutInSeconds.setText((String) null);
    jTextFieldConnectionTimeoutInSeconds.setColumns(10);
    jTextFieldConnectionTimeoutInSeconds.setBounds(225, 29, 100, 23);
    jPanelParametersToConnection.add(jTextFieldConnectionTimeoutInSeconds);

    jTextFieldParametersToConnectionWithNoAuthentication = new JTextField();
    jTextFieldParametersToConnectionWithNoAuthentication.setColumns(10);
    jTextFieldParametersToConnectionWithNoAuthentication.setBounds(225, 53, 330, 23);
    jPanelParametersToConnection.add(jTextFieldParametersToConnectionWithNoAuthentication);

    jTextFieldParametersToConnectionWithVNCAuthentication = new JTextField();
    jTextFieldParametersToConnectionWithVNCAuthentication.setColumns(10);
    jTextFieldParametersToConnectionWithVNCAuthentication.setBounds(225, 77, 330, 23);
    jPanelParametersToConnection.add(jTextFieldParametersToConnectionWithVNCAuthentication);

    jTextFieldparametersToConnectionWithUltraVNCAuthentication = new JTextField();
    jTextFieldparametersToConnectionWithUltraVNCAuthentication.setColumns(10);
    jTextFieldparametersToConnectionWithUltraVNCAuthentication.setBounds(225, 101, 330, 23);
    jPanelParametersToConnection.add(jTextFieldparametersToConnectionWithUltraVNCAuthentication);

    jTextFieldParameterForInteraction = new JTextField();
    jTextFieldParameterForInteraction.setColumns(10);
    jTextFieldParameterForInteraction.setBounds(225, 125, 180, 23);
    jPanelParametersToConnection.add(jTextFieldParameterForInteraction);

    jTextFieldParameterForNotInteraction = new JTextField();
    jTextFieldParameterForNotInteraction.setColumns(10);
    jTextFieldParameterForNotInteraction.setBounds(225, 149, 180, 23);
    jPanelParametersToConnection.add(jTextFieldParameterForNotInteraction);

    setSize(600, 445);
    setLocationRelativeTo(swingMediator.getJFrameAdministratorFrame());
  }

  private void setLanguageToText() {

    jButtonCancel.setText(theLanguague.getText("jButtonCancel"));
    jButtonOk.setText(theLanguague.getText("jButtonOk"));
    jButtonPathToExecutable.setText(theLanguague.getText("jButtonPathToExecutable"));

    jCheckBoxIsToUseChecksum.setText(theLanguague.getText("jCheckBoxIsToUseChecksum"));

    jLabelVncName.setText(theLanguague.getText("jLabelVncName"));
    jLabelExecutionLine.setText(theLanguague.getText("jLabelExecutionLine"));
    jLabelDefaultPortToAccess.setText(theLanguague.getText("jLabelDefaultPortToAccess"));
    jLabelPathToExecutable.setText(theLanguague.getText("jLabelPathToExecutable"));
    jLabelConnectionTimeoutInSeconds.setText(
        theLanguague.getText("jLabelConnectionTimeoutInSeconds"));
    jLabelParametersToConnectionWithNoAuthentication.setText(
        theLanguague.getText("jLabelParametersToConnectionWithNoAuthentication"));
    jLabelParametersToConnectionWithVNCAuthentication.setText(
        theLanguague.getText("jLabelParametersToConnectionWithVNCAuthentication"));
    jLabelParametersToConnectionWithUltraVNCAuthentication.setText(
        theLanguague.getText("jLabelParametersToConnectionWithUltraVNCAuthentication"));
    jLabelParameterForInteraction.setText(theLanguague.getText("jLabelParameterForInteraction"));
    jLabelParameterForNotInteraction.setText(
        theLanguague.getText("jLabelParameterForNotInteraction"));
    jLabelCheckSum.setText(theLanguague.getText("jLabelCheckSum"));
  }

  private void setFieldsWithVNConfigurations(VNCProgramConfiguration vncConfig) {

    jTextFieldVncName.setText(vncConfig.getName());
    jTextFieldExecutionLine.setText(vncConfig.getExecutionLine());
    jTextFieldDefaultPortToAccess.setText(String.valueOf(vncConfig.getDefaultPortToAccess()));

    jTextFieldPathToExecutable.setText(vncConfig.getPathToExecutable());
    jTextFieldChecksum.setText(vncConfig.getCheckSum());
    jCheckBoxIsToUseChecksum.setSelected(vncConfig.isToUseChecksum());
    jTextFieldConnectionTimeoutInSeconds.setText(
        String.valueOf(vncConfig.getTimeoutInSecondsToConnection()));
    jTextFieldParametersToConnectionWithNoAuthentication.setText(
        vncConfig.getParametersToConnectionWithNoAuthentication());
    jTextFieldParametersToConnectionWithVNCAuthentication.setText(
        vncConfig.getParametersToConnectionWithVNCAuthentication());
    jTextFieldparametersToConnectionWithUltraVNCAuthentication.setText(
        vncConfig.getParametersToConnectionWithUltraVNCAuthentication());
    jTextFieldParameterForInteraction.setText(vncConfig.getParameterForInteraction());
    jTextFieldParameterForNotInteraction.setText(vncConfig.getParameterForNotInteraction());

    // if this is not going to use the check, we will disable the fields related to it
    isToUseChecksum(vncConfig.isToUseChecksum());
  }

  public void fillVNCProgramConfiguration(VNCProgramConfiguration vncConfig)
      throws VNCProgramConfigurationException, TCPPortException {

    boolean isToUseChecksum = jCheckBoxIsToUseChecksum.isSelected();

    vncConfig.setName(jTextFieldVncName.getText());
    vncConfig.setExecutionLine(jTextFieldExecutionLine.getText());
    vncConfig.setDefaultPortToAccess(jTextFieldDefaultPortToAccess.getText());
    vncConfig.setPathToExecutable(jTextFieldPathToExecutable.getText(), isToUseChecksum);
    vncConfig.setCheckSum(jTextFieldChecksum.getText(), isToUseChecksum);
    vncConfig.setToUseChecksum(isToUseChecksum);
    vncConfig.setTimeoutInSecondsToConnection(jTextFieldConnectionTimeoutInSeconds.getText());
    vncConfig.setParametersToConnectionWithNoAuthentication(
        jTextFieldParametersToConnectionWithNoAuthentication.getText());
    vncConfig.setParametersToConnectionWithVNCAuthentication(
        jTextFieldParametersToConnectionWithVNCAuthentication.getText());
    vncConfig.setParametersToConnectionWithUltraVNCAuthentication(
        jTextFieldparametersToConnectionWithUltraVNCAuthentication.getText());
    vncConfig.setParameterForInteraction(jTextFieldParameterForInteraction.getText());
    vncConfig.setParameterForNotInteraction(jTextFieldParameterForNotInteraction.getText());
  }

  public void setPathToExecutable(String pathToExecutable, String checksum) {
    jTextFieldPathToExecutable.setText(pathToExecutable);
    jTextFieldChecksum.setText(checksum);
  }

  private class JButtonsAction implements ActionListener {

    private VNCProgramConfigurationJDialog vncProgramConfigurationJDialog;

    public JButtonsAction(VNCProgramConfigurationJDialog vncProgramConfigurationJDialog) {
      this.vncProgramConfigurationJDialog = vncProgramConfigurationJDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonCancel)) {
        jButtonCancel();
        return;
      }
      if (e.getSource().equals(jButtonOk)) {
        jButtonOk();
        return;
      }
      if (e.getSource().equals(jButtonPathToExecutable)) {
        jButtonPathToExecutable();
        return;
      }
    }

    private void jButtonCancel() {
      dispose();
    }

    private void jButtonOk() {
      if (vncConfigurationJDialogController.updateVNCProgramConfiguration(
          vncProgramConfigurationJDialog)) {
        dispose();
      }
    }

    private void jButtonPathToExecutable() {
      vncConfigurationJDialogController.setPathToExecutable(vncProgramConfigurationJDialog);
    }
  }

  private class JCheckBoxItemEvent implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {

      if (e.getSource().equals(jCheckBoxIsToUseChecksum)) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          isToUseChecksum(true);
        } else {
          isToUseChecksum(false);
        }
        return;
      }
    }
  }

  public void isToUseChecksum(boolean isToUseChesum) {
    jTextFieldPathToExecutable.setEnabled(isToUseChesum);
    jButtonPathToExecutable.setEnabled(isToUseChesum);
    jTextFieldChecksum.setEnabled(isToUseChesum);
  }
}
