package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPanel {
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginFacebookButton;

    @FXML
    private Button registerCustomerButton;

    @FXML
    private Button registerProviderButton;

    @FXML
    private Label titleLabel;

    public void initialize() {
    	if(MainGUI.input == 1) { // feedme
    		titleLabel.setText("Welcome to FeedMe");
    	}
    	else if(MainGUI.input == 2) { // maturepear
    		titleLabel.setText("Welcome to MaturePear");
    	}
    	else if(MainGUI.input == 3){ // healthy life
    		titleLabel.setText("Welcome to HealthyLife");
    	}
    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleLoginFacebookButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleRegisterCustomerButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registercustomer_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleRegisterProviderButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerprovider_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}