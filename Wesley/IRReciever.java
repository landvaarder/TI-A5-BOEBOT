import stamp.core.CPU;   
/**
 * IRReciever class which has a method which checks if the IR-reciever reciever a IR-signal.
 *
 * @version 1.0 11-26-2014
 * @author Wesley
 */
public class IRReciever {

  int IRRecieverPin;

  public IRReciever(int IRRecieverPin) {
   this.IRRecieverPin = IRRecieverPin;
  }

  public boolean checkSignal() {
   return CPU.readPin(CPU.pins[IRRecieverPin]);
  }
}