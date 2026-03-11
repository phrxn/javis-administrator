package com.quazzom.javis.administrator.authentication;

import com.quazzom.javis.administrator.configuration.GeneralConfiguration;

public class AuthenticationController {

  private GeneralConfiguration generalConfiguration;
  private AuthenticationView authenticationView;
  private Authentication authentication;

  public AuthenticationController(
      GeneralConfiguration configuration, AuthenticationView authenticationView) {
    this.generalConfiguration = configuration;
    this.authenticationView = authenticationView;
    setTheAuthentication();
  }

  public boolean makeLogin(String user, String password) {

    try {
      authentication.makeLogin(user, password);
      return true;
    } catch (AuthenticationException e) {
      authenticationView.showErrorAuthentication(e.getMessage());
    }
    return false;
  }

  private void setTheAuthentication() {
    if (generalConfiguration.getExecutionMode() == GeneralConfiguration.ExecutionModeOptions.DEMO)
      authentication = new AuthenticationDemo();
    authentication = new AuthenticationDemo();
  }
}
