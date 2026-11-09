import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveRecordDemo {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:enterprise_erp.db";

        String sql = "SELECT * FROM employees";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            System.out.println("Employee Records:");

            while (result.next()) {

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Department: " + result.getString("department"));
                System.out.println("Salary: " + result.getDouble("salary"));
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
    }
}
