package com.quazzom.javis.administrator.gui.document_filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public abstract class DocumentFilterRoot extends DocumentFilter {

  @Override
  public final void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
      throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.insert(offset, string);

    if (isTheDocumentTextValid(sb.toString())) {
      super.insertString(fb, offset, string, attr);
    }
  }

  @Override
  public final void replace(
      FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
      throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.replace(offset, offset + length, text);

    if (isTheDocumentTextValid(sb.toString())) {
      super.replace(fb, offset, length, text, attrs);
    }
  }

  @Override
  public final void remove(FilterBypass fb, int offset, int length) throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.delete(offset, offset + length);

    if (isTheDocumentTextValid(sb.toString())) {
      super.remove(fb, offset, length);
    }
  }

  /**
   * The implementation of this method must check whether the passed text is valid or not. If the
   * text is invalid, the current Document value is not changed. If it is valid, the Document value
   * is changed.
   *
   * @param documentText the full text within the document
   * @return <code>true</code> if the documentText is valid, <code>false</code> otherwise
   */
  abstract boolean isTheDocumentTextValid(String documentText);
}
