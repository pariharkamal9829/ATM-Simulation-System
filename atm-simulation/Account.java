import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private String accountHolder;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, String pin, double balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount, new Date()));
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount, new Date()));
        }
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}

