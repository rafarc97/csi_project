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
	private JTextField txtRegistro;
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
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(10, 23, 54, 23);
		getContentPane().add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setBounds(10, 48, 96, 19);
		getContentPane().add(txtRegistro);
		txtRegistro.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(149, 28, 45, 13);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(149, 48, 96, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTripulantes = new JLabel("Tripulantes");
		lblTripulantes.setBounds(280, 28, 67, 13);
		getContentPane().add(lblTripulantes);
		
		txtTripulantes = new JTextField();
		txtTripulantes.setBounds(280, 48, 96, 19);
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
			txtRegistro.setText(_barco.getRegistro());
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
					if (txtRegistro.getText().isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "Campo Vacío", "Error en el campo Registro",
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
						_barco = Barco.Create(txtRegistro.getText(), (CategoriaBarco)cmbCategoriaBarco.getModel().getSelectedItem(), txtNombre.getText(), Integer.parseInt(txtTripulantes.getText()));
					}
					else 
					{
						_barco.setRegistro(txtRegistro.getText());
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
		butSave.setBounds(291, 104, 85, 21);
		getContentPane().add(butSave);	
	}
}