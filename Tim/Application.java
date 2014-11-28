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


  public Application(){

  this.detection = new CollisionDetection();
  this.transmission = new Transmission();
  }

  public void collisionPrevention(){
  int move = 0;
  detection.collisionListener();
  move = detection.getMove();

  switch (move) {
     case 0:
     transmission.forward() ;
     break;
     case 1:
     transmission.stop() ;
     transmission.backwards() ;
     transmission.turnLeft() ;
     transmission.stop() ;
     break;
     case 2:
     transmission.stop() ;
     transmission.backwards() ;
     transmission.turnRight() ;
     transmission.stop() ;
     break;
     case 3:
     transmission.stop() ;
     transmission.backwards() ;
     transmission.turnHalfCW() ;
     transmission.stop() ;
     break;


  }
 }
}