/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.os;

import stamp.util.*;

/**
 * A task that is run whenever there are free cycles.
 * <p>
 * To create a new task, you should make a subclass of this class and implement
 * the execute() and wait() methods.
 */
public abstract class Task extends LinkedListItem {

  public Task nextTask = null;

  /**
   * State of the task. Initialized to <code>STATE_READY</code> so the task
   * will execute as soon as possible.
   */
  public int state = TaskManager.STATE_READY;

  /**
   * Perform a period task. All subclasses must implement this method.
   * An idle task is executed whenever the GUI is idle. Implementations of
   * <code>execute()</code> should not block to ensure that the
   * interface remains responsive.
   */
  public abstract void execute();

  /**
   * If a task is waiting for an event to occur this is a chance for the task
   * to change its state.
   */
  public abstract void wait();

  /**
   * Change the task to the STATE_WAITING state. Tasks in this state will have
   * their wait() method called periodically.
   */
  public void enterWait() {
    state = TaskManager.STATE_WAITING;
  }

  /**
   * Change the task to the STATE_READY state. Tasks in this state will have
   * their execute() method called periodically.
   */
  public void enterExecute() {
    state = TaskManager.STATE_READY;
  }

  /**
   * Change the task to the STATE_STOPPED state. Tasks in this state will not
   * execute at a all.
   */
  public void stop() {
    state = TaskManager.STATE_STOPPED;
  }

}