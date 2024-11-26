package dao;

import database.DBCon;
import database.SchemaDB;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void crearPedido(Order order)throws SQLException {
        connection = new DBCon().getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUE (?,?,?)",
                SchemaDB.TABLE_ORDERS,
                SchemaDB.ORDERS_IDPRODUCT,
                SchemaDB.ORDERS_DESCRIPTION,
                SchemaDB.ORDERS_TOTALPRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,order.getProductId());
        preparedStatement.setString(2,order.getDescription());
        preparedStatement.setDouble(3,order.getTotalPrice());
        preparedStatement.execute();
    }

    public ArrayList<Order> selectAll() throws SQLException{
        connection = new DBCon().getConnection();
        ArrayList<Order> resultados = new ArrayList<Order>();
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s", SchemaDB.TABLE_ORDERS));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Order order = new Order();
            order.setId(resultSet.getInt(SchemaDB.ORDERS_ID));
            order.setProductId(resultSet.getInt(SchemaDB.ORDERS_IDPRODUCT));
            order.setDescription(resultSet.getString(SchemaDB.ORDERS_DESCRIPTION));
            order.setTotalPrice(resultSet.getDouble(SchemaDB.ORDERS_TOTALPRICE));
            resultados.add(order);
        }
        return resultados;
    }
}
