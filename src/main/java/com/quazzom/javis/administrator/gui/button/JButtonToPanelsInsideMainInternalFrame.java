package com.quazzom.javis.administrator.gui.button;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JButtonToPanelsInsideMainInternalFrame extends JButton {

  public JButtonToPanelsInsideMainInternalFrame(String text) {

    setText(text);
    setHorizontalAlignment(SwingConstants.LEFT);
    setPreferredSize(new Dimension(250, 40));
  }
}
