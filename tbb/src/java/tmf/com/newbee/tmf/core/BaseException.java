package com.newbee.tmf.core;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends Exception {

  /**
   * 序列化id
   */
  private static final long serialVersionUID = 2754902902412730592L;

  private Map values = new HashMap();

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable rootCause) {
    super(message, rootCause);
  }

  public void addValue(String key, Object value) {
    values.put(key, value);
  }

  public Object getValue(String key) {
    return values.get(key);
  }

}
