package ch.bbw;

import java.util.ArrayList;

class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor;

    //TODO: Add better generator
    //TODO: Add more start panels
    //TODO: maybe add better jumping tiles

    PlateManager(Camera camera) {
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        lastGeneratedYCoor = camera.getY();
    }


    void update() {
        //remove out of sight objects
        objects.removeIf(object -> object.positionY > camera.getY() + camera.getHeight() );

        //add new objects
        if (camera.getY() < lastGeneratedYCoor - 150) {
            objects.addAll(generateNewLayer(camera.getY()));
        }
    }

    ArrayList<GameObject> getObjects() {
        return objects;
    }

    private ArrayList<GameObject> generateNewLayer(int yCoor) {
        ArrayList<GameObject> plates = new ArrayList<>();
        lastGeneratedYCoor = yCoor - (yCoor % 150);
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() <= 0.3) {
                plates.add(new Plate(100 * i + 10, lastGeneratedYCoor));
            }
        }
        return plates;
    }
}
