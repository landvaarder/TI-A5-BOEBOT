import stamp.core.CPU;
/**
 * IRReciever class which has a method which checks if the IR-reciever reciever a IR-signal.
 *
 * @version 1.0 11-26-2014
 * @author Wesley
 */
public class IRReciever {

  private int IRRecieverPin;

  public IRReciever(int pin) {
   this.IRRecieverPin = pin;
  }

  public boolean checkSignal() {
   return CPU.readPin(IRRecieverPin);
  }
}