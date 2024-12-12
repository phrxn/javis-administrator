package com.quazzom.javis.administrator.io.parser;

public class TokenStream {

  private int index;
  private String line;

  public TokenStream() {
    this("");
  }

  public TokenStream(String line) {
    this.line = line;
    this.index = 0;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public char getActualCharAndGoToTheNext() {
    if (line.length() == index) return '\0';
    char charToReturn = line.charAt(index);
    index++;
    return charToReturn;
  }

  public char checkNextChar() {
    if (index == line.length()) return '\0';
    if (index + 1 == line.length()) return '\0';
    return line.charAt(index + 1);
  }

  public char getActualChar() {
    if (index == line.length()) return '\0';
    return line.charAt(index);
  }
}
