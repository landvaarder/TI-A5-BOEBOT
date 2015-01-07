/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Receive bytes from the debugger serial interface.
 *
 * @author Chris Waters
 * @version 1.0 8/28/00
 */
public class Terminal {

  static char temp;
  static boolean tempAvailable;

  /**
   * Returns the last character sent from the terminal. If there are no bytes
   * waiting then blocks until a byte is available.
   *
   * @return the last received character.
   */
  public static char getChar() {
    while ( !(tempAvailable || internalGetChar()) )
      CPU.delay(1000);  // Delay for 100ms.

    tempAvailable = false;
    return temp;
  }

  /**
   * Checks if a byte is available into the terminal buffer. If at least
   * one byte is available <code>true</code> is returned.
   */
  public static boolean byteAvailable() {
    if ( tempAvailable )
      return true;
    else
      return internalGetChar();
  }

//============================================================================
// Methods and fields below this point are private.
//============================================================================

  static boolean internalGetChar() {
    int r = getByte();
    if ( (r>>>8) != 0 )
      return false;
    else {
      temp = (char)(r&0x00ff);
      tempAvailable = true;
      return true;
    }
  }

  static native int getByte();
}