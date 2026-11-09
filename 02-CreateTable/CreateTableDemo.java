import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateTableDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        String sql = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "department TEXT, "
                + "salary REAL)";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

            System.out.println("Employee table created successfully!");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
