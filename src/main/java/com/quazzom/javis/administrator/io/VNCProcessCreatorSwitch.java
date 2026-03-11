package com.quazzom.javis.administrator.io;

import java.util.List;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration.ExecutionModeOptions;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCProcessCreatorSwitch implements VNCProcessCreator {

  private VNCProcessCreator vncProcessCreator;

  public VNCProcessCreatorSwitch(
      GeneralConfiguration generalConfiguration,
      List<String> parametersToExecution,
      VNCProgramConfiguration vncProgramConfiguration,
      SwingMediator swingMediator) {

    if (generalConfiguration.getExecutionMode() == ExecutionModeOptions.DEMO) {
      vncProcessCreator = new VNCProcessCreatorDemo(swingMediator, parametersToExecution);
    } else {
      vncProcessCreator =
          new VNCProcessCreatorInSystem(
              parametersToExecution, vncProgramConfiguration, swingMediator);
    }
  }

  @Override
  public void executeVNCProcess() {
    vncProcessCreator.executeVNCProcess();
  }
}
