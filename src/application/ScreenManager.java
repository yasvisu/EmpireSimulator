package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.guiModels.FXMLBundle;
import utils.GUIConstants;

public class ScreenManager {

    private Stage primaryStage;

    private BorderPane mainPane;
    private HashMap<String, FXMLBundle> menuBundleMap;

    public ScreenManager(Stage primaryStage) {
	this.primaryStage = primaryStage;
	this.menuBundleMap = new HashMap<String, FXMLBundle>();
	this.loadScreenResources();
    }
    
    public void begin(){
	this.setScene("IntroMenu");
    }

    private void loadScreenResources() {
	FXMLLoader pageLoader;
	try {
	    // IntroMenu
	    pageLoader = new FXMLLoader(Main.class.getResource(GUIConstants.introMenuFxml));
	    this.mainPane = (BorderPane) pageLoader.load();
	    UIController currentController = pageLoader.getController();
	    
	    this.menuBundleMap.put("IntroMenu", new FXMLBundle(new Scene(mainPane), currentController));

	    // Other menus
	    // ...

	} catch (IOException ioe) {
	    // TODO: Log or wrap and re-throw exception
	}
    }

    private void setScene(String sceneName) throws IllegalArgumentException {
	if (!this.menuBundleMap.containsKey(sceneName)) {
	    throw new IllegalArgumentException("No such scene");
	}

	this.primaryStage.setScene(menuBundleMap.get(sceneName).getScene());
    }
}
