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

public class IRCollisionDetector {

  private IRBeam irBeamer1;
  private IRBeam irBeamer2;
  private IRReciever irReciever1;
  private IRReciever irReciever2;
  private int collisionCode;

  public IRCollisionDetector() {
   irBeamer1 = new IRBeam(1);
   irBeamer2 = new IRBeam(2);
   irReciever1 = new IRReciever(3);
   irReciever2 = new IRReciever(4);
  }

  public int getIRCollision() {
   collisionCode = 0;
   irBeamer1.startBeam();
   CPU.delay(5);
   if(irReciever1.checkSignal())
   collisionCode++;
   irBeamer1.stopBeam();
   irBeamer2.startBeam();
   CPU.delay(5);
   if(irReciever2.checkSignal())
   collisionCode++;
   irBeamer2.stopBeam();
   return collisionCode;
  }
}


