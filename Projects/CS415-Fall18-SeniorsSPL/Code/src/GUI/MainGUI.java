package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
	public static Stage parentWindow;
	public static int input;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login_panel.fxml"));
        primaryStage.setTitle("CS415 Course Project : Seniors");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        parentWindow = primaryStage;
    }


    public static void main(String[] args) {
    	input = Integer.parseInt(args[0]);
        launch(args);
    }
}