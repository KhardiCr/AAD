package dao;

import database.DBCon;
import database.SchemaDB;
import model.Employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
    //Lógica de la Base de datos

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void altaEmpleado(Employee empleado)throws SQLException {
        connection = new DBCon().getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUE (?,?,?)",
                SchemaDB.TABLE_EMPLOYEES,
                SchemaDB.EMPLOYEES_NAME,
                SchemaDB.EMPLOYEES_LASTNAME,
                SchemaDB.EMPLOYEES_MAIL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, empleado.getName());
        preparedStatement.setString(2, empleado.getLastName());
        preparedStatement.setString(3, empleado.getEmail());
        preparedStatement.execute();
    }
    public ArrayList<Employee> selectAll() throws SQLException{
        connection = new DBCon().getConnection();
        ArrayList<Employee> resultados = new ArrayList<Employee>();
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s", SchemaDB.TABLE_EMPLOYEES));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getInt(SchemaDB.EMPLOYEES_ID));
            employee.setName(resultSet.getString(SchemaDB.EMPLOYEES_NAME));
            employee.setLastName(resultSet.getString(SchemaDB.EMPLOYEES_LASTNAME));
            employee.setEmail(resultSet.getString(SchemaDB.EMPLOYEES_MAIL));
            resultados.add(employee);
        }
        return resultados;
    }
}
