import stamp.core.*;


public class Application {

  public Application(){
   Transmission transmission = new Transmission();
   CollisionDetection detection = new CollisionDetection();
  }

  public static void main() {

  }

  public void collisionPrevention(){
    detection.collisionListener();
    detection.getMove();
  }

}