/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

import stamp.core.*;

/**
 * Maintains a pool of reusable objects. When an object instance is needed
 * it is checked out of the pool. When the object is no longer required it
 * is checked back into the pool.
 *
 * This class should be subclassed for each object class that needs to be
 * pooled.
 */

public abstract class Pool extends List {

  protected boolean objectUsed[];

  /**
   * Creates a new object pool. The subclass must allocate the objects in
   * the pool.
   */
  public Pool(int maxSize) {
    super(maxSize);
    objectUsed = new boolean[maxSize];
  }

  /**
   * Check an object out of the pool.
   *
   * @return a free object instantance
   */
  public Object checkOut() throws IndexOutOfBoundsException {
    for ( int i = 0; i < numObjects; i++ )
      if ( objectUsed[i] == false ) {
        objectUsed[i] = true;
        return list[i];
      }

    throw IndexOutOfBoundsException.throwIt();
  }

  /**
   * Checks an object back into the pool when it is no longer needed.
   */
  public void checkIn(Object buffer) {
    for ( int i = 0; i < numObjects; i++ )
      if ( buffer == list[i] ) {
        objectUsed[i] = false;
        return;
      }
  }

}