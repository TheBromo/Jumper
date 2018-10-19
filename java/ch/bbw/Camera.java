package ch.bbw;

class Camera {
    private int y, height, width;

    Camera(int y, int height, int width) {
        this.height = height;
        this.width = width;
        this.y = y;
    }

    void update(int playerY) {
        if (playerY < y +(double) (height / 2)) {
            y = (int) (playerY- (double)(height)/2);
        }
    }

    int getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}
