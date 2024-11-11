package com.quazzom.javis.administrator.gui.internal_frame;

import javax.swing.JInternalFrame;
import com.quazzom.javis.administrator.gui.SwingMediator;

public class RootInternalFrame extends JInternalFrame {

  private SwingMediator swingMediator;

  public RootInternalFrame(SwingMediator swingMediator) {
    this("", true, true, true, false, swingMediator);
  }

  public RootInternalFrame(
      String title,
      boolean resizable,
      boolean closable,
      boolean maximizable,
      boolean iconifiable,
      SwingMediator swingMediator) {
    super(title, resizable, closable, maximizable, iconifiable);
  }
}
