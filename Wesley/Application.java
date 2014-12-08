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
    case 116: //arrow up
     transmission.forward();
     break;
    case 117: //arrow down
     transmission.backward();
     break;
    case 51: //arrow right
     transmission.turnRight(90);
     break;
    case 52: //arrow left
     transmission.turnLeft(90);
     break;
    case 21: //power
     transmission.stop();
     break;
    case 16: //ch+
     transmission.upSpeed();
     break;
    case 17: //ch-
     transmission.downSpeed();
     break;
    case 0: //1
     pivotLeft(90);
     break;
    case 2: //3
     pivotRight(90);
     break;
    case 1: //2
     transmission.forward();
     break;
    case 4: //5
     transmission.stop();
     break;
    case 3: //4
     transmission.turnLeft(90);
     break;
    case 5: //6
     transmission.turnRight(90);
     break;
    case 7: //8
     transmission.backward();
     break;

   }
  }

  private void pivotLeft(int angle) {
   if(transmission.getMotorDir() == 1)
    transmission.pivotLeft(angle);
   else if(transmission.getMotorDir() == 2)
    transmission.pivotRight(angle);
   else
    transmission.pivotLeft(angle);
  }

  private void pivotRight(int angle) {
   if(transmission.getMotorDir() == 1)
    transmission.pivotRight(angle);
   else if(transmission.getMotorDir() == 2)
    transmission.pivotLeft(angle);
   else
    transmission.pivotRight(angle);
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
   CPU.delay(5000);
   transmission.pivotLeft(180);
   transmission.forward();
  }
















}