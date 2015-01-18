  

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTController
{
	private          int                     port;
        private          String                  COMport;
	private          boolean                 portInitialised;
        
        public           CommPortIdentifier      portIdentifier;         
        public           CommPort                commPort; 
        public           SerialPort              serialPort;
        
        
        private          InputStream             inStream;
        public           OutputStream            outStream;
        private          Thread                  readThread;        
        
        ArrayList<String> taskqueue = new ArrayList();
        
        
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
        
        public ArrayList<String> getTaskqueue()
        {
            return this.taskqueue;
        }
        
        public void addTaskQueue(String item)
        {
            taskqueue.add(item);
        }
        
        public void emptyTaskQueue()
        {
            taskqueue = new ArrayList<String>();
        }

        public void sendCommand(String message, boolean isSingle)
        {
            
            try 
            { 
		this.outStream = serialPort.getOutputStream(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace();
            }							
			
            try 
            { 
                if (isSingle == true)
                {
		outStream.write(message.getBytes());
                }
                else
                {
                    String iets;
                    for (int x = 0; x < taskqueue.size(); x++)
                    {
                        iets = taskqueue.get(x);
                        outStream.write(iets.getBytes());
                    }
                }
            } 
            catch (IOException e)
            { 
            } 
            
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
        
    public void readStream() {
       
        try 
        {
            inStream = serialPort.getInputStream();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
                        
        serialPort.notifyOnDataAvailable(true);

        
        /*
        readThread = new Thread(this);
        readThread.start();
                */
    }
}
