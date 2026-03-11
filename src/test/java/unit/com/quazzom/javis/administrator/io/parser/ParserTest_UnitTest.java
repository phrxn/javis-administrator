package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.Parser;
import com.quazzom.javis.administrator.io.parser.Token;
import com.quazzom.javis.administrator.io.parser.TypeOfToken;

public class ParserTest_UnitTest {

  // -----------------------------------------------------

  @Test
  public void testConvertTheTypeofTokensInsideDoubleQuoteToWord_onlyEOL() {

    Parser parser = new Parser();

    List<Token> listToConvertValues = new ArrayList<>();
    listToConvertValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.convertTheTypeofTokensInsideDoubleQuoteToWord(listToConvertValues);

    assertEquals(listToCompare, listToConvertValues);
  }

  @Test
  public void testConvertTheTypeofTokensInsideDoubleQuoteToWord_onlyDoubleQuotes() {

    Parser parser = new Parser();

    List<Token> listToConvertValues = new ArrayList<>();
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.convertTheTypeofTokensInsideDoubleQuoteToWord(listToConvertValues);

    assertEquals(listToCompare, listToConvertValues);
  }

  @Test
  public void testConvertTheTypeofTokensInsideDoubleQuoteToWord_separatorInsideDoubleQuotes() {

    Parser parser = new Parser();

    List<Token> listToConvertValues = new ArrayList<>();
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.WORD, " "));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.convertTheTypeofTokensInsideDoubleQuoteToWord(listToConvertValues);

    assertEquals(listToCompare, listToConvertValues);
  }

  @Test
  public void testConvertTheTypeofTokensInsideDoubleQuoteToWord_separatorOutsideDoubleQuotes() {

    Parser parser = new Parser();

    List<Token> listToConvertValues = new ArrayList<>();
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.convertTheTypeofTokensInsideDoubleQuoteToWord(listToConvertValues);

    assertEquals(listToCompare, listToConvertValues);
  }

  @Test
  public void
      testConvertTheTypeofTokensInsideDoubleQuoteToWord_separatorOutsideAndOutsideDoubleQuotes() {

    Parser parser = new Parser();

    List<Token> listToConvertValues = new ArrayList<>();
    listToConvertValues.add(new Token(TypeOfToken.WORD, "z"));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.WORD, "a"));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.WORD, "c"));
    listToConvertValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToConvertValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToConvertValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "z"));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.WORD, " "));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.WORD, "a"));
    listToCompare.add(new Token(TypeOfToken.WORD, " "));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.WORD, "c"));
    listToCompare.add(new Token(TypeOfToken.WORD, " "));
    listToCompare.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.convertTheTypeofTokensInsideDoubleQuoteToWord(listToConvertValues);

    assertEquals(listToCompare, listToConvertValues);
  }

  // -----------------------------------------------------

  @Test
  public void testRemoveDoubleQuotesFromList_onlyEOL() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  @Test
  public void testRemoveDoubleQuotesFromList_onlyDoubleQuotes() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  @Test
  public void testRemoveDoubleQuotesFromList_doubleWithAWordInside() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "foo"));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "foo"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  @Test
  public void testRemoveDoubleQuotesFromList_doubleWithTwoWordsInside() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "foo"));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "bar"));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "foo"));
    listToCompare.add(new Token(TypeOfToken.WORD, "bar"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  @Test
  public void testRemoveDoubleQuotesFromList_doubleWithTwoWordsInsideAndOutside() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "foo"));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "bar"));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "zzz"));
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "foo"));
    listToCompare.add(new Token(TypeOfToken.WORD, "bar"));
    listToCompare.add(new Token(TypeOfToken.WORD, "zzz"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  @Test
  public void testRemoveDoubleQuotesFromList_manyDoubleQuotesWordsAndSeparator() {

    Parser parser = new Parser();

    List<Token> listToRemoveValues = new ArrayList<>();
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "aaa"));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "bbb"));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "ccc"));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "ddd"));
    listToRemoveValues.add(new Token(TypeOfToken.DOUBLE_QUOTE, ""));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "eee"));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "fff"));
    listToRemoveValues.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToRemoveValues.add(new Token(TypeOfToken.WORD, "ggg"));
    listToRemoveValues.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "aaa"));
    listToCompare.add(new Token(TypeOfToken.WORD, "bbb"));
    listToCompare.add(new Token(TypeOfToken.WORD, "ccc"));
    listToCompare.add(new Token(TypeOfToken.WORD, "ddd"));
    listToCompare.add(new Token(TypeOfToken.WORD, "eee"));
    listToCompare.add(new Token(TypeOfToken.WORD, "fff"));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.WORD, "ggg"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.removeDoubleQuotesFromList(listToRemoveValues);

    assertEquals(listToCompare, listToRemoveValues);
  }

  // -----------------------------------------------------

  @Test
  public void testJoinTokensOfTheWordType_onlyEOL() {
    Parser parser = new Parser();

    List<Token> listToJoinWords = new ArrayList<>();
    listToJoinWords.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.joinTokensOfTheWordType(listToJoinWords);

    assertEquals(listToCompare, listToJoinWords);
  }

  @Test
  public void testJoinTokensOfTheWordType_oneWordAndEOL() {
    Parser parser = new Parser();

    List<Token> listToJoinWords = new ArrayList<>();
    listToJoinWords.add(new Token(TypeOfToken.WORD, "ab"));
    listToJoinWords.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "ab"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    parser.joinTokensOfTheWordType(listToJoinWords);

    assertEquals(listToCompare, listToJoinWords);
  }

  @Test
  public void testJoinTokensOfTheWordType_twoWordsAndEOL() {
    Parser parser = new Parser();

    List<Token> listToJoinWords = new ArrayList<>();
    listToJoinWords.add(new Token(TypeOfToken.WORD, "ab"));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "cd"));
    listToJoinWords.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "abcd"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    listToJoinWords = parser.joinTokensOfTheWordType(listToJoinWords);

    assertEquals(listToCompare, listToJoinWords);
  }

  @Test
  public void testJoinTokensOfTheWordType_manyWords() {
    Parser parser = new Parser();

    List<Token> listToJoinWords = new ArrayList<>();
    listToJoinWords.add(new Token(TypeOfToken.WORD, "ab"));
    listToJoinWords.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "cd"));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "ef"));
    listToJoinWords.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "gh"));
    listToJoinWords.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "ij"));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "kl"));
    listToJoinWords.add(new Token(TypeOfToken.WORD, "mn"));
    listToJoinWords.add(new Token(TypeOfToken.EOL, ""));

    List<Token> listToCompare = new ArrayList<>();
    listToCompare.add(new Token(TypeOfToken.WORD, "ab"));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.WORD, "cdef"));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.WORD, "gh"));
    listToCompare.add(new Token(TypeOfToken.SEPARADOR, " "));
    listToCompare.add(new Token(TypeOfToken.WORD, "ijklmn"));
    listToCompare.add(new Token(TypeOfToken.EOL, ""));

    listToJoinWords = parser.joinTokensOfTheWordType(listToJoinWords);

    assertEquals(listToCompare, listToJoinWords);
  }

  // -----------------------------------------------------

  @Test
  public void testCreateCommandLine_emptyString() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine = parser.createCommandLine("");

    List<String> listToCompare = new ArrayList<String>();

    assertEquals(listToCompare, listCommandLine);
  }

  @Test
  public void testCreateCommandLine_oneWord() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine = parser.createCommandLine("abc");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add("abc");

    assertEquals(listToCompare, listCommandLine);
  }

  @Test
  public void testCreateCommandLine_twoDoubleQuotes() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine = parser.createCommandLine("\"\"");

    List<String> listToCompare = new ArrayList<String>();

    assertEquals(listToCompare, listCommandLine);
  }

  @Test
  public void testreateCommandLine_twoWords() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine = parser.createCommandLine("\"\" exe param");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add("exe");
    listToCompare.add("param");

    assertEquals(listToCompare, listCommandLine);
  }

  @Test
  public void testCreateCommandLine_twoWordsBetwenDoubleQuotes() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine = parser.createCommandLine("\"exe param\"");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add("exe param");

    assertEquals(listToCompare, listCommandLine);
  }

  @Test
  public void testCreateCommandLine_complexCommandLine() throws GrammarException {

    Parser parser = new Parser();

    List<String> listCommandLine =
        parser.createCommandLine(
            "\"\" \" exe name\" \\\\\\\"param \\\\ 123 xyz      \"\\\\\\\"hi \\\"\"  \"zz \\\" \\\\\\\" xx\"");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add(" exe name");
    listToCompare.add("\\\"param");
    listToCompare.add("\\");
    listToCompare.add("123");
    listToCompare.add("xyz");
    listToCompare.add("\\\"hi \"");
    listToCompare.add("zz \" \\\" xx");
    assertEquals(listToCompare, listCommandLine);
  }
}
