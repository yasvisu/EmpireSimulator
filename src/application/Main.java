package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static String menu1ID = "background";
    public static String screen1File = "menu.fxml";
    public static String menu2ID = "menu";
    public static String screen2File = "gameView.fxml";
    public static String menu3ID = "up";
    public static String screen3File = "upgrades.fxml";

    

    @Override
    public void start(Stage primaryStage) {
	try {
	    primaryStage.setTitle("Empire Simulator");
	    
	    ScreenManager sm = new ScreenManager(primaryStage);
	    sm.begin();
	    
	    primaryStage.show();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
