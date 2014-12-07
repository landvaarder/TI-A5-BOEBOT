import stamp.core.*;


public class Application {

  public static void main() {
  Application application = new Application(CPU.pin0,CPU.pin1,CPU.pin2,CPU.pin3,CPU.pin4,CPU.pin5,CPU.pin12,CPU.pin13);
  while(true){
  application.collisionPrevention();
  }
  }

  private CollisionDetection detection;
  private Transmission transmission;
  private RemoteController remote;


  public Application(int leftSensorEmitterPin, int leftSensorRecieverPin,
                     int rightSensorEmitterPin, int rightSensorRecieverPin,
                     int leftWhiskerPin, int rightWhiskerPin,
                     int leftMotorPin, int rightMotorPin,
                     int remotePin){
  this.detection = new CollisionDetection(leftSensorEmitterPin, leftSensorRecieverPin,
                                          rightSensorEmitterPin, rightSensorRecieverPin,
                                          leftWhiskerPin, rightWhiskerPin);
  this.transmission = new Transmission(leftMotorPin, rightMotorPin);
  this.remote = new RemoteController(remotePin);
  }

  public void collisionPrevention(){
  int move = 0;

  detection.collisionListener();
  //detection.getIRCollision();
  move = detection.getCollisionCode();

  switch (move) {
     case 0:
     transmission.forward() ;
     break;
     case 1:
     transmission.stop() ;
     transmission.backward() ;
     transmission.turnLeft(90) ;
     transmission.stop() ;
     break;
     case 2:
     transmission.stop() ;
     transmission.backward() ;
     transmission.turnRight(90) ;
     transmission.stop() ;
     break;
     case 3:
     transmission.stop() ;
     transmission.backward() ;
     transmission.turnRight(90) ;
     transmission.turnRight(90) ;
     transmission.stop() ;
     break;


  }
 }
}