package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class HelpPanel {
    @FXML
    private TextField inputField;

    @FXML
    private Button registerProviderButton;

    @FXML
    private Button returnButton;

    @FXML
    private Label productLabel;

    @FXML
    private Label providerLabel;

    public void initialize() {
    	if(MainGUI.input == 1) { // feedme
    		productLabel.setText("Where is my food?");
    		providerLabel.setText("I've spoken to the restaurant. Your order is on its way.");
    	}
    	else if(MainGUI.input == 2) { // maturepear
    		productLabel.setText("Where is the plumber?");
    		providerLabel.setText("I've spoken to the provider. They are on their way.");
    	}
    	else { // healthy life
    		productLabel.setText("Where is my medicine?");
    		providerLabel.setText("I've spoken to the pharmacy. The courier is on their way.");
    	}
    }

    @FXML
    protected void handleSendMessageButtonAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Success!");
    	alert.setContentText("Message Delivered Successfully.");
    	alert.showAndWait();
    }

    @FXML
    protected void handleReturnButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}