/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLeditResponsableController implements Initializable {
    @FXML private ComboBox cbUserResponsables;
    @FXML private AnchorPane principaleResp;
    @FXML private ImageView imageText;
    @FXML private Label dtNumResponsable,lbImgUrlDetail;
    @FXML private JFXTextField txtNomDetail,txtPrenomDetail,txtPseudoDetail,txtPassDetail,txtAddressDetail,txtTelDetail;
    @FXML private Pane movePane;
    
    DaoResponsables daoresponsables = new DaoResponsables();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbUserResponsables.getItems().addAll("SUPERS","USERS");
        lbImgUrlDetail.setVisible(false);
    }
    
    @FXML public void afficheDetail(String e) throws ClassNotFoundException, SQLException{
        if(Integer.parseInt(e) < daoresponsables.find(daoresponsables.findAll().size()).getNumResponsable()+1){
            Responsables resp = daoresponsables.find(Integer.parseInt(e));
            Image image = new Image("file:"+resp.getImageUrlResponsable());
            imageText.setImage(image);
            
            lbImgUrlDetail.setText(resp.getImageUrlResponsable());
            txtNomDetail.setText(resp.getNomResponsable());
            txtPrenomDetail.setText(resp.getPrenomResponsable());
            txtPseudoDetail.setText(resp.getPseudoResponsable());
            txtPassDetail.setText(resp.getPasswordResponsable());
            cbUserResponsables.getSelectionModel().select(resp.getDroitResponsable());
            txtAddressDetail.setText(resp.getAddressResponsable());
            txtTelDetail.setText(resp.getTelResponsable());
        }else{
            Responsables resp = daoresponsables.find(Integer.parseInt(e));
            Image image = new Image("/Desktop/images/DefaultUser.png");
            imageText.setImage(image);

            lbImgUrlDetail.setText("/Desktop/images/DefaultUser.png");
            txtNomDetail.setText("");
            txtPrenomDetail.setText("");
            txtPseudoDetail.setText("");
            txtPassDetail.setText("");
            cbUserResponsables.getSelectionModel().selectFirst();
            txtAddressDetail.setText("");
            txtTelDetail.setText("");
        }
    }
    
    @FXML public  void onClickQuit(){
        Stage stage = (Stage)principaleResp.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void filesSources() throws MalformedURLException{
        Stage stage = (Stage)principaleResp.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageText.setImage(image);
            lbImgUrlDetail.setText(file.toURI().toString().substring(5, file.toURI().toString().length()));
        }
    }
    @FXML public void setT(String e) throws ClassNotFoundException, SQLException{
        dtNumResponsable.setText(e);
        this.afficheDetail(e);
    }
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    public void OnMousePressed(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
    }
    
    public void OnMouseDragged(MouseEvent event) {
        Stage stage = (Stage)principaleResp.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    
    @FXML public void ajouterResponsable() throws SQLException, ClassNotFoundException{
        if(Integer.parseInt(dtNumResponsable.getText()) < daoresponsables.find(daoresponsables.findAll().size()).getNumResponsable()+1){
            daoresponsables.mod(Integer.parseInt(dtNumResponsable.getText()), txtNomDetail.getText(), txtPseudoDetail.getText(), txtPassDetail.getText(), txtPrenomDetail.getText(), txtAddressDetail.getText(), txtTelDetail.getText(), cbUserResponsables.getValue().toString(), lbImgUrlDetail.getText());
            System.out.println("voici Num RESPONSABLE: "+daoresponsables.find(Integer.parseInt(dtNumResponsable.getText())).getPrenomResponsable());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("AFFIRMATION");
            alert.setContentText("Modification  reussi");
            alert.show();
        }else{
            daoresponsables.add(Integer.parseInt(dtNumResponsable.getText()), txtNomDetail.getText(), txtPseudoDetail.getText(), txtPassDetail.getText(), txtPrenomDetail.getText(), txtAddressDetail.getText(), txtTelDetail.getText(), (String) cbUserResponsables.getValue(), lbImgUrlDetail.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("AFFIRMATION");
            alert.setContentText("ajout  reussi");
            alert.show();
        }
    }
    
}
