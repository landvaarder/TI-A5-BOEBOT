/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;
import stamp.core.*;

/**
 * Dialer pause action.
 * <p>
 */

public class ActionPause extends DialerAction {

  int delay;

  /**
   * Create a pause action. The pause action will delay for a fixed amount
   * of time.
   *
   * @param delay the amount of time to delay, measured in milliseconds. Maximum
   *              delay is 3 seconds.
   */
  public ActionPause(int delay) {
    this.delay = delay;
  }

  /**
   * Executes the action.
   *
   * @return the offset to the next action that should be executed.
   */
  public int execute(DialerControl dialer) {
    if ( dialer.debug )
      System.out.println("Dialer pausing");
    CPU.delay(delay*10);
    return 1;
  }

}