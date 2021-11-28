package es.uca.gii.csi21.turner.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrMain 
{

	private JFrame frame;

	/**
	 * public static void main(String[] args)
     * @param args
	 * Description: Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FrMain window = new FrMain();
					window.frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	/**
	 * public FrMain() 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * Description: Create the application.
	 */
	public FrMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * private void initialize() 
	 * Description: Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("Aplicaci\u00F3n Turner");
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mitNew = new JMenu("Nuevo");
		menuBar.add(mitNew);
		
		JMenuItem mitNewBarco = new JMenuItem("Barco");
		mitNewBarco.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IfrBarco ifrBarco = null;
				try {
					ifrBarco = new IfrBarco(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ifrBarco.setBounds(10, 27, 450, 350);
				frame.getContentPane().add(ifrBarco);
				ifrBarco.setVisible(true);
			}
		});
		mitNew.add(mitNewBarco);
		
		JMenu mitSearch = new JMenu("Buscar");
		menuBar.add(mitSearch);
		
		JMenuItem mitSearchBarco = new JMenuItem("Barco");
		mitSearchBarco.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IfrBarcos ifrBarcos = null;
				try {
					ifrBarcos = new IfrBarcos(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ifrBarcos.setBounds(10, 27, 450, 350);
				// Second parameter makes always appear in the foreground appear in the foreground
				frame.getContentPane().add(ifrBarcos, 0);
				ifrBarcos.setVisible(true);
			}
		});
		mitSearch.add(mitSearchBarco);
		frame.getContentPane().setLayout(null);
	}
}
