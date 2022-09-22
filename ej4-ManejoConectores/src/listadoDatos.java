import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;

public class listadoDatos extends JFrame {
	
	//j menu para volver al menu principal o opciones
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItem1,menuItem2;
	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	
	//area para mostrar resultado.
	JTextArea infoResulta = new JTextArea();
	JTextArea mostrarInfo = new JTextArea();
	//panel para scrollar
	JPanel container = new JPanel();
	JScrollPane scrPane = new JScrollPane();
	public listadoDatos() {
		//Base frame del menu
				this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
				this.setSize(500, 200);
				this.setTitle("Tienda de Ropas -Machote S.L");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
				
				//set text buttones
				button1.setText("Listado Articulo Superior");
				button2.setText("Listado Articulo Inferior");
				button3.setText("Listado Datos Cliente");
				button4.setText("Volver al Menu");
				//tamaño buttones
				button1.setPreferredSize(new Dimension(200,50));
				button2.setPreferredSize(new Dimension(200,50));
				button3.setPreferredSize(new Dimension(200,50));
				button4.setPreferredSize(new Dimension(200,50));
				//Quitar Border
				button1.setFocusable(false);
				button2.setFocusable(false);
				button3.setFocusable(false);
				button4.setFocusable(false);
				//añadir al frame
				this.add(button1);
				this.add(button2);
				this.add(button3);
				this.add(button4);
				this.setVisible(true);
				//acciones
				button1.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasArticuloSuperior =  "idArticulo"+"\t"+
														"Producto"+"\t"+
														"Tipo"+"\t"+
														"Talla"+"\t"+
														"Cantidad"+"\t"+
														"Precio";
				   
					JFrame mostraArticuloSuperior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					mostraArticuloSuperior.setSize(550, 300);
					mostraArticuloSuperior.setTitle("Mostrar Datos");
					mostraArticuloSuperior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					mostraArticuloSuperior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloSuperior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(mostrarArticuloSuperior()); // recibe el string que devuelve el funcion
					
					//container y sroll, acaso que el mostrar resulta devuelve un mas largo que el size del JFrame 
					//inciar
					container = new JPanel();
					scrPane = new JScrollPane(container);
					//meter policy para poder "Scroll"
					scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					container.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
					container.setBackground(new Color(245, 245, 220));
					//añadir el contenido del jtext al jpane
					container.add(infoResulta);
					container.add(mostrarInfo);
					
					// iniciar barra de menu
					menuBar = new JMenuBar();
					menu = new JMenu("Opciones");
					menuItem1 = new JMenuItem("Volver Opcion Listado");
					menuItem2 = new JMenuItem("Volver Opcion principal");
					menu.add(menuItem1);
					menu.add(menuItem2);
					menuBar.add(menu);//añadir menu al la barra
					
					//menu jbar acciones setup
						menuItem1.addActionListener(g->{
							listadoDatos listado = new listadoDatos();
							mostraArticuloSuperior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							mostraArticuloSuperior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					mostraArticuloSuperior.setJMenuBar(menuBar);
					mostraArticuloSuperior.add(container);
					mostraArticuloSuperior.setVisible(true);
					
				});
				
				button2.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "idArticulo"+"\t"+
														"Producto"+"\t"+
														"Tipo"+"\t"+
														"Talla"+"\t"+
														"Cantidad"+"\t"+
														"Precio";
				   
					JFrame mostraArticuloInferior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					mostraArticuloInferior.setSize(550, 300);
					mostraArticuloInferior.setTitle("Mostrar Datos");
					mostraArticuloInferior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					mostraArticuloInferior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(mostrarArticuloInferior()); // recibe el string que devuelve el funcion
					
					//container y sroll, acaso que el mostrar resulta devuelve un mas largo que el size del JFrame 
					//inciar
					container = new JPanel();
					scrPane = new JScrollPane(container);
					//meter policy para poder "Scroll"
					scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					container.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
					container.setBackground(new Color(245, 245, 220));
					//añadir el contenido del jtext al jpane
					container.add(infoResulta);
					container.add(mostrarInfo);
					// iniciar barra de menu
					menuBar = new JMenuBar();
					menu = new JMenu("Opciones");
					menuItem1 = new JMenuItem("Volver Opcion Listado");
					menuItem2 = new JMenuItem("Volver Opcion principal");
					menu.add(menuItem1);
					menu.add(menuItem2);
					menuBar.add(menu);//añadir menu al la barra
					
					//menu jbar acciones setup
						menuItem1.addActionListener(g->{
							listadoDatos listado = new listadoDatos();
							mostraArticuloInferior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							mostraArticuloInferior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					mostraArticuloInferior.setJMenuBar(menuBar);
					mostraArticuloInferior.add(container);
					mostraArticuloInferior.setVisible(true);
				});
				button3.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasCliente =  "idCliente"+"\t"+
														"Nombre"+"\t"+
														"Correo Electronico"+"\t"+
														"Telefono"+"\t"+
														"Fecha Alta";
				   
					JFrame mostrarCliente = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					mostrarCliente.setSize(550, 300);
					mostrarCliente.setTitle("Mostrar Datos");
					mostrarCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					mostrarCliente.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasCliente); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(mostrarCliente()); // recibe el string que devuelve el funcion
					
