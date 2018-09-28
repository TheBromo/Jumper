package ch.bbw;

public abstract class GameObject {
    int positionX,positionY;
    double velocityX,velocityY;
    double gravity = 0.5;

    public abstract void update(long passedTime);
}
