package models.guiModels;

import javafx.scene.Node;
import ui.fxmlbundles.UIController;

public class FXMLBundle {
    private Node node;
    private UIController controller;

    public FXMLBundle(Node node, UIController controller) {
	this.setNode(node);
	this.setController(controller);
    }

    public Node getNode() {
	return this.node;
    }

    private void setNode(Node node) {
	if (node == null) {
	    throw new IllegalArgumentException("Node cannot be null.");
	}

	this.node = node;
    }

    public UIController getController() {
	return this.controller;
    }

    private void setController(UIController controller) {
	this.controller = controller;
    }

}
