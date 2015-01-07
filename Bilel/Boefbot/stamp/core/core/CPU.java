/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Utility methods for accessing the Javelin Stamp hardware core.
 *
 * This class contains many of the native methods integrated into the
 * Javelin Stamp. There is no need to create an instance of <code>CPU</code>
 * since all of its methods and fields are declared static.
 *
 * @author Chris Waters
 * @version 1.1 12/26/02
 */

public class CPU {

  /**
   * A constant representing pin P0.
   */
  public final static int pin0  = 1<<0;
  /**
   * A constant representing pin P1.
   */
  public final static int pin1  = 1<<1;
  /**
   * A constant representing pin P2.
   */
  public final static int pin2  = 1<<2;
  /**
   * A constant representing pin P3.
   */
  public final static int pin3  = 1<<3;
  /**
   * A constant representing pin P4.
   */
  public final static int pin4  = 1<<4;
  /**
   * A constant representing pin P5.
   */
  public final static int pin5  = 1<<5;
  /**
   * A constant representing pin P6.
   */
  public final static int pin6  = 1<<6;
  /**
   * A constant representing pin P7.
   */
  public final static int pin7  = 1<<7;
  /**
   * A constant representing pin P8.
   */
  public final static int pin8  = (1<<0)+256;
  /**
   * A constant representing pin P9.
   */
  public final static int pin9  = (1<<1)+256;
  /**
   * A constant representing pin P10.
   */
  public final static int pin10 = (1<<2)+256;
  /**
   * A constant representing pin P11.
   */
  public final static int pin11 = (1<<3)+256;
  /**
   * A constant representing pin P12.
   */
  public final static int pin12 = (1<<4)+256;
  /**
   * A constant representing pin P13.
   */
  public final static int pin13 = (1<<5)+256;
  /**
   * A constant representing pin P14.
   */
  public final static int pin14 = (1<<6)+256;
  /**
   * A constant representing pin P15.
   */
  public final static int pin15 = (1<<7)+256;

  /**
   * An array representing all of the pins on the module. The nth element of the
   * array refers to the nth pin. i.e. CPU.pins[5] == CPU.pin5
   */
  public static int pins[] = {CPU.pin0, CPU.pin1, CPU.pin2, CPU.pin3, CPU.pin4,
                              CPU.pin5, CPU.pin6, CPU.pin7, CPU.pin8, CPU.pin9,
                              CPU.pin10, CPU.pin11, CPU.pin12, CPU.pin13,
                              CPU.pin14, CPU.pin15};

  /**
   * A constant representing the first I/O port on the Javelin Stamp. This port
   * contains pins 0-7.
   */
  public final static int PORTA = 0x00FF;

  /**
   * A constant representing the second I/O port on the Javelin Stamp. This port
   * contains pins 8-15.
   */
  public final static int PORTB = (short)0x01FF;

  /**
   * Specify pre-clock sampling and MSB-first transmission for the shift
   * methods.
   */
  public final static int PRE_CLOCK_MSB = 0;
  /**
   * Specify post-clock sampling and MSB-first transmission for the shift
   * methods.
   */
  public final static int POST_CLOCK_MSB = 1;
  /**
   * Specify pre-clock sampling and LSB-first transmission for the shift
   * methods.
   */
  public final static int PRE_CLOCK_LSB = 2;
  /**
   * Specify post-clock sampling and LSB-first transmission for the shift
   * methods.
   */
  public final static int POST_CLOCK_LSB = 3;

  /**
   * Specify MSB-first transmission for the shift methods.
   */
  public final static int SHIFT_MSB = 0;
  /**
   * Specify LSB-first transmission for the shift methods.
   */
  public final static int SHIFT_LSB = 2;

  /**
   * The maximum number of virtual peripherals that can be installed at once.
   */
  public final static int MAX_NUM_VPS = 6;

