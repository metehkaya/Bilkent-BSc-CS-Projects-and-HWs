package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ResourceBundle;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javafx.fxml.FXML;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlayerController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private Text playerName;
	@FXML
	private Text overall;
	@FXML
	private ImageView playerClub;
	@FXML
	private GridPane generalGrid;
	@FXML
	private GridPane attGrid;
	@FXML
	private GridPane statGrid;

	private static int playerID;
	private static int teamID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tournament c = Tournament.getInstance();
		Team t = c.getTeams()[teamID];
		Player p = t.getPlayers().get(playerID);

		// Team Logo
		String st = t.getName().toLowerCase().trim();
		st = st.replaceAll("\\s+", "");
		System.out.println(st);
		File file = new File("img/logos/" + st + ".png");
		Image image = new Image(file.toURI().toString());
		playerClub.setImage(image);

		playerName.setText(p.getName());
		// Overall
		overall.setText(String.valueOf(p.getOverall()));

		// General
		generalGrid.add(new Text(t.getName()), 1, 0);
		generalGrid.add(new Text(p.getPosition()), 1, 1);
		generalGrid.add(new Text(String.valueOf(p.getAge())), 1, 2);
		generalGrid.add(new Text("" + p.getFoot()), 1, 3);
		File nationImg = new File("img/flags/" + p.getNationality().toLowerCase().trim() + ".png");
		ImageView flag = new ImageView(new Image(nationImg.toURI().toString()));
		flag.setFitHeight(20);
		flag.setFitWidth(40);
		generalGrid.add(flag, 1, 4);
		generalGrid.add(new Text(String.valueOf(p.getHeight()) + "cm"), 3, 0);
		generalGrid.add(new Text(String.valueOf(p.getWeight()) + "kg"), 3, 1);
		generalGrid.add(new Text(p.getValue() / 1000000.0 + " m €"), 3, 2);
		generalGrid.add(new Text(p.getSalary() / 1000000.0 + " m €"), 3, 3);
		generalGrid.add(new Text("Available"), 3, 4);

		// Attributes
		attGrid.add(new Text(String.valueOf(p.getAttributes()[0])), 1, 0);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[1])), 1, 1);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[2])), 1, 2);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[3])), 1, 3);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[4])), 3, 0);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[5])), 3, 1);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[6])), 3, 2);
		attGrid.add(new Text(String.valueOf(p.getAttributes()[7])), 3, 3);

		// Statistics
		statGrid.add(new Text(String.valueOf(12)), 0, 0);
		statGrid.add(new Text(String.valueOf(p.getCntGoal())), 1, 0);
		statGrid.add(new Text(String.valueOf(p.getCntAssist())), 2, 0);
		statGrid.add(new Text(String.valueOf(p.getCntYellowCard())), 3, 0);
		statGrid.add(new Text(String.valueOf(p.getCntRedCard())), 4, 0);
	}

	@FXML
	public void offerClicked() {

	}

	public static int getPlayerID() {
		return playerID;
	}

	static void setPlayerID(int playerID) {
		PlayerController.playerID = playerID;
	}

	public static int getTeamID() {
		return teamID;
	}

	public static void setTeamID(int teamID) {
		PlayerController.teamID = teamID;
	}
}
