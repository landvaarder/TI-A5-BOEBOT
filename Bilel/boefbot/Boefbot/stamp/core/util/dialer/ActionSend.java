/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;
import stamp.core.*;

/**
 * Dialer send action.
 * <p>
 */

public class ActionSend extends DialerAction  {

  final static boolean SUCCESS = true;
  final static boolean FAILURE = false;

  String sendStr;
  Uart uart;

  /**
   * Create a send action. When executed this action will send a string to
   * the specified UART.
   *
   * @param sendUart the UART that the string should be sent with.
   * @param s the string to send.
   */
  public ActionSend(Uart sendUart, String s) {
    uart = sendUart;
    sendStr = s;
  }

  /**
   * Executes the action.
   *
   * @return the offset to the next action that should be executed.
   */
  public int execute(DialerControl dialer) {
    if ( dialer.debug ) {
      System.out.print("Dialer send: ");
      System.out.println(sendStr);
    }
    uart.sendString(sendStr);
    return 1;
  }

}