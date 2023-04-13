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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class KnockoutController implements Initializable {

	@FXML
	private Text t1;
	@FXML
	private Text t2;
	@FXML
	private Text t3;
	@FXML
	private Text t4;
	@FXML
	private Text t5;
	@FXML
	private Text t6;
	@FXML
	private Text t7;
	@FXML
	private Text t8;
	@FXML
	private Text t9;
	@FXML
	private Text t10;
	@FXML
	private Text t11;
	@FXML
	private Text t12;
	@FXML
	private Text t13;
	@FXML
	private Text t14;
	@FXML
	private Text t15;
	@FXML
	private Text t16;
	@FXML
	private Text t17;
	@FXML
	private Text t18;
	@FXML
	private Text t19;
	@FXML
	private Text t20;
	@FXML
	private Text t21;
	@FXML
	private Text t22;
	@FXML
	private Text t23;
	@FXML
	private Text t24;
	@FXML
	private Text t25;
	@FXML
	private Text t26;
	@FXML
	private Text t27;
	@FXML
	private Text t28;
	@FXML
	private Text t00;
	@FXML
	private Text t01;

	@FXML
	private GridPane last16Matches;
	@FXML
	private GridPane quarterMatches;
	@FXML
	private GridPane semiMatches;
	@FXML
	private GridPane finalMatch;

	@FXML
	private Text last16Text;
	@FXML
	private Text quarterText;
	@FXML
	private Text semiText;
	@FXML
	private Text finalText;

	@FXML
	private AnchorPane matchAnchor;

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
	Tournament t = Tournament.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buildTree();
		if (t.getStatusEliminationStage())
			fillMatches();
		else
			matchAnchor.setPrefHeight(500);

	}

	public void fillMatches() {
		Match[] all = t.getKnockout().getKnockout().getMatches();
		int space = 0;
		int j = 0;
		// Last 16
		if (all[28].getHome() != null) {
			for (int i = 28; i > 12; i--) {
				if ((j + space + 1) % 3 == 0)
					space++;
				Text homeName = new Text(all[i].getHome().getName());
				homeName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				last16Matches.add(homeName, 0, j + space);

				Text awayName = new Text(all[i].getAway().getName());
				awayName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				last16Matches.add(awayName, 2, j + space);

				String goalHomeText = "", goalAwayText = "";
				int goalHome = all[i].getGoalHome();
				int goalAway = all[i].getGoalAway();
				if (goalHome != -1)
					goalHomeText = Integer.toString(goalHome);
				if (goalAway != -1)
					goalAwayText = Integer.toString(goalAway);

				Text score = new Text(goalHomeText + "-" + goalAwayText);
				score.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				last16Matches.add(score, 1, j + space);
				j++;
			}
		} else {
			last16Text.setVisible(false);
			last16Matches.setVisible(false);
		}

		// Quarter
		if (all[12].getHome() != null) {
			space = 0;
			j = 0;
			for (int i = 12; i > 4; i--) {
				if ((j + space + 1) % 3 == 0)
					space++;
				Text homeName = new Text(all[i].getHome().getName());
				homeName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				quarterMatches.add(homeName, 0, j + space);

				Text awayName = new Text(all[i].getAway().getName());
				awayName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				quarterMatches.add(awayName, 2, j + space);

				String goalHomeText = "", goalAwayText = "";
				int goalHome = all[i].getGoalHome();
				int goalAway = all[i].getGoalAway();
				if (goalHome != -1)
					goalHomeText = Integer.toString(goalHome);
				if (goalAway != -1)
					goalAwayText = Integer.toString(goalAway);

				Text score = new Text(goalHomeText + "-" + goalAwayText);
				score.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				quarterMatches.add(score, 1, j + space);
				j++;
			}
		} else {
			quarterText.setVisible(false);
			quarterMatches.setVisible(false);
		}

		// Semi
		if (all[4].getHome() != null) {
			space = 0;
			j = 0;
			for (int i = 4; i > 0; i--) {
				if ((j + space + 1) % 3 == 0)
					space++;
				Text homeName = new Text(all[i].getHome().getName());
				homeName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				semiMatches.add(homeName, 0, j + space);

				Text awayName = new Text(all[i].getAway().getName());
				awayName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				semiMatches.add(awayName, 2, j + space);

				String goalHomeText = "", goalAwayText = "";
				int goalHome = all[i].getGoalHome();
				int goalAway = all[i].getGoalAway();
				if (goalHome != -1)
					goalHomeText = Integer.toString(goalHome);
				if (goalAway != -1)
					goalAwayText = Integer.toString(goalAway);

				Text score = new Text(goalHomeText + "-" + goalAwayText);
				score.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
				semiMatches.add(score, 1, j + space);
				j++;
			}
		} else {
			semiText.setVisible(false);
			semiMatches.setVisible(false);
		}

		// Final

		if (all[0].getHome() != null) {
			Text homeName = new Text(all[0].getHome().getName());
			homeName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			finalMatch.add(homeName, 0, 0);
		}
		if (all[0].getAway() != null) {
			Text awayName = new Text(all[0].getAway().getName());
			awayName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
			finalMatch.add(awayName, 2, 0);
		}
		if (all[0].getHome() == null || all[0].getAway() == null) {
			finalText.setVisible(false);
			finalMatch.setVisible(false);
		}

		String goalHomeText = "", goalAwayText = "";
		int goalHome = all[0].getGoalHome();
		int goalAway = all[0].getGoalAway();
		if (goalHome != -1)
			goalHomeText = Integer.toString(goalHome);
		if (goalAway != -1)
			goalAwayText = Integer.toString(goalAway);

		Text score = new Text(goalHomeText + "-" + goalAwayText);
		score.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 16));
		quarterMatches.add(score, 1, 0);

	}

	public void buildTree() {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
		t9.setText("");
		t10.setText("");
		t11.setText("");
		t12.setText("");
		t13.setText("");
		t14.setText("");
		t15.setText("");
		t16.setText("");
		t17.setText("");
		t18.setText("");
		t19.setText("");
		t20.setText("");
		t21.setText("");
		t22.setText("");
		t23.setText("");
		t24.setText("");
		t25.setText("");
		t26.setText("");
		t27.setText("");
		t28.setText("");
		t00.setText("");
		t01.setText("");
		if (t.getStatusEliminationStage()) {
			// Last 16
			t13.setText(t.getKnockout().getKnockout().getMatches()[13].getHome().getName());
			t14.setText(t.getKnockout().getKnockout().getMatches()[14].getHome().getName());
			t15.setText(t.getKnockout().getKnockout().getMatches()[15].getHome().getName());
			t16.setText(t.getKnockout().getKnockout().getMatches()[16].getHome().getName());
			t17.setText(t.getKnockout().getKnockout().getMatches()[17].getHome().getName());
			t18.setText(t.getKnockout().getKnockout().getMatches()[18].getHome().getName());
			t19.setText(t.getKnockout().getKnockout().getMatches()[19].getHome().getName());
			t20.setText(t.getKnockout().getKnockout().getMatches()[20].getHome().getName());
			t21.setText(t.getKnockout().getKnockout().getMatches()[21].getHome().getName());
			t22.setText(t.getKnockout().getKnockout().getMatches()[22].getHome().getName());
			t23.setText(t.getKnockout().getKnockout().getMatches()[23].getHome().getName());
			t24.setText(t.getKnockout().getKnockout().getMatches()[24].getHome().getName());
			t25.setText(t.getKnockout().getKnockout().getMatches()[25].getHome().getName());
			t26.setText(t.getKnockout().getKnockout().getMatches()[26].getHome().getName());
			t27.setText(t.getKnockout().getKnockout().getMatches()[27].getHome().getName());
			t28.setText(t.getKnockout().getKnockout().getMatches()[28].getHome().getName());

			// Quarter Final
			if (t.getKnockout().getKnockout().getMatches()[6].getHome() != null) {
				t5.setText(t.getKnockout().getKnockout().getMatches()[5].getHome().getName());
				t6.setText(t.getKnockout().getKnockout().getMatches()[6].getHome().getName());
			}
			if (t.getKnockout().getKnockout().getMatches()[8].getHome() != null) {
				t7.setText(t.getKnockout().getKnockout().getMatches()[7].getHome().getName());
				t8.setText(t.getKnockout().getKnockout().getMatches()[8].getHome().getName());
			}
			if (t.getKnockout().getKnockout().getMatches()[10].getHome() != null) {
				t9.setText(t.getKnockout().getKnockout().getMatches()[9].getHome().getName());
				t10.setText(t.getKnockout().getKnockout().getMatches()[10].getHome().getName());
			}
			if (t.getKnockout().getKnockout().getMatches()[12].getHome() != null) {
				t11.setText(t.getKnockout().getKnockout().getMatches()[11].getHome().getName());
				t12.setText(t.getKnockout().getKnockout().getMatches()[12].getHome().getName());
			}

			// Semi Final
			if (t.getKnockout().getKnockout().getMatches()[2].getHome() != null) {
				t1.setText(t.getKnockout().getKnockout().getMatches()[1].getHome().getName());
				t2.setText(t.getKnockout().getKnockout().getMatches()[2].getHome().getName());
			}
			if (t.getKnockout().getKnockout().getMatches()[3].getHome() != null) {
				t3.setText(t.getKnockout().getKnockout().getMatches()[3].getHome().getName());
				t4.setText(t.getKnockout().getKnockout().getMatches()[4].getHome().getName());
			}

			// Final
			if (t.getKnockout().getKnockout().getMatches()[0].getHome() != null) {
				t01.setText(t.getKnockout().getKnockout().getMatches()[0].getHome().getName());
			}
			if (t.getKnockout().getKnockout().getMatches()[0].getAway() != null) {
				t00.setText(t.getKnockout().getKnockout().getMatches()[0].getAway().getName());
			}

		}
	}

}
