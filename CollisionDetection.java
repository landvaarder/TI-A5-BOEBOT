import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 3.0
*
*/

public class CollisionDetection {

  private IRSensor sensorLeft;
  private IRSensor sensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int collisionCode;

  public CollisionDetection(int emitterLeft, int recieverLeft,
                            int emitterRight, int recieverRight,
                            int whiskerLeft, int whiskerRight){
    this.sensorLeft = new IRSensor(emitterLeft, recieverLeft);
    this.sensorRight = new IRSensor(emitterRight, recieverRight);
    this.whiskerLeft = new CollisionSensor(whiskerLeft);
    this.whiskerRight = new CollisionSensor(whiskerRight);
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