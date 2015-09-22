package ui;

import contracts.Engine;
import javafx.animation.Animation;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.guiModels.Missile;
import models.guiModels.Planet;
import utils.Constants;
import utils.EnhancedRandom;

import java.util.*;


public class BattlesSimulator {

    private Animation missileAnimation;
    private Group missiles;

    private List<Planet> planets;

    private Missile missile;

    private Planet chosenPlanet;
    /**
     * Missile starting point. It starting point is the location of the player planet.
     */
    private int missilePointX = Constants.PLAYER_PLANET_X-120;
    private int missilePointY = Constants.PLAYER_PLANET_Y-160;

    private final int deviationX = 65;
    private final int deviationY = 35;

    /**
     * When the missile reach the chosen enemy planet the animation ends.
     * The missile wont be rendered again on the screen.
     */
    private Boolean missileAnimationEnded = false;

    /**
     * Removes the difference in the coordinates of the planets.
     */
    private int enemyPlanetDeviation = 150;

    private double unstableRotationRatio = 0;
    /**
     * Displays the battles window.
     * This method render all the objects in it.
     */
    private Engine engine;

    public BattlesSimulator(Engine engine){
        this.engine = engine;
    }

    public void display(){
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

        this.missile = new Missile(
                Constants.MISSILE_IMAGE,
                Constants.MISSILE_WIDTH,
                Constants.MISSILE_HEIGHT,
                Constants.MISSILE_X,
                Constants.MISSILE_Y);

        this.missiles = new Group(this.missile.getImageView());
        this.missiles.setEffect(new DropShadow(2, Color.color(1, 0, 0)));

        final Group foreground = new Group();

        for (Planet planet : this.planets) {
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

        /**
         * it keeps all the player resources in the game.
         */
        final VBox itemsBox = VBoxBuilder.create()
                .children(baconLabel, soldiersLabel, freedomLabel, moolahLabel, elapsedSecondsLabel)
                .translateX(20)
                .translateY(20)
                .build();
        itemsBox.setStyle("-fx-background-color: black;");

        final Group root = new Group(background, foreground, this.missiles, itemsBox);
        this.missiles.setVisible(false);
        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);

        window.setScene(scene);
        scene.setOnMousePressed(e -> this.startAnimation(e.getX(), e.getY()));
        window.showAndWait();
    }

    /**
     * It animates the missile and moves it from the starting point to the enemy planet location.
     * @param x coordinate of the enemy planet location.
     * @param y coordinate of the enemy planet location.
     */
    private void startAnimation(double x, double y){
        this.chosenPlanet = findPlanetByXY(x,y);

        if (!missileAnimationEnded){
            this.missiles.setVisible(true);
        }

        if (chosenPlanet == null){
            this.setMissileMovement(x, y);
            this.missiles.setRotate(unstableRotationRatio+=2);

            if (this.missilePointX == x-this.enemyPlanetDeviation && this.missilePointY == y-this.enemyPlanetDeviation){
                this.missilePointX = Constants.PLAYER_PLANET_X-120;
                this.missilePointY = Constants.PLAYER_PLANET_Y-160;
                this.missiles.setVisible(false);
                this.missileAnimation.stop();
                this.missileAnimationEnded = true;
            }
        }
        else{
            this.setMissileRotation(this.chosenPlanet.getY());
            this.setMissileMovement(this.chosenPlanet.getX(), this.chosenPlanet.getY());

            if (this.missilePointX == this.chosenPlanet.getX()-this.enemyPlanetDeviation && this.missilePointY ==this.chosenPlanet.getY()-enemyPlanetDeviation){
                this.missilePointX = Constants.PLAYER_PLANET_X-120;
                this.missilePointY = Constants.PLAYER_PLANET_Y-160;
                this.missiles.setVisible(false);
                this.missileAnimation.stop();
                this.missileAnimationEnded = true;
            }
        }

        this.missileAnimation = TranslateTransitionBuilder.create()
                .node(this.missiles)
                .fromX(this.missilePointX)
                .toX(this.missilePointX)
                .fromY(this.missilePointY)
                .toY(this.missilePointY)
                .duration(Duration.millis(0.1))
                .onFinished(e -> this.startAnimation(x,y))
                .build();

        this.missileAnimation.play();
    }

