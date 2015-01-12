/*
 * Copyright © 1999-2002 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * A virtual peripheral UART. Each instance of this class provides
 * asynchronous serial communication in a single direction. For full-duplex
 * communications create two <code>Uart</code> objects, one for each direction.
 * <p>
 * The Uart class uses 256 byte buffers for both transmit and receive. The buffer
 * size is not configurable. If a handshaking pin is specified for a receive Uart then
 * the peer will be signalled to stop transmitting when there are 16 bytes still free
 * in the buffer.
 *
 * @author Chris Waters
 * @author Nick Kelsey
 */

public class Uart extends VirtualPeripheral {

  /**
   * The value to be using in the direction parameter to create a receiver.
   */
  public final static int dirReceive = 1;
  /**
   * The value to be using in the direction parameter to create a transmitter.
   */
  public final static int dirTransmit = 0;

  /*
   * The baud rate speed values are computed by:
   *
   *   speed = 25,000,000/(baud rate * 217)
   */

  /**
   * The speed value for 600 baud.
   */
  public final static int speed600 = 192;
  /**
   * The speed value for 1200 baud.
   */
  public final static int speed1200 = 96;
  /**
   * The speed value for 2400 baud.
   */
  public final static int speed2400 = 48;
  /**
   * The speed value for 4800 baud.
   */
  public final static int speed4800 = 24;
  /**
   * The speed value for 7200 baud.
   */
  public final static int speed7200 = 16;
  /**
   * The speed value for 9600 baud.
   */
  public final static int speed9600 = 12;
  /**
   * The speed value for 14400 baud.
   */
  public final static int speed14400 = 8;
  /**
   * The speed value for 19200 baud.
   */
  public final static int speed19200 = 6;
  /**
   * The speed value for 28800 baud.
   */
  public final static int speed28800 = 4;
  /**
   * The speed value for 38400 baud.
   */
  public final static int speed38400 = 3;
  /**
   * The speed value for 57600 baud.
   */
  public final static int speed57600 = 2;

  /**
   * The value for dataInvert and hsInvert to indicate that inverted logic
   * should be used.
   */
  public final static boolean invert = true;
  /**
   * The value for dataInvert and hsInvert to indicate that inverted logic
   * should not be used.
   */
  public final static boolean dontInvert = false;

  /**
   * Value to generate 1 stop bit.
   */
  public final static int stop1 = 1;
  /**
   * Value to generate 2 stop bits.
   */
  public final static int stop2 = 3;
  /**
   * Value to generate 3 stop bits.
   */
  public final static int stop3 = 7;
  /**
   * Value to generate 4 stop bits.
   */
  public final static int stop4 = 15;
  /**
   * Value to generate 5 stop bits.
   */
  public final static int stop5 = 31;
  /**
   * Value to generate 6 stop bits.
   */
  public final static int stop6 = 63;
  /**
   * Value to generate 7 stop bits.
   */
  public final static int stop7 = 127;
  /**
   * Value to generate 8 stop bits.
   */
  public final static int stop8 = 255;

  /**
   * Creates and initialises a new Uart for simplex communication using no
   * handshaking. The constructor
   * calls the <code>start()</code> method so the Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public Uart(int direction, int dataPin, boolean dataInvert, int baudRate, int stopBits) {
    this( direction, dataPin, dataInvert, 0, dontInvert, baudRate, stopBits );
  }

  /**
   * Creates and initialises a new Uart for simplex communication using
   * hardware (RTS/CTS) handshaking. The constructor
   * calls the <code>start()</code> method so the Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param hsPin      the pin to use for hardware handshaking.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public Uart(int direction, int dataPin, boolean dataInvert, int hsPin, int baudRate, int stopBits) {
    this( direction, dataPin, dataInvert, hsPin, dataInvert, baudRate, stopBits );
  }

  /**
   * Creates and initialises a new Uart for simplex communication using
   * hardware (RTS/CTS) handshaking. The constructor
   * calls the <code>start()</code> method so the Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param hsPin      the pin to use for hardware handshaking.
   * @param hsInvert   whether the logic of the handshaking pin should be inverted or
   *                   not.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public Uart(int direction, int dataPin, boolean dataInvert, int hsPin, boolean hsInvert, int baudRate, int stopBits) {
    this.direction = direction;
    this.dataPin = dataPin;
    this.dataInvert = dataInvert;
    this.hsPin = hsPin;
    this.hsInvert = hsInvert;
    this.baudRate = baudRate;
    this.stopBits = stopBits;

    // Allocate the buffer.
    buffer = new int[stdBufferSize];

    start();
  }

  /**
   * Starts the Uart running. The constructor will start the Uart when it is first
   * created. If it is later stopped with <code>stop()</code> then
   * <code>start()</code> will start it again with the same set of parameters.
   */
  public void start() {
    // Install the interrupt handler.
    CPU.installVP( this );

    // Configure the VP registers.
    CPU.writeRegister( vpBank|nmUartPort, dataPin>>8 );
    CPU.writeRegister( vpBank|nmUartPin, dataPin );
    CPU.writeRegister( vpBank|nmUartHSPort, hsPin>>8 );
    CPU.writeRegister( vpBank|nmUartHSPin, hsPin );
    CPU.writeRegister( vpBank|nmUartInvert, (dataInvert?1:0)|(hsInvert?2:0) );
    CPU.writeRegister( vpBank|nmUartBaud, baudRate );
    CPU.writeRegister( vpBank|nmUartStopBits, stopBits );

    CPU.writeObject( vpBank|nmUartBufferH, buffer );

    // Initialise the Uart VP.
    if ( direction == dirTransmit )
      txInit(vpBank);
    else
      rxInit(vpBank);
  }