  /**
   * Install a new Virtual Peripheral.
   *
   * @param vp the virtual peripheral instance to install.
   * @return the bank number the virtual peripheral has been installed in.
   * @exception IllegalArgumentException the virtual peripheral could not be installed.
   */
  public static void installVP( VirtualPeripheral vp )
    throws IllegalArgumentException {
    if ( vp.vpBank != VirtualPeripheral.NOT_INSTALLED ) {
      return;
    }
    if ( vpList == null )
      vpList = new VirtualPeripheral[MAX_NUM_VPS];
    for ( int i = 0; i < vpList.length; i++ )
      if ( vpList[i] == null ) {
        vpList[i] = vp;
        vp.vpBank = nmPeriph0Bank + i*0x10;
        return;
      }

    throw IllegalArgumentException.throwIt();
  }

  /**
   * Remove a virtual peripheral from its slot. The VP will stop executing
   * immediately and its slot will be available to install a different VP.
   *
   * @param vp the virtual peripheral to remove.
   */
  public static void removeVP(VirtualPeripheral vp) {
    if ( vp.vpBank == VirtualPeripheral.NOT_INSTALLED ) {
      return;
    }

    for ( int i = 0; i < vpList.length; i++ )
      if ( vpList[i] == vp ) {
        vpList[i] = null;
        uninstall(vp.vpBank);
        break;
      }

    vp.vpBank = VirtualPeripheral.NOT_INSTALLED;
  }

  /**
   * Delays the program execution for a period of time. The delay method does not
   * return until the delay period has expired. During a delay any installed Virtual
   * Peripherals will continue to run.
   * <p>
   *
   * @param period the length of time to delay for, measured in
   * 95.48 microsecond units.
   */
  public static native void delay(int period);

  /**
   * Put the Javelin Stamp into sleep mode for a period of time. The accuracy
   * of the sleep timer is very poor. See the manual for more information.
   *
   * @param period specifies the period for which the Stamp should sleep. The
   * following values are valid:
   *
   * <ul>
   * <li>0 - 16ms
   * <li>1 - 32ms
   * <li>2 - 64ms
   * <li>3 - 128ms
   * <li>4 - 256ms
   * <li>5 - 512ms
   * <li>6 - 1024ms
   * <li>7 - 2048ms
   * </ul>
   */
  public static native void nap(int period);

  /**
   * Count the number of rising or falling edge transitions that occur during
   * a set period of time.
   * If the pin is an output then it will be changed to be an input and will remain as
   * an input when the counting is complete.
   * <p>
   * To ensure reliable detection the input signal must maintain a value for at least
   * 8.68us. This means that a square wave must have a period of at least 17.36us and
   * a frequency less than 57.6kHz.
   *
   * @param timeout the amount of time to count transitions over. Measured in
   *                8.68us units. The value of 32,767 allows a maximum measurement
   *                time of 284.4ms.
   * @param portPin     the I/O pin to measure transitions on.
   * @param edge    the type of edge to count. Pass true to count rising edges,
   *                or false to count falling edges.
   * @return the number of rising edges or falling edges that occured on the pin
   *         during the timeout period.
   */
  public native static int count( int timeout, int portPin, boolean edge );

  /**
   * Measure the length of time it takes for a pin to match a desired value.
   * If the pin is an output then it will be changed to be an input and will
   * remain as an input when rcTime is complete. rcTime can be used to measure
   * the charge/discharge time of a RC (resistor-capacitor) circuit in order to
   * read an analogue value.
   *
   * @param timeout the maximum measurable time.
   * @param portPin the I/O pin to measure.
   * @param pinState the pin state to wait for.
   * @return the amount of time taken for the pin to change to the desired value,
   * measured in 8.68us units. A value of -1 indicates that the timeout expired
   * before the desired state was reached.
   */
  public native static int rcTime( int timeout, int portPin, boolean pinState );

  /**
   * Measure the length of a single pulse. Waits for the value of the selected
   * pin to match the value selected by the state parameter and then times how
   * long it takes for the pin to return to its original state.
   * If the pin is an output then it will be changed to be an input and will
   * remain as an input when complete.
   * <p>
   * A pulse of less than 8.68us in duration may be missed.
   *
   * @param timeout  the maximum amount of time to spend in the pulseIn method.
   *                 The timeout covers the complete time of the process—both
   *                 the wait for the start of the pulse and the time of the pulse.
   * @param portPin      the I/O pin to measure the pulse on.
   * @param pinState    whether to measure a high pulse (true) or a low pulse (false).
   * @return the length of the pulse in 8.68us units. A value of 0 indicates
   *         that no start edge was detected. A value of -1 indicates that no
   *         stop edge was detected.
   */
  public native static int pulseIn( int timeout, int portPin, boolean pinState );

