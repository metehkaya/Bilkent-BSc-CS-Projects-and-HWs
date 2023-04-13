/**
 *
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import model.*;

public class MatchPlayController implements Initializable {

	@FXML
	private Text hgk;

	@FXML
	private Text hrb;

	@FXML
	private Text hlb;

	@FXML
	private Text hcb1;

	@FXML
	private Text hcb2;

	@FXML
	private Text hcm1;

	@FXML
	private Text hcm2;

	@FXML
	private Text hcm3;

	@FXML
	private Text hlw;

	@FXML
	private Text hrw;

	@FXML
	private Text hst;

	@FXML
	private Text agk;

	@FXML
	private Text arb;

	@FXML
	private Text alb;

	@FXML
	private Text acb1;

	@FXML
	private Text acb2;

	@FXML
	private Text acm1;

	@FXML
	private Text acm2;

	@FXML
	private Text acm3;

	@FXML
	private Text alw;

	@FXML
	private Text arw;

	@FXML
	private Text ast;

	@FXML
	private Text homeName;
	@FXML
	private Text awayName;

	@FXML
	private Text scoreHome;
	@FXML
	private Text scoreAway;

	@FXML
	private GridPane eventGrid;
	@FXML
	private ScrollPane scrollEvent;
	@FXML
	private Text timer;
	@FXML
	private ImageView homeLogo;
	@FXML
	private ImageView awayLogo;

	@FXML
	private ImageView htacticField;
	@FXML
	private ImageView atacticField;

	public static Match currentMatch;
	public static Integer currentMatchType;

	private boolean paused;

	private int actionCount = 0;

	private Integer seconds = 0;

	private Tournament t;

	@FXML
	private Label label;

	@FXML
	private ComboBox<String> homeTactic;
	@FXML
	private ComboBox<String> awayTactic;
	@FXML
	private ComboBox<String> homeStyle;
	@FXML
	private ComboBox<String> awayStyle;
	@FXML
	private ComboBox<String> homeTempo;
	@FXML
	private ComboBox<String> awayTempo;

	@FXML
	private Slider speedSlider;

	@FXML
	private ComboBox<String> homeOld;
	@FXML
	private ComboBox<String> homeNew;
	@FXML
	private ComboBox<String> awayOld;
	@FXML
	private ComboBox<String> awayNew;
	@FXML
	private Button homeChanged;
	@FXML
	private Button awayChanged;

	@FXML
	private Text homeSubCount;
	@FXML
	private Text awaySubCount;

	@FXML
	private Rectangle hTactics;
	@FXML
	private Rectangle aTactics;

	@FXML
	private Text hSubText;
	@FXML
	private Text aSubText;

	@FXML
	private Text homeInstructionText;
	@FXML
	private Text awayInstructionText;

	@FXML
	private Button matchFinish;

	private static int subCountHome = 3;

	private static int subCountAway = 3;

	public static boolean started = false;

	public MediaPlayer musicplayer;

	private static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	private static int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	public void homeChanged() {
		int swapped1 = currentMatch.getHome().indexOfPlayer(homeOld.getValue().split("-")[1]);
		int swapped2 = currentMatch.getHome().indexOfPlayer(homeNew.getValue().split("-")[1]);
		// Swapping
		Collections.swap(currentMatch.getHome().getPlayers(), swapped1, swapped2);
		calibrateNamesHome();
		if (swapped2 > 10) {
			subCountHome--;
			homeSubCount.setText("" + subCountHome);
		}
		if (subCountHome == 0) {
			homeOld.setVisible(false);
			homeNew.setVisible(false);
			homeChanged.setVisible(false);
		}
		fillSubstituteHome();

	}

	@FXML
	public void awayChanged() {
		int swapped1 = currentMatch.getAway().indexOfPlayer(awayOld.getValue().split("-")[1]);
		int swapped2 = currentMatch.getAway().indexOfPlayer(awayNew.getValue().split("-")[1]);
		// Swapping
		Collections.swap(currentMatch.getAway().getPlayers(), swapped1, swapped2);
		calibrateNamesAway();
		if (swapped2 > 10) {
			subCountAway--;
			awaySubCount.setText("" + subCountAway);
		}
		if (subCountHome == 0) {
			awayOld.setVisible(false);
			awayNew.setVisible(false);
			awayChanged.setVisible(false);
		}
		fillSubstituteAway();
	}

	public void fillSubstituteHome() {
		homeOld.getItems().clear();
		homeNew.getItems().clear();
		for (int i = 0; i < 11; i++) {
			homeOld.getItems()
					.add(currentMatch.getHome().getPlayers().get(i).getPosition() + "-"
							+ currentMatch.getHome().getPlayers().get(i).getName() + "-"
							+ currentMatch.getHome().getPlayers().get(i).getOverall());
		}
		for (int i = 0; i < 20; i++) {
			homeNew.getItems()
					.add(currentMatch.getHome().getPlayers().get(i).getPosition() + "-"
							+ currentMatch.getHome().getPlayers().get(i).getName() + "-"
							+ currentMatch.getHome().getPlayers().get(i).getOverall());
		}
	}

	public void fillSubstituteAway() {
		awayOld.getItems().clear();
		awayNew.getItems().clear();
		for (int i = 0; i < 11; i++) {
			awayOld.getItems()
					.add(currentMatch.getAway().getPlayers().get(i).getPosition() + "-"
							+ currentMatch.getAway().getPlayers().get(i).getName() + "-"
							+ currentMatch.getAway().getPlayers().get(i).getOverall());
		}
		for (int i = 0; i < 20; i++) {
			awayNew.getItems()
					.add(currentMatch.getAway().getPlayers().get(i).getPosition() + "-"
							+ currentMatch.getAway().getPlayers().get(i).getName() + "-"
							+ currentMatch.getAway().getPlayers().get(i).getOverall());
		}
	}

	ArrayList<Action> actions;

	private double speed = 1;

	public static void fillColorText(Text t, String color) {
		if (color.equals("red")) {
			t.setFill(Color.DARKRED);
		} else if (color.equals("black")) {
			t.setFill(Color.BLACK);
		} else if (color.equals("blue")) {
			t.setFill(Color.DARKBLUE);
		} else if (color.equals("yellow")) {
			t.setFill(Color.YELLOW);
		} else if (color.equals("purple")) {
			t.setFill(Color.PURPLE);
		} else if (color.equals("green")) {
			t.setFill(Color.DARKGREEN);
		}
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		{
			String filePath = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/match.wav";
			filePath = filePath.replace("\\", "/");
			Media mp3MusicFile = new Media(filePath);
			musicplayer = new MediaPlayer(mp3MusicFile);
			musicplayer.setAutoPlay(true);
			musicplayer.setVolume(0.5);
			musicplayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					musicplayer.seek(Duration.ZERO);
				}
			});
		}

		t = Tournament.getInstance();
		paused = false;
		label.setTextFill(Color.BLACK);

		Main.pauseMusic();
		Team home = currentMatch.getHome();
		Team away = currentMatch.getAway();

		homeName.setText(home.getName());
		awayName.setText(away.getName());

		homeName.setFont(Font.font("Gill Sans", FontWeight.BOLD, 38));
		fillColorText(homeName, home.getColor());
		awayName.setFont(Font.font("Gill Sans", FontWeight.BOLD, 38));
		fillColorText(awayName, away.getColor());

		homeTactic.getSelectionModel().select(home.getTactic());
		homeStyle.getSelectionModel().select(home.getStyle());
		homeTempo.getSelectionModel().select(home.getTempo());

		awayTactic.getSelectionModel().select(away.getTactic());
		awayStyle.getSelectionModel().select(away.getStyle());
		awayTempo.getSelectionModel().select(away.getTempo());


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

		if (home.getName().equals(t.getTeams()[t.getMyTeamId()].getName())) {
			homeTactic.getItems().addAll("4-3-3", "4-4-2", "4-2-3-1");
			homeStyle.getItems().addAll("Attack", "Defensive", "Holding");
			homeTempo.getItems().addAll("Fast", "Normal", "Slow");
			homeTactic.getSelectionModel().select(home.getTactic());
			homeStyle.getSelectionModel().select(home.getStyle());
			homeTempo.getSelectionModel().select(home.getTempo());
			awayTactic.setVisible(false);
			awayStyle.setVisible(false);
			awayTempo.setVisible(false);
			awayOld.setVisible(false);
			awayNew.setVisible(false);
			awayChanged.setVisible(false);
			aTactics.setVisible(false);
			aSubText.setVisible(false);
			awayInstructionText.setVisible(false);
			awaySubCount.setVisible(false);
		}

		else {
			awayTactic.getItems().addAll("4-3-3", "4-4-2", "4-2-3-1");
			awayStyle.getItems().addAll("Attack", "Defensive", "Holding");
			awayTempo.getItems().addAll("Fast", "Normal", "Slow");
			awayTactic.getSelectionModel().select(away.getTactic());
			awayStyle.getSelectionModel().select(away.getStyle());
			awayTempo.getSelectionModel().select(away.getTempo());
			homeTactic.setVisible(false);
			homeStyle.setVisible(false);
			homeTempo.setVisible(false);
			homeOld.setVisible(false);
			homeNew.setVisible(false);
			homeChanged.setVisible(false);
			hTactics.setVisible(false);
			hSubText.setVisible(false);
			homeInstructionText.setVisible(false);
			homeSubCount.setVisible(false);

		}

		File tactic = new File("img/tactics/" + home.getTactic() + "_" + home.getColor() + ".png");
		Image tacticImage = new Image(tactic.toURI().toString(), 456, 454, false, false);
		htacticField.setImage(tacticImage);

		File tactic2 = new File("img/tactics/" + away.getTactic() + "_" + away.getColor() + ".png");
		Image tacticImage2 = new Image(tactic2.toURI().toString(), 456, 454, false, false);
		atacticField.setImage(tacticImage2);
		calibrateNamesHome();
		calibrateNamesAway();
		/*
		speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				doTime();
			}
		});*/

		double ratio = speedSlider.getValue()/100;

		fillSubstituteHome();
		fillSubstituteAway();
		String path = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/start.wav";
		path = path.replace("\\", "/");
		AudioClip start = new AudioClip(path);
		start.play();
		doTime(ratio);
		matchFinish.setVisible(false);
	}

	@FXML
	public void matchDone() throws IOException {
		Main.playMusic();
		if (!Tournament.getInstance().getStatusEliminationStage())
			goView("/view/GroupView.fxml");
		else
			goView("/view/KnockoutView.fxml");
	}

	public boolean checkExtensionStop() {
		if( currentMatchType == 0 )
			return true;
		int matchId = t.getKnockout().getMatchId(currentMatch.getDay(), currentMatch.getMonth(),
				currentMatch.getYear());
		if(matchId % 2 == 1)
			return true;
		if( matchId == 0 ) {
			if( currentMatch.getGoalHome() == currentMatch.getGoalAway() )
				return false;
			return true;
		}
		Match prev = t.getKnockout().getKnockout().getMatches()[matchId-1];
		if( currentMatch.getGoalHome() == prev.getGoalHome() && currentMatch.getGoalAway() == prev.getGoalAway() )
			return false;
		return true;
	}

	public void doTime(double ratio) {
		int matchDuration = 89 + (int) (Math.random() * 6);
		currentMatch.setGoalHome(0);
		currentMatch.setGoalAway(0);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				actions = new ArrayList<Action>();
				if (!paused) {
					seconds++;
					label.setText(seconds.toString() + "'");
					Action a = currentMatch.actionGenerator(seconds);
					if (a != null) {
						actions.add(a);
					}
					if (actions != null)
						updateActionView();
					if (a != null && a.getClass().getSimpleName().equals("Goal")) {
						String filePath = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/goal.wav";
						filePath = filePath.replace("\\", "/");
						AudioClip goal = new AudioClip(filePath);
						goal.play();
					}
					else if (a != null && (a.getClass().getSimpleName().equals("RedCard"))) {
						String filePath = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/red.wav";
						filePath = filePath.replace("\\", "/");
						AudioClip red = new AudioClip(filePath);
						red.play();
						Tournament tc = Tournament.getInstance();
						if (currentMatch.getHome().getName().equals(tc.getTeams()[tc.getMyTeamId()].getName()) & currentMatch.getHome().contains(((RedCard)(a)).getPlayer())
								|| currentMatch.getAway().getName().equals(tc.getTeams()[tc.getMyTeamId()].getName()) & currentMatch.getAway().contains(((RedCard)(a)).getPlayer())) {
							paused = true;
						}
					}
					else if (a != null && (a.getClass().getSimpleName().equals("Injury"))) {
						String filePath = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/red.wav";
						filePath = filePath.replace("\\", "/");
						AudioClip red = new AudioClip(filePath);
						red.play();
						Tournament tc = Tournament.getInstance();
						if (currentMatch.getHome().getName().equals(tc.getTeams()[tc.getMyTeamId()].getName()) & currentMatch.getHome().contains(((Injury)(a)).getInjured())
								|| currentMatch.getAway().getName().equals(tc.getTeams()[tc.getMyTeamId()].getName()) & currentMatch.getAway().contains(((Injury)(a)).getInjured())) {
							paused = true;
						}
					}

					if (seconds > matchDuration && checkExtensionStop()) {
						String filePath = "file:///" + new java.io.File("").getAbsolutePath() + "/data/sounds/end.wav";
						filePath = filePath.replace("\\", "/");
						AudioClip end = new AudioClip(filePath);
						end.play();
						musicplayer.pause();
						timeline.stop();
						matchFinish.setVisible(true);
						int pointHome = -1;
						int pointAway = -1;
						if (currentMatch.getGoalHome() > currentMatch.getGoalAway()) {
							pointHome = 3;
							pointAway = 0;
						} else if (currentMatch.getGoalHome() < currentMatch.getGoalAway()) {
							pointHome = 0;
							pointAway = 3;
						} else {
							pointHome = 1;
							pointAway = 1;
						}
						currentMatch.setPointHome(pointHome);
						currentMatch.setPointAway(pointAway);
						if (currentMatchType == 0) {
							t.getGroups()[t.getMyGroupId()].modifyGroupStatistics(currentMatch);

						} else if (currentMatchType == 1) {
							try {
								t.getKnockout().playMatch(t.getLastMatchId(), true);
								int matchId = t.getKnockout().getMatchId(currentMatch.getDay(), currentMatch.getMonth(),
										currentMatch.getYear());
								if (matchId % 2 == 0) {
									if (t.getKnockout().getKnockout().getTeams()[matchId / 2] != t.getMyTeam())
										t.setChampionshipFailed(true);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}

		});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();

	}

	public void updateActionView() {
		for (int i = 0; i < actions.size(); i++) {
			Text action = new Text(actions.get(i).toString());
			action.setFont(Font.font("Gill Sans", FontWeight.SEMI_BOLD, 15));
			eventGrid.add(action, 1, actionCount++);
			String actionName = actions.get(i).getClass().getName().toLowerCase().substring(6);
			File nationImg = new File("img/actions/" + actionName + ".png");
			ImageView act = new ImageView(new Image(nationImg.toURI().toString()));
			act.setFitHeight(27);
			act.setFitWidth(25);
			eventGrid.add(act, 0, actionCount - 1);
			if (actionName.equals("goal")) {
				if (currentMatch.getHome().contains(((Goal) (actions.get(i))).getScored()))
					scoreHome.setText(String.valueOf(Integer.parseInt(scoreHome.getText()) + 1));
				else
					scoreAway.setText(String.valueOf(Integer.parseInt(scoreAway.getText()) + 1));
			}
		}
	}

	@FXML
	public void homeTacticChanged() {
		Team h = currentMatch.getHome();
		h.setTactic(homeTactic.getValue());
		calibrateNamesHome();

		File tactic = new File(
				"img/tactics/" + currentMatch.getHome().getTactic() + "_" + currentMatch.getHome().getColor() + ".png");
		Image tacticImage = new Image(tactic.toURI().toString(), 456, 454, false, false);
		htacticField.setImage(tacticImage);
	}

	@FXML
	public void awayTacticChanged() {
		Team a = currentMatch.getAway();
		a.setTactic(awayTactic.getValue());
		calibrateNamesAway();

		File tactic = new File(
				"img/tactics/" + currentMatch.getAway().getTactic() + "_" + currentMatch.getAway().getColor() + ".png");
		Image tacticImage = new Image(tactic.toURI().toString(), 456, 454, false, false);
		atacticField.setImage(tacticImage);
	}

	@FXML
	public void homeTempoChanged() {
		Team h = currentMatch.getHome();
		h.setTempo(homeTempo.getValue());
	}

	@FXML
	public void awayTempoChanged() {
		Team a = currentMatch.getAway();
		a.setTempo(awayTempo.getValue());
	}

	@FXML
	public void homeStyleChanged() {
		Team h = currentMatch.getHome();
		h.setStyle(homeStyle.getValue());
	}

	@FXML
	public void awayStyleChanged() {
		Team a = currentMatch.getHome();
		a.setStyle(awayStyle.getValue());
	}

	@FXML
	public void calibrateNamesHome() {
		Tournament current = Tournament.getInstance();
		int homeID = -1;
		for (int i = 0; i < current.getTeams().length; i++) {
			if (current.getTeams()[i].getName().equals(currentMatch.getHome().getName())) {
				homeID = i;
				break;
			}
		}
		Team t = current.getTeams()[homeID];
		// Names of the players as Text
		hgk.setText(t.getPlayers().get(0).getName());
		hrb.setText(t.getPlayers().get(1).getName());
		hcb1.setText(t.getPlayers().get(2).getName());
		hcb2.setText(t.getPlayers().get(3).getName());
		hlb.setText(t.getPlayers().get(4).getName());
		hcm1.setText(t.getPlayers().get(5).getName());
		hcm3.setText(t.getPlayers().get(6).getName());
		hcm2.setText(t.getPlayers().get(7).getName());
		hrw.setText(t.getPlayers().get(8).getName());
		hst.setText(t.getPlayers().get(9).getName());
		hlw.setText(t.getPlayers().get(10).getName());

		if (t.getTactic().equals("4-3-3")) {
			hgk.setLayoutX(200);
			hgk.setLayoutY(96);

			hrb.setLayoutX(320);
			hrb.setLayoutY(370);

			hcb2.setLayoutX(150);
			hcb2.setLayoutY(390);

			hcb1.setLayoutX(240);
			hcb1.setLayoutY(390);

			hlb.setLayoutX(60);
			hlb.setLayoutY(370);

			// RW
			hcm1.setLayoutX(200);
			hcm1.setLayoutY(300);

			// Stays as midfielders
			hcm2.setLayoutX(260);
			hcm2.setLayoutY(235);

			hcm3.setLayoutX(150);
			hcm3.setLayoutY(235);

			// becomes LW
			hrw.setLayoutX(310);
			hrw.setLayoutY(170);

			// LW becomes LF
			hlw.setLayoutX(90);
			hlw.setLayoutY(170);

			// ST becomes RF
			hst.setLayoutX(200);
			hst.setLayoutY(110);
		}

		else if (t.getTactic().equals("4-4-2")) {
			hgk.setLayoutX(200);
			hgk.setLayoutY(101);

			hrb.setLayoutX(320);
			hrb.setLayoutY(370);

			hcb2.setLayoutX(150);
			hcb2.setLayoutY(390);

			hcb1.setLayoutX(240);
			hcb1.setLayoutY(390);

			hlb.setLayoutX(64);
			hlb.setLayoutY(369);

			// RW
			hcm1.setLayoutX(310);
			hcm1.setLayoutY(225);

			// RCM
			hcm2.setLayoutX(260);
			hcm2.setLayoutY(270);

			// LCM
			hcm3.setLayoutX(150);
			hcm3.setLayoutY(270);

			// LW
			hrw.setLayoutX(90);
			hrw.setLayoutY(225);

			hlw.setLayoutX(160);
			hlw.setLayoutY(125);

			hst.setLayoutX(230);
			hst.setLayoutY(125);
		} else if (t.getTactic().equals("4-2-3-1")) {
			hgk.setLayoutX(200);
			hgk.setLayoutY(96);

			hrb.setLayoutX(320);
			hrb.setLayoutY(379);

			hcb2.setLayoutX(160);
			hcb2.setLayoutY(390);

			hcb1.setLayoutX(250);
			hcb1.setLayoutY(390);

			hlb.setLayoutX(64);
			hlb.setLayoutY(370);

			// RCM
			hcm1.setLayoutX(260);
			hcm1.setLayoutY(285);

			hcm2.setLayoutX(310);
			hcm2.setLayoutY(200);

			// LCM
			hcm3.setLayoutX(140);
			hcm3.setLayoutY(285);

			hrw.setLayoutX(200);
			hrw.setLayoutY(220);

			hlw.setLayoutX(200);
			hlw.setLayoutY(115);

			hst.setLayoutX(100);
			hst.setLayoutY(200);
		}
	}

	@FXML
	public void calibrateNamesAway() {
		Tournament current = Tournament.getInstance();
		int awayID = -1;
		for (int i = 0; i < current.getTeams().length; i++) {
			if (current.getTeams()[i].getName().equals(currentMatch.getAway().getName())) {
				awayID = i;
				break;
			}
		}
		Team t = current.getTeams()[awayID];
		// Names of the players as Text
		agk.setText(t.getPlayers().get(0).getName());
		arb.setText(t.getPlayers().get(1).getName());
		acb1.setText(t.getPlayers().get(2).getName());
		acb2.setText(t.getPlayers().get(3).getName());
		alb.setText(t.getPlayers().get(4).getName());
		acm1.setText(t.getPlayers().get(5).getName());
		acm3.setText(t.getPlayers().get(6).getName());
		acm2.setText(t.getPlayers().get(7).getName());
		arw.setText(t.getPlayers().get(8).getName());
		ast.setText(t.getPlayers().get(9).getName());
		alw.setText(t.getPlayers().get(10).getName());

		if (t.getTactic().equals("4-3-3")) {
			agk.setLayoutX(200);
			agk.setLayoutY(96);

			arb.setLayoutX(320);
			arb.setLayoutY(370);

			acb2.setLayoutX(150);
			acb2.setLayoutY(390);

			acb1.setLayoutX(240);
			acb1.setLayoutY(390);

			alb.setLayoutX(60);
			alb.setLayoutY(370);

			// RW
			acm1.setLayoutX(200);
			acm1.setLayoutY(300);

			// Stays as midfielders
			acm2.setLayoutX(260);
			acm2.setLayoutY(235);

			acm3.setLayoutX(150);
			acm3.setLayoutY(235);

			// becomes LW
			arw.setLayoutX(310);
			arw.setLayoutY(170);

			// LW becomes LF
			alw.setLayoutX(90);
			alw.setLayoutY(170);

			// ST becomes RF
			ast.setLayoutX(200);
			ast.setLayoutY(110);
		}

		else if (t.getTactic().equals("4-4-2")) {
			agk.setLayoutX(200);
			agk.setLayoutY(101);

			arb.setLayoutX(320);
			arb.setLayoutY(370);

			acb2.setLayoutX(150);
			acb2.setLayoutY(390);

			acb1.setLayoutX(240);
			acb1.setLayoutY(390);

			alb.setLayoutX(64);
			alb.setLayoutY(369);

			// RW
			acm1.setLayoutX(310);
			acm1.setLayoutY(225);

			// RCM
			acm2.setLayoutX(260);
			acm2.setLayoutY(270);

			// LCM
			acm3.setLayoutX(150);
			acm3.setLayoutY(270);

			// LW
			arw.setLayoutX(90);
			arw.setLayoutY(225);

			alw.setLayoutX(160);
			alw.setLayoutY(125);

			ast.setLayoutX(230);
			ast.setLayoutY(125);
		} else if (t.getTactic().equals("4-2-3-1")) {
			agk.setLayoutX(200);
			agk.setLayoutY(96);

			arb.setLayoutX(320);
			arb.setLayoutY(379);

			acb2.setLayoutX(160);
			acb2.setLayoutY(390);

			acb1.setLayoutX(250);
			acb1.setLayoutY(390);

			alb.setLayoutX(64);
			alb.setLayoutY(370);

			// RCM
			acm1.setLayoutX(260);
			acm1.setLayoutY(285);

			acm2.setLayoutX(310);
			acm2.setLayoutY(200);

			// LCM
			acm3.setLayoutX(140);
			acm3.setLayoutY(285);

			arw.setLayoutX(200);
			arw.setLayoutY(220);

			alw.setLayoutX(200);
			alw.setLayoutY(115);

			ast.setLayoutX(100);
			ast.setLayoutY(200);
		}
	}

	@FXML
	public void pauseClicked() {
		paused = true;
	}

	@FXML
	public void playClicked() {
		paused = false;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Match getCurrentMatch() {
		return currentMatch;
	}

	public void setCurrentMatch(Match currentMatch2) {
		currentMatch = currentMatch2;
	}

}
