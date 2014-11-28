import stamp.core.*;

public class CollisionSensor {

  private boolean isPositive;
  private int pin;

  public CollisionSensor(int pin){
  this.isPositive = true;
  this.pin = pin;
  }

  public boolean getCollision(){
  isPositive = CPU.readPin(pin);
  return isPositive;
  }
}