  /**
   * Generate a pulse of a specific duration.If the pin is an input then it will
   * be changed to be an output and remain as an output when complete. To
   * ensure that a positive going pulse or negative going pulse is generated it
   * is a good idea to write a value to the pin first to put it into the correct
   * state. pulseOut inverts the value of the selected pin, waits for the
   * specified length of time, and then returns the pin to its original value.
   * <p>
   * The time is measured in 8.68us units with a maximum value of 32767 giving a
   * maximum pulse time of 284.4ms. A time of 0 will not cause a pulse.
   * pulseOut can be used to generate the control pulses for a servo motor.
   *
   * @param length the length of the pulse to generate, measured in 8.68us units.
   * @param portPin    the I/O to generate the pulse on.
   */
  public native static void pulseOut( int length, int portPin );

  /**
   * Reads the logic value on a pin. If the pin is an output then it will be
   * changed to be an input and will remain as an input when complete.
   *
   * @param portPin the I/O pin to read.
   * @return the state of the pin where true indicates high and false indicates low.
   */
  public native static boolean readPin( int portPin );

  /**
   * Set a pin to a logic state. Makes the selected pin high or low as selected
   * by the state parameter. If the pin is an input then it will be changed to
   * be an output and remain as an output when complete.
   *
   * @param portPin the I/O pin to write.
   * @param value the state the set the pin to.
   */
  public native static void writePin( int portPin, boolean value );

  /**
   * Read data from a synchronous serial device.
   * The clock pin outputs a clock signal and the data is clocked in form the data pin.
   * If the data pin is an output then it will be changed to be an input and remain as
   * an input when complete. If the clock pin is an input then it will be changed to be
   * an output and remain as an output when complete.
   * <p> The mode parameter selects pre/post clock sampling. Note that some devices
   * require pre-clock sampling. When pre-clock sampling is used the data pin is
   * sampled to obtain the first bit prior to any change of the clock pin. Clearly
   * the device must have had a clock pulse to output the first bit and this is
   * normally supplied by the previous call to shiftIn or shiftOut. Most
   * synchronous serial devices require a write (shiftOut) prior to reading and so the
   * clock for the first bit will be supplied by the shiftOut call. If the device must be
   * read without first being written to then the first read (shiftIn) should request an
   * extra bit to provide the initial clock pulse.
   * <p>
   * Note that the bit count can be set to higher than 16 (up to 256) if desired. When
   * a number greater than 16 is used the last 16 bits will be returned.
   *
   * @param dataPortPin the I/O pin to read data on.
   * @param clockPortPin the I/O pin on which to provide the clock to the external device.
   * @param bitCount the number of bits to count in (from 1 to 16). bitCount must not be 0.
   * @param mode the shifting and clocking mode to employ. This determines whether
   * bits are shifted into the least-significant-bit (LSb) or most-significant-bit (MSb)
   * first. The clocking mode determines whether the data is sampled before or after
   * the first clock transition. See the description for the possible values.
   * @return the bits shifted in on the data pin. The result contains bitCount bits and is
   * justified according to the mode setting. If MSb first is selected then the result
   * will be the data read in on the port. If LSb first is selected then the data is
   * returned MSb justified and must be shifted right (16-bitCount) bits to
   * obtain the correct value.
   */
  public native static int shiftIn( int dataPortPin, int clockPortPin, int bitCount, int mode );

