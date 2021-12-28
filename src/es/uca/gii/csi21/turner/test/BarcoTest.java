package es.uca.gii.csi21.turner.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi21.turner.data.Barco;
import es.uca.gii.csi21.turner.data.CategoriaBarco;
import es.uca.gii.csi21.turner.data.Data;

class BarcoTest 
{
	/**
	 * static void setUpBeforeClass()
	 * @throws Exception
	 * Description: Load the Driver needed
	 * TODO: Data class is imported and contains LoadDriver method
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
			Barco barco = new Barco(2);
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre, tripulantes FROM barco WHERE id = " + Data.String2Sql("A45JG8W", true, false));
			rs.next();
			
			assertEquals(rs.getString("id"), barco.getId());
			assertEquals(rs.getString("nombre"), barco.getNombre());
			assertEquals(rs.getInt("tripulantes"), barco.getTripulantes());
		}catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}

	/**
	 * public void testCreate()
	 * @throws Exception
	 * Description: Test Create method works correctly
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	public void testCreate() throws Exception 
	{
		
		Connection con = null;
		ResultSet rs = null;

		try 
		{
			
			Barco barco = Barco.Create(new CategoriaBarco(1), "Francisco", 600);
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre, tripulantes FROM barco WHERE id = " + Data.String2Sql("B88YT9P", true, false));
			rs.next();
			
			assertEquals(rs.getString("id"), barco.getId());
			assertEquals(rs.getString("nombre"), barco.getNombre());
			assertEquals(rs.getInt("tripulantes"), barco.getTripulantes());
			
			con.createStatement().executeUpdate("DELETE FROM barco WHERE id = " + barco.getId());
			
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
	 * Description: Test Select method from "Barco" class
	 * TODO: Database is working correctly
	 */
	@Test
	public void testSelect() throws Exception 
	{

			// First Test
			CategoriaBarco categoriaBarco = new CategoriaBarco(2);
			Barco.Create(categoriaBarco, "Roberto", 100);
			ArrayList<Barco> aBarco = Barco.Select(categoriaBarco.getNombre(), "Roberto", 100);
			
			assertEquals(1, aBarco.size());
			assertEquals("bote", aBarco.get(0).getCategoriaBarco().getNombre());
			assertEquals("Roberto", aBarco.get(0).getNombre());
			assertEquals(100, aBarco.get(0).getTripulantes());
			
			aBarco.get(0).Delete();
			
			// Second Test
			Barco.Create(categoriaBarco, "", 0);
			aBarco = Barco.Select(categoriaBarco.getNombre(), null, 0);
			
			assertEquals(1, aBarco.size());
			assertEquals("bote", aBarco.get(0).getCategoriaBarco().getNombre());
			assertEquals("", aBarco.get(0).getNombre());
			assertEquals(0, aBarco.get(0).getTripulantes());
			
			aBarco.get(0).Delete();
			
			// Third Test
			Barco.Create(categoriaBarco, "Roberto", 0);
			aBarco = Barco.Select(categoriaBarco.getNombre(), "Roberto", 0);
			
			assertEquals(1, aBarco.size());
			assertEquals("bote", aBarco.get(0).getCategoriaBarco().getNombre());
			assertEquals("Roberto", aBarco.get(0).getNombre());
			assertEquals(0, aBarco.get(0).getTripulantes());
			
			aBarco.get(0).Delete();
			
			// Fourth Test
			Barco.Create(categoriaBarco, "", 100);
			aBarco = Barco.Select(categoriaBarco.getNombre(), null, 100);
			
			assertEquals(1, aBarco.size());
			assertEquals("bote", aBarco.get(0).getCategoriaBarco().getNombre());
			assertEquals("", aBarco.get(0).getNombre());
			assertEquals(100, aBarco.get(0).getTripulantes());
			
			aBarco.get(0).Delete();
	}
	
	/**
	 * public void testUpdate()
	 * @throws Exception
	 * Description: Test Update method from "Barco" class
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	public void testUpdate() throws Exception 
	{
		try 
		{
			Barco barco = new Barco(2);
			barco.setNombre("Roberto");
			barco.setTripulantes(20000);
			barco.Update();
			
			assertEquals("A45JG8W", barco.getId());
			assertEquals("Roberto", barco.getNombre());
			assertEquals(20000, barco.getTripulantes());
		}catch (Exception e) { throw e; } 
	}

	/**
	 * public void testDelete()
	 * @throws Exception
	 * Description: Test Delete method from "Barco" class
	 * TODO: Database is working correctly
	 */
	@Disabled
	@Test
	public void testDelete() throws Exception 
	{

		Connection con = null;
		ResultSet rs = null;
		
		Barco barco = Barco.Create(new CategoriaBarco(1), "ElNacho", 200);
		barco.Delete();

		try 
		{
			con = Data.Connection();
			rs = con.createStatement()
					.executeQuery("SELECT COUNT(id) AS id " + "FROM barco WHERE id = " + barco.getId());
			rs.next();
			
			assertEquals(0, Integer.parseInt(rs.getString("id")));
			assertEquals(true, barco.getIsDeleted());

		}catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
}