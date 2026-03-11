package com.quazzom.javis.administrator.gui;

import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class SwingExitProgram {

  private Text theLanguage;
  private SwingMediator swingMediator;

  public SwingExitProgram(SwingMediator swingMediator) {
    this.swingMediator = swingMediator;
    theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.SWING_EXIT_PROGRAM);
  }

  public void closeTheProgram() {

    JDialogYesCancelOption chosenOption =
        swingMediator.showYesCancelOptions(theLanguage.getText("WISH_EXIT"));

    if (chosenOption != JDialogYesCancelOption.YES) return;

    System.exit(0);
  }
}
