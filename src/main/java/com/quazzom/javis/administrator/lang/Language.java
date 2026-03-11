package com.quazzom.javis.administrator.lang;

import java.util.List;
import java.util.Properties;

public class Language implements Text {

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
      throw new TextNotFoundException(createTextKeyDoesntExist(key));

    String languageText = propertiesTexts.getProperty(key);

    return String.format(languageText, args);
  }

  public void propertiesTexts(Properties propertiesTexts) {
    this.propertiesTexts = propertiesTexts;
  }

  @Override
  public boolean containsKey(String key) {
    return this.propertiesTexts.containsKey(key);
  }

  public void containsAllTheseKeys(List<String> listKeys) throws TextKeyDoesntExist {
    for (String key : listKeys) {
      if (!containsKey(key)) throw new TextKeyDoesntExist(createTextKeyDoesntExist(key));
    }
  }

  private String createTextKeyDoesntExist(String key) {
    return String.format("The key %s doesn't exist", key);
  }
}
