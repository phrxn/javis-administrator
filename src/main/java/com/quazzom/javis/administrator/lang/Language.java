package com.quazzom.javis.administrator.lang;

import java.util.Properties;

public class Language implements Text {

  private static final String COMMENT_AND_LANGUAGE_TEXT_SEPARATOR = "\t";

  protected Properties propertiesTexts;

  public Language() {
    this(new Properties());
  }

  public Language(Properties propertiesTexts) {
    this.propertiesTexts = propertiesTexts;
  }

  @Override
  public String getText(String key, Object... args) throws TextNotFoundException {
    if (!propertiesTexts.containsKey(key))
      throw new TextNotFoundException(String.format("The key %s doesn't exist", key));

    String languageText = removeCommentFromLanguageText(propertiesTexts.getProperty(key));

    return String.format(languageText, args);
  }

  public String removeCommentFromLanguageText(String value) {
    String theLanguageText = value;

    theLanguageText = theLanguageText.trim();

    if (value.contains(COMMENT_AND_LANGUAGE_TEXT_SEPARATOR)) {
      String[] commentAndLanguageText = value.split(COMMENT_AND_LANGUAGE_TEXT_SEPARATOR);
      theLanguageText = "";
      if (commentAndLanguageText.length == 2) theLanguageText = commentAndLanguageText[1];
    }

    return theLanguageText;
  }

  public void propertiesTexts(Properties propertiesTexts) {
    this.propertiesTexts = propertiesTexts;
  }
}
