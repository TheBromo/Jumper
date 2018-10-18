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
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager manager;
    private Image kangaroo, kangarooJumping, kangarooFalling,background;
    private boolean lost;
    private Timeline timeline;


    //TODO: Make The paddles a snake
    //TODO: implement highscore
    private void draw() {
        if (lost) {
            gc.setFill(Color.PAPAYAWHIP);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.web("#360E0B"));
            gc.fillText("Your Score: "+ -manager.getCamera().getY()+"\n\n\nOh no!\n It seems like you've lost.\n\n \nTo try again \npress any key.",canvas.getWidth()/2,canvas.getHeight()/2);

        } else {

            Camera camera = manager.getCamera();
            gc.drawImage(background, 0,0, canvas.getWidth(), canvas.getHeight());

            gc.translate(0, -camera.getY());

            gc.setFill(Color.SADDLEBROWN);
            for (GameObject object : manager.getGameObjects()) {

                gc.fillRect(object.getPositionX(), object.getPositionY(), object.getWidth(), object.getHeight());
            }

            Player player = manager.getPlayer();
            gc.setFill(Color.web("#f28452"));
            gc.fillText("Score: " + -camera.getY(), canvas.getWidth()/2, camera.getY()+20);

            if (player.getPositionX() + player.getWidth() > canvas.getWidth()) {
                if (player.isOnGround()) {
                    gc.drawImage(kangaroo, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                    gc.drawImage(kangaroo, player.getPositionX() - canvas.getWidth(), player.getPositionY(), player.getWidth(), player.getHeight());

                } else if (player.getVelocityY() > 0) {
                    gc.drawImage(kangarooFalling, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                    gc.drawImage(kangarooFalling, player.getPositionX() - canvas.getWidth(), player.getPositionY(), player.getWidth(), player.getHeight());
                } else {
                    gc.drawImage(kangarooJumping, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                    gc.drawImage(kangarooJumping, player.getPositionX() - canvas.getWidth(), player.getPositionY(), player.getWidth(), player.getHeight());
                }
            } else {
                if (player.isOnGround()) {
                    gc.drawImage(kangaroo, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                } else if (player.getVelocityY() > 0) {
                    gc.drawImage(kangarooFalling, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                } else {
                    gc.drawImage(kangarooJumping, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
                }
            }


            if (player.getPositionY() > camera.getHeight() + camera.getY()) {
                lost = true;
            }


            gc.translate(0, camera.getY());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        manager = new GameManager(new Player(0, 0, 80, 80, (int) canvas.getWidth()), new Camera(0, (int) canvas.getHeight(), (int) canvas.getWidth()));
        lost = false;
        canvas.setFocusTraversable(true);
        kangaroo = new Image(getClass().getResourceAsStream("/kangaroo.png"));
        kangarooFalling = new Image(getClass().getResourceAsStream("/kangaroofalling.png"));
        kangarooJumping = new Image(getClass().getResourceAsStream("/kangarooJumping.png"));
        background = new Image(getClass().getResourceAsStream("/background.png"));

        gc.setFont(Font.loadFont(ClassLoader.getSystemResource("Mali-Regular.ttf" ).toExternalForm(), 40));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        timeline = new Timeline(new KeyFrame(
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
        if (lost){
            timeline.stop();
            initialize(null,null);
        }else {
            manager.startMoving(keyEvent.getCode());
        }
    }

    @FXML
    private void keyReleased(KeyEvent keyEvent) {
        manager.stopMoving(keyEvent.getCode());
    }

}
