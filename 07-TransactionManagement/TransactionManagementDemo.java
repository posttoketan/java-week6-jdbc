import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManagementDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        String sql = "INSERT INTO employees(name, department, salary) "
                   + "VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url)) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, "Rahul Kumar");
                statement.setString(2, "Development");
                statement.setDouble(3, 30000);
                statement.executeUpdate();

                statement.setString(1, "Priya Singh");
                statement.setString(2, "Testing");
                statement.setDouble(3, 32000);
                statement.executeUpdate();

                connection.commit();

                System.out.println("Transaction committed successfully!");

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("Transaction rolled back.");
                System.out.println("Error: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
