/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Notification;

import Desktop.ControlerDesktop;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tropikhotel.DAO.DaoAfaire;
import tropikhotel.GetSet.Afaire;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class AfaireController implements Initializable {

    
    @FXML 
    private Button btnHide;
    @FXML
    private JFXMasonryPane jfxmasonrypane;
    
    @FXML
    private JFXTextField inputTodo;

    @FXML
    private AnchorPane readFaire;
    
    @FXML 
    private Label lbCopyrigth;
    
    DaoAfaire daoafaire = new DaoAfaire();
    
      
    @FXML public void setT() throws IOException{
        FXMLLoader Loader  = new FXMLLoader(getClass().getResource("/Desktop/FXMLDesktop.fxml"));
        
        Loader.setRoot(Loader.load()); 
       
        ControlerDesktop disp = Loader.getController();
        disp.getNotificationChildren(readFaire.getChildrenUnmodifiable());
    }

    public void getPrincipale(AnchorPane Notification, VBox aNotif) {
        
        btnHide.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                Notification.setVisible(false);
                aNotif.getChildren().forEach((e) ->{
                    e.setStyle("-fx-background-color: #202020;");
                });
        });
    }
    public void affichageList() throws SQLException, ClassNotFoundException{
        jfxmasonrypane.getChildren().clear();
        ArrayList<Afaire> af = daoafaire.findAll();
        af.forEach(e->{
            JFXCheckBox jfxcheckbox = new JFXCheckBox();
            jfxcheckbox.setStyle("-fx-text-fill: #fefefe;-fx-wrap-text: true;");
            jfxcheckbox.setSelected(e.getEtatAfaire().equals("1"));
            jfxcheckbox.setText(e.getDescriptionAfaire());
            jfxcheckbox.setId("'"+e.getNumAfaire()+"'");
            jfxcheckbox.setOnMouseClicked(h->{
                try {
                    this.onClickMod(h);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AfaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            jfxmasonrypane.getChildren().add(jfxcheckbox);
        }); 
    }
    
    @FXML public void onClickMod(MouseEvent e) throws SQLException, ClassNotFoundException{
        String Stock = ""; 
        String nouv = e.toString().substring(37);
        boolean first = true;
        int i = 0;
        while(first){
            String oa = String.valueOf( nouv.charAt(i) );
            if(oa.matches("[0-9]*")){
                Stock = Stock + oa;
            }else{
                first=false;
            }
            i++;
        }
        daoafaire.modDescription(Integer.parseInt(Stock));
        this.affichageList(); 
    }
    
    @FXML
    public void onClickEnter(KeyEvent e) throws SQLException, ClassNotFoundException{
        if (e.getCode().equals(KeyCode.ENTER)) {
            if(daoafaire.findAll().size()<8){
                daoafaire.add(inputTodo.getText(), "0");
                inputTodo.setText("");
                this.affichageList(); 
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("REMARQUE");
                alert.setContentText("nombre de tache maximum est 8");
                alert.show();
            }
            
        }else
	if (inputTodo.getText().length()>20){
            inputTodo.setText(inputTodo.getText().substring(0, 20));
	}
        int positionFin = inputTodo.getText().length();
        if ( inputTodo.getCaretPosition() != positionFin ) {
            inputTodo.positionCaret(positionFin);
        }
    }
    
    @FXML
    public void removeAllFinish() throws ClassNotFoundException, SQLException{
        daoafaire.RemoveEtatFinish();
        this.affichageList();
    }
    
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
            Logger.getLogger(AfaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.affichageList();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AfaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
