import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.NumberFormat;
import java.util.Date;

public class BalanceScreen extends BorderPane {
    
    public BalanceScreen() {
        setPadding(new Insets(20));
        
        // Header
        Label titleLabel = new Label("Balance Inquiry");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Balance information
        Account account = App.getCurrentAccount();
        
        // Add a balance inquiry transaction to history
        account.getTransactionHistory().add(new Transaction(TransactionType.BALANCE_INQUIRY, 0, new Date()));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        
        Label accountLabel = new Label("Account: " + account.getAccountNumber());
        accountLabel.setFont(Font.font("Arial", 14));
        
        Label balanceLabel = new Label("Current Balance: " + currencyFormat.format(account.getBalance()));
        balanceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        VBox balanceBox = new VBox(15, titleLabel, accountLabel, balanceLabel);
        balanceBox.setAlignment(Pos.CENTER);
        
        // Back button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> App.showMainMenu());
        
        VBox mainBox = new VBox(30, balanceBox, backButton);
        mainBox.setAlignment(Pos.CENTER);
        
        setCenter(mainBox);
    }
}

