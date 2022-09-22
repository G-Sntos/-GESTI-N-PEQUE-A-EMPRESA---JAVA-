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

public class eliminarDatos extends JFrame {
	int inputUsuario;
	boolean errorCheck;
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	public eliminarDatos() {
		//Base frame del menu
				this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
				this.setSize(500, 200);
				this.setTitle("Tienda de Ropas -Machote S.L");
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				this.setResizable(false);
				this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
				//set text buttones
				button1.setText("Eliminar Articulo Superior");
				button2.setText("Eliminar Articulo Inferior");
				button3.setText("Eliminar Datos Cliente");
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
					try { 
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));
						
					}catch(InputMismatchException | NumberFormatException | NullPointerException a) {  // acaso que intentan meter datos fuera de lo que pide
						 errorCheck = true;
						
					}
					if(!errorCheck) {
						borrarArticuloSuperior(inputUsuario);
					}else {
						JOptionPane.showMessageDialog(null, "Dato INVALIDO/NO EXISTE, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.ERROR_MESSAGE);
					}
				});
				button2.addActionListener(e->{
					try { 
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));
						
					}catch(InputMismatchException | NumberFormatException | NullPointerException a) {  // acaso que intentan meter datos fuera de lo que pide
						 errorCheck = true;
						
					}
					if(!errorCheck) {
						borrarArticuloInferior(inputUsuario);
					}else {
						JOptionPane.showMessageDialog(null, "Dato INVALIDO/NO EXISTE, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.ERROR_MESSAGE);
					}
				});
				button3.addActionListener(e->{
					try { 
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));
						
					}catch(InputMismatchException | NumberFormatException | NullPointerException a) {  // acaso que intentan meter datos fuera de lo que pide
						 errorCheck = true;
						
					}
					if(!errorCheck) {
						borrarCliente(inputUsuario);
					}else {
						JOptionPane.showMessageDialog(null, "Dato INVALIDO/NO EXISTE, VUELVA A INTRODUCIR.","Insertar Datos", JOptionPane.ERROR_MESSAGE);
					}
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
	public void borrarArticuloSuperior(int id) {
        String sql = "DELETE FROM parteSuperior WHERE idArticuloSuperior = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // montar param
            pstmt.setInt(1, id);
            // ejecutar  delete statement
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS BORRADO INFORMACION! en ARTICULO SUPERIOR","BORRAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","BORRAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
	public void borrarArticuloInferior(int id) {
        String sql = "DELETE FROM parteInferior WHERE idArticuloInferior = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // montar param
            pstmt.setInt(1, id);
            // ejecutar  delete statement
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS BORRADO INFORMACION! en ARTICULO INFERIOR","BORRAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","BORRAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
	public void borrarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // montar param
            pstmt.setInt(1, id);
            // ejecutar  delete statement
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "HAS BORRADO INFORMACION! en CLIENTE","BORRAR DATOS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR,MIRA CONSOLA","BORRAR DATOS", JOptionPane.WARNING_MESSAGE);
        }
    }
}
