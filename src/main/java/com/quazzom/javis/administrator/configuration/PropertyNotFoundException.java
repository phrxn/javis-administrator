package com.quazzom.javis.administrator.configuration;

import com.quazzom.javis.administrator.JavisAdministratorException;

public class PropertyNotFoundException extends JavisAdministratorException {

  public PropertyNotFoundException(String message) {
    super(message);
  }
}
