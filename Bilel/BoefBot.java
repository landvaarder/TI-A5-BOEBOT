/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boefbot;

import java.io.*;



/**
 *
 * @author SuperMachine
 */
public class BoefBot {

    /**
     * @param args the command line arguments
     */
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
