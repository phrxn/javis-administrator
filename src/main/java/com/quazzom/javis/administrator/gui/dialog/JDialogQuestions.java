package com.quazzom.javis.administrator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.quazzom.javis.administrator.gui.panel.JPanelTextPaneWithoutWrap;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;
import com.quazzom.javis.administrator.lang.Text;

public class JDialogQuestions extends JDialogInputAndOutputs {

  private JPanel jPanelMain;
  private JLabel jLableYouWish;

  private JPanel jPanelRoot;

  private JPanelTextPaneWithoutWrap jPanelAllInformations;

  private JFrame parent;

  private Text text;

  public JDialogQuestions(JFrame parent, Text text) {
    super(parent, true);
    this.parent = parent;
    this.text = text;
    startGUI();
  }

  private void startGUI() {
    jPanelRoot = new JPanel();
    jPanelRoot.setLayout(new BorderLayout());
    jPanelRoot.setBackground(new Color(255, 25, 88));

    jLableYouWish = new JLabel();
    jLableYouWish.setIcon(JDialogType.QUESTION.getIcon());
    jLableYouWish.setFont(new Font("Arial", Font.PLAIN, 16));
    jLableYouWish.setText(text.getText("JLABEL_YOU_WISH")); // You Wish

    jPanelMain = new JPanel();
    jPanelMain.setBackground(new Color(200, 255, 200));
    jPanelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 12));
    jPanelMain.add(jLableYouWish);
    jPanelMain.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

    jPanelAllInformations = new JPanelTextPaneWithoutWrap();

    jPanelRoot.add(jPanelMain, BorderLayout.PAGE_START);
    jPanelRoot.add(jPanelAllInformations, BorderLayout.CENTER);
  }

  /**
   * Shows an OptionDialog to the user with a list of options, and returns the index of the
   * collected option, counted from 0, or -1 if the user does not choose any option.
   *
   * @param message a message to show to the user
   * @param options an array with options MUST HAVE at least one item
   * @param defaultOption an index to default option
   * @return the index of the chosen option or -1 if no option was chosen
   */
  public int showChoose(String message, String options[], int defaultOption) {

    jPanelAllInformations.appendText(message);

    JOptionPane optionPane =
        new JOptionPane(
            jPanelRoot,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[defaultOption]);

    JDialog dialog = optionPane.createDialog(parent, getTitle());

    JPanel buttonPanel = (JPanel) optionPane.getComponent(1);
    JButton defaultButton = (JButton) buttonPanel.getComponent(defaultOption);

    dialog.getRootPane().setDefaultButton(defaultButton);

    for (int i = 0; i < options.length; i++) {
      JButton button = (JButton) buttonPanel.getComponent(i);
      String option = options[i];

      button.addActionListener(
          e -> {
            optionPane.setValue(option);
            dialog.dispose();
          });

      button.addKeyListener(
          new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                optionPane.setValue(option);
                dialog.dispose();
              }
            }
          });
    }

    dialog.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              JButton focusedButton = (JButton) dialog.getRootPane().getDefaultButton();
              focusedButton.doClick();
            }
          }
        });

    dialog.setIconImage(SwingImages.getImage(ImagePathToFile.ICON_JAVIS_16_X_16));
    dialog.setVisible(true);

    optionPane.selectInitialValue();
    dialog.dispose();

    Object selectedValue = optionPane.getValue();

    if (selectedValue == null) return JOptionPane.CLOSED_OPTION;
    for (int counter = 0, maxCounter = options.length; counter < maxCounter; counter++) {
      if (options[counter].equals(selectedValue)) return counter;
    }
    return JOptionPane.CLOSED_OPTION;
  }
}
