import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseListing {
    public static void displayAvailableCourses() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM courses";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Course Code: " + resultSet.getString("course_code"));
                    System.out.println("Title: " + resultSet.getString("title"));
                    System.out.println("Description: " + resultSet.getString("description"));
                    System.out.println("Capacity: " + resultSet.getInt("capacity"));
                    System.out.println("Schedule: " + resultSet.getString("schedule"));
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        displayAvailableCourses();
    }
}
