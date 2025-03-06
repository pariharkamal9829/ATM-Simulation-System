import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenuScreen extends BorderPane {
    
    public MainMenuScreen() {
        setPadding(new Insets(20));
        
        // Header
        Label welcomeLabel = new Label("Welcome, " + App.getCurrentAccount().getAccountHolder());
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Label accountLabel = new Label("Account: " + App.getCurrentAccount().getAccountNumber());
        accountLabel.setFont(Font.font("Arial", 14));
        
        VBox headerBox = new VBox(10, welcomeLabel, accountLabel);
        headerBox.setAlignment(Pos.CENTER);
        setTop(headerBox);
        BorderPane.setMargin(headerBox, new Insets(0, 0, 20, 0));
        
        // Menu options
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        
        Button checkBalanceBtn = new Button("Check Balance");
        checkBalanceBtn.setPrefWidth(200);
        checkBalanceBtn.setOnAction(e -> App.showBalanceScreen());
        
        Button withdrawBtn = new Button("Withdraw Cash");
        withdrawBtn.setPrefWidth(200);
        withdrawBtn.setOnAction(e -> App.showWithdrawScreen());
        
        Button depositBtn = new Button("Deposit Cash");
        depositBtn.setPrefWidth(200);
        depositBtn.setOnAction(e -> App.showDepositScreen());
        
        Button historyBtn = new Button("Transaction History");
        historyBtn.setPrefWidth(200);
        historyBtn.setOnAction(e -> App.showTransactionHistoryScreen());
        
        Button exitBtn = new Button("Exit");
        exitBtn.setPrefWidth(200);
        exitBtn.setOnAction(e -> App.showLoginScreen());
        
        menuBox.getChildren().addAll(
            checkBalanceBtn, 
            withdrawBtn, 
            depositBtn, 
            historyBtn, 
            exitBtn
        );
        
        setCenter(menuBox);
    }
}

