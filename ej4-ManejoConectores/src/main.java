import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class main {

	public static void main(String[] args) {
		jFrameMenu a = new jFrameMenu();
		//crearBaseDeDatos();
	}
	public static void crearBaseDeDatos() {
	 	boolean creado = false;
        String url = "jdbc:sqlite:" + "EMPRESA.db"; //hacer el bd via sqlite y nombrar "main" que se encuentra en el mismo ruta del proyecto

        try (Connection conn = DriverManager.getConnection(url)) {  // tener conexion al bd
            if (conn != null) {
            	creado = true;
                DatabaseMetaData meta = conn.getMetaData();
                JOptionPane.showMessageDialog(null, "BASE DE DATOS CREADO!",meta.getDriverName(), JOptionPane.INFORMATION_MESSAGE);
            }
            createNewTable();
            if(creado)JOptionPane.showMessageDialog(null, "TABLAS CREADO!","TABLAS BASES DE DATOS", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
    }
 public static void createNewTable() {
        // SQLite locacion del bd
        String url = "jdbc:sqlite:"+"EMPRESA.db";
        
        // SQL statement para crear tablas
        String sqlSuperior = "CREATE TABLE IF NOT EXISTS parteSuperior(\r\n"
        		+ "idArticuloSuperior INT(5) PRIMARY KEY,\r\n"
        		+ "nombreProducto CHAR(30) NOT NULL,\r\n"
        		+ "tipoProducto CHAR(30),\r\n"
        		+ "tallaProducto CHAR(5),\r\n"
        		+ "cantidadDisponible INT(10),\r\n"
        		+ "precioSuperior INT(5));\r\n"
        		+ ");";
        
        String sqlInferior = "CREATE TABLE IF NOT EXISTS parteInferior(\r\n"
        		+ "idArticuloInferior INT(5) PRIMARY KEY,\r\n"
        		+ "nombreProducto CHAR(30) NOT NULL,\r\n"
        		+ "tipoProducto CHAR(30),\r\n"
        		+ "tallaProducto CHAR(1),\r\n"
        		+ "cantidadDisponible INT(10),\r\n"
        		+ "precioInferior INT(5));\r\n"
        		+ ");";
        String sqlCliente = "CREATE TABLE cliente(\r\n"
        		+ "idCliente INT(5) PRIMARY KEY,\r\n"
        		+ "nombre CHAR(30) NOT NULL,\r\n"
        		+ "email CHAR(30),\r\n"
        		+ "telefono INT(12),\r\n"
        		+ "fechaAlta DATE);\r\n"
        		+ ");";
        String sqlPedidos = "CREATE TABLE IF NOT EXISTS pedidos(\r\n"
        		+ "idPedido INT(5) PRIMARY KEY,\r\n"
        		+ "idCliente INT(5), \r\n"
        		+ "articuloSuperior CHAR(30) NOT NULL,\r\n"
        		+ "articuloInferior CHAR(30) NOT NULL,\r\n"
        		+ "cantidadProductoTotal INT(3) NOT NULL,\r\n"
        		+ "precioTotal INT(12),\r\n"
        		+ "fechaCompra DATE,\r\n"
        		+ "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),\r\n"
        		+ "FOREIGN KEY (articuloSuperior) REFERENCES parteSuperior(idArticulo),\r\n"
        		+ "FOREIGN KEY (articuloInferior) REFERENCES parteInferior(idArticulo));\r\n"
        		+ ");";
        		
    	        
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // crear los tablas
        	stmt.execute(sqlSuperior);
        	stmt.execute(sqlInferior);
         	stmt.execute(sqlCliente);
        	stmt.execute(sqlPedidos);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
}
