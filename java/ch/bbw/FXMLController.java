package ch.bbw;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager manager;
    private Image kangaroo, kangarooJumping, kangarooFalling;

    //TODO: Add Restart
    private void draw() {
        //FIXME: canvas
        gc.setFill(Color.SKYBLUE);
        Camera camera = manager.getCamera();
        gc.fillRect(0, camera.getY(), canvas.getWidth(), canvas.getHeight() - camera.getY());

        gc.translate(0, -camera.getY());

        gc.setFill(Color.SADDLEBROWN);
        for (GameObject object : manager.getGameObjects()) {

            gc.fillRect(object.getPositionX(), object.getPositionY(), object.getWidth(), object.getHeight());


        }
        Player player = manager.getPlayer();
        if (player.isOnGround()) {
            gc.drawImage(kangaroo, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        } else if (player.getVelocityY() > 0) {
            gc.drawImage(kangarooFalling, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        } else {
            gc.drawImage(kangarooJumping, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        }


        if (player.getPositionY() > camera.getHeight() + camera.getY()) {
            System.exit(0);
        }

        gc.translate(0, camera.getY());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        manager = new GameManager(new Player(0, 0, 100, 100), new Camera(0, (int) canvas.getHeight(), (int) canvas.getWidth()));

        canvas.setFocusTraversable(true);
        kangaroo = new Image(getClass().getResourceAsStream("/kangaroo.png"));
        kangarooFalling = new Image(getClass().getResourceAsStream("/kangaroofalling.png"));
        kangarooJumping = new Image(getClass().getResourceAsStream("/kangarooJumping.png"));

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(16.666666666667),
                ae -> {
                    manager.update();
                    draw();
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        manager.startMoving(keyEvent.getCode());
    }

    @FXML
    private void keyReleased(KeyEvent keyEvent) {
        manager.stopMoving(keyEvent.getCode());
    }

}
