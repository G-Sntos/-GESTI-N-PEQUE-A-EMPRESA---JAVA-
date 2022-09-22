import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class consultaEspeciales extends JFrame{
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItem1,menuItem2;
	//area para mostrar resultado.
	JTextArea infoResulta = new JTextArea();
	JTextArea mostrarInfo = new JTextArea();
	//panel para poder scrollar
	JPanel container = new JPanel();
	JScrollPane scrPane = new JScrollPane();
	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	JButton button5 = new JButton();
	JButton button6 = new JButton();
	JButton button7 = new JButton();
	JButton button8 = new JButton();
	JButton button9 = new JButton();
	JButton button10 = new JButton();
	JButton button11 = new JButton();
	
	int inputUsuario;
	public consultaEspeciales() {
		//Base frame del menu
				this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
				this.setSize(505, 450);
				this.setTitle("Tienda de Ropas -Machote S.L");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.getContentPane().setBackground(new Color(173, 202, 214)); // Colombia blue
				//set text buttones
				button1.setText("Todo los pedidos");
				button2.setText("Articulo Parte Superior Mas Caro a Barato");
				button3.setText("Articulo Parte Inferior Mas Caro a Barato");
				button4.setText("Numero de Clientes");
				button5.setText("Numero de producto de Articulo Superior");
				button6.setText("Numero de producto de Articulo Inferior");
				button7.setText("Cliente hecho el pedido mas caro");
				button8.setText("Buscar nombre y precio por id de articulo Superior");
				button9.setText("Buscar nombre y precio por id de articulo Superior");
				button10.setText("Informacion de un pedido en concreto");
				button11.setText("Volver al Menu");
				//solo para el volcer
				button11.setPreferredSize(new Dimension(200,50));
				//Quitar Border
				button1.setFocusable(false);
				button2.setFocusable(false);
				button3.setFocusable(false);
				button4.setFocusable(false);
				button5.setFocusable(false);
				button6.setFocusable(false);
				button7.setFocusable(false);
				button8.setFocusable(false);
				button9.setFocusable(false);
				button10.setFocusable(false);
				button11.setFocusable(false);
				//añadir al frame
				this.add(button1);
				this.add(button2);
				this.add(button3);
				this.add(button4);
				this.add(button5);
				this.add(button6);
				this.add(button7);
				this.add(button8);
				this.add(button9);
				this.add(button10);
				this.add(button11);
				
				this.setVisible(true);
				//acciones
				button1.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasCliente =  "idCliente"+"\t"+
														"idCliente"+"\t"+
														"Articulo Superior"+"\t"+
														"Articulo Inferior"+"\t"+
														"Cantidad Producto"+"\t"+
														"Precio Total"+"\t"+
														"Fecha Compra";
				   
					JFrame mostrarPedidos = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					mostrarPedidos.setSize(850, 300);
					mostrarPedidos.setTitle("Mostrar Datos");
					mostrarPedidos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					mostrarPedidos.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasCliente); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(todosLosPedidos()); // recibe el string que devuelve el funcion
					
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
							consultaEspeciales consultaEspeciales = new consultaEspeciales();
							mostrarPedidos.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							this.dispose();
							mostrarPedidos.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					mostrarPedidos.setJMenuBar(menuBar);
					mostrarPedidos.add(container);
					mostrarPedidos.setVisible(true);
					});
				
				button2.addActionListener(e->{
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
					mostrarInfo.append(superiorMasCaro()); // recibe el string que devuelve el funcion
					
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
				button3.addActionListener(e->{
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
					mostrarInfo.append(inferiorMasCaro()); // recibe el string que devuelve el funcion
					
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
				button4.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "NUMERO";
				   
					JFrame numeroClientes = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					numeroClientes.setSize(550, 300);
					numeroClientes.setTitle("Mostrar Datos");
					numeroClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					numeroClientes.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(numeroClientes()); // recibe el string que devuelve el funcion
					
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
							numeroClientes.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							numeroClientes.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					numeroClientes.setJMenuBar(menuBar);
					numeroClientes.add(container);
					numeroClientes.setVisible(true);
					
				});
				button5.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "NUMERO";
				   
					JFrame numeroSuperior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					numeroSuperior.setSize(550, 300);
					numeroSuperior.setTitle("Mostrar Datos");
					numeroSuperior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					numeroSuperior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(numeroProductoSuperior()); // recibe el string que devuelve el funcion
					
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
							numeroSuperior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							numeroSuperior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					numeroSuperior.setJMenuBar(menuBar);
					numeroSuperior.add(container);
					numeroSuperior.setVisible(true);
					
				});
				button6.addActionListener(e->{
					this.dispose();
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "NUMERO";
				   
					JFrame numeroInferior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					numeroInferior.setSize(550, 300);
					numeroInferior.setTitle("Mostrar Datos");
					numeroInferior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					numeroInferior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(numeroProductoInferior()); // recibe el string que devuelve el funcion
					
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
							numeroInferior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							numeroInferior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					numeroInferior.setJMenuBar(menuBar);
					numeroInferior.add(container);
					numeroInferior.setVisible(true);
				});
				button7.addActionListener(e->{
					this.dispose();

					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "Nombre"+"\t"+
														"fecha"+"\t"+
														"idPedido"+"\t"+
														"precioTotal";
				   
					JFrame pedidoMasCaro = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					pedidoMasCaro.setSize(550, 300);
					pedidoMasCaro.setTitle("Mostrar Datos");
					pedidoMasCaro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					pedidoMasCaro.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(clientePedidoMasCaro()); // recibe el string que devuelve el funcion
					
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
							pedidoMasCaro.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							pedidoMasCaro.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					pedidoMasCaro.setJMenuBar(menuBar);
					pedidoMasCaro.add(container);
					pedidoMasCaro.setVisible(true);
					
				});
				button8.addActionListener(e->{
					this.dispose();
					try {
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
					}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
						System.out.println(b);
						JOptionPane.showMessageDialog(null, "Mirar consola","Error", JOptionPane.WARNING_MESSAGE);
						
						}
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "Nombre"+"\t"+
														"Precio";
														
				   
					JFrame buscarArticuloSuperior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					buscarArticuloSuperior.setSize(200, 300);
					buscarArticuloSuperior.setTitle("Mostrar Datos");
					buscarArticuloSuperior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					buscarArticuloSuperior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(buscarArticuloSuperior(inputUsuario)); // recibe el string que devuelve el funcion
					
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
							buscarArticuloSuperior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							buscarArticuloSuperior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					buscarArticuloSuperior.setJMenuBar(menuBar);
					buscarArticuloSuperior.add(container);
					buscarArticuloSuperior.setVisible(true);
				});
				button9.addActionListener(e->{
					this.dispose();
					try {
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
					}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
						System.out.println(b);
						JOptionPane.showMessageDialog(null, "Mirar consola","Error", JOptionPane.WARNING_MESSAGE);
						
						}
					//Columnas para mostrar mejor el resultado
					String columnasArticuloInferior =  "Nombre"+"\t"+
														"Precio";
														
				   
					JFrame buscarArticuloInferior = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					buscarArticuloInferior.setSize(200, 300);
					buscarArticuloInferior.setTitle("Mostrar Datos");
					buscarArticuloInferior.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					buscarArticuloInferior.setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasArticuloInferior); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(buscarArticuloInferior(inputUsuario)); // recibe el string que devuelve el funcion
					
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
							buscarArticuloInferior.dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							buscarArticuloInferior.dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					buscarArticuloInferior.setJMenuBar(menuBar);
					buscarArticuloInferior.add(container);
					buscarArticuloInferior.setVisible(true);
				});
				button10.addActionListener(e->{
					this.dispose();
					try {
						inputUsuario =Integer.valueOf( JOptionPane.showInputDialog("ID Articulo: "));//primero se pide el idArticulo
					}catch (InputMismatchException | NumberFormatException | NullPointerException b) {
						System.out.println(b);
						JOptionPane.showMessageDialog(null, "Mirar consola","Error", JOptionPane.WARNING_MESSAGE);
						
						}
					//Columnas para mostrar mejor el resultado
					String columnasCliente =  "idCliente"+"\t"+
														"idCliente"+"\t"+
														"Articulo Superior"+"\t"+
														"Articulo Inferior"+"\t"+
														"Cantidad Producto"+"\t"+
														"Precio Total"+"\t"+
														"Fecha Compra";
				   
					JFrame mostrarUnPedido = new JFrame(); //nuevo JFrame para mostrar resultado
					this.setLayout(new FlowLayout(FlowLayout.LEFT,20,20)); 
					mostrarUnPedido .setSize(850, 300);
					mostrarUnPedido .setTitle("Mostrar Datos");
					mostrarUnPedido .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					mostrarUnPedido .setResizable(false);
					
					infoResulta.setBounds(0, 0, 250, 300);
					infoResulta.append(columnasCliente); // recibe el string que devuelve el funcion
					mostrarInfo.setBounds(0, 0, 250, 300);
					mostrarInfo.append(buscarUnPedido(inputUsuario)); // recibe el string que devuelve el funcion
					
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
							consultaEspeciales consultaEspeciales = new consultaEspeciales();
							mostrarUnPedido .dispose();
						});
						menuItem2.addActionListener(g->{
							jFrameMenu menu = new jFrameMenu();
							this.dispose();
							mostrarUnPedido .dispose();
						});
					
					//añadir el jpane al JFrame y menuBar
					mostrarUnPedido .setJMenuBar(menuBar);
					mostrarUnPedido .add(container);
					mostrarUnPedido .setVisible(true);
				});
				button11.addActionListener(e->{
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
	 public String todosLosPedidos(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM pedidos";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("idPedido") +  "\t" + 
	                                   rs.getInt("idCliente") + "\t" +
	                                   rs.getString("articuloSuperior") + "\t" +
	                                   rs.getString("articuloInferior") + "\t" +
	                                   rs.getInt("cantidadProductoTotal") + "\t" +
	                                   rs.getInt("precioTotal")+  "\t" +
	                                   rs.getString("fechaCompra")+"\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String superiorMasCaro(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM parteSuperior ORDER BY precioSuperior DESC";
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
	                                   rs.getInt("precioSuperior")+"\n");  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String inferiorMasCaro(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM parteInferior ORDER BY precioInferior DESC";
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
	                                   rs.getInt("precioInferior")+"\n");  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String numeroClientes(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT COUNT (*) AS total FROM cliente";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("total"));  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String numeroProductoSuperior(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT COUNT (*) AS total FROM parteSuperior";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	            	resultado+=(rs.getInt("total"));  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String numeroProductoInferior(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT COUNT (*) AS total FROM parteInferior;";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	            	resultado+=(rs.getInt("total"));    
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 
	 public String clientePedidoMasCaro(){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT cliente.nombre, pedidos.fechaCompra, pedidos.idPedido,pedidos.precioTotal FROM pedidos INNER JOIN cliente on pedidos.idCliente = cliente.idCliente ORDER BY pedidos.precioTotal DESC";
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getString("nombre")+  "\t" + 
	                			rs.getString("fechaCompra")+  "\t" + 
	                			rs.getInt("idPedido")+  "\t" + 
	                			rs.getInt("precioTotal")+ "\n");  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String buscarArticuloSuperior(int usuario){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT parteSuperior.nombreProducto, parteSuperior.precioSuperior FROM parteSuperior WHERE parteSuperior.idArticuloSuperior="+usuario;
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getString("nombreProducto")+  "\t" + 
	                			rs.getString("precioSuperior")+ "\n");  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String buscarArticuloInferior(int usuario){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT parteInferior.nombreProducto, parteInferior.precioInferior FROM parteInferior WHERE parteInferior.idArticuloInferior="+usuario;
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getString("nombreProducto")+  "\t" + 
	                			rs.getString("precioInferior")+ "\n");  
	                                  
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
	 public String buscarUnPedido(int usuario){ //mostrar lo que lleva aquel BD
	        String sql = "SELECT * FROM pedidos WHERE idPedido="+usuario;
	        String resultado="";
	        try (Connection conn = this.connect();  //usando el conn del sqlite
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop del resulta a mostrar
	            while (rs.next()) {
	                resultado+=(rs.getInt("idPedido") +  "\t" + 
	                                   rs.getInt("idCliente") + "\t" +
	                                   rs.getString("articuloSuperior") + "\t" +
	                                   rs.getString("articuloInferior") + "\t" +
	                                   rs.getInt("cantidadProductoTotal") + "\t" +
	                                   rs.getInt("precioTotal")+  "\t" +
	                                   rs.getString("fechaCompra")+"\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return resultado; //devuelve el conjunto del loop como un string
	 }
}
