import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 1.0
* pin 3 = right emitter
* pin 2 = left emitter (green wire))
*
*/

public class CollisionDetection {

  private IRSensor sensorLeft;
  private IRSensor sensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int collisionCode;

  public CollisionDetection(){
    this.sensorLeft = new IRSensor(CPU.pin2, CPU.pin0);
    this.sensorRight = new IRSensor(CPU.pin3, CPU.pin1);
    this.whiskerLeft = new CollisionSensor(CPU.pin10);
    this.whiskerRight = new CollisionSensor(CPU.pin11);
  }

  private void collisionListener(){
   if(collisionCode == 0) {
     boolean left = whiskerLeft.getCollision();
     CPU.delay(2000);
     boolean right = whiskerRight.getCollision();
     if(left && right ) {
     collisionCode += 0;   //Straight forward
     }
     else if (left && !right) {
     collisionCode += 3;   //Go bit backwards then left
     }
     else if (!left && right) {
     collisionCode += 4;   //Go bit backwards then right
     }
     else if (!left && !right)  {
     collisionCode += 5;   //Backwards and half turn
     }
   }
  }

  private void getIRCollision() {
    boolean left = sensorLeft.getCollision();
    boolean right = sensorRight.getCollision();
    if(left) { //hole, turn around
     collisionCode += 1;
    }
    else if (right) { //collision detection, turn around
     collisionCode += 0;
    }
    else if(!left && !right){ //forward
     collisionCode += 0;
    }
  }

  public int getCollisionCode() {
   collisionCode = 0;
   getIRCollision();
   collisionListener();
   return collisionCode;
  }

}