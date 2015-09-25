package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.guiModels.FXMLBundle;
import ui.fxmlbundles.UIController;
import utils.GUIConstants;

public class ScreenManager {

    private Stage primaryStage;

    private BorderPane mainPane;
    private HashMap<String, FXMLBundle> menuBundleMap;

    public ScreenManager(Stage primaryStage) {
	this.primaryStage = primaryStage;
	this.menuBundleMap = new HashMap<String, FXMLBundle>();
    }

    public void begin() {
	this.loadScreenResources();
	this.primaryStage.setScene(new Scene(this.mainPane));
	this.setLayout("IntroMenu");
	this.primaryStage.show();
    }

    private void loadScreenResources() {
	FXMLLoader pageLoader;
	UIController currentController;
	Node currentNode;

	try {
	    // MainPane
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.mainPaneFxml));
	    this.mainPane = (BorderPane) pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("MainPane", new FXMLBundle(mainPane, currentController));

	    // IntroMenu
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.introMenuFxml));
	    currentNode = pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("IntroMenu", new FXMLBundle(currentNode, currentController));

	    // AboutMenu
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.aboutMenuFxml));
	    currentNode = pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("AboutMenu", new FXMLBundle(currentNode, currentController));

	    // CreditsMenu
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.creditsMenuFxml));
	    currentNode = pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("CreditsMenu", new FXMLBundle(currentNode, currentController));
	    
	    // OptionsMenu
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.optionsMenuFxml));
	    currentNode = pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("OptionsMenu", new FXMLBundle(currentNode, currentController));
	    
	    // GameMenu
	    pageLoader = new FXMLLoader(UIController.class.getResource(GUIConstants.gameMenuFxml));
	    currentNode = pageLoader.load();
	    currentController = pageLoader.getController();
	    currentController.setScreenManager(this);

	    this.menuBundleMap.put("GameMenu", new FXMLBundle(currentNode, currentController));
	    
	    // Other menus
	    // ...

	} catch (IOException ioe) {
	    // TODO: Log or wrap and re-throw exception
	}
    }

    public void setLayout(String nodeName) throws IllegalArgumentException {
	if (!this.menuBundleMap.containsKey(nodeName)) {
	    throw new IllegalArgumentException("No such scene: " + nodeName);
	}

	this.mainPane.setCenter(menuBundleMap.get(nodeName).getNode());
    }
}
