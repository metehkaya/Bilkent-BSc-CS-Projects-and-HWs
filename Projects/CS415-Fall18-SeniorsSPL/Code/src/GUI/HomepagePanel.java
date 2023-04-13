package GUI;

import java.io.IOException;
import java.util.ArrayList;

import CodeBase.Context;
import CodeBase.Provider;
import FeedMe.FoodProviderDB;
import HealthyLife.MedicineProviderDB;
import MaturePear.ServiceProviderDB;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HomepagePanel {
    @FXML
    private TextField inputField;

    @FXML
    private Button customerSupportButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Label providerLabel1;

    @FXML
    private Label providerLabel2;

    @FXML
    private Label providerLabel3;

    @FXML
    private Button servicerPageButton1;

    @FXML
    private Button servicerPageButton2;

    @FXML
    private Button servicerPageButton3;

    public void initialize() {

    	Context context;

    	if( MainGUI.input == 1 ) { // FeedMe
    		context = new Context( new FoodProviderDB() );
    	}
    	else if( MainGUI.input == 2 ) { // MaturePear
    		context = new Context( new ServiceProviderDB() );
    		paymentButton.setVisible(false);
    		leaderboardButton.setVisible(false);
    	}
    	else { // HealthyLife
    		context = new Context( new MedicineProviderDB() );
    		leaderboardButton.setVisible(false);
    	}

    	ArrayList<Provider> providers = context.getProviders();
    	providerLabel1.setText( providers.get(0).getName() );
    	providerLabel2.setText( providers.get(1).getName() );
    	providerLabel3.setText( providers.get(2).getName() );

    }

    @FXML
    protected void handleCustomerSupportButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handlePaymentButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleLogOutButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleLeaderboardButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leaderboard_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleServicerPageAction1(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("servicer_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    protected void handleServicerPageAction2(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("servicer_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }

    @FXML
    protected void handleServicerPageAction3(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("servicer_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
    }
}