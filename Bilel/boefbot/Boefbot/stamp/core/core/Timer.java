/*
 * Copyright © 1999-2003 Celsius Research Ltd. All rights reserved.
 * Do not modify this file.
 */

package stamp.core;

/**
 * A general purpose 32 bit timer.
 *
 * There
 * is only a single instantiation of the timer itself, but there can be
 * multiple <code>Timer</code> objects which use the timer value.
 * The first <code>Timer</code> created starts the timer VP. Subsequent
 * instantiations use the same VP. Caution should be taken that stopping
 * the timer VP does not affect other <code>Timer</code> objects.
 * <p>
 * The timer is a 32 bit counter which is incremented every 8.68us. Therefore:
 * <ul>
 *    <li>1 millisecond = 115</li>
 *    <li>1 second = 115,207</li>
 *    <li>1 minute = 6,912,442</li>
 *    <li>1 hour = 414,746,543</li>
 * </ul>
 * The timer will overflow every 10.36 hours.
 * <p>
 * A timer would typically be used in code like the following:
 * <pre>
 * Timer t = new Timer();
 *
 * t.mark();
 *
 * while (true) {
 *   // Perform some action.
 *
 *   if ( t.timeout(100) ) { // Check for a 100ms timeout.
 *     t.mark();
 *     // Perform a periodic action.
 *   }
 * }
 *
 * </pre>
 * @author Chris Waters
 * @version 1.1 1/29/03
 */
public class Timer extends VirtualPeripheral {

  /**
   * Creates a <code>Timer</code> object. The first instantiation of this class
   * starts the timer VP. Subsequent instantiations use the same VP.
   */
  public Timer() {
    if ( globalTimerVP == null )
      start(this);
  }

  /**
   * Starts the timer VP. The constructor will start the Timer when it is first
   * created. If it is later stopped with <code>stop()</code> then
   * <code>start()</code> will start it again with the same set of parameters.
   * <p>
   * It is an error to call start if the Timer is already running.
   */
  public static void start(Timer vp) {
    // Install the interrupt handler.
    CPU.installVP(vp);
    globalTimerVP = vp;
    globalVPBank = vp.vpBank;

    // Clear the timer.
    CPU.writeRegister( globalVPBank|nmTimer1, 0 );
    CPU.writeRegister( globalVPBank|nmTimer2, 0 );
    CPU.writeRegister( globalVPBank|nmTimer3, 0 );
    CPU.writeRegister( globalVPBank|nmTimer4, 0 );

    // Start the timer.
    init(globalVPBank);
  }

  /**
   * Stops the timer VP.
   */
  public static void stop() {
    if ( globalTimerVP != null )
      CPU.removeVP(globalTimerVP);
    globalTimerVP = null;
  }

  /**
   * Retrieves the high 16 bits of the timer value.
   *
   * @return the high 16 bits of the timer value.
   */
  public int tickHi() {
    return (CPU.readRegister( globalVPBank|nmLatchTimer4 )<<8)|CPU.readRegister( globalVPBank|nmLatchTimer3 );
  }

  /**
   * Retrieves the low 16 bits of the timer value.
   * <p>
   * <code>tickLo()</code> should always be called before <code>tickHi()</code>.
   *
   * @return the low 16 bits of the timer value.
   */
  public int tickLo() {
    Timer.latch(globalVPBank);
    return (CPU.readRegister( globalVPBank|nmLatchTimer2 )<<8)|CPU.readRegister( globalVPBank|nmLatchTimer1 );
  }

  /**
   * Remembers the current timer value. Call this method to mark the start of
   * a timeout period.
   */
  public void mark() {
    startLo = tickLo();
    startHi = tickHi();
  }

  /**
   * Compares the current timer value with the value remembered by the last
   * call to the <code>mark()</code> method. If the difference is greater than
   * or equal to the parameter then returns true.
   *
   * @return true if the timeout period has been exceeded.
   */
  public boolean timeout(int hi, int lo) {
    // Check for timeout of 0.
    if (hi == 0 && lo == 0)
      return true;

    // Get the current timer value.
    int l = tickLo();
    int h = tickHi();

    // Calculate the elapsed time since last mark.
    if (startHi != 0 || startLo != 0) {
      h = h - startHi;
      l = l - startLo;
      if (!CPU.carry())
        h--;
    }

    // Check if elapsed time == timeout value.
    if (h == hi && l == lo)
      return true;

    // Do a 32-bit unsigned compare.
    hi = ~hi;
    lo = -lo;
    if (lo == 0)
      hi++;
    lo = lo + l;
    if (CPU.carry()) {
      hi++;
      if (hi == 0)
        return true;
    }
    hi = hi + h;
    if (CPU.carry())
      return true;
    return false;
  }

  /**
   * Checks whether <code>timeMS</code> milliseconds have elapsed since
   * the last call to <code>mark()</code>.
   *
   * @return true if the timeout period has been exceeded.
   */
  public boolean timeout(int timeMS) {
    return timeout(timeMS/569, (timeMS%569)*115);
  }

  /**
   * Checks whether <code>timeS</code> seconds have elapsed since
   * the last call to <code>mark()</code>. The maximum timeout is 935 seconds.
   *
   * @return true if the timeout period has been exceeded.
   */
  public boolean timeoutSec(int timeS) {
    return timeout(timeS + (timeS >>> 1) + (timeS >>> 2),
      ((timeS & 1) << 15) + (timeS & 3) << 14);
  }

//============================================================================
// Private methods and fields below this point.
//============================================================================

  static Timer globalTimerVP = null;
  static int globalVPBank;
  int startHi, startLo;

  static native void init(int bank);
  static native void latch(int bank);

  final static int nmPeriphISRVec = 0;

  final static int nmLatchTimer1 = 6;
  final static int nmLatchTimer2 = 7;
  final static int nmLatchTimer3 = 8;
  final static int nmLatchTimer4 = 9;

  final static int nmTimer1 = 2;
  final static int nmTimer2 = 3;
  final static int nmTimer3 = 4;
  final static int nmTimer4 = 5;
}