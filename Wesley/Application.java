import stamp.core.*;

public class Application {

  private CollisionDetection detection;
  private Transmission transmission;
  private IRRemote irRemote;
  private LineFollowerController lineFollower;
  private boolean collisionDetection;
  private boolean lineMode;
  private boolean remoteMode;
  private int crossWayCounter;
  private char[] directions  = { 'R', 'F', 'R', 'R' };

 public Application(){
   this.detection = new CollisionDetection();
   this.transmission = new Transmission();
   this.irRemote = new IRRemote(CPU.pin0);
   this.lineFollower = new LineFollowerController();
   this.collisionDetection = true;
   this.lineMode = false;
   this.remoteMode = true;
   this.crossWayCounter = 0;
  }

  public static void main() {
   Application application = new Application();
   while(true){
    if(application.collisionDetection) {
     int checkCollision = application.detection.getCollisionCode();
     if(checkCollision !=0)
      application.collisionDodger(checkCollision);
    }


    int signalCode = application.irRemote.getIRSignalCode();
    if(signalCode != 999 && application.remoteMode) {
     System.out.println(signalCode);
     application.remoteListener(signalCode);
    }
    else if(signalCode == 21) {
     application.remoteMode = true;
     application.lineMode = false;
     application.collisionDetection = false;
    }

    if(application.lineMode)
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

  private void remoteListener(int code) {
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
     transmission.pivot(90);
     break;
    case 18: //vol+
     transmission.pivot(270);
     break;
    case 0: //1 - automatic mode (collisionDetection = on && remote = off)
     collisionDetection = true;
     remoteMode = false;
     break;
    case 1: //2 - line follower mode (collisonDetection = on && remote = off)
     collisionDetection = false;
     remoteMode = false;
     lineMode = true;
     break;
   }
  }

  public void lineFollowing() {

   int code = lineFollower.getLineFollowersCode();
   switch(code) {
    case 1:
     transmission.driveRight();
     break;
    case 2:
     transmission.driveLeft();
     break;
    case 3:
     System.out.println("3");
     turnRight();
     break;
    case 4:
     System.out.println("4");
     turnLeft();
     break;
    case 5:
     System.out.println("5");
     driveCrossWay();
     break;
    case 99:
     transmission.forward();
   }
  }

  /*private void pivotLeft(int angle) {
   if(transmission.getMotorDir() == 1)
    transmission.pivotLeft(360);
   else if(transmission.getMotorDir() == 2)
    transmission.pivotRight(angle);
   else
    transmission.pivotLeft(angle);
  }  */

 /* private void pivotRight(int angle) {
   if(transmission.getMotorDir() == 1)
    transmission.pivotRight(angle);
   else if(transmission.getMotorDir() == 2)
    transmission.pivotLeft(angle);
   else
    transmission.pivotRight(angle);
  }     */

  private void turnLeftWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivot(90);
   transmission.forward();
  }

  private void turnRightWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivot(90);
   transmission.forward();
  }

  private void turnAroundWithStop() {
   transmission.slowStop();
   transmission.backward();
   CPU.delay(5000);
   transmission.pivot(180);
   transmission.forward();
  }

  private void tunrAroundWithoutStop() {
   transmission.accelerateBackward();
   CPU.delay(5000);
   transmission.pivot(180);
   transmission.forward();
  }

  private void turnRight() {
  while(lineFollower.getLeft())
  transmission.driveLeft();
  while(!lineFollower.getLeft())
  transmission.driveLeft();
 }

 private void turnLeft() {
  while(lineFollower.getRight())
  transmission.driveRight();
  while(!lineFollower.getRight())
  transmission.driveRight();
 }

 private void linePivoLeft() {
  while(lineFollower.getRight())
  transmission.pivoRight();
  while(!lineFollower.getRight())
  transmission.pivoRight();
 }

 private void linePivoRight() {
  while(lineFollower.getLeft())
  transmission.pivoLeft();
  while(!lineFollower.getLeft())
  transmission.pivoLeft();
 }

 private void driveCrossWay() {
   transmission.slowStop();
   switch(directions[crossWayCounter]) {
   case 'R':
    System.out.println("r");
    linePivoRight();
    break;
   case 'L':
    System.out.println("l");
    linePivoLeft();
    break;
   case 'F':
    System.out.println("f");
    transmission.forward();
    CPU.delay(1000);
    break;
   }
  crossWayCounter++;
 }
}