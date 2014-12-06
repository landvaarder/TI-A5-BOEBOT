import stamp.core.*;

public class Application {

  private CollisionDetection detection;
  private Transmission transmission;
  private IRRemote irRemote;

 public Application(){
   this.detection = new CollisionDetection();
   this.transmission = new Transmission();
   this.irRemote = new IRRemote(CPU.pin0);
  }

  public static void main() {
   Application application = new Application();
   while(true){
    int checkCollision = application.detection.getCollisionCode();
    if(checkCollision !=0)
     application.collisionDodger(checkCollision);
    else {
     int signalCode = application.irRemote.getIRSignalCode();
     if(signalCode != 999) {
      System.out.println(signalCode);
      application.remote(signalCode);
     }
    }
   }
  }

  private void collisionDodger(int code) {
   switch(code) {
    case 1: //IR, turn around
     tunrAroundWithoutStop();
     break;
    case 3: //Whisper, go left
     turnLeftWithStop();
     break;
    case 4: //Whisper, go right
     turnRightWithStop();
     break;
    case 5: //Whisper, turn around
     turnAroundWithStop();
     break;

    }
   }

  private void remote(int code) {
   switch(code) {
    case 116:
     transmission.forward();
     break;
    case 117:
     transmission.backward();
     break;
    case 51:
     transmission.turnRight(90);
     break;
    case 52:
     break;
    case 21:
     transmission.stop();
     break;
    case 16:
     transmission.upSpeed();
     break;
    case 17:
     transmission.downSpeed();
     break;
    case 5:
     transmission.pivotRight(90);
     break;
    case 3:
     transmission.pivotLeft(360);

   }
  }

  private void turnLeftWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivotLeft(90);
   transmission.forward();
  }

  private void turnRightWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivotLeft(90);
   transmission.forward();
  }

  private void turnAroundWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivotLeft(180);
   transmission.forward();
  }

  private void tunrAroundWithoutStop() {
   transmission.accelerateBackward();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivotLeft(180);
   transmission.forward();
  }
















}