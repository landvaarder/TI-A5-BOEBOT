import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 1.0
*/

public class CollisionDetection {

  private IRSensor sensorLeft;
  private IRSensor sensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int collisionCode;
  ;

  /*
  *Constructor
  *@param = pins for sensors
  */

  public CollisionDetection(){
    this.sensorLeft = new IRSensor(CPU.pin3, CPU.pin0);
    this.sensorRight = new IRSensor(CPU.pin2, CPU.pin1);
    this.whiskerLeft = new CollisionSensor(CPU.pin9);
    this.whiskerRight = new CollisionSensor(CPU.pin11);
  }

  public void collisionListener(){
     boolean left = whiskerLeft.getCollision();
     boolean right = whiskerRight.getCollision();
     if(left && right ) {
     collisionCode = 0;   //Straight forward
     }
     else if (left && !right) {
     collisionCode = 1;   //Go bit backwards then left
     }
     else if (!left && right) {
     collisionCode = 2;   //Go bit backwards then right
     }
     else if (!left && !right)  {
     collisionCode = 3;   //Backwards and half turn
     }
  }

  public void getIRCollision() {
    sensorLeft.runIRCollision();
    sensorRight.runIRCollision();
    boolean left = sensorLeft.getCollision();
    boolean right = sensorRight.getCollision();
    if(left && right){
     collisionCode += 0;
    }
    else if (left && !right){
     collisionCode += 0;
    }
    else if (!left && right){
     collisionCode += 0;
    }
    else if(!left && !right){
     collisionCode += 30;
    }
  }

  public int getCollisionCode() {
   collisionCode = 0;
   collisionListener();
   getIRCollision();
   return collisionCode;
  }

}