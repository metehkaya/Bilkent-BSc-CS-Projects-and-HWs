/**
 *
 */
package controller;

/**
 * @author kerem
 *
 */
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

public class NewGameController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private RadioButton team1;
	@FXML
	private RadioButton team2;
	@FXML
	private RadioButton team3;
	@FXML
	private RadioButton team4;
	@FXML
	private RadioButton team5;
	@FXML
	private RadioButton team6;
	@FXML
	private RadioButton team7;
	@FXML
	private RadioButton team8;
	@FXML
	private RadioButton team9;
	@FXML
	private RadioButton team10;
	@FXML
	private RadioButton team11;
	@FXML
	private RadioButton team12;
	@FXML
	private RadioButton team13;
	@FXML
	private RadioButton team14;
	@FXML
	private RadioButton team15;
	@FXML
	private RadioButton team16;
	@FXML
	private RadioButton team17;
	@FXML
	private RadioButton team18;
	@FXML
	private RadioButton team19;
	@FXML
	private RadioButton team20;
	@FXML
	private RadioButton team21;
	@FXML
	private RadioButton team22;
	@FXML
	private RadioButton team23;
	@FXML
	private RadioButton team24;
	@FXML
	private RadioButton team25;
	@FXML
	private RadioButton team26;
	@FXML
	private RadioButton team27;
	@FXML
	private RadioButton team28;
	@FXML
	private RadioButton team29;
	@FXML
	private RadioButton team30;
	@FXML
	private RadioButton team31;
	@FXML
	private RadioButton team32;

	@FXML
	private TextField nameField;
	@FXML
	private TextField ageField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField weightField;
	@FXML
	private TextField nationField;

	@FXML
	private Text choose;
	@FXML
	private ToggleGroup toggleGroup;
	@FXML
	private AnchorPane blurredPane;

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
	private Button cButton;

	private Manager player;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		blurRight(true);
		team1.setToggleGroup(toggleGroup);
		team2.setToggleGroup(toggleGroup);
		team3.setToggleGroup(toggleGroup);
		team4.setToggleGroup(toggleGroup);
		team5.setToggleGroup(toggleGroup);
		team6.setToggleGroup(toggleGroup);
		team7.setToggleGroup(toggleGroup);
		team8.setToggleGroup(toggleGroup);
		team9.setToggleGroup(toggleGroup);
		team10.setToggleGroup(toggleGroup);
		team11.setToggleGroup(toggleGroup);
		team12.setToggleGroup(toggleGroup);
		team13.setToggleGroup(toggleGroup);
		team14.setToggleGroup(toggleGroup);
		team15.setToggleGroup(toggleGroup);
		team16.setToggleGroup(toggleGroup);
		team17.setToggleGroup(toggleGroup);
		team18.setToggleGroup(toggleGroup);
		team19.setToggleGroup(toggleGroup);
		team20.setToggleGroup(toggleGroup);
		team21.setToggleGroup(toggleGroup);
		team22.setToggleGroup(toggleGroup);
		team23.setToggleGroup(toggleGroup);
		team24.setToggleGroup(toggleGroup);
		team25.setToggleGroup(toggleGroup);
		team26.setToggleGroup(toggleGroup);
		team27.setToggleGroup(toggleGroup);
		team28.setToggleGroup(toggleGroup);
		team29.setToggleGroup(toggleGroup);
		team30.setToggleGroup(toggleGroup);
		team31.setToggleGroup(toggleGroup);
		team32.setToggleGroup(toggleGroup);

		Tournament cur = Tournament.getInstance();
		team1.setText(cur.getTeams()[0].getName());
		team2.setText(cur.getTeams()[1].getName());
		team3.setText(cur.getTeams()[2].getName());
		team4.setText(cur.getTeams()[3].getName());
		team5.setText(cur.getTeams()[4].getName());
		team6.setText(cur.getTeams()[5].getName());
		team7.setText(cur.getTeams()[6].getName());
		team8.setText(cur.getTeams()[7].getName());
		team9.setText(cur.getTeams()[8].getName());
		team10.setText(cur.getTeams()[9].getName());
		team11.setText(cur.getTeams()[10].getName());
		team12.setText(cur.getTeams()[11].getName());
		team13.setText(cur.getTeams()[12].getName());
		team14.setText(cur.getTeams()[13].getName());
		team15.setText(cur.getTeams()[14].getName());
		team16.setText(cur.getTeams()[15].getName());
		team17.setText(cur.getTeams()[16].getName());
		team18.setText(cur.getTeams()[17].getName());
		team19.setText(cur.getTeams()[18].getName());
		team20.setText(cur.getTeams()[19].getName());
		team21.setText(cur.getTeams()[20].getName());
		team22.setText(cur.getTeams()[21].getName());
		team23.setText(cur.getTeams()[22].getName());
		team24.setText(cur.getTeams()[23].getName());
		team25.setText(cur.getTeams()[24].getName());
		team26.setText(cur.getTeams()[25].getName());
		team27.setText(cur.getTeams()[26].getName());
		team28.setText(cur.getTeams()[27].getName());
		team29.setText(cur.getTeams()[28].getName());
		team30.setText(cur.getTeams()[29].getName());
		team31.setText(cur.getTeams()[30].getName());
		team32.setText(cur.getTeams()[31].getName());

	}

	@FXML
	public void pressedContinue() {
		String mName = nameField.getText();
		int mAge = Integer.parseInt(ageField.getText());
		int mHeight = Integer.parseInt(heightField.getText());
		int mWeight = Integer.parseInt(weightField.getText());
		String mNation = nationField.getText();
		player = new Manager(mName, mAge, mHeight, mWeight, mNation, 80, 0);
		blurRight(false);
		blurLeft(true);
	}

	@FXML
	public void pressedDone() throws IOException, ClassNotFoundException {
		String teamSelected = ((RadioButton) ((toggleGroup.getSelectedToggle()))).getText();
		Tournament.setInstance(Database.newGameLoad());
		Tournament t = Tournament.getInstance();
		int id = -1;
		for (int i = 0; i < 32; i++) {
			if (t.getTeams()[i].getName().equals(teamSelected)) {
				t.getTeams()[i].setManager(player);
				id = i;
				break;
			}
		}
		t.setMyTeamId(id);
		t.distributeTeams();
		// t.chooseMyGroupId();
		TeamController.setCurrentTeamId(id);
		Parent root = FXMLLoader.load(getClass().getResource("/view/TacticView.fxml"));
		root.setScaleX(screenWidth/1400.0);
		root.setScaleY(screenHeight/900.0);
		if (Main.isWindows()) {
			root.setLayoutX(320);
			root.setLayoutY(108);
		}
		if (Main.isMacos()) {
			root.setLayoutX(20);
		}
		Stage m = Main.getMainStage();
		Scene ts = Main.getMainStage().getScene();
		ts.setRoot(root);
		m.setScene(ts);
		m.setFullScreen(true);
		Main.setMainStage(m);
	}

	public void blurLeft(boolean blurred) {
		ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
		GaussianBlur blur = new GaussianBlur(35); // 55 is just to show edge
													// effect more clearly.
		adj.setInput(blur);
		if (!blurred)
			adj = null;
		t1.setEffect(adj);
		t2.setEffect(adj);
		t3.setEffect(adj);
		t4.setEffect(adj);
		t5.setEffect(adj);
		t6.setEffect(adj);
		nameField.setEffect(adj);
		ageField.setEffect(adj);
		heightField.setEffect(adj);
		weightField.setEffect(adj);
		nationField.setEffect(adj);
		cButton.setEffect(adj);

	}

	public void blurRight(boolean blurred) {
		ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
		GaussianBlur blur = new GaussianBlur(35); // 55 is just to show edge
													// effect more clearly.
		adj.setInput(blur);
		if (!blurred)
			adj = null;
		choose.setEffect(adj);
		team1.setEffect(adj);
		team2.setEffect(adj);
		team3.setEffect(adj);
		team4.setEffect(adj);
		team5.setEffect(adj);
		team6.setEffect(adj);
		team7.setEffect(adj);
		team8.setEffect(adj);
		team9.setEffect(adj);
		team10.setEffect(adj);
		team11.setEffect(adj);
		team12.setEffect(adj);
		team13.setEffect(adj);
		team14.setEffect(adj);
		team15.setEffect(adj);
		team16.setEffect(adj);
		team17.setEffect(adj);
		team18.setEffect(adj);
		team19.setEffect(adj);
		team20.setEffect(adj);
		team21.setEffect(adj);
		team22.setEffect(adj);
		team23.setEffect(adj);
		team24.setEffect(adj);
		team25.setEffect(adj);
		team26.setEffect(adj);
		team27.setEffect(adj);
		team28.setEffect(adj);
		team29.setEffect(adj);
		team30.setEffect(adj);
		team31.setEffect(adj);
		team32.setEffect(adj);
	}

}