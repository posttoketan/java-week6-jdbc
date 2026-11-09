import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeCRUDApplication {

    static final String URL = "jdbc:sqlite:enterprise_erp.db";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL)) {

            createTable(connection);
            insertEmployee(connection);
            displayEmployees(connection);
            updateSalary(connection);
            displayEmployees(connection);
            deleteEmployee(connection);
            displayEmployees(connection);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    static void createTable(Connection connection)
            throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS employee_crud ("
                + "id INTEGER PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "department TEXT, "
                + "salary REAL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Employee table created.");
        }
    }

    static void insertEmployee(Connection connection)
            throws SQLException {

        String sql = "INSERT OR REPLACE INTO employee_crud "
                + "(id, name, department, salary) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, 1);
            statement.setString(2, "Aarav Mehta");
            statement.setString(3, "Development");
            statement.setDouble(4, 35000);
            statement.executeUpdate();

            statement.setInt(1, 2);
            statement.setString(2, "Meera Sharma");
            statement.setString(3, "Testing");
            statement.setDouble(4, 40000);
            statement.executeUpdate();

            System.out.println("Employee records inserted.");
        }
    }

    static void displayEmployees(Connection connection)
            throws SQLException {

        String sql = "SELECT * FROM employee_crud";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            System.out.println("\nEmployee Records:");

            while (result.next()) {
                System.out.println(
                        result.getInt("id") + " | "
                        + result.getString("name") + " | "
                        + result.getString("department") + " | "
                        + result.getDouble("salary")
                );
            }
        }
    }

    static void updateSalary(Connection connection)
            throws SQLException {

        String sql = "UPDATE employee_crud SET salary = ? WHERE id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, 45000);
            statement.setInt(2, 1);

            statement.executeUpdate();

            System.out.println("\nEmployee salary updated.");
        }
    }

    static void deleteEmployee(Connection connection)
            throws SQLException {

        String sql = "DELETE FROM employee_crud WHERE id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, 2);
            statement.executeUpdate();

            System.out.println("\nEmployee record deleted.");
        }
    }
}
