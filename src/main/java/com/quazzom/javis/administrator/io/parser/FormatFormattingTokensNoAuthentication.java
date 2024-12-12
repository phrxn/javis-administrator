package com.quazzom.javis.administrator.io.parser;

import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class FormatFormattingTokensNoAuthentication implements FormatFormattingTokens {

  private Text theLanguage;
  private String theHost;
  private String thePort;

  public FormatFormattingTokensNoAuthentication(String theHost, String thePort) {
    this(
        theHost,
        thePort,
        LanguageFactory.getLanguage(LanguagePathToFile.FORMAT_FORMATTING_TOKENS_NO_AUTHENTICATION));
  }

  public FormatFormattingTokensNoAuthentication(String theHost, String thePort, Text theLanguage) {
    this.theLanguage = theLanguage;
    this.theHost = theHost;
    this.thePort = thePort;
  }

  @Override
  public void replaceFormattingTokensByWords(List<Token> listOfTokens) throws GrammarException {

    // checks if the host formatting token exists, if not, an exception should be thrown
    if (!listOfTokens.contains(
        new Token(TypeOfToken.HOST, FormattingStrings.FORMATTER_HOST.getText()))) {
      throw new GrammarException(
          theLanguage.getText(
              "FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION",
              FormattingStrings.FORMATTER_HOST.getFullTextWithEscape()));
    }

    // checks if the port formatting token exists, if not, an exception should be thrown
    if (!listOfTokens.contains(
        new Token(TypeOfToken.PORT, FormattingStrings.FORMATTER_PORT.getText()))) {
      throw new GrammarException(
          theLanguage.getText(
              "FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION",
              FormattingStrings.FORMATTER_PORT.getFullTextWithEscape()));
    }

    // replace all host formmating
    setFormattingToken(listOfTokens, TypeOfToken.HOST, theHost);

    // replace all port formmating
    setFormattingToken(listOfTokens, TypeOfToken.PORT, thePort);
  }

  private void setFormattingToken(
      List<Token> listOfTokens, TypeOfToken typeOfToken, String newText) {
    for (Token token : listOfTokens) {
      if (token.getTypeOfToken() != typeOfToken) {
        continue;
      }
      token.setText(newText);
      token.setTypeOfToken(TypeOfToken.WORD);
    }
  }
}
