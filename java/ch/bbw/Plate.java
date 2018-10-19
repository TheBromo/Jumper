package ch.bbw;

import javafx.scene.image.Image;

public class Plate extends GameObject {
    private Image img;

    Plate(int positionX, int positionY, Image img) {
        super(positionX, positionY, 10, 80);
        this.img = img;
    }

    Image getImg() {
        return img;
    }

    @Override
    public void update() {
    }

    @Override
    public void collide(GameObject object) {
    }
}
