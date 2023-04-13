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

import com.sun.prism.paint.Color;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TacticController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private Text teamName;
	@FXML
	private Text st;
	@FXML
	private Text lw;
	@FXML
	private Text rw;
	@FXML
	private Text cm1;
	@FXML
	private Text cm2;
	@FXML
	private Text cm3;
	@FXML
	private Text lb;
	@FXML
	private Text cb1;
	@FXML
	private Text cb2;
	@FXML
	private Text rb;
	@FXML
	private Text gk;
	@FXML
	private ComboBox<String> tacticBox;
	@FXML
	private ComboBox<String> styleBox;
	@FXML
	private ComboBox<String> tempoBox;
	@FXML
	private GridPane gridPlayer;
	@FXML
	private ScrollPane scrollPlayer;
	@FXML
	private ImageView logo;
	@FXML
	private ImageView tacticField;

	@FXML
	private ComboBox<String> pl1;
	@FXML
	private ComboBox<String> pl2;
	@FXML
	private ComboBox<String> pl3;
	@FXML
	private ComboBox<String> pl4;
	@FXML
	private ComboBox<String> pl5;
	@FXML
	private ComboBox<String> pl6;
	@FXML
	private ComboBox<String> pl7;
	@FXML
	private ComboBox<String> pl8;
	@FXML
	private ComboBox<String> pl9;
	@FXML
	private ComboBox<String> pl10;
	@FXML
	private ComboBox<String> pl11;

	@FXML
	private ComboBox<String> sub1;
	@FXML
	private ComboBox<String> sub2;
	@FXML
	private ComboBox<String> sub3;
	@FXML
	private ComboBox<String> sub4;
	@FXML
	private ComboBox<String> sub5;
	@FXML
	private ComboBox<String> sub6;
	@FXML
	private ComboBox<String> sub7;
	@FXML
	private ComboBox<String> sub8;
	@FXML
	private ComboBox<String> sub9;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];

		tacticFieldSetup();
		calibrateNames();
		playerBoxSetup(t.getTactic(), false);
		// Team Logo
		String st = t.getName().toLowerCase().trim();
		st = st.replaceAll("\\s+", "");
		File file = new File("img/logos/" + st + ".png");
		Image image = new Image(file.toURI().toString());
		logo.setImage(image);

		// ComboBox for the tactics, tempo and style
		tacticBox.getItems().addAll("4-3-3", "4-4-2", "4-2-3-1");
		styleBox.getItems().addAll("Attack", "Defensive", "Holding");
		tempoBox.getItems().addAll("Fast", "Normal", "Slow");

		tacticBox.getSelectionModel().select(t.getTactic());
		styleBox.getSelectionModel().select(t.getStyle());
		tempoBox.getSelectionModel().select(t.getTempo());
		// Sub-players' comboBox setup
		comboBoxSetup(t.getTactic());
		gridUpdate();
		for (int i = 1; i < 5; i++) {
			for (int j = 0; j < 20; j++) {
				addPane(i, j);
			}
		}
	}

	@FXML
	public void calibrateNames() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Names of the players as Text
		gk.setText(t.getPlayers().get(0).getName());
		rb.setText(t.getPlayers().get(1).getName());
		cb1.setText(t.getPlayers().get(2).getName());
		cb2.setText(t.getPlayers().get(3).getName());
		lb.setText(t.getPlayers().get(4).getName());
		cm1.setText(t.getPlayers().get(5).getName());
		cm3.setText(t.getPlayers().get(6).getName());
		cm2.setText(t.getPlayers().get(7).getName());
		rw.setText(t.getPlayers().get(8).getName());
		st.setText(t.getPlayers().get(9).getName());
		lw.setText(t.getPlayers().get(10).getName());

		if (t.getTactic().equals("4-3-3")) {
			gk.setLayoutX(270);
			gk.setLayoutY(470);

			rb.setLayoutX(505);
			rb.setLayoutY(363);

			cb2.setLayoutX(174);
			cb2.setLayoutY(392);

			cb1.setLayoutX(359);
			cb1.setLayoutY(394);

			lb.setLayoutX(28);
			lb.setLayoutY(367);

			// RW
			cm1.setLayoutX(280);
			cm1.setLayoutY(300);

			// Stays as midfielders
			cm2.setLayoutX(356);
			cm2.setLayoutY(233);

			cm3.setLayoutX(178);
			cm3.setLayoutY(232);

			// becomes LW
			rw.setLayoutX(465);
			rw.setLayoutY(177);

			// LW becomes LF
			lw.setLayoutX(87);
			lw.setLayoutY(175);

			// ST becomes RF
			st.setLayoutX(275);
			st.setLayoutY(139);
		}

		else if (t.getTactic().equals("4-2-3-1")) {
			gk.setLayoutX(281);
			gk.setLayoutY(475);

			rb.setLayoutX(505);
			rb.setLayoutY(363);

			cb2.setLayoutX(166);
			cb2.setLayoutY(402);

			cb1.setLayoutX(368);
			cb1.setLayoutY(400);

			lb.setLayoutX(28);
			lb.setLayoutY(367);

			// RW
			cm1.setLayoutX(204);
			cm1.setLayoutY(298);

			// RCM
			cm2.setLayoutX(358);
			cm2.setLayoutY(300);

			// LCM
			cm3.setLayoutX(284);
			cm3.setLayoutY(229);

			// LW
			rw.setLayoutX(471);
			rw.setLayoutY(208);

			lw.setLayoutX(97);
			lw.setLayoutY(204);

			st.setLayoutX(282);
			st.setLayoutY(123);
		} else if (t.getTactic().equals("4-4-2")) {
			gk.setLayoutX(270);
			gk.setLayoutY(475);

			rb.setLayoutX(506);
			rb.setLayoutY(360);

			cb2.setLayoutX(175);
			cb2.setLayoutY(389);

			cb1.setLayoutX(364);
			cb1.setLayoutY(389);

			lb.setLayoutX(31);
			lb.setLayoutY(370);

			// RCM
			rw.setLayoutX(100);
			rw.setLayoutY(203);

			cm2.setLayoutX(339);
			cm2.setLayoutY(275);

			// LCM
			cm3.setLayoutX(205);
			cm3.setLayoutY(273);

			cm1.setLayoutX(471);
			cm1.setLayoutY(208);

			lw.setLayoutX(205);
			lw.setLayoutY(141);

			st.setLayoutX(354);
			st.setLayoutY(144);
		}
	}

	private int[] addPane(int colIndex, int rowIndex) {
		Pane pane = new Pane();
		int[] res = new int[2];
		pane.setOnMouseClicked(e -> {
			PlayerController.setPlayerID(rowIndex);
			PlayerController.setTeamID(TeamController.getCurrentTeamId());
			try {
				changeToPlayerView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		gridPlayer.add(pane, colIndex, rowIndex);
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

	@FXML
	public void tacticFieldSetup() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		teamName.setText(t.getName());
		MatchPlayController.fillColorText(teamName, t.getColor());
		File tactic = new File("img/tactics/" + t.getTactic() + "_" + t.getColor() + ".png");
		Image tacticImage = new Image(tactic.toURI().toString(), 610, 490, false, false);
		tacticField.setImage(tacticImage);
	}

	@FXML
	public void sub1Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub1.getSelectionModel().getSelectedIndex(), 11);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub2Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub2.getSelectionModel().getSelectedIndex(), 12);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub3Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub3.getSelectionModel().getSelectedIndex(), 13);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub4Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub4.getSelectionModel().getSelectedIndex(), 14);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub5Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub5.getSelectionModel().getSelectedIndex(), 15);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub6Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub6.getSelectionModel().getSelectedIndex(), 16);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub7Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub7.getSelectionModel().getSelectedIndex(), 17);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub8Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub8.getSelectionModel().getSelectedIndex(), 18);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void sub9Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), sub9.getSelectionModel().getSelectedIndex(), 19);
		gridUpdate();
		comboBoxSetup(t.getTactic());
		calibrateNames();
	}

	@FXML
	public void pl1Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl1.getSelectionModel().getSelectedIndex(), 0);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl2Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl2.getSelectionModel().getSelectedIndex(), 1);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl3Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl3.getSelectionModel().getSelectedIndex(), 2);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl4Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl4.getSelectionModel().getSelectedIndex(), 3);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl5Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl5.getSelectionModel().getSelectedIndex(), 4);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl6Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl6.getSelectionModel().getSelectedIndex(), 5);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl7Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl7.getSelectionModel().getSelectedIndex(), 6);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl8Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl8.getSelectionModel().getSelectedIndex(), 7);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl9Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl9.getSelectionModel().getSelectedIndex(), 8);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl10Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl10.getSelectionModel().getSelectedIndex(), 9);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void pl11Change() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		// Swapping
		Collections.swap(t.getPlayers(), pl11.getSelectionModel().getSelectedIndex(), 10);
		gridUpdate();
		playerBoxSetup(t.getTactic(), false);
		calibrateNames();
	}

	@FXML
	public void gridUpdate() {
		// Clear grid text fields
		for (int i = 0; i < gridPlayer.getChildren().size(); i++)
			if (gridPlayer.getChildren().get(i).getClass() == st.getClass())
				((Text) (gridPlayer.getChildren().get(i))).setText("");

		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		for (int i = 0; i < 20; i++) {
			Text playerName = new Text(t.getPlayers().get(i).getName());
			Text overall = new Text(t.getPlayers().get(i).getOverall() + "");
			Text nation = new Text(t.getPlayers().get(i).getNationality());
			Text value = new Text(t.getPlayers().get(i).getValue() / 1000000 + "m €");

			if (i < 11) {
				overall.setStyle("-fx-font-weight: bold");
				playerName.setStyle("-fx-font-weight: bold");
				nation.setStyle("-fx-font-weight: bold");
				value.setStyle("-fx-font-weight: bold");
			}

			File nationImg = new File("img/flags/" + nation.getText().toLowerCase().trim() + ".png");
			ImageView flag = new ImageView(new Image(nationImg.toURI().toString()));
			flag.setFitHeight(20);
			flag.setFitWidth(40);

			gridPlayer.add(playerName, 1, i);
			gridPlayer.add(overall, 2, i);
			gridPlayer.add(flag, 3, i);
			gridPlayer.add(value, 4, i);
		}
	}

	public void playerBoxSetup(String tactic, boolean clear) {

		if (tactic.equals("4-3-3")) {
			pl1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl10.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
			pl11.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW");
		}

		else if (tactic.equals("4-4-2")) {
			pl1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl10.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
			pl11.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF");
		}

		else {
			pl1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl10.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
			pl11.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST");
		}

		pl1.getSelectionModel().select(0);
		pl2.getSelectionModel().select(1);
		pl3.getSelectionModel().select(2);
		pl4.getSelectionModel().select(3);
		pl5.getSelectionModel().select(4);
		pl6.getSelectionModel().select(5);
		pl7.getSelectionModel().select(6);
		pl8.getSelectionModel().select(7);
		pl9.getSelectionModel().select(8);
		pl10.getSelectionModel().select(9);
		pl11.getSelectionModel().select(10);
	}

	@FXML
	public void comboBoxSetup(String tactic) {

		if (tactic.equals("4-3-3")) {
			sub1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
			sub9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "CDM", "LCM", "RCM", "RW", "ST", "LW", " ");
		}

		else if (tactic.equals("4-4-2")) {
			sub1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", "");
			sub2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", "");
			sub3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", "");
			sub4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", "");
			sub5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", "");
			sub6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", " ");
			sub7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", " ");
			sub8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", " ");
			sub9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RW", "LCM", "RCM", "LW", "RF", "LF", " ");
		} else {
			sub1.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub2.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub3.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub4.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub5.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub6.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub7.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub8.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
			sub9.getItems().setAll("GK", "RB", "RCB", "LCB", "LB", "RCM", "LCM", "RW", "CAM", "LW", "ST", " ");
		}

		sub1.getSelectionModel().select(11);
		sub2.getSelectionModel().select(11);
		sub3.getSelectionModel().select(11);
		sub4.getSelectionModel().select(11);
		sub5.getSelectionModel().select(11);
		sub6.getSelectionModel().select(11);
		sub7.getSelectionModel().select(11);
		sub8.getSelectionModel().select(11);
		sub9.getSelectionModel().select(11);

	}

	@FXML
	public void tacticBoxChange() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		t.setTactic(tacticBox.getValue());
		tacticFieldSetup();
		calibrateNames();
		gridUpdate();
		comboBoxSetup(t.getTactic());
		playerBoxSetup(t.getTactic(), true);

	}

	@FXML
	public void tempoBoxChange() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		t.setTempo(tempoBox.getValue());
		tempoBox.getSelectionModel().select(t.getTempo());
	}

	@FXML
	public void styleBoxChange() {
		Tournament current = Tournament.getInstance();
		Team t = current.getTeams()[current.getMyTeamId()];
		t.setStyle(styleBox.getValue());
	}

}
