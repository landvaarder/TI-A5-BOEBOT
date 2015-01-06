package boefbot;

import java.io.InputStream;
import java.io.OutputStream;
import javax.comm.*;

public class BTController
{
	public          String                  port;
	public          boolean                 portInitialised;
        
        public          SerialPort              serialPort;
        public          CommPortIdentifier      portId;         
        
        public          String                  messageIn;
        public          String                  messageOut;
        
        public          InputStream             inputStream;        
        public          OutputStream            outstream;
	
	BTController(int port){
		this.port 			= "COM"+ port;
		this.portInitialised            = false;
	}
	
        //* Port initialiseren
	public void InitPort()
	{		
                try
		{
                    portId = CommPortIdentifier.getPortIdentifier(this.port);
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
        
        public void BytesToString(OutputStream out)
        {
            this.message += out.toString();        
        }
        
        public void getMessage()
        {
            return this.message;
        }
        
        public String getPort()
        {
            return this.port;
        }
        
       
}
	
	


	


}