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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GroupController implements Initializable {
	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private Text t1;
	@FXML
	private Text t2;
	@FXML
	private Text t3;
	@FXML
	private Text t4;

	@FXML
	private Text t1p;
	@FXML
	private Text t2p;
	@FXML
	private Text t3p;
	@FXML
	private Text t4p;

	@FXML
	private Text t1w;
	@FXML
	private Text t2w;
	@FXML
	private Text t3w;
	@FXML
	private Text t4w;

	@FXML
	private Text t1d;
	@FXML
	private Text t2d;
	@FXML
	private Text t3d;
	@FXML
	private Text t4d;

	@FXML
	private Text t1l;
	@FXML
	private Text t2l;
	@FXML
	private Text t3l;
	@FXML
	private Text t4l;

	@FXML
	private Text t1s;
	@FXML
	private Text t2s;
	@FXML
	private Text t3s;
	@FXML
	private Text t4s;

	@FXML
	private Text t1c;
	@FXML
	private Text t2c;
	@FXML
	private Text t3c;
	@FXML
	private Text t4c;

	@FXML
	private Text t1pts;
	@FXML
	private Text t2pts;
	@FXML
	private Text t3pts;
	@FXML
	private Text t4pts;

	@FXML
	private ComboBox<String> groupName;
	@FXML
	private Text gName;
	@FXML
	private GridPane matchesGrid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		groupName.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
		setup("A");
		groupName.getSelectionModel().selectFirst();
	}

	public void setup(String group) {
		char c = group.charAt(0);
		int num = (int) c - 65;
		gName.setText("Group ");
		Group cur = Tournament.getInstance().getGroups()[num];
		cur.orderGroup();
		t1.setText(cur.getOrderedTeamName(0));
		t2.setText(cur.getOrderedTeamName(1));
		t3.setText(cur.getOrderedTeamName(2));
		t4.setText(cur.getOrderedTeamName(3));

		t1p.setText(String.valueOf(cur.getOrderedStats()[0][0]));
		t2p.setText(String.valueOf(cur.getOrderedStats()[1][0]));
		t3p.setText(String.valueOf(cur.getOrderedStats()[2][0]));
		t4p.setText(String.valueOf(cur.getOrderedStats()[3][0]));

		t1w.setText(String.valueOf(cur.getOrderedStats()[0][1]));
		t2w.setText(String.valueOf(cur.getOrderedStats()[1][1]));
		t3w.setText(String.valueOf(cur.getOrderedStats()[2][1]));
		t4w.setText(String.valueOf(cur.getOrderedStats()[3][1]));

		t1d.setText(String.valueOf(cur.getOrderedStats()[0][2]));
		t2d.setText(String.valueOf(cur.getOrderedStats()[1][2]));
		t3d.setText(String.valueOf(cur.getOrderedStats()[2][2]));
		t4d.setText(String.valueOf(cur.getOrderedStats()[3][2]));

		t1l.setText(String.valueOf(cur.getOrderedStats()[0][3]));
		t2l.setText(String.valueOf(cur.getOrderedStats()[1][3]));
		t3l.setText(String.valueOf(cur.getOrderedStats()[2][3]));
		t4l.setText(String.valueOf(cur.getOrderedStats()[3][3]));

		t1s.setText(String.valueOf(cur.getOrderedStats()[0][4]));
		t2s.setText(String.valueOf(cur.getOrderedStats()[1][4]));
		t3s.setText(String.valueOf(cur.getOrderedStats()[2][4]));
		t4s.setText(String.valueOf(cur.getOrderedStats()[3][4]));

		t1c.setText(String.valueOf(cur.getOrderedStats()[0][5]));
		t2c.setText(String.valueOf(cur.getOrderedStats()[1][5]));
		t3c.setText(String.valueOf(cur.getOrderedStats()[2][5]));
		t4c.setText(String.valueOf(cur.getOrderedStats()[3][5]));

		t1pts.setText(String.valueOf(cur.getOrderedStats()[0][6]));
		t2pts.setText(String.valueOf(cur.getOrderedStats()[1][6]));
		t3pts.setText(String.valueOf(cur.getOrderedStats()[2][6]));
		t4pts.setText(String.valueOf(cur.getOrderedStats()[3][6]));

		setupMatches(group, false);

	}

	public void setupMatches(String group, boolean clear) {
		if (clear)
			matchesGrid.getChildren().clear();
		char c = group.charAt(0);
		int num = (int) c - 65;
		Group cur = Tournament.getInstance().getGroups()[num];
		int space = 0;
		for (int i = 0; i < cur.getMatches().length; i++) {
			if ((i+space+1)%3 == 0)
				space++;
			Text homeName = new Text(cur.getMatches()[i].getHome().getName());
			homeName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			matchesGrid.add(homeName, 0, i+space);

			Text awayName = new Text(cur.getMatches()[i].getAway().getName());
			awayName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			matchesGrid.add(awayName, 2, i+space);

			String goalHomeText = "", goalAwayText = "";
			int goalHome = cur.getMatches()[i].getGoalHome();
			int goalAway = cur.getMatches()[i].getGoalAway();
			if (goalHome != -1)
				goalHomeText = Integer.toString(goalHome);
			if (goalAway != -1)
				goalAwayText = Integer.toString(goalAway);

			Text score = new Text(goalHomeText + "-" + goalAwayText);
			score.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			matchesGrid.add(score, 1, i+space);


		}
	}

	@FXML
	public void groupChange() {
		String group = groupName.getValue();
		setup(group);
		setupMatches(group, true);
	}
}
