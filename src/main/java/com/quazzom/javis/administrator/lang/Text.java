package com.quazzom.javis.administrator.lang;

public interface Text {

  String getText(String key, Object... args) throws TextNotFoundException;

  boolean containsKey(String key);

  // void setText(String key, String value);
}
