package es.uca.gii.csi21.turner.gui;

import es.uca.gii.csi21.turner.data.Barco;
import java.util.ArrayList;

public class BarcosTableModel extends javax.swing.table.AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Barco> _aData;
	
	/**
	 * public BarcosTableModel(ArrayList<Barco> aData)
	 * @param aData
	 * Description: Assign aData var value to _aData private var attribute
	 * TODO: Must receive aData var
	 */
	public BarcosTableModel(ArrayList<Barco> aData) { _aData = aData; }
	
	@Override
	public int getColumnCount() { return 4; }
	
	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public Object getValueAt(int iRow, int iCol) throws IllegalStateException
	{
		switch (iCol) 
		{
			case 0: return _aData.get(iRow).getId();
			case 1: return _aData.get(iRow).getNombre();
			case 2: return _aData.get(iRow).getTripulantes();
			case 3: return _aData.get(iRow).getCategoriaBarco().getNombre();
			default: throw new IllegalStateException("Error. Registro no encontrado");
		}
	}
	public Barco getData(int iRow) { return _aData.get(iRow); }
}