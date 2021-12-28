package es.uca.gii.csi21.turner.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import es.uca.gii.csi21.turner.data.Barco;
import es.uca.gii.csi21.turner.data.CategoriaBarco;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IfrBarco extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtTripulantes;
	private Barco _barco = null;

	/**
	 * public IfrBarco() 
	 * @param barco
	 * Description: Create the frame.
	 * @throws Exception 
	 */
	public IfrBarco(Barco barco) throws Exception 
	{
		_barco = barco;
		setResizable(true);
		setClosable(true);
		setTitle("Barco");
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 28, 45, 13);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 48, 96, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTripulantes = new JLabel("Tripulantes");
		lblTripulantes.setBounds(116, 28, 67, 13);
		getContentPane().add(lblTripulantes);
		
		txtTripulantes = new JTextField();
		txtTripulantes.setBounds(116, 48, 96, 19);
		getContentPane().add(txtTripulantes);
		txtTripulantes.setColumns(10);
		
		JComboBox<CategoriaBarco> cmbCategoriaBarco = new JComboBox<CategoriaBarco>();
		cmbCategoriaBarco.setModel(new CategoriaBarcoListModel(CategoriaBarco.Select()));
		cmbCategoriaBarco.setBounds(10, 104, 100, 20);
		getContentPane().add(cmbCategoriaBarco);
		
		JLabel lblCategoriaBarco = new JLabel("Categoria Barco");
		lblCategoriaBarco.setBounds(10, 77, 96, 17);
		getContentPane().add(lblCategoriaBarco);
		
		if(_barco != null) 
		{
			txtNombre.setText(_barco.getNombre());
			txtTripulantes.setText("" + _barco.getTripulantes() + "");
			cmbCategoriaBarco.getModel().setSelectedItem(_barco.getCategoriaBarco());
		}
		
		JButton butSave = new JButton("Guardar");
		butSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if ((CategoriaBarco) cmbCategoriaBarco.getModel().getSelectedItem() == null)
					{
						JOptionPane.showMessageDialog(null, "Campo Vacío", "Error en el campo CategoriaBarco",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (txtNombre.getText().isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "Campo Vacío", "Error en el campo Nombre",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (txtTripulantes.getText().isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "Campo Vacío", "Error en el campo Tripulantes",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (_barco == null)
					{
						_barco = Barco.Create((CategoriaBarco)cmbCategoriaBarco.getModel().getSelectedItem(), txtNombre.getText(), Integer.parseInt(txtTripulantes.getText()));
					}
					else 
					{
						_barco.setNombre(txtNombre.getText());
						_barco.setTripulantes(Integer.parseInt(txtTripulantes.getText()));						
						_barco.Update();
					}
				}catch (NumberFormatException ee) 
				{
					JOptionPane.showMessageDialog(null,
							"Error. Se ha encontrado alguna letra en el campo tripulantes",
							"Error en el campo Tripulantes", JOptionPane.ERROR_MESSAGE);

				}catch (Exception eee) 
				{
					JOptionPane.showMessageDialog(null, "Ha habido un error", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		butSave.setBounds(127, 104, 85, 21);
		getContentPane().add(butSave);	
	}
}