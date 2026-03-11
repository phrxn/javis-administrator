package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.ParserNoAuthentication;

public class ParserNoAuthenticationTest_UnitTest {

  @Test
  public void testCreateCommandLine() throws GrammarException {

    ParserNoAuthentication parser = new ParserNoAuthentication("server", "8080");

    List<String> listCommandLine =
        parser.createCommandLine("exe.exe \\host \\port \\\\host \\\\port \"foo bar\" \\\"");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add("exe.exe");
    listToCompare.add("server");
    listToCompare.add("8080");
    listToCompare.add("\\host");
    listToCompare.add("\\port");
    listToCompare.add("foo bar");
    listToCompare.add("\"");

    assertEquals(listToCompare, listCommandLine);
  }
}
