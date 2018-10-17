package ch.bbw;

import java.util.ArrayList;

public class PlateManager {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private int maxAmount, lastGeneratedYCoor=0;

    PlateManager(Camera camera) {
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        System.out.println(maxAmount);
    }


    void update() {
        objects.removeIf(object -> object.getHeight() > camera.getY() + camera.getHeight());
        //TODO: add layer adding
        if (camera.getY() < lastGeneratedYCoor -100) {
            objects.addAll(generateNewLayer(camera.getY(),0.3));

        }
    }

    ArrayList<GameObject> getObjects() {
        return objects;
    }

    ArrayList<GameObject> generateNewLayer(int yCoor, double probability) {
        ArrayList<GameObject> plates = new ArrayList<>();
        System.out.println("yCoor = [" + yCoor + "], probability = [" + probability + "]");
        System.out.println("lastGeneratedYCoor = " + lastGeneratedYCoor);
        lastGeneratedYCoor = yCoor - (yCoor % 100);
        System.out.println("lastGeneratedYCoor = " + lastGeneratedYCoor);
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() < probability) {
                plates.add(new Plate(100 * i +10, lastGeneratedYCoor));
            }
        }
        return plates;
    }
}
