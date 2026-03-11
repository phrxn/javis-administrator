package com.quazzom.javis.administrator.authentication;

import java.util.HashMap;
import java.util.Map;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class AuthenticationDemo implements Authentication {

  private static final Map<String, String> mapCredentials = new HashMap<>();
  private Text language;

  static {
    mapCredentials.put("root", "toor");
  }

  public AuthenticationDemo() {
    this(LanguageFactory.getLanguage(LanguagePathToFile.AUTHENTICATION_DEMO));
  }

  public AuthenticationDemo(Text text) {
    this.language = text;
  }

  @Override
  public boolean makeLogin(String user, String password) throws AuthenticationException {

    String theLocalUserPassword = mapCredentials.get(user);
    if (theLocalUserPassword == null) throwInvalidUserOrPassword();
    if (!theLocalUserPassword.equals(password)) throwInvalidUserOrPassword();
    return true;
  }

  private void throwInvalidUserOrPassword() throws AuthenticationException {
    throw new AuthenticationException(language.getText("ERROR_INVALID_USER_OR_PASSWORD"));
  }
}
