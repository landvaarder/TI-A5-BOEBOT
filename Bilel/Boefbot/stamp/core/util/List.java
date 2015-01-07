/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

/**
 * An ordered list of objects.
 */

public class List {

  protected int numObjects;
  protected Object list[];

  /**
   * Creates a new list with the specified number of entries.
   */
  public List(int maxSize) {
    numObjects = 0;
    list = new Object[maxSize];
  }

  /**
   * Returns the element at the specified position in the list.
   *
   * @param index index of the element to get.
   * @return element at the specified position in the list.
   */
  public Object get(int index) {
    if ( index >= numObjects )
      throw IndexOutOfBoundsException.throwIt();
    return list[index];
  }

  /**
   * Adds an object to the end of the list.
   *
   * @param o the object to append to the list.
   */
  public void add(Object o) {
    list[numObjects++] = o;
  }

  /**
   * Inserts an object at the specified location in the list.
   *
   * @param index the index at which to insert the object.
   * @param o the object to insert into the list.
   */
  public void add(int index, Object o) {
    if ( index < 0 || index > numObjects )
      throw IndexOutOfBoundsException.throwIt();
    for ( int i = numObjects; i > index; --i )
      list[i] = list[i-1];
    list[index] = o;
    numObjects++;
  }

  /**
   * Gets the number of objects in the list.
   *
   * @return the number of objects currently in the list.
   */
  public int size() {
    return numObjects;
  }

}