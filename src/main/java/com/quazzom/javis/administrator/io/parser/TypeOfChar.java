package com.quazzom.javis.administrator.io.parser;

public enum TypeOfChar {
  BACKSLASH("\\"),
  DOUBLE_QUOTE("\""),
  SPACE(" "),
  WORD(""),
  NULL("\0");

  private String charText;

  TypeOfChar(final String charText) {
    this.charText = charText;
  }

  public String getCharText() {
    return charText;
  }
}
