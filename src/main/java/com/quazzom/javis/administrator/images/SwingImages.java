package com.quazzom.javis.administrator.images;

import java.awt.Image;
import javax.swing.ImageIcon;

public class SwingImages {

  private SwingImages() {}

  public static ImageIcon getImageIcon(ImagePathToFile imagePathToFile) {
    return new ImageIcon(imagePathToFile.getPath());
  }

  public static Image getImage(ImagePathToFile imagePathToFile) {
    return getImageIcon(imagePathToFile).getImage();
  }
}
