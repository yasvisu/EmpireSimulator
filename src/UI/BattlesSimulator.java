package UI;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Constants;

import java.util.Random;


public class BattlesSimulator {

    private static Animation missileAnimation;
    private static Group missiles;

    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Constants.WINDOW_TITLE);
        window.setWidth(Constants.BATTLE_WINDOW_WIDTH);
        window.setHeight(Constants.BATTLE_WINDOW_HEIGHT);

        final ImageView background = new ImageView(Constants.BACKGROUND_IMAGE);

        final ImageView playerPlanet = imageViewLoader(
                Constants.PLAYER_PLANET_IMAGE,
                Constants.PLAYER_PLANET_WIDTH,
                Constants.PLAYER_PLANET_HEIGHT,
                Constants.PLAYER_PLANET_X,
                Constants.PLAYER_PLANET_Y);

        final ImageView enemyVarmalusPlanet = imageViewLoader(
                Constants.ENEMY_VARMALUS_PLANET_IMAGE,
                Constants.ENEMY_VARMALUS_PLANET_WIDTH,
                Constants.ENEMY_VARMALUS_PLANET_HEIGHT,
                Constants.ENEMY_VARMALUS_PLANET_X,
                Constants.ENEMY_VARMALUS_PLANET_Y);

        final ImageView enemySlekonPlanet = imageViewLoader(
                Constants.ENEMY_SLEKON_PLANET_IMAGE,
                Constants.ENEMY_SLEKON_PLANET_WIDTH,
                Constants.ENEMY_SLEKON_PLANET_HEIGHT,
                Constants.ENEMY_SLEKON_PLANET_X,
                Constants.ENEMY_SLEKON_PLANET_Y);

        final ImageView enemyZakrosPlanet = imageViewLoader(
                Constants.ENEMY_ZAKROS_PLANET_IMAGE,
                Constants.ENEMY_ZAKROS_PLANET_WIDTH,
                Constants.ENEMY_ZAKROS_PLANET_HEIGHT,
                Constants.ENEMY_ZAKROS_PLANET_X,
                Constants.ENEMY_ZAKROS_PLANET_Y);

        final ImageView enemyNurutaPlanet = imageViewLoader(
                Constants.ENEMY_NURUTA_PLANET_IMAGE,
                Constants.ENEMY_NURUTA_PLANET_WIDTH,
                Constants.ENEMY_NURUTA_PLANET_HEIGHT,
                Constants.ENEMY_NURUTA_PLANET_X,
                Constants.ENEMY_NURUTA_PLANET_Y);

        final ImageView missile = imageViewLoader(
                Constants.MISSILE_IMAGE,
                Constants.MISSILE_WIDTH,
                Constants.MISSILE_HEIGHT,
                Constants.MISSILE_X,
                Constants.MISSILE_Y);

        missiles = new Group(missile);
        missiles.setEffect(new DropShadow(2, Color.color(1,0,0)));

        final Group foreground = new Group(playerPlanet, enemyVarmalusPlanet, enemySlekonPlanet, enemyZakrosPlanet, enemyNurutaPlanet);
        foreground.setEffect(new DropShadow());
        final Group root = new Group(background, foreground, missiles);

        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);
        window.setScene(scene);
        startAnimation();
        window.showAndWait();
    }

    private static ImageView imageViewLoader(Image image, double width, double height, double x, double y) {
        final ImageView imageView = new ImageView(image);
        setWidthAndHeight(imageView, width, height);
        setVector(imageView, x, y);
        return imageView;
    }

    private static void setVector(ImageView image, double x, double y) {
        image.setTranslateX(x);
        image.setTranslateY(y);
    }

    private static void setWidthAndHeight(ImageView image, double width, double height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

    private static void startAnimation(){
        if (missileAnimation != null){
            missileAnimation.stop();
        }

        Random rand = new Random();

        final int y0 = rand.nextInt(Constants.BATTLE_WINDOW_HEIGHT / 2) + Constants.BATTLE_WINDOW_HEIGHT / 4;
        final int y1 = rand.nextInt(Constants.BATTLE_WINDOW_HEIGHT / 2) + Constants.BATTLE_WINDOW_HEIGHT / 4;

        missileAnimation = TranslateTransitionBuilder.create()
                .node(missiles)
                .fromX(-100)
                .toX(Constants.BATTLE_WINDOW_WIDTH)
                .fromY(y0)
                .toY(y1)
                .duration(Duration.seconds(3))
                .onFinished(e -> startAnimation())
                .build();

        missileAnimation.play();


    }
}