  /**
   * Stops the Uart.
   */
  public void stop() {
    CPU.removeVP(this);
  }

  /**
   * Change transmission direction.
   *
   * @param direction new direction
   */
  public void setDirection(int direction) {
    stop();
    this.direction = direction;
    start();
  }

  /**
   * Reinitialises the Uart for simplex communication using no
   * handshaking. The Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public void restart (int direction, int dataPin, boolean dataInvert, int baudRate, int stopBits) {
    restart ( direction, dataPin, dataInvert, 0, dontInvert, baudRate, stopBits );
  }

  /**
   * Reinitialises the Uart for simplex communication using
   * hardware (RTS/CTS) handshaking. The Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param hsPin      the pin to use for hardware handshaking.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public void restart(int direction, int dataPin, boolean dataInvert, int hsPin, int baudRate, int stopBits) {
    restart( direction, dataPin, dataInvert, hsPin, dataInvert, baudRate, stopBits );
  }


  /**
   * Reinitialises a Uart for simplex communication using
   * hardware (RTS/CTS) handshaking. The Uart will begin working immediately.
   *
   * @param direction  whether the Uart is to be used transmit or receive mode. Valid
   *                   options are <code>Uart.dirTransmit</code> and
   *                   <code>Uart.dirReceive</code>.
   * @param dataPin    the Javelin Stamp I/O pin to use for sending or receiving data.
   * @param dataInvert whether the data should be inverted or not. Valid options are
   *                   <code>Uart.invert</code> and <code>Uart.dontInvert</code>.
   * @param hsPin      the pin to use for hardware handshaking
   * @param hsInvert   whether the logic of the handshaking pin should be inverted or
   *                   not.
   * @param baudRate   the baud rate to use.
   * @param stopBits   the number of stop bits to use. Valid values are between
   *                   <code>Uart.stop1</code> and <code>Uart.stop8</code>.
   */
  public void restart (int direction, int dataPin, boolean dataInvert, int hsPin, boolean hsInvert, int baudRate, int stopBits) {
    stop () ;
    this.direction = direction;
    this.dataPin = dataPin;
    this.dataInvert = dataInvert;
    this.hsPin = hsPin;
    this.hsInvert = hsInvert;
    this.baudRate = baudRate;
    this.stopBits = stopBits;
    start();
  }


  /**
   * Check for space in the transmit buffer. If the VP is not
   * install say that the buffer is full.
   *
   * @return transmit buffer is full
   */
  public boolean sendBufferFull() {
    if ( vpBank == NOT_INSTALLED ) {
      return true;
    }
    return !(((CPU.readRegister( vpBank|nmUartHead )
      - CPU.readRegister( vpBank|nmUartTail ))&0xff) < 0xff );
  }

  /**
   * Check space in the transmit buffer.
   *
   * @return transmit buffer is empty (and nothing being sent now)
   */
  public boolean sendBufferEmpty() {
    if ( vpBank == NOT_INSTALLED ) {
      return true;
    }

    return CPU.readRegister( vpBank|nmUartDataL ) == 0
      && CPU.readRegister( vpBank|nmUartDataH ) == 0
      && CPU.readRegister( vpBank|nmUartHead )
        == CPU.readRegister( vpBank|nmUartTail );
  }

