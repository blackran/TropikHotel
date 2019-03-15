/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLLoadingController implements Initializable {
    
    @FXML private AnchorPane principale;
    @FXML private ProgressBar progressbar;
    
    public void setT(String s){
        progressbar.setMaxWidth(Double.MAX_VALUE);
        IntegerProperty seconds = new SimpleIntegerProperty();
        progressbar.progressProperty().bind(seconds.divide(60.0));
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
            new KeyFrame(Duration.minutes(0.05), e-> {
                ClassRunLaterDesktop desk = new ClassRunLaterDesktop();
                desk.run(s, principale);
            }, new KeyValue(seconds, 60))   
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
