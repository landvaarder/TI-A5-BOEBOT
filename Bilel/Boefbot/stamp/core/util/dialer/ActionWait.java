/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;
import stamp.core.*;
import stamp.util.*;

/**
 * Dialer wait action.
 * <p>
 */

public class ActionWait extends DialerAction {

  final static boolean SUCCESS = true;
  final static boolean FAILURE = false;

  String recvStr;
  Uart uart;
  int timeout, timeoutOffset;

  /**
   * Create a wait action. When executed this action will wait for a string
   * to be received on the UART.
   * <p>
   * If the string is received before the timeout then the script will
   * continue at the next action.
   *
   * @param recvUart the UART to wait for the string to be received on.
   * @param s the string to expect.
   * @param timeout the time in seconds to wait for the string.
   * @param timeoutOffset the jump offset if a timeout occurs.
   */
  public ActionWait(Uart recvUart, String s, int timeout, int timeoutOffset) {
    uart = recvUart;
    recvStr = s;
    this.timeout = timeout;
    this.timeoutOffset = timeoutOffset;
  }

  /**
   * Executes the action.
   *
   * @return the offset to the next action that should be executed.
   */
  public int execute(DialerControl dialer) {
    if ( dialer.debug ) {
      System.out.print("Dialer wait: ");
      System.out.print(recvStr);
    }
    if ( Expect.string ( uart, recvStr, timeout) ) {
      if ( dialer.debug )
        System.out.println("  received");
      return 1;
    }
    else {
      if ( dialer.debug )
        System.out.println("  timeout");
      return timeoutOffset;
    }
  }

}