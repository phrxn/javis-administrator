package com.quazzom.javis.administrator.io.parser;

public class Peek {

  public TypeOfChar peek(TokenStream tokenStream) {

    char actualCharacter = tokenStream.getActualChar();

    switch (actualCharacter) {
      case '\0':
        return TypeOfChar.NULL;
      case '\\':
        return TypeOfChar.BACKSLASH;
      case '\"':
        return TypeOfChar.DOUBLE_QUOTE;
      case ' ':
        return TypeOfChar.SPACE;
      default:
        return TypeOfChar.WORD;
    }
  }
}
