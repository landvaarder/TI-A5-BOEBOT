package boefbot.Boefbot;

import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 2.0
*
*
*/

public class ServoMotor {

  private PWM motor;

public ServoMotor(int motorPin){
   motor = new PWM (motorPin, 173, 2304);
}

public void setPWM(int highTime){
  motor.update(highTime, 2304);
}

}