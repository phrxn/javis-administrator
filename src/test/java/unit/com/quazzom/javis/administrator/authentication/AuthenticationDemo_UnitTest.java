package unit.com.quazzom.javis.administrator.authentication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.authentication.AuthenticationDemo;
import com.quazzom.javis.administrator.authentication.AuthenticationException;
import com.quazzom.javis.administrator.lang.Text;

public class AuthenticationDemo_UnitTest {

  @Test
  public void makeLogin_validUserAndValidPassword() {
    Text text = Mockito.mock(Text.class);

    when(text.getText(anyString())).thenReturn("xxx");

    AuthenticationDemo ad = new AuthenticationDemo(text);

    assertDoesNotThrow(
        () -> {
          assertTrue(ad.makeLogin("root", "toor"));
        });
  }

  @Test
  public void makeLogin_validUserAndInvalidPassword() {
    Text text = Mockito.mock(Text.class);

    when(text.getText(anyString())).thenReturn("xxx");

    AuthenticationDemo ad = new AuthenticationDemo(text);

    AuthenticationException ae =
        assertThrows(
            AuthenticationException.class,
            () -> {
              ad.makeLogin("root", "xxxxxx");
            });
    assertEquals("xxx", ae.getMessage());
  }

  @Test
  public void makeLogin_invalidUserAndValidPassword() {
    Text text = Mockito.mock(Text.class);

    when(text.getText(anyString())).thenReturn("xxx");

    AuthenticationDemo ad = new AuthenticationDemo(text);

    AuthenticationException ae =
        assertThrows(
            AuthenticationException.class,
            () -> {
              ad.makeLogin("rootxxx", "toor");
            });
    assertEquals("xxx", ae.getMessage());
  }

  @Test
  public void makeLogin_invalidUserAndInvalidPassword() {
    Text text = Mockito.mock(Text.class);

    when(text.getText(anyString())).thenReturn("xxx");

    AuthenticationDemo ad = new AuthenticationDemo(text);

    AuthenticationException ae =
        assertThrows(
            AuthenticationException.class,
            () -> {
              ad.makeLogin("xxxxroot", "xxxxxx");
            });
    assertEquals("xxx", ae.getMessage());
  }
}