					//container y sroll, acaso que el mostrar resulta devuelve un mas largo que el size del JFrame 
					//inciar
					container = new JPanel();
					scrPane = new JScrollPane(container);
					//meter policy para poder "Scroll"
					scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					container.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
					container.setBackground(new Color(245, 245, 220));
					//añadir el contenido del jtext al jpane
					container.add(infoResulta);
					container.add(mostrarInfo);
					// iniciar barra de menu
					menuBar = new JMenuBar();
					menu = new JMenu("Opciones");
					menuItem1 = new JMenuItem("Volver Opcion Listado");
					menuItem2 = new JMenuItem("Volver Opcion principal");
					menu.add(menuItem1);
					menu.add(menuItem2);
					menuBar.add(menu);//añadir menu al la barra
					
					//menu jbar acciones setup
						menuItem1.addActionListener(g->{
							listadoDatos listado = new listadoDatos();
							mostrarCliente.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							this.dispose();
							mostrarCliente.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					mostrarCliente.setJMenuBar(menuBar);
					mostrarCliente.add(container);
					mostrarCliente.setVisible(true);
				});
				button4.addActionListener(e->{
					jFrameMenu menu = new jFrameMenu();
					this.dispose();
				});
	}
	 private Connection connect() { //para establecer conexion con el BD
	      
	        String url = "jdbc:sqlite:"+"EMPRESA.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 public String mostrarArticuloSuperior(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM parteSuperior";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("idArticuloSuperior") +  "\t" + 
	                                   rs.getString("nombreProducto") + "\t" +
	                                   rs.getString("tipoProducto") + "\t" +
	                                   rs.getString("tallaProducto") + "\t" +
	                                   rs.getInt("cantidadDisponible") + "\t" +
	                                   rs.getInt("precioSuperior")+ "\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String mostrarArticuloInferior(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM parteInferior";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("idArticuloInferior") +  "\t" + 
	                                   rs.getString("nombreProducto") + "\t" +
	                                   rs.getString("tipoProducto") + "\t" +
	                                   rs.getString("tallaProducto") + "\t" +
	                                   rs.getInt("cantidadDisponible") + "\t" +
	                                   rs.getInt("precioInferior")+ "\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String mostrarCliente(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM cliente";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("idCliente") +  "\t" + 
	                                   rs.getString("nombre") + "\t" +
	                                   rs.getString("email") + "\t" +
	                                   rs.getString("telefono") + "\t" +
	                                   rs.getString("fechaAlta")+ "\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
}
