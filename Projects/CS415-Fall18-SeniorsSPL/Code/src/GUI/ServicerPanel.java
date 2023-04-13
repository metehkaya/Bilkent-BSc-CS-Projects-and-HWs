package GUI;

import java.io.IOException;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class ServicerPanel {

    @FXML
    private Label titleLabel;

    @FXML
    private Button addButton1;
    @FXML
    private Label itemLabel1;
    @FXML
    private Label priceLabel1;

    @FXML
    private Button addButton2;
    @FXML
    private Label itemLabel2;
    @FXML
    private Label priceLabel2;

    @FXML
    private Button addButton3;
    @FXML
    private Label itemLabel3;
    @FXML
    private Label priceLabel3;

    @FXML
    private Button backButton;
    @FXML
    private Button payButton;

    public void initialize() {
    	if(MainGUI.input == 1) { // feedme
    		titleLabel.setText("FeedMe - Servicer Page");
    		itemLabel1.setText( "Deluxe Vegie" );
    		priceLabel1.setText( "35 TL" );
    		itemLabel2.setText( "Chessy Dip" );
    		priceLabel2.setText( "32 TL" );
    		itemLabel3.setText( "Cheese Margherita" );
    		priceLabel3.setText( "43 TL" );
    	}
    	else if(MainGUI.input == 2) { // maturepear
    		titleLabel.setText("MaturePear - Servicer Page");
    		itemLabel1.setText( "Garden Decoration" );
    		priceLabel1.setText( "500 - 10000 TL" );
    		itemLabel2.setText( "Office Decoration" );
    		priceLabel2.setText( "1000 - 25000 TL" );
    		itemLabel3.setText( "Home Decoration" );
    		priceLabel3.setText( "5000 - 50000 TL" );
    		addButton1.setVisible(false);
    		addButton2.setVisible(false);
    		addButton3.setVisible(false);
    		payButton.setVisible(false);
    	}
    	else if(MainGUI.input == 3){ // healthy life
    		titleLabel.setText("HealthyLife - Servicer Page");
    		itemLabel1.setText( "Aferin" );
    		priceLabel1.setText( "8 TL" );
    		itemLabel2.setText( "Rennie" );
    		priceLabel2.setText( "10 TL" );
    		itemLabel3.setText( "Minoset" );
    		priceLabel3.setText( "12 TL" );
    	}
    }

    @FXML
    protected void handleAddButtonAction1(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Success!");
    	alert.setContentText("Added to Card.");
    	alert.showAndWait();
    	System.out.println("1");
    }

    @FXML
    protected void handleAddButtonAction2(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Success!");
    	alert.setContentText("Added Successful.");
    	alert.showAndWait();
    	System.out.println("2");
    }

    @FXML
    protected void handleAddButtonAction3(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Success!");
    	alert.setContentText("Added Successful.");
    	alert.showAndWait();
    	System.out.println("3");
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handlePayButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    	System.out.println( "Pay" );
    }

}
