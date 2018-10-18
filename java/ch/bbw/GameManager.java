package ch.bbw;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

class GameManager {
    private PlateManager plateManager;
    private Player player;
    private Camera camera;

    GameManager(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
        plateManager = new PlateManager(camera);
        getGameObjects().add(new Plate(40, 0, new Image(getClass().getResourceAsStream(FXMLController.wood1Path))));
        getGameObjects().add(new Plate(10, -150, new Image(getClass().getResourceAsStream(FXMLController.wood2Path))));
    }

    void update() {

        //moves the player
        player.update();
        //adds and removes plates
        plateManager.update();

        //checks if players feet are colliding
        for (GameObject object : plateManager.getObjects()) {
            player.checkCollision(object);
            if (player.isOnGround()){
                player.collide(object);
                break;
            }
        }

        camera.update(player.getPositionY());
    }


    void stopMoving(KeyCode kc) {
        if (kc.equals(KeyCode.LEFT) || kc.equals(KeyCode.RIGHT) || kc.equals(KeyCode.A) || kc.equals(KeyCode.D)) {
            player.setVelocityX(0);
        }
    }

    void startMoving(KeyCode kc) {
        if (kc.equals(KeyCode.LEFT) || kc.equals(KeyCode.A)) {
            player.setVelocityX(-5.0);
        } else if (kc.equals(KeyCode.RIGHT) || kc.equals(KeyCode.D)) {
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
