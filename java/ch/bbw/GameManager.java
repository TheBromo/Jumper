package ch.bbw;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

class GameManager {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Player player;
    private Camera camera;

    GameManager(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
        getGameObjects().add(new Plate(40, 70));
    }

    void update(long passedTime) {

        //moves the player
        player.update(passedTime);

        //moves all tiles according to
        for (GameObject object : gameObjects) {
            object.update(passedTime);
        }

        //deletes all Objects out of sight
        getGameObjects().removeIf(object -> object.positionY > camera.getY() + camera.getHeight());

        //checks if players feet are colliding
        for (GameObject object : gameObjects) {
            player.checkCollision(object, camera.getY());
        }
        //TODO: Generate new padles in view

        camera.update(player.positionY);

    }


    void stopMoving(KeyCode kc) {
        if (kc.equals(KeyCode.LEFT) || kc.equals(KeyCode.RIGHT)) {
            player.setVelocityX(0);
        }

    }

    void startMoving(KeyCode kc) {

        if (kc.equals(KeyCode.LEFT)) {
            player.setVelocityX(-5.0);
        } else if (kc.equals(KeyCode.RIGHT)) {
            player.setVelocityX(5.0);
        }
    }

    ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    Player getPlayer() {
        return player;
    }

    Camera getCamera() {
        return camera;
    }
}
