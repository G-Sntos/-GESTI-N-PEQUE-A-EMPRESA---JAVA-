	import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class modificarDatos extends JFrame{
	//int y String para meter datos de los inputs
	int idCliente,telefono,idArticulo,cantidadArticulo,precioArticulo,inputUsuario;
	String nombre,email,nombreProducto,tipoProducto,tallaProducto;
	boolean error; // para comprobar datos introducidos
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	
	JButton insertarButton = new JButton();
	//text fields para recibir inputs
	JTextField idJtext		= new JTextField("");
	JTextField nombreJtext	= new JTextField("");
	JTextField infoJtext	 = new JTextField("");
	JTextField numeroJtext 	= new JTextField("");
	JTextField CantidadJtext	 = new JTextField("");
	JTextField tallaJtext 	= new JTextField("");
	
		public modificarDatos() {
			//Base frame del menu
			this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
			this.setSize(500, 200);
			this.setTitle("Tienda de Ropas -Machote S.L");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
			//set text buttones
			button1.setText("Modificar Articulo Superior");
			button2.setText("Modificar Articulo Inferior");
			button3.setText("Modificar Datos Cliente");
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
				JFrame actualizarArticuloSuperior = new JFrame();
				actualizarArticuloSuperior.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
				actualizarArticuloSuperior.setSize(300, 400);                                       
				actualizarArticuloSuperior.setTitle("Insertar Datos");                              
				actualizarArticuloSuperior.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
				actualizarArticuloSuperior.setResizable(false);                                     
				actualizarArticuloSuperior.getContentPane().setBackground(new Color(245, 245, 220));
				
				//button insertar
				insertarButton.setFocusable(false);
				insertarButton.setPreferredSize(new Dimension(240,30));
				insertarButton.setText("Modificar Datos");
				//JText field
				idJtext.setText("ID Articulo");
				nombreJtext.setText("Nombre Producto");
				infoJtext.setText("Tipo Producto");	
				tallaJtext.setText("Talla Producto");
				CantidadJtext.setText("Cantidad Disponible");
				numeroJtext.setText("Precio Disponible"); 
				//Tamaño Jtext Field
				idJtext.setPreferredSize(new Dimension(240,30));
				nombreJtext.setPreferredSize(new Dimension(240,30));
				infoJtext.setPreferredSize(new Dimension(240,30));
				numeroJtext.setPreferredSize(new Dimension(240,30));	
				tallaJtext.setPreferredSize(new Dimension(240,30));
				CantidadJtext.setPreferredSize(new Dimension(240,30));
				//añdir al frame nuevo
				actualizarArticuloSuperior.add(idJtext);
				actualizarArticuloSuperior.add(nombreJtext);
				actualizarArticuloSuperior.add(infoJtext);
				actualizarArticuloSuperior.add(tallaJtext);
				actualizarArticuloSuperior.add(CantidadJtext);
				actualizarArticuloSuperior.add(numeroJtext);
				actualizarArticuloSuperior.add(insertarButton);
				actualizarArticuloSuperior.setVisible(true);
				try {
					inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
				}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
					System.out.println(b);
					actualizarArticuloSuperior.setVisible(false);//acaso que ponen algo mas que un numero, no se muestra el menu para modificar datos
					
					}
				//Accion cuando pulsa el button insertar datos
				insertarButton.addActionListener(f->{
				
					try {

						idArticulo = Integer.valueOf(idJtext.getText());
						nombreProducto = nombreJtext.getText();
						tipoProducto = infoJtext.getText();	
						tallaProducto = tallaJtext.getText();
						cantidadArticulo = Integer.valueOf(CantidadJtext.getText());
						precioArticulo = Integer.valueOf(numeroJtext.getText());	
					}catch (InputMismatchException | NumberFormatException | NullPointerException a) {// acaso que intentan meter datos fuera de lo que pide
						System.out.println(a);
						actualizarArticuloSuperior.dispose();
					}
					if(!error) {
						updateArticuloSuperiorBD(idArticulo, nombreProducto, tipoProducto, tallaProducto, cantidadArticulo, precioArticulo,inputUsuario);
					}else {
						
						JOptionPane.showMessageDialog(null, "Dato(s) INVALIDO, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.WARNING_MESSAGE);
					}
				});
			});
			button2.addActionListener(e->{
				JFrame actualizarArticuloInferior = new JFrame();
				actualizarArticuloInferior.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
				actualizarArticuloInferior.setSize(300, 400);                                       
				actualizarArticuloInferior.setTitle("Insertar Datos");                              
				actualizarArticuloInferior.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
				actualizarArticuloInferior.setResizable(false);                                     
				actualizarArticuloInferior.getContentPane().setBackground(new Color(245, 245, 220));
				
				//button insertar
				insertarButton.setFocusable(false);
				insertarButton.setPreferredSize(new Dimension(240,30));
				insertarButton.setText("Modificar Datos");
				//JText field
				idJtext.setText("ID Articulo");
				nombreJtext.setText("Nombre Producto");
				infoJtext.setText("Tipo Producto");	
				tallaJtext.setText("Talla Producto");
				CantidadJtext.setText("Cantidad Disponible");
				numeroJtext.setText("Precio Disponible"); 
				//Tamaño Jtext Field
				idJtext.setPreferredSize(new Dimension(240,30));
				nombreJtext.setPreferredSize(new Dimension(240,30));
				infoJtext.setPreferredSize(new Dimension(240,30));
				numeroJtext.setPreferredSize(new Dimension(240,30));	
				tallaJtext.setPreferredSize(new Dimension(240,30));
				CantidadJtext.setPreferredSize(new Dimension(240,30));
				//añdir al frame nuevo
				actualizarArticuloInferior.add(idJtext);
				actualizarArticuloInferior.add(nombreJtext);
				actualizarArticuloInferior.add(infoJtext);
				actualizarArticuloInferior.add(tallaJtext);
				actualizarArticuloInferior.add(CantidadJtext);
				actualizarArticuloInferior.add(numeroJtext);
				actualizarArticuloInferior.add(insertarButton);
				actualizarArticuloInferior.setVisible(true);
				try {
					inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
				}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
					System.out.println(b);
					actualizarArticuloInferior.setVisible(false);//acaso que ponen algo mas que un numero, no se muestra el menu para modificar datos
					
					}
				//Accion cuando pulsa el button insertar datos
				insertarButton.addActionListener(f->{
				
					try {

						idArticulo = Integer.valueOf(idJtext.getText());
						nombreProducto = nombreJtext.getText();
						tipoProducto = infoJtext.getText();	
						tallaProducto = tallaJtext.getText();
						cantidadArticulo = Integer.valueOf(CantidadJtext.getText());
						precioArticulo = Integer.valueOf(numeroJtext.getText());	
					}catch (InputMismatchException | NumberFormatException | NullPointerException a) {// acaso que intentan meter datos fuera de lo que pide
						System.out.println(a);
						actualizarArticuloInferior.dispose();
					}
					if(!error) {
						updateArticuloInferiorBD(idArticulo, nombreProducto, tipoProducto, tallaProducto, cantidadArticulo, precioArticulo,inputUsuario);
					}else {
						
						JOptionPane.showMessageDialog(null, "Dato(s) INVALIDO, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.WARNING_MESSAGE);
					}
				});
			});
			button3.addActionListener(e->{
				JFrame modificarCliente = new JFrame();
				modificarCliente.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
				modificarCliente.setSize(300, 300);                                       
				modificarCliente.setTitle("Insertar Datos");                              
				modificarCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
				modificarCliente.setResizable(false);                                     
				modificarCliente.getContentPane().setBackground(new Color(245, 245, 220));
				
				//button insertar
				insertarButton.setFocusable(false);
				insertarButton.setPreferredSize(new Dimension(240,30));
				insertarButton.setText("Insertar Datos");
				//JText field
				idJtext.setText("ID Cliente");
				nombreJtext.setText("Nombre");
				infoJtext.setText("Email Cliente");	
				numeroJtext.setText("Telefono Cliente"); 	
				//Tamaño Jtext Field
				idJtext.setPreferredSize(new Dimension(240,30));
				nombreJtext.setPreferredSize(new Dimension(240,30));
				infoJtext.setPreferredSize(new Dimension(240,30));
				numeroJtext.setPreferredSize(new Dimension(240,30));	
				
				modificarCliente.add(idJtext);
				modificarCliente.add(nombreJtext);
				modificarCliente.add(infoJtext);
				modificarCliente.add(numeroJtext);
				modificarCliente.add(insertarButton);
				
				modificarCliente.setVisible(true);
				try {
					inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
				}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
					System.out.println(b);
					modificarCliente.setVisible(false);//acaso que ponen algo mas que un numero, no se muestra el menu para modificar datos
					
					}
				//Accion cuando pulsa el button insertar datos
				insertarButton.addActionListener(f->{
					
					try {
						idCliente= Integer.valueOf(idJtext.getText());
						nombreProducto = nombreJtext.getText();
						email = infoJtext.getText();	
						telefono = Integer.valueOf(numeroJtext.getText());
						
					}catch (InputMismatchException | NumberFormatException | NullPointerException a) {// acaso que intentan meter datos fuera de lo que pide
						JOptionPane.showMessageDialog(null, "Dato(s) INVALIDO, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.WARNING_MESSAGE);
						 error = true;
					}
					if(!error) {
						updateClienteBD(idCliente, nombreProducto, email, telefono,inputUsuario);
					}else {
						modificarCliente.dispose();
					}
				});
			});
			button4.addActionListener(e->{
				jFrameMenu menu = new jFrameMenu();
				this.dispose();
			});
			
		}
		 private Connection connect() {
		        // SQLite connection string
		        String url = "jdbc:sqlite:"+"EMPRESA.db";
		        Connection conn = null;
		        try {
		            conn = DriverManager.getConnection(url);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		        return conn;
		    }
		 public void updateArticuloSuperiorBD(int idArticulo, String nombreProducto,String tipoProducto,String tallaProducto,int cantidadDisponible,int precioSuperior,int inputUsuario) {

		        String sql = "UPDATE parteSuperior SET idArticuloSuperior=?,nombreProducto=?,tipoProducto=?,tallaProducto=?,cantidadDisponible=?,precioSuperior=? WHERE idArticuloSuperior="+inputUsuario;

		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)){ //usando el conn del sqlite
	
		            pstmt.setInt(1, idArticulo);
		            pstmt.setString(2, nombreProducto);
		            pstmt.setString(3, tipoProducto);
		            pstmt.setString(4, tallaProducto);
		            pstmt.setInt(5, cantidadDisponible);
		            pstmt.setInt(6, precioSuperior);
		            pstmt.executeUpdate();
		            JOptionPane.showMessageDialog(null, "HAS MODIFICADO INFORMACION en Articulo Superior!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		 public void updateArticuloInferiorBD(int idArticulo, String nombreProducto,String tipoProducto,String tallaProducto,int cantidadDisponible,int precioSuperior,int inputUsuario) {

		        String sql = "UPDATE parteInferior SET idArticuloInferior=?,nombreProducto=?,tipoProducto=?,tallaProducto=?,cantidadDisponible=?,precioInferior=? WHERE idArticuloInferior="+inputUsuario;

		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)){ //usando el conn del sqlite
	        		
		            pstmt.setInt(1, idArticulo);
		            pstmt.setString(2, nombreProducto);
		            pstmt.setString(3, tipoProducto);
		            pstmt.setString(4, tallaProducto);
		            pstmt.setInt(5, cantidadDisponible);
		            pstmt.setInt(6, precioSuperior);
		            pstmt.executeUpdate();
		            JOptionPane.showMessageDialog(null, "HAS MODIFICADO INFORMACION en Articulo Inferior!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		 public void updateClienteBD(int idCliente, String nombreCliente,String emailCliente,int telefono,int inputUsuario) {

		        String sql = "UPDATE cliente SET idCliente=?,nombre=?,email=?,telefono=? WHERE idCliente="+inputUsuario;

		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) { //usando el conn del sqlite   
		            pstmt.setInt(1, idCliente);
		            pstmt.setString(2, nombreCliente);
		            pstmt.setString(3, emailCliente);
		            pstmt.setInt(4, telefono);
		            pstmt.executeUpdate();
		            JOptionPane.showMessageDialog(null, "HAS INSERTADO INFORMACION en Cliente!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
		        }
		    }
}
