package com.quazzom.javis.administrator.gui.controllers;

import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;

public abstract class InternalFrameController {

  protected GeneralConfiguration generalConfiguration;
  protected SwingMediator swingMediator;

  public InternalFrameController(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    this.generalConfiguration = generalConfiguration;
    this.swingMediator = swingMediator;
  }

  public abstract void showInternalFrame();
}
