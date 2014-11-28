import stamp.core.*;
/**
 * Put a one line description of your class here.
 * <p>
 * This comment should contain a description of the class. What it
 * is for, what it does and how to use it.
 *
 * You should rename the class and then save it in a file with
 * exactly the same name as the class.
 *
 * @version 1.0 Date
 * @author Your Name Here
 */

public class IRSensor {

  private IREmitter irEmitter;
  private IRReciever irReciever;
  private boolean collision;

  public IRSensor(int emitterPin, int recieverPin) {
   irEmitter = new IREmitter(emitterPin);
   irReciever = new IRReciever(recieverPin);
  }

  public boolean getIRCollision() {
   irEmitter.startBeam();
   CPU.delay(5);
   collision = irReciever.checkSignal();
   irEmitter.stopBeam();
   return collision;
  }
}


