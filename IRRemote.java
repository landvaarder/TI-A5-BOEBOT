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
  pulseLength = CPU.pulseIn(30000,recieverPin,false);
  if(pulseLength == -1)
   return 999; //no pulse
  if(pulseLength > 200) {
   for(int i = 0; i < 8; i++) {
    pulseLength = CPU.pulseIn(10000,recieverPin,false);
    if(pulseLength == -1)
     return 999;
    if(pulseLength > 100)
     buttonValue += bitValue;
    bitValue <<= bitValue;
   }
   for(int i = 0; i < 6; i++) {
    pulseLength = CPU.pulseIn(10000,recieverPin,false);
    if(pulseLength == -1)
     return 999;
   }
  }
  return buttonValue;
 }
}