package com.quazzom.javis.administrator.gui.internal_frame;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ClosableInternalFrame extends RootInternalFrame {

  private JDesktopPane jdesktopPanel;

  public ClosableInternalFrame(JFrame administratorFrame, JDesktopPane jdesktopPanel) {
    this("", true, true, true, false, administratorFrame, jdesktopPanel);
  }

  public ClosableInternalFrame(
      String title,
      boolean resizable,
      boolean closable,
      boolean maximizable,
      boolean iconifiable,
      JFrame administratorFrame,
      JDesktopPane jdesktopPanel) {
    super(title, false, closable, true, iconifiable);
    this.jdesktopPanel = jdesktopPanel;

    Dimension dimensionDefaultSize = new Dimension(new Dimension(1024 - 150, 768 - 150));

    addInternalFrameListener(new InternalFrameAdapterClose(this));

    setMinimumSize(dimensionDefaultSize);
    setPreferredSize(dimensionDefaultSize);
    setSize(dimensionDefaultSize);
  }

  class InternalFrameAdapterClose extends InternalFrameAdapter {
    private JInternalFrame jInternalFrame;

    public InternalFrameAdapterClose(JInternalFrame jInternalFrame) {
      this.jInternalFrame = jInternalFrame;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
      jdesktopPanel.remove(jInternalFrame);
      jdesktopPanel.revalidate();
      jdesktopPanel.repaint();
    }
  }
}
