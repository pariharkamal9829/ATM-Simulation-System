import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.List;

public class TransactionHistoryScreen extends BorderPane {
    
    public TransactionHistoryScreen() {
        setPadding(new Insets(20));
        
        // Header
        Label titleLabel = new Label("Transaction History");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Account information
        Account account = App.getCurrentAccount();
        
        Label accountLabel = new Label("Account: " + account.getAccountNumber());
        accountLabel.setFont(Font.font("Arial", 14));
        
        // Transaction list
        List<Transaction> transactions = account.getTransactionHistory();
        
        ListView<String> transactionListView = new ListView<>();
        
        if (transactions.isEmpty()) {
            transactionListView.setItems(FXCollections.observableArrayList("No transactions found"));
        } else {
            String[] transactionStrings = transactions.stream()
                    .map(Transaction::toString)
                    .toArray(String[]::new);
            
            transactionListView.setItems(FXCollections.observableArrayList(transactionStrings));
        }
        
        transactionListView.setPrefHeight(200);
        
        // Back button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> App.showMainMenu());
        
        VBox mainBox = new VBox(15, titleLabel, accountLabel, transactionListView, backButton);
        mainBox.setAlignment(Pos.CENTER);
        
        setCenter(mainBox);
    }
}

