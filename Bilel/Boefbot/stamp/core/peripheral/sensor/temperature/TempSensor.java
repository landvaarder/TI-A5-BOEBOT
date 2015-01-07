/*
 * Copyright © 2002 Parallax Inc. All rights reserved.
 * Do not modify this file.
 */

package stamp.peripheral.sensor.temperature;

/**
 * TempSensor is an abstract class encapsulating the basic capabilities of
 * a generic temperature sensor, including high and low threshold settings and
 * simple (polled) alarms.
 *
 * @author Jon Williams, Parallax, Inc.
 * @version 1.0 March 29 2002
 */
public abstract class TempSensor {

  public final static int TEMP_LO = 0x00;
  public final static int TEMP_OK = 0x01;
  public final static int TEMP_HI = 0x02;

  private int tLo;  // low temperature threshold  (stored in degrees C)
  private int tHi;  // high temperature threshold (stored in degrees C)

  public abstract int getTempRaw();
  public abstract int getTempC();
  public abstract int getTempF();

  /**
   * Returns current temperature.
   *
   * @param tmpUnits Temperature units (c, C, f or F)
   */
  public int getTemp(char tmpUnits) {
    return ((tmpUnits == 'c') || (tmpUnits == 'C')) ? getTempC() : getTempF();
  }

  /**
   * Converts Fahrenheit temperature to Celsius
   *
   * @param temp Fahrenheit temperature
   * @return Celsius temperature
   */
  public int FahrToCelsius(int temp) {
     // return ((temp - 32) * 5 / 9);
     return (((temp - 32) * 50 / 9 + 5) / 10);  // round up
  }

  /**
   * Converts Celsius temperature to Fahrenheit
   *
   * @param temp Celsius temperature
   * @return Celsius temperature
   */
  public int CelsiusToFahr(int temp) {
     // return (temp * 9 / 5 + 32);
     return (temp * 90 / 5 + 325) / 10;         // round up
  }

  /**
   * Sets low temperature threshold
   *
   * @param temp Low temperature threshold
   * @param tmpUnits Temperature type (c, C, f or F)
   */
  public void setTempLo(int temp, char tmpUnits) {
    tLo = ((tmpUnits == 'c') || (tmpUnits == 'C')) ? temp : FahrToCelsius(temp);
  }

  /**
   * Sets high temperature threshold
   *
   * @param temp High temperature threshold
   * @param tmpUnits Temperature type (c, C, f or F)
   */
  public void setTempHi(int temp, char tmpUnits) {
    tHi = ((tmpUnits == 'c') || (tmpUnits == 'C')) ? temp : FahrToCelsius(temp);
  }

  /**
   * Returns low temperature threshold in specified units
   *
   * @param tmpUnits Temperature type (c, C, f or F)
   */
  public int getTempLo(char tmpUnits) {
    return ((tmpUnits == 'c') || (tmpUnits == 'C')) ? tLo : CelsiusToFahr(tLo);
  }

  /**
   * Returns high temperature threshold in specified units
   *
   * @param tmpUnits Temperature type (c, C, f or F)
   */

  public int getTempHi(char tmpUnits) {
    return ((tmpUnits == 'c') || (tmpUnits == 'C')) ? tHi : CelsiusToFahr(tHi);
  }

  /**
   * Returns low temperature alarm status.
   *
   * @return True if temperature is at or belwo low temperature threshold.
   */
  public boolean tempLo() {
    return (getTempC() <= tLo);
  }

  /**
   * Returns temperature alarm status.
   *
   * @return True if temperature is between low and high thresholds.
   */
  public boolean tempOk() {
    return ((getTempC() > tLo) && (getTempC() < tHi));
  }

  /**
   * Returns high temperature alarm status.
   *
   * @return True if temperature is at or exceeds high temperature threshold.
   */
  public boolean tempHi() {
    return (getTempC() >= tHi);
  }

  /**
   * Returns sensor status: low, okay or high.
   *
   * @return Sensor status (0 = low, 1 = okay, 2 = high)
   */
  public int tempStatus() {
    if (tempLo())
      return TEMP_LO;
    else if (tempOk())
      return TEMP_OK;
    else
      return TEMP_HI;
  }
}