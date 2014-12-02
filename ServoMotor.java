import stamp.core.*;

public class ServoMotor {

  private PWM motor;

public ServoMotor(int motorPin){
   motor = new PWM (motorPin, 173, 2304);
}

public void setPWM(int highTime, int lowTime){
  motor.update(highTime, lowTime);
}

}