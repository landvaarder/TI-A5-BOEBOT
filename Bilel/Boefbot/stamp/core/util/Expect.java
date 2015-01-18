/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

import stamp.core.*;

/**
 * Class to wait for an input from a UART with a timeout.
 */
public class Expect {

  static Timer expectTimer = new Timer(); //create a timer for the expect routine

  /**
   * Expect a string to be received.
   * Received data from the Uart until the <code>string</code> is seen or
   * the timeout occurs.
   *
   * @param input Uart to receive data from.
   * @param string String to wait for.
   * @param timeout number of seconds to wait for the string to be received.
   * @return trye if the string is seen, false if the timeout occurs.
   */
  public static boolean string ( Uart input, String string, int timeout) {
    int i = 0;
    expectTimer.mark();

    while ( true ) {
      if ( expectTimer.timeoutSec(timeout) )
        return false;
      if ( input.byteAvailable() ) {
        int c = input.receiveByte();
        if ( c == string.charAt(i) ) {
          i++;
          if ( i == string.length() ) {
            return true;
          }
        }
        else
          i = 0;
      }
    }
  }
}