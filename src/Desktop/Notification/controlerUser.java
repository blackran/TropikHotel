/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Notification;

import Desktop.ClassRunLater;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

/**
 *
 * @author blackran
 */
public class controlerUser implements Initializable{
    @FXML private Label label, stockNumResponsable;
    @FXML private Circle circle;
    @FXML
    private AnchorPane readUser;
    @FXML
    private Label lbCopyrigth;

    @FXML private TextField lbNomUser, lbPrenomUser, lbTelUser;
    
    @FXML private TextArea lbAddressUser;
    @FXML private Button btnHide;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClassRunLater later = new ClassRunLater();
        later.run(stockNumResponsable, circle);
        stockNumResponsable.setVisible(false);
        try {
            this.setT();
        } catch (IOException ex) {
            Logger.getLogger(controlerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML public void setT() throws IOException{
       
    }
    
    @FXML public void onClickHide(MouseEvent e){
        
    }
    
    @FXML public void getT(String s) throws ClassNotFoundException, SQLException{
        this.stockNumResponsable.setText(s);
        this.affichageOneResponsable(s);
    }
    public void hide(MouseEvent e){
        //System.out.println(label.getParent() +" "+ e.getTarget());
    }
    public void affichageOneResponsable(String e) throws ClassNotFoundException, SQLException{
        DaoResponsables daoresponsables = new DaoResponsables();
        Responsables responsable = daoresponsables.find(Integer.parseInt(e));
        lbNomUser.setText(responsable.getNomResponsable());
        lbPrenomUser.setText(responsable.getPrenomResponsable());
        lbAddressUser.setText(responsable.getAddressResponsable());
        lbTelUser.setText(responsable.getTelResponsable());
        LocalDate dt = LocalDate.now();
        lbCopyrigth.setText("©"+dt.getYear()+" | R.Andrianantenaina");
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
