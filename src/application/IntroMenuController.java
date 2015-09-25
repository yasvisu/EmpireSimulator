package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class IntroMenuController extends UIController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label IntroMenuClickLabel;

    @FXML
    private Button IntroMenuOptions;

    @FXML
    private BorderPane IntroMenuBorderPane;

    @FXML
    private Pane IntroMenuTitlePane;

    @FXML
    private Button IntroMenuCredits;

    @FXML
    private Button IntroMenuNew;

    @FXML
    private Button IntroMenuHighscores;

    @FXML
    private Button IntroMenuLoad;

    @FXML
    private Button IntroMenuAbout;

    @FXML
    void IntroMenuOptions_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("OptionsMenu");
    }

    @FXML
    void IntroMenuCredits_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("CreditsMenu");
    }

    @FXML
    void IntroMenuAbout_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("AboutMenu");
    }

    @FXML
    void IntroMenuNew_onAction(ActionEvent event) {
	this.getScreenManager().setLayout("GameMenu");
    }

    @FXML
    void IntroMenuLoad_onAction(ActionEvent event) {
	this.setLabelText("Not implemented: Load.");
    }

    @FXML
    void IntroMenuHighscores_onAction(ActionEvent event) {
	this.setLabelText("Not implemented: Highscores.");
    }

    @FXML
    void initialize() {
	assert IntroMenuOptions != null : "fx:id=\"IntroMenuOptions\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuBorderPane != null : "fx:id=\"IntroMenuBorderPane\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuTitlePane != null : "fx:id=\"IntroMenuTitlePane\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuCredits != null : "fx:id=\"IntroMenuCredits\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuNew != null : "fx:id=\"IntroMenuNew\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuHighscores != null : "fx:id=\"IntroMenuHighscores\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuLoad != null : "fx:id=\"IntroMenuLoad\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuAbout != null : "fx:id=\"IntroMenuAbout\" was not injected: check your FXML file 'IntroMenu.fxml'.";
	assert IntroMenuClickLabel != null : "fx:id=\"IntroMenuAbout\" was not injected: check your FXML file 'IntroMenu.fxml'.";
    }
    
    private void setLabelText(String text){
	this.IntroMenuClickLabel.setText(text);
    }
}
