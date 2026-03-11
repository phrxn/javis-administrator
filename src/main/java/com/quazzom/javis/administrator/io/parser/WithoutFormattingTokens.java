package com.quazzom.javis.administrator.io.parser;

import java.util.List;

/** class created to be used with command lines that do not need/have formatting tokens */
public class WithoutFormattingTokens implements FormatFormattingTokens {

  @Override
  public void replaceFormattingTokensByWords(List<Token> listOfTokens) throws GrammarException {}
}
