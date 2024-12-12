package com.quazzom.javis.administrator.gui.document_filter;

public class DocumentFilterInteger extends DocumentFilterRoot {

  @Override
  public boolean isTheDocumentTextValid(String documentText) {

    if (documentText.isEmpty()) return true;

    if (documentText.length() == 1 && documentText.charAt(0) == '-') return true;

    try {
      Integer.valueOf(documentText);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
