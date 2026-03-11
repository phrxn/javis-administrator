package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.io.parser.Token;
import com.quazzom.javis.administrator.io.parser.TokenStream;
import com.quazzom.javis.administrator.io.parser.Tokenizer;
import com.quazzom.javis.administrator.io.parser.TypeOfToken;

public class TokenizerTest_UnitTest {

  @Test
  public void testHandlerNullCharacter() {

    TokenStream tokenStream = new TokenStream("");

    Token token = new Tokenizer().handlerNullCharacter(tokenStream);

    Token toCompare = new Token(TypeOfToken.EOL, "");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  // -------------------------------------------------

  @Test
  public void testHandlerBackslashCharacter() {

    TokenStream tokenStream = new TokenStream("\\");

    Token token = new Tokenizer().handlerBackslashCharacter(tokenStream);

    Token toCompare = new Token(TypeOfToken.ESCAPE, "\\");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  // -------------------------------------------------

  @Test
  public void testHandlerSpace_oneSpace() {

    TokenStream tokenStream = new TokenStream(" ");

    Token token = new Tokenizer().handlerSpace(tokenStream);

    Token toCompare = new Token(TypeOfToken.SEPARADOR, " ");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  @Test
  public void testHandlerSpace_manySpaces() {

    TokenStream tokenStream = new TokenStream("  ");

    Token token = new Tokenizer().handlerSpace(tokenStream);

    Token toCompare = new Token(TypeOfToken.SEPARADOR, "  ");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  // -------------------------------------------------

  @Test
  public void testHandlerDoubleQuote() {

    TokenStream tokenStream = new TokenStream("\"");

    Token token = new Tokenizer().handlerDoubleQuote(tokenStream);

    Token toCompare = new Token(TypeOfToken.DOUBLE_QUOTE, "\"");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  // -------------------------------------------------

  @Test
  public void testHandlerWord_oneWord() {

    TokenStream tokenStream = new TokenStream("a");

    Token token = new Tokenizer().handlerWord(tokenStream);

    Token toCompare = new Token(TypeOfToken.WORD, "a");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  @Test
  public void testHandlerWord_manyWords() {

    TokenStream tokenStream = new TokenStream("ab");

    Token token = new Tokenizer().handlerWord(tokenStream);

    Token toCompare = new Token(TypeOfToken.WORD, "ab");

    assertEquals(toCompare, token);
    assertEquals('\0', tokenStream.getActualChar());
  }

  @Test
  public void testHandlerWord_getFormattingString() {

    TokenStream tokenStream = new TokenStream("host:port");

    Token token = new Tokenizer().handlerWord(tokenStream);

    Token toCompare = new Token(TypeOfToken.WORD, "host");

    assertEquals(toCompare, token);
    assertEquals(':', tokenStream.getActualChar());
  }

  // -------------------------------------------------

  @Test
  public void testCreateTokenList_emptyString() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("");
    List<Token> listOfTokenToCompare = new ArrayList<>();
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }

  @Test
  public void testCreateTokenList_word() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("foo");
    List<Token> listOfTokenToCompare = new ArrayList<>();
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "foo"));
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }

  @Test
  public void testCreateTokenList_wordSeparator() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("manga ");
    List<Token> listOfTokenToCompare = new ArrayList<>();

    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "manga"));
    listOfTokenToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }

  @Test
  public void testCreateTokenList_DoubleQuoteWordSeparatorDoubleQuote() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("\"manga \"");
    List<Token> listOfTokenToCompare = new ArrayList<>();
    listOfTokenToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, "\""));
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "manga"));
    listOfTokenToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listOfTokenToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, "\""));
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }

  @Test
  public void testCreateTokenList_allTypesOfTokes() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("\"manga \"\\\\ ");
    List<Token> listOfTokenToCompare = new ArrayList<>();
    listOfTokenToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, "\""));
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "manga"));
    listOfTokenToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listOfTokenToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, "\""));
    listOfTokenToCompare.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokenToCompare.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokenToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }

  @Test
  public void testCreateTokenList_splittingFormattingStrings() {

    Tokenizer tokenizer = new Tokenizer();

    List<Token> listOfToken = tokenizer.createTokenList("-connect \\host::\\port");
    List<Token> listOfTokenToCompare = new ArrayList<>();
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "-connect"));
    listOfTokenToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listOfTokenToCompare.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "host"));
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "::"));
    listOfTokenToCompare.add(new Token(TypeOfToken.ESCAPE, "\\"));
    listOfTokenToCompare.add(new Token(TypeOfToken.WORD, "port"));
    listOfTokenToCompare.add(new Token(TypeOfToken.EOL, ""));

    assertEquals(listOfTokenToCompare, listOfToken);
  }
}
