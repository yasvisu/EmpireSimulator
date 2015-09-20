package models.GUIModels;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Constants;
import utils.Messages;
import utils.exceptions.GameObjectOutOfWindowBoundsException;

public abstract class GameObject {

    private ImageView imageView;
    private double x;
    private double y;

    protected GameObject(Image image, double width, double height, double x, double y){
        try {
            this.setX(x);
        } catch (GameObjectOutOfWindowBoundsException e) {
            e.printStackTrace();
        }
        try {
            this.setY(y);
        } catch (GameObjectOutOfWindowBoundsException e) {
            e.printStackTrace();
        }
        imageViewLoader(image, width, height, x, y);
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public double getX(){
        return this.x;
    }

    private void setX(double x)throws GameObjectOutOfWindowBoundsException{
        if (x > Constants.BATTLE_WINDOW_WIDTH || x < 0){
            throw new GameObjectOutOfWindowBoundsException(
                    String.format(Messages.gameObjectOutOfWindowBoundsMessage, 'x', Constants.BATTLE_WINDOW_WIDTH));
        }
        this.x = x;
    }

    public double getY(){
        return this.y;
    }

    private void setY(double y) throws GameObjectOutOfWindowBoundsException {
        if (y > Constants.BATTLE_WINDOW_HEIGHT || y < 0){
            throw new GameObjectOutOfWindowBoundsException(
                    String.format(Messages.gameObjectOutOfWindowBoundsMessage, 'y', Constants.BATTLE_WINDOW_HEIGHT));
        }
        this.y = y;
    }

    private void imageViewLoader(Image image, double width, double height, double x, double y) {
        final ImageView imageView = new ImageView(image);
        setWidthAndHeight(imageView, width, height);
        setVector(imageView, x, y);
        this.imageView = imageView;
    }

    private void setVector(ImageView image, double x, double y) {
        image.setTranslateX(x);
        image.setTranslateY(y);
    }

    private void setWidthAndHeight(ImageView image, double width, double height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }
}