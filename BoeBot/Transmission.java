package BoeBot;
import stamp.core.*;

public class Transmission
{

  private int speed;
  ServoMotor motorL = new ServoMotor(12, false);
  ServoMotor motorR = new ServoMotor(13, true);

  public Transmission()
  {

  }

//* Bot rijdt vooruit
  public void forward(int speed)
  {
      motorL.setSpeed(speed);
      motorR.setSpeed(speed);
  }

  //* Bot rijdt achteruit
  public void backwards(int speed)
  {
      motorL.setSpeed(-speed);
      motorR.setSpeed(-speed);
  }

  //* Bot stopt met rijden
  public void stop()
  {
      motorL.setSpeed(0);
      motorR.setSpeed(0);
  }

  //* Bot maakt 'angle' graden draai
  /*public void pivot(int angle)
  {
      if (angle > 180)
      {
              turnRight(1);
              stop();
      }
      else
      {
              turnLeft(1);
              stop();
      }
  }*/

  //* Bot maakt 90 graden draai naar links
  public void turnLeft(int speed)
  {
      motorL.setSpeed(-speed);
      motorR.setSpeed(speed);
  }

  //* Bot maakt 90 graden draai naar rechts
  public void turnRight(int speed)
  {
      motorL.setSpeed(speed);
      motorR.setSpeed(-speed);

  }
}