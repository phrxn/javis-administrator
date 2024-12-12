package com.quazzom.javis.administrator.io.parser;

public class ParserVNCAuthentication extends Parser {

  public ParserVNCAuthentication(String theHost, String thePort, String thePassword) {
    super(new FormatFormattingTokensVNCAuthentication(theHost, thePort, thePassword));
  }
}
