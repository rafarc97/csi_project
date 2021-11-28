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
			Barco barco = new Barco("A45JG8W");
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT registro, nombre, tripulantes FROM barco WHERE registro = " + Data.String2Sql("A45JG8W", true, false));

			rs.next();
			assertEquals(rs.getString("registro"), barco.getRegistro());
			assertEquals(rs.getString("nombre"), barco.getNombre());
			assertEquals(rs.getInt("tripulantes"), barco.getTripulantes());
		} catch (SQLException e) { throw e; } 
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
	@Test
	public void testCreate() throws Exception 
	{
		Connection con = null;
		ResultSet rs = null;

		try 
		{
			
			Barco barco = Barco.Create("B88YT9P",  new CategoriaBarco(1),"Francisco", 600);
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT registro, nombre, tripulantes FROM barco WHERE registro = " + Data.String2Sql("B88YT9P", true, false));
			rs.next();
			
			assertEquals(rs.getString("registro"), barco.getRegistro());
			assertEquals(rs.getString("nombre"), barco.getNombre());
			assertEquals(rs.getInt("tripulantes"), barco.getTripulantes());
			
			con.createStatement().executeUpdate(
					"DELETE FROM barco WHERE registro = " + Data.String2Sql(barco.getRegistro(), true, false));
			
		} catch (SQLException e) { throw e; } 
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
	@Disabled
	@Test
	public void testSelect() throws Exception 
	{
		try 
		{
			ArrayList<Barco> aBarco1 = Barco.Select(null, null, null, 0);
			assertEquals(3, aBarco1.size());

			ArrayList<Barco> aBarco2 = Barco.Select("A45JG8W", null, null, 0);
			assertEquals(1, aBarco2.size());

			ArrayList<Barco> aBarco3 = Barco.Select(null, null, "Roberto", 0);
			assertEquals(1, aBarco3.size());
			
			ArrayList<Barco> aBarco4 = Barco.Select(null, null, null, 20000);
			assertEquals(1, aBarco4.size());
			
			ArrayList<Barco> aBarco5 = Barco.Select("A45JG8W", null, null, 20000);
			assertEquals(1, aBarco5.size());
			
			ArrayList<Barco> aBarco6 = Barco.Select("A45JG8W", null , "Roberto", 0);
			assertEquals(1, aBarco6.size());
			
			ArrayList<Barco> aBarco7 = Barco.Select(null, null, "Roberto", 20000);
			assertEquals(1, aBarco7.size());
			
			ArrayList<Barco> aBarco8 = Barco.Select(null, "3", "Roberto", 0);
			assertEquals(1, aBarco8.size());
			
			ArrayList<Barco> aBarco9 = Barco.Select("A45JG8W", "3" , null, 20000);
			assertEquals(1, aBarco9.size());
			
			ArrayList<Barco> aBarco10 = Barco.Select("A45JG8W", null , "Roberto", 20000);
			assertEquals(1, aBarco10.size());
			
			ArrayList<Barco> aBarco11 = Barco.Select(null, "3", "Roberto", 20000);
			assertEquals(1, aBarco11.size());
			
			ArrayList<Barco> aBarco12 = Barco.Select("A45JG8W", "3", "Roberto", 0);
			assertEquals(1, aBarco12.size());
			
			ArrayList<Barco> aBarco13 = Barco.Select("A45JG8W", "3", "Roberto", 20000);
			assertEquals(1, aBarco13.size());
		
		}catch (Exception e) { throw e; } 
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
			Barco barco = new Barco("A45JG8W");
			barco.setNombre("Roberto");
			barco.setTripulantes(20000);
			
			barco.Update();
			
			assertEquals("A45JG8W", barco.getRegistro());
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
		
		Barco barco = Barco.Create("GH46F78R", new CategoriaBarco(1), "ElNacho", 200);

		barco.Delete();

		try 
		{
			con = Data.Connection();
			rs = con.createStatement()
					.executeQuery("SELECT COUNT(registro) AS registro " + "FROM barco WHERE registro = " + Data.String2Sql(barco.getRegistro(), true, true));
			rs.next();
			
			assertEquals(0, Integer.parseInt(rs.getString("registro")));
			assertEquals(true, barco.getIsDeleted());

		} catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
}