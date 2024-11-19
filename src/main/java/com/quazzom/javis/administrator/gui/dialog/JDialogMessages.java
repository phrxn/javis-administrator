package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.quazzom.javis.administrator.gui.panel.JPanelTextPaneWithoutWrap;
import com.quazzom.javis.administrator.lang.LanguageInMemory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class JDialogMessages extends JDialogDefault implements ActionListener {

  private static final long serialVersionUID = 1L;

  private JButton jButtonClose;
  private JPanel jPanelMain;
  private JLabel jLableBugImageAndType;
  private JPanelTextPaneWithoutWrap jPanelAllInformations;

  private JDialogType type;
  private String title;
  private String messageToShow;
  private Text text;
  private Image imageIcon;

  public JDialogMessages() {
    this(null, JDialogType.INFO, "the title", "message");
  }

  public JDialogMessages(JFrame parent, JDialogType type, String title, String messageToShow) {
    this(
        parent,
        type,
        title,
        messageToShow,
        new LanguageInMemory(LanguagePathToFile.JDIALOG_MESSAGES));
  }

  public JDialogMessages(
      JFrame parent, JDialogType type, String title, String messageToShow, Text text) {
    this(parent, null, type, title, messageToShow, text);
  }

  public JDialogMessages(
      JFrame parent,
      Image imageIcon,
      JDialogType type,
      String title,
      String messageToShow,
      Text text) {
    super(parent, true);
    this.imageIcon = imageIcon;
    this.type = type;
    this.title = title;
    this.messageToShow = messageToShow;
    this.text = text;

    startGUI();
  }

  private void startGUI() {

    JPanel jPanelRoot = new JPanel();
    jPanelRoot.setLayout(new BorderLayout());

    jLableBugImageAndType = new JLabel();
    jLableBugImageAndType.setFont(new Font("Arial", Font.PLAIN, 16));
    jLableBugImageAndType.setIcon(type.getIcon());
    jLableBugImageAndType.setText(title);

    jButtonClose = new JButton();
    jButtonClose.setText(text.getText("JBUTTON_CLOSE"));
    jButtonClose.addActionListener(this);

    jPanelMain = new JPanel();
    jPanelMain.setBackground(type.getBackgroundColor());
    jPanelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
    jPanelMain.add(jLableBugImageAndType);
    jPanelMain.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

    final JPanel jPanelBottomButtons = new JPanel();
    jPanelBottomButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
    jPanelBottomButtons.add(jButtonClose);

    jPanelAllInformations = new JPanelTextPaneWithoutWrap();
    jPanelAllInformations.appendText(messageToShow);

    jPanelRoot.add(jPanelMain, BorderLayout.PAGE_START);
    jPanelRoot.add(jPanelAllInformations, BorderLayout.CENTER);
    jPanelRoot.add(jPanelBottomButtons, BorderLayout.PAGE_END);

    getRootPane().setDefaultButton(jButtonClose);
    jButtonClose.requestFocusInWindow();

    setIconImage(imageIcon);
    setContentPane(jPanelRoot);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(jButtonClose)) dispose();
  }
}
