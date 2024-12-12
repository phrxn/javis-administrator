package unit.com.quazzom.javis.administrator.io.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.io.parser.GrammarException;
import com.quazzom.javis.administrator.io.parser.ParserUltraVNCMslogonAuthentication;

public class ParserUltraVNCMslogonAuthenticationTest_UnitTest {

  @Test
  public void testCreateCommandLine() throws GrammarException {

    ParserUltraVNCMslogonAuthentication parser =
        new ParserUltraVNCMslogonAuthentication("server", "8080", "mary", "123@123");

    List<String> listCommandLine =
        parser.createCommandLine(
            "exe.exe \\host \\port \\user \\password \\\\host \\\\port \\\\user \\\\password \"foo bar\" \\\"");

    List<String> listToCompare = new ArrayList<String>();
    listToCompare.add("exe.exe");
    listToCompare.add("server");
    listToCompare.add("8080");
    listToCompare.add("mary");
    listToCompare.add("123@123");
    listToCompare.add("\\host");
    listToCompare.add("\\port");
    listToCompare.add("\\user");
    listToCompare.add("\\password");
    listToCompare.add("foo bar");
    listToCompare.add("\"");

    assertEquals(listToCompare, listCommandLine);
  }
}
