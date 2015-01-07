package boefbot.Boefbot;

/**
 *
 * @author SuperMachine
 */
public class BoefBTController {

    BoefBTSend    sendPin       = new BoefBTSend(1);
    BoefBTReceive receivePin    = new BoefBTReceive(1);
    
    int StreamReaderSize = 100;
    
    BoefBTController(int receiver, int sender)
    {
        BoefBTSend    sendPin       = new BoefBTSend(sender);
        BoefBTReceive receivePin    = new BoefBTReceive(receiver);
    }
    
    public void sendData(String commands)
    {
        sendPin.sendData(commands);
    }
    
    public void receiveData()
    {
        int x = 0;
        int[] dataReceive = new int[StreamReaderSize];
        
        while (receivePin.receiver.byteAvailable())    
        {
            dataReceive[x] = receivePin.receiver.receiveByte();
            x++;
                if (x >= StreamReaderSize) 
                { 
                    return; 
                }
        }
    }
    
}
