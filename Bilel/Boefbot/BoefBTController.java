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
    
    public static void sendData(String commands)
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
}
