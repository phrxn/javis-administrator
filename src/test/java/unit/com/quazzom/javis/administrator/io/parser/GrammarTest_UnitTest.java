package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.io.parser.Grammar;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Token;
import com.quazzom.javis.administrator.io.parser.TypeOfToken;
import com.quazzom.javis.administrator.lang.Text;

public class GrammarTest_UnitTest {

  @Test
  public void testTheListContainsOpenDoubleQuotes_notDoubleQuote() {

    Grammar grammar = new Grammar(null);

    List<Token> listToCheck = new ArrayList<>();
    listToCheck.add(new Token(TypeOfToken.EOL, ""));

    boolean thereIsOpenDoubleQuotes = grammar.theListContainsOpenDoubleQuotes(listToCheck);

    assertFalse(thereIsOpenDoubleQuotes);
  }

  @Test
  public void testTheListContainsOpenDoubleQuotes_oneDoubleQuote() {

    Grammar grammar = new Grammar(null);

    List<Token> listToCheck = new ArrayList<>();
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.EOL, ""));

    boolean thereIsOpenDoubleQuotes = grammar.theListContainsOpenDoubleQuotes(listToCheck);

    assertTrue(thereIsOpenDoubleQuotes);
  }

  @Test
  public void testTheListContainsOpenDoubleQuotes_twoDoubleQuote() {

    Grammar grammar = new Grammar(null);

    List<Token> listToCheck = new ArrayList<>();
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.WORD, ""));
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.EOL, ""));

    boolean thereIsOpenDoubleQuotes = grammar.theListContainsOpenDoubleQuotes(listToCheck);

    assertFalse(thereIsOpenDoubleQuotes);
  }

  @Test
  public void testTheListContainsOpenDoubleQuotes_treeDoubleQuote() {

    Grammar grammar = new Grammar(null);

    List<Token> listToCheck = new ArrayList<>();
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.WORD, ""));
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.WORD, ""));
    listToCheck.add(new Token(TypeOfToken.WORD, ""));
    listToCheck.add(new Token(TypeOfToken.WORD, ""));
    listToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCheck.add(new Token(TypeOfToken.EOL, ""));

    boolean thereIsOpenDoubleQuotes = grammar.theListContainsOpenDoubleQuotes(listToCheck);

    assertTrue(thereIsOpenDoubleQuotes);
  }

  @Test
  public void escapeSpecialTokens_eolAfterEscape() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("ESCAPE_AT_THE_END_OF_THE_LINE_EXCEPTION")).thenReturn("x");

    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, ""));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gEx =
        assertThrows(
            GrammarException.class,
            () -> {
              grammar.escapeSpecialTokens(listOfTokensToCheck);
            });
    assertEquals("x", gEx.getMessage());
  }

  @Test
  public void escapeSpecialTokens_separatorAfterEscape() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("SEPARATOR_AFTER_ESCAPE_EXCEPTION")).thenReturn("x");

    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, ""));
    listOfTokensToCheck.add(new Token(TypeOfToken.SEPARADOR, "   "));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gEx =
        assertThrows(
            GrammarException.class,
            () -> {
              grammar.escapeSpecialTokens(listOfTokensToCheck);
            });
    assertEquals("x", gEx.getMessage());
  }

  @Test
  public void escapeSpecialTokens_invalidWordWithSizeOneAfterEscape() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("INVALID_CHARACTER_TO_ESCAPE_EXCEPTION", 'z')).thenReturn("x");

    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, ""));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "z"));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gEx =
        assertThrows(
            GrammarException.class,
            () -> {
              grammar.escapeSpecialTokens(listOfTokensToCheck);
            });
    assertEquals("x", gEx.getMessage());
  }

  @Test
  public void escapeSpecialTokens_invalidWordAfterEscape() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("INVALID_WORD_TO_ESCAPE_EXCEPTION", "abcd")).thenReturn("x");

    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, ""));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "abcd"));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gEx =
        assertThrows(
            GrammarException.class,
            () -> {
              grammar.escapeSpecialTokens(listOfTokensToCheck);
            });
    assertEquals("x", gEx.getMessage());
  }

  @Test
  public void escapeSpecialTokens_escapeFormattingString() throws GrammarException {
    Text theLanguage = Mockito.mock(Text.class);
    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "host"));
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "port"));
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "user"));
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.WORD, "password"));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listOfTokensToCompare = new ArrayList<Token>();
    listOfTokensToCompare.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensToCompare.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensToCompare.add(new Token(TypeOfToken.USER, "user"));
    listOfTokensToCompare.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensToCompare.add(new Token(TypeOfToken.EOL, ""));

    grammar.escapeSpecialTokens(listOfTokensToCheck);

    assertEquals(listOfTokensToCompare, listOfTokensToCheck);
  }

  @Test
  public void escapeSpecialTokens_escapeAnEscape() throws GrammarException {
    Text theLanguage = Mockito.mock(Text.class);
    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listOfTokensToCompare = new ArrayList<Token>();
    listOfTokensToCompare.add(new Token(TypeOfToken.WORD, "\\"));
    listOfTokensToCompare.add(new Token(TypeOfToken.EOL, ""));

    grammar.escapeSpecialTokens(listOfTokensToCheck);

    assertEquals(listOfTokensToCompare, listOfTokensToCheck);
  }

  @Test
  public void escapeSpecialTokens_escapeAnDoubleQuote() throws GrammarException {

    Text theLanguage = Mockito.mock(Text.class);
    Grammar grammar = new Grammar(theLanguage, null);

    List<Token> listOfTokensToCheck = new ArrayList<Token>();
    listOfTokensToCheck.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokensToCheck.add(new Token(TypeOfToken.DOUBLE_QUOTE, "\""));
    listOfTokensToCheck.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listOfTokensToCompare = new ArrayList<Token>();
    listOfTokensToCompare.add(new Token(TypeOfToken.WORD, "\""));
    listOfTokensToCompare.add(new Token(TypeOfToken.EOL, ""));

    grammar.escapeSpecialTokens(listOfTokensToCheck);

    assertEquals(listOfTokensToCompare, listOfTokensToCheck);
  }
}
