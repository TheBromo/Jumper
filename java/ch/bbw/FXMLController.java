package ch.bbw;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager manager;

    public void draw(){

    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        manager = new GameManager();
    }    
}
