import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize the database (drop and recreate table)
        EmployeeDAO employeeDAO;
        EmployeeDAO.initializeDatabase();

        employeeDAO = new EmployeeDAO();

        // Test the CRUD operations
        Employee employee1 = new Employee(0, "John", "abc@gmail.com", "USA");
        Employee employee2 = new Employee(1, "John D.", "emp@gmail.com", "London");

        employeeDAO.insertEmployee(employee1);
        employeeDAO.insertEmployee(employee2);
        System.out.println(employeeDAO.getAllEmployees());
        System.out.println();

        // Update employee
        employeeDAO.updateEmployeeById(3, "John Snow", "emp.updated@gmail.com", "London");
        System.out.println("After update:");
        employeeDAO.printEmployee();
        // Update employee details by name
        employeeDAO.updateEmployeeByName("Jane Smith", "Jane Doe", "jane.doe@gmail.com", "New York");
        employeeDAO.printEmployee();

        // Update employee details by email
        employeeDAO.updateEmployeeByEmail("emp@gmail.com", "Jane Doe", "emp.new@gmail.com", "New York");
        employeeDAO.printEmployee();

        // Delete employee
        employeeDAO.deleteEmployee(4);

        // Print table after delete
        System.out.println("After delete:");
        employeeDAO.printEmployee();

        // Create a list of employees to batch insert
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "Emily Davis", "emily@example.com", "Australia"));
        employees.add(new Employee(0, "David Brown", "david@example.com", "Germany"));
        employees.add(new Employee(0, "Laura Wilson", "laura@example.com", "France"));
        employees.add(new Employee(0, "Mike Johnson", "mike@example.com", "Canada"));

        // Perform batch insertion
        employeeDAO.batchInsertEmployees(employees);
        employeeDAO.printEmployee();
    }
}