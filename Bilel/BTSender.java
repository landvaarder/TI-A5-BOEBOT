package boefbot;

import static boefbot.BTController.serialPort;
import javax.comm.*;
import java.util.*;
import java.io.*;

public class BTSender
{
    
        public static String message;
     
        BTSender(String message)
        {
            this.message = message;
        }
        
        public static void sendCommand(String message)
        {
            
            String serialPort;
            
            
		try 
		{ 
			BTController.outstream = BTController.serialPort.getOutputStream(); 
		} 
                catch (IOException e) 
                { 
                }							
			
		try 
		{ 
			BTController.outstream.write(message.getBytes()); 		// 
		} 
		catch (IOException e)
                { 
                    
                } 

	}
}