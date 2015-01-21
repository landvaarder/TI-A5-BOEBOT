import stamp.core.CPU;

/*
 *@author Tim Schijvenaars & Wesley de Hek
 *@version 5.0
 */

public class Application {

  private CollisionDetection detection;
  private Transmission transmission;
  private IRRemote irRemote;
  private LineFollowerController lineFollower;
  private boolean collisionDetection;
  private boolean lineMode;
  private boolean remoteMode;
  private int crossWayCounter;
  private char[] directions;
  private int maxLength;

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
   this.collisionDetection = false;
   this.lineMode = false;
   this.remoteMode = true;
   this.crossWayCounter = 0;
   this.maxLength = 0;
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
   Bluetooth bluetooth = new Bluetooth(profile.getPins(13),profile.getPins(14),application.transmission,application);

   while(true){

    //Bluetooth:
    bluetooth.readSignal();

    //Line followers:
    if(application.lineMode)
     application.lineFollowing();

     //Collision:
    int checkCollision = application.detection.getCollisionCode();
    if(application.collisionDetection) {
     if(checkCollision !=0)
      application.collisionDodger(checkCollision);
     }
    else if(application.lineMode) {
     if(checkCollision !=0)
      application.lineCollision();
    }

    //Remote:
   int signalCode = application.irRemote.getIRSignalCode();
   if(signalCode != 999 && application.remoteMode) {
    System.out.println(signalCode);
     application.remoteListener(signalCode);
    }
    else if(signalCode == 21) {
     application.setRemoteMode();
    }

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
    case 0: //Nothing
     break;
    default: //Collision with the whispers
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
     setCollisionMode();
     break;
    case 1: //2 - line follower mode (collisonDetection = on && remote = off)
     setLineMode();
     break;
   }
  }

/**
*Line following
*acts according to linefollowing code
*/
  public void lineFollowing() {
   int code = lineFollower.getLineFollowersCode();
   switch(code) {
    case 1:
     transmission.driveLeft();
     break;
    case 2:
     transmission.driveRight();
     break;
    case 3:
     turnRight();
     break;
    case 4:
     turnLeft();
     break;
    case 5:
     driveCrossWay();
     break;
    case 99:
     transmission.forward();
     break;
   }
  }

/**
 * Turn right & left methodes for line following.
 * This methodes lets the bot turn just as long it takes to take a turn.
 **/
 private void turnRight() {
  CPU.delay(1000);
  transmission.slowStop();
  while(lineFollower.getLeft())
  transmission.pivoLeft();
  while(!lineFollower.getLeft())
  transmission.pivoLeft();
 }

 private void turnLeft() {
  CPU.delay(1000);
  transmission.slowStop();
  while(lineFollower.getRight())
  transmission.pivoRight();
  while(!lineFollower.getRight())
  transmission.pivoRight();
 }

/**
*Crossroad detection
*reacts on crossrow and moves according to preferred direction
*/
 private void driveCrossWay() {
  if(maxLength >= crossWayCounter) {
   transmission.slowStop();
   switch(directions[crossWayCounter]) {
   case 'R':
    turnRight();
    break;
   case 'L':
    turnLeft();
    break;
   case 'F':
    transmission.forward();
    CPU.delay(1500);
    break;
   }
  crossWayCounter++;
  }
  else {
   transmission.stop();
   lineMode = false;
  }
 }

 /**
  * sets the new route for the line followers to follow.
  **/
 public void setCrossWays(char[] directions, int length) {
  this.directions = directions;
  this.maxLength = length;
  crossWayCounter = 0;
 }

 /**
  * turns on collision mode
  **/
 public void setCollisionMode() {
  collisionDetection = true;
  remoteMode = false;
  lineMode = false;
 }

 /**
  * turns on line followers mode
  **/
 public void setLineMode() {
  collisionDetection = false;
  remoteMode = false;
  lineMode = true;
 }

 /**
  * Turns on remote mode
  **/
 public void setRemoteMode() {
  remoteMode = true;
  lineMode = false;
  collisionDetection = false;
 }

 /**
  * Line collison while in line followers mode.
  **/
 public void lineCollision() {
  transmission.turnAroundWithStop();
  lineMode = false;
  collisionDetection = false;
  transmission.stop();
 }
}