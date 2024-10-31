package com.quazzom.javis.administrator.gui.dialog;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.UIManager;

public enum JDialogType {
  ERROR(UIManager.getIcon("OptionPane.errorIcon"), new Color(255, 200, 200)),
  WARN(UIManager.getIcon("OptionPane.warningIcon"), new Color(255, 255, 200)),
  INFO(UIManager.getIcon("OptionPane.informationIcon"), new Color(200, 200, 255)),
  QUESTION(UIManager.getIcon("OptionPane.questionIcon"), new Color(200, 255, 200));

  private Icon icon;
  private Color backgroundColor;

  JDialogType(Icon icon, Color backgroundColor) {
    this.icon = icon;
    this.backgroundColor = backgroundColor;
  }

  public Icon getIcon() {
    return this.icon;
  }

  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
}
