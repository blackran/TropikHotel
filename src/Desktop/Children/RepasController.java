/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class RepasController implements Initializable {

    @FXML
    private AnchorPane CPaysClient;
    @FXML
    private JFXTimePicker heureRepas;
    @FXML
    private TableView<?> tableClients1;
    @FXML
    private TableColumn<?, ?> CNumClient1;
    @FXML
    private TableColumn<?, ?> CNomClient1;
    @FXML
    private TableColumn<?, ?> CAddressClient1;
    @FXML
    private TableColumn<?, ?> CCpClient1;

    @FXML private Button btnSupClient, btnEnrClient, btnModClient1, btnSupClient1, btnEnrClient1;
    
    @FXML
    private TableView<?> tableClients;
    @FXML
    private TableColumn<?, ?> CNumClient;
    @FXML
    private TableColumn<?, ?> CNomClient;
    @FXML
    private TableColumn<?, ?> CAddressClient;
    @FXML
    private Button btnModClient;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @FXML private void enregistrer(){
        System.out.println(heureRepas.getValue());

    }
    @FXML
    private void onMouseClickedTableauCommande(){
        
    }
    
    @FXML
    private void onMouseClickedTableauRepas(){
        
    }
    
    public void search() throws SQLException, ClassNotFoundException{
        if(!"".equals(search.getText())){
            this.affichageCommanderS(search.getText());
            this.affichageRepasS(search.getText());
        }else{
            this.affichageCommander();
            this.affichageRepas();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){
                search.setText("");
                this.affichageCommander();
                this.affichageRepas();
            }
        });
    }  

    private void affichageCommander() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void affichageCommanderS(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void affichageRepasS(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void affichageRepas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
