import stamp.core.*;

public class Transmission {

  private ServoMotor servoLeft;
  private ServoMotor servoRight;
  private int speed = 10;
  private int motorDir = 0; //0 = still, 1 = fowrward, 2 = backward

  public Transmission() {
   this.servoLeft = new ServoMotor (CPU.pin12);
   this.servoRight = new ServoMotor (CPU.pin13);
  }

  public void stop() {
   motorDir = 0;
   servoRight.setPWM(173, 2304);
   servoLeft.setPWM(173, 2304);
  }

  public void forward() {
   motorDir = 1;
   servoRight.setPWM(172+speed,2304);
   servoLeft.setPWM(173-speed,2304);
  }

  public void backward() {
   motorDir = 2;
   servoRight.setPWM(174-speed,2304);
   servoLeft.setPWM(173+speed,2304);
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
   servoRight.setPWM(173+speed,2304);
   servoLeft.setPWM(173-speed+10,2304);
   CPU.delay((((4450/10)/90)*angle)*speed);
  }

   public void turnLeft(int angle) {
   servoRight.setPWM(173-speed,2304);
   servoLeft.setPWM(173+speed+10,2304);
   CPU.delay((((4450/10)/90)*angle)*speed);
  }

  //works:
  public void pivot(int angle) {
   if(angle <= 180) {
    servoRight.setPWM(173-speed, 2304);
    servoLeft.setPWM(173-speed, 2304);
   }
   else if(angle > 180) {
    servoRight.setPWM(173+speed, 2304);
    servoLeft.setPWM(173+speed, 2304);
   }
   CPU.delay((63333/1000)*angle);
   resumeDrive();
  }

  public void slowStop() {
   for(int i = 0; i <= speed; i += 4) {
    servoRight.setPWM((173+speed-2)-i,2304);
    servoLeft.setPWM((173-speed+2)+i,2304);
    CPU.delay(500);
   }
   stop();
  }

  public void accelerateBackward() {
   for(int i = 0; i <= speed; i++) {
    servoRight.setPWM((173-speed)-i,2304);
    servoLeft.setPWM((173+speed)+i,2304);
    CPU.delay(500);
   }
   backward();
  }

  public void driveLeft() {
   servoRight.setPWM(193,2304);
   servoLeft.setPWM(171,2304);
  }

  public void driveRight() {
   servoRight.setPWM(175,2304);
   servoLeft.setPWM(150,2304);
  }

  public void pivoLeft() {
   servoRight.setPWM(193, 2304);
   servoLeft.setPWM(193, 2304);
  }

  public void pivoRight() {
   servoRight.setPWM(150, 2304);
   servoLeft.setPWM(150, 2304);
  }







}