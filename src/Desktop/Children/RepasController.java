/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
