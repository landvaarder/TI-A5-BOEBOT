import stamp.core.*;

/**
* @author Tim Schijvenaars
* @version 2.0
*/

public class Transmission {

  private ServoMotor servoLeft;
  private ServoMotor servoRight;
  private int speed = 10;

  public Transmission(int leftMotorPin, int rightMotorPin) {
   this.servoLeft = new ServoMotor (leftMotorPin);
   this.servoRight = new ServoMotor (rightMotorPin);
  }

  public void stop() {
   servoRight.setPWM(173);
   servoLeft.setPWM(173);
  }

  public void forward() {
   servoRight.setPWM(173+speed);
   servoLeft.setPWM(173-speed);
  }

  public void upSpeed() {
   speed++;
  }

  public void downSpeed() {
   speed--;
  }

  /**
   ** 445*speed delay = 90 turn
   ** 4450/90 = 50 delay
   **/
  public void turnRight(int angle) {
   servoRight.setPWM(173+speed);
   servoLeft.setPWM(173+speed);
   CPU.delay((5*angle)*speed);
  }

  public void turnLeft(int angle) {
   servoRight.setPWM(173-speed);
   servoLeft.setPWM(173-speed);
   CPU.delay((5*angle)*speed);
  }

  public void backward() {
   servoRight.setPWM(173-speed);
   servoLeft.setPWM(173+speed);
  }

  public void slowStop() {
   for(int i = 0; i <= speed; i += 1) {
    servoRight.setPWM((173+speed)-i);
    servoLeft.setPWM((173-speed)+i);
    CPU.delay(500);
   }
   stop();
  }






}