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
        getGameObjects().add(new Plate(40, 70));
        amount = (camera.getWidth() - (camera.getWidth() % 100)) / 100;
        System.out.println(amount);
    }

    void update(long passedTime) {

        //moves the player
        player.update(passedTime);

        //moves all tiles according to
        for (GameObject object : plateManager.getLayers()) {
            object.update(passedTime);
        }

        //checks if players feet are colliding
        for (GameObject object : plateManager.getLayers()) {
            player.checkCollision(object, camera.getY());
        }
        //adds and removes plates
        plateManager.update();


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
        return plateManager.getLayers();
    }

    Player getPlayer() {
        return player;
    }

    Camera getCamera() {
        return camera;
    }
}
