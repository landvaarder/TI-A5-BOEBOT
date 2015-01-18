/*
 * Copyright © 2002 Parallax Inc
 * Do not modify this file.
 */

package stamp.core;

/**
 * Frequency generation based on pulse width modulation.
 *
 * @author William Wong
 * @version 1.0 7-12-02
 */

public class Freqout extends PWM {

  /**
   * Creates a Freqout object based on PWM. Does not start running.
   *
   * @param pin the pin to generate the PWM signal on.
   */
  public Freqout(int pin) {
    super(pin);
    setFrequency(1) ;
  }

  /**
   * Creates a Freqout object based on PWM. Does not start running.
   *
   * @param pin the pin to generate the PWM signal on.
   * @param frequency frequency in hertz/10 (1 - 12k)
   */
  public Freqout(int pin, int frequency) {
    super(pin);
    setFrequency(frequency);
  }

  /**
   * Set output frequency. Accurate only to 8.68us timebase.
   *
   * @param frequency frequency in hertz/10 (1 - 12k)
   */
  public void setFrequency(int frequency) {
    if ( frequency < 1 ) {
      frequency = 1;
    } else if ( frequency > 11521 ) {
      frequency = 11521;
    }

    int halfCycleTime = 11521 / frequency;
    update(halfCycleTime, halfCycleTime);
  }

  /**
   * Set output frequency for a fixed amount of time.
   * It starts and stops the base PWM.
   *
   * @param frequency frequency in hertz/10 (1 - 12k)
   * @param time amount of time in CPU.delay() units
   */
  public void freqout(int frequency, int time) {
    setFrequency(frequency);
    freqout(time);
  }

  /**
   * Set output frequency for a fixed amount of time.
   * It start()s and stop()s the base PWM.
   *
   * @param time amount of time in CPU.delay() units
   */
  public void freqout(int time) {
    start();
    CPU.delay(time);
    stop();
  }
}