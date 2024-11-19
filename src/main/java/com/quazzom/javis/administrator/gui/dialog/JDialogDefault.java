package com.quazzom.javis.administrator.gui.dialog;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.quazzom.javis.administrator.gui.SwingCommons;

public class JDialogDefault extends JDialog {

  private static final long serialVersionUID = 1L;

  private SwingCommons swingCommons;

  public JDialogDefault(JFrame jFrameParent, boolean isModal) {
    super(jFrameParent, isModal);
    swingCommons = new SwingCommons();
    setResizable(false);
    setSize(new Dimension(680, 300));
    setTitle(swingCommons.createTitle());
    setLocationRelativeTo(jFrameParent);
  }
}
