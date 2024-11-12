package com.quazzom.javis.administrator.images;

public enum ImagePathToFile {
  ICON_JAVIS_16_X_16("images/icons/icon_javis_16x16.png"),
  ICON_JAVIS_64_X_64("images/icons/icon_javis_64x64.png");

  private String path;

  private ImagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
