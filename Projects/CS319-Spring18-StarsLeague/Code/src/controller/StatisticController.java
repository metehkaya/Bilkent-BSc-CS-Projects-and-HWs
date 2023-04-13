package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StatisticController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private GridPane scorer;
	@FXML
	private GridPane assister;
	@FXML
	private GridPane yellow;
	@FXML
	private GridPane red;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tournament c = Tournament.getInstance();
		String[] scorers = c.getTopGoals();
		String[] assisters = c.getTopAssists();
		String[] redCards = c.getTopRedCards();
		String[] yellowCards = c.getTopYellowCards();

		for (int i = 0; i < 5; i++) {
			Text s = new Text(scorers[i].split(" ")[0]);
			s.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			scorer.add(s, 0, i);

			Text ns = new Text(scorers[i].split(" ")[scorers[i].split(" ").length-1]);
			ns.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			scorer.add(ns, 1, i);

			Text a = new Text(assisters[i].split(" ")[0]);
			a.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			assister.add(a, 0, i);

			Text na = new Text(assisters[i].split(" ")[assisters[i].split(" ").length-1]);
			na.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			assister.add(na, 1, i);

			Text y = new Text(yellowCards[i].split(" ")[0]);
			y.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			yellow.add(y, 0, i);

			Text ny = new Text(yellowCards[i].split(" ")[yellowCards[i].split(" ").length-1]);
			ny.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			yellow.add(ny, 1, i);

			Text r = new Text(redCards[i].split(" ")[0]);
			r.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			red.add(r, 0, i);

			Text nr = new Text(redCards[i].split(" ")[redCards[i].split(" ").length-1]);
			nr.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			red.add(nr, 1, i);

		}

	}
}
