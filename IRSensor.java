import stamp.core.*;

/**
* @version 2.0
* @author Wesley van de Hek
* @revised by Tim Schijvenaars
*/


public class IRSensor {

  private IREmitter irEmitter;
  private IRReciever irReciever;
  private boolean collision;

  public IRSensor(int emitterPin, int recieverPin) {
   this.irEmitter = new IREmitter(emitterPin);
   this.irReciever = new IRReciever(recieverPin);
  }

  public void runIRCollision() {
   irEmitter.startBeam();
   CPU.delay(5);
   this.collision = irReciever.checkSignal();
   irEmitter.stopBeam();
  }

  public boolean getCollision(){
   return collision;
  }
}