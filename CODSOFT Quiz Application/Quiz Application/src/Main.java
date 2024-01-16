import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static class QuizQuestion {
        String question;
        List<String> options;
        String correctAnswer;

        public QuizQuestion(String question, List<String> options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private int score;
    private final int timerDuration = 10;
    private List<QuizQuestion> questions;

    public Main() {
        this.score = 0;
        this.questions = new ArrayList<>();

        // Add quiz questions
        questions.add(new QuizQuestion("Which city is home to the Indian Institute of Technology (IIT) Madras?", List.of("A. coimbatore", "B. chennai", "C. madurai", "D. Tripur"), "B. chennai"));
        questions.add(new QuizQuestion("Which university in Tamil Nadu is known for its expertise in agricultural sciences?", List.of("A.  Tamil Nadu Agricultural University (TNAU)", "B.  Alagappa University", "C. Periyar University", "D. Periyar University"), "C.  periyar University"));
    }

    public void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
    }

    public boolean checkAnswer(QuizQuestion question, String userAnswer) {
        return userAnswer.equalsIgnoreCase(question.correctAnswer);
    }

    public void runQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            displayQuestion(question);

            // Set up timer
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            Runnable task = () -> {
                System.out.println("\nTime's up! Moving to the next question.\n");
                executorService.shutdownNow();
            };
            executorService.schedule(task, timerDuration, TimeUnit.SECONDS);

            // Get user input
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();

            // Cancel the timer
            executorService.shutdownNow();

            // Check user's answer
            checkAnswer(question, userAnswer);
        }

        displayResult();
        scanner.close();
    }

    public void displayResult() {
        System.out.println("\nQuiz completed!");

        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            String correctOrIncorrect = checkAnswer(question, question.correctAnswer) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + correctOrIncorrect);
        }
        System.out.println("\nThank you for playing!");
    }

    public static void main(String[] args) {
        Main quiz = new Main();
        quiz.runQuiz();
    }
}
