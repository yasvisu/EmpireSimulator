package models.GUIModels;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

    private ImageView imageView;

    protected GameObject(Image image, double width, double height, double x, double y){
        imageViewLoader(image, width, height, x, y);
    }

    public ImageView getImageView(){
        return this.imageView;
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
