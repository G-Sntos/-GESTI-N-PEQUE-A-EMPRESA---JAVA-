import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class jFrameMenu extends JFrame{
	
	JButton insertar= new JButton();
	JButton eliminar= new JButton();
	JButton listado= new JButton();
	JButton modificarDatos= new JButton();
	JButton consultaEspeciales= new JButton();
	JButton salirPrograma= new JButton();
	
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItem1,menuItem2;
	
	public jFrameMenu(){
		//Base frame del menu
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		this.setSize(500, 300);
		this.setTitle("Tienda de Ropas -Machote S.L");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
		//set text buttones
	
		insertar.setText("Insertar Datos");
		eliminar.setText("Eliminar Datos");
		listado.setText("Listado Datos");
		modificarDatos.setText("Modificar Datos");
		consultaEspeciales.setText("Consultas");
		salirPrograma.setText("SALIR");
	
		
		//quitar border
		insertar.setFocusable(false);
		eliminar.setFocusable(false);
		listado.setFocusable(false);
		modificarDatos.setFocusable(false);
		consultaEspeciales.setFocusable(false);
		salirPrograma.setFocusable(false);
	
		//Tamaño butones
	
		insertar.setPreferredSize(new Dimension(200,50));
		eliminar.setPreferredSize(new Dimension(200,50));
		listado.setPreferredSize(new Dimension(200,50));
		modificarDatos.setPreferredSize(new Dimension(200,50));
		consultaEspeciales.setPreferredSize(new Dimension(200,50));
		salirPrograma.setPreferredSize(new Dimension(200,50));
	
		//Menu
		menuBar = new JMenuBar();// iniciar barra de menu
		menu = new JMenu("Fichero");
		menuItem1 = new JMenuItem("Crear BD");
		menuItem2 = new JMenuItem("INFO");
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);//añadir menu al la barra
		
		//añadir al frame
		
		this.add(insertar);
		this.add(eliminar);
		this.add(listado);
		this.add(modificarDatos);
		this.add(consultaEspeciales);
		this.add(salirPrograma);
		this.setJMenuBar(menuBar);
		
		this.setVisible(true);
		
		//acciones
		insertar.addActionListener(e->{
			insertarDatos insertar = new insertarDatos();
			this.dispose();
		});
		eliminar.addActionListener(e->{
			eliminarDatos eliminar = new eliminarDatos();
			this.dispose();
		});
		listado.addActionListener(e->{
			listadoDatos listado = new listadoDatos();
			this.dispose();
		});
		modificarDatos.addActionListener(e->{
			modificarDatos modificar = new modificarDatos();
			this.dispose();
		});
		consultaEspeciales.addActionListener(e->{
			consultaEspeciales consultas= new consultaEspeciales();
			this.dispose();
		});
		salirPrograma.addActionListener(e->{
			System.exit(0);
		});
		
		//acciones menuNav
		menuItem1.addActionListener(e->{
			main.crearBaseDeDatos();
		});
		menuItem2.addActionListener(e->{
			JOptionPane.showMessageDialog(null, "Programa para gestionar datos","Ropas Machote S.L", JOptionPane.INFORMATION_MESSAGE);
		});
		}
}