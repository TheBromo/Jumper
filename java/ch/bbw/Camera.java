package ch.bbw;

class Camera {
    private int y, height;
    private boolean changed;

    Camera(int y, int height) {
        this.height = height;
        this.y = y;
        changed = true;
    }

    boolean isChanged() {
        return changed;
    }

    void update(int newY) {
        if (newY < y) {
            y = newY;
            changed = true;
        }
    }

    int getY() {
        changed = false;
        return y;
    }

    public int getHeight() {
        return height;
    }
}
