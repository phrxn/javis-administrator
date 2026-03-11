package com.quazzom.javis.administrator.gui.text_field;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import com.quazzom.javis.administrator.gui.document_filter.DocumentFilterInteger;

public class JTextFieldInteger extends JTextFieldGenericTypes {

  public JTextFieldInteger() {
    super(new DocumentFilterInteger());

    addFocusListener(new CheckIntegerValue());
  }

  private class CheckIntegerValue implements FocusListener {

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
      String value = getText();
      try {
        Integer.valueOf(value);
      } catch (Exception ex) {
        setText("");
      }
    }
  }
}
