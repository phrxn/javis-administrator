package com.quazzom.javis.administrator.lang;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LanguageFromFile extends Language {

  private static final String FOLDER_TO_LANGUAGES = "lang/";

  public LanguageFromFile(LanguageIdiom languageType, LanguagePathToFile languagePathToFile) {
    loadPropertiesFromFile(languageType, languagePathToFile);
  }

  public LanguageFromFile(Properties propertiesTexts) {
    super(propertiesTexts);
  }

  public void loadPropertiesFromFile(
      LanguageIdiom languageType, LanguagePathToFile languagePathToFile) {

    String pathToLanguageFile = createPathToLanguageFile(languageType, languagePathToFile);

    try {
      FileInputStream fileLanguage = new FileInputStream(pathToLanguageFile);
      propertiesTexts.load(fileLanguage);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String createPathToLanguageFile(
      LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile) {
    StringBuilder sb = new StringBuilder();
    sb.append(FOLDER_TO_LANGUAGES)
        .append(languageIdiom.getValue())
        .append("/")
        .append(languagePathToFile.getPath());

    return sb.toString();
  }
}
