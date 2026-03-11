package com.quazzom.javis.administrator.io.parser;

import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.lang.TextNotFoundException;

public class Grammar {

  private Text theLanguage;
  private FormatFormattingTokens formatFormattingTokens;

  public Grammar(FormatFormattingTokens formatFormattingTokens) {
    this(LanguageFactory.getLanguage(LanguagePathToFile.GRAMMAR), formatFormattingTokens);
  }

  public Grammar(Text theLanguage, FormatFormattingTokens formatFormattingTokens) {
    this.theLanguage = theLanguage;
    this.formatFormattingTokens = formatFormattingTokens;
  }

  public void syntax(List<Token> listOfTokens) throws GrammarException {

    escapeSpecialTokens(listOfTokens);

    if (theListContainsOpenDoubleQuotes(listOfTokens)) {
      throw new GrammarException(theLanguage.getText("OPENED_DOUBLE_QUOTES_EXCEPTION"));
    }

    formatFormattingTokens.replaceFormattingTokensByWords(listOfTokens);
  }

  public boolean theListContainsOpenDoubleQuotes(List<Token> listOfTokens) {

    int numberOfDoubleQuote = 0;

    for (Token token : listOfTokens) {
      if (token.getTypeOfToken() == TypeOfToken.DOUBLE_QUOTE) numberOfDoubleQuote++;
    }

    if (numberOfDoubleQuote % 2 == 0) {
      return false;
    }
    return true;
  }

  public void escapeSpecialTokens(List<Token> listOfTokens)
      throws GrammarException, TextNotFoundException {

    for (int countToken = 0; countToken < listOfTokens.size(); countToken++) {
      Token tokenTemp = listOfTokens.get(countToken);
      if (tokenTemp.getTypeOfToken() != TypeOfToken.ESCAPE) {
        continue;
      }

      Token tokenAfterScape = listOfTokens.get(countToken + 1);

      if (tokenAfterScape.getTypeOfToken() == TypeOfToken.EOL) {
        throw new GrammarException(theLanguage.getText("ESCAPE_AT_THE_END_OF_THE_LINE_EXCEPTION"));
      }
      if (tokenAfterScape.getTypeOfToken() == TypeOfToken.SEPARADOR) {
        throw new GrammarException(theLanguage.getText("SEPARATOR_AFTER_ESCAPE_EXCEPTION"));
      }

      if (tokenAfterScape.getTypeOfToken() == TypeOfToken.WORD) {
        if (!tokenIsAValidFormmatringString(tokenAfterScape)) {
          int sizeOfWord = tokenAfterScape.getText().length();
          if (sizeOfWord == 1) {
            char invalidChar = tokenAfterScape.getText().charAt(0);
            throw new GrammarException(
                theLanguage.getText("INVALID_CHARACTER_TO_ESCAPE_EXCEPTION", invalidChar));
          }
          throw new GrammarException(
              theLanguage.getText("INVALID_WORD_TO_ESCAPE_EXCEPTION", tokenAfterScape.getText()));
        }
      }

      if (tokenIsAValidFormmatringString(tokenAfterScape)) {
        String tokenWordFormatting = tokenAfterScape.getText();

        if (tokenWordFormatting.equals(FormattingStrings.FORMATTER_HOST.getText())) {
          tokenAfterScape.setTypeOfToken(TypeOfToken.HOST);
        } else if (tokenWordFormatting.equals(FormattingStrings.FORMATTER_PORT.getText())) {
          tokenAfterScape.setTypeOfToken(TypeOfToken.PORT);
        } else if (tokenWordFormatting.equals(FormattingStrings.FORMATTER_USER.getText())) {
          tokenAfterScape.setTypeOfToken(TypeOfToken.USER);
        } else if (tokenWordFormatting.equals(FormattingStrings.FORMATTER_PASSWORD.getText())) {
          tokenAfterScape.setTypeOfToken(TypeOfToken.PASSWORD);
        } else {
          throw new RuntimeException(
              theLanguage.getText("FORMMATING_STRING_DOESNT_LISTED_EXCEPTION"));
        }
      } else {
        tokenAfterScape.setTypeOfToken(TypeOfToken.WORD);
      }

      listOfTokens.remove(countToken);
      listOfTokens.set(countToken, tokenAfterScape);
    }
  }

  private boolean tokenIsAValidFormmatringString(Token token) {

    if (token.getTypeOfToken() != TypeOfToken.WORD) {
      return false;
    }
    for (FormattingStrings f : FormattingStrings.values()) {
      if (f.getText().equals(token.getText())) {
        return true;
      }
    }
    return false;
  }
}
