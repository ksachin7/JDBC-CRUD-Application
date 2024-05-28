import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/JDBC_CRUD";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqls01)";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

}
