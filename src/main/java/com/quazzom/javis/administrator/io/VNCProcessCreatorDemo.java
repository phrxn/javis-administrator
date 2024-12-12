package com.quazzom.javis.administrator.io;

import java.util.List;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class VNCProcessCreatorDemo implements VNCProcessCreator {

  private SwingMediator swingMediator;
  private Text theLanguage;
  private List<String> parametersToExecution;

  public VNCProcessCreatorDemo(SwingMediator swingMediator, List<String> parametersToExecution) {
    this(
        swingMediator,
        parametersToExecution,
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_PROCESS_CREATOR_DEMO));
  }

  public VNCProcessCreatorDemo(
      SwingMediator swingMediator, List<String> parametersToExecution, Text theLanguage) {
    this.swingMediator = swingMediator;
    this.parametersToExecution = parametersToExecution;
    this.theLanguage = theLanguage;
  }

  @Override
  public void executeVNCProcess() {
    swingMediator.showMessageToUser(
        JDialogType.INFO,
        theLanguage.getText("THE_COMMAND_LINE"),
        parametersToExecution.toString());
  }
}