    /**
     * Moves the missile closer to the enemy planet.
     * @param x coordinate of the enemy planet location.
     * @param y coordinate of the enemy planet location.
     */
    private void setMissileMovement(double x, double y) {
        if(this.missilePointX < x - this.enemyPlanetDeviation){
            this.missilePointX++;
        }
        else if (this.missilePointX > x - this.enemyPlanetDeviation){
            this.missilePointX--;
        }

        if (this.missilePointY < y - this.enemyPlanetDeviation){
            this.missilePointY++;
        }
        else if (this.missilePointY > y - this.enemyPlanetDeviation){
            this.missilePointY--;
        }
    }

    /**
     * rotates the missile towards an enemy planet
     * it rotates the image by degrees
     * @param planetY y coordinate of the enemy planet.
     */
    private void setMissileRotation(double planetY) {
        if (this.missilePointY < planetY){
            double ratio = planetY - this.missilePointY;
            double degrees = (ratio / 10) - 15;
            this.missiles.setRotate(degrees);
        }
        else if (this.missilePointY > planetY){
            double ratio = this.missilePointY - planetY;
            double degrees = (ratio / 10) + 260;
            this.missiles.setRotate(degrees);
        }
    }

    /**
     * Finds a planet by x and y coordinates.
     * This method checks if the x and y coordinates belong to any of the enemies planets.
     * If true, return the planet and start the animation, else, stop animation.
     * @param x coordinate of wanted planet.
     * @param y coordinate of wanted planet.
     * @return Planet by given x and y coordinates.
     */
    private Planet findPlanetByXY(double x, double y) {

        Optional<Planet> matchedPlanet = this.planets
                .stream()
                .filter(planet -> Math.pow(
                        (x - (planet.getX() + this.deviationX)), 2)
                        + Math.pow((y - (planet.getY() + this.deviationY)), 2)
                        <= Math.pow((planet.getWidth() / 2), 2)
                        && planet != this.planets.get(0))
                .findFirst();

        try {
            return matchedPlanet.get();
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    private void battleProcess(){
        long enemyMoolah = engine.getMoolahAmount();

        int enemyBaconExponent = EnhancedRandom.nextInt(
                (engine.getBaconAmount().getExponent() / 2),
                engine.getBaconAmount().getExponent() + (engine.getBaconAmount().getExponent() / 2));
        double enemyBaconPrecision = EnhancedRandom.nextDouble(
                (engine.getBaconAmount().getPrecision() / 2),
                engine.getBaconAmount().getPrecision() + (engine.getBaconAmount().getPrecision() / 2));

        int enemyFreedomExponent = EnhancedRandom.nextInt(
                (engine.getFreedomAmount().getExponent() / 2),
                engine.getFreedomAmount().getExponent() + (engine.getFreedomAmount().getExponent() / 2));
        double enemyFreedomPrecision = EnhancedRandom.nextDouble(
                (engine.getFreedomAmount().getPrecision() / 2),
                engine.getFreedomAmount().getPrecision() + (engine.getFreedomAmount().getPrecision() / 2));

        int enemyDemocracyExponent = EnhancedRandom.nextInt(
                (engine.getDemocracyAmount().getExponent() / 2),
                engine.getDemocracyAmount().getExponent() + (engine.getDemocracyAmount().getExponent() / 2));
        double enemyDemocracyPrecision = EnhancedRandom.nextDouble(
                (engine.getDemocracyAmount().getPrecision() / 2),
                engine.getDemocracyAmount().getPrecision() + (engine.getDemocracyAmount().getPrecision() / 2));



    }
}
