package com.quazzom.javis.administrator.gui;

import com.quazzom.javis.administrator.authentication.AuthenticationController;
import com.quazzom.javis.administrator.authentication.AuthenticationView;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.Language;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;

public class SwingMakeLogin implements AuthenticationView {

  private AuthenticationController authenticationController;
  private SwingMediator swingMediator;
  private Language theLanguage;

  public SwingMakeLogin(GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    this.authenticationController = new AuthenticationController(generalConfiguration, this);
    this.swingMediator = swingMediator;
    this.theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.SWING_MAKE_LOGIN);
  }

  public boolean makeLogin(String user, String password) {
    return authenticationController.makeLogin(user, password);
  }

  @Override
  public void showErrorAuthentication(String errorMessage) {
    swingMediator.showMessageToUser(
        JDialogType.ERROR, theLanguage.getText("NOT_POSSIBLE_MAKE_LOGIN"), errorMessage);
  }
}
