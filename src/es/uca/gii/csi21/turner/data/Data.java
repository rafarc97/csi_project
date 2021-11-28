package es.uca.gii.csi21.turner.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import es.uca.gii.csi21.turner.util.Config;

public class Data 
{
	
    /**
     * public static String getPropertiesUrl()
     * @return a string which is the db.propoerties path
     * Description: Return a string which is the db.propoerties path
     * TODO: Must exists the "db.properties" file
     */
    public static String getPropertiesUrl() { return "./db.properties"; }
	
    /**
     * @return "url", "username" and password parameters
     * @throws Exception
     * public static Connection Connection()
     * Description: Receive the "url", the "username" and the password parameters which are contain in the fb.properties file
     * TODO: Musts exist getProperties method in this concretely Class, getConnection method in the DriverManager Class and the
     * Properties method in the "Config" Class
     * Finally, have to exist the jdb.url, jdbc.username and jdbc.password parameters in db.properties file
     */
    public static Connection Connection() throws Exception 
    {
        try 
        {
            Properties properties = Config.Properties(getPropertiesUrl());
            return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
       }
       catch (Exception ee) { throw ee; }
	}
    
    /**
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     * public static void LoadDriver()
     * Description: Return the jdbc.driverClassName parameter which contains (in our case) the string "com.mysql.cj.jdbc.Driver"
     * TODO: Exist Properties method in Config Class, getPropertiesUrl method in Data Class
     * Finally, have to exist the jdb.driverClassName in db.properties file  
     */
    public static void LoadDriver() 
        throws InstantiationException, IllegalAccessException, 
        ClassNotFoundException, IOException 
    {
        Class.forName(Config.Properties(Data.getPropertiesUrl()
        ).getProperty("jdbc.driverClassName")).newInstance();
    }
    
    /**
     * @param s
     * @param bAddQuotes
     * @param bAddWildcards
     * @return A string modified
     * public static String String2Sql(String sString, boolean bAddQuotes, boolean bAddWildcards)
     * Description: All strings contain a simple comma will be replaced by two simple commas, if bAddQuotes 
     * variable is true, then if the String receive as a second parameter (bAddWildcards) and it is true, it will be added using percent sign at the beginning
     * and at the end of the String.
     * Finally, if we receive as a third parameter (bAddQuotes) and it is true, it will be added using simple comma sign at the beginning
     * and at the end of the String.
     * TODO: Musts receive and String which is going to be modified depending on what description show. Moreover, this method must receive two more booleans.
     */
    public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildcards)
    {
    	s = s.replace("'", "''");
		if (bAddWildcards) s = '%' + s + '%';
		if (bAddQuotes) s = "'" + s + "'";
		
		return s;
    }
    
    /**
     * @param b
     * @return 1 or 0 (depending on if the boolean received is true or false)
     * public static int Boolean2Sql(boolean bBoolean)
     * Description: Receive a boolean
     * TODO: Convert a true boolean into 1, and a false boolean into a 0
     */
    public static int Boolean2Sql(boolean b){ return b ? 1 : 0; }
}