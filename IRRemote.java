import stamp.core.CPU;

/*
 * !<>Not working atm<>!
 *@author = Wesley de Hek
 *@version = 2.0
 */

public class IRRemote {

 private int recieverPin;
 private int pulseLength;

 public IRRemote(int recieverPin) {
  this.recieverPin = recieverPin;
  pulseLength = 0;
 }

 public int getIRSignalCode() {
  int buttonValue = 0;
  int bitValue = 1;
  pulseLength = CPU.pulseIn(1000,recieverPin,false); //30000 //1000 for line
  if(pulseLength == -1)
   return 999; //no pulse
  if(pulseLength > 200) {
   for(int i = 1; i <= 7; i++) {
    pulseLength = CPU.pulseIn(10000,recieverPin,false);
    if(pulseLength > 100)
     buttonValue += bitValue;
    bitValue <<= 1;
   }
   for(int x = 1; x <= 5; x++) {
    pulseLength = CPU.pulseIn(10000,recieverPin,false);
   }
  }
  else
   return 999; //no pulse
  return buttonValue;
 }
}