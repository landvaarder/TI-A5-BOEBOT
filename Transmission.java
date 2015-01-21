import stamp.core.CPU;

/*
 *@author Tim Schijvenaars & Wesley de Hek
 *@version 5.0
 */

public class Transmission {

  private ServoMotor servoLeft;
  private ServoMotor servoRight;
  private int speed = 10;
  private int motorDir = 0; //0 = idle, 1 = forward, 2 = backward

  public Transmission(int pinLeft, int pinRight) {
   this.servoLeft = new ServoMotor (pinLeft);
   this.servoRight = new ServoMotor (pinRight);
  }

  public void stop() {
   motorDir = 0;
   servoRight.setPWM(173);
   servoLeft.setPWM(173);
  }

  public void forward() {
   motorDir = 1;
   servoRight.setPWM(172+speed);
   servoLeft.setPWM(173-speed);
  }

  public void backward() {
   motorDir = 2;
   servoRight.setPWM(174-speed);
   servoLeft.setPWM(173+speed);
  }

  public void resumeDrive() {
   if(motorDir == 1)
    forward();
   else if(motorDir == 2)
    backward();
   else
    stop();
  }

  public int getMotorDir() {
   return motorDir;
  }

  /** 5700 delay = 90
   ** 445*speed delay = 90 turn
   ** 4450/90 = 50 delay
   **/
  public void turnRight(int angle) {
   servoRight.setPWM(173+speed);
   servoLeft.setPWM(173-speed+10);
   CPU.delay((((4450/10)/90)*angle)*speed);
  }

   public void turnLeft(int angle) {
   servoRight.setPWM(173-speed);
   servoLeft.setPWM(173+speed+10);
   CPU.delay((((4450/10)/90)*angle)*speed);
  }

  //works:
  public void pivot(int angle) {
   if(angle <= 180) {
    servoRight.setPWM(173-speed);
    servoLeft.setPWM(173-speed);
   }
   else if(angle > 180) {
    servoRight.setPWM(173+speed);
    servoLeft.setPWM(173+speed);
   }
   CPU.delay((63333/1000)*angle);
   resumeDrive();
  }

  public void slowStop() {
   for(int i = 0; i <= speed; i += 4) {
    servoRight.setPWM((173+speed-2)-i);
    servoLeft.setPWM((173-speed+2)+i);
    CPU.delay(500);
   }
   stop();
  }

  public void accelerateBackward() {
   for(int i = 0; i <= speed; i++) {
    servoRight.setPWM((173-speed)-i);
    servoLeft.setPWM((173+speed)+i);
    CPU.delay(500);
   }
   backward();
  }

  public void driveLeft() {
   servoRight.setPWM(175);
   servoLeft.setPWM(150);
  }

  public void driveRight() {
   servoRight.setPWM(193);
   servoLeft.setPWM(171);
  }

  public void pivoLeft() {
   servoRight.setPWM(193);
   servoLeft.setPWM(193);
  }

  public void pivoRight() {
   servoRight.setPWM(150);
   servoLeft.setPWM(150);
  }

  public void turnLeftWithStop() {
   slowStop();
   backward();
   CPU.delay(5000);
   pivot(90);
   forward();
  }

  public void turnRightWithStop() {
   slowStop();
   backward();
   CPU.delay(5000);
   pivot(90);
   forward();
  }

  public void turnAroundWithStop() {
   slowStop();
   backward();
   CPU.delay(5000);
   pivot(180);
   forward();
  }

  public void turnAroundWithoutStop() {
   accelerateBackward();
   CPU.delay(5000);
   pivot(180);
   forward();
  }
}