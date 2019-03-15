/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import tropikhotel.DAO.DaoResponsables;

/**
 *
 * @author blackran
 */
public class ClassRunLater extends Thread {
    public void run(Label stockNumResponsable, Circle circle){
        try{
            Thread.sleep(1);
            Platform.runLater(() -> {
                try {
                    DaoResponsables res = new DaoResponsables();
                    String urls = res.find(Integer.parseInt(stockNumResponsable.getText())).getImageUrlResponsable();
                    Image image = new Image("file:"+urls);
                    System.out.println("file:"+urls);

                    if(image.isError()){
                        image = new Image("/Desktop/images/User.jpg");
                    }
                    circle.setFill(new ImagePattern(image));
                    circle.setStroke(Paint.valueOf("white"));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ClassRunLater.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(ClassRunLater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
