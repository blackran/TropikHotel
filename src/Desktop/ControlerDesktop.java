/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import Desktop.Children.ControlerReservations;
import Desktop.Notification.AfaireController;
import Desktop.Notification.NotificationController;
import Desktop.Notification.controlerUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

/**
 *
 * @author robot
 */
public class ControlerDesktop implements Initializable {
    @FXML private AnchorPane pClient, pChambre, pReservation, pRepas, stockChildrenNotif;
    @FXML private ImageView imgClients, imgChambres, imgReservations,imgRepas;
    @FXML private Label lbClients, lbChambre, lbReservation, lbRepas,stockNumResponsable;

    @FXML private AnchorPane pUser, pToDo, pNotif, pConfig, pExit, Notification, loginConfig;
    @FXML private Circle circle;

    private AnchorPane aTop;

    @FXML private AnchorPane anch, principal;
    
    @FXML private PasswordField txtLoginConfig;
    @FXML private ComboBox cbLoginConfig;
    
    @FXML private VBox VBoxBlur;
    
    private final ObservableList<Node> te = FXCollections.observableArrayList();
    @FXML
    private AnchorPane aNavigator;
    @FXML
    private AnchorPane ooooooo;
    @FXML
    private VBox aNotif;
    
    @FXML private Label annee, hours, mois;
    
    
    
    
    //public String te;
    
