import stamp.core.*;

public class IRRemote {

 private int recieverPin;
 private int pulseLength;

 public IRRemote(int recieverPin) {
  this.recieverPin = recieverPin;
 }

 public int getIRSignalCode() {
  int buttonValue = 0;
  int bitValue = 1;
  pulseLength = CPU.pulseIn(4000,recieverPin,false); //30000
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