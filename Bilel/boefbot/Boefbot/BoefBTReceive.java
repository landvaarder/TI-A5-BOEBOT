package boefbot.Boefbot;

import stamp.core.*;

public class BoefBTReceive {
     
    static Uart receiver;
    
    int     direction;              int dataPin;            boolean dataInvert;             
    int     baudRate;               int stopBits;
    
    static int StreamReaderSize = 100;
    
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
    
    public static void receiveData(String type)
    {
        
        if (type.equals("single"))
        {
            if (receiver.byteAvailable())
            {
                processSingleByte(receiver.receiveByte());
            }
        }
        else if (type.equals("stream"))
        {           
            int x = 0;
            int[] dataReceive = new int[StreamReaderSize];

            while (receiver.byteAvailable())    
            {
                dataReceive[x] = receiver.receiveByte();
                x++;
                    if (x >= StreamReaderSize) 
                    { 
                        //* Stop wanneer gehele stream is uitgelezen.
                        return; 
                    }
            }
        
        //* Stream toevoegen aan taskQueue (voor andere verwerking)
            for(int received: dataReceive)
            {
                BoefBTController.addToTaskQueue(received);
            }
        }
    }

    public static void processSingleByte(int code)
    {        
        int commandCode = code;
        
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
    
}