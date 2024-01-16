import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display available courses
        CourseListing.displayAvailableCourses();

        // Assume studentId is known
        int studentId = 1;

        // Register for a course
        System.out.print("Enter the course code to register (e.g., CS101): ");
        String registerCourseCode = scanner.next();
        StudentRegistration.registerStudentForCourse(studentId, registerCourseCode);

        // Display available courses after registration
        System.out.println("\nAvailable Courses After Registration:");
        CourseListing.displayAvailableCourses();

        // Drop a registered course
        System.out.print("Enter the course code to drop (e.g., CS101): ");
        String dropCourseCode = scanner.next();
        CourseRemoval.dropCourseForStudent(studentId, dropCourseCode);

        // Display available courses after dropping
        System.out.println("\nAvailable Courses After Dropping:");
        CourseListing.displayAvailableCourses();

        // Close the scanner
        scanner.close();
    }
}
