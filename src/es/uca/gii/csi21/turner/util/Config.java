package es.uca.gii.csi21.turner.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config 
{
    /**
     * public static Properties Properties(String sFile)
     * @param sFile
     * @return Properties class instance
     * @throws IOException
     * Description: Return a Properties class instance
     * TODO: Receive the "db.properties" file as a string variable which musts exists
     * Database is working correctly
     */
    public static Properties Properties(String sFile) throws IOException 
    {
        InputStream inputStream = null;	
        try 
        {
            inputStream = new FileInputStream(sFile);
            Properties result = new Properties();
            result.load(inputStream);
            return result;
        }
        finally { if (inputStream != null) inputStream.close(); }
    }
}