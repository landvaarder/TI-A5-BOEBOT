/*
 * Copyright © 2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Methods for operating on the Javelin Stamp's memory.
 *
 *
 * @author Chris Waters
 * @version 1.0 3-30-02
 */

public class Memory {

  /**
   * Find the number of bytes of memory SRAM.
   *
   * @return the number of bytes of free SRAM.
   */
  public static int freeMemory() {
    int stackPointer = (CPU.readRegister(JVM_STACK_POINTER+1)<<8)|CPU.readRegister(JVM_STACK_POINTER);
    int heapPointer = (CPU.readRegister(JVM_HEAP_POINTER+1)<<8)|CPU.readRegister(JVM_HEAP_POINTER);

    return heapPointer - stackPointer;
  }

//============================================================================
// Methods and fields below this point are private.
//============================================================================
  // Private constructor to prevent objects of this class from being instantiated.
  private Memory() {};

  private final static int JVM_HEAP_POINTER = 0x0018;
  private final static int JVM_STACK_POINTER = 0x0012;
}