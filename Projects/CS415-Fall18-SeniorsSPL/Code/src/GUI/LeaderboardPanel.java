package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class LeaderboardPanel {
    @FXML
    private Button backButton;

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}