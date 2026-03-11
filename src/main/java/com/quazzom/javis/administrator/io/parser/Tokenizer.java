package com.quazzom.javis.administrator.io.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  public Token createToken(TokenStream tokenStream) {

    Peek thePeek = new Peek();

    TypeOfChar typeOfChar = thePeek.peek(tokenStream);

    switch (typeOfChar) {
      case TypeOfChar.NULL:
        return handlerNullCharacter(tokenStream);
      case TypeOfChar.BACKSLASH:
        return handlerBackslashCharacter(tokenStream);
      case TypeOfChar.SPACE:
        return handlerSpace(tokenStream);
      case TypeOfChar.DOUBLE_QUOTE:
        return handlerDoubleQuote(tokenStream);
      default:
        return handlerWord(tokenStream);
    }
  }

  public Token handlerNullCharacter(TokenStream tokenStream) {
    return new Token(TypeOfToken.EOL, "");
  }

  public Token handlerBackslashCharacter(TokenStream tokenStream) {

    // consume the backslash and jump to the next character
    tokenStream.getActualCharAndGoToTheNext();

    return new Token(TypeOfToken.ESCAPE, "\\");
  }

  public Token handlerSpace(TokenStream tokenStream) {

    Peek peek = new Peek();

    StringBuilder sb = new StringBuilder();

    // jump all espaces
    while (peek.peek(tokenStream) == TypeOfChar.SPACE) {
      sb.append(tokenStream.getActualCharAndGoToTheNext());
    }
    return new Token(TypeOfToken.SEPARADOR, sb.toString());
  }

  public Token handlerDoubleQuote(TokenStream tokenStream) {

    // jump to the next character
    tokenStream.getActualCharAndGoToTheNext();

    return new Token(TypeOfToken.DOUBLE_QUOTE, "\"");
  }

  public Token handlerWord(TokenStream tokenStream) {

    Peek peek = new Peek();
    StringBuilder sb = new StringBuilder();

    while (peek.peek(tokenStream) == TypeOfChar.WORD) {
      sb.append(tokenStream.getActualCharAndGoToTheNext());
      if (isWORDAFormattingWord(sb.toString())) {
        return new Token(TypeOfToken.WORD, sb.toString());
      }
    }

    return new Token(TypeOfToken.WORD, sb.toString());
  }

  public List<Token> createTokenList(String line) {

    TokenStream tokenStream = new TokenStream(line);

    Tokenizer tokenizer = new Tokenizer();

    Peek peek = new Peek();

    List<Token> listOfTokens = new ArrayList<Token>();

    while (peek.peek(tokenStream) != TypeOfChar.NULL) {
      Token newToken = tokenizer.createToken(tokenStream);
      listOfTokens.add(newToken);
    }

    // add EOL token
    Token newToken = tokenizer.createToken(tokenStream);
    listOfTokens.add(newToken);

    return listOfTokens;
  }

  private boolean isWORDAFormattingWord(String word) {
    for (FormattingStrings f : FormattingStrings.values()) {
      if (f.getText().equals(word)) {
        return true;
      }
    }
    return false;
  }
}
