package es.uca.gii.csi21.turner.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi21.turner.data.Data;

class DataTest 
{
	/**
	 * static void setUpBeforeClass()
	 * @throws Exception
	 * Description: Load the Driver needed
	 * TODO: Exists Data class, the LoadDriver method and it is imported in this concretely file 
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception { Data.LoadDriver(); }

	/**
	 * void testTableAccess()
	 * @throws Exception
	 * Description: Test "barco" table
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	void testTableAccess() throws Exception 
	{
		Connection con = null;
		ResultSet rs = null;
		
		try 
		{
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT COUNT(nombre) num FROM barco;");
			rs.next();
			
			int i = rs.getInt("num");
			assertEquals(2, i);
			
			rs.close();
			rs = con.createStatement().executeQuery("SELECT * FROM barco;");
			
			i = 0;
			while (rs.next()) 
			{
				System.out.println(rs.getString("nombre") + " " + rs.getString("tripulantes"));
				i++;
			}
			
			assertEquals(2, i);
			assertEquals(2, rs.getMetaData().getColumnCount());
			
		}catch (SQLException e) { throw e; }
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * void testString2Sql()
	 * Description: Go over String2Sql method works correctly
	 * Exists Boolean2Sql method in Data Class
	 * TODO: Must exist String2Sql method from Data class
	 */
	@Disabled
	@Test
	void testString2Sql()
	{
		assertEquals("hola", Data.String2Sql("hola", false, false));
		assertEquals("'hola'", Data.String2Sql("hola", true, false));
		assertEquals("%hola%", Data.String2Sql("hola", false, true));
		assertEquals("'%hola%'", Data.String2Sql("hola", true, true));
		assertEquals("O''Connell", Data.String2Sql("O'Connell", false, false));
		assertEquals("'O''Connell'", Data.String2Sql("O'Connell", true, false));
		assertEquals("%''Smith ''%", Data.String2Sql("'Smith '", false, true));
		assertEquals("'''Smith '''", Data.String2Sql("'Smith '", true, false));
		assertEquals("'%''Smith ''%'", Data.String2Sql("'Smith '", true, true));
	}
	
	/**
	 * void testBoolean2Sql()
	 * Description: Go over testBoolean2Sql method works correctly
	 * Exists Boolean2Sql method in Data Class
	 * TODO: Must exist Boolean2Sql method from Data class
	 */
	@Disabled
	@Test
	void testBoolean2Sql()
	{
		assertEquals(1, Data.Boolean2Sql(true));
		assertEquals(0, Data.Boolean2Sql(false));
	}
}