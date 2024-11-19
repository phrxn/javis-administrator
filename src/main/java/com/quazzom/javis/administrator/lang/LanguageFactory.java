package com.quazzom.javis.administrator.lang;

import java.util.HashMap;

public class LanguageFactory {

  public enum LanguageFrom {
    FILE,
    MEMORY;
  }

  private static LanguageFrom languageFrom = LanguageFrom.MEMORY;
  private static LanguageIdiom languageType = LanguageIdiom.EN_US;

  private static HashMap<LanguagePathToFile, Language> mapLanguage =
      new HashMap<LanguagePathToFile, Language>();

  private LanguageFactory() {}

  public static Language getLanguage(LanguagePathToFile languagePathToFile) {

    Language language;

    if (mapLanguage.containsKey(languagePathToFile)) {
      return mapLanguage.get(languagePathToFile);
    }

    if (languageFrom == LanguageFrom.MEMORY) {
      language = new LanguageInMemory(languagePathToFile);
    } else {
      language = new LanguageFromFile(languageType, languagePathToFile);
    }
    mapLanguage.put(languagePathToFile, language);
    return language;
  }

  public static void setLanguageFrom(LanguageFrom languageFrom) {
    LanguageFactory.languageFrom = languageFrom;
  }

  public static void setLanguageIdiom(LanguageIdiom languageType) {
    LanguageFactory.languageType = languageType;
  }
}
