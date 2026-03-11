package com.quazzom.javis.administrator.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDialogUncaughtExceptionLanguage extends LanguageFromFile {

  private static List<String> allValidKeys;

  static {
    allValidKeys = new ArrayList<>();

    allValidKeys.add("JBUTTON_CLOSE");
    allValidKeys.add("JLABEL_INFORMATION");
    allValidKeys.add("JLABEL_TITLE_H1");
    allValidKeys.add("JLABEL_PROGRAM_NAME_INFO");
    allValidKeys.add("JLABEL_PROGRAM_NAME");
    allValidKeys.add("JLABEL_VERSION_INFO");
    allValidKeys.add("JLABEL_VERSION");
    allValidKeys.add("JLABEL_THREAD_ID_INFO");
    allValidKeys.add("JLABEL_THREAD_ID");
    allValidKeys.add("JLABEL_THREAD_NAME_INFO");
    allValidKeys.add("JLABEL_THREAD_NAME");
  }

  public JDialogUncaughtExceptionLanguage(Properties propertiesLanguage) throws TextKeyDoesntExist {
    super(propertiesLanguage);
    super.containsAllTheseKeys(allValidKeys);
  }

  public JDialogUncaughtExceptionLanguage(
      LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile)
      throws TextKeyDoesntExist {
    super(languageIdiom, languagePathToFile);
    super.containsAllTheseKeys(allValidKeys);
  }
}
