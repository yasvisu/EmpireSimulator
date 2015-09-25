package ui.fxmlbundles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class AboutMenuController extends UIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text AboutMenuText;

    @FXML
    private Button AboutMenuMainMenu;

    @FXML
    void AboutMenuMainMenu_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("IntroMenu");
    }

    @FXML
    void initialize() {
        assert AboutMenuText != null : "fx:id=\"AboutMenuText\" was not injected: check your FXML file 'AboutMenu.fxml'.";
        assert AboutMenuMainMenu != null : "fx:id=\"AboutMenuMainMenu\" was not injected: check your FXML file 'AboutMenu.fxml'.";

    }
}
