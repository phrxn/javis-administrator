package com.quazzom.javis.administrator.gui;

import com.quazzom.javis.administrator.Administrator;
import com.quazzom.javis.administrator.AdministratorSingleton;

public class SwingCommons {

  private Administrator administrator;

  public SwingCommons() {
    this(AdministratorSingleton.getInstance());
  }

  public SwingCommons(Administrator administrator) {
    this.administrator = administrator;
  }

  public String createTitle() {
    return String.format(
        "%s - %s", administrator.getProgramName(), administrator.getProgramVersion());
  }
}
