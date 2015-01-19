 

import java.io.*;

public class FileController {
    
    FileReader reader;
    FileWriter writer;
    BufferedWriter buffer;
    

    FileController()
    {
        
    }
    
    public void writer(String input)
    {
       
        try
        {
            writer = new FileWriter("savedpath.txt");
            buffer = new BufferedWriter(writer);
            buffer.write(input);
            buffer.close();
            writer.close();
         
        } 
        catch (IOException e)
        {
        }

    }
    
    public char[] reader()
    {
        
        char[] dataChar = new char[20];
        
        try
        {
            reader          = new FileReader("savedpath.txt");
            int perLetter   = reader.read();

            

            for(int x = 0; perLetter != -1; x++)
            {
                
                if (Character.toString((char)perLetter).equals("n"))
                {
                perLetter       = reader.read();
                perLetter       = reader.read();
                perLetter       = reader.read();
                perLetter       = reader.read();
                x--;
                }
                else 
                {
                    dataChar[x]     = (char)perLetter;
                    perLetter       = reader.read();
                }
                
            }
        }
        catch (IOException f)
        {
            return null; 
        }
            return dataChar;

    }
}

