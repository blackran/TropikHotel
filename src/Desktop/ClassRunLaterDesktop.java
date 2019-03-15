/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author blackran
 */
public class ClassRunLaterDesktop extends Thread {
    public void run(String stockNumResponsable, AnchorPane principal){
        try{
            Thread.sleep(1);
            Platform.runLater(() -> {
                try {
                    principal.getChildren().clear();
                    Stage stage = (Stage)principal.getScene().getWindow();
                    stage.setResizable(false);
                    Scene scene = principal.getScene();
                    scene.getStylesheets().clear();
                    scene.getStylesheets().add(ClassRunLaterDesktop.this.getClass().getResource("/Desktop/StylesDesktop.css").toExternalForm());
                    scene.setFill(Color.TRANSPARENT);
                    FXMLLoader Loader  = new FXMLLoader();
                    Loader.setLocation(ClassRunLaterDesktop.this.getClass().getResource("/Desktop/FXMLDesktop.fxml"));
                    Parent root = Loader.load();
                    principal.getChildren().add(root);
                    ControlerDesktop disp = Loader.getController();
                    disp.setT(String.valueOf(stockNumResponsable));
                    stage.setWidth(1000);
                    stage.setHeight(729);
                    javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                }catch (IOException ex) {
                    Logger.getLogger(ClassRunLaterDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(ClassRunLaterDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
