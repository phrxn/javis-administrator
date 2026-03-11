package unit.com.quazzom.javis.administrator.uncaught_exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.uncaught_exceptions.InformationAboutUncaughtException;
import com.quazzom.javis.administrator.uncaught_exceptions.OutputStreamUncaughtException;

public class OutputStreamUncaughtException_UnitTest {

  @Test
  public void testOutputStreamUncaughtException() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    InformationAboutUncaughtException iaue =
        new InformationAboutUncaughtException(
            "Program name test",
            "version test",
            "Thread name xxx",
            "Thread id 00",
            "aaaaaaaaaaaaaaaaa\nbbbbbbbbbbbbbb\ncccccccccccccccccccc\nddddddddddddddddddddddd");

    OutputStreamUncaughtException osue = new OutputStreamUncaughtException(outputStream);
    osue.showUncaughtException(iaue);

    assertEquals(
        "\n"
            + "Program: Program name test\n"
            + "Version: version test\n"
            + "Thread ID: Thread id 00\n"
            + "Thread name: Thread name xxx\n"
            + "Stack Trace: \n"
            + "\taaaaaaaaaaaaaaaaa\n"
            + "\tbbbbbbbbbbbbbb\n"
            + "\tcccccccccccccccccccc\n"
            + "\tddddddddddddddddddddddd\n"
            + "\n",
        outputStream.toString());
  }
}
