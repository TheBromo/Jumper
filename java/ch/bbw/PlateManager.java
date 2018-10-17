package ch.bbw;

import java.util.ArrayList;

public class PlateManager {
    private ArrayList<Layer> layers = new ArrayList<>();
    private Camera camera;
    private int maxAmount;

    PlateManager(Camera camera) {
        this.camera = camera;
        maxAmount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
    }


    public void createNewLayer() {
        layers.add(new Layer(maxAmount, camera.getHeight(), 0.7));
    }

    public void update() {
        layers.removeIf(object -> object.getHeight() > camera.getY() + camera.getHeight());
        //TODO: add layer adding
    }

    public ArrayList<GameObject> getLayers() {
        ArrayList<GameObject> objects = new ArrayList<>();
        layers.forEach(layer -> objects.addAll(layer.getPlates()));
        return objects;
    }
}
