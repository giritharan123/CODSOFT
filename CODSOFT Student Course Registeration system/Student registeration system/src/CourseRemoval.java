import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseRemoval {
    public static void dropCourseForStudent(int studentId, String courseCode) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if the student is registered for the course
            String checkRegistrationSql = "SELECT * FROM students_courses WHERE student_id = ? AND course_code = ?";
            try (PreparedStatement checkRegistrationStatement = connection.prepareStatement(checkRegistrationSql)) {
                checkRegistrationStatement.setInt(1, studentId);
                checkRegistrationStatement.setString(2, courseCode);
                try (var resultSet = checkRegistrationStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        System.out.println("Student is not registered for this course.");
                        return;
                    }
                }
            }

            // Drop the course for the student
            String dropCourseSql = "DELETE FROM students_courses WHERE student_id = ? AND course_code = ?";
            try (PreparedStatement dropCourseStatement = connection.prepareStatement(dropCourseSql)) {
                dropCourseStatement.setInt(1, studentId);
                dropCourseStatement.setString(2, courseCode);
                int rowsAffected = dropCourseStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Course dropped successfully!");
                } else {
                    System.out.println("Invalid student ID or course code. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Assume studentId and courseCode are known
        int studentId = 1;
        String courseCode = "CS101";

        dropCourseForStudent(studentId, courseCode);
    }
}
