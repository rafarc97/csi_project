package es.uca.gii.csi21.turner.gui;

import java.util.List;
import es.uca.gii.csi21.turner.data.CategoriaBarco;
import javax.swing.AbstractListModel;

public class CategoriaBarcoListModel extends AbstractListModel<CategoriaBarco> implements  javax.swing.ComboBoxModel<CategoriaBarco>
{
	private static final long serialVersionUID = 1L;
	private List<CategoriaBarco> _aData;
	private Object _oSelectedItem = null;
	
	/**
	 * public CategoriaBarcoListModel(List<CategoriaBarco> aData)
	 * @param aData
	 */
	public CategoriaBarcoListModel(List<CategoriaBarco> aData) { _aData = aData; }

	@Override
	public CategoriaBarco getElementAt(int iIndex) { return _aData.get(iIndex); }

	@Override
	public int getSize() { return _aData.size(); }
	public Object getSelectedItem() { return _oSelectedItem; }
	public void setSelectedItem(Object oSelectedItem) { _oSelectedItem = oSelectedItem; }
}