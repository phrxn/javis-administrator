package com.quazzom.javis.administrator.lang;

import java.util.Properties;

public class LanguageFromFile extends Language {

  public static final String FOLDER_TO_LANGUAGES = "lang/";

  private CreatePropertiesToLanguage createPropertiesToLanguage;

  public LanguageFromFile(LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile) {
    loadPropertiesFromFile(languageIdiom, languagePathToFile);
  }

  public LanguageFromFile(Properties propertiesTexts) {
    super(propertiesTexts);
  }

  public void loadPropertiesFromFile(
      LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile) {

    createPropertiesToLanguage =
        new CreatePropertiesToLanguageFromXMLFile(languageIdiom, languagePathToFile);

    try {
      propertiesTexts = createPropertiesToLanguage.createProperties();
    } catch (CreatePropertiesToLanguageException e) {
      throw new RuntimeException(e);
    }
  }
}
