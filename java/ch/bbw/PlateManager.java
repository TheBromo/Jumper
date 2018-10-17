package ch.bbw;

import java.util.ArrayList;

class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor;

    PlateManager(Camera camera) {
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        System.out.println(maxAmount);
        lastGeneratedYCoor = camera.getY();
    }


    void update() {
        objects.removeIf(object -> object.positionY > camera.getY() + camera.getHeight() );
        if (camera.getY() < lastGeneratedYCoor - 100) {
            objects.addAll(generateNewLayer(camera.getY()));

        }
        System.out.println(objects.size());
    }

    ArrayList<GameObject> getObjects() {
        return objects;
    }

    private ArrayList<GameObject> generateNewLayer(int yCoor) {
        ArrayList<GameObject> plates = new ArrayList<>();
        lastGeneratedYCoor = yCoor - (yCoor % 100);
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() < 0.3) {
                plates.add(new Plate(100 * i + 10, lastGeneratedYCoor));
            }
        }
        return plates;
    }
}
