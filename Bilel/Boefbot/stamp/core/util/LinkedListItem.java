/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

/**
 * An item in a linked list.
 *
 * Note that descendents of this class can only appear in one list at a time.
 */

public abstract class LinkedListItem {

  public LinkedListItem nextItem = null;

}