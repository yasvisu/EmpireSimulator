package UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
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

        final ImageView playerPlanet = new ImageView(Constants.PLAYER_PLANET_IMAGE);
        setWidthAndHeight(playerPlanet, Constants.PLAYER_PLANET_WIDTH, Constants.PLAYER_PLANET_HEIGHT);
        setVector(playerPlanet, Constants.PLAYER_PLANET_X, Constants.PLAYER_PLANET_Y);

        final ImageView enemyVarmalusPlanet = new ImageView(Constants.ENEMY_VARMALUS_PLANET_IMAGE);
        setWidthAndHeight(enemyVarmalusPlanet, Constants.ENEMY_VARMALUS_PLANET_WIDTH, Constants.ENEMY_VARMALUS_PLANET_HEIGHT);
        setVector(enemyVarmalusPlanet, Constants.ENEMY_VARMALUS_PLANET_X, Constants.ENEMY_VARMALUS_PLANET_Y);

        final ImageView enemySlekonPlanet = new ImageView(Constants.ENEMY_SLEKON_PLANET_IMAGE);
        setWidthAndHeight(enemySlekonPlanet, Constants.ENEMY_SLEKON_PLANET_WIDTH, Constants.ENEMY_SLEKON_PLANET_HEIGHT);
        setVector(enemySlekonPlanet, Constants.ENEMY_SLEKON_PLANET_X, Constants.ENEMY_SLEKON_PLANET_Y);

        final ImageView enemyZakrosPlanet = new ImageView(Constants.ENEMY_ZAKROS_PLANET_IMAGE);
        setWidthAndHeight(enemyZakrosPlanet, Constants.ENEMY_ZAKROS_PLANET_WIDTH, Constants.ENEMY_ZAKROS_PLANET_HEIGHT);
        setVector(enemyZakrosPlanet, Constants.ENEMY_ZAKROS_PLANET_X, Constants.ENEMY_ZAKROS_PLANET_Y);

        final Group foreground = new Group(playerPlanet, enemyVarmalusPlanet, enemySlekonPlanet, enemyZakrosPlanet);
        foreground.setEffect(new DropShadow());
        final Group root = new Group(background, foreground);

        Scene scene = new Scene(root, Constants.BATTLE_WINDOW_WIDTH, Constants.BATTLE_WINDOW_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
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
