package dao;

import database.DBCon;
import database.SchemaDB;
import model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    //Crud de la clase Product
    //Lógica de la Base de datos

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void subirProductos(Product product) throws SQLException {
        connection = new DBCon().getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUE (?,?,?,?)",
                SchemaDB.TABLE_PRODUCTS,
                SchemaDB.PRODUCTS_TITLE,
                SchemaDB.PRODUCTS_DESCRIPTION,
                SchemaDB.PRODUCTS_STOCK,
                SchemaDB.PRODUCTS_PRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setInt(3, product.getStock());
        preparedStatement.setDouble(4, product.getPrice());
        preparedStatement.execute();
        //connection.close();
    }
    public ArrayList<Product> buscarProductosPorPrecio(Double filterPrice) throws SQLException{
        connection = new DBCon().getConnection();
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s > ?",
                SchemaDB.TABLE_PRODUCTS,
                SchemaDB.PRODUCTS_PRICE));
        preparedStatement.setDouble(1, filterPrice);
        resultSet = preparedStatement.executeQuery();

        //Guardamos los metadatos de Resultset, para sacar el numero de registros encontrados.
        //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        //int nRegistros = resultSetMetaData.getColumnCount();
        ArrayList<Product> resultados = new ArrayList<Product>();

        //Usamos el numero de registros para iterar sobre el resultset y guardar la información en el arraylist que retornamos
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt(SchemaDB.PRODUCTS_ID));
            product.setTitle(resultSet.getString(SchemaDB.PRODUCTS_TITLE));
            product.setDescription(resultSet.getString(SchemaDB.PRODUCTS_DESCRIPTION));
            product.setStock(resultSet.getInt(SchemaDB.PRODUCTS_STOCK));
            product.setPrice(resultSet.getDouble(SchemaDB.PRODUCTS_PRICE));
            resultados.add(product);
            // Esto no me acaba de mapear el objeto correctamente.
            // Se podría hacer con un rowmapper.
            //int i = 1;
            //while(i < nRegistros){
            //    resultados.add(resultSet.getObject(i++, Product.class));
            //}
        }
        return resultados;
    }

    public void guardarFav(Product product)throws SQLException{
        connection = new DBCon().getConnection();
        String queryExist = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?",
                SchemaDB.TABLE_PRODUCTFAV,
                SchemaDB.PRODUCTS_ID);
        String queryInsert = String.format("INSERT INTO %s (%s) VALUES (?)",
                SchemaDB.TABLE_PRODUCTFAV,
                SchemaDB.PRODUCTFAV_IDPRODUCT);

        preparedStatement = connection.prepareStatement(queryExist);
        preparedStatement.setInt(1, product.getId());

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next() && resultSet.getInt(1) > 0){
            System.out.println("El producto que has intentado añadir, ya está guardado en favoritos");
        } else {
            preparedStatement = connection.prepareStatement(queryInsert);
            preparedStatement.setInt(1, product.getId());

            preparedStatement.executeUpdate();
            System.out.println("Producto guardado con éxito");
        }
    }

    public ArrayList<Product> selectAll() throws SQLException{
        connection = new DBCon().getConnection();
        ArrayList<Product> resultados = new ArrayList<Product>();
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s", SchemaDB.TABLE_PRODUCTS));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt(SchemaDB.PRODUCTS_ID));
            product.setTitle(resultSet.getString(SchemaDB.PRODUCTS_TITLE));
            product.setDescription(resultSet.getString(SchemaDB.PRODUCTS_DESCRIPTION));
            product.setStock(resultSet.getInt(SchemaDB.PRODUCTS_STOCK));
            product.setPrice(resultSet.getDouble(SchemaDB.PRODUCTS_PRICE));
            resultados.add(product);
        }
        return resultados;
    }
}