  /**
   * Write data to a synchronous serial device.
   * The clock pin outputs a clock signal and the data is clocked out the data pin. If
   * the data pin is an input then it will be changed to be an output and remain as an
   * output when complete. Likewise if the clock pin is an input then it will be
   * changed to be an output and remain as an output when complete.
   * <p>
   * Both the clock and data pins should be initialized before calling shiftOut. shiftOut
   * works by inverting the pins so you can generate either idle high or idle low signals.
   * <p>
   * If LSb first is selected then the data can be passed directly. If MSb first is
   * selected then if less than 16 bits are to be shift out, data should be shifted left
   * to MSb justify it.
   * @param dataPortPin the I/O pin to write data on.
   * @param clockPortPin the I/O pin on which to provide the clock to the external device.
   * @param bitCount the number of bits to count out (from 1 to 16). bitCount must not be 0.
   * @param mode the shifting mode to employ. This determines whether
   * bits are shifted into the least-significant-bit (LSb) or most-significant-bit (MSb)
   * first. Valid values are CPU.SHIFT_MSB and CPU.SHIFT_LSB.
   */
  public native static void shiftOut( int dataPortPin, int clockPortPin, int bitCount, int mode, int data );

  /**
   * Output a value onto a port.
   * The lower 8 bits of value will be written to the port. Pins on the port
   * will <b>not</b> be converted to outputs first. This method does not
   * affect the direction of the port.
   * <p>
   * This method will disturb any virtual peripherals which are using the port.
   *
   * @param port the port to control. Can be either CPU.PORTA, or CPU.PORTB.
   * @param value the value to write to the port.
   */
  public static void writePort(int port, byte value) {
    if ( port == PORTA )
      CPU.writeRegister(0x06, value);
    else if ( port == PORTB )
      CPU.writeRegister(0x07, value);
  }

  /**
   * Read the value on a port.
   * Read the value currently on a port.
   *
   * @param port the port to read. Can be either CPU.PORTA, or CPU.PORTB.
   * @return the value on the port.
   */
  public static byte readPort(int port) {
    if ( port == PORTA )
      return (byte)CPU.readRegister(0x06);
    else if ( port == PORTB )
      return (byte)CPU.readRegister(0x07);
    else
      return 0;
  }

  /**
   * Make a pin an input.
   * The specified pin will be converted to an input. More than one pin can
   * be specified using the + operator, as long as all pins are on the same
   * port.
   *
   * @param portPin the pin to make into an input.
   */
  public native static void setInput(int portPin);

//============================================================================
// Methods and fields below this point are private.
//============================================================================

  // The array of virtual peripherals.
  static VirtualPeripheral vpList[];

  // The SX banks the VPs use.
  final static int nmPeriph0Bank = 0x80;
  final static int nmPeriph1Bank = nmPeriph0Bank + 0x10;
  final static int nmPeriph2Bank = nmPeriph0Bank + 0x20;
  final static int nmPeriph3Bank = nmPeriph0Bank + 0x30;
  final static int nmPeriph4Bank = nmPeriph0Bank + 0x40;
  final static int nmPeriph5Bank = nmPeriph0Bank + 0x50;
  final static int nmBlockBank = 0x70;

  // Private constructor so the class can't be instantiated.
  CPU() {}

  /**
   * Internal method. Do not use.
   */
  public native static void writeRegister( int address, int value );
  /**
   * Internal method. Do not use.
   */
  public native static int readRegister( int address );
  /**
   * Internal method. Do not use.
   */
  public native static void writeObject( int address, Object value );
  /**
   * Internal method. Do not use.
   */
  public static native boolean carry();
  /**
   * Internal method. Do not use.
   */
  public static native void uninstall(int bank);
  /**
   * Internal method. Do not use.
   */
  public static native void install(int routine);
  /**
   * Internal method. Do not use.
   */
  public native static void message( char data[], int length );
  /**
   * Internal method. Do not use.
   */
  public native static void setOutput(int portPin);

  /**
   * Internal method. Do not use.
   */
  // Only lower 8 bits are written.
  public native static void writeSRAM(int addr, int value);
  /**
   * Internal method. Do not use.
   */
  // Only lower 8 bits are read.
  public native static int readSRAM(int addr);

  static {
    // Allocate the array of VPs.
    if ( vpList == null )
      vpList = new VirtualPeripheral[MAX_NUM_VPS];
  }

}