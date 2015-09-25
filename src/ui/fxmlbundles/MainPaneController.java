package ui.fxmlbundles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainPaneController extends UIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane MainPaneTitlePane;

    @FXML
    void initialize() {
	assert MainPaneTitlePane != null : "fx:id=\"MainPaneTitlePane\" was not injected: check your FXML file 'MainPane.fxml'.";

    }
    
    public Pane getMainTitlePane(){
	return this.MainPaneTitlePane;
    }
}
