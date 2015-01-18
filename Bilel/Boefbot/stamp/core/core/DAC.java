/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * This class provides an interface to the Virtual Peripheral DAC. The
 * DAC VP implements a one bit digital to analog converter.
 *
 * @author Chris Waters
 * @version 1.0 2-25-00
 */

public class DAC extends VirtualPeripheral {

  /**
   * Create a new one bit digital to analog converter.
   *
   * @param pin the pin to output the signal on.
   */
  public DAC(int pin) {
    this.pin = pin;
    start();
  }

  /**
   * Update the value being generate on the pin.
   *
   * @param value the value to generate on the ouput pin. This value must be between 0 and 255.
   */
  public void update(int value) {
    if ( vpBank != NOT_INSTALLED ) {
      CPU.writeRegister( vpBank|nmDacValue, value&0x00ff);
    }
  }

  /**
   * Start the DAC running. The constructor will start the DAC when it is first
   * created. If it is later stopped with <code>stop()</code> then
   * <code>start()</code> will start it again with the same set of parameters.
   * <p>
   * It is an error to call start if the DAC is already running.
   */
  public void start() {
    if ( vpBank == NOT_INSTALLED ) {
      CPU.installVP( this );

      // Configure the VP registers.
      CPU.writeRegister( vpBank|nmDacPort, pin>>8 );
      CPU.writeRegister( vpBank|nmDacPin, pin );

      install(this.vpBank);
    }
  }

  /**
   * Stop the DAC from running.
   */
  public void stop() {
    CPU.removeVP(this);
  }

//============================================================================
// Private methods and fields below this point.
//============================================================================

  private int pin;

  private native static void install(int vpBank);

  final static int nmDacValue = 0xB; // Current ADC value.

  final static int nmDacPort = 0xE; // 0 for Port0, 1 for Port1
  final static int nmDacPin = 0xF; // bitmask (1 of 8 set)
}