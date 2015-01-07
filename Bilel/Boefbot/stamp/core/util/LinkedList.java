/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util;

import java.util.*;

/**
 * A linked list.
 */

public class LinkedList {

  protected LinkedListItem list = null;

  /**
   * Return the first item in the list.
   */
  public LinkedListItem getFirst() throws NoSuchElementException {
    if ( list == null )
      throw NoSuchElementException.throwIt();
    return list;
  }

  /**
   * return the last item in the list
   */
  public LinkedListItem getLast() throws NoSuchElementException  {
    LinkedListItem lastItem = list;

    if ( list == null )
      throw NoSuchElementException.throwIt();
    else
      while ( lastItem.nextItem != null )
        lastItem = lastItem.nextItem;
    return lastItem.nextItem;
  }

  /**
   * Given an item in the list return the next item.
   */
  public LinkedListItem getNext(LinkedListItem item) throws NoSuchElementException  {
    if ( item.nextItem == null )
      throw NoSuchElementException.throwIt();
    return item.nextItem;
  }

  /**
   * Given an item in the list return the next item, or the first item in the
   * list if the end of the list is reached.
   */
  public LinkedListItem getNextLoop(LinkedListItem item) throws NoSuchElementException  {
    if ( item.nextItem == null ) {
      if ( list != null )
        return list;
      else
        throw NoSuchElementException.throwIt();
    }
    return item.nextItem;
  }

  /**
   * Add an item to the list. The item is added to the head of the list.
   */
  public void addItem(LinkedListItem item) {
    item.nextItem = list;
    list = item;
  }

  /**
   * Remove an item from the list.
   */
  public void removeItem(LinkedListItem item) {
    if ( list == item )
      list = item.nextItem;
    else {
      for ( LinkedListItem prevItem = list; prevItem != null; prevItem = prevItem.nextItem )
        if ( prevItem.nextItem == item ) {
          prevItem.nextItem = item.nextItem;
          return;
        }
    }
  }

}