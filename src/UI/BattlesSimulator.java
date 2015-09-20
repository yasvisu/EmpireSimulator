package UI;

import controllers.EmpireEngine;
import javafx.animation.Animation;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.GUIModels.GameObject;
import models.GUIModels.Missile;
import models.GUIModels.Planet;
import utils.Constants;
import java.util.*;
import static javax.script.Bindings.*;


public class BattlesSimulator {

    private Animation missileAnimation;
    private Group missiles;

    List<Planet> planets;

    Missile missile;

    Planet chosenPlanet;

    private int missilePointX = Constants.PLAYER_PLANET_X-120;
    private int missilePointY = Constants.PLAYER_PLANET_Y-160;

    private Boolean missileAnimationEnded = false;
    private int enemyPlanetDeviation = 150;

    public  void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Constants.WINDOW_TITLE);
        window.setWidth(Constants.BATTLE_WINDOW_WIDTH);
        window.setHeight(Constants.BATTLE_WINDOW_HEIGHT);

        final ImageView background = new ImageView(Constants.BACKGROUND_IMAGE);


        planets = new ArrayList<>(Arrays.asList(
            new Planet(
                    Constants.PLAYER_PLANET_IMAGE,
                    Constants.PLAYER_PLANET_WIDTH,
                    Constants.PLAYER_PLANET_HEIGHT,
                    Constants.PLAYER_PLANET_X,
                    Constants.PLAYER_PLANET_Y),
            new Planet(
                    Constants.ENEMY_NURUTA_PLANET_IMAGE,
                    Constants.ENEMY_NURUTA_PLANET_WIDTH,
                    Constants.ENEMY_NURUTA_PLANET_HEIGHT,
                    Constants.ENEMY_NURUTA_PLANET_X,
                    Constants.ENEMY_NURUTA_PLANET_Y),
            new Planet(
                    Constants.ENEMY_VARMALUS_PLANET_IMAGE,
                    Constants.ENEMY_VARMALUS_PLANET_WIDTH,
                    Constants.ENEMY_VARMALUS_PLANET_HEIGHT,
                    Constants.ENEMY_VARMALUS_PLANET_X,
                    Constants.ENEMY_VARMALUS_PLANET_Y),

            new Planet(
                    Constants.ENEMY_SLEKON_PLANET_IMAGE,
                    Constants.ENEMY_SLEKON_PLANET_WIDTH,
                    Constants.ENEMY_SLEKON_PLANET_HEIGHT,
                    Constants.ENEMY_SLEKON_PLANET_X,
                    Constants.ENEMY_SLEKON_PLANET_Y),
            new Planet(
                    Constants.ENEMY_ZAKROS_PLANET_IMAGE,
                    Constants.ENEMY_ZAKROS_PLANET_WIDTH,
                    Constants.ENEMY_ZAKROS_PLANET_HEIGHT,
                    Constants.ENEMY_ZAKROS_PLANET_X,
                    Constants.ENEMY_ZAKROS_PLANET_Y)));

        missile = new Missile(
                Constants.MISSILE_IMAGE,
                Constants.MISSILE_WIDTH,
                Constants.MISSILE_HEIGHT,
                Constants.MISSILE_X,
                Constants.MISSILE_Y);

        missiles = new Group(missile.getImageView());
        missiles.setEffect(new DropShadow(2, Color.color(1, 0, 0)));

        final Group foreground = new Group();

        for (Planet planet : planets) {
            foreground.getChildren().add(planet.getImageView());
        }

        foreground.setEffect(new DropShadow());

        final Text baconLabel = new Text();
        baconLabel.textProperty().bind(Bindings.concat("Bacon: ", 5));
        baconLabel.setStyle("-fx-stroke: white;");
        final Text soldiersLabel = new Text();
        soldiersLabel.textProperty().bind(Bindings.concat("Soldiers: ", 5));
        soldiersLabel.setStyle("-fx-stroke: white;");
        final Text freedomLabel = new Text();
        freedomLabel.textProperty().bind(Bindings.concat("Freedom: ", 5));
        freedomLabel.setStyle("-fx-stroke: white;");
        final Text moolahLabel = new Text();
        moolahLabel.textProperty().bind(Bindings.concat("Moolah: ", 5));
        moolahLabel.setStyle("-fx-stroke: white;");
        final Text elapsedSecondsLabel = new Text();
        elapsedSecondsLabel.textProperty().bind(Bindings.concat("Elapsed seconds: ", 5));
        elapsedSecondsLabel.setStyle("-fx-stroke: white;");

        final VBox itemsBox = VBoxBuilder.create()
                .children(baconLabel, soldiersLabel, freedomLabel, moolahLabel, elapsedSecondsLabel)
                .translateX(20)
                .translateY(20)
                .build();
        itemsBox.setStyle("-fx-background-color: black;");

        final Group root = new Group(background, foreground, missiles, itemsBox);
        missiles.setVisible(false);
        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);

        window.setScene(scene);
        scene.setOnMousePressed(e -> startAnimation(e.getX(), e.getY()));
        window.showAndWait();
    }

    private void startAnimation(double x, double y){
        chosenPlanet = findPlanetByXY(x,y);
        if (missilePointX == chosenPlanet.getX()-enemyPlanetDeviation && missilePointY == chosenPlanet.getY()-enemyPlanetDeviation){
            missilePointX = Constants.PLAYER_PLANET_X-120;
            missilePointY = Constants.PLAYER_PLANET_Y-160;
            missiles.setVisible(false);
            missileAnimation.stop();
            missileAnimationEnded = true;
        }
        if (!missileAnimationEnded){
            missiles.setVisible(true);
        }
        setMissileRotation(chosenPlanet.getY());
        setMissileMovement(chosenPlanet.getX(), chosenPlanet.getY());

        missileAnimation = TranslateTransitionBuilder.create()
                .node(missiles)
                .fromX(missilePointX)
                .toX(missilePointX)
                .fromY(missilePointY)
                .toY(missilePointY)
                .duration(Duration.millis(0.1))
                .onFinished(e -> startAnimation(x,y))
                .build();

        missileAnimation.play();
    }

    private void setMissileMovement(double x, double y) {
        if(missilePointX < x - enemyPlanetDeviation){
            missilePointX++;
        }
        else if (missilePointX > x - enemyPlanetDeviation){
            missilePointX--;
        }

        if (missilePointY < y - enemyPlanetDeviation){
            missilePointY++;
        }
        else if (missilePointY > y - enemyPlanetDeviation){
            missilePointY--;
        }
    }

    private void setMissileRotation(double planetY) {
        if (missilePointY < planetY){
            double ratio = planetY - missilePointY;
            double degrees = (ratio / 10) - 15;
            missiles.setRotate(degrees);
        }
        else if (missilePointY > planetY){
            double ratio = missilePointY - planetY;
            double degrees = (ratio / 10) + 260;
            missiles.setRotate(degrees);
        }
    }

    private Planet findPlanetByXY(double x, double y) {
        final int deviationX = 66;
        final int deviationY = 35;

        Optional<Planet> matchedPlanet = planets
                .stream()
                .filter(planet -> Math.pow((x - (planet.getX() + deviationX)), 2) + Math.pow((y - (planet.getY() + deviationY)), 2)
                        <= Math.pow((planet.getWidth() / 2), 2))
                .findFirst();

        try {
            return matchedPlanet.get();
        }
        catch (NoSuchElementException e){
            missileAnimationEnded = true;
            return planets.get(0);
        }
    }
}
