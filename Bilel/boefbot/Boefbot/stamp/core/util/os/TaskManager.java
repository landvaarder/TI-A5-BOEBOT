/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.util.os;

import stamp.util.*;
import java.util.*;

/**
 * Co-operative RTOS
 *
 * This very basic RTOS that does round robin scheduling of tasks.
 * <p>
 * The TaskManager maintains a linked list of tasks. Each task in the list
 * can be in one of three states: STATE_STOPPED, STATE_READY, STATE_WAITING.
 * The TaskManager iterates around the list of tasks. Each time around the list
 * the execute() method of each task in the STATE_READY state is called and the
 * wait() method of each task in the STATE_WAITING state is called.
 * <p>
 * Tasks should not block, i.e. they should perform a short action and return
 * to give other tasks an opportunity to run.
 * <p>
 * Create your own tasks by subclassing the Task class and providing implementations
 * of the execute() and wait() methods.
 *
 * @author Bruce Wilson
 * @author Chris Waters
 */

public class TaskManager {

  // States
  public final static int STATE_STOPPED = 0;
  public final static int STATE_READY = 1;
  public final static int STATE_WAITING = 2;
  public final static int PUBLIC_STATES = STATE_WAITING+1;

  /**
   * Turns on debug mode which will print debugging messages.
   */
  public boolean debug;

  LinkedList taskList = new LinkedList();

  /**
   * The task which is currently being executed.
   */
  public Task currentTask = null;

  /**
   * Add a task to task list.
   *
   * @param aNewTask the task to add to the list.
   */
  public void addTask(Task aNewTask) {
    taskList.addItem(aNewTask);
  }

  /**
   * This method will run forever executing each task in the readyTask list
   * in a round robin fashion
   */
  public void run() {
    int cycleCount = 0;

    if ( debug )
      System.out.println("RTOS Running...");

    currentTask = (Task)taskList.getFirst(); // Set to First Task

    while (true) { // Run forever.
      cycleCount++;
      try {
        currentTask = (Task)taskList.getNextLoop(currentTask); // Get Next task
        switch(currentTask.state) {
        case STATE_READY:
          currentTask.execute();
          break;

        case STATE_WAITING:
          currentTask.wait();
          break;
        }
      }
      catch (NoSuchElementException d) {    // No tasks found.
        currentTask = null;
      }
    }
  }
}