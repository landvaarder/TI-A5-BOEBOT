package boefbot.Boefbot;

import stamp.core.*;

/**
 *
 * @author SuperMachine
 */
public class BoefBTSend {
     
    static Uart sender;
    
    int dataPin;            boolean dataInvert;             
    int baudRate;           int     stopBits;
    
    BoefBTSend(int dataPin)
    {
        this.dataPin = dataPin;
        
        sender = new Uart(Uart.dirTransmit, dataPin, Uart.dontInvert, Uart.speed9600, Uart.stop1);
    }
    
    BoefBTSend(int dataPin, boolean dataInvert, int baudRate, int stopBits)
    {       
    this.dataPin    = dataPin;
    this.dataInvert = dataInvert;
    this.baudRate   = baudRate;
    this.stopBits   = stopBits;
    
    sender = new Uart(Uart.dirTransmit, this.dataPin, this.dataInvert, this.baudRate, this.stopBits);
    }
    
    public void sendData(String data)
    {
        sender.sendString(data); 
    }
}