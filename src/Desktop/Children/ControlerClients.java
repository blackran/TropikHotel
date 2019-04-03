package Desktop.Children;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import tropikhotel.Con;
import tropikhotel.DAO.DaoClients;
import tropikhotel.GetSet.Clients;
import tropikhotel.GetSet.ClientsT;

public class ControlerClients
  implements Initializable
{
  @FXML
  private TitledPane panesEdit;
  @FXML
  private Button btnSup1;
  @FXML
  private Button btnEnr1;
  @FXML
  private Button btnMod1;
  @FXML
  private Button btnValider1;
  @FXML
  private Button btnAnnuler1;
  @FXML
  private Button btnRes1;
  @FXML
  private Label stock;
  @FXML
  private Label txtNumClients;
  @FXML
  private JFXTextField txtNomClients;
  @FXML
  private JFXTextField txtAddressClients;
  @FXML
  private JFXTextField txtCpClients;
  @FXML
  private JFXTextField txtPaysClients;
  @FXML
  private JFXTextField txtTelClients;
  @FXML
  private JFXTextField txtEmailClients;
  @FXML
  private TableView<ClientsT> tableClients;
  @FXML
  private TableColumn<ClientsT, String> CNumClient;
  @FXML
  private TableColumn<ClientsT, String> CNomClient;
  @FXML
  private TableColumn<ClientsT, String> CTelClient;
  
  @FXML
  private TextField search;
  
  DaoClients clients = new DaoClients();
  
    private ObservableList<ClientsT> getClients() throws ClassNotFoundException, SQLException {
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from CLIENTS order by NumClient DESC";
        ResultSet rs = st.executeQuery(sql);
        ObservableList<ClientsT> cli = FXCollections.observableArrayList();
        while (rs.next()) {
            cli.add(new ClientsT(String.valueOf(rs.getInt("NumClient")), rs.getString("NomClient"), rs.getString("TelClient")));
        }
        return cli;
    }

    @FXML
    private void suprimerClient() throws SQLException, ClassNotFoundException{
      this.clients.remove(Integer.parseInt(this.stock.getText()));
      affichage();
    }

    public void affTextField(int i) throws ClassNotFoundException, SQLException{
        Clients cli = this.clients.find(i);
        this.txtNumClients.setText(String.valueOf(cli.getNumClient()));
        this.txtNomClients.setText(cli.getNomClient());
        this.txtAddressClients.setText(cli.getAddressClient());
        this.txtCpClients.setText(cli.getCpClient());
        this.txtPaysClients.setText(cli.getPaysClient());
        this.txtTelClients.setText(cli.getTelClient());
        this.txtEmailClients.setText(cli.getEmailClient());
    }

    @FXML
    public void onMouseClickedTableau() throws ClassNotFoundException, SQLException {
        int nbRow = this.tableClients.getSelectionModel().getSelectedIndex();
        this.stock.setText(((ClientsT)this.tableClients.getItems().get(nbRow)).getNumClient());
        this.affTextField(Integer.parseInt(this.stock.getText()));
    }

    @FXML
    public void modifier() throws SQLException, ClassNotFoundException {
        this.clients.mod(Integer.parseInt(this.txtNumClients.getText()), this.txtNomClients.getText(), this.txtAddressClients.getText(), this.txtCpClients.getText(), this.txtPaysClients.getText(), this.txtTelClients.getText(), this.txtEmailClients.getText(), this.clients.find(Integer.parseInt(this.txtNumClients.getText())).getAnneeCreClient());
        affichage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reusi");
        alert.show();
    }

    @FXML
    public void affichage(){
        this.CNumClient.setCellValueFactory(new PropertyValueFactory("NumClient"));
        this.CNomClient.setCellValueFactory(new PropertyValueFactory("NomClient"));
        this.CTelClient.setCellValueFactory(new PropertyValueFactory("TelClient"));
        try
        {
          this.tableClients.setItems(getClients());
        }
        catch (ClassNotFoundException|SQLException ex)
        {
          Logger.getLogger(ControlerClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }
    @FXML
    public void affichageS(String id) throws SQLException, ClassNotFoundException{
        this.CNumClient.setCellValueFactory(new PropertyValueFactory("NumClient"));
        this.CNomClient.setCellValueFactory(new PropertyValueFactory("NomClient"));
        this.CTelClient.setCellValueFactory(new PropertyValueFactory("TelClient"));
        this.tableClients.setItems(clients.searchAllT(id));
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }

    @FXML
    private void ajouterClient() throws SQLException, ClassNotFoundException {
        this.clients.add(this.txtNomClients.getText(), this.txtAddressClients.getText(), this.txtCpClients.getText(), this.txtPaysClients.getText(), this.txtTelClients.getText(), this.txtEmailClients.getText(), String.valueOf(LocalDate.now().getYear()));
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
        affichage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reusi");
        alert.show();
    }

    @FXML
    private void verification(ActionEvent e)
      throws SQLException, ClassNotFoundException
    {
      System.out.println("verification");
      if (e.getTarget() == this.btnValider1)
      {
        switch (this.btnValider1.getId()){
        case "btnEnr11": 
          System.out.println("arrive ici");
          ajouterClient();
          break;
        case "btnSup11": 
          System.out.println("arrive ici");
          suprimerClient();
          break;
        case "btnMod11": 
          modifier();
          break;
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action annuller");
        alert.show();
      }
      this.btnValider1.setId("");
    }

    @FXML
    public void activeSupClient() throws SQLException, ClassNotFoundException{
        this.btnValider1.setId("btnSup11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }

    @FXML
    public void activeAjouClient() throws SQLException, ClassNotFoundException {
        this.txtNumClients.setText(String.valueOf(this.clients.find(this.clients.findAll().size()).getNumClient() + 1));
        this.txtNomClients.setText("");
        this.txtAddressClients.setText("");
        this.txtCpClients.setText("");
        this.txtPaysClients.setText("");
        this.txtTelClients.setText("");
        this.txtEmailClients.setText("");
        this.btnValider1.setId("btnEnr11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }

    @FXML
    public void activeModClient()
      throws SQLException, ClassNotFoundException
    {
      this.btnValider1.setId("btnMod11");
      this.btnValider1.setVisible(true);
      this.btnAnnuler1.setVisible(true);
    }
    
    public void search() throws SQLException, ClassNotFoundException{
        if(!"".equals(search.getText())){
            this.affichageS(search.getText());
        }else{
            this.affichage();
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        search.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){
                search.setText("");
                this.affichage();
            }
        });
        
        affichage();
        this.stock.setVisible(false);
        try
        {
          ArrayList<Clients> cli = this.clients.findAll();
          this.affTextField(((Clients)cli.get(0)).getNumClient());
          this.btnValider1.setVisible(false);
          this.btnAnnuler1.setVisible(false);
        }
        catch (ClassNotFoundException|SQLException ex)
        {
          Logger.getLogger(ControlerClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.panesEdit.setCollapsible(false);
    }
}
