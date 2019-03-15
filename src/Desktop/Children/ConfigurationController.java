/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import Desktop.ClassRunLaterDesktop;
import com.jfoenix.controls.JFXMasonryPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoReglements;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

/**
 * FXML Controller class
 *
 * @author blackran
 */

public class ConfigurationController {

    @FXML
    private AnchorPane CPaysClient;
    
    @FXML private Label lbNumResponsable, statusPie, statusLine;

    @FXML
    private Button btnEnrClient;

    @FXML
    private Button btnSupClient;

    @FXML
    private Button btnModClient;

    @FXML
    private VBox vboxConfig;
    
    @FXML
    private ImageView imageText;

    @FXML private JFXMasonryPane jfxmasonrypane;
    
    @FXML private Parent edit;
    
    @FXML private PieChart piechart;
    
    @FXML private LineChart<String, Number> lineChart;
    
    @FXML private StackedBarChart<String, Number> stackedBarChart;
     

    DaoClients daoclients = new DaoClients();
    DaoReglements daoreglements = new DaoReglements();
    
    /**
     *
     */
    @FXML public void filesSources(){
        Stage stage = (Stage)btnEnrClient.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.toPath()+" | "+file.toURI().toString());
            Image image = new Image(file.toURI().toString());
            imageText.setImage(image);
        }
    }

    
    @FXML public void affImageAll(){
        vboxConfig.setId("");
    }
    
    public void affichageAllResponsable() throws SQLException, ClassNotFoundException{
        jfxmasonrypane.getChildren().clear();
        DaoResponsables daoresponsables = new DaoResponsables();
        ArrayList<Responsables> resp = daoresponsables.findAll();
        int i=0;
        resp.stream().map((res) -> {
            ImageView imageview1 = new ImageView();
            Image image1 = new Image("/Desktop/images/numResponsable.png");
            imageview1.setImage(image1);
            imageview1.setFitWidth(60);
            imageview1.setFitHeight(60);
            
            ImageView imageSuprimer = new ImageView();
            Image image3 = new Image("/Desktop/images/delete1.png");
            imageSuprimer.setImage(image3);
            imageSuprimer.setFitWidth(16);
            imageSuprimer.setFitHeight(16);
            imageSuprimer.setLayoutX(120);
            imageSuprimer.setLayoutY(177);
            imageSuprimer.setOnMouseClicked(e->{
                try {
                    this.onClickSuprimer(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            ImageView imageModifier = new ImageView();
            Image image4 = new Image("/Desktop/images/006-pencil.png");
            imageModifier.setImage(image4);
            System.out.println(String.valueOf(res.getNumResponsable()));
            imageModifier.setId("'"+res.getNumResponsable()+"'");
            imageModifier.setLayoutX(100);
            imageModifier.setLayoutY(177);
            imageModifier.setOnMouseClicked(e->{
                try {
                    this.onClickEdit(e);
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            ImageView imageview2 = new ImageView();
            Image image2 = new Image("file:"+res.getImageUrlResponsable());
            imageview2.setImage(image2);
            imageview2.setFitWidth(145);
            imageview2.setFitHeight(145);
            Label NumResponsable = new Label(String.valueOf(res.getNumResponsable()));
            NumResponsable.setLayoutX(10);
            NumResponsable.setLayoutY(10);
            NumResponsable.setTextFill(Paint.valueOf("white"));
            Label NomResponsable = new Label(res.getPseudoResponsable());
            NomResponsable.setLayoutX(30);
            NomResponsable.setLayoutY(160);
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #cdcdcd;");
            pane.setPrefSize(150, 200);
            pane.getChildren().addAll(imageview2,imageview1,NumResponsable,NomResponsable,imageSuprimer,imageModifier);
            return pane;
        }).forEachOrdered((pane) -> {
            jfxmasonrypane.getChildren().add(pane);
        });
        System.out.println("continue");
        Pane pane = new Pane();
        pane.setPrefSize(150, 200);
        pane.setId("ajoutResponsable");
        pane.setOnMouseClicked(e-> {
            try {
                this.addResponsable();
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jfxmasonrypane.getChildren().add(pane);
    }
    
    //statistic d'argent en %
    @FXML private void affichePieChart() throws ClassNotFoundException, SQLException{
        piechart.getData().clear();
        ObservableList<Data> data = FXCollections.observableArrayList();
        LocalDate dt = LocalDate.now();
        int annee = Integer.parseInt(dt.format(DateTimeFormatter.ofPattern("yyyy")))-10;
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i=1 ; i<=10 ; i++){
            data.add(new PieChart.Data(String.valueOf(annee+i), daoreglements.findSomme(String.valueOf(annee+i))));
        }
        piechart.setData(data);
    }
    //statistic d'argent
    @FXML private void affichestackedBarChart() throws ClassNotFoundException, SQLException{
        stackedBarChart.getData().clear();
        LocalDate dt = LocalDate.now();
        int annee = Integer.parseInt(dt.format(DateTimeFormatter.ofPattern("yyyy")))-10;
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i=1 ; i<=10 ; i++){
            series.getData().add(new XYChart.Data<>(String.valueOf(annee+i), daoreglements.findSomme(String.valueOf(annee+i))));
        }
        stackedBarChart.getData().addAll(series);
    }
    
    //statistique des clients
    @FXML private void affichelineChart() throws ClassNotFoundException, SQLException{
        LocalDate dt = LocalDate.now();
        int annee = Integer.parseInt(dt.format(DateTimeFormatter.ofPattern("yyyy")))-20;
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i=1 ; i<=20 ; i++){
            series.getData().add(new XYChart.Data<>( String.valueOf(annee+i) , daoclients.findAnnee(String.valueOf(annee+i))));
        }
        lineChart.getData().addAll(series);
    }
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    
    public void initialize(URL url, ResourceBundle rb) throws SQLException, ClassNotFoundException {
    }
    
    @FXML private void clickTabUtilateur() throws SQLException, ClassNotFoundException{
        this.affichageAllResponsable();
    }
    
    public FXMLLoader loadFxml() throws IOException{
        Stage primaryStage = new Stage();
        FXMLLoader Loader  = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Desktop/Children/FXMLeditResponsable.fxml"));
        Parent root = Loader.load();
        Scene scene = new Scene(root);
        root.setId("edit");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnHiding((WindowEvent e) -> {
            try {
                this.affichageAllResponsable();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        primaryStage.show();
        
        return Loader;
    }
    @FXML public void onClickEdit(MouseEvent e) throws IOException, ClassNotFoundException, SQLException{
        FXMLLoader Loader = this.loadFxml();
        FXMLeditResponsableController disp = Loader.getController();
        String Stock = ""; 
        for(int i=0; i < (int)(e.getSource().toString().length());i++){
            String oa = String.valueOf( e.getSource().toString().charAt(i) );
            if(oa.matches("[0-9]*")){
                Stock = Stock + oa;
                break;
            }
        }  
        disp.setT(Stock);
    }
    
    @FXML public void onClickSuprimer(MouseEvent e) throws SQLException, ClassNotFoundException{
        DaoResponsables res = new DaoResponsables();
        String Stock = ""; 
        for(int i=0; i < (int)(e.getSource().toString().length());i++){
            String oa = String.valueOf( e.getSource().toString().charAt(i) );
            if(oa.matches("[0-9]*")){
                Stock = Stock + oa;
                break;
            }
        } 
        res.remove(Integer.parseInt(Stock));
        this.affichageAllResponsable();
        
    }
    
    @FXML
    private void onClickBtnGenerale() throws InterruptedException, ClassNotFoundException, SQLException{
        this.affichestackedBarChart();
        this.affichePieChart();
        this.affichelineChart();
        IntegerProperty seconds = new SimpleIntegerProperty();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
            new KeyFrame(Duration.minutes(0.02), e-> {
            try {
                this.affichestackedBarChart();
                this.affichePieChart();
                this.affichelineChart();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }, new KeyValue(seconds, 0.4))   
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
    
    @FXML public void addResponsable() throws IOException, SQLException, ClassNotFoundException{
        DaoResponsables daoresponsables = new DaoResponsables();
        FXMLLoader Loader = this.loadFxml();
        FXMLeditResponsableController disp = Loader.getController();
        disp.setT(String.valueOf(daoresponsables.find(daoresponsables.findAll().size()).getNumResponsable() + 1));
    }
}
