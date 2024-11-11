package com.quazzom.javis.administrator.authentication;

public interface Authentication {
  boolean makeLogin(String user, String password) throws AuthenticationException;
}
