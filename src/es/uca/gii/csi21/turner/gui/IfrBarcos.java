package es.uca.gii.csi21.turner.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import es.uca.gii.csi21.turner.data.Barco;
import es.uca.gii.csi21.turner.data.CategoriaBarco;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class IfrBarcos extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtTripulantes;
	private JTable tabResult;
	private JFrame pnlParent;

	/**
	 * @param frame
	 * Description: Create the frame.
	 * @throws Exception 
	 */
	public IfrBarcos(JFrame frame) throws Exception 
	{
		pnlParent = frame;
		setResizable(true);
		setClosable(true);
		setTitle("Barcos");
		setBounds(100, 100, 1100, 800);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTripulantes = new JLabel("Tripulantes");
		panel.add(lblTripulantes);
		
		txtTripulantes = new JTextField();
		panel.add(txtTripulantes);
		txtTripulantes.setColumns(10);
		
		JLabel lblCategoriaBarco = new JLabel("Categoria Barco");
		panel.add(lblCategoriaBarco);
		
		JComboBox<CategoriaBarco> cmbCategoriaBarco = new JComboBox<CategoriaBarco>();
		cmbCategoriaBarco.setModel(new CategoriaBarcoListModel(CategoriaBarco.Select()));
		cmbCategoriaBarco.setBounds(200, 50, 100, 100);		
		cmbCategoriaBarco.setEditable(true);
		panel.add(cmbCategoriaBarco);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					tabResult.setModel(
							 new BarcosTableModel(Barco.Select(
									 cmbCategoriaBarco.getModel().getSelectedItem() == null ? null : cmbCategoriaBarco.getModel().getSelectedItem().toString(),
									 txtNombre.getText().isEmpty() ? null : txtNombre.getText(),
								     txtTripulantes.getText().isEmpty() ? 0 : Integer.parseInt(txtTripulantes.getText()))));

				}catch (NumberFormatException ee) 
				{
					JOptionPane.showMessageDialog(null,
							"Error. Encontrado al menos un caracter no num en un campo tipo numérico.",
							"Error", JOptionPane.ERROR_MESSAGE);

				}catch (Exception i) 
				{
					JOptionPane.showMessageDialog(null, "Error durante la búsqueda.", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panel.add(btnBuscar);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() == 2) 
				{
					 int iRow = ((JTable)e.getSource()).getSelectedRow();
					 Barco barco = ((BarcosTableModel) tabResult.getModel()).getData(iRow);
					 if (barco != null) 
					 {
						 IfrBarco ifrBarco = null;
						try 
						{
							ifrBarco = new IfrBarco(barco);
							ifrBarco.setBounds(10, 27, 244, 192);
							pnlParent.getContentPane().add(ifrBarco, 0);
							ifrBarco.setVisible(true);
						} catch (Exception e1) 
						{
							JOptionPane.showMessageDialog(null, "Error al abrir detalles del barco seleccionado", "Error",
									JOptionPane.ERROR_MESSAGE); 
						}
					 }
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);
	}
}