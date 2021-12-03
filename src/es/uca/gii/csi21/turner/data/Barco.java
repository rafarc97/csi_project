package es.uca.gii.csi21.turner.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Barco 
{
	
	// Attributes
	private String _sRegistro;
	private String _sNombre;
	private int _iTripulantes;
	private boolean _bIsDeleted = false;
	private CategoriaBarco _categoriaBarco;

	// Getters
	public String getRegistro() { return _sRegistro; }
	public String getNombre() { return _sNombre; }
	public int getTripulantes() { return _iTripulantes; }
	public boolean getIsDeleted() { return _bIsDeleted; }
	public CategoriaBarco getCategoriaBarco() { return _categoriaBarco; }
	
	// Setters
	public void setIsDeleted(boolean bIsDeleted) { _bIsDeleted = bIsDeleted; }
	public void setRegistro(String sRegistro) { _sRegistro = sRegistro; }
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	public void setTripulantes(int iTripulantes) { _iTripulantes = iTripulantes; }
	public void setCategoriaBarco(CategoriaBarco categoriaBarco) { _categoriaBarco = categoriaBarco; }

	/**
	 * public Barco(String sRegistro)
	 * @param sRegistro
	 * @throws Exception
	 * Description: Constructor 
	 * TODO: Receive a string which is the RegisterCode of a
	 * specific "barco", Strin2Sql and Connection methods which
	 * belongs to Data class work correctly
	 */
	public Barco(String sRegistro) throws Exception 
	{

		Connection con = null;
		ResultSet rs = null;

		try 
		{
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT registro, nombre, tripulantes, id_categoriabarco FROM barco"
					+ " WHERE registro = " + Data.String2Sql(sRegistro, true, false));

			if (rs.next()) 
			{
				_sNombre = rs.getString("nombre");
				_iTripulantes = rs.getInt("tripulantes");
				_sRegistro = sRegistro;
				_categoriaBarco = new CategoriaBarco(rs.getInt("id_categoriabarco"));
			}
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
	 * TODO: _sRegistro, _sNombre, _iTripulantes attributes musts exist
	 */
	@Override
	public String toString() 
	{
		return super.toString() + ":" + _sRegistro + ":" + _categoriaBarco.toString() + ":" + _sNombre + ":" + _iTripulantes;
	}

	/**
	 * public static Barco Create(String sRegistro, CategoriaBarco categoriaBarco, String sNombre, int iTripulantes)
	 * @param sRegistro
	 * @param categoriaBarco
	 * @param sNombre
	 * @param iTripulantes
	 * @return "Barco" Class instance
	 * @throws Exception
	 * Description: Create a new register in the "barco" table 
	 * TODO: Database is working correctly, Strin2Sql and Connection method which belongs to
	 * Data class work correctly
	 */
	public static Barco Create(String sRegistro, CategoriaBarco categoriaBarco, String sNombre, int iTripulantes) throws Exception 
	{
		Connection con = null;
		try 
		{
			con = Data.Connection();
			con.createStatement()
					.executeUpdate("INSERT INTO barco (registro, id_categoriabarco, nombre, tripulantes) " + " VALUES " + "("
							+ Data.String2Sql(sRegistro, true, false) + ", " + Data.String2Sql("" + categoriaBarco.getId() + "", true, false) + 
							", " + Data.String2Sql(sNombre, true, false)
							+ ", " + Data.String2Sql("" + iTripulantes + "", true, false) + ");");
			return new Barco(sRegistro);
		} catch (SQLException e) { throw e; } 
		finally { if (con != null) con.close(); }
	}

	/**
	 * public void Delete()
	 * @throws Exception
	 * Description: Delete a register of the "barco" table using data from the current instance
	 * TODO: Database is working correctly
	 */
	public void Delete() throws Exception 
	{

		if (_bIsDeleted) throw new Exception("Instancia creada previamente");

		Connection con = null;

		try 
		{
			con = Data.Connection();
			con.createStatement().executeUpdate(
					"DELETE FROM barco WHERE registro = " + Data.String2Sql(_sRegistro, true, false));
			_bIsDeleted = true;
		} catch (SQLException e) { throw e; } 
		finally { if (con != null) con.close(); }
	}

	/**
	 * public void Update()
	 * @throws Exception
	 * Description: Update the "barco" table using data from the current instance
	 * TODO: Database is working correctly
	 */
	public void Update() throws Exception 
	{
		if (_bIsDeleted) throw new Exception("Registro creado previamente");

		Connection con = null;

		try 
		{
			con = Data.Connection();	
			con.createStatement()
				.executeUpdate("UPDATE barco SET nombre = " + Data.String2Sql(_sNombre, true, false) + ", id_categoriabarco = " + _categoriaBarco.getId() 
						+ ", tripulantes = " + _iTripulantes + " WHERE registro = " + Data.String2Sql(_sRegistro, true, false));
			
		} catch (SQLException e) { throw e; } 
		finally { if (con != null) con.close(); }
	}

	/**
	 * public static ArrayList<Barco> Select(String sRegistro, String sCategoriaBarco, String sNombre, int iTripulantes)
	 * @param sRegistro
	 * @param sCategoriaBarco
	 * @param sNombre
	 * @param iTripulantes
	 * @return An ArrayList<Barco>
	 * @throws Exception
	 * Description: Get all registers from database, create and ArrayList<Barco> and return it
	 * TODO: Database is working correctly; is received "registro", "categoriabarco", "nombre" and "tripulantes" parameters
	 */
	public static ArrayList<Barco> Select(String sRegistro, String sCategoriaBarco, String sNombre, int iTripulantes) throws Exception 
	{
		ResultSet rs = null;
		Connection con = null;
		ArrayList<Barco> aBarco = new ArrayList<Barco>();

		try 
		{
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT B.registro FROM barco B INNER JOIN categoriabarco C ON B.id_categoriabarco = C.id"
					+ Barco.Where(sRegistro, sCategoriaBarco, sNombre, iTripulantes));
					
			while (rs.next()) { aBarco.add(new Barco(rs.getString("registro"))); }
				
			return aBarco;
		} catch (SQLException e) { throw e; } 
		finally 
		{
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}

	/**
	 * private static String Where(String _sRegistro, String sCategoriaBarco, String sNombre, int iTripulantes)
	 * @param _sRegistro
	 * @param sCategoriaBarco
	 * @param nombre
	 * @param tripulantes
	 * @return A string which contains "Where" conditions
	 * @throws Exception
	 * Description: Static private method which return a string that contains "Where" conditions
	 * TODO: Receive "_sRegistro", "sCategoriaBarco", "sNombre" and "iTripulantes" parameters
	 */
	private static String Where(String _sRegistro, String sCategoriaBarco, String sNombre, int iTripulantes) throws Exception 
	{
		String query = "";
		
		try 
		{
			if (_sRegistro != null)
				query += "B.registro LIKE " + Data.String2Sql(_sRegistro, true, true) + " AND ";
			if (sCategoriaBarco != null)
				query += "C.nombre LIKE " + Data.String2Sql(sCategoriaBarco, true, true) + " AND ";
			if (sNombre != null)
				query += "B.nombre LIKE " + Data.String2Sql(sNombre, true, true) + " AND ";
			if (iTripulantes >= 1)
				query += "B.tripulantes LIKE " + "" + iTripulantes + "" + " AND ";
			if (!query.isEmpty())
				query = " WHERE " + query.substring(0, query.length() - 5);
			
		} catch (Exception e) { throw e; } 
	
		return query;
	}
}