import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount[] accounts = {
                new BankAccount("Alice Wonderland", 12345678, 1111, 5000),
                new BankAccount("Bob Marley", 87654321, 2222, 10000),
                new BankAccount("Charlie Chaplin", 98765432, 3333, 20000)
        };

        int accountIndex = -1;

        while (true) {
            if (accountIndex == -1) {
                System.out.print("ENTER ACCOUNT NO: ");
                int accountNumber = scanner.nextInt();

                System.out.print("ENTER PIN NO: ");
                int pin = scanner.nextInt();

                accountIndex = authenticate(accounts, accountNumber, pin);

                if (accountIndex == -1) {
                    System.out.println("INVALID ACCOUNT NUMBER OR PASSWORD");
                    continue;
                }
            }

            System.out.println("SELECT ONE OPTION BELOW");
            System.out.println("1. WITHDRAW");
            System.out.println("2. DEPOSIT");
            System.out.println("3. CHECK BALANCE");
            System.out.println("4. EXIT");
            System.out.print("ENTER OPTION: ");
            int option = scanner.nextInt();

            if (option == 4) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            System.out.print("ENTER AMOUNT: ");
            int amount = scanner.nextInt();

            ATM atm = new ATM(accounts[accountIndex]);
            atm.performTransaction(option, amount);
        }
    }

    public static int authenticate(BankAccount[] accounts, int accountNumber, int pin) {
        for (int i = 0; i < accounts.length; i++) {
            if (accountNumber == accounts[i].getAccNo() && pin == accounts[i].getAccPin()) {
                return i;
            }
        }
        return -1;
    }
}