package boefbot.Boefbot;

import stamp.core.*;

/**
 *
 * @author SuperMachine
 */
public class BoefBTReceive {
     
    static Uart receiver;
    
    int     direction;              int dataPin;
    boolean dataInvert;             int baudRate;
    int     stopBits;
    
    BoefBTReceive(int dataPin)
    {
        this.dataPin = dataPin;
        
        receiver = new Uart(Uart.dirReceive, dataPin, Uart.dontInvert, Uart.speed9600, Uart.stop1);
    }
    
    BoefBTReceive(int dataPin, boolean dataInvert, int baudRate, int stopBits)
    {       
    this.direction  = Uart.dirReceive;
    this.dataPin    = dataPin;
    this.dataInvert = dataInvert;
    this.baudRate   = baudRate;
    this.stopBits   = stopBits;
    
    receiver = new Uart(Uart.dirReceive, this.dataPin, this.dataInvert, this.baudRate, this.stopBits);
    }
    

}