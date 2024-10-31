package com.quazzom.javis.administrator.uncaught_exceptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.popup.CopyPopup;
import com.quazzom.javis.administrator.gui.text_pane.JTextPaneWithoutWrap;
import com.quazzom.javis.administrator.lang.LanguageInMemory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class JDialogUncaughtException extends JDialog implements UncaughtExceptionListener {

  private static final long serialVersionUID = 1L;

  private JButton jButtonClose;

  private JLabel jLabelInformation;

  private JLabel jLabelTitleH1;

  private JLabel jLabelProgramNameInfo;
  private JLabel jLabelProgramName;
  // --
  private JLabel jLabelVersionInfo;
  private JLabel jLabelVersion;
  // --
  private JLabel jLabelThreadIDInfo;
  private JLabel jLabelThreadID;
  // --
  private JLabel jLabelThreadNameInfo;
  private JLabel jLabelThreadName;
  //
  //
  private JTextPane jTextPaneException;
  //
  private Text textLanguage;

  //
  public JDialogUncaughtException() {
    this(new LanguageInMemory(LanguagePathToFile.JDIALOG_UNCAUGHT_EXCEPTION));
  }

  public JDialogUncaughtException(Text textLanguage) {

    super((Frame) null, true);

    this.textLanguage = textLanguage;

    jLabelTitleH1 = new JLabel();
    jLabelTitleH1.setIcon(
        new ImageIcon(JDialogUncaughtException.class.getResource("/images/icons/bug.png")));
    jLabelTitleH1.setFont(new Font("Arial", Font.PLAIN, 20));

    jLabelInformation = new JLabel();

    jTextPaneException = new JTextPaneWithoutWrap();
    jTextPaneException.setEditable(false);
    final JScrollPane JScrollPanelExceptionDetails = new JScrollPane(jTextPaneException);
    JScrollPanelExceptionDetails.setPreferredSize(new Dimension(0, 100));
    new CopyPopup(jTextPaneException, new LanguageInMemory(LanguagePathToFile.COPY_POPUP));

    jButtonClose = new JButton();
    jButtonClose.addActionListener(
        (e) -> {
          dispose();
        });

    final JPanel jPanelTitleH1 = new JPanel();
    jPanelTitleH1.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
    jPanelTitleH1.add(jLabelTitleH1);
    jPanelTitleH1.setBackground(Color.WHITE);
    jPanelTitleH1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

    final JPanel jPanelButtonClose = new JPanel();
    jPanelButtonClose.setLayout(new FlowLayout(FlowLayout.RIGHT));
    jPanelButtonClose.add(jButtonClose);

    JPanel jPanelUncaughtExceptionDetails = new JPanel();
    jPanelUncaughtExceptionDetails.setSize(new Dimension(200, 200));
    jPanelUncaughtExceptionDetails.setPreferredSize(new Dimension(getWidth(), 70));
    GridBagLayout gbl_jPanelUncaughtExceptionDetails = new GridBagLayout();
    gbl_jPanelUncaughtExceptionDetails.columnWidths = new int[] {50, 300, 0};
    gbl_jPanelUncaughtExceptionDetails.rowHeights = new int[] {16, 16, 16, 16, 0};
    gbl_jPanelUncaughtExceptionDetails.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
    gbl_jPanelUncaughtExceptionDetails.rowWeights =
        new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    jPanelUncaughtExceptionDetails.setLayout(gbl_jPanelUncaughtExceptionDetails);

    GridBagConstraints gbc_jLabelInfoProgramName = new GridBagConstraints();
    gbc_jLabelInfoProgramName.fill = GridBagConstraints.BOTH;
    gbc_jLabelInfoProgramName.insets = new Insets(0, 0, 5, 5);
    gbc_jLabelInfoProgramName.gridx = 0;
    gbc_jLabelInfoProgramName.gridy = 0;
    jLabelProgramNameInfo = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelProgramNameInfo, gbc_jLabelInfoProgramName);

    GridBagConstraints gbc_jLabelProgramName = new GridBagConstraints();
    gbc_jLabelProgramName.fill = GridBagConstraints.BOTH;
    gbc_jLabelProgramName.insets = new Insets(0, 0, 5, 0);
    gbc_jLabelProgramName.gridx = 1;
    gbc_jLabelProgramName.gridy = 0;
    jLabelProgramName = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelProgramName, gbc_jLabelProgramName);

    GridBagConstraints gbc_jLabelInfoVersion = new GridBagConstraints();
    gbc_jLabelInfoVersion.fill = GridBagConstraints.BOTH;
    gbc_jLabelInfoVersion.insets = new Insets(0, 0, 5, 5);
    gbc_jLabelInfoVersion.gridx = 0;
    gbc_jLabelInfoVersion.gridy = 1;
    jLabelVersionInfo = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelVersionInfo, gbc_jLabelInfoVersion);

    GridBagConstraints gbc_jLabelVersion = new GridBagConstraints();
    gbc_jLabelVersion.fill = GridBagConstraints.BOTH;
    gbc_jLabelVersion.insets = new Insets(0, 0, 5, 0);
    gbc_jLabelVersion.gridx = 1;
    gbc_jLabelVersion.gridy = 1;
    jLabelVersion = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelVersion, gbc_jLabelVersion);

    GridBagConstraints gbc_jLabelInfoThreadID = new GridBagConstraints();
    gbc_jLabelInfoThreadID.fill = GridBagConstraints.BOTH;
    gbc_jLabelInfoThreadID.insets = new Insets(0, 0, 5, 5);
    gbc_jLabelInfoThreadID.gridx = 0;
    gbc_jLabelInfoThreadID.gridy = 2;
    jLabelThreadIDInfo = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelThreadIDInfo, gbc_jLabelInfoThreadID);

    GridBagConstraints gbc_jLabelThreadID = new GridBagConstraints();
    gbc_jLabelThreadID.fill = GridBagConstraints.BOTH;
    gbc_jLabelThreadID.insets = new Insets(0, 0, 5, 0);
    gbc_jLabelThreadID.gridx = 1;
    gbc_jLabelThreadID.gridy = 2;
    jLabelThreadID = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelThreadID, gbc_jLabelThreadID);

    GridBagConstraints gbc_jLabelInfoThreadName = new GridBagConstraints();
    gbc_jLabelInfoThreadName.fill = GridBagConstraints.BOTH;
    gbc_jLabelInfoThreadName.insets = new Insets(0, 0, 0, 5);
    gbc_jLabelInfoThreadName.gridx = 0;
    gbc_jLabelInfoThreadName.gridy = 3;
    jLabelThreadNameInfo = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelThreadNameInfo, gbc_jLabelInfoThreadName);

    GridBagConstraints gbc_jLabelThreadName = new GridBagConstraints();
    gbc_jLabelThreadName.fill = GridBagConstraints.BOTH;
    gbc_jLabelThreadName.gridx = 1;
    gbc_jLabelThreadName.gridy = 3;
    jLabelThreadName = new JLabel();
    jPanelUncaughtExceptionDetails.add(jLabelThreadName, gbc_jLabelThreadName);

    final JPanel jPanelInformations = new JPanel();
    jPanelInformations.setLayout(new BorderLayout(5, 10));
    jPanelInformations.add(jLabelInformation, BorderLayout.NORTH);
    jPanelInformations.add(jPanelUncaughtExceptionDetails, BorderLayout.CENTER);
    jPanelInformations.add(JScrollPanelExceptionDetails, BorderLayout.SOUTH);
    jPanelInformations.setBorder(BorderFactory.createEmptyBorder(8, 4, 2, 4));

    getContentPane().add(jPanelTitleH1, BorderLayout.PAGE_START);
    getContentPane().add(jPanelButtonClose, BorderLayout.PAGE_END);
    getContentPane().add(jPanelInformations, BorderLayout.CENTER);

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle(AdministratorSingleton.getInstance().getProgramName());
    setIconImage(
        Toolkit.getDefaultToolkit()
            .getImage(
                JDialogUncaughtException.class.getResource("/images/icons/icon_javis_16x16.png")));
    setSize(630, 450);
  }

  void showDialog() {
    setLocationRelativeTo(getParent());
    setVisible(true);
  }

  private void setTexts(Text text, InformationAboutUncaughtException iaue) {

    jButtonClose.setText(text.getText("JBUTTON_CLOSE"));
    // --
    jLabelInformation.setText(text.getText("JLABEL_INFORMATION", iaue.getStrProgramName()));
    // --
    jLabelTitleH1.setText(text.getText("JLABEL_TITLE_H1"));
    // --
    jLabelProgramNameInfo.setText(text.getText("JLABEL_PROGRAM_NAME_INFO"));
    jLabelProgramName.setText(text.getText("JLABEL_PROGRAM_NAME", iaue.getStrProgramName()));
    // --
    jLabelVersionInfo.setText(text.getText("JLABEL_VERSION_INFO"));
    jLabelVersion.setText(text.getText("JLABEL_VERSION", iaue.getStrProgramVersion()));
    // --
    jLabelThreadIDInfo.setText(text.getText("JLABEL_THREAD_ID_INFO"));
    jLabelThreadID.setText(text.getText("JLABEL_THREAD_ID", iaue.getStrThreadID()));
    // --
    jLabelThreadNameInfo.setText(text.getText("JLABEL_THREAD_NAME_INFO"));
    jLabelThreadName.setText(text.getText("JLABEL_THREAD_NAME", iaue.getStrThreadName()));
    // --
    jTextPaneException.setText(createTheJTextPaneExceptionText(iaue));
    jTextPaneException.setCaretPosition(0);
  }

  private String createTheJTextPaneExceptionText(InformationAboutUncaughtException iaue) {
    StringBuilder sb = new StringBuilder();

    sb.append(textLanguage.getText("JLABEL_PROGRAM_NAME_INFO").replaceAll(":$", ": "))
        .append(iaue.getStrProgramName())
        .append("\n")
        .append(textLanguage.getText("JLABEL_VERSION_INFO").replaceAll(":$", ": "))
        .append(iaue.getStrProgramVersion())
        .append("\n")
        .append(textLanguage.getText("JLABEL_THREAD_ID_INFO").replaceAll(":$", ": "))
        .append(iaue.getStrThreadID())
        .append("\n")
        .append(textLanguage.getText("JLABEL_THREAD_NAME_INFO").replaceAll(":$", ": "))
        .append(iaue.getStrThreadName())
        .append("\n------------------------------\n")
        .append(iaue.getStrStackTrace());

    return sb.toString();
  }

  @Override
  public void showUncaughtException(InformationAboutUncaughtException iue) {

    SwingUtilities.invokeLater(
        () -> {
          setTexts(textLanguage, iue);
          showDialog();
        });
  }
}
