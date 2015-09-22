package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("menu.fxml"));
			primaryStage.setScene(new Scene(parent));
			primaryStage.show();



			//BorderPane root = new BorderPane();
			//Button battleButtonSample = new Button("Go to battle simulator");
			//battleButtonSample.setOnAction(e -> new BattlesSimulator().display());
			//root.setCenter(battleButtonSample);

			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
