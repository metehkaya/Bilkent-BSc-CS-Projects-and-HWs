package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentPanel {
    @FXML
    private TableView<Data> tableView;

    @FXML
    private TableColumn<Data, String> nameColumn;

    @FXML
    private TableColumn<Data, String> valueColumn;

    @FXML
    private Button confirmPaymentButton;

    @FXML
    private Button returnButton;

    final ObservableList<Data> itemData1 = FXCollections.observableArrayList( // feedme
		new Data("Kasarli Sosisli Makarna", "19.5 TL"),
		new Data("500 Gram Izgara Kofte", "56 TL"),
		new Data("Litrelik Coca Cola Zero", "7 TL"),
		new Data("Garlic Sos", "2 TL"),
		new Data("Rio Soslu Tavuk", "21 TL"),
		new Data("Total Sum", "105.5 TL")
	);

    final ObservableList<Data> itemData2 = FXCollections.observableArrayList( // maturepear
		new Data("Lavabo Açma", "15 TL"),
		new Data("Civi Cakma", "10 TL"),
		new Data("Total Sum", "25 TL")
	);

    final ObservableList<Data> itemData3 = FXCollections.observableArrayList( // healthylife
		new Data("Minnoset", "12 TL"),
		new Data("Rennie", "10 TL"),
		new Data("Vitamin C", "15 TL"),
		new Data("Aferin", "8 TL"),
		new Data("Total Sum", "45 TL")
	);

    public void initialize() {
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
    	valueColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("value"));


    	if(MainGUI.input == 1) { // feedme
    		tableView.setItems(itemData1);
    	}
    	else if(MainGUI.input == 2) { // maturepear
    		tableView.setItems(itemData2);
    	}
    	else { // healthy life
    		tableView.setItems(itemData3);
    	}
    	tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    protected void handleConfirmPaymentButtonAction(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("creditcard_panel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MainGUI.parentWindow.getScene().setRoot(root1);
    	}
    	catch (IOException e) {}
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

    public static class Data {
    	private final SimpleStringProperty name;
    	private final SimpleStringProperty value;

    	private Data(String nname, String nvalue) {
    		this.name = new SimpleStringProperty(nname);
    		this.value = new SimpleStringProperty(nvalue);
    	}

    	public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String fName) {
            value.set(fName);
        }
    }
}