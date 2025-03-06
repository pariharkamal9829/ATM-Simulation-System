import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginScreen extends VBox {
    
    public LoginScreen() {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        
        // Title
        Text title = new Text("ATM Simulation System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Login form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        Label accountLabel = new Label("Account Number:");
        grid.add(accountLabel, 0, 0);
        
        TextField accountField = new TextField();
        accountField.setPromptText("Enter your account number");
        grid.add(accountField, 1, 0);
        
        Label pinLabel = new Label("PIN:");
        grid.add(pinLabel, 0, 1);
        
        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Enter your PIN");
        grid.add(pinField, 1, 1);
        
        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);
        
        loginButton.setOnAction(e -> {
            String accountNumber = accountField.getText();
            String pin = pinField.getText();
            
            if (accountNumber.isEmpty() || pin.isEmpty()) {
                showAlert("Error", "Please enter both account number and PIN.");
                return;
            }
            
            Account account = App.authenticateUser(accountNumber, pin);
            if (account != null) {
                App.showMainMenu();
            } else {
                showAlert("Authentication Failed", "Invalid account number or PIN.");
                pinField.clear();
            }
        });
        
        getChildren().addAll(title, grid, loginButton);
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

