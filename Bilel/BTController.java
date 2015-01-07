package boefbot;

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTController
{
	private          int                     port;
        public           String                  COMport;
	private          boolean                 portInitialised;
        
        public           static                  CommPortIdentifier      portIdentifier;         
        public           static                  CommPort                commPort; 
        public           static                  SerialPort              serialPort;
        
        private          String                  messageIn;
        private          String                  messageOut;
        
        //* Zender en Ontvanger instanties aanmaken.
        BTSender sender           = new BTSender(3);
        BTReceiver receiver       = new BTReceiver(3);
        
	BTController(int port){
                this.port                       = port;
		this.COMport 			= "COM"+ port;
		this.portInitialised            = false;
        }
                
        public void setConnection()
        {
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
                serialPort = (SerialPort)portIdentifier.open(this.getClass().getName(),2000);
                } 
                catch (PortInUseException ex) 
                {
                        System.out.println(ex);
                }                
                
                if (portIdentifier.isCurrentlyOwned() == true)
                {
                    System.out.println("Error: Port is currently in use");
                }
                else
                {
                    this.portInitialised = true;
		}
                
                try 
                {
                serialPort.setSerialPortParams( 9600,
                                                serialPort.DATABITS_8,
                                                serialPort.STOPBITS_1,
                                                serialPort.PARITY_NONE);
                } 
            catch (UnsupportedCommOperationException e) 
            {
                System.out.println(e);
            }                
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
        
        public void switchPort(int port)
        {
            this.port                       = port;
            this.COMport 		    = "COM"+ port;
            this.portInitialised            = false;   
            
            setConnection();
        }
}