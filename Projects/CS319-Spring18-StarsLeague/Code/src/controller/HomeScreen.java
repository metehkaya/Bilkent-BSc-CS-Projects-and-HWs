package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Database;
import model.Tournament;

public class HomeScreen implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	public void newGameClicked() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewGameView.fxml"));
		Parent root = loader.load();
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		if (Main.isWindows()) {
			root.setLayoutX(355);
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

	public void loadGameClicked() throws IOException, ClassNotFoundException {
		Tournament.setInstance(Database.loadCurrentGame());
		Tournament ts = Tournament.getInstance();
		TeamController.setCurrentTeamId(ts.getMyTeamId());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TeamView.fxml"));
		Parent root = loader.load();
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		if (Main.isWindows()) {
			root.setLayoutX(355);
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

	public void helpClicked() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HelpView.fxml"));
		Parent root = loader.load();
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		if (Main.isWindows()) {
			root.setLayoutX(355);
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
	public void creditsClicked() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreditsView.fxml"));
		Parent root = loader.load();
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		if (Main.isWindows()) {
			root.setLayoutX(355);
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

	public void exitClicked() throws IOException {
		System.exit(0);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
