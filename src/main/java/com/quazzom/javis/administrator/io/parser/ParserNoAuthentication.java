package com.quazzom.javis.administrator.io.parser;

public class ParserNoAuthentication extends Parser {

  public ParserNoAuthentication(String theHost, String thePort) {
    super(new FormatFormattingTokensNoAuthentication(theHost, thePort));
  }
}
