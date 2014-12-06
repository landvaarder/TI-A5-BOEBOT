import stamp.core.*;

public class Transmission {

  private ServoMotor servoLeft;
  private ServoMotor servoRight;
  private int speed = 10;
  private boolean reverse = false;

  public Transmission() {
   this.servoLeft = new ServoMotor (CPU.pin12);
   this.servoRight = new ServoMotor (CPU.pin13);
  }

  public void stop() {
   servoRight.setPWM(173, 2304);
   servoLeft.setPWM(173, 2304);
  }

  public void forward() {
   reverse = false;
   servoRight.setPWM(171+speed,2304);
   servoLeft.setPWM(173-speed,2304);
  }

  public void upSpeed() {
   if(speed < 22)
    speed++;
   if(reverse)
    backward();
   else
    forward();
  }

  public void downSpeed() {
   if(speed > 1)
    speed--;
   if(reverse)
    backward();
   else
    forward();
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
  public void pivotLeft(int angle) {
   servoRight.setPWM(173-speed, 2304);
   servoLeft.setPWM(173-speed, 2304);
   CPU.delay((63333/1000)*angle);
   stop();
  }

  public void pivotRight(int angle) {
   servoRight.setPWM(173+speed, 2304);
   servoLeft.setPWM(173+speed, 2304);
   CPU.delay((63333/1000)*angle);
   stop();
  }

  public void backward() {
   reverse = true;
   servoRight.setPWM(175-speed,2304);
   servoLeft.setPWM(173+speed,2304);
  }

  public void slowStop() {
   for(int i = 0; i <= speed; i += 2) {
    servoRight.setPWM((173+speed)-i,2304);
    servoLeft.setPWM((173-speed)+i,2304);
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






}