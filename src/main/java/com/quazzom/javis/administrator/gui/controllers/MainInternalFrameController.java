package com.quazzom.javis.administrator.gui.controllers;

import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.internal_frame.MainInternalFrame;

public class MainInternalFrameController {

  private MainInternalFrame mainInternalFrame;

  // --mediators
  private SwingMediator swingMediator;
  private ControllersMediator controllersMediator;

  public MainInternalFrameController(
      SwingMediator swingMediator, ControllersMediator controllersMediator) {
    this.swingMediator = swingMediator;
    this.controllersMediator = controllersMediator;
  }

  public void showMainInternalFrame() {

    if (swingMediator.doesTheInternalFrameAlreadyExist(mainInternalFrame)) return;

    MainInternalFrame mainInternalFrame = new MainInternalFrame(controllersMediator);
    swingMediator.addInternalFrame(mainInternalFrame);
  }
}
