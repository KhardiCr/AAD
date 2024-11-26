import com.google.gson.Gson;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Employee;
import model.Order;
import model.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Entrada {

    public static void main(String[] args) {
        //Lógica de negocio
        ArrayList<Product> productsArray = new ArrayList<Product>();
        try {
            URL urlString = new URL("https://dummyjson.com/products");
            productsArray = extractor(urlString);
        } catch (MalformedURLException e) {
            System.out.println("La url está mal formada");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            productDigest(productsArray);
            filtroPrecio(600.00);
            agregarEmpleados();
            agregarPedidos();
        }catch (SQLException e){
            System.out.println("Hay un problema con la base de datos");
            e.printStackTrace();
        }

        try {
            recoveryAllData();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> extractor(URL urlString)throws IOException{

        ArrayList<Product> productsArray = new ArrayList<Product>();
        HttpURLConnection connection = (HttpURLConnection) urlString.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String linea = br.readLine();
        JSONObject response = new JSONObject(linea);
        JSONArray results = response.getJSONArray("products");

        for(Object product : results){
            if(product instanceof JSONObject) {
                Gson gson = new Gson();
                productsArray.add(gson.fromJson(product.toString(), Product.class));
            }
        }
        return productsArray;
    }

    public static void productDigest(ArrayList<Product> productArray)throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        for (Product product: productArray){
            productDAO.subirProductos(product);
        }
    }

    public static void filtroPrecio(Double price)throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        mostrarFiltro(productDAO.buscarProductosPorPrecio(price));
    }

    private static void mostrarFiltro(ArrayList<Product> productosFiltrados)throws SQLException{
        for (Product product : productosFiltrados){
            System.out.println("Este producto vale mas mas de 600€ ");
            System.out.println(product.toString());
            if (product.getPrice() >= 1000.00){
                actualizarProductosFav(product);
            }
        }
    }

    private static void agregarEmpleados()throws SQLException{
        Employee empleado1 = new Employee("Pepe","Gotera","pegote@test.com");
        Employee empleado2 = new Employee("Armando","Paredes","apares@test.com");
        Employee empleado3 = new Employee("Lola","Mento","mucho@test.com");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.altaEmpleado(empleado1);
        employeeDAO.altaEmpleado(empleado2);
        employeeDAO.altaEmpleado(empleado3);
    }

    private static void agregarPedidos()throws SQLException{
        Order order1 = new Order(5,"producto1",9.99);
        Order order2 = new Order(10,"producto2",5.99);
        Order order3 = new Order(15,"producto3", 20.99);
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.crearPedido(order1);
        orderDAO.crearPedido(order2);
        orderDAO.crearPedido(order3);
    }

    private static void actualizarProductosFav(Product product) throws SQLException{
        ProductDAO productDAO = new ProductDAO();
        productDAO.guardarFav(product);
    }

    private static void recoveryAllData() throws SQLException{
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> allProducts = productDAO.selectAll();

        for (Product product : allProducts ){
            System.out.println(product.toString());
        }

        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> allOrders = orderDAO.selectAll();

        for (Order order : allOrders){
            System.out.println(order.toString());
        }

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Employee> allEmployees = employeeDAO.selectAll();

        for (Employee employee : allEmployees){
            System.out.println(employee.toString());
        }




    }
}
