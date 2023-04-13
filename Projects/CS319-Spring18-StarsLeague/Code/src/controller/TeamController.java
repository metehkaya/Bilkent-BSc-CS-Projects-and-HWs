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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TeamController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private GridPane playerGrid;
	@FXML
	private ImageView teamImage;
	@FXML
	private GridPane infoPane;
	@FXML
	private GridPane managerPane;
	@FXML
	private Text managerName;
	@FXML
	private Text name;

	private static int currentTeamId;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Team cur = Tournament.getInstance().getTeams()[currentTeamId];
		for (int i = 0; i < 20; i++) {
			Player p = cur.getPlayers().get(i);
			Text pName = new Text(p.getName());
			Text pPosition = new Text(p.getPosition());
			Text pOverall = new Text(String.valueOf(p.getOverall()));
			Text pFoot = new Text(p.getFoot() + "");
			Text pSalary = new Text(String.valueOf(p.getSalary() / 1000000.0 + "m €"));
			Text pValue = new Text(cur.getPlayers().get(i).getValue() / 1000000 + "m €");

			File nationImg = new File("img/flags/" + p.getNationality().toLowerCase().trim() + ".png");
			ImageView flag = new ImageView(new Image(nationImg.toURI().toString()));
			flag.setFitHeight(20);
			flag.setFitWidth(40);

			playerGrid.add(pName, 0, i);
			playerGrid.add(pPosition, 1, i);
			playerGrid.add(pOverall, 2, i);
			playerGrid.add(pFoot, 3, i);
			playerGrid.add(pSalary, 5, i);
			playerGrid.add(pValue, 6, i);
			playerGrid.add(flag, 4, i);

		}

		// Team Logo
		String st = cur.getName().toLowerCase().trim();
		st = st.replaceAll("\\s+", "");
		File file = new File("img/logos/" + st + ".png");
		Image image = new Image(file.toURI().toString());
		teamImage.setImage(image);
		name.setText(cur.getName());
		MatchPlayController.fillColorText(name, cur.getColor());
		fillInformation();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 20; j++) {
				addPane(i, j);
			}
		}
	}

	public void fillInformation() {
		Team cur = Tournament.getInstance().getTeams()[currentTeamId];
		Text stadium = new Text("" + cur.getStadium());
		stadium.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		infoPane.add(stadium, 1, 0);

		File nationFlag = new File("img/flags/" + cur.getNationality().toLowerCase().trim() + ".png");
		Image nationImage = new Image(nationFlag.toURI().toString(), 40, 25, false, false);
		infoPane.add(new ImageView(nationImage), 1, 1);

		Text president = new Text(cur.getPresident().getName());
		president.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		infoPane.add(president, 1, 2);

		Text nick = new Text(cur.getNick());
		nick.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		infoPane.add(nick, 1, 3);

		Text history = new Text(cur.getHistory());
		history.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		infoPane.add(history, 1, 4);


		Text overall = new Text("" + cur.getManager().getOverall());
		overall.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		managerPane.add(overall, 1, 0);

		Text experience = new Text("" + cur.getManager().getExperience());
		experience.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		managerPane.add(experience, 1, 1);

		Text age = new Text("" + cur.getManager().getAge());
		age.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		managerPane.add(age, 3, 1);


		File mn = new File("img/flags/" + cur.getManager().getNationality().toLowerCase().trim() + ".png");
		Image managerNation = new Image(mn.toURI().toString(), 40, 25, false, false);
		managerPane.add(new ImageView(managerNation), 3, 0);

		managerName.setText(cur.getManager().getName());
		managerName.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
		managerName.setFill(Color.BLACK);

	}

	// Taken from https://code.i-harness.com/en/q/2b1fed4
	private int[] addPane(int colIndex, int rowIndex) {
		Pane pane = new Pane();
		int[] res = new int[2];
		pane.setOnMouseClicked(e -> {
			PlayerController.setPlayerID(rowIndex);
			PlayerController.setTeamID(currentTeamId);
			try {
				changeToPlayerView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		playerGrid.add(pane, colIndex, rowIndex);
		return res;
	}

	public void changeToPlayerView() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/PlayerView.fxml"));
		root.setScaleX(screenWidth / 1400.0);
		root.setScaleY(screenHeight / 900.0);
		root.setLayoutX(20);
		if (Main.isWindows()) {
			root.setLayoutX(355);
			root.setLayoutY(108);
		}
		Stage m = Main.getMainStage();
		Scene t = Main.getMainStage().getScene();
		t.setRoot(root);
		m.setScene(t);
		m.setFullScreen(true);
		Main.setMainStage(m);
	}

	public static int getCurrentTeamId() {
		return currentTeamId;
	}

	public static void setCurrentTeamId(int currentTeamId2) {
		currentTeamId = currentTeamId2;
	}
}
