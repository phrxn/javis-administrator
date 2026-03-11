package unit.com.quazzom.javis.administrator.gui.document_filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.gui.document_filter.DocumentFilterInteger;

public class DocumentFilterIntegerTest_UnitTest {

  @Test
  public void testIsTheDocumentTextValid_underflow() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("-2147483649");

    assertFalse(isValid);
  }

  @Test
  public void testIsTheDocumentTextValid_minimumIntegerPossible() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("-2147483648");

    assertTrue(isValid);
  }

  @Test
  public void testIsTheDocumentTextValid_overflow() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("2147483648");

    assertFalse(isValid);
  }

  @Test
  public void testIsTheDocumentTextValid_maximumIntegerPossible() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("2147483647");

    assertTrue(isValid);
  }

  @Test
  public void testIsTheDocumentTextValid_zeroInteger() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("0");

    assertTrue(isValid);
  }

  @Test
  public void testIsTheDocumentTextValid_emptyString() {

    DocumentFilterInteger docInteger = new DocumentFilterInteger();

    boolean isValid = docInteger.isTheDocumentTextValid("");

    assertTrue(isValid);
  }
}
