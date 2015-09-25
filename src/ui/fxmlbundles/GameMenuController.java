package ui.fxmlbundles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameMenuController extends UIController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button GameMenuSave;

    @FXML
    private Button GameMenuMainMenu;

    @FXML
    private Label GameMenuClickLabel;

    @FXML
    void GameMenuSave_onAction(ActionEvent event) {
	this.GameMenuClickLabel.setText("Not implemented: Save.");
    }

    @FXML
    void GameMenuMainMenu_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("IntroMenu");
    }

    @FXML
    void initialize() {
        assert GameMenuSave != null : "fx:id=\"GameMenuSave\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert GameMenuMainMenu != null : "fx:id=\"GameMenuMainMenu\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert GameMenuClickLabel != null : "fx:id=\"GameMenuClickLabel\" was not injected: check your FXML file 'GameMenu.fxml'.";

    }
}
