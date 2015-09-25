package ui.fxmlbundles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class OptionsMenuController extends UIController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OptionsMenuMainMenu;

    @FXML
    private Text OptionsMenuText;

    @FXML
    void OptionsMenuMainMenu_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("IntroMenu");
    }

    @FXML
    void initialize() {
        assert OptionsMenuMainMenu != null : "fx:id=\"OptionsMenuMainMenu\" was not injected: check your FXML file 'OptionsMenu.fxml'.";
        assert OptionsMenuText != null : "fx:id=\"OptionsMenuText\" was not injected: check your FXML file 'OptionsMenu.fxml'.";

    }
}
