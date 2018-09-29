package ch.bbw;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
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

public class FXMLController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager manager;
    private Image kangaroo;
    private AnimationTimer animationTimer;
    private long last ;

    private void draw() {
        gc.setFill(Color.SKYBLUE);
        Camera camera = manager.getCamera();

        gc.fillRect(0, camera.getY(), canvas.getWidth(), canvas.getHeight() - camera.getY());

        if (camera.isChanged()) {
            gc.translate(0, -camera.getY());
        }
        gc.setFill(Color.SADDLEBROWN);
        for (GameObject object : manager.getGameObjects()) {
            gc.fillRect(object.getPositionX(), object.getPositionY() - camera.getY(), object.getWidth(), object.getHeight());
        }
        GameObject object = manager.getPlayer();
        gc.drawImage(kangaroo, object.getPositionX(), object.getPositionY(), object.getWidth(), object.getHeight());

        gc.setFill(Color.RED);
        for (int i = 0; i < camera.getHeight(); i = i + 100) {
            gc.fillRect(0,i,camera.getWidth(),1);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        manager = new GameManager(new Player(0, 0, 100, 100), new Camera(0, (int) canvas.getHeight(), (int) canvas.getWidth()));

        canvas.setFocusTraversable(true);
        kangaroo = new Image(getClass().getResourceAsStream("/kangaroo.png"));

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                manager.update(TimeUnit.NANOSECONDS.toMillis(now - last));
                last = now;
                draw();
            }
        };
        animationTimer.start();
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
