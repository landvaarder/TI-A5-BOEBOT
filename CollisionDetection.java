/*
*@author = Tim Schijvenaars & Wesley de Hek
*@version = 4.0
*/

public class CollisionDetection {

  private IRSensor sensorLeft;
  private IRSensor sensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int collisionCode;
  private boolean ir = false;

  public CollisionDetection(int emitterLeft, int recieverLeft, int emitterRight, int recieverRight, int whiskerLeft, int whiskerRight) {
    this.sensorLeft = new IRSensor(emitterLeft, recieverLeft);
    this.sensorRight = new IRSensor(emitterRight, recieverRight);
    this.whiskerLeft = new CollisionSensor(whiskerLeft);
    this.whiskerRight = new CollisionSensor(whiskerRight);
  }

  private void collisionListener(){
   if(collisionCode == 0) {
     boolean left = whiskerLeft.getCollision();
     boolean right = whiskerRight.getCollision();
     if(left && right ) {
     collisionCode += 0;   //Straight forward.
     }
     else if (left && !right) {
     collisionCode += 3;   //Go bit backwards then left.
     }
     else if (!left && right) {
     collisionCode += 4;   //Go bit backwards then right.
     }
     else if (!left && !right)  {
     collisionCode += 5;   //Backwards and half turn.
     }
   }
  }

  private void getIRCollision() {
    boolean left = sensorLeft.getCollision();
    boolean right = sensorRight.getCollision();
    if(left) { //hole, turn aroun.d
     collisionCode += 1;
    }
    else //nothing, straight forward.
     collisionCode +=0;
  }

 /**
  * Gets the collision with the proper action in vorm of a code
  **/
  public int getCollisionCode() {
   collisionCode = 0;
   if(ir)
   getIRCollision();
   collisionListener();
   return collisionCode;
  }

}