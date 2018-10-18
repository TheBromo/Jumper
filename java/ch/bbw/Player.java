package ch.bbw;

public class Player extends GameObject {

    private int sceenW;
    private boolean onGround, jumpad;
    private double velocityX = 0, velocityY, gravity;


    Player(int positionX, int positionY, int height, int width, int sceenW) {
        super(positionX, positionY, height, width);
        onGround = true;
        this.sceenW = sceenW;
        gravity = 0.2;
    }

    @Override
    public void update() {

        velocityY += gravity;
        if (velocityY > 0) {
            gravity = 0.2;
        }
        setPositionY((int) (getPositionY() + velocityY));
        setPositionX((int) (getPositionX() + velocityX));

        if (getPositionX() > sceenW) {
            setPositionX(0);
        } else if (getPositionX() < 0) {
            setPositionX(sceenW);
        }
        startJump();
        endJump();
    }

    @Override
    public void collide(GameObject object) {
        if (object instanceof Snake) {
            jumpad = true;
            object.collide(this);
        }
    }

    private void startJump() {
        if (onGround) {
            velocityY = -12.0;
            onGround = false;
            if (jumpad) {
                gravity = 0.14;
                jumpad = false;
            }
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    private void endJump() {
        if (velocityY < -30.0) {
            velocityY = -4.0;
            gravity = 0.2;
        }
    }

    void checkCollision(GameObject object) {
        //returns if the feet collide with the object
        int positionX = getPositionX(), positionY = getPositionY(), height = getHeight(), width = getWidth();
        int objPositionX = object.getPositionX(), objPositionY = object.getPositionY(), objHeight = object.getHeight(), objWidth = object.getWidth();

        if (positionX + width > sceenW) {
            onGround = ((positionX < objPositionX + objWidth && positionX + width > objPositionX) ||
                    (positionX - sceenW < objPositionX + objWidth && positionX - sceenW + width > objPositionX))

                    && velocityY > 0 &&
                    positionY + height - 20 < objPositionY + objHeight &&
                    positionY + height > objPositionY;

        } else {
            onGround = positionX < objPositionX + objWidth &&
                    positionX + width > objPositionX &&
                    positionY + height - 20 < objPositionY + objHeight &&
                    positionY + height > objPositionY && velocityY > 0;
        }
    }

    boolean isOnGround() {
        return onGround;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
