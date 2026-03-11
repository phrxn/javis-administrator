package com.quazzom.javis.administrator.gui.controllers;

import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.internal_frame.RootInternalFrame;

public class ControllersMediator {

  private InternalFrameController vNCComputerListInternalFrameController;
  private InternalFrameController loginInternalFrameController;
  private MainInternalFrameController mainInternalFrameController;
  private ExitProgramController exitProgramController;
  private VNCConfigurationJDialogController vncConfigurationJDialogController;

  private SwingMediator swingMediator;

  public ControllersMediator(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    this.swingMediator = swingMediator;

    this.vNCComputerListInternalFrameController =
        new VNCComputerListInternalFrameController(generalConfiguration, swingMediator);
    this.loginInternalFrameController =
        new LoginInternalFrameController(generalConfiguration, swingMediator, this);
    this.mainInternalFrameController = new MainInternalFrameController(swingMediator, this);
    this.vncConfigurationJDialogController =
        new VNCConfigurationJDialogController(generalConfiguration, swingMediator);

    this.exitProgramController = new ExitProgramController(swingMediator);
  }

  public void showLoginInternalFrame() {
    loginInternalFrameController.showInternalFrame();
  }

  public void showVNCComputerListInternalFrame() {
    vNCComputerListInternalFrameController.showInternalFrame();
  }

  public void showVNCConfigurationJDialog() {
    vncConfigurationJDialogController.showVNCConfigurationJDialog();
  }

  public void showMainInternalFrame() {
    mainInternalFrameController.showMainInternalFrame();
  }

  public void showExitProgram() {
    exitProgramController.showExitProgram();
  }

  public void removeInternalFrame(RootInternalFrame rootInternalFrame) {
    swingMediator.removeInternalFrame(rootInternalFrame);
  }
}
