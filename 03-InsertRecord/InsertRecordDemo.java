import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRecordDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        String sql = "INSERT INTO employees(name, department, salary) "
                   + "VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "Ketan Das");
            statement.setString(2, "Java Development");
            statement.setDouble(3, 35000);

            statement.executeUpdate();

            statement.setString(1, "Sneha Sharma");
            statement.setString(2, "IT Support");
            statement.setDouble(3, 40000);

            statement.executeUpdate();

            System.out.println("Employee records inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting records: " + e.getMessage());
        }
    }
}
