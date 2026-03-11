package com.quazzom.javis.administrator.io.parser;

public enum FormattingStrings {
  FORMATTER_HOST("host"),
  FORMATTER_PORT("port"),
  FORMATTER_USER("user"),
  FORMATTER_PASSWORD("password");

  private final String text;

  FormattingStrings(final String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public String getFullTextWithEscape() {
    return TypeOfChar.BACKSLASH.getCharText() + text;
  }
}
