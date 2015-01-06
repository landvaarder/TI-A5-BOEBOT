// Com1Writer.java 1.1 12/02/22  voor BlueJ omgeving
// @author Jan Woolderink
// @author Johan Talboom
// @version 24/12/13
//
import java.io.*;
import java.util.*;
import gnu.io.*;

public class ComWriter
{
    private CommPortIdentifier portId;
    private static SerialPort serialPort;
    private OutputStream outputStream;

    public ComWriter() 
    {
        initCom();
    }
    
    private void initCom() 
    {
        try 
        {
            portId = CommPortIdentifier.getPortIdentifier("COM8");
        } catch (NoSuchPortException e) { e.printStackTrace(); }
     
        try 
        {
            serialPort = (SerialPort) portId.open("SimpleWriteApp", 2000);
        } catch (PortInUseException e) { e.printStackTrace(); }
     
        try 
        {
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) { e.printStackTrace(); }

        try 
        {
            serialPort.setSerialPortParams(9600,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {}
     
        System.out.println("COM6: ");
        System.out.println(serialPort.getBaudRate());
        System.out.println(serialPort.getDataBits());
        System.out.println(serialPort.getStopBits());
    }

    public void writeString(String tekst) 
    {
        try 
        {
            outputStream.write(tekst.getBytes());
        } catch (IOException e) {}        
    }
}
