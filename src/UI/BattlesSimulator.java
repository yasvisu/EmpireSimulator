package UI;

import javafx.animation.Animation;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.GUIModels.Missile;
import models.GUIModels.Planet;
import utils.Constants;


public class BattlesSimulator {

    private static Animation missileAnimation;
    private static Group missiles;

    static Planet enemyNurutaPlanet;
    static Planet playerPlanet;
    static Planet enemyVarmalusPlanet;
    static Planet enemySlekonPlanet;
    static Planet enemyZakrosPlanet;
    static Missile missile;

    private static int missilePointX = Constants.PLAYER_PLANET_X-110;
    private static int missilePointY = Constants.PLAYER_PLANET_Y-150;

    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Constants.WINDOW_TITLE);
        window.setWidth(Constants.BATTLE_WINDOW_WIDTH);
        window.setHeight(Constants.BATTLE_WINDOW_HEIGHT);

        final ImageView background = new ImageView(Constants.BACKGROUND_IMAGE);

        playerPlanet = new Planet(
                Constants.PLAYER_PLANET_IMAGE,
                Constants.PLAYER_PLANET_WIDTH,
                Constants.PLAYER_PLANET_HEIGHT,
                Constants.PLAYER_PLANET_X,
                Constants.PLAYER_PLANET_Y);

        enemyNurutaPlanet = new Planet(
                Constants.ENEMY_NURUTA_PLANET_IMAGE,
                Constants.ENEMY_NURUTA_PLANET_WIDTH,
                Constants.ENEMY_NURUTA_PLANET_HEIGHT,
                Constants.ENEMY_NURUTA_PLANET_X,
                Constants.ENEMY_NURUTA_PLANET_Y);

        enemyVarmalusPlanet = new Planet(
                Constants.ENEMY_VARMALUS_PLANET_IMAGE,
                Constants.ENEMY_VARMALUS_PLANET_WIDTH,
                Constants.ENEMY_VARMALUS_PLANET_HEIGHT,
                Constants.ENEMY_VARMALUS_PLANET_X,
                Constants.ENEMY_VARMALUS_PLANET_Y);

        enemySlekonPlanet = new Planet(
                Constants.ENEMY_SLEKON_PLANET_IMAGE,
                Constants.ENEMY_SLEKON_PLANET_WIDTH,
                Constants.ENEMY_SLEKON_PLANET_HEIGHT,
                Constants.ENEMY_SLEKON_PLANET_X,
                Constants.ENEMY_SLEKON_PLANET_Y);

        enemyZakrosPlanet = new Planet(
                Constants.ENEMY_ZAKROS_PLANET_IMAGE,
                Constants.ENEMY_ZAKROS_PLANET_WIDTH,
                Constants.ENEMY_ZAKROS_PLANET_HEIGHT,
                Constants.ENEMY_ZAKROS_PLANET_X,
                Constants.ENEMY_ZAKROS_PLANET_Y);

        missile = new Missile(
                Constants.MISSILE_IMAGE,
                Constants.MISSILE_WIDTH,
                Constants.MISSILE_HEIGHT,
                Constants.MISSILE_X,
                Constants.MISSILE_Y);

        missiles = new Group(missile.getImageView());
        missiles.setEffect(new DropShadow(2, Color.color(1,0,0)));

        final Group foreground = new Group(
                playerPlanet.getImageView(),
                enemyVarmalusPlanet.getImageView(),
                enemySlekonPlanet.getImageView(),
                enemyZakrosPlanet.getImageView(),
                enemyNurutaPlanet.getImageView());

        foreground.setEffect(new DropShadow());

        final Group root = new Group(background, foreground, missiles);

        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);
        window.setScene(scene);
        startAnimation();
        window.showAndWait();
    }

    private static void startAnimation(){
        if (missilePointX == Constants.ENEMY_NURUTA_PLANET_X-150 && missilePointY == Constants.ENEMY_NURUTA_PLANET_Y-150){
            missilePointX = Constants.PLAYER_PLANET_X-110;
            missilePointY = Constants.PLAYER_PLANET_Y-150;
            missileAnimation.stop();
        }

        setMissileRotation(Constants.ENEMY_NURUTA_PLANET_Y);
        setMissileMovement();

        missileAnimation = TranslateTransitionBuilder.create()
                .node(missiles)
                .fromX(missilePointX)
                .toX(missilePointX)
                .fromY(missilePointY)
                .toY(missilePointY)
                .duration(Duration.millis(5))
                .onFinished(e -> startAnimation())
                .build();

        missileAnimation.play();
    }

    private static void setMissileMovement() {
        if(missilePointX < Constants.ENEMY_NURUTA_PLANET_X-150){
            missilePointX++;
        }
        else if (missilePointX > Constants.ENEMY_NURUTA_PLANET_X-150){
            missilePointX--;
        }

        if (missilePointY < Constants.ENEMY_NURUTA_PLANET_Y-150){
            missilePointY++;
        }
        else if (missilePointY > Constants.ENEMY_NURUTA_PLANET_Y-150){
            missilePointY--;
        }
    }

    private static void setMissileRotation(double planetY) {
        if (missilePointY < planetY){
            double ratio = planetY - missilePointY;
            double degrees = ratio / 10;
            missiles.setRotate(degrees);
        }
        else if (missilePointY > planetY){
            double ratio = missilePointY - planetY;
            double degrees = (ratio / 10) + 270;
            missiles.setRotate(degrees);
        }
    }
}
