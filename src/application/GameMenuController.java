package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameMenuController extends UIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button GameMenuMainMenu;

    @FXML
    void GameMenuMainMenu_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("IntroMenu");
    }

    @FXML
    void initialize() {
	assert GameMenuMainMenu != null : "fx:id=\"GameMenuMainMenu\" was not injected: check your FXML file 'GameMenu.fxml'.";

    }
}
