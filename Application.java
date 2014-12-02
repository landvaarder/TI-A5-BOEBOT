import stamp.core.*;


public class Application {

  public static void main() {
  Application application = new Application();
  while(true){
  application.collisionPrevention();
  }
  }

  private CollisionDetection detection;
  private Transmission transmission;
  private int ledPin1 = CPU.pin5;
  private int ledPin2 = CPU.pin6;


  public Application(){
  this.detection = new CollisionDetection();
  this.transmission = new Transmission();
  }

  public void collisionPrevention() {
   int collisionCode;
   collisionCode = detection.getCollisionCode();
   switch(collisionCode) {
    case 0:
     //Go bit backwards and than turn right
     transmission.stop();
     transmission.backward();
     CPU.delay(1000);
     transmission.turnRight(180);
     break;
    case 30:
     // straight forward
     transmission.forward();
     System.out.println("0");
     break;
    case 31:
     //Go bit backwards then left
     transmission.stop();
     transmission.backward();
     CPU.delay(1000);
     transmission.turnLeft(90);
     System.out.println("1");
     break;
    case 32:
     //Go bit backwards then right
     transmission.stop();
     transmission.backward();
     CPU.delay(1000);
     transmission.turnRight(90);
     System.out.println("2");
      break;
    case 33:
     //Backwards and half turn
     transmission.stop();
     transmission.backward();
     CPU.delay(1000);
     transmission.turnRight(90);
     transmission.turnRight(90);
     break;
    }
   }
  }
  