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
  private char[] directions  = { 'L', 'F', 'R'};

/**
*Constructor
*@param - hardware pin numbers
*/
 public Application(int emitterLeft, int recieverLeft, int emitterRight, int recieverRight,
                    int whiskerLeft, int whiskerRight, int servoLeft, int servoRight,
                    int remotePin, int pinLeft, int pinRight, int pinUpperRight, int pinUpperLeft){

   this.detection = new CollisionDetection(emitterLeft, recieverLeft,
                                           emitterRight, recieverRight,
                                           whiskerLeft, whiskerRight);
   this.transmission = new Transmission(servoLeft, servoRight);
   this.irRemote = new IRRemote( remotePin);
   this.lineFollower = new LineFollowerController(pinLeft, pinRight,
                                                  pinUpperRight, pinUpperLeft);
   this.collisionDetection = true;
   this.lineMode = false;
   this.remoteMode = true;
   this.crossWayCounter = 0;
  }

/**
*Main method
*Checks mode and what do
*@param - hardware pin numbers
*/

  public static void main() {
  Profiles profile = new Profiles(1);
   Application application = new Application(profile.getPins(0), profile.getPins(1), profile.getPins(2),
                                             profile.getPins(3), profile.getPins(4), profile.getPins(5),
                                             profile.getPins(6), profile.getPins(7), profile.getPins(8),
                                             profile.getPins(9), profile.getPins(10), profile.getPins(11),
                                             profile.getPins(12));

   while(true){
    if(application.collisionDetection) {
     int checkCollision = application.detection.getCollisionCode();
     if(checkCollision !=0)
      application.collisionDodger(checkCollision);
    }

    int signalCode = application.irRemote.getIRSignalCode();
    if(signalCode != 999 && application.remoteMode) {
     //System.out.println(signalCode);
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

/**
*Collsion avoidance
*reacts on whiskers & infrared
*@param - code of collision (see collisiondetection class)
*/
  private void collisionDodger(int code) {
   switch(code) {
    case 1: //IR, turn around
     transmission.turnAroundWithoutStop();
     break;
    case 3: //Whisper, go left
     transmission.turnAroundWithStop();
     break;
    case 4: //Whisper, go right
     transmission.turnAroundWithStop();
     break;
    case 5: //Whisper, turn around
     transmission.turnAroundWithStop();
     break;
    }
   }

/**
*Remote listener
*reacts on remote control buttons
*@param - code of the remote (see remote class)
*/
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

/**
*Line following
*acts according to linefollowing code
*
*/
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
     //System.out.println("3");
     turnRight();
     break;
    case 4:
     //System.out.println("4");
     turnLeft();
     break;
    case 5:
     //System.out.println("5");
     driveCrossWay();
     break;
    case 99:
     transmission.forward();
   }
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

 private void leftLine() {
  while(lineFollower.getRight())
  transmission.pivoRight();
  while(!lineFollower.getRight())
  transmission.pivoRight();
 }

 private void rightLine() {
  while(lineFollower.getLeft())
  transmission.pivoLeft();
  while(!lineFollower.getLeft())
  transmission.pivoLeft();
 }
/**
*Crossroad detection
*reacts on crossrow and moves according to preferred direction
*/
 private void driveCrossWay() {
   transmission.slowStop();
   switch(directions[crossWayCounter]) {
   case 'R':
    //System.out.println("r");
    rightLine();
    break;
   case 'L':
    //System.out.println("l");
    leftLine();
    break;
   case 'F':
    //System.out.println("f");
    transmission.forward();
    CPU.delay(1000);
    break;
   }
  crossWayCounter++;
 }
}