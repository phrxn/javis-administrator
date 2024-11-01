package com.quazzom.javis.administrator.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CopyPopupLanguage extends LanguageFromFile {

  private static List<String> allValidKeys;

  static {
    allValidKeys = new ArrayList<>();
    allValidKeys.add("JMENUITEM_COPY");
    allValidKeys.add("JMENUITEM_SELECTALL");
  }

  public CopyPopupLanguage(Properties propertiesLanguage) throws TextKeyDoesntExist {
    super(propertiesLanguage);
    super.containsAllTheseKeys(allValidKeys);
  }

  public CopyPopupLanguage(LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile)
      throws TextKeyDoesntExist {
    super(languageIdiom, languagePathToFile);
    super.containsAllTheseKeys(allValidKeys);
  }
}
