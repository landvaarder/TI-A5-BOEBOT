package boefbot;

import java.io.*;
import java.util.*;
import javax.comm.*;

public class BTReceiver
{
    
    int message;
    
    static          Thread                  readThread;
    
    BTReceiver()
    {
        
    }
    
    
    public void readStream() {
        try 
        {
            BTController.serialPort = (SerialPort)BTController.portId.open("JavaGUI", 2000);
        } 
        catch (PortInUseException e) 
        {
            System.out.println(e);
        }
        
        try 
        {
            BTController.inputStream = BTController.serialPort.getInputStream();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
        
	try 
        {
            BTController.serialPort.addEventListener(this);
	} 
        catch (TooManyListenersException e) 
        {
            System.out.println(e);
        }
        
        BTController.serialPort.notifyOnDataAvailable(true);
        try 
        {
                BTController.serialPort.setSerialPortParams(9600,
                BTController.serialPort.DATABITS_8,
                BTController.serialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        } 
        catch (UnsupportedCommOperationException e) 
        {
            System.out.println(e);
        }
        
        readThread = new Thread(this);
        readThread.start();
    }
}