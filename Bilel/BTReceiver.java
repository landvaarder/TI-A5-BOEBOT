package boefbot;

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTReceiver
{
    public          InputStream             inStream;
    static          Thread                  readThread;
    
    BTController controller = new BTController(3);
    
    BTReceiver(int port)
    {
        
    }
    
    //* Stream uitlezen en omzetten naar begrijpbare waarde
    public void readStream() {
        try
        {
            controller.serialPort = (SerialPort)controller.portId.open("JavaGUI", 2000);
        } 
        catch (PortInUseException e) 
        {
            System.out.println(e);
        }
        
        try 
        {
            inStream = controller.serialPort.getInputStream();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
        
        /*
	try 
        {
            controller.serialPort.addEventListener(this);
	} 
        catch (TooManyListenersException e) 
        {
            System.out.println(e);
        }
        */
        
        controller.serialPort.notifyOnDataAvailable(true);
        try 
        {
                controller.serialPort.setSerialPortParams(9600,
                controller.serialPort.DATABITS_8,
                controller.serialPort.STOPBITS_1,
                controller.serialPort.PARITY_NONE);
        } 
        catch (UnsupportedCommOperationException e) 
        {
            System.out.println(e);
        }
        
        /*
        readThread = new Thread(this);
        readThread.start();
                */
    }
}