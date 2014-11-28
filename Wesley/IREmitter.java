import stamp.core.CPU;
import stamp.core.PWM;

/**
 * IR Beamer class which has methodes to send out a beam of IR light.
 *
 * @version 1.0 11-26-2014
 * @author Wesley
 */

public class IREmitter {

  PWM ledBeam;

  public IREmitter(int IRLedPin) {
    ledBeam = new PWM(CPU.pins[IRLedPin],1,2);
  }

  public void startBeam() {
   ledBeam.start();
  }

  public void stopBeam() {
   ledBeam.stop();
  }
}