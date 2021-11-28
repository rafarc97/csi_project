package es.uca.gii.csi21.turner.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaBarco 
{
	
	// Attributes
	private int _iId;
	private String _sNombre;
	
	// Getters
	public int getId() { return _iId; }
	public String getNombre() { return _sNombre; }

	
	// Setters
	public void set_sNombre(String sNombre) { _sNombre = sNombre; }
	public void set_iId(int iId) { _iId = iId; }
	

	/**
	 * public CategoriaBarco(int iId)
	 * @param iId
	 * @throws Exception
	 * Description: Constructor
	 * TODO: Receive an integer which belongs to the id of the database
	 */
	public CategoriaBarco(int iId) throws Exception 
	{
		
		Connection con = null;
		ResultSet rs = null;
		
		try 
		{
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM categoriabarco"
					+ " WHERE id = " + iId);

			if (rs.next()) 
			{
				_sNombre = rs.getString("nombre");
				_iId = iId;		
			}
		} catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * public static ArrayList<CategoriaBarco> Select()
	 * @return
	 * @throws Exception
	 * Description: Get all registers from database, create and ArrayList<CategoriaBarco> and return it
	 * TODO: Database is working correctly; 
	 */
	public static ArrayList<CategoriaBarco> Select() throws Exception 
	{
		ResultSet rs = null;
		Connection con = null;
		ArrayList<CategoriaBarco> aCategoriaBarco = new ArrayList<CategoriaBarco>();

		try 
		{
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id FROM categoriabarco");
					
			while (rs.next()) { aCategoriaBarco.add(new CategoriaBarco(rs.getInt("id"))); }
				
			System.out.println(aCategoriaBarco);
			return aCategoriaBarco;
		} catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * public String toString() 
	 * Description: Return all class attributes as a string for showing information 
	 * TODO: _iId, _sNombre attributes musts exist
	 */
	@Override
	public String toString() { return super.toString() + _sNombre; }
}
