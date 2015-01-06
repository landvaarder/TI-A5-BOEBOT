package boefbot;

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTController
{
	public          int                     port;
        public          String                  COMport;
	public          boolean                 portInitialised;
        
        public          SerialPort              serialPort;
        public          CommPortIdentifier      portIdentifier;         
        public          CommPort                commPort;              
        
        public          String                  messageIn;
        public          String                  messageOut;
        
        //* Zender en Ontvanger instanties aanmaken.
        BTSender sender           = new BTSender(3);
        BTReceiver receiver       = new BTReceiver(3);
        
	BTController(int port){
                this.port                       = port;
		this.COMport 			= "COM"+ port;
		this.portInitialised            = false;
      
                try 
                {
                portIdentifier = CommPortIdentifier.getPortIdentifier(COMport);    
                
                }
		catch (NoSuchPortException e)
		{
			System.out.println("No port found on " + port + ", please try again");
		}    
                
                try
                {
                commPort = portIdentifier.open(this.getClass().getName(),2000);
                } 
                catch (PortInUseException ex) 
                {
                        System.out.println("iets");
                }
                
                
                if (portIdentifier.isCurrentlyOwned() == true)
                {
                    System.out.println("Error: Port is currently in use");
                }
                else
                {
                    this.portInitialised = true;
		}

                
                }

        
        //* Iets naar buiten sturen
        public void StreamOut()
        {
           //(new Thread(new SerialWriter(out))).start();
                SerialReader input = new SerialReader();
                serialPort.addEventListener(input);
                serialPort.notifyOnDataAvailable(true);
                Thread.sleep(3000);
                out.write("yes".getBytes());   
        }
        	
        //* String converteren naar char array        
        public byte[] StringToBytes(String message)
        {
            //* Array aanmaken om elke ASCII waarde op te slaan
            byte[] messageInBytes = new byte[message.length()];
            
            //* String opslitsen in chars (per letter -> ASCII code)
            for (int x = 0; x <= message.length(); x++)
            {
		messageInBytes[x] 	= 	(byte)message.charAt(x);
            }
            
            return messageInBytes;     
        }     
        
        public String getPort()
        {
            return this.COMport;
        }
        
       
}