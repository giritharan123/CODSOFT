import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRegistration {
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

    public static void registerStudentForCourse(int studentId, String courseCode) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if the student is already registered for the course
            String checkRegistrationSql = "SELECT * FROM students_courses WHERE student_id = ? AND course_code = ?";
            try (PreparedStatement checkRegistrationStatement = connection.prepareStatement(checkRegistrationSql)) {
                checkRegistrationStatement.setInt(1, studentId);
                checkRegistrationStatement.setString(2, courseCode);
                try (ResultSet resultSet = checkRegistrationStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Student is already registered for this course.");
                        return;
                    }
                }
            }

            // Register the student for the course
            String registerSql = "INSERT INTO students_courses (student_id, course_code) VALUES (?, ?)";
            try (PreparedStatement registerStatement = connection.prepareStatement(registerSql)) {
                registerStatement.setInt(1, studentId);
                registerStatement.setString(2, courseCode);
                int rowsAffected = registerStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student registered successfully!");
                } else {
                    System.out.println("Registration failed. Please check course availability.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        displayAvailableCourses();

        // Assume studentId and courseCode are known
        int studentId = 1;
        String courseCode = "CS101";

        registerStudentForCourse(studentId, courseCode);
        displayAvailableCourses();
    }
}
