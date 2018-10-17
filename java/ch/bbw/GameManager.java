package ch.bbw;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

class GameManager {

    private PlateManager plateManager;
    private Player player;
    private Camera camera;
    private int amount;

    GameManager(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
        plateManager = new PlateManager(camera);
        getGameObjects().add(new Plate(40, 0));
        amount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
    }

    void update() {

        //moves the player
        player.update();
        //adds and removes plates
        plateManager.update();

        //checks if players feet are colliding
        for (GameObject object : plateManager.getObjects()) {
            player.checkCollision(object);
            if (player.isOnGround()) break;
        }

        camera.update(player.getPositionY());
    }


    void stopMoving(KeyCode kc) {
        //TODO: Add ad controls
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
        return plateManager.getObjects();
    }

    Player getPlayer() {
        return player;
    }

    Camera getCamera() {
        return camera;
    }
}
