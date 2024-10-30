package com.quazzom.javis.administrator.lang;

public class LanguageFactory {

  public enum LanguageFrom {
    FILE,
    MEMORY;
  }

  private static LanguageFrom languageFrom = LanguageFrom.MEMORY;
  private static LanguageIdiom languageType = LanguageIdiom.EN_US;

  private LanguageFactory() {}

  public static Language getLanguage(LanguagePathToFile languagePathToFile) {
    if (languageFrom == LanguageFrom.MEMORY) {
      return new LanguageInMemory(languagePathToFile);
    }
    return new LanguageFromFile(languageType, languagePathToFile);
  }

  public static void setLanguageFrom(LanguageFrom languageFrom) {
    LanguageFactory.languageFrom = languageFrom;
  }

  public static void setLanguageType(LanguageIdiom languageType) {
    LanguageFactory.languageType = languageType;
  }
}
