package com.quazzom.javis.administrator.gui.text_field;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import com.quazzom.javis.administrator.gui.document_filter.DocumentFilterRoot;

public class JTextFieldGenericTypes extends JTextField {

  public JTextFieldGenericTypes(DocumentFilterRoot document) {
    ((AbstractDocument) getDocument()).setDocumentFilter(document);
  }
}
