package models.guiModels;

import application.UIController;
import javafx.scene.Scene;

public class FXMLBundle {
    public Scene scene;
    public UIController controller;

    public FXMLBundle(Scene scene, UIController controller) {
	this.setScene(scene);
	this.setController(controller);
    }

    public Scene getScene() {
	return this.scene;
    }

    private void setScene(Scene scene) {
	if (scene == null) {
	    throw new IllegalArgumentException("Scene cannot be null.");
	}

	this.scene = scene;
    }

    public UIController getController() {
	return this.controller;
    }

    private void setController(UIController controller) {
	this.controller = controller;
    }

}
