import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        try (Connection connection =
                     DriverManager.getConnection(url)) {

            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            System.out.println("Database connection failed: "
                    + e.getMessage());
        }
    }
}
