/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * Use the EEPROM on the Javelin board for persistent storage.
 *
 * @author Chris Waters
 * @version 1.0 5/14/00
 */
public class EEPROM {

  /**
   * Returns the size of the EEPROM in bytes. The size depends upon how much
   * of the EEPROM is taken up with the Java code.
   *
   * @return size of the available EEPROM space in bytes.
   */
  public static int size() {
    return EEPROM_SIZE - eepromBase;
  }

  /**
   * Writes a byte into the EEPROM at the specified address.
   *
   * @param address the address to write the byte to.
   * @param value value to write.
   */
  public static void write(int address, byte value) {
    if ( address > size() )
      throw IndexOutOfBoundsException.throwIt();
    eeWrite(EEPROM_SIZE-address, value);
  }

  /**
   * Reads a byte from the EEPROM at the specified address.
   *
   * @param address the address to read the byte from.
   * @return the byte read from the EEPROM.
   */
  public static byte read(int address) {
    if ( address > size() )
      throw IndexOutOfBoundsException.throwIt();
    return eeRead(EEPROM_SIZE-address);
  }

//============================================================================
// Methods and fields below this point are private.
//============================================================================

  final static int JVM_SB_MSB = 0x4C;
  final static int JVM_SB_LSB = 0x4D;

  final static int EEPROM_SIZE = (32*1024)-1;

  static int eepromBase;

  protected static native void eeWrite(int address, byte data);
  protected static native byte eeRead(int address);

  static {
    // Work out where the end of the program is in the EEPROM.
    eepromBase = (CPU.readRegister(JVM_SB_MSB)<<8)|CPU.readRegister(JVM_SB_LSB);
  }
}