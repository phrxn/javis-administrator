package com.quazzom.javis.administrator.io.parser;

public class Token {

  private TypeOfToken typeOfToken;
  private String text;

  public Token(TypeOfToken typeOfToken, String text) {
    this.typeOfToken = typeOfToken;
    this.text = text;
  }

  public TypeOfToken getTypeOfToken() {
    return typeOfToken;
  }

  public void setTypeOfToken(TypeOfToken typeOfToken) {
    this.typeOfToken = typeOfToken;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Token other = (Token) obj;
    if (text == null) {
      if (other.text != null) return false;
    } else if (!text.equals(other.text)) return false;
    if (typeOfToken != other.typeOfToken) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Token [typeOfToken=" + typeOfToken + ", text=" + text + "]";
  }
}
