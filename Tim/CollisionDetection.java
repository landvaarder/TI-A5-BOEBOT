import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 1.0
*/

public class CollisionDetection {

//private IRSensor sensorLeft;
//private IRSensor sensorRight;
  private CollisionSensor whiskerLeft;
  private CollisionSensor whiskerRight;
  private int move;

  /*
  *Constructor
  *@param = pins for sensors
  */

  public CollisionDetection(){
   //IRSensor sensorLeft = new IRSensor(pinIRLeft);
   //IRSensor sensorRight = new IRSensor(pinIRRight);
    this.whiskerLeft = new CollisionSensor(CPU.pin2);
    this.whiskerRight = new CollisionSensor(CPU.pin3);
    this.move = 0;
  }

  public void collisionListener(){

     if(whiskerLeft.getCollision()&& whiskerRight.getCollision()){
     move = 0;   //Straight forward
     } else if (whiskerLeft.getCollision() && !whiskerRight.getCollision()) {
     move = 1;   //Go bit backwards then left
     } else if (!whiskerLeft.getCollision() && whiskerRight.getCollision()) {
     move = 2;   //Go bit backwards then right
     } else if (!whiskerLeft.getCollision() && !whiskerRight.getCollision())  {
     move = 3;   //Backwards and half turn
     }
  }

  public int getMove(){
   return move;
  }

 }