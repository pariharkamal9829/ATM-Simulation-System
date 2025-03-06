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

public class DepositScreen extends BorderPane {
    
    public DepositScreen() {
        setPadding(new Insets(20));
        
        // Header
        Label titleLabel = new Label("Deposit Cash");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Account information
        Account account = App.getCurrentAccount();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        
        Label balanceLabel = new Label("Current Balance: " + currencyFormat.format(account.getBalance()));
        balanceLabel.setFont(Font.font("Arial", 14));
        
        // Deposit amount
        Label amountLabel = new Label("Enter Deposit Amount:");
        amountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setPrefWidth(150);
        
        Button depositBtn = new Button("Deposit");
        depositBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                processDeposit(amount);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid amount.");
            }
        });
        
        HBox amountBox = new HBox(10, amountField, depositBtn);
        amountBox.setAlignment(Pos.CENTER);
        
        // Quick deposit options
        Label quickLabel = new Label("Quick Deposit:");
        quickLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        HBox quickOptions = new HBox(10);
        quickOptions.setAlignment(Pos.CENTER);
        
        int[] amounts = {50, 100, 200, 500};
        for (int amount : amounts) {
            Button amountBtn = new Button("$" + amount);
            amountBtn.setPrefWidth(80);
            final int depositAmount = amount;
            amountBtn.setOnAction(e -> processDeposit(depositAmount));
            quickOptions.getChildren().add(amountBtn);
        }
        
        // Back button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> App.showMainMenu());
        
        VBox mainBox = new VBox(15, 
            titleLabel, 
            balanceLabel, 
            new Label(""), // Spacer
            amountLabel, 
            amountBox, 
            new Label(""), // Spacer
            quickLabel, 
            quickOptions, 
            new Label(""), // Spacer
            backButton
        );
        mainBox.setAlignment(Pos.CENTER);
        
        setCenter(mainBox);
    }
    
    private void processDeposit(double amount) {
        Account account = App.getCurrentAccount();
        
        if (amount <= 0) {
            showAlert("Error", "Please enter a positive amount.");
            return;
        }
        
        account.deposit(amount);
        showSuccessAlert("Deposit Successful", 
                String.format("You have deposited $%.2f.\nNew balance: $%.2f", 
                        amount, account.getBalance()));
        App.showMainMenu();
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

