package application;

import UI.BattlesSimulator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * Created by borislavivanov on 9/22/15.
 */
public class Controller {

    public Button startButton;
    public MenuItem showUnits;

    public void startAction(ActionEvent actionEvent) {
        startButton.setOnAction(event -> new BattlesSimulator().display());
    }

    public void startActionShowUnits(ActionEvent actionEvent) {

    }
}
