package ch.bbw;

class Camera {
    private int y, height, width;
    private boolean changed;

    Camera(int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.y = y;
        changed = true;
    }

    boolean isChanged() {
        return changed;
    }

    void update(int newY) {
        System.out.println("newY = " + newY);
        System.out.println("y = " + y);
        if (newY < y) {
            y = newY;
            changed = true;
        }
    }

    int getY() {
        changed = false;
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}
