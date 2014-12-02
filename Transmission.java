import stamp.core.*;



public class Transmission {

  private ServoMotor servoLeft;
  private ServoMotor servoRight;
  private int speed = 10;

  public Transmission() {
   this.servoLeft = new ServoMotor (CPU.pin12);
   this.servoRight = new ServoMotor (CPU.pin13);
  }

  public void stop() {
   servoRight.setPWM(173, 2304);
   servoLeft.setPWM(173, 2304);
  }

  public void forward() {
   servoRight.setPWM(173+speed,2304);
   servoLeft.setPWM(173-speed,2304);
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
   servoRight.setPWM(173+speed, 2304);
   servoLeft.setPWM(173+speed, 2304);
   CPU.delay((5*angle)*speed);
  }

  public void turnLeft(int angle) {
   servoRight.setPWM(173-speed, 2304);
   servoLeft.setPWM(173-speed, 2304);
   CPU.delay((5*angle)*speed);
  }

  public void backward() {
   servoRight.setPWM(173-speed,2304);
   servoLeft.setPWM(173+speed,2304);
  }

  public void slowStop() {
   for(int i = 0; i <= speed; i += 1) {
    servoRight.setPWM((173+speed)-i,2304);
    servoLeft.setPWM((173-speed)+i,2304);
    CPU.delay(500);
   }
   stop();
  }






}