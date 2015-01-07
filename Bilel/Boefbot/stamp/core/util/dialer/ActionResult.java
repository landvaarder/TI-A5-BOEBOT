/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;

/**
 * Dialer result action.
 * <p>
 */

public class ActionResult extends DialerAction {

  public final static boolean SUCCESS = true;
  public final static boolean FAILURE = false;

  boolean result;

  /**
   * Create a result action. A result action will terminate the running
   * script and indicate success or failure.
   *
   * @param result the result which should be returned. The result can be
   *               SUCCESS or FAILURE.
   */
  public ActionResult(boolean result) {
    this.result = result;
  }

  /**
   * Executes the action.
   *
   * @return the offset to the next action that should be executed.
   */
  public int execute(DialerControl dialer) {
    if ( dialer.debug )
      System.out.println("Dialer result");
    if ( result == SUCCESS )
      return DialerControl.SUCCESS_OFFSET;
    else
      return DialerControl.FAILURE_OFFSET;
  }

}