import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private static List<Account> accounts;
    private static Stage primaryStage;
    private static Account currentAccount;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("ATM Simulation System");
        
        // Initialize sample accounts
        initializeAccounts();
        
        // Show login screen
        showLoginScreen();
        
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeAccounts() {
        accounts = new ArrayList<>();
        // Add some sample accounts
        accounts.add(new Account("123456789", "1234", 1000.0, "John Doe"));
        accounts.add(new Account("987654321", "4321", 2500.0, "Jane Smith"));
        accounts.add(new Account("111222333", "5678", 500.0, "Bob Johnson"));
    }

    public static void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen();
        Scene scene = new Scene(loginScreen, 400, 300);
        primaryStage.setScene(scene);
    }

    public static void showMainMenu() {
        MainMenuScreen mainMenu = new MainMenuScreen();
        Scene scene = new Scene(mainMenu, 600, 400);
        primaryStage.setScene(scene);
    }

    public static void showBalanceScreen() {
        BalanceScreen balanceScreen = new BalanceScreen();
        Scene scene = new Scene(balanceScreen, 600, 400);
        primaryStage.setScene(scene);
    }

    public static void showWithdrawScreen() {
        WithdrawScreen withdrawScreen = new WithdrawScreen();
        Scene scene = new Scene(withdrawScreen, 600, 400);
        primaryStage.setScene(scene);
    }

    public static void showDepositScreen() {
        DepositScreen depositScreen = new DepositScreen();
        Scene scene = new Scene(depositScreen, 600, 400);
        primaryStage.setScene(scene);
    }

    public static void showTransactionHistoryScreen() {
        TransactionHistoryScreen historyScreen = new TransactionHistoryScreen();
        Scene scene = new Scene(historyScreen, 600, 400);
        primaryStage.setScene(scene);
    }

    public static Account authenticateUser(String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.validatePin(pin)) {
                currentAccount = account;
                return account;
            }
        }
        return null;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

