/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * This class provides an interface to the Virtual Peripheral ADC.
 *
 * @author Chris Waters
 * @version 1.0 2-25-00
 */

public class ADC extends VirtualPeripheral {

  /**
   * Create a sigma-delta analog to digital converter virtual peripheral and
   * start it running.
   *
   * @param inPin the pin to be used as an input by the converter.
   * @param outPin the pin to be used as an output by the converter.
   */
  public ADC(int inPin, int outPin) {
    this.inPin = inPin;
    this.outPin = outPin;
    start();
  }

  /**
   * Read the value currently being read by the ADC.
   *
   * @return value being read by ADC.
   */
  public int value() {
    return (vpBank == NOT_INSTALLED)?0:CPU.readRegister( vpBank|nmAdcValue);
  }

  /**
   * Start the ADC running. The constructor will start the ADC when it is first
   * created. If it is later stopped with <code>stop()</code> then
   * <code>start()</code> will start it again with the same set of parameters.
   * <p>
   * It is an error to call start if the ADC is already running.
   */
  public void start() {
    if ( vpBank == NOT_INSTALLED ) {
      CPU.installVP( this );

      // Configure the VP registers.
      CPU.writeRegister( vpBank|nmInPort, inPin>>8 );
      CPU.writeRegister( vpBank|nmInPin, inPin );
      CPU.writeRegister( vpBank|nmOutPort, outPin>>8 );
      CPU.writeRegister( vpBank|nmOutPin, outPin );

      install(this.vpBank);
    }
  }

  /**
   * Stop the ADC from running.
   */
  public void stop() {
    CPU.removeVP(this);
  }

//============================================================================
// Private methods and fields below this point.
//============================================================================

  private int inPin;
  private int outPin;

  private native static void install(int vpBank);

  final static int nmAdcValue = 0xB; // Current ADC value.
  final static int nmInPort = 0xC; // 0 for Port0, 1 for Port1
  final static int nmInPin = 0xD; // bitmask (1 of 8 set)
  final static int nmOutPort = 0xE; // 0 for Port0, 1 for Port1
  final static int nmOutPin = 0xF; // bitmask (1 of 8 set)
}