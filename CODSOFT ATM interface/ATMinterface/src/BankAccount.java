import java.util.Scanner;

class BankAccount {
    private String accName;
    private int accNo;
    private int accPin;
    private int balance;

    public BankAccount(String accName, int accNo, int accPin, int balance) {
        this.accName = accName;
        this.accNo = accNo;
        this.accPin = accPin;
        this.balance = balance;
    }

    public String getAccName() {
        return accName;
    }

    public int getAccNo() {
        return accNo;
    }

    public int getAccPin() {
        return accPin;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }
}
