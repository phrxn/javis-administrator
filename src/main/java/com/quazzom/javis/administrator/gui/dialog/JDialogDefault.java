package com.quazzom.javis.administrator.gui.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import com.quazzom.javis.administrator.gui.SwingCommons;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;

public class JDialogDefault extends JDialog {

  private static final long serialVersionUID = 1L;

  private SwingCommons swingCommons;

  public JDialogDefault(JFrame jFrameParent, boolean isModal) {
    super(jFrameParent, isModal);
    swingCommons = new SwingCommons();
    setResizable(false);
    setTitle(swingCommons.createTitle());
    setIconImage(SwingImages.getImage(ImagePathToFile.ICON_JAVIS_16_X_16));
  }
}
