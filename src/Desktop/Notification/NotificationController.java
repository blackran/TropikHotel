/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Notification;

import Desktop.ControlerDesktop;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class NotificationController implements Initializable {

    @FXML
    private AnchorPane ReadNotif;
    
    @FXML private Label lbCopyrigth;

    @FXML private Button btnHide, btnNo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.setT();
            LocalDate dt = LocalDate.now();
            lbCopyrigth.setText("©"+dt.getYear()+" | R.Andrianantenaina");
        } catch (IOException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    @FXML public void setT() throws IOException{
        FXMLLoader Loader  = new FXMLLoader(getClass().getResource("/Desktop/FXMLDesktop.fxml"));
        
        Loader.setRoot(Loader.load()); 
       
        ControlerDesktop disp = Loader.getController();
        System.out.println(disp);
        disp.getNotificationChildren(ReadNotif.getChildrenUnmodifiable());
    }

    public void getPrincipale(AnchorPane Notification, VBox aNotif) {
        
        btnHide.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                Notification.setVisible(false);
                aNotif.getChildren().forEach((e) ->{
                    e.setStyle("-fx-background-color: #202020;");
                });
        });
    }
    
}
