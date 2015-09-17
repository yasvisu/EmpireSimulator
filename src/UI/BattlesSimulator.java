package UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Constants;


public class BattlesSimulator {

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

        final Group foreground = new Group(playerPlanet, enemyVarmalusPlanet, enemySlekonPlanet, enemyZakrosPlanet, enemyNurutaPlanet);
        foreground.setEffect(new DropShadow());
        final Group root = new Group(background, foreground);

        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }

    private static ImageView imageViewLoader(Image planetImage, double planetWidth, double planetHeight, double planetX, double planetY) {
        final ImageView planet = new ImageView(planetImage);
        setWidthAndHeight(planet, planetWidth, planetHeight);
        setVector(planet, planetX, planetY);
        return planet;
    }

    private static void setVector(ImageView playerPlanet, double x, double y) {
        playerPlanet.setTranslateX(x);
        playerPlanet.setTranslateY(y);
    }

    private static void setWidthAndHeight(ImageView planet, double width, double height) {
        planet.setFitHeight(height);
        planet.setFitWidth(width);
    }
}
