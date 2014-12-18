package boefbot;

import java.io.*;


public class BoefBot {

    static mainFrame applicationFrame = new mainFrame();
        
    BoefBot()
    {
    
    }
             
    public static void restartApp()
    {
        applicationFrame.dispose();          
        applicationFrame.setVisible(true);
        
    }
    
    public static FileReader openFile(String bestandsLocatie)
    {
        try {
        File bestand            = new File(bestandsLocatie);
        FileReader bestandLaden = new FileReader(bestand);
        
        return bestandLaden;       
        }
        catch (FileNotFoundException E)
        {
            //* Kan bestand niet laden script
        }
        
        return null;
    }
    
    public static void saveFile()
    {
        
        
    }
    
    public static void main(String[] args) 
    {
        applicationFrame.setVisible(true);
    }
}
