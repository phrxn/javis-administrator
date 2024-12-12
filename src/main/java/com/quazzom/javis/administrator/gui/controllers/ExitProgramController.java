package com.quazzom.javis.administrator.gui.controllers;

import com.quazzom.javis.administrator.gui.SwingExitProgram;
import com.quazzom.javis.administrator.gui.SwingMediator;

public class ExitProgramController {

  private SwingMediator swingMediator;

  public ExitProgramController(SwingMediator swingMediator) {
    this.swingMediator = swingMediator;
  }

  public void showExitProgram() {
    SwingExitProgram swingExitProgram = new SwingExitProgram(swingMediator);
    swingExitProgram.closeTheProgram();
  }
}
