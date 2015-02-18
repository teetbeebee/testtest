package com.newbee.tmf.core;

import java.util.List;

public interface Context {
  public Object get(String key);

  public void put(String key, Object value);

  public Object remove(String key);

  public boolean contains(String key);

  public void clear();

  public List getKeys();
}
