package com.quazzom.javis.administrator.io.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

  private FormatFormattingTokens formatFormattingTokens;

  public Parser() {
    this(new WithoutFormattingTokens());
  }

  public Parser(FormatFormattingTokens formatFormattingTokens) {
    this.formatFormattingTokens = formatFormattingTokens;
  }

  public List<String> createCommandLine(String line) throws GrammarException {

    List<String> listCommandLine;

    Tokenizer tokenizer = new Tokenizer();
    List<Token> listOfTokens = tokenizer.createTokenList(line);

    Grammar grammar = new Grammar(formatFormattingTokens);
    grammar.syntax(listOfTokens);

    convertTheTypeofTokensInsideDoubleQuoteToWord(listOfTokens);
    removeDoubleQuotesFromList(listOfTokens);
    listOfTokens = joinTokensOfTheWordType(listOfTokens);

    listCommandLine = createCommandListFromTokenList(listOfTokens);

    return listCommandLine;
  }

  /** this method can only be called after {@link Grammar#theListContainsOpenDoubleQuotes} */
  public void convertTheTypeofTokensInsideDoubleQuoteToWord(List<Token> listOfTokens) {

    boolean isInsideDoubleQuotes = false;
    for (Token token : listOfTokens) {

      if (token.getTypeOfToken() == TypeOfToken.EOL) {
        break;
      }

      if (token.getTypeOfToken() == TypeOfToken.DOUBLE_QUOTE && !isInsideDoubleQuotes) {
        isInsideDoubleQuotes = true;
        continue;
      }

      if (token.getTypeOfToken() == TypeOfToken.DOUBLE_QUOTE && isInsideDoubleQuotes) {
        isInsideDoubleQuotes = false;
        continue;
      }

      if (isInsideDoubleQuotes) {
        token.setTypeOfToken(TypeOfToken.WORD);
      }
    }
  }

  /**
   * this method can only be called after {@link
   * Grammar#convertTheTypeofTokensInsideDoubleQuoteToWord}
   */
  public void removeDoubleQuotesFromList(List<Token> listOfTokens) {
    for (int countIndex = 0; countIndex < listOfTokens.size(); countIndex++) {
      Token tempToken = listOfTokens.get(countIndex);
      if (tempToken.getTypeOfToken() != TypeOfToken.DOUBLE_QUOTE) {
        continue;
      }
      listOfTokens.remove(countIndex);
      countIndex--;
    }
  }

  /** this method can only be called after {@link Grammar#removeDoubleQuotesFromList} */
  public List<Token> joinTokensOfTheWordType(List<Token> listOfTokens) {

    StringBuilder sb = new StringBuilder();
    List<Token> listOfTokensWithWordsJoined = new ArrayList<>();

    for (Token token : listOfTokens) {
      if (token.getTypeOfToken() == TypeOfToken.WORD) {
        sb.append(token.getText());
        continue;
      }

      if (sb.length() > 0) {
        Token newToken = new Token(TypeOfToken.WORD, sb.toString());
        listOfTokensWithWordsJoined.add(newToken);

        // reset the string builder to create a new word token
        sb.setLength(0);
      }

      listOfTokensWithWordsJoined.add(token);
    }

    return listOfTokensWithWordsJoined;
  }

  public List<String> createCommandListFromTokenList(List<Token> listOfTokens) {

    List<String> listCommandLine = new ArrayList<String>();

    for (Token token : listOfTokens) {
      if (token.getTypeOfToken() != TypeOfToken.WORD) {
        continue;
      }
      listCommandLine.add(token.getText());
    }
    return listCommandLine;
  }
}
