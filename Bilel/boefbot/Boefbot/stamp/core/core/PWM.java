/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Perform Pulse Width Modulation (PWM) on an I/O pin. PWM is a virtual
 * peripheral so it will continue to run in the background.
 * <p>
 * The timebase for PWM (and all other virtual peripherals) is 8.68 microseconds.
 *
 *
 * @author Chris Waters
 * @version 1.0 2-25-00
 */

public class PWM extends VirtualPeripheral {

  /**
   * The state that the pin should be set to whenever the PWM is stopped. By
   * default the pin will be set low if the PWM is stopped.
   */
  public boolean stoppedState;

  /**
   * Creates a PWM object, assigns it to a pin but does not start it running.
   * Call update () to set the time. Call start () to start it running.
   *
   * @param pin the pin to generate the PWM signal on.
   */
  public PWM(int pin ) {
    this.pin = pin;
    this.highTime = 1;
    this.lowTime = 1;
  }
  
  /**
   * Creates a PWM object, assigns it to a pin and starts it running.
   *
   * @param pin the pin to generate the PWM signal on.
   * @param highTime the number of 8.68us the waveform should be high.
   * @param lowTime the number of 8.68us the waveform should be low.
   * @param stoppedState the state that the pin should be set to whenever the
   *        PWM is stopped.
   */
  public PWM(int pin, int highTime, int lowTime, boolean stoppedState) {
    this.pin = pin;
    this.highTime = highTime;
    this.lowTime = lowTime;
    this.stoppedState = stoppedState;
    start();
  }

  /**
   * Creates a PWM object, assigns it to a pin and starts it running.
   *
   * @param pin the pin to generate the PWM signal on.
   * @param highTime the number of 8.68us the waveform should be high.
   * @param lowTime the number of 8.68us the waveform should be low.
   */
  public PWM(int pin, int highTime, int lowTime) {
    this(pin, highTime, lowTime, false);
  }

  /**
   * Update the PWM duty cycle.
   *
   * @param highTime the number of 8.68us the waveform should be high.
   * @param lowTime the number of 8.68us the waveform should be low.
   */
  public void update(int highTime, int lowTime) {
    if ( highTime%256 == 0 )
      highTime++;
    if ( lowTime%256 == 0 )
      lowTime++;
    this.highTime = highTime;
    this.lowTime = lowTime;
    if ( vpBank != NOT_INSTALLED )
      updateInternal(this.vpBank, highTime, lowTime, highTime!=0, lowTime!=0);
  }

  /**
   * Start the PWM running. The constructor will start the PWM when it is first
   * created. If it is later stopped with <code>stop()</code> then
   * <code>start()</code> will start it again with the same set of parameters.
   * <p>
   * It is an error to call start if the PWM is already running.
   */
  public void start() {
    if ( vpBank == NOT_INSTALLED ) {
      CPU.installVP( this );

      // Configure the VP registers.
      CPU.writeRegister( vpBank|nmPWMPort, pin>>8 );
      CPU.writeRegister( vpBank|nmPWMPin, pin );

      update(highTime, lowTime);
      install(this.vpBank);
    }
  }

  /**
   * Stop a running PWM.
   * <p>
   * The PWM will stop immediately and may leave the output pin in either a
   * high or low state.
   */
  public void stop() {
    CPU.removeVP(this);
    CPU.writePin(pin, stoppedState);
  }

//============================================================================
// Private methods and fields below this point.
//============================================================================

  private int pin;
  private int highTime;
  private int lowTime;

  private native static void install(int vpBank);
  private native static void updateInternal(int vpBank, int highTime, int lowTime, boolean highZero, boolean lowZero);

  final static int nmPWMPort = 0xE; // 0 for Port0, 1 for Port1
  final static int nmPWMPin = 0xF; // bitmask (1 of 8 set)
}