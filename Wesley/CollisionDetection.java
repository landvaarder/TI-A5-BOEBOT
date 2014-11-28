import stamp.core.*;

/**
*@author = Tim Schijvenaars
*@version = 1.0
*/

public class CollisionDetection {

  private IRSensor irSensorLeft;
  private IRSensor irSensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int move;

  /*
  *Constructor
  *@param = pins for sensors
  */

  public CollisionDetection(int pinWhiskerLeft, int pinWhiskerRight){
    CollisionSensor whiskerLeft = new CollisionSensor(pinWhiskerLeft);
    CollisionSensor whiskerRight = new CollisionSensor(pinWhiskerRight);
    irSensorLeft = new IRSensor(1,2); //@param emitter pin, reciever pin
    irSensorRight = new IRSensor(3,4); //@param emitter pin, reciever pin
  }

  private int collisionListener() {

     if(whiskerLeft.getCollision()&& whiskerRight.getCollision()){
     return 0;   //Straight forward
     } else if (whiskerLeft.getCollision() && !whiskerRight.getCollision()) {
     return 1;   //Go bit backwards then left
     } else if (!whiskerLeft.getCollision() && whiskerRight.getCollision()) {
     return 2;   //Go bit backwards then right
     } else {
     return 3;   //Backwards and half turn
     }
  }

  private int irSensorListener(){
   boolean irLeft = irSensorLeft.getIRCollision();
   boolean irRight = irSensorRight.getIRCollision();
   if( irLeft && irRight )
    return 1; //turn around
   else if( !irLeft && irRight)
    return 2; //Go bit backwards and then turn left
   else if ( irLeft && !irRight)
    return 3; //Go bit backwards and than turn right
   else
    return 0; //no Collision, move forward
  }

  public int getCollisionCode() {
   int collisionCode = collisionListener();
   collisionCode += irSensorListener()*10;
   return collisionCode;
  }



 }