import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecordDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        String sql = "DELETE FROM employees WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "Sneha Sharma");

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee record deleted successfully!");
            } else {
                System.out.println("Employee record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}
