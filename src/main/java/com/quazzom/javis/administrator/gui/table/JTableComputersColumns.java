package com.quazzom.javis.administrator.gui.table;

public enum JTableComputersColumns {
  COLUMN_ID(0, "COLUMN_NAME_ID"),
  COLUMN_POWER_STATUS_COMPUTER(1, "COLUMN_NAME_ID_POWER_STATUS_COMPUTER"),
  COLUMN_SESSION_TYPE(2, "COLUMN_NAME_ID_SESSION_TYPE"),
  COLUMN_USERNAME(3, "COLUMN_NAME_ID_USERNAME"),
  COLUMN_HOSTNAME(4, "COLUMN_NAME_ID_HOSTNAME"),
  COLUMN_IP(5, "COLUMN_NAME_ID_IP"),
  COLUMN_IS_MANUAL(6, "COLUMN_NAME_ID_IS_MANUAL"),
  COLUMN_LOGIN_DATE(7, "COLUMN_NAME_ID_LOGIN_DATE"),
  COLUMN_JAVIS_CLIENT_VERSION(8, "COLUMN_NAME_ID_JAVIS_CLIENT_VERSION"),
  COLUMN_DETAILS(9, "COLUMN_NAME_ID_DETAILS");

  private int number;
  private String languageID;

  JTableComputersColumns(int number, String languageID) {
    this.number = number;
    this.languageID = languageID;
  }

  public int getNumber() {
    return number;
  }

  public String getLanguageID() {
    return languageID;
  }
};
