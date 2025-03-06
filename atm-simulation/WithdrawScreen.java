import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.NumberFormat;

public class WithdrawScreen extends BorderPane {
    
    public WithdrawScreen() {
        setPadding(new Insets(20));
        
        // Header
        Label titleLabel = new Label("Withdraw Cash");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Account information
        Account account = App.getCurrentAccount();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        
        Label balanceLabel = new Label("Available Balance: " + currencyFormat.format(account.getBalance()));
        balanceLabel.setFont(Font.font("Arial", 14));
        
        // Quick withdrawal options
        Label quickLabel = new Label("Quick Withdrawal:");
        quickLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        HBox quickOptions = new HBox(10);
        quickOptions.setAlignment(Pos.CENTER);
        
        int[] amounts = {20, 50, 100, 200};
        for (int amount : amounts) {
            Button amountBtn = new Button("$" + amount);
            amountBtn.setPrefWidth(80);
            final int withdrawAmount = amount;
            amountBtn.setOnAction(e -> processWithdrawal(withdrawAmount));
            quickOptions.getChildren().add(amountBtn);
        }
        
        // Custom amount
        Label customLabel = new Label("Custom Amount:");
        customLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setPrefWidth(150);
        
        Button withdrawBtn = new Button("Withdraw");
        withdrawBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                processWithdrawal(amount);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid amount.");
            }
        });
        
        HBox customBox = new HBox(10, amountField, withdrawBtn);
        customBox.setAlignment(Pos.CENTER);
        
        // Back button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> App.showMainMenu());
        
        VBox mainBox = new VBox(15, 
            titleLabel, 
            balanceLabel, 
            new Label(""), // Spacer
            quickLabel, 
            quickOptions, 
            new Label(""), // Spacer
            customLabel, 
            customBox, 
            new Label(""), // Spacer
            backButton
        );
        mainBox.setAlignment(Pos.CENTER);
        
        setCenter(mainBox);
    }
    
    private void processWithdrawal(double amount) {
        Account account = App.getCurrentAccount();
        
        if (amount <= 0) {
            showAlert("Error", "Please enter a positive amount.");
            return;
        }
        
        if (account.withdraw(amount)) {
            showSuccessAlert("Withdrawal Successful", 
                    String.format("You have withdrawn $%.2f.\nRemaining balance: $%.2f", 
                            amount, account.getBalance()));
            App.showMainMenu();
        } else {
            showAlert("Withdrawal Failed", "Insufficient funds or invalid amount.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

