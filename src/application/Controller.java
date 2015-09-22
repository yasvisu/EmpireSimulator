package application;

import contracts.Engine;
import controllers.EmpireEngine;
import ui.BattlesSimulator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public Button startButton;
    public MenuItem showUnits;
    public Button exitButton;
    public AnchorPane background;
    private Engine engine = new EmpireEngine();

    public void startAction(ActionEvent actionEvent) {
        startButton.setOnAction(event -> this.engine.run());
    }

    public void startActionShowUnits(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
        exitButton.setOnAction(event -> AlertBox.display());
    }
}
