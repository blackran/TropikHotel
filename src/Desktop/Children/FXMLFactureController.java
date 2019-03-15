/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoConcerner;
import tropikhotel.DAO.DaoReserver;
import tropikhotel.GetSet.Concerner;
import tropikhotel.GetSet.Reserver;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLFactureController implements Initializable {

    @FXML
    private Label sommeFacture;
    @FXML
    private Label NumAndDate;
    @FXML
    private Label NomClientFacture;
    @FXML
    private ListView<?> listResponsable;
    
    @FXML
    private Pane principaleFact;
    
    @FXML
    private AnchorPane principaleFacture;
    
    DaoClients daoclients = new DaoClients();
    DaoReserver daoreserver = new DaoReserver();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate dt = LocalDate.now();
        NumAndDate.setText(dt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    } 
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public void OnMousePressed(MouseEvent event){
      this.xOffset = event.getSceneX();
      this.yOffset = event.getSceneY();
    }

    public void OnMouseDragged(MouseEvent event){
      Stage stage = (Stage)this.principaleFacture.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }

    @FXML
    public void onClickQuit(){
      Stage stage = (Stage)this.principaleFact.getScene().getWindow();
      stage.close();
    }

    void setT(String string) throws ClassNotFoundException, SQLException {
        ArrayList<Reserver> rese = daoreserver.searchOne(0, string, "NumReglement");
        System.out.println(daoclients.find(rese.get(0).getNumClient()).getNomClient());
        String nom = "";
        for(int i=0;i<rese.size();i++){
            System.out.println(i+"  "+daoclients.find(rese.get(i).getNumClient()).getNomClient());
            nom += daoclients.find(rese.get(i).getNumClient()).getNomClient()+ ", ";  
        }      
        NomClientFacture.setText(nom);
        DaoConcerner daoconcerner = new DaoConcerner();
        for(int i=0;i<rese.size();i++){
            ArrayList<Concerner> Con = daoconcerner.findNomChambre(rese.get(i).getNumReservation());
            for(int j =0; i<Con.size(); i++ ){
                System.out.println(Con.get(i));
            }
        } 
        
    }
    public void printBtn(Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        System.out.println(printer.getPrinterAttributes());
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterJob job = PrinterJob.createPrinterJob();
        job.showPrintDialog(node.getScene().getWindow());

        if (job != null) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                System.out.println("ca fontionne");
            }
        }
    }
    public void onClickPrintBtn() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
        this.printBtn(principaleFact);
    }
    
}
