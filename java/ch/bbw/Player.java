package ch.bbw;

public class Player extends GameObject {

    private boolean onGround;
    private double velocityX = 0, velocityY;


    Player(int positionX, int positionY, int height, int width) {
        super(positionX, positionY, height, width);
        onGround = true;
    }

    @Override
    public void update(long passedTime) {
        //TODO: Add animation
        double gravity = 0.3;
        velocityY += gravity;
        positionY += velocityY;
        positionX += velocityX;
        startJump();
        endJump();
    }

    @Override
    public void collide() {

    }

    private void startJump() {
        if (onGround) {
            velocityY = -12.0;
            onGround = false;
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    private void endJump() {
        if (velocityY < -30.0) velocityY = -4.0;
    }

    void checkCollision(GameObject object, int offset) {
        //returns if the feet collide with the object
        onGround = positionX < object.positionX + object.width &&
                positionX + width > object.positionX &&
                positionY < object.positionY - offset + object.height &&
                positionY + height > object.positionY - offset;
    }

    boolean isOnGround() {
        return onGround;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
