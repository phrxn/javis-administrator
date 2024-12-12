package com.quazzom.javis.administrator.io.parser;

public class ParserUltraVNCMslogonAuthentication extends Parser {

  public ParserUltraVNCMslogonAuthentication(
      String theHost, String thePort, String theUser, String thePassword) {
    super(
        new FormatFormattingTokensUltraVNCMslogonAuthentication(
            theHost, thePort, theUser, thePassword));
  }
}
