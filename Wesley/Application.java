import stamp.core.*;

public class Application {

  private CollisionDetection detection;
  private Transmission transmission;
  private IRRemote irRemote;
  private LineFollowerController lineFollower;
  private boolean collisionDetection;
  private boolean lineMode;
  private boolean remoteMode;

 public Application(){
   this.detection = new CollisionDetection();
   this.transmission = new Transmission();
   this.irRemote = new IRRemote(CPU.pin0);
   this.lineFollower = new LineFollowerController();
   this.collisionDetection = true;
   this.lineMode = false;
   this.remoteMode = true;
  }

  public static void main() {
   Application application = new Application();
   while(true){
    int checkCollision = application.detection.getCollisionCode();
    if(checkCollision !=0 && application.collisionDetection)
     application.collisionDodger(checkCollision);
    else if(application.remoteMode && application.remoteMode) {
     int signalCode = application.irRemote.getIRSignalCode();
     if(signalCode != 999) {
      System.out.println(signalCode);
      application.remote(signalCode);
     }
    }
    else if(application.lineMode)
     application.lineFollowing();
   }
  }

  private void collisionDodger(int code) {
   switch(code) {
    case 1: //IR, turn around
     tunrAroundWithoutStop();
     break;
    case 3: //Whisper, go left
     turnAroundWithStop();
     break;
    case 4: //Whisper, go right
     turnAroundWithStop();
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
    case 88: //ch+
     transmission.forward();
     break;
    case 89: //ch-
     transmission.backward();
     break;
    case 19: //vol -
     transmission.pivotLeft(90);
     break;
    case 18: //vol+
     transmission.pivotRight(90);
     break;
    case 0: //1 - automatic mode (collisionDetection = on && remote = off)
     collisionDetection = true;
     remoteMode = false;
     break;
    case 1: //2 - line follower mode (collisonDetection = on && remote = off)
     collisionDetection = true;
     remoteMode = false;
     lineMode = true;
     break;
   }
  }

  public void lineFollowing() {
   //check remote button
   int code = lineFollower.getLineFollowersCode();
   if(code == 1) //line detected on the left turn right.
    transmission.driveRight();
   else if(code == 2) //line detected on the right turn left.
    transmission.driveLeft();
   else
    transmission.forward();
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