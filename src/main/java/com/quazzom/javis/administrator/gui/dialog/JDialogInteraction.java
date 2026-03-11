package com.quazzom.javis.administrator.gui.dialog;

import javax.swing.JFrame;
import com.quazzom.javis.administrator.gui.SwingCommons;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class JDialogInteraction {

  private SwingCommons swingCommons;
  private JFrame administratorFrame;
  private Text theLanguage;

  public JDialogInteraction(JFrame administratorFrame) {
    this.swingCommons = new SwingCommons();
    this.administratorFrame = administratorFrame;

    this.theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_INTERACTION);
  }

  public JDialogYesCancelOption showYesCancelOptions(String message) {

    String[] options = {theLanguage.getText("YES"), theLanguage.getText("CANCEL")};

    int theOption =
        swingCommons.showJDialogQuestion(
            administratorFrame,
            LanguageFactory.getLanguage(LanguagePathToFile.JDIALOG_QUESTIONS),
            message,
            options,
            1);

    if (theOption == 0) return JDialogYesCancelOption.YES;

    return JDialogYesCancelOption.CANCEL;
  }
}
