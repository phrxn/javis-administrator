package com.quazzom.javis.administrator.gui.controllers;

import java.awt.Dimension;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMakeLogin;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.internal_frame.LoginInternalFrame;

public class LoginInternalFrameController extends InternalFrameController {

  private LoginInternalFrame loginInternalFrame;
  private ControllersMediator controllersMediator;

  public LoginInternalFrameController(
      GeneralConfiguration generalConfiguration,
      SwingMediator swingMediator,
      ControllersMediator controllersMediator) {
    super(generalConfiguration, swingMediator);
    this.controllersMediator = controllersMediator;
  }

  public void leaveTheProgram() {
    controllersMediator.showExitProgram();
  }

  public void makeLogin(String user, String password) {
    SwingMakeLogin swingMakeLogin = new SwingMakeLogin(generalConfiguration, swingMediator);
    if (swingMakeLogin.makeLogin(user, password)) {
      controllersMediator.removeInternalFrame(loginInternalFrame);
      controllersMediator.showMainInternalFrame();
    }
  }

  @Override
  public void showInternalFrame() {

    if (swingMediator.doesTheInternalFrameAlreadyExist(loginInternalFrame)) return;

    loginInternalFrame = new LoginInternalFrame(this);

    Dimension centerOfScreen = swingMediator.getCenterOfScreenToInternalFrame(loginInternalFrame);

    loginInternalFrame.setLocation(
        (int) centerOfScreen.getWidth(), (int) centerOfScreen.getHeight());

    swingMediator.addInternalFrame(loginInternalFrame);
    loginInternalFrame.setFocusOnJTextFiedUsername();
  }
}
