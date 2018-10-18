package ch.bbw;

public abstract class GameObject {
    private int positionX, positionY, height, width;

    public GameObject(int positionX, int positionY, int height, int width) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    public abstract void update();

    public abstract void collide(GameObject object);

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
