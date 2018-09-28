package ch.bbw;

public class Player extends GameObject {

    private boolean onGround;

    public  Player(){
        onGround = true;
    }
    @Override
    public void update(long passedTime) {
        velocityY += gravity;
        positionY += velocityY;
        positionX += velocityX;
    }

    public void startJump(){
        if (onGround){
            velocityY = -12.0;
            onGround = false;
        }
    }

    public void endJump(){
        if(velocityY < -6.0) velocityY = -6.0;
    }

}
