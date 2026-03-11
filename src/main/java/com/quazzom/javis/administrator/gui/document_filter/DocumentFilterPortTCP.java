package com.quazzom.javis.administrator.gui.document_filter;

import com.quazzom.javis.administrator.net.TCPPort;
import com.quazzom.javis.administrator.net.TCPPortException;

public class DocumentFilterPortTCP extends DocumentFilterRoot {

  @Override
  public boolean isTheDocumentTextValid(String documentText) {

    if (documentText.isEmpty()) return true;

    try {
      new TCPPort(documentText);
    } catch (TCPPortException e) {
      return false;
    }
    return true;
  }
}
