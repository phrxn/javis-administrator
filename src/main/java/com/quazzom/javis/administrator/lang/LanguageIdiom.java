package com.quazzom.javis.administrator.lang;

public enum LanguageIdiom {
  EN_US("EN_US"),
  PT_BR("PT_BR");

  private String type;

  private LanguageIdiom(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
