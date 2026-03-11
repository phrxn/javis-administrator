package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.io.parser.FormatFormattingTokensVNCAuthentication;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Token;
import com.quazzom.javis.administrator.io.parser.TypeOfToken;
import com.quazzom.javis.administrator.lang.Text;

public class FormatFormattingTokensVNCAuthenticationTest_UnitTest {

  @Test
  public void testReplaceFormattingTokensByWords_listWithoutHost() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION", "\\host"))
        .thenReturn("x");

    FormatFormattingTokensVNCAuthentication ffta =
        new FormatFormattingTokensVNCAuthentication("", "", "", theLanguage);

    List<Token> listOfTokensFormat = new ArrayList<Token>();
    listOfTokensFormat.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensFormat.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensFormat.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gExc =
        assertThrows(
            GrammarException.class,
            () -> {
              ffta.replaceFormattingTokensByWords(listOfTokensFormat);
            });
    assertEquals("x", gExc.getMessage());
  }

  // -------------------------------------------------

  @Test
  public void testReplaceFormattingTokensByWords_listWithoutPort() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION", "\\port"))
        .thenReturn("x");

    FormatFormattingTokensVNCAuthentication ffta =
        new FormatFormattingTokensVNCAuthentication("", "", "", theLanguage);

    List<Token> listOfTokensFormat = new ArrayList<Token>();
    listOfTokensFormat.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensFormat.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensFormat.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gExc =
        assertThrows(
            GrammarException.class,
            () -> {
              ffta.replaceFormattingTokensByWords(listOfTokensFormat);
            });
    assertEquals("x", gExc.getMessage());
  }

  // -------------------------------------------------

  @Test
  public void testReplaceFormattingTokensByWords_listWithoutPassoword() {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION", "\\password"))
        .thenReturn("x");

    FormatFormattingTokensVNCAuthentication ffta =
        new FormatFormattingTokensVNCAuthentication("", "", "", theLanguage);

    List<Token> listOfTokensFormat = new ArrayList<Token>();
    listOfTokensFormat.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensFormat.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensFormat.add(new Token(TypeOfToken.EOL, ""));

    GrammarException gExc =
        assertThrows(
            GrammarException.class,
            () -> {
              ffta.replaceFormattingTokensByWords(listOfTokensFormat);
            });
    assertEquals("x", gExc.getMessage());
  }

  // -------------------------------------------------

  @Test
  public void testReplaceFormattingTokensByWords_replaceOneTokenEach() throws GrammarException {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION", "\\port"))
        .thenReturn("x");

    FormatFormattingTokensVNCAuthentication ffta =
        new FormatFormattingTokensVNCAuthentication("zzzz", "9090", "123!132", theLanguage);

    List<Token> listOfTokensFormat = new ArrayList<Token>();
    listOfTokensFormat.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensFormat.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensFormat.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensFormat.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<Token>();
    listToCompare.add(new Token(TypeOfToken.WORD, "zzzz"));
    listToCompare.add(new Token(TypeOfToken.WORD, "9090"));
    listToCompare.add(new Token(TypeOfToken.WORD, "123!132"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    ffta.replaceFormattingTokensByWords(listOfTokensFormat);

    assertEquals(listToCompare, listOfTokensFormat);
  }

  @Test
  public void testReplaceFormattingTokensByWords_replaceFormattingTokensOneTokenEach()
      throws GrammarException {

    Text theLanguage = Mockito.mock(Text.class);
    when(theLanguage.getText("FORMATTING_TOKEN_FOMATTER_MISSING_EXCEPTION", "\\port"))
        .thenReturn("x");

    FormatFormattingTokensVNCAuthentication ffta =
        new FormatFormattingTokensVNCAuthentication("zzzz", "9090", "123!132", theLanguage);

    List<Token> listOfTokensFormat = new ArrayList<Token>();
    listOfTokensFormat.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensFormat.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensFormat.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensFormat.add(new Token(TypeOfToken.PORT, "port"));
    listOfTokensFormat.add(new Token(TypeOfToken.HOST, "host"));
    listOfTokensFormat.add(new Token(TypeOfToken.PASSWORD, "password"));
    listOfTokensFormat.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<Token>();
    listToCompare.add(new Token(TypeOfToken.WORD, "123!132"));
    listToCompare.add(new Token(TypeOfToken.WORD, "zzzz"));
    listToCompare.add(new Token(TypeOfToken.WORD, "9090"));
    listToCompare.add(new Token(TypeOfToken.WORD, "9090"));
    listToCompare.add(new Token(TypeOfToken.WORD, "zzzz"));
    listToCompare.add(new Token(TypeOfToken.WORD, "123!132"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    ffta.replaceFormattingTokensByWords(listOfTokensFormat);

    assertEquals(listToCompare, listOfTokensFormat);
  }
}
