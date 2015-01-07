/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;
import stamp.core.*;

/**
 * Superclass for all dialer actions.
 * <p>
 */

public abstract class DialerAction {

  /**
   * Executes the action.
   *
   * @return the offset to the next action that should be executed.
   */
  public abstract int execute(DialerControl dialer);

}