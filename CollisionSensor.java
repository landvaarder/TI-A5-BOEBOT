import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 1.0
*
*
*/

public class CollisionSensor {

  private int pin;

  public CollisionSensor(int pin){
   this.pin = pin;
  }

  public boolean getCollision(){
   return  CPU.readPin(pin);
  }
}