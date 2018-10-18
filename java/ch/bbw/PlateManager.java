package ch.bbw;

import javafx.scene.image.Image;

import java.util.ArrayList;

class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor, lastNoGenerated;
    private Image wood1, wood2;

    //TODO: maybe add better jumping tiles

    PlateManager(Camera camera) {
        lastNoGenerated = 0;
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        lastGeneratedYCoor = camera.getY();
        wood1 = new Image(getClass().getResourceAsStream(FXMLController.wood1Path));
        wood2 = new Image(getClass().getResourceAsStream(FXMLController.wood2Path));
    }


    void update() {
        //remove out of sight objects
        objects.removeIf(object -> object.getPositionY() > camera.getY() + camera.getHeight());

        //add new objects
        if (camera.getY() < lastGeneratedYCoor - 150) {
            double difficulty = 0.5 + ((double) camera.getY() / (double) (camera.getY() + 100000));
            do {
                objects.addAll(generateNewLayer(camera.getY(), difficulty));

            } while (lastNoGenerated >= 2);
        }
    }

    ArrayList<GameObject> getObjects() {
        return objects;
    }

    private ArrayList<GameObject> generateNewLayer(int yCoor, double difficulty) {
        ArrayList<GameObject> plates = new ArrayList<>();
        lastGeneratedYCoor = yCoor - (yCoor % 150);
        int count = 0;
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() <= difficulty) {
                plates.add(new Plate(100 * i++ + 10, lastGeneratedYCoor, Math.random() > 0.5 ? wood1 : wood2));
                count++;
            }
        }
        if (count == 0) {
            lastNoGenerated++;
        } else {
            lastNoGenerated = 0;
        }
        return plates;
    }
}
