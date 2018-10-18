package ch.bbw;

import javafx.scene.image.Image;

import java.util.ArrayList;

class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor, lastNoGenerated;
    private Image wood1, wood2, neutral, jump, after;

    //TODO: maybe add better jumping tiles

    PlateManager(Camera camera) {
        lastNoGenerated = 0;
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        lastGeneratedYCoor = camera.getY();
        wood1 = new Image(getClass().getResourceAsStream(FXMLController.wood1Path));
        wood2 = new Image(getClass().getResourceAsStream(FXMLController.wood2Path));
        neutral = new Image(getClass().getResourceAsStream(FXMLController.neutralPath));
        jump = new Image(getClass().getResourceAsStream(FXMLController.jumpPath));
        after = new Image(getClass().getResourceAsStream(FXMLController.afterPath));
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

        objects.forEach(GameObject::update);
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
                if (Math.random() > 0.1) {
                    plates.add(new Plate(100 * i++ + 10, lastGeneratedYCoor, Math.random() > 0.5 ? wood1 : wood2));
                } else {
                    plates.add(new Snake(100 * i++ + 10, lastGeneratedYCoor, 10, 80,neutral,jump,after));
                }
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
