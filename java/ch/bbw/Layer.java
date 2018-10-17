package ch.bbw;

import java.util.ArrayList;
import java.util.Random;

class Layer {
    private ArrayList<Plate> plates = new ArrayList<>();
    private int height;

    public Layer(int maxAmount, int yCoor, double probability) {

    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Plate> getPlates() {
        return plates;
    }
}
