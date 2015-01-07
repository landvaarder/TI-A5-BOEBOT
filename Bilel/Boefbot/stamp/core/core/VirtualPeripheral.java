/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Base class for virtual peripheral classes. A virtual peripheral is a
 * hardware function which runs continuously in the background.
 *
 * @author Chris Waters
 * @version 1.0 5/1/99
 */
public class VirtualPeripheral {
  // A constant to indicate that the VP is not current installed.
  final static int NOT_INSTALLED = -1 ;

  /**
   * The SX bank the VP is installed in.
   */
  int vpBank = NOT_INSTALLED;
}