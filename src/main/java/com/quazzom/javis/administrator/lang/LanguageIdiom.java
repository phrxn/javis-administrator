package com.quazzom.javis.administrator.lang;

import com.quazzom.javis.administrator.HasValue;

public enum LanguageIdiom implements HasValue {
  INVALID("?"),
  EN_US("en_us"),
  PT_BR("pt_br");

  private final String variableName;

  LanguageIdiom(String variableName) {
    this.variableName = variableName;
  }

  @Override
  public String getValue() {
    return this.variableName;
  }
}
