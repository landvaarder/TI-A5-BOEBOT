package boefbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskQueue {
    
    //* Codes voor bewegingen:
    //* R: Rechts               L: Links
    //* O: Achteruit            V: Vooruit
    //* VR: Vooruit Rechts      VL: Vooruit Links
    //* AR: Achteruit Rechts    AL: Achteruit Links
    
    static List<Object> lijstMetTaken = new ArrayList<>();   
    
    //*
    TaskQueue (){
        
    }
    
    public static void resetQueue()
    {
        lijstMetTaken.clear();
    }
    
    public static void add(Object richting)
    {
        lijstMetTaken.add(richting);
    }
    
    public static void goReverse(int ammountOfSteps, boolean goBackToStart)
    {
        //* Teller voor while loop.
        int hoeVaakTerug;
        
        //* 1 stap of terug naar start
        if (goBackToStart)
        {   
            hoeVaakTerug = lijstMetTaken.size();
        } 
        else { hoeVaakTerug = ammountOfSteps; }
        
        for(int x = 0; x < hoeVaakTerug; x++)
        {
            //* Laatste pad was String ('L','R', etc...)
            if (lijstMetTaken.get(lijstMetTaken.size() -1) instanceof String)
            {

                String richting  = lijstMetTaken.get(lijstMetTaken.size() -1).toString();
                //* Methodes invoegen
                switch (richting) {
                    //* Signaal sturen voor 'andere richting'
                    case "R": ; 
                        break;
                    case "L": ;
                        break;
                    case "V": ;
                        break;
                    case "A": ;
                        break;
                    case "VR": ;
                        break;
                    case "VL": ;
                        break;
                    case "AR": ;
                        break;
                    case "AL": ;
                        break;
                    default: ;               
                }            
            }
            //* Als richting in graden aangegeven is.
            else if (lijstMetTaken.get(lijstMetTaken.size() -1) instanceof Integer)
            {
                int richting     =  Integer.parseInt((lijstMetTaken.get(lijstMetTaken.size() -1)).toString());
                //* Signaal sturen voor graden draai
            }

             /* Laatste entry in ArrayList weghalen, is ongedaan gemaakt */
                lijstMetTaken.remove(lijstMetTaken.size() -1);
                //* Ga richting: reverse           
        }       
    }
    
}
   