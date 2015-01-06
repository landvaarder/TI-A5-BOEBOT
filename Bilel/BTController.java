package boefbot;

import java.io.InputStream;
import java.io.OutputStream;
import javax.comm.*;

public class BTController
{
	public          int                     port;
        public          String                  COMport;
	public          boolean                 portInitialised;
        
        public          SerialPort              serialPort;
        public          CommPortIdentifier      portId;         
        
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
                    portId = CommPortIdentifier.getPortIdentifier(this.COMport);
                        if (!(portId == null))
                        {
                            portInitialised = true;
                        }
		}
		catch (NoSuchPortException e)
		{
			System.out.println("No port found on " + port + ", please try again");
		}    
	}
	
        public void StreamIn(String message)
        {
            byte[] sendMessage = StringToBytes(message);
            sender.sendMessage(sendMessage);
        }
        
        //* Iets naar buiten sturen
        public void checkStreamOut()
        {
            
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