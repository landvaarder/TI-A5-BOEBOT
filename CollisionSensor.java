import stamp.core.*;

public class CollisionSensor {

  private int pin;

  public CollisionSensor(int pin){
   this.pin = pin;
  }

  public boolean getCollision(){
   return  CPU.readPin(pin);
  }
}