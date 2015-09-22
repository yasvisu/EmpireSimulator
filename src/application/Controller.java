package application;

import contracts.Engine;
import contracts.Unit;
import contracts.UnitTree;
import contracts.UpgradeTypes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import models.HugeInteger;
import ui.BattlesSimulator;



/**
 * Created by borislavivanov on 9/22/15.
 */
public class Controller {

    public Button startButton;
    public MenuItem showUnits;
    public Button exitButton;
    public AnchorPane background;

    public void startAction(ActionEvent actionEvent) {
        startButton.setOnAction(event -> new BattlesSimulator(new Engine() {
            @Override
            public void update() {

            }

            @Override
            public UnitTree getUnits(String resourceName) {
                return null;
            }

            @Override
            public boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType) {
                return false;
            }

            @Override
            public boolean upgrade(Unit unit, UpgradeTypes upgradeType) {
                return false;
            }

            @Override
            public boolean peekBuyUnits(Unit unit, HugeInteger count) {
                return false;
            }

            @Override
            public boolean buyUnits(Unit unit, HugeInteger count) {
                return false;
            }

            @Override
            public void leapSeconds(long seconds) {

            }

            @Override
            public long getScore() {
                return 0;
            }

            @Override
            public HugeInteger getBaconAmount() {
                return null;
            }

            @Override
            public HugeInteger getFreedomAmount() {
                return null;
            }

            @Override
            public HugeInteger getDemocracyAmount() {
                return null;
            }

            @Override
            public long getMoolahAmount() {
                return 0;
            }

            @Override
            public void setBaconAmount(HugeInteger amount) {

            }

            @Override
            public void setFreedomAmount(HugeInteger amount) {

            }

            @Override
            public void setDemocracyAmount(HugeInteger amount) {

            }

            @Override
            public void setMoolahAmount(long amount) {

            }
        }).display());
    }

    public void startActionShowUnits(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
        exitButton.setOnAction(event -> AlertBox.display());
    }
}
