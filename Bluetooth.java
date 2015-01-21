import stamp.core.Uart;

/*
 *@author Wesley de Hek
 *@version 1.0
 */

public class Bluetooth {

 private int serial_RX; //Rx pin 15
 private int serial_TX; //Tx pin 14
 private Uart rxUART;
 private Uart txUART;
 private Transmission transmission;
 private Application application;

 public Bluetooth(int Rx, int Tx, Transmission transmission, Application application) {
  serial_RX = Rx;
  serial_TX = Tx;
  rxUART = new Uart(Uart.dirReceive, serial_RX, Uart.dontInvert,Uart.speed9600, Uart.stop1);
  txUART = new Uart(Uart.dirTransmit, serial_TX,Uart.dontInvert,Uart.speed9600, Uart.stop1);
  this.transmission = transmission;
  this.application = application;
 }

 public int readSignal() {
  String code = "";
  while(rxUART.byteAvailable()) {
   char letterOfCode = (char) rxUART.receiveByte();
   code += letterOfCode;
  }
  if(code == "")
   return 0; //no bluetooth command recieved
  else {
   bluetoothControll(code);
   return 0; //bluetooth commando executed
  }
 }

 private void bluetoothControll(String code) {
  char[] codeArray = code.toCharArray();
  if(code.length() <= 1) {
   switch(codeArray[0]) {
    case 'F':
     transmission.forward();
     break;
    case 'B':
     transmission.backward();
     break;
    case 'R':
     transmission.driveRight();
     break;
    case 'L':
     transmission.driveLeft();
     break;
    case 'S':
     transmission.stop();
     break;
    case '0':
     application.setCollisionMode();
     break;
    case '1':
     application.setLineMode();
     break;
    case '2':
     application.setRemoteMode();
     break;
    case 'g':
     System.out.println("recieved");
     txUART.sendString("F");
     break;
   }
  }
  else {
   application.setCrossWays(codeArray,code.length());
  }
 }

}