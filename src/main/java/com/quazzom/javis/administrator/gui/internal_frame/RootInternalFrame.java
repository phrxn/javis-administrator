package com.quazzom.javis.administrator.gui.internal_frame;

import javax.swing.JInternalFrame;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;

public class RootInternalFrame extends JInternalFrame {

  public RootInternalFrame() {
    this("", true, true, true, false);
  }

  public RootInternalFrame(
      String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
    super(title, resizable, closable, maximizable, iconifiable);

    setFrameIcon(SwingImages.getImageIcon(ImagePathToFile.ICON_JAVIS_16_X_16));
  }
}
