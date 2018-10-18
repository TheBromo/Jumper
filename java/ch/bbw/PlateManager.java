package ch.bbw;

import java.util.ArrayList;

class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor, lastNoGenerated;

    //TODO: maybe add better jumping tiles

    PlateManager(Camera camera) {
        lastNoGenerated = 0;
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        lastGeneratedYCoor = camera.getY();
    }


    void update() {
        //remove out of sight objects
        objects.removeIf(object -> object.getPositionY() > camera.getY() + camera.getHeight());

        //add new objects
        if (camera.getY() < lastGeneratedYCoor - 150) {
            do {
                objects.addAll(generateNewLayer(camera.getY()));

            } while (lastNoGenerated  >2);
        }
    }

    ArrayList<GameObject> getObjects() {
        return objects;
    }

    private ArrayList<GameObject> generateNewLayer(int yCoor) {
        ArrayList<GameObject> plates = new ArrayList<>();
        lastGeneratedYCoor = yCoor - (yCoor % 150);
        int count = 0;
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() <= 0.3) {
                plates.add(new Plate(100 * i++ + 10, lastGeneratedYCoor));
                count++;
            }
        }
        if (count == 0) {
            lastNoGenerated++;
            System.out.println(lastNoGenerated);
        } else {
            lastNoGenerated = 0;
        }
        return plates;
    }
}
