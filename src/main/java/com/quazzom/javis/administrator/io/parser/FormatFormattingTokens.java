package com.quazzom.javis.administrator.io.parser;

import java.util.List;

public interface FormatFormattingTokens {

  /**
   * The implementer of this method must replace the formatting tokens with their texts, and must
   * also change the token type to word. If the list does not contain the expected formatting token
   * or an error occurs, a xxx error must be thrown.
   *
   * @param listOfTokens
   * @throws GrammarException if an error occurs or the list does not contain an expected formatting
   *     token
   */
  void replaceFormattingTokensByWords(List<Token> listOfTokens) throws GrammarException;
}
