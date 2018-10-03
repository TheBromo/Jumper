package ch.bbw;

import java.util.ArrayList;
import java.util.Random;

class Layer {
    private ArrayList<Plate> plates = new ArrayList<>();
    private int height;

    public Layer(int maxAmount, int yCoor, double probability) {
        height = yCoor;
        for (int i = 0; i < maxAmount; i++) {
            if (Math.random() < probability) {
                plates.add(new Plate(100 * i, yCoor));
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Plate> getPlates() {
        return plates;
    }
}
