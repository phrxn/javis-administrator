package com.quazzom.javis.administrator;

public class AdministratorSingleton {

  private static Administrator singleton;

  private AdministratorSingleton() {}

  public static Administrator getInstance() {
    if (singleton == null) singleton = new Administrator();
    return singleton;
  }
}
