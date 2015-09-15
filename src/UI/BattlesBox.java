package UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class BattlesBox {
    private static final String WINDOWTITLE = "Battle mode";
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final Image BACKGROUND = new Image(BattlesBox.class.getResource("images/background.jpg").toString());

    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(WINDOWTITLE);
        window.setWidth(WIDTH);
        window.setHeight(HEIGHT);

        //TODO: implement battles
        final ImageView background = new ImageView(BACKGROUND);
        final Group root = new Group(background);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }
}
