package com.quazzom.javis.administrator.gui.text_field;

import com.quazzom.javis.administrator.gui.document_filter.DocumentFilterPortTCP;

public class JTextFieldTCPPort extends JTextFieldGenericTypes {

  public JTextFieldTCPPort() {
    super(new DocumentFilterPortTCP());
  }
}
