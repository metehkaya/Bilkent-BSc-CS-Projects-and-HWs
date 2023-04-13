package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.*;
import java.time.*;

public class CalendarController implements Initializable {

	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
	int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

	@FXML
	private GridPane gridCalendar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Tournament tour = Tournament.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate;
			startDate = formatter.parse("2018-10-29");
			Date endDate = formatter.parse("2019-05-27");
			String cur = "" + Tournament.getInstance().getCurrentYear() + "-" + Tournament.getInstance().getCurrentMonth() + "-" + Tournament.getInstance().getCurrentDay();
			Date todayDate = formatter.parse(cur);
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			// Get today
			todayDate = formatter.parse(tour.getCurrentYear() + "-" + tour.getCurrentMonth() + "-" + tour.getCurrentDay());
			Calendar today = Calendar.getInstance();
			today.setTime(todayDate);
			int rowInd = 0;
			int colInd = 0;

			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

				//Shorten the date and change the font
				Text t = new Text(date.toString().substring(4, 10));
				t.setFont(Font.font("Gill Sans", FontWeight.BOLD, 15));
				t.setFill(Color.BLACK);

				//Variables to test the existence of the match
				boolean matchVar = false;
				Team opponent = null;
				String currentDay = formatter.format(date);
				for (int i = 0; i < 12 && !matchVar; i++) {
					String matchDay = Tournament.getGroupMatchYears()[i] + "-" + Tournament.getGroupMatchMonths()[i] + "-";
					if (Tournament.getGroupMatchDays()[i] < 10)
						matchDay += "0";
					matchDay += "" + Tournament.getGroupMatchDays()[i];
					if (currentDay.equals(matchDay)) {
						Match m = tour.getGroups()[tour.getMyGroupId()].getMatches()[i];
						// Opponent is away
						if (m.getHome().getName().equals(tour.getTeams()[tour.getMyTeamId()].getName())) {
							opponent = m.getAway();
							matchVar = true;
						}
						// Opponent is home
						else if (m.getAway().getName().equals(tour.getTeams()[tour.getMyTeamId()].getName())) {
							opponent = m.getHome();
							matchVar = true;
						}
					}
				}
				if( tour.getStatusEliminationStage() ) {
					for( int i = 0 ; i < tour.getKnockout().getNUMBER_OF_MATCHES() && !matchVar ; i++ ) {
						String matchDay = tour.getKnockout().getEliminationMatchYears()[i] + "-";
						if(tour.getKnockout().getEliminationMatchMonths()[i] < 10)
							matchDay += "0";
						matchDay += "" + tour.getKnockout().getEliminationMatchMonths()[i] + "-";
						if(tour.getKnockout().getEliminationMatchDays()[i] < 10)
							matchDay += "0";
						matchDay += "" + tour.getKnockout().getEliminationMatchDays()[i];
						if(currentDay.equals(matchDay)) {
							Match m = tour.getKnockout().getKnockout().getMatches()[i];
							if(m.getHome() == null || m.getAway() == null)
								break;
							// Opponent is away
							if (m.getHome().getName().equals(tour.getTeams()[tour.getMyTeamId()].getName())) {
								opponent = m.getAway();
								matchVar = true;
							}
							// Opponent is home
							else if (m.getAway().getName().equals(tour.getTeams()[tour.getMyTeamId()].getName())) {
								opponent = m.getHome();
								matchVar = true;
							}
						}
					}
				}
				if (matchVar) {
					String st = opponent.getName().toLowerCase().trim();
					st = st.replaceAll("\\s+", "");
					File file = new File("img/logos/" + st + ".png");
					Image image = new Image(file.toURI().toString());
					ImageView im = new ImageView(image);
					im.setFitWidth(40);
					im.setFitHeight(40);
					gridCalendar.add(im, rowInd++, colInd);
				}
				else {
					gridCalendar.add(t, rowInd++, colInd);
				}
				///////////////////////////////////////
				if (rowInd == 7) {
					rowInd = 0;
					colInd++;
				}
				if (start.before(today)) {
					if (gridCalendar.getChildren().get(colInd * 7 + rowInd).getClass().getName().equals("javafx.scene.text.Text")) {
						GaussianBlur blur = new GaussianBlur(25);
						t.setEffect(blur);
					}
					else if (gridCalendar.getChildren().get(colInd * 7 + rowInd).getClass().getName().equals("javafx.scene.image.ImageView")) {
						GaussianBlur blur = new GaussianBlur(25);
						gridCalendar.getChildren().get(colInd * 7 + rowInd).setEffect(blur);
					}
				}

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Is game over ?
		tour.checkChampionshipFailed();
		if (tour.isChampionshipFailed()) {
			try {
				goView("/view/Eliminated.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			int x = 0 / 0;
		}

		// Is champion ?
		Team champ = tour.getKnockout().getKnockout().getTeams()[0];
		if (champ != null && champ.getName().equals(tour.getTeams()[tour.getMyTeamId()].getName())) {
			try {
				goView("/view/Champion.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			int x = 0 / 0;
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

}
