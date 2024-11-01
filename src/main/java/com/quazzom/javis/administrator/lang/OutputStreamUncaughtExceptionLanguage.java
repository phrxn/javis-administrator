package com.quazzom.javis.administrator.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OutputStreamUncaughtExceptionLanguage extends LanguageFromFile {

  private static List<String> allValidKeys;

  static {
    allValidKeys = new ArrayList<>();

    allValidKeys.add("PROGRAM");
    allValidKeys.add("VERSION");
    allValidKeys.add("THREAD_ID");
    allValidKeys.add("THREAD_NAME");
    allValidKeys.add("STACK_TRACE");
  }

  public OutputStreamUncaughtExceptionLanguage(Properties propertiesLanguage)
      throws TextKeyDoesntExist {
    super(propertiesLanguage);
    super.containsAllTheseKeys(allValidKeys);
  }

  public OutputStreamUncaughtExceptionLanguage(
      LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile)
      throws TextKeyDoesntExist {
    super(languageIdiom, languagePathToFile);
    super.containsAllTheseKeys(allValidKeys);
  }
}
