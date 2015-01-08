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
    
    public void receiveSingleByte(byte[] code)
    {
        int commandCode = (int)code[0];
        
        switch (commandCode)
        {
            //* Keren
            case 100: BoefBTController.transmissie.stop();
            break;
            case 101: BoefBTController.transmissie.forward();
            break;
            case 102: BoefBTController.transmissie.pivot(90);
            break;
            case 103: BoefBTController.transmissie.pivot(270);
            break;
            case 104: BoefBTController.transmissie.backward();
            break;
            //* Rijden
            case 105: BoefBTController.transmissie.driveRight();
            break;
            case 106: BoefBTController.transmissie.driveLeft();
            break;
            case 107: BoefBTController.transmissie.turnAroundWithStop();
            break;
            default: /* Doe niets */ ;
        }
    }
    
    public void receiveTaskQueue()
    {
        
    }
    

}