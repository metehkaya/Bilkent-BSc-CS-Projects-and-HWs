package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.image.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.*;
import java.time.*;

public class MatchStart implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private Text hGK;
	@FXML
	private Text hRB;
	@FXML
	private Text hLB;
	@FXML
	private Text hCB1;
	@FXML
	private Text hCB2;
	@FXML
	private Text hCM1;
	@FXML
	private Text hCM2;
	@FXML
	private Text hCM3;
	@FXML
	private Text hLW;
	@FXML
	private Text hRW;
	@FXML
	private Text hST;
	@FXML
	private Text aGK;
	@FXML
	private Text aRB;
	@FXML
	private Text aLB;
	@FXML
	private Text aCB1;
	@FXML
	private Text aCB2;
	@FXML
	private Text aCM1;
	@FXML
	private Text aCM2;
	@FXML
	private Text aCM3;
	@FXML
	private Text aLW;
	@FXML
	private Text aRW;
	@FXML
	private Text aST;
	@FXML
	private Text homeName;
	@FXML
	private Text awayName;
	@FXML
	private Text referee;
	@FXML
	private Text weather;
	@FXML
	private Text stadium;

	private static Match currentMatch;

	@FXML
	private ImageView homeLogo;
	@FXML
	private ImageView awayLogo;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Tournament t = Tournament.getInstance();
		Integer currentMatchType = -1;
		Pair<Match, Integer> currentMatchInfo;
		try {
			currentMatchInfo = t.goNextDay();
			currentMatch = currentMatchInfo.getKey();
			currentMatchType = currentMatchInfo.getValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		MatchPlayController.currentMatch = currentMatch;
		MatchPlayController.currentMatchType = currentMatchType;

		if (currentMatch == null) {
			try {
				goView("/view/CalendarView.fxml");
			} catch (Exception e) {
			}
			int x = 0 / 0;
		}
		else {
			MatchPlayController.started = true;
			Team home = currentMatch.getHome();
			Team away = currentMatch.getAway();

			homeName.setText(home.getName());
			awayName.setText(away.getName());

			String st = home.getName().toLowerCase().trim();
			st = st.replaceAll("\\s+", "");
			File logo1 = new File("img/logos/" + st + ".png");
			Image image = new Image(logo1.toURI().toString());
			homeLogo.setImage(image);

			String st2 = away.getName().toLowerCase().trim();
			st2 = st2.replaceAll("\\s+", "");
			File logo2 = new File("img/logos/" + st2 + ".png");
			Image image2 = new Image(logo2.toURI().toString());
			awayLogo.setImage(image2);

			hGK.setText(home.getPlayers().get(0).getName());
			hRB.setText(home.getPlayers().get(1).getName());
			hCB1.setText(home.getPlayers().get(2).getName());
			hCB2.setText(home.getPlayers().get(3).getName());
			hLB.setText(home.getPlayers().get(4).getName());
			hCM1.setText(home.getPlayers().get(5).getName());
			hCM2.setText(home.getPlayers().get(6).getName());
			hCM3.setText(home.getPlayers().get(7).getName());
			hRW.setText(home.getPlayers().get(8).getName());
			hST.setText(home.getPlayers().get(9).getName());
			hLW.setText(home.getPlayers().get(10).getName());

			aGK.setText(away.getPlayers().get(0).getName());
			aRB.setText(away.getPlayers().get(1).getName());
			aCB1.setText(away.getPlayers().get(2).getName());
			aCB2.setText(away.getPlayers().get(3).getName());
			aLB.setText(away.getPlayers().get(4).getName());
			aCM1.setText(away.getPlayers().get(5).getName());
			aCM2.setText(away.getPlayers().get(6).getName());
			aCM3.setText(away.getPlayers().get(7).getName());
			aRW.setText(away.getPlayers().get(8).getName());
			aST.setText(away.getPlayers().get(9).getName());
			aLW.setText(away.getPlayers().get(10).getName());

			weather.setText(currentMatch.getWeather());
			referee.setText(currentMatch.getReferee());
			stadium.setText(home.getStadium());
		}
	}

	@FXML
	public void startMatch() throws IOException {
		goView("/view/MatchPlayView.fxml");
	}

	public void goView(String view) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(view));
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		if (Main.isWindows()) {
			root.setLayoutX(320);
			root.setLayoutY(108);
		}
		if (Main.isMacos()) {
			root.setLayoutX(20);
		}
		Stage m = Main.getMainStage();
		Scene t = Main.getMainStage().getScene();
		t.setRoot(root);
		m.setScene(t);
		m.setFullScreen(true);
		Main.setMainStage(m);

	}

	@FXML
	public void goTactic() throws IOException {
		goView("/view/TacticView.fxml");
	}

	public static Match getCurrentMatch() {
		return currentMatch;
	}

	public static void setCurrentMatch(Match currentMatch2) {
		currentMatch = currentMatch2;
	}

}
