package com.quazzom.javis.administrator.io.parser;

import java.util.List;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class FormatFormattingTokensUltraVNCMslogonAuthentication implements FormatFormattingTokens {

  private Text theLanguage;
  private String theHost;
  private String thePort;
  private String theUser;
  private String thePassword;

  public FormatFormattingTokensUltraVNCMslogonAuthentication(
      String theHost, String thePort, String theUser, String thePassword) {
    this(
        theHost,
        thePort,
        theUser,
        thePassword,
        LanguageFactory.getLanguage(
            LanguagePathToFile.FORMAT_FORMATTING_TOKENS_ULTRA_VNC_MSLOGON_AUTHENTICATION));
  }

  public FormatFormattingTokensUltraVNCMslogonAuthentication(
      String theHost, String thePort, String theUser, String thePassword, Text theLanguage) {
    this.theLanguage = theLanguage;
    this.theHost = theHost;
    this.theUser = theUser;
    this.thePort = thePort;
    this.thePassword = thePassword;
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

    // checks if the user formatting token exists, if not, an exception should be thrown
    if (!listOfTokens.contains(
        new Token(TypeOfToken.USER, FormattingStrings.FORMATTER_USER.getText()))) {
      throw new GrammarException(
          theLanguage.getText(
              "FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION",
              FormattingStrings.FORMATTER_USER.getFullTextWithEscape()));
    }

    // checks if the password formatting token exists, if not, an exception should be thrown
    if (!listOfTokens.contains(
        new Token(TypeOfToken.PASSWORD, FormattingStrings.FORMATTER_PASSWORD.getText()))) {
      throw new GrammarException(
          theLanguage.getText(
              "FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION",
              FormattingStrings.FORMATTER_PASSWORD.getFullTextWithEscape()));
    }

    // replace all host formmating
    setFormattingToken(listOfTokens, TypeOfToken.HOST, theHost);

    // replace all port formmating
    setFormattingToken(listOfTokens, TypeOfToken.PORT, thePort);

    // replace all user formmating
    setFormattingToken(listOfTokens, TypeOfToken.USER, theUser);

    // replace all password formmating
    setFormattingToken(listOfTokens, TypeOfToken.PASSWORD, thePassword);
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
