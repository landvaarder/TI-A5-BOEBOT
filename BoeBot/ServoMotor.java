package BoeBot;
import stamp.core.*;

public class ServoMotor
{
  private boolean clockwise;
  private PWM pwm;

  public ServoMotor(int motorPin, boolean clockwise)
  {
    pwm = new PWM(CPU.pins[motorPin], 173, 2304);
    this.clockwise = clockwise;
  }

  public void setSpeed(int speed)
  {
    int x = roundSpeed(speed);
    if(clockwise)
      clockwise(x);
    else
      counterclockwise(x);
  }

  private void clockwise(int speed)
  {
    pwm.update(173 - speed, 2304);
  }

  private void counterclockwise(int speed)
  {
    pwm.update(173 + speed, 2304);
  }

  private int roundSpeed(int speed)
  {
    if(speed > 23)
      return 23;
    else if (speed < -23)
      return -23;
    else
      return speed;
  }

};