package com.newbee.tmf.core;

public class LoginException extends BaseException {

  /**
   * ���л�id
   */
  private static final long serialVersionUID = 4680466312298175786L;

  public LoginException(String msg) {
    super(msg);
  }

  public LoginException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
