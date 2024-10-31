package com.quazzom.javis.administrator.gui.frame;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;
import com.quazzom.javis.administrator.gui.SwingCommons;

public class AdministratorFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private static final int FRAME_WIDTH_MINIMUM = 1024;
  private static final int FRAME_HEIGHT_MINIMUM = 735;

  private final Rectangle SCREEN_SIZE =
      GraphicsEnvironment.getLocalGraphicsEnvironment()
          .getDefaultScreenDevice()
          .getDefaultConfiguration()
          .getBounds();
  private final Dimension FRAME_MINIMUM_SIZE =
      new Dimension(FRAME_WIDTH_MINIMUM, FRAME_HEIGHT_MINIMUM);

  private SwingCommons swingCommons;

  public AdministratorFrame() {

    swingCommons = new SwingCommons();

    startTheFrameGUIThings();
  }

  private void startTheFrameGUIThings() {
    setTitle(swingCommons.createTitle());
    setBounds(SCREEN_SIZE);
    setMinimumSize(FRAME_MINIMUM_SIZE);
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
  }
}
