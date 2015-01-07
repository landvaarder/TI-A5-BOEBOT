package boefbot;

import javax.comm.*;
import java.util.*;
import java.io.*;

public class BTSender
{
        public          OutputStream             outStream;    
        
        BTController controller = new BTController(3);
    
        BTSender(int port)
        {
            BTController controller = new BTController(3);            
        }
        
        public void sendMessage(byte[] message)
        {
            
            try 
            { 
		this.outStream = controller.serialPort.getOutputStream(); 
            } 
            catch (IOException e) 
            { 
            }							
			
            try 
            { 
		outStream.write(message);
            } 
            catch (IOException e)
            { 
                System.out.println();
            } 
	}
        
        public void StreamIn(String message)
        {
            byte[] messageInBytes = controller.StringToBytes(message);
            sendMessage(messageInBytes);
        }
}