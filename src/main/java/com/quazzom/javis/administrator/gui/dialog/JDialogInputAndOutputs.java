package com.quazzom.javis.administrator.gui.dialog;

import java.awt.Dimension;
import javax.swing.JFrame;

public class JDialogInputAndOutputs extends JDialogDefault {

  public JDialogInputAndOutputs(JFrame jFrameParent, boolean isModal) {
    super(jFrameParent, isModal);
    setSize(new Dimension(680, 300));
    setLocationRelativeTo(jFrameParent);
  }
}
