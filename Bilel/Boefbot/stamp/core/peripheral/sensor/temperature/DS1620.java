/*
 * Copyright © 2000-2002 Parallax Inc. All rights reserved.
 * Do not modify this file.
 */

package stamp.peripheral.sensor.temperature;

import stamp.core.*;
import stamp.peripheral.sensor.temperature.TempSensor;

/**
 * This class encapsulates the basic capabilities of the Dallas DS1620 3-wire
 * temperature sensor.
 *
 * @author Stephen Holland / Jon Williams (Parallax, Inc.)
 * @version 1.2 March 29 2002
 */
public class DS1620 extends TempSensor {

  // DS1620 Commands
  final static int READ_TEMP = 0xAA;
  final static int WRITE_TH = 0x01;
  final static int WRITE_TL = 0x02;
  final static int READ_TH = 0xA1;
  final static int READ_TL = 0xA2;
  final static int READ_COUNTER = 0xA0;
  final static int READ_SLOPE = 0xA9;
  final static int START_CONVERT = 0xEE;
  final static int STOP_CONVERT = 0x22;
  final static int WRITE_CONFIG = 0x0C;
  final static int READ_CONFIG = 0xAC;

  private int dataPin, clockPin, enablePin;
  private int tempIn, sign;

  /**
   * Creates DS1620 temperature sensor object that is intialized for
   * free-run mode for use with a CPU.
   *
   * @param dataPin DS1620 DQ pin (bi-directional)
   * @param clockPin DS1620 clock input
   * @param enablePin DS1620 RST| input (high to enable)
   */
  public DS1620(int dataPin, int clockPin, int enablePin) {
    this.dataPin = dataPin;
    this.clockPin = clockPin;
    this.enablePin = enablePin;

    // initialize for free-run, with CPU
    CPU.writePin(enablePin,true);
    CPU.shiftOut(dataPin,clockPin,8,CPU.SHIFT_LSB,WRITE_CONFIG);
    CPU.shiftOut(dataPin,clockPin,8,CPU.SHIFT_LSB,0x02);
    CPU.writePin(enablePin,false);
    CPU.delay(100);
    CPU.writePin(enablePin,true);
    CPU.shiftOut(dataPin,clockPin,8,CPU.SHIFT_LSB,START_CONVERT);
    CPU.writePin(enablePin,false);
  }

  /**
   * Returns DS1620 temp in units of 0.5 degrees Celcius
   *
   * @return Temperature in half degrees Celcius.
   */
  public int getTempRaw(){
    CPU.writePin(enablePin,true);
    CPU.shiftOut(dataPin,clockPin,8,CPU.SHIFT_LSB,READ_TEMP);
    tempIn = (CPU.shiftIn(dataPin,clockPin,9,CPU.POST_CLOCK_LSB) >> 7);
    CPU.writePin(enablePin,false);
    sign = (tempIn >> 8);                         // isolate sign bit
    tempIn = (tempIn & 0x00FF);                   // mask sign bit from temp

    if (sign == 1) // temp is negative
      return (tempIn | -256);                     // tempIn | 0xFF00
    else
      return tempIn;
  }

  /**
   * Returns the current temperature in degrees Celsius.
   *
   * @return Temperature in whole degrees Celsius.
   */
  public int getTempC() {
    return (getTempRaw() / 2);
  }

  /**
   * Returns the current temperature in degrees Farenheit.
   *
   * @return Temperature in whole degrees Farenheit.
   */
  public int getTempF() {
    return (getTempRaw() * 9 + 320) / 10;
  }
}