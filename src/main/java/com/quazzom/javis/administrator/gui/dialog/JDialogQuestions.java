package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.quazzom.javis.administrator.AdministratorSingleton;
import com.quazzom.javis.administrator.gui.panel.JPanelTextPaneWithoutWrap;
import com.quazzom.javis.administrator.lang.Text;

public class JDialogQuestions {

  private JPanel jPanelMain;
  private JLabel jLableBugImageAndType;

  private JPanel jPanelRoot;

  private JPanelTextPaneWithoutWrap jPanelAllInformations;

  private JFrame parent;

  private Text text;

  public JDialogQuestions(JFrame parent, Text text) {
    this.parent = parent;
    this.text = text;
    startGUI();
  }

  private void startGUI() {

    jPanelRoot = new JPanel();
    jPanelRoot.setLayout(new BorderLayout());
    jPanelRoot.setBackground(new Color(255, 25, 88));

    jLableBugImageAndType = new JLabel();
    jLableBugImageAndType.setIcon(JDialogType.QUESTION.getIcon());
    jLableBugImageAndType.setFont(new Font("Arial", Font.PLAIN, 16));
    jLableBugImageAndType.setText(text.getText("JLABEL_BUG_IMAGE_AND_TYPE")); // You Wish

    jPanelMain = new JPanel();
    jPanelMain.setBackground(new Color(200, 255, 200));
    jPanelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
    jPanelMain.add(jLableBugImageAndType);
    jPanelMain.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

    jPanelAllInformations = new JPanelTextPaneWithoutWrap();

    jPanelRoot.add(jPanelMain, BorderLayout.PAGE_START);
    jPanelRoot.add(jPanelAllInformations, BorderLayout.CENTER);
  }

  /**
   * shows an OptionDialog to the user with a list of options, and returns the index of the
   * collected option, counted from 0, or -1 if the user does not choose any option.
   *
   * @param message a message to show to the user
   * @param options a array with options MUST HAVE at least one item
   * @param defaultOption a index to default option
   * @return the index of the chosen option or -1 if no option was chosen
   */
  public int showChoose(String message, String options[], int defaultOption) {

    jPanelAllInformations.appendText(message);

    int result =
        JOptionPane.showOptionDialog(
            parent,
            jPanelRoot,
            AdministratorSingleton.getInstance().getProgramName(),
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[defaultOption]);
    return result;
  }
}
