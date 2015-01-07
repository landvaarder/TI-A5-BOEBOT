/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.dialer;
import stamp.core.*;
import stamp.util.dialer.*;

/**
 * Dialer control for automated dialing of modems and ISPs.
 * <p>
 */

public class DialerControl {

  public final static int SUCCESS_OFFSET = 999;
  public final static int FAILURE_OFFSET = -999;

  DialerAction script[];

  /**
   * Whether debugging information should be printed.
   */
  public boolean debug = false;

  /**
   * The current failure count.
   */
  public int failureCount = 0;

  public DialerControl(DialerAction script[]) {
    this.script = script;
  }

  /**
   * Runs the script.
   *
   * @return true if the script completes successfully, false otherwise.
   */
  public boolean runScript() {
    for (int i = 0; i < script.length; ) {
      int offset = script[i].execute(this);

      if ( offset == SUCCESS_OFFSET ) {
        if (debug)
          System.out.println("Script succeeded.");
        return true;
      }
      else if ( offset == FAILURE_OFFSET ) {
        if (debug)
          System.out.println("Script failed.");
        return false;
      }
      else
        i += offset;
    }

    return false;
  }

}