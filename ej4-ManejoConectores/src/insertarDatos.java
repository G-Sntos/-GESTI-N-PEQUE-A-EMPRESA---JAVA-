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

public class insertarDatos extends JFrame {
	//int y String para meter datos de los inputs
	int idCliente,telefono,idArticulo,cantidadArticulo,precioArticulo;
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
	
	public insertarDatos() {
		//Base frame del menu
				this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
				this.setSize(500, 200);
				this.setTitle("Tienda de Ropas -Machote S.L");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
				//set text buttones
				button1.setText("Insertar Articulo Superior");
				button2.setText("Insertar Articulo Inferior");
				button3.setText("Insertar Datos Cliente");
				button4.setText("Volver al Menu");
				//Quitar Border
				button1.setFocusable(false);
				button2.setFocusable(false);
				button3.setFocusable(false);
				button4.setFocusable(false);
				//tamaño buttones
				button1.setPreferredSize(new Dimension(200,50));
				button2.setPreferredSize(new Dimension(200,50));
				button3.setPreferredSize(new Dimension(200,50));
				button4.setPreferredSize(new Dimension(200,50));
				//añadir al frame
				this.add(button1);
				this.add(button2);
				this.add(button3);
				this.add(button4);
				this.setVisible(true);
				//acciones
				button1.addActionListener(e->{
					JFrame insertarArticuloSuperior = new JFrame();
					insertarArticuloSuperior.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
					insertarArticuloSuperior.setSize(300, 400);                                       
					insertarArticuloSuperior.setTitle("Insertar Datos");                              
					insertarArticuloSuperior.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
					insertarArticuloSuperior.setResizable(false);                                     
					insertarArticuloSuperior.getContentPane().setBackground(new Color(245, 245, 220));
					
					//button insertar
					insertarButton.setFocusable(false);
					insertarButton.setPreferredSize(new Dimension(240,30));
					insertarButton.setText("Insertar Datos");
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
					insertarArticuloSuperior.add(idJtext);
					insertarArticuloSuperior.add(nombreJtext);
					insertarArticuloSuperior.add(infoJtext);
					insertarArticuloSuperior.add(tallaJtext);
					insertarArticuloSuperior.add(CantidadJtext);
					insertarArticuloSuperior.add(numeroJtext);
					insertarArticuloSuperior.add(insertarButton);
					
					insertarArticuloSuperior.setVisible(true);
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
							JOptionPane.showMessageDialog(null, "Dato(s) INVALIDO, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.WARNING_MESSAGE);
							 error = true;
						}
						if(!error) {
							insertArticuloSuperiorBD(idArticulo, nombreProducto, tipoProducto, tallaProducto, cantidadArticulo, precioArticulo);
						}else {
							insertarArticuloSuperior.dispose();
						}
					});
					
				});
				button2.addActionListener(e->{
					JFrame insertarArticuloInferior = new JFrame();
					insertarArticuloInferior.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
					insertarArticuloInferior.setSize(300, 400);                                       
					insertarArticuloInferior.setTitle("Insertar Datos");                              
					insertarArticuloInferior.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
					insertarArticuloInferior.setResizable(false);                                     
					insertarArticuloInferior.getContentPane().setBackground(new Color(245, 245, 220));
					
					//button insertar
					insertarButton.setFocusable(false);
					insertarButton.setPreferredSize(new Dimension(240,30));
					insertarButton.setText("Insertar Datos");
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
					insertarArticuloInferior.add(idJtext);
					insertarArticuloInferior.add(nombreJtext);
					insertarArticuloInferior.add(infoJtext);
					insertarArticuloInferior.add(tallaJtext);
					insertarArticuloInferior.add(CantidadJtext);
					insertarArticuloInferior.add(numeroJtext);
					insertarArticuloInferior.add(insertarButton);
					
					insertarArticuloInferior.setVisible(true);
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
							JOptionPane.showMessageDialog(null, "Dato(s) INVALIDO, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.WARNING_MESSAGE);
							 error = true;
						}
						if(!error) {
							insertArticuloInferiorBD(idArticulo, nombreProducto, tipoProducto, tallaProducto, cantidadArticulo, precioArticulo);
						}else {
							insertarArticuloInferior.dispose();
						}
					
						
					});
				});
				button3.addActionListener(e->{
					JFrame insertarCliente = new JFrame();
					insertarCliente.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));      
					insertarCliente.setSize(300, 300);                                       
					insertarCliente.setTitle("Insertar Datos");                              
					insertarCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          
					insertarCliente.setResizable(false);                                     
					insertarCliente.getContentPane().setBackground(new Color(245, 245, 220));
					
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
					
					insertarCliente.add(idJtext);
					insertarCliente.add(nombreJtext);
					insertarCliente.add(infoJtext);
					insertarCliente.add(numeroJtext);
					insertarCliente.add(insertarButton);
					
					insertarCliente.setVisible(true);
					//Accion cuando pulsa el button insertar datos
					insertarButton.addActionListener(f->{
						//4/5/2022 : 15:00
						//FALTA INSERTAR DATOS PARA CLIENTE.
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
							insertClienteBD(idCliente, nombreProducto, email, telefono);
						}else {
							insertarCliente.dispose();
						}
					});
				});
				button4.addActionListener(e->{
					jFrameMenu menu = new jFrameMenu();
					this.dispose();
				});
	} 
	
	

	//funcion para connectar al DB
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
	//funciones para insertar:
	public void insertArticuloSuperiorBD(int idArticulo, String nombreProducto,String tipoProducto,String tallaProducto,int cantidadDisponible,int precioSuperior) {

        String sql = "INSERT INTO parteSuperior(idArticuloSuperior,nombreProducto,tipoProducto,tallaProducto,cantidadDisponible,precioSuperior) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { //usando el conn del sqlite
            pstmt.setInt(1, idArticulo);
            pstmt.setString(2, nombreProducto);
            pstmt.setString(3, tipoProducto);
            pstmt.setString(4, tallaProducto);
            pstmt.setInt(5, cantidadDisponible);
            pstmt.setInt(6, precioSuperior);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS INSERTADO INFORMACION en Articulo Superior!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
	public void insertArticuloInferiorBD(int idArticulo, String nombreProducto,String tipoProducto,String tallaProducto,int cantidadDisponible,int precioInferior) {

        String sql = "INSERT INTO parteInferior(idArticuloInferior,nombreProducto,tipoProducto,tallaProducto,cantidadDisponible,precioInferior) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { //usando el conn del sqlite
            pstmt.setInt(1, idArticulo);
            pstmt.setString(2, nombreProducto);
            pstmt.setString(3, tipoProducto);
            pstmt.setString(4, tallaProducto);
            pstmt.setInt(5, cantidadDisponible);
            pstmt.setInt(6, precioInferior);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS INSERTADO INFORMACION en Articulo Inferior!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
	public void insertClienteBD(int idCliente, String nombreCliente,String emailCliente,int telefono) {

        String sql = "INSERT INTO cliente(idCliente,nombre,email,telefono,fechaAlta) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) { //usando el conn del sqlite   
            pstmt.setInt(1, idCliente);
            pstmt.setString(2, nombreCliente);
            pstmt.setString(3, emailCliente);
            pstmt.setInt(4, telefono);
            pstmt.setString(5, String.valueOf(java.time.LocalDate.now()));
           
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS INSERTADO INFORMACION en Cliente!","INSERTAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","INSERTAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
}
