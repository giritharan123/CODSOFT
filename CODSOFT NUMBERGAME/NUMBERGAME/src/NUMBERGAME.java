
import java.util.Random;
import java.util.Scanner;

public class NUMBERGAME{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int totalAttempts = 0;
        int bestScore = Integer.MAX_VALUE;

        while (true) {
            System.out.println("Round " + (roundsPlayed + 1));

            System.out.print("Enter the lower limit for the number range: ");
            int lowerLimit = scanner.nextInt();

            System.out.print("Enter the upper limit for the number range: ");
            int upperLimit = scanner.nextInt();

            int secretNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int maxAttempts = Math.max(1, Math.min(upperLimit - lowerLimit + 1, 10));

            System.out.println("Guess the number between " + lowerLimit + " and " + upperLimit);

            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + secretNumber + " in " + attempts + " attempts!");
                    totalAttempts += attempts;
                    bestScore = Math.min(bestScore, attempts);
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you ran out of attempts. The correct number was " + secretNumber + ".");
                }
            }

            roundsPlayed++;

            System.out.println("Best score so far: " + (bestScore == Integer.MAX_VALUE ? "N/A" : bestScore) + " attempts");

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Game over! You played " + roundsPlayed + " round(s) and your total score is " + totalAttempts);
                break;
            }
        }
    }
}
