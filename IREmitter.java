import stamp.core.CPU;
import stamp.core.PWM;

/**
 * IR Beamer class which has methodes to send out a beam of IR light.
 * dateFormat: mm-dd-yyyy
 * @version 2.0 12-04-2014
 * @author Wesley
 */

public class IREmitter {

  private PWM ledBeam;

  public IREmitter(int pin) {
    this.ledBeam = new PWM(pin,1,2);
  }

  public void startBeam() {
   ledBeam.start();
  }

  public void stopBeam() {
   ledBeam.stop();
  }
}