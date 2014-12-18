package boefbot;

import java.util.ArrayList;

/**
 *
 * @author SuperMachine
 */
public class TaskQueue {
    
    //* Codes voor bewegingen:
    //* R: Rechts           L: Links
    //* O: Achteruit        V: Vooruit
    //* VR: Vooruit Rechts  VL: Vooruit Links
    //* 
    
    static ArrayList<String> lijstMetTaken;
    
    
    //*
    TaskQueue (){
        
    }
    
    public static void resetQueue()
    {
        ArrayList<String> lijstMetTaken         = new ArrayList<>();
    }
    
    public static void add(String richting)
    {
        lijstMetTaken.add(richting);
    }
    
    public static String calcReverse(String richting)
    {
        String reverseRichting = richting;
        
        //* Wat elke case inhoudt, staat boverin de klasse 
        switch (richting) {
            case "R": reverseRichting = "L";
                break;
            case "L": reverseRichting = "R";
                break;
            case "V": reverseRichting = "A";
                break;
            case "A": reverseRichting = "V";
                break;
            case "VR": reverseRichting = "VL";
                break;
            case "VL": reverseRichting = "VR";
                break;
            case "AR": reverseRichting = "AL";
                break;
            case "AL": reverseRichting = "AR";
                break;
                  
        }
        return reverseRichting;                
     }
           

    public static String goReverse()
    {
        /* Omgekeerde richting bepalen */
            String reverse = lijstMetTaken.get(lijstMetTaken.size() -1);
            reverse = calcReverse(reverse);
        /* Omgekeerd, dus laatste entry in ArrayList weghalen */
            lijstMetTaken.remove(lijstMetTaken.size() -1);
            //* Ga richting: reverse
            
        return reverse;
    }
    

}
