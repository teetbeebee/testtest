package com.newbee.tmf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtils {

  /**
   * 将byte类型对象反序列化成原对象
   * 
   * @param byteObject
   * @return 对象
   * @throws Exception
   */
  public static Object readObject(byte[] byteObject) throws Exception {
    Object object = null;
    try {
      ByteArrayInputStream byteStream = new ByteArrayInputStream(byteObject);
      ObjectInputStream receive = new ObjectInputStream(byteStream);
      object = receive.readObject();
      byteStream.close();
      receive.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return object;
  }

  /**
   * 将一个对象序列化成byte类型
   * 
   * @param object
   * @return
   * @throws Exception
   */
  public static byte[] writeObject(Object object) throws Exception {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(byteStream);
      oos.writeObject(object);
      oos.flush();
      byteStream.close();
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return byteStream.toByteArray();
  }

}