import stamp.core.*;

/**
 * Put a one line description of your class here.
 * <p>
 * This comment should contain a description of the class. What it
 * is for, what it does and how to use it.
 *
 * You should rename the class and then save it in a file with
 * exactly the same name as the class.
 *
 * @version 1.0 Date
 * @author Your Name Here
 */

public class Application {

  private CollisionDetection collision;

  public Application() {
  collision = new CollisionDetection(5,6);
  }

  public void main() {
   while(true) {
   int collisionCode;
   collisionCode = collision.getCollisionCode();
   switch(collisionCode/10) {
    case 0:
     switch(collisionCode%10) {
      case 0:
       // straight forward
       break;
      case 1:
       //Go bit backwards then left
       break;
      case 2:
       //Go bit backwards then right
       break;
      case 3:
       //Backwards and half turn
       break;
     }
     break;
    case 1:
     // turn around
     break;
    case 2:
     //Go bit backwards and then turn left
     break;
    case 3:
     //Go bit backwards and than turn right
     break;
    }
   }
  }
 }

