package boefbot.Boefbot;

import java.util.ArrayList;

public class BoefBTController {

    static  BoefBTSend    sendPin       = new BoefBTSend(1);
    static  BoefBTReceive receivePin    = new BoefBTReceive(1);
    
    static      Transmission    transmissie  = new Transmission(3,3); //* 3,3 parameters PSUEDO
     
    //* Task queue array
    static ArrayList<Integer> taskQueueList = new ArrayList();
    
    BoefBTController(int receiver, int sender)
    {
        BoefBTSend    sendPin       = new BoefBTSend(sender);
        BoefBTReceive receivePin    = new BoefBTReceive(receiver);
    }
    
    
    //* Data versturen naar GUI
    public static void sendData(int commands)
    {
        sendPin.sendData(commands);
    }
    
    public static void addToTaskQueue(int code)
    {
        taskQueueList.add(code);
    }
    
    public static ArrayList<Integer> getTaskQueue()
    {
        return taskQueueList;
    }
    
    public static void resetTaskQueue()
    {
        taskQueueList = new ArrayList<>();
    }    
    
    public static void remoteControl()
    {
       //* Wachten op enkele byte (Voor directe besturing)
        receivePin.receiveData("single");
    }
    
    public static void taskQueueHandler()
    {
        //* Wachten op taskQueue
        receivePin.receiveData("stream");
    }
}
