package com.quazzom.javis.administrator.gui.panel;

import java.io.StringWriter;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.quazzom.javis.administrator.gui.popup.CopyPopup;
import com.quazzom.javis.administrator.gui.text_pane.JTextPaneWithoutWrap;

public class JPanelTextPaneWithoutWrap extends JPanel {

  private static final long serialVersionUID = 1L;

  private JTextPaneWithoutWrap jTextPaneExceptionMessage;

  public JPanelTextPaneWithoutWrap() {

    setBorder(BorderFactory.createEmptyBorder(8, 4, 2, 4));

    jTextPaneExceptionMessage = new JTextPaneWithoutWrap();
    jTextPaneExceptionMessage.setEditable(false);
    jTextPaneExceptionMessage.setBackground(getBackground());
    final JScrollPane jScrollPanelexception = new JScrollPane(jTextPaneExceptionMessage);

    new CopyPopup(jTextPaneExceptionMessage);

    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(
        groupLayout
            .createParallelGroup(Alignment.LEADING)
            .addGroup(
                groupLayout
                    .createSequentialGroup()
                    .addComponent(
                        jScrollPanelexception, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addContainerGap()));
    groupLayout.setVerticalGroup(
        groupLayout
            .createParallelGroup(Alignment.TRAILING)
            .addGroup(
                Alignment.LEADING,
                groupLayout
                    .createSequentialGroup()
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(
                        jScrollPanelexception, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)));
    setLayout(groupLayout);
  }

  public void appendText(String messageToUser) {

    final StringWriter stringWriter = new StringWriter();

    if (jTextPaneExceptionMessage.getText().length() > 0) {
      stringWriter.append("\n");
      stringWriter.append(jTextPaneExceptionMessage.getText());
    }
    stringWriter.append(messageToUser);

    jTextPaneExceptionMessage.setText(stringWriter.toString());
    jTextPaneExceptionMessage.setCaretPosition(0);
  }
}
