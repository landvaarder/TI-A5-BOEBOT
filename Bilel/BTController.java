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
        
        
        public void sendCommand(String watDoen)
        {
            //* Invoer omzetten naar commandCode
            byte[] commandCode = new byte[1];
            
            switch (watDoen)
            {
                
                case "stop":                commandCode[0] = (byte)100;
                break;
                case "vooruit":             commandCode[0] = (byte)101;
                break;
                case "keerlinks":           commandCode[0] = (byte)102;
                break;
                case "keerrechts":          commandCode[0] = (byte)103;
                break;
                case "keerachteruit":       commandCode[0] = (byte)104;
                break;  
                case "links":               commandCode[0] = (byte)105;
                break;
                case "rechts":              commandCode[0] = (byte)106;
                break;               
                case "achteruit":           commandCode[0] = (byte)107;
                break;                    
                default:                    commandCode[0] = (byte)100;   //* Bij foutieve aaroep "stop" code sturen.
                break;
            }
            
            
            sender.sendMessage(commandCode);
        }
        
        public static byte[] IntegerToByte(int number)
        {
            byte[] bytesArray   =   new byte[1];
            bytesArray[0]       =   (byte)number;
            return bytesArray;
        }

       //* String converteren naar char array        
        public static byte[] StringToBytes(String message)
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