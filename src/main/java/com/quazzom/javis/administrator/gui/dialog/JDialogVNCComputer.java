package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.text_field.JTextFieldTCPPort;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformations;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformationsException;

public class JDialogVNCComputer extends JDialogDefault {

  private SwingMediator swingMediator;
  private ComputerConnectionInformations computer;
  private Text theLanguage;
  private JDialogYesCancelOption optionToReturn = JDialogYesCancelOption.CANCEL;

  private JButton jButtonOk;
  private JButton jButtonCancel;
  private JTextField jTextFieldHostname;
  private JTextField jTextFieldPort;

  /**
   * @wbp.parser.constructor
   */
  public JDialogVNCComputer(SwingMediator swingMediator, ComputerConnectionInformations computer) {
    this(
        swingMediator,
        computer,
        LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_VNC_COMPUTER));
  }

  public JDialogVNCComputer(
      SwingMediator swingMediator, ComputerConnectionInformations computer, Text theLanguage) {
    super(swingMediator.getJFrameAdministratorFrame(), true);
    this.swingMediator = swingMediator;
    this.computer = computer;
    this.theLanguage = theLanguage;
    startGUI();
  }

  void startGUI() {

    ButtonsEvent inputEvent = new ButtonsEvent();

    JPanel jPanelRoot = new JPanel();
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
    jPanelMainOnCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
    jPanelRoot.add(jPanelMainOnCenter, BorderLayout.CENTER);

    GridBagLayout gbl_jPanelMainOnCenter = new GridBagLayout();
    gbl_jPanelMainOnCenter.columnWidths = new int[] {117, 224, 0};
    gbl_jPanelMainOnCenter.rowHeights = new int[] {35, 30, 0};
    gbl_jPanelMainOnCenter.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
    gbl_jPanelMainOnCenter.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
    jPanelMainOnCenter.setLayout(gbl_jPanelMainOnCenter);

    JLabel jLabelHostname =
        new JLabel(String.format("%s:", theLanguage.getText("JLABEL_HOSTNAME")));
    jLabelHostname.setFont(new Font("Tahoma", Font.PLAIN, 12));
    GridBagConstraints gbc_jLabelHostname = new GridBagConstraints();
    gbc_jLabelHostname.fill = GridBagConstraints.BOTH;
    gbc_jLabelHostname.insets = new Insets(0, 0, 5, 5);
    gbc_jLabelHostname.gridx = 0;
    gbc_jLabelHostname.gridy = 0;
    jPanelMainOnCenter.add(jLabelHostname, gbc_jLabelHostname);

    jTextFieldHostname = new JTextField();
    jTextFieldHostname.addActionListener(inputEvent);
    jTextFieldHostname.setText(computer.getHostName());
    GridBagConstraints gbc_jTextFieldHostname = new GridBagConstraints();
    gbc_jTextFieldHostname.fill = GridBagConstraints.BOTH;
    gbc_jTextFieldHostname.insets = new Insets(0, 0, 5, 0);
    gbc_jTextFieldHostname.gridx = 1;
    gbc_jTextFieldHostname.gridy = 0;
    jPanelMainOnCenter.add(jTextFieldHostname, gbc_jTextFieldHostname);
    jTextFieldHostname.setColumns(10);

    JLabel jLabelPort = new JLabel(String.format("%s:", theLanguage.getText("JLABEL_PORT")));
    jLabelPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
    GridBagConstraints gbc_jLabelPort = new GridBagConstraints();
    gbc_jLabelPort.fill = GridBagConstraints.BOTH;
    gbc_jLabelPort.insets = new Insets(0, 0, 0, 5);
    gbc_jLabelPort.gridx = 0;
    gbc_jLabelPort.gridy = 1;
    jPanelMainOnCenter.add(jLabelPort, gbc_jLabelPort);

    jTextFieldPort = new JTextFieldTCPPort();
    jTextFieldPort.addActionListener(inputEvent);
    jTextFieldPort.setText(String.valueOf(computer.getPort()));
    GridBagConstraints gbc_jTextFieldPort = new GridBagConstraints();
    gbc_jTextFieldPort.fill = GridBagConstraints.BOTH;
    gbc_jTextFieldPort.gridx = 1;
    gbc_jTextFieldPort.gridy = 1;
    jPanelMainOnCenter.add(jTextFieldPort, gbc_jTextFieldPort);
    jTextFieldPort.setColumns(10);

    // getRootPane().setDefaultButton(jButtonCancel);
    setContentPane(jPanelRoot);
    setSize(380, 185);
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
          || e.getSource().equals(jTextFieldHostname)
          || e.getSource().equals(jTextFieldPort)) {
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
      try {
        computer.setHostName(jTextFieldHostname.getText());
        computer.setPort(jTextFieldPort.getText());
        optionToReturn = JDialogYesCancelOption.YES;
        dispose();
      } catch (ComputerConnectionInformationsException | TCPPortException e1) {
        swingMediator.showMessageToUser(
            JDialogType.ERROR,
            theLanguage.getText("TITLE_TO_INVALID_COMPUTER_INFORMATION"),
            e1.getMessage());
      }
    }

    private void buttonCancel() {
      dispose();
    }
  }
}
