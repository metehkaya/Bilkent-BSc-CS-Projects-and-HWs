package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterCustomerPanel {
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnButton;

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleReturnButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}