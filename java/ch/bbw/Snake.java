package ch.bbw;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake extends GameObject {
    private Image neutral, jump, after, actual;
    private int steps;

    public Snake(int positionX, int positionY, int height, int width, Image neutral, Image jump, Image after) {
        super(positionX, positionY, height, width);
        this.neutral = neutral;
        this.after = after;
        this.jump = jump;
        actual = neutral;
    }


    @Override
    public void update() {
        steps++;
        if (steps > 20) {
            if (actual.equals(jump)) {
                actual = after;
                setHeight(20);
                steps = 0;
            } else if (actual.equals(after)) {
                actual = neutral;
                setHeight(10);
            }
            steps = 0;
        }
    }

    @Override
    public void collide(GameObject object) {
        if (object instanceof Player) {
            actual = jump;
            setHeight(20);
        }
    }

    public Image getImg() {
        return actual;
    }
}
