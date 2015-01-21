// Com1Writer.java 1.1 12/02/22  voor BlueJ omgeving
// @author Jan Woolderink
// @author Johan Talboom
// @version 24/12/13
//
import java.io.*;
import java.util.*;
import gnu.io.*;

public class ComWriter {
    private CommPortIdentifier portIdRecieve,portIdSend;
    private static SerialPort serialPortRecieve, serialPortSend;       
    private OutputStream outputStream;
    private InputStream inputStream;

    SerialPort serialPort1, serialPort2;
    static String TimeStamp;
    Thread            readThread;

    public ComWriter() 
    {
        initCom();
    }

    private void initCom() 
    {
        try 
        {
            portIdSend = CommPortIdentifier.getPortIdentifier("COM8"); //send
            portIdRecieve = CommPortIdentifier.getPortIdentifier("COM7"); // recieve
        } catch (NoSuchPortException e) { e.printStackTrace(); }

        try 
        {
            serialPortSend = (SerialPort) portIdSend.open("SimpleWriteApp", 2000); // send
            serialPortRecieve = (SerialPort) portIdRecieve.open("SimpleWriteApp", 2000); // recieve
        } catch (PortInUseException e) { e.printStackTrace(); }

        try 
        {
            outputStream = serialPortSend.getOutputStream(); // send
            inputStream = serialPortRecieve.getInputStream(); // recieve
        } catch (IOException e) { e.printStackTrace(); }

        try 
        {
            serialPortSend.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            serialPortRecieve.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {}


        System.out.println("COM8: ");
        System.out.println(serialPortSend.getBaudRate());
        System.out.println(serialPortSend.getDataBits());
        System.out.println(serialPortSend.getStopBits());
        //readThread = new Thread(this);

        // readThread.start();
    }

    //public void run() {
    //  try {
    //       Thread.sleep(20000);
    // } catch (InterruptedException e) {}
    // } 

    public void writeString(String tekst) 
    {
        try 
        {
            outputStream.write(tekst.getBytes());
        } catch (IOException e) {}    
    }

    public void recieveString() {
        int hoi = 3;
        while(true) {
            try {
                hoi = inputStream.read();
            } catch (IOException e) {}
            System.out.println(hoi);
        }
    }
}