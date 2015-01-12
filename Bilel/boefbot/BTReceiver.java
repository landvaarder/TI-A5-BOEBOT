 

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTReceiver
{
    private          InputStream             inStream;
    private          Thread                  readThread;
    
    BTReceiver(int port)
    {
        
    }
    
    //* Stream uitlezen en omzetten naar begrijpbare waarde
    public void readStream() {
       
        try 
        {
            inStream = BTController.serialPort.getInputStream();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
                        
        BTController.serialPort.notifyOnDataAvailable(true);

        
        /*
        readThread = new Thread(this);
        readThread.start();
                */
    }
}