  /**
   * Adds a byte to the transmit buffer. The byte is queued for transmit at the
   * end of the buffer. If the buffer is already full then <code>sendByte()</code>
   * will block until there is room in the buffer.
   *
   * @param data the data to transmit. Only the low 8 bits of the parameter
   * are used.
   */
  public void sendByte( int data ) {
    if ( vpBank == NOT_INSTALLED ) {
      return;
    }

    while ( true ) {  // Wait indefinitely.
      int headPointer = CPU.readRegister( vpBank|nmUartHead );
      int tailPointer = CPU.readRegister( vpBank|nmUartTail );

      if ( ((headPointer - tailPointer)&0xff) < 0xff ) {
        int index = headPointer>>1;
        // There is space in the buffer.
        if ( (headPointer&1) == 0 )
          buffer[index] = (buffer[index] & (short)0x00ff) | (data<<8);
        else
          buffer[index] = (buffer[index] & (short)0xff00) | (data&0x00ff);
        headPointer++;
        CPU.writeRegister( vpBank|nmUartHead, headPointer );
        return;
      }
      else
        headPointer = 1;
    }
  }

  /**
   * Transmits a String.
   *
   * @param data the string to transmit.
   */
  public void sendString( String data ) {
    int l = data.length() ;

    for ( int i = 0; i < l; i++ )
      sendByte( (byte)data.charAt(i));
  }

  /**
   * Receives a byte from a receive Uart. This method will block until a
   * byte is available.
   *
   * @return the next byte in the receive buffer.
   */
  public int receiveByte() {
    int b;

    if ( vpBank == NOT_INSTALLED ) {
      return -1 ;
    }

    while ( true ) {
      int headPointer = CPU.readRegister( vpBank|nmUartHead );
      int tailPointer = CPU.readRegister( vpBank|nmUartTail );

      if ( headPointer != tailPointer ) {
        int index = tailPointer>>1;

        if ( (tailPointer&1) == 0 )
          b = (byte)(buffer[index] >>> 8);
        else
          b = (byte)(buffer[index] & 0x00ff);

        tailPointer++;
        CPU.writeRegister( vpBank|nmUartTail, tailPointer );
        break;
      }
    }
    // Inform the receiver so it can do the handshaking.
    rxRead(vpBank);

    return b;
  }

  /**
   * Test whether any bytes are available in the receive buffer.
   * Can also be used to test if the transmit buffer is empty.
   *
   * @return true if there is at least one byte available in the receive buffer,
   * or at least one byte free in the transmit buffer.
   */
  public boolean byteAvailable() {
    if ( vpBank == NOT_INSTALLED ) {
      return false;
    }

    int headPointer = CPU.readRegister( vpBank|nmUartHead );
    int tailPointer = CPU.readRegister( vpBank|nmUartTail );

    return ( headPointer != tailPointer );
  }

//============================================================================
// Private methods and fields below this point.
//============================================================================

  int direction;
  int dataPin;
  boolean dataInvert;
  int hsPin;
  boolean hsInvert;
  int baudRate;
  int stopBits;

  static native void txInit( int bank );
  static native void rxInit( int bank );
  static native void rxRead( int bank );

  final static int nmUartDataH = 0x2;
  final static int nmUartDataL = 0x3;

  final static int nmUartInvert = 0x5; // 0 for normal, 1 for invert
  final static int nmUartBaud = 0x6; // 1000000/(bps * 4.34)
  final static int nmUartStopBits = 0x7; // Tx only: 00000001 for 1 stop bit, 00000011 for 2 etc

  final static int nmUartPort = 0xC; // 0 for Port0, 1 for Port1
  final static int nmUartPin = 0xD; // bit mask (1 of 8 set)
  final static int nmUartHSPort = 0xE; // 0 for Port0, 1 for Port1
  final static int nmUartHSPin = 0xF; // bitmask 1 of 8 set)

  final static int nmUartBufferH = 0xA;
  final static int nmUartBufferL = 0xB;
  // Index of the next free byte in the buffer.
  final static int nmUartHead = 0x8;
  // Index of the oldest byte in the buffer. If tailPointer == headPointer the
  // buffer is empty.
  final static int nmUartTail = 0x9;

  final static int stdBufferSize = 128;

  // Transmit and receive buffer storage.
  int buffer[];
}