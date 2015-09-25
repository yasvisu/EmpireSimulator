package ui.fxmlbundles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CreditsMenuController extends UIController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text CreditsMenuText;

    @FXML
    private Button CreditsMenuMainMenu;

    @FXML
    void CreditsMenuMainMenu_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("IntroMenu");
    }

    @FXML
    void initialize() {
        assert CreditsMenuText != null : "fx:id=\"CreditsMenuText\" was not injected: check your FXML file 'CreditsMenu.fxml'.";
        assert CreditsMenuMainMenu != null : "fx:id=\"CreditsMenuMainMenu\" was not injected: check your FXML file 'CreditsMenu.fxml'.";

    }
}
