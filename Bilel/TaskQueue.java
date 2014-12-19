package boefbot;

import java.util.ArrayList;

public class TaskQueue {
    
    //* Codes voor bewegingen:
    //* R: Rechts               L: Links
    //* O: Achteruit            V: Vooruit
    
    static ArrayList<String> lijstMetTaken = new ArrayList<>();   
    
    //*
    TaskQueue (){
        
    }
    
    public static void resetQueue()
    {
        lijstMetTaken.clear();
    }
    
    public static void add(String richting)
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
                String richting  = lijstMetTaken.get(lijstMetTaken.size() -1);
                
                //* VB: L, R, ...
                if (richting.matches("[A-Z]?") == true)
                {
                     goReverseRichtingStr(richting);

                } 
                //* VB: 67L (67 Links).
                else if (richting.matches("[\\D]+[A-Z]?")) 
                {
                     goReverseRichtingCode(richting);
                }
            }
            //* Als richting in graden aangegeven is.
            

             /* Laatste entry in List weghalen, is ongedaan gemaakt */
                lijstMetTaken.remove(lijstMetTaken.size() -1);
                //* Ga richting: reverse           
        }       
    
    public static void goReverseRichtingStr(String richting)
    {
        
        switch (richting) {
            case "R": ;     //* Ga Links
                break;
            case "L": ;     //* Ga Rechts
                break;
            case "V": ;
                break;
            case "A": ;
                break;
        }
    }

    public static void goReverseRichtingCode(String richting)
    {
        int reverseRichtingNumb;    String reverseRichtingChar;
        
        reverseRichtingChar = richting.replaceAll("[^\\d.]", "");
        reverseRichtingNumb = Integer.parseInt(richting.replaceAll("[A-Z]", ""));
        
        if (reverseRichtingChar.equals("R"))
        {
            //* pivot(Links, 
        }
        
        else if (reverseRichtingChar.equals("L"))
        {
            //* 
        }
    }
    
}