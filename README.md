# -GESTION-PEQUEÑA-EMPRESA---JAVA- SQLITE - JDBC

EMPRESA - Tienda de Ropas -Machote S.L
SQL - SQLITE
Tablas - 
 <br />
Insertar - Producto - Cliente
Eliminar - Producto - Cliente
Listado - Articulo Parte Superior- Articulo Parte Inferior - Cliente
Modifica - Modificar un producto - Modifica Cliente
 <br />
Consulta -
(10):

1. Todo los pedidos - SELECT * FROM pedidos x

2. Articulo Parte Superior Mas Caro a Barato - SELECT * FROM parteSuperior ORDER BY precioSuperior DESC; x

3. Articulo Parte Inferior Mas Caro a Barato - SELECT * FROM parteInferior ORDER BY precioInferior DESC; x

4. Numero de Clientes - SELECT COUNT (*) AS total FROM cliente; x

5. Numero de producto de Articulo Superior - SELECT COUNT (*) AS total FROM parteSuperior;  x

6. Numero de producto de Articulo Inferior - SELECT COUNT (*) AS total FROM parteInferior;  x

7. Cliente que ha hecho el pedido mas caro - SELECT cliente.nombre, pedidos.fechaCompra, pedidos.idPedido,pedidos.precioTotal FROM pedidos INNER JOIN cliente on pedidos.idCliente = cliente.idCliente ORDER BY pedidos.precioTotal DESC; x

8. Buscar nombre y precio por id de articulo Superior - SELECT parteSuperior.nombreProducto, parteSuperior.precioSuperior FROM parteSuperior WHERE parteSuperior.idArticuloSuperior=("input"); x

9. Buscar nombre y precio por id de articulo Superior - SELECT parteInferior.nombreProducto, parteInferior.precioInferior FROM parteInferior WHERE parteInferior.idArticuloInferior=("input"); x

10.Info de un pedido en concreto - SELECT * FROM pedidos WHERE idPedido = ("input");

 <br />Columnas Producto - Articulo Parte Superior

 <br />idArticuloSuperior INT - primary
 <br />nombreProducto CHAR
 <br />tipoProducto CHAR camisa/jersey/polo
 <br />tallaProducto CHAR s/m/l
 <br />colorProducto CHAR
 <br />cantidadDisponible INT
 <br />precioSuperior INT
 
 <br />Columnas Producto - Articulo Parte Inferior
 
 <br />idArticuloInferior INT - primary
 <br />nombreProducto CHAR
 <br />tipoProducto CHAR - vaquero/shorts/deporte
 <br />tallaProducto CHAR - s/m/l
 <br />colorProducto CHAR
 <br />cantidadDisponible INT
 <br />precioInferior INT

 <br />Columnas Cliente

 <br />idCliente - primary
 <br />nombre CHAR
 <br />email CHAR
 <br />telefono INT
 <br />fechaAlta DATE
 
 <br />Columnas Pedidos

 <br />idPedido - primary
 <br />idCliente - FOREIGN
 <br />articuloSuperior - FOREIGN
 <br />articuloInferior - FOREIGN
 <br />precioTotal
 <br />fechaCompra
