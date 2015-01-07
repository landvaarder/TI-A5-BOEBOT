/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

import stamp.core.*;

/**
 * A cache is a special type of pool. When an attempt is made to check out a
 * new object when all objects are in use, the 'oldest' object is returned.
 * <p> A least recently used algorithm is used to determine the oldest object.
 * <p> Caution: the LRU algorithm doesn't wrap around correctly.
 */

public class Cache extends Pool {

  // The age of each entry.
  protected int age[];
  protected int globalAge;

  /**
   * Creates a new cache with the specified number of entries.
   */
  public Cache(int maxSize) {
    super(maxSize);
    globalAge = 0;
    age = new int[maxSize];
  }

  /**
   * Check an object out of the pool.
   *
   * @return a free object instantance
   */
  public Object checkOut() throws IndexOutOfBoundsException {
    int oldestAge = globalAge;
    int oldestIndex = 0;

    for ( int i = 0; i < numObjects; i++ ) {
      if ( objectUsed[i] == false ) {
        objectUsed[i] = true;
        age[i] = globalAge++;
        return list[i];
      }
      if ( age[i] < oldestAge ) {
        oldestAge = age[i];
        oldestIndex = i;
      }
    }

    // There are no empty entries, reuse the oldest one.
    age[oldestIndex] = globalAge++;
    return list[oldestIndex];
  }

}