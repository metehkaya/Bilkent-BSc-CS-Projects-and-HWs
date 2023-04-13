package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreditCardPanel {
    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField nameCardHolder;

    @FXML
    private TextField securityCodeField;

    @FXML
    private TextField expireDateField;

    @FXML
    private Button confirmButton;

    @FXML
    private Button backButton;

    @FXML
    protected void handleConfirmPaymentButtonAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Success!");
    	alert.setContentText("Payment Successful.");
    	alert.showAndWait();
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}