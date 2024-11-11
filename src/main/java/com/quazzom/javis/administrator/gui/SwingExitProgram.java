package com.quazzom.javis.administrator.gui;

import javax.swing.JFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class SwingExitProgram {

  private SwingCommons swingCommons;
  private JFrame jFrameAdministrator;
  private Text theLanguage;

  public SwingExitProgram(JFrame jFrameAdministrator) {
    this.jFrameAdministrator = jFrameAdministrator;
    swingCommons = new SwingCommons();
    theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.SWING_EXIT_PROGRAM);
  }

  public void closeTheProgram() {

    String[] options = {theLanguage.getText("YES"), theLanguage.getText("CANCEL")};

    int theOption =
        swingCommons.showJDialogQuestion(
            jFrameAdministrator,
            LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_QUESTIONS),
            theLanguage.getText("WISH_EXIT"),
            options,
            1);

    if (theOption == 0) System.exit(0);
  }
}
