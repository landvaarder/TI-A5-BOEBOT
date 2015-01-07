/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Push button with auto-repeat and repeat-delay.
 *
 * @author Chris Waters
 * @version 1.0 2/22/00
 */

public class Button {

  int pin;
  int delay; // Delay before auto-repeat.
  boolean upState;

  /**
   * Delay in milliseconds before repeat starts.
   */
  public int repeatDelay;

  /**
   * Delay in milliseconds between each repetition.
   */
  public int repeatRate;

  /**
   * The amount of time to debounce when the button goes down. Measured in
   * milliseconds. 25ms is a reasonable value. By default there is no
   * debounce.
   */
  public int debounceDelay = 0;

  public static final int DEFAULT_REPEAT_DELAY = 1000;
  public static final int DEFAULT_REPEAT_RATE = 200;

  /**
   * The button is currently up.
   */
  public static final int BUTTON_UP = 0;

  /**
   * The button has just gone down.
   */
  public static final int BUTTON_DOWN = 1;

  /**
   * The button is down and is auto-repeating.
   */
  public static final int BUTTON_AUTO_REPEAT = 2;

  /**
   * The button has just gone down and we are waiting for the debounce period.
   */
  public static final int BUTTON_DEBOUNCE = 3;

  int lastState;
  Timer delayTimer;

  /**
   * Create an instance of the Button class with auto-repeat enabled.
   *
   * @param portPin the pin that the button is connected to.
   * @param upState the state of the pin when the button is up.
   * @param repeatDelay the delay in milliseconds after the button has gone down
   * and before it should start repeating.
   * @param repeatRate the time in milliseconds before each repeated button
   * down event.
   */
  public Button(int portPin, boolean upState, int repeatDelay, int repeatRate) {
    this.repeatDelay = repeatDelay;
    this.repeatRate = repeatRate;
    this.pin = portPin;
    this.upState = upState;
    delayTimer = new Timer();
  }

  /**
   * Create an instance of the Button class without auto-repeat.
   *
   * @param portPin the pin that the button is connected to.
   * @param upState the state of the pin when the button is up.
   */
  public Button(int portPin, boolean upState) {
    this(portPin, upState, 0, 0);
  }

  /**
   * Check whether the button is down. This method should be called continuously.
   * It will monitor the button and the debounce and auto-repeat timers.
   *
   * @return true if the button is currently down.
   */
  public boolean buttonDown() {
    boolean down = (CPU.readPin(pin) != upState);

    switch ( lastState ) {
      case BUTTON_UP:
        if ( down ) {
          if ( debounceDelay > 0 ) {
            delayTimer.mark(); // Start the debounce timer.
            lastState = BUTTON_DEBOUNCE;
            return false; // Button is not down until end of debounce period.
          }
          else {
            goButtonDown();
            return true;
          }
        }
        break;

      case BUTTON_DOWN:
        if ( !down ) {
          lastState = BUTTON_UP;
        }
        else if ( repeatDelay > 0 && delayTimer.timeout(repeatDelay) ) {
          lastState = BUTTON_AUTO_REPEAT;
          delayTimer.mark();
          return true;
        }
        break;

      case BUTTON_AUTO_REPEAT:
        if ( !down ) {
          lastState = BUTTON_UP;
        }
        else if ( delayTimer.timeout(DEFAULT_REPEAT_RATE) ) {
          delayTimer.mark();
          return true;
        }
        break;

      case BUTTON_DEBOUNCE:
        if ( down ) {
          if ( delayTimer.timeout(debounceDelay) ) {
            goButtonDown();
            return true;
          }
        }
        else {
          lastState = BUTTON_UP;
        }
        break;
    }

    return false;
  }

  /*
   * Change the state of the button to down. Start the repeat timer.
   */
  void goButtonDown() {
    delayTimer.mark(); // Start the repeat delay timer.
    lastState = BUTTON_DOWN;
  }

  /**
   * Returns the current state of the button.
   *
   * @return the current button state.
   */
  public int getState() {
    return lastState;
  }

}