    @SuppressWarnings("empty-statement")
    @FXML
    public void onClickLogOut(MouseEvent e) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("QUESTION");
        alert.setContentText("vous voulez quiter vraiment?");
        alert.show();
        alert.setOnHidden(ev->{
            if("OK".equals(alert.getResult().getText())){
                try {
                    Stage stage = (Stage)pExit.getScene().getWindow();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/tropikhotel/FXML.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/tropikhotel/styles.css").toExternalForm());
                    scene.setFill(Color.TRANSPARENT);
                    primaryStage.setTitle("Tropik Hotel");
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();  
                    stage.hide();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ControlerDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
        });
    }
    public  void onClickQuit(){
        Platform.exit();
        System.exit(0);
    }
    public void minimize(MouseEvent event){
        Stage stage = (Stage)aTop.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void loadFxml(int i, String name) throws IOException, ClassNotFoundException, SQLException{
        switch (i) {
            case 0:{
                    anch.getChildren().clear();
                    FXMLLoader Loader  = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("/Desktop/Children/"+name+".fxml"));
                    Parent root = Loader.load();
                    if("Reservations".equals(name)){
                       ControlerReservations rese = Loader.getController();
                       rese.getT(stockNumResponsable.getText());
                    }
                    anch.getChildren().add(root);
                    break;
            }
            case 1:{
                    Notification.setVisible(true);
                    FXMLLoader Loader  = new FXMLLoader();
                        Loader.setLocation(getClass().getResource("/Desktop/Notification/" +name+".fxml"));
                        Parent root = Loader.load();
                        if("User".equals(name)){   
                            controlerUser rese = Loader.getController();
                            rese.getT(stockNumResponsable.getText());
                        }
                        this.tranferData(name, Loader,aNotif);
                    Notification.getChildren().clear();
                    root.setId("AAAA");
                    Notification.getChildren().add(root);
                    
                    break;
            }
            default:
                Notification.setVisible(false);
                break;
        }
        
    }
    
    public void tranferData(String name, FXMLLoader Loader,VBox aNotif){
        if(null != name)switch (name) {
            case "User":
                controlerUser rese = Loader.getController();
                rese.getPrincipale(Notification, aNotif);
                break;
            case "Afaire":
                AfaireController afaire = Loader.getController();
                afaire.getPrincipale(Notification, aNotif);
                break;
            case "Notification":
                NotificationController not = Loader.getController();
                not.getPrincipale(Notification, aNotif);
                break;
            default:
                break;
        }
    }
    public void params(int i , int j){
        // valeur de i:
        //   0: style de navigation placer en haut
        //   1: style de paneau en gauche defile les notification
        //   2: les paneaux voir plus sur les notification
        // valeur de j:

        int [] tab = new int[6];
        for(int a=0;a<6;a++){
            if(a==j){
                tab[a]=0;
            }else{
                tab[a]=1;
            }
        }
        switch (i) {
            case 0:
                Notification.setVisible(false);
                loginConfig.setVisible(false);
                params(1,6);
                String [] color1 = {"-fx-background-color: #041105; -fx-effect: dropshadow(gaussian, #000000 , 10, 0, 0, 0);","-fx-background-color: transparent;"};
                String [] color3 = {"-fx-text-fill:#ffffff;","-fx-text-fill:e0e0e0;-fx-effect: dropshadow(gaussian, #e0e0e0 , 2, 0, 0, 0);"};
                pClient.setStyle(color1[tab[0]]);
                lbClients.setStyle(color3[tab[0]]);
                pChambre.setStyle(color1[tab[1]]);
                lbChambre.setStyle(color3[tab[1]]);
                pReservation.setStyle(color1[tab[2]]);
                lbReservation.setStyle(color3[tab[2]]);
                pRepas.setStyle(color1[tab[3]]);
                lbRepas.setStyle(color3[tab[3]]);
                break;
            case 1:
                loginConfig.setVisible(false);
                String [] color2 = {"-fx-background-color: #0b6623;","-fx-background-color: #202020;"};
                pUser.setStyle(color2[tab[0]]);
                pToDo.setStyle(color2[tab[2]]);
                pNotif.setStyle(color2[tab[3]]);
                pConfig.setStyle(color2[tab[4]]);
                pExit.setStyle(color2[tab[5]]);
                break;
            default:
                break;
        }
           
    }
    public void background(int i){
        String d;
        int val;
        String [] tab = {"115-users","001-home","084-calendar","164-spoon-knife"};
        ImageView [] imgV = {imgClients, imgChambres, imgReservations,imgRepas};
        String [] color3 = {"","-fx-effect: dropshadow(gaussian, #e0e0e0 , 2, 0, 0, 0);"};
        for(int a=0;a<imgV.length;a++){
            if(a==i){
               d="";
               val=0;
            }else{
               d="-d";
               val=1;
            }
            Image image = new Image("/Desktop/images/"+tab[a]+d+".png",false);
            imgV[a].setImage(image);
            imgV[a].setStyle(color3[val]);
        }
    }
    @FXML
    public void onClicFocus(MouseEvent e) throws IOException, ClassNotFoundException, SQLException{
        if(e.getTarget() == imgClients || e.getTarget() == pClient || e.getTarget().toString().indexOf("Clients") > 0){
            params(0,0);
            loadFxml(0,"Clients");
            background(0);
        }else
        if(e.getTarget() == imgChambres || e.getTarget() == pChambre || e.getTarget().toString().indexOf("Chambres") > 0){
            params(0,1);
            loadFxml(0,"Chambres");
            background(1);
        }else
        if(e.getTarget() == imgReservations || e.getTarget() == pReservation || e.getTarget().toString().indexOf("Reservations") > 0){
            params(0,2);
            loadFxml(0,"Reservations");
            background(2);
        }else
        if(e.getTarget() == imgRepas || e.getTarget() == pRepas || e.getTarget().toString().indexOf("Repas") > 0){
            params(0,3);
            loadFxml(0,"Repas");
            background(3);
        }
    }
    
    public void getNotificationChildren(ObservableList<Node> childrenUnmodifiable) {
        //System.out.println(stockChildrenNotif.getChildren());
    }
    
    
    @FXML public void onClickNotif(MouseEvent e) throws IOException, ClassNotFoundException, SQLException{
        
        if(e.getTarget() == pUser || e.getTarget() == circle){
            loadFxml(1,"User");
            params(1,0);
        }else
        if(e.getTarget() == pToDo){
            loadFxml(1,"Afaire");
            params(1,2);
        }else
        if(e.getTarget() == pNotif){
            loadFxml(1,"Notification");
            params(1,3);
        }else
        if(e.getTarget() == pConfig){
            params(0,4);
            background(4);
            Notification.setVisible(false);
            loginConfig.setVisible(true);
            BoxBlur boxblur = new BoxBlur();
            VBoxBlur.setEffect(boxblur);
            txtLoginConfig.requestFocus();
        }else{
        }
    }
    
    //pour login sur le configuration
    
    @FXML private void loginConfigAnnul(){
        loginConfig.setVisible(false);
        txtLoginConfig.setText("");
        VBoxBlur.setEffect(null);
    }
    
    @FXML private void loginConfigValid() throws IOException{
        try {
            DaoResponsables resp = new DaoResponsables();
            ArrayList<Responsables> respArr = resp.searchAll(String.valueOf(cbLoginConfig.getValue().toString()));
            if(!respArr.isEmpty()){
                for(int i=0;i<respArr.size();i++){
                    if(respArr.get(i).getPseudoResponsable().equals(String.valueOf(cbLoginConfig.getValue().toString())) && respArr.get(i).getPasswordResponsable().equals(txtLoginConfig.getText())){
                        loadFxml(0,"Configuration");
                        this.loginConfigAnnul();
                        params(1,4);
                        VBoxBlur.setEffect(null);   
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControlerDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void onKeyEnter(KeyEvent e){
        if(e.getCode().equals(KeyCode.ENTER)){
            try {
                this.loginConfigValid();
            } catch (IOException ex) {
                Logger.getLogger(ControlerDesktop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void setT(String s){
        this.stockNumResponsable.setText(s);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            params(2,5);
            stockNumResponsable.setVisible(false);
            Notification.setVisible(false);
            loginConfig.setVisible(false);
            DaoResponsables daoresponsables = new DaoResponsables();
            ArrayList<Responsables> respUser = daoresponsables.findAllSuper();
            
            ObservableList <String> listSuper = FXCollections.observableArrayList();
            for(int i= 0;i<respUser.size();i++){
                listSuper.add(String.valueOf(respUser.get(i).getPseudoResponsable()));
            }
            cbLoginConfig.setItems(listSuper);
            cbLoginConfig.getSelectionModel().select(respUser.get(0).getPseudoResponsable());
            stockNumResponsable.setText(String.valueOf(respUser.get(0).getNumResponsable()));
            
            params(0,2);
            loadFxml(0,"Reservations");
            background(2);
            
            ClassRunLater later = new ClassRunLater();
            later.run(stockNumResponsable, circle);
            
            LocalDateTime dt = LocalDateTime.now();
            
            annee.setText(String.valueOf(dt.getYear()));
            hours.setText(String.valueOf(dt.format(DateTimeFormatter.ofPattern("dd"))));
            mois.setText(String.valueOf(dt.getMonth()));
            
            
    
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControlerDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
