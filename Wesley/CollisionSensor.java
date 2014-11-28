import stamp.core.CPU;

public class CollisionSensor {

  private boolean isPositive;
  private int pin;

  public CollisionSensor(int pin){
  isPositive = true;
  this.pin = pin;
  }

  public boolean getCollision(){
  isPositive = CPU.readPin(pin);
  return isPositive;
  }
}