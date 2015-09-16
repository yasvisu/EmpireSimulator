package utils;

import UI.BattlesBox;
import javafx.scene.image.Image;

public class Constants {
    public static final String WINDOW_TITLE = "Battle mode";
    public static final double BATTLE_WINDOW_WIDTH = 800;
    public static final double BATTLE_WINDOW_HEIGHT = 600;

    public static final Image BACKGROUND_IMAGE = new Image(BattlesBox.class.getResource("images/background.jpg").toString());
    public static final Image PLAYER_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet3.png").toString());
    public static final Image ENEMY_ONE_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet1.jpg").toString());
    public static final Image ENEMY_TOW_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet2.png").toString());
    public static final Image ENEMY_THREE_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet3.png").toString());
    public static final Image ENEMY_FOUR_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet4.png").toString());
    public static final Image ENEMY_FIVE_PLANET_IMAGE = new Image(BattlesBox.class.getResource("images/planet5.png").toString());

    public static final double PLAYER_PLANET_HEIGHT = 70;
    public static final double PLAYER_PLANET_WIDTH = 90;
    public static final double ENEMY_ONE_PLANET_HEIGHT = 90;
    public static final double ENEMY_ONE_PLANET_WIDTH = 90;

    public static final double PLAYER_PLANET_X = 55;
    public static final double PLAYER_PLANET_Y = 180;
    public static final double ENEMY_ONE_PLANET_X = 350;
    public static final double ENEMY_ONE_PLANET_Y = 250;
}
