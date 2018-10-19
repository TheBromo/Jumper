package ch.bbw;

abstract class GameObject {
    private int positionX, positionY, height, width;

    GameObject(int positionX, int positionY, int height, int width) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    abstract void update();

    abstract void collide(GameObject object);

    int getPositionX() {
        return positionX;
    }

    void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    int getPositionY() {
        return positionY;
    }

    void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    int getWidth() {
        return width;
    }
}
