import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numOfSubjects = scanner.nextInt();

        // Input: Take marks obtained in each subject
        int totalMarks = 0;
        for (int i = 1; i <= numOfSubjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int subjectMarks = scanner.nextInt();

            // Validate input marks
            if (subjectMarks < 0 || subjectMarks > 100) {
                System.out.println("Invalid marks entered. Marks should be between 0 and 100.");
                i--;  // Re-enter marks for the current subject
                continue;
            }

            totalMarks += subjectMarks;
        }

        // Calculate Total Marks
        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numOfSubjects;

        // Grade Calculation: Assign grades based on the average percentage achieved
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display Results: Show the total marks, average percentage, and the corresponding grade
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
