package com.quazzom.javis.administrator.images;

public enum ImagePathToFile {
  ICON_JAVIS_16_X16("images/icons/icon_javis_16x16.png");

  private String path;

  private ImagePathToFile(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
