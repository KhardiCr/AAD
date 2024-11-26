package database;

public interface SchemaDB {
    String HOST = "127.0.0.1";
    String PORT = "3306";
    String DATABASE = "almacen";
    String TABLE_PRODUCTS = "Productos";
    String PRODUCTS_ID = "id";
    String PRODUCTS_TITLE = "nombre";
    String PRODUCTS_DESCRIPTION = "descripcion";
    String PRODUCTS_STOCK = "cantidad";
    String PRODUCTS_PRICE = "precio";

    String TABLE_PRODUCTFAV = "Productos_Fav";
    //No necesaria, es autoincremental
    //String PRODUCTFAV_ID = "id";
    String PRODUCTFAV_IDPRODUCT = "id_producto";

    String TABLE_EMPLOYEES = "Empleados";
    String EMPLOYEES_ID = "id";
    String EMPLOYEES_NAME = "nombre";
    String EMPLOYEES_LASTNAME = "apellido";
    String EMPLOYEES_MAIL = "correo";

    String TABLE_ORDERS = "Pedidos";
    String ORDERS_ID = "id";
    String ORDERS_IDPRODUCT = "id_producto";
    String ORDERS_DESCRIPTION = "descripcion";
    String ORDERS_TOTALPRICE = "precio_total";
}
