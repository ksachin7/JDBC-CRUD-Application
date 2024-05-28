import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {
    private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());

    public static void initializeDatabase() {
        try (Connection connection = DB_Connection.getConnection();
             Statement statement = connection.createStatement()) {

            // Drop table if it exists
            String dropTableSQL = "DROP TABLE IF EXISTS employees";
            statement.executeUpdate(dropTableSQL);

            // Create table
            String createTableSQL = "CREATE TABLE employees ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "email VARCHAR(100) NOT NULL UNIQUE, "
                    + "country VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createTableSQL);

            // Insert initial data if needed
            String insertDataSQL = "INSERT INTO employees (name, email, country) VALUES "
                    + "('John Doe', 'john.doe@example.com', 'USA'), "
                    + "('Jane Smith', 'jane.smith@example.com', 'UK')";
            statement.executeUpdate(insertDataSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, email, country) VALUES (?, ?, ?)";

        try (Connection con = DB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getCountry());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employees= new ArrayList<>();
        try(Connection connection= DB_Connection.getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * from employees")){

            while (resultSet.next()){
                int id= resultSet.getInt("id");
                String name= resultSet.getString("name");
                String email= resultSet.getString("email");
                String country= resultSet.getString("country");

                employees.add(new Employee(id, name, email, country));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employees;
    }
    public void batchInsertEmployees(List<Employee> employees) {
        String sql = "INSERT INTO employees (name, email, country) VALUES (?, ?, ?)";
        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Disable auto-commit for batch processing for Atomicity, Performance improvement, Better error handling.
            connection.setAutoCommit(false);

            for (Employee employee : employees) {
                ps.setString(1, employee.getName());
                ps.setString(2, employee.getEmail());
                ps.setString(3, employee.getCountry());
                ps.addBatch();
            }

            int[] results = ps.executeBatch();
            connection.commit();

            logger.log(Level.INFO, "Batch executed successfully. Inserted {0} records.", results.length);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing batch", e);
            try (Connection connection = DB_Connection.getConnection()) {
                if (connection != null) {
                    connection.rollback();
                    logger.log(Level.INFO, "Transaction rolled back successfully.");
                }
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error rolling back transaction", ex);
            }
        }
    }

    public void updateEmployeeById(int id, String newName, String newEmail, String newCountry) {
        String sql = "UPDATE employees SET name = ?, email = ?, country = ? WHERE id = ?";
        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newCountry);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with ID: " + id);
            } else {
                System.out.println("Employee with ID " + id + " updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmployeeByName(String name, String newName, String newEmail, String newCountry) {
        String sql = "UPDATE employees SET name = ?, email = ?, country = ? WHERE name = ?";
        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newCountry);
            statement.setString(4, name);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with name: " + name);
            } else {
                System.out.println("Employee with name '" + name + "' updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeByEmail(String email, String newName, String newEmail, String newCountry) {
        String sql = "UPDATE employees SET name = ?, email = ?, country = ? WHERE email = ?";
        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newCountry);
            statement.setString(4, email);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with email: " + email);
            } else {
                System.out.println("Employee with email '" + email + "' updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM employees WHERE id=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printEmployee(){
        List<Employee> employees = getAllEmployees();

        // Print table header
        System.out.println("+----+--------------+---------------------------+---------------+");
        System.out.println("| ID | Name         | Email                     | Country       |");
        System.out.println("+----+--------------+---------------------------+---------------+");

        // Print table data
        for (Employee employee : employees) {
            System.out.printf("| %2d | %-12s | %25s | %-13s |\n",
                    employee.getId(), employee.getName(), employee.getEmail(), employee.getCountry());
        }

        // Print table footer
        System.out.println("+----+--------------+---------------------------+---------------+"+"\n");
    }
}

