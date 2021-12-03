package es.uca.gii.csi21.turner.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi21.turner.data.CategoriaBarco;
import es.uca.gii.csi21.turner.data.Data;

class CategoriaBarcoTest 
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
	 * void testConstructor()
	 * @throws Exception
	 * Description: Test Constructor method works correctly
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	void testConstructor() throws Exception
	{
		
		Connection con = null;
		ResultSet rs = null;

		try 
		{
			CategoriaBarco categoriaBarco = new CategoriaBarco(1);
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM categoriabarco WHERE id = 1" );
			rs.next();
			
			assertEquals(rs.getInt("id"), categoriaBarco.getId());
			assertEquals(rs.getString("nombre"), categoriaBarco.getNombre());
		}catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * public void testSelect()
	 * @throws Exception
	 * Description: Test Select method from "CategoriaBarco" class
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	public void testSelect() throws Exception 
	{
		try 
		{
			ArrayList<CategoriaBarco> aCategoriaBarco = CategoriaBarco.Select();
			assertEquals(3, aCategoriaBarco.size());
		}catch (Exception e) { throw e; } 
	}
}