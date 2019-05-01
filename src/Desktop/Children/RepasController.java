/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import Desktop.ControleChamp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tropikhotel.Con;
import tropikhotel.DAO.DaoAjouter;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoCommander;
import tropikhotel.DAO.DaoReglements;
import tropikhotel.DAO.DaoRepas;
import tropikhotel.GetSet.Ajouter;
import tropikhotel.GetSet.AjouterT;
import tropikhotel.GetSet.Clients;
import tropikhotel.GetSet.Commander;
import tropikhotel.GetSet.CommanderT;
import tropikhotel.GetSet.Reglements;
import tropikhotel.GetSet.Repas;
import tropikhotel.GetSet.RepasT;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class RepasController implements Initializable {
    
    @FXML
    private TableView<RepasT> tableRepas;
    @FXML
    private TableColumn<RepasT, String> CNumRepas;
    @FXML
    private TableColumn<RepasT, String> CNomRepas;
    @FXML
    private TableColumn<RepasT, String> CHeure;
    @FXML
    private TableColumn<RepasT, String> CPrixRepas;
    
    @FXML
    private TableView<CommanderT> tableCommander;
    @FXML
    private TableColumn<CommanderT, String> CNumCommander;
    @FXML
    private TableColumn<CommanderT, String> CTarifCommander;
    @FXML
    private TableColumn<CommanderT, String> CNumClient;
    @FXML
    private TableColumn<CommanderT, String> CNumReglement;
    
    @FXML
    private TableView<AjouterT> tableview;
    @FXML
    private TableColumn<AjouterT, String> CNumRepasCom;
    @FXML
    private TableColumn<AjouterT, String> CNomRepasCom;
    @FXML
    private TableColumn<AjouterT, String> CQtRepasCom;
    
    
    @FXML
    private AnchorPane CPaysClient;

    @FXML
    private TextField search;

    @FXML
    private Label NumCommande;

    @FXML
    private JFXTextField tarifCommande;

    @FXML
    private JFXDatePicker dateCommande;

    @FXML
    private JFXTextField clientsCommande;
    
    @FXML
    private JFXTextField txtNomClientCommande;
    
    @FXML
    private JFXTextField reglementCommande;

    @FXML
    private JFXComboBox repasCommande;

    @FXML
    private Pane paneEdit1;

    @FXML
    private Button btnSup1;

    @FXML
    private Button btnEnr1;

    @FXML
    private Button btnRes1;

    @FXML
    private Button btnMod1;

    @FXML
    private Button btnValider1;

    @FXML
    private Button btnAnnuler1;

    @FXML
    private Label stockCommande;
    
    

    
    @FXML
    private Label NumRepas;

    @FXML
    private JFXTextField NomRepas;

    @FXML
    private JFXTextField PrixRepas;

    @FXML
    private JFXComboBox heureRepas;

    @FXML
    private Button btnSup2;

    @FXML
    private Button btnEnr2;

    @FXML
    private Button btnRes2;

    @FXML
    private Button btnMod2;

    @FXML
    private Button btnValider2;

    @FXML
    private Button btnAnnuler2;

    @FXML
    private Label stockRepas;
    
    

    @FXML private Button btnSupClient, btnEnrClient, btnModClient1, btnSupClient1, btnEnrClient1;
    
    @FXML
    private JFXButton moinsCommander, plusCommander;
    
    @FXML
    private Button btnModClient;
    
    @FXML
    private JFXTextField lBrepasCommande;
    
    @FXML
    private JFXTextField QtrepasCommande;
    

    
    DaoCommander daocommander = new DaoCommander();
    DaoReglements daoreglements = new DaoReglements();
    DaoRepas daorepas = new DaoRepas();
    DaoAjouter daoajouter = new DaoAjouter();
    DaoClients daoclient = new DaoClients();
	ControleChamp controlechamp = new ControleChamp();
    
    private ObservableList<CommanderT> getCommander() throws ClassNotFoundException, SQLException {
//        this.updateMontantRedevable();
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from COMMANDER order by NumCommander DESC";
        ResultSet rs = st.executeQuery(sql);
        ObservableList<CommanderT> com = FXCollections.observableArrayList();
        while (rs.next()) {
            com.add(new CommanderT(String.valueOf(rs.getInt("NumCommander")), String.valueOf(rs.getInt("TarifCommander")), String.valueOf(rs.getInt("NumClient")), String.valueOf(rs.getInt("NumReglement"))));
        }
        return com;
    }
    
    private ObservableList<RepasT> getRepas() throws ClassNotFoundException, SQLException {
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from REPAS order by NumRepas DESC";
        ResultSet rs = st.executeQuery(sql);
        ObservableList<RepasT> Rep = FXCollections.observableArrayList();
        while (rs.next()) {
            Rep.add(new RepasT(String.valueOf(rs.getInt("NumRepas")), rs.getString("NomRepas"), rs.getString("Heure"), String.valueOf(rs.getInt("PrixRepas"))));
        }
        return Rep;
    }
    private ObservableList<Repas> listNameRepas() throws ClassNotFoundException, SQLException{
        ArrayList<Ajouter> ajout = daoajouter.findNumRepas(Integer.parseInt(NumCommande.getText()));
        ObservableList<Repas> rep= FXCollections.observableArrayList();
        ajout.forEach(e->{
            try {
                rep.add(daorepas.find(e.getNumRepas()));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RepasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        return rep;
    }
    
    private ObservableList<AjouterT> getRepasCom() throws ClassNotFoundException, SQLException {
        ObservableList<Repas> rep = this.listNameRepas();
        Con c = new Con();
        Connection cn = c.conn();
        Statement sta = cn.createStatement();
        String sql = ("select * from AJOUTER where NumCommander =" + NumCommande.getText());
        ResultSet rs = sta.executeQuery(sql);
        ObservableList<AjouterT> Ajou = FXCollections.observableArrayList();
        int i = 0;
        while (rs.next()) {
            Ajou.add(new AjouterT(String.valueOf(rs.getInt("NumRepas")), rep.get(i).getNomRepas() , String.valueOf(rs.getInt("QtAjouter"))));
            i++;
        }
        return Ajou;
    }
    
    public ObservableList<AjouterT> stockage(String NumRepas,String NomRepas, String QtAjouter){
        ObservableList<AjouterT> sto = FXCollections.observableArrayList();
        this.tableview.getItems().forEach(e->{
            sto.add(e);
        });
        sto.add(new AjouterT(NumRepas, NomRepas ,QtAjouter));
        return sto;
    }

    @FXML
    public void affichageRepasCommander2(String NumRepas,String NomRepas, String QtAjouter) throws ClassNotFoundException, SQLException{
        this.CNumRepasCom.setCellValueFactory(new PropertyValueFactory("NumRepas"));
        this.CNomRepasCom.setCellValueFactory(new PropertyValueFactory("NomRepas"));
        this.CQtRepasCom.setCellValueFactory(new PropertyValueFactory("QtAjouter"));
        
        this.tableview.setItems(this.stockage(NumRepas, NomRepas ,QtAjouter));
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);   
    }
    
    
    
    @FXML
    public void affichageCommander(){
        this.CNumCommander.setCellValueFactory(new PropertyValueFactory("NumCommander"));
        this.CTarifCommander.setCellValueFactory(new PropertyValueFactory("TarifCommander"));
        this.CNumClient.setCellValueFactory(new PropertyValueFactory("NumClient"));
        this.CNumReglement.setCellValueFactory(new PropertyValueFactory("NumReglement"));
        try
        {
          this.tableCommander.setItems(getCommander());
        }
        catch (ClassNotFoundException|SQLException ex)
        {
          Logger.getLogger(ControlerClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void affichageRepas(){
        this.CNumRepas.setCellValueFactory(new PropertyValueFactory("NumRepas"));
        this.CNomRepas.setCellValueFactory(new PropertyValueFactory("NomRepas"));
        this.CHeure.setCellValueFactory(new PropertyValueFactory("Heure"));
        this.CPrixRepas.setCellValueFactory(new PropertyValueFactory("PrixRepas"));
        try
        {
          this.tableRepas.setItems(getRepas());
        }
        catch (ClassNotFoundException|SQLException ex)
        {
          Logger.getLogger(ControlerClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);
    }
    
    @FXML
    public void affichageRepasCommander() throws ClassNotFoundException, SQLException{
        this.CNumRepasCom.setCellValueFactory(new PropertyValueFactory("NumRepas"));
        this.CNomRepasCom.setCellValueFactory(new PropertyValueFactory("NomRepas"));
        this.CQtRepasCom.setCellValueFactory(new PropertyValueFactory("QtAjouter"));
        this.tableview.setItems(this.getRepasCom());
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);
    }
    
    @FXML
    public void affichageCommanderS(String id) throws SQLException, ClassNotFoundException{
        this.CNumCommander.setCellValueFactory(new PropertyValueFactory("NumCommander"));
        this.CTarifCommander.setCellValueFactory(new PropertyValueFactory("TarifCommander"));
        this.CNumClient.setCellValueFactory(new PropertyValueFactory("NumClient"));
        this.CNumReglement.setCellValueFactory(new PropertyValueFactory("NumReglement"));
//        this.tableCommander.setItems(clients.searchAllT(id));
        
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }
    @FXML
    public void affichageRepasS(String id) throws SQLException, ClassNotFoundException{
        this.CNumRepas.setCellValueFactory(new PropertyValueFactory("NumRepas"));
        this.CNomRepas.setCellValueFactory(new PropertyValueFactory("NomRepas"));
        this.CHeure.setCellValueFactory(new PropertyValueFactory("Heure"));
        this.CPrixRepas.setCellValueFactory(new PropertyValueFactory("PrixRepas"));
//        this.tableRepas.setItems(clients.searchAllT(id));
        
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }

    /**
     * Initializes the controller class.
     */
    @FXML private void enregistrer(){
        System.out.println(heureRepas.getValue());

    }
    @FXML
    private void onMouseClickedTableauCommande() throws ClassNotFoundException, SQLException{
        int row = tableCommander.getSelectionModel().getSelectedIndex();
        this.affTextFieldCommander(Integer.parseInt(tableCommander.getItems().get(row).getNumCommander()));
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }
    
    @FXML
    private void onMouseClickedTableauRepas() throws ClassNotFoundException, SQLException{
        int row = tableRepas.getSelectionModel().getSelectedIndex();
        this.affTextFieldRepas(Integer.parseInt(tableRepas.getItems().get(row).getNumRepas()));
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);
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
    
    public void resetAllCommander() throws SQLException, ClassNotFoundException{
        Commander com = (Commander) daocommander.findAll().get(daocommander.findAll().size()-1);
        NumCommande.setText(String.valueOf(com.getNumCommander()+1));
        tarifCommande.setText("0");
        clientsCommande.setText("");
        txtNomClientCommande.setText("");
        reglementCommande.setText("");
        QtrepasCommande.setText("");
        tableview.getItems().clear();
        dateCommande.setValue(LocalDate.now());
        repasCommande.getSelectionModel().select(0);
        this.btnValider1.setId("btnSup11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }
    
//    CRUD sur le commander de repas
    
    public void ajouterCommander(ActionEvent e) throws SQLException, ClassNotFoundException{
        daocommander.add(Integer.parseInt(NumCommande.getText()), Integer.parseInt(tarifCommande.getText()), String.valueOf(dateCommande.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), Integer.parseInt(clientsCommande.getText()), Integer.parseInt(reglementCommande.getText()));
        ObservableList<AjouterT> h = tableview.getItems();
        for(int y=0 ; y<h.size() ; y++ ){
            try {
                daoajouter.add(Integer.parseInt(h.get(y).getNumRepas()), Integer.parseInt(NumCommande.getText()), Integer.parseInt(h.get(y).getQtAjouter()));
                daoreglements.modEtats(Integer.parseInt(reglementCommande.getText()), "");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("REMARQUE");
                alert.setContentText("action reussi");
                alert.show();
                this.affichageCommander();
                this.resetAllCommander();
                this.btnValider1.setVisible(false);
                this.btnAnnuler1.setVisible(false);       
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
    public void suprimerCommander() throws SQLException, ClassNotFoundException{
        daocommander.remove(Integer.parseInt(NumCommande.getText()));
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reussi");
        alert.show();
    }
	
    public void modifierCommander() throws SQLException, ClassNotFoundException{
        daocommander.mod(Integer.parseInt(NumCommande.getText()), Integer.parseInt(tarifCommande.getText()), String.valueOf(dateCommande.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), Integer.parseInt(clientsCommande.getText()), Integer.parseInt(reglementCommande.getText()));
        ArrayList<Ajouter> ajout = daoajouter.findNumRepas(Integer.parseInt(NumCommande.getText()));
        ajout.forEach(h->{
            try {
                daoajouter.remove(h.getNumRepas(), Integer.parseInt(NumCommande.getText()));
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
		ObservableList<AjouterT> h = tableview.getItems();
        for(int y=0 ; y<h.size() ; y++ ){
            try {
                daoajouter.add(Integer.parseInt(h.get(y).getNumRepas()), Integer.parseInt(NumCommande.getText()), Integer.parseInt(h.get(y).getQtAjouter()));
                daoreglements.modEtats(Integer.parseInt(reglementCommande.getText()), "");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("REMARQUE");
                alert.setContentText("action reussi");
                alert.show();
                this.affichageCommander();
//                this.resetAllCommander();
                this.btnValider1.setVisible(false);
                this.btnAnnuler1.setVisible(false);       
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
//    CRUD sur les repas
    
    public void ajouterRepas() throws SQLException, ClassNotFoundException{
        daorepas.add(NomRepas.getText(), Integer.parseInt(PrixRepas.getText()), heureRepas.getValue().toString());
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reussi");
        alert.show();
    }
	
    public void suprimerRepas() throws SQLException, ClassNotFoundException{
        daorepas.remove(Integer.parseInt(NumRepas.getText()));
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reussi");
        alert.show();
    }
	
    public void modifierRepas() throws SQLException, ClassNotFoundException{
        daorepas.mod(Integer.parseInt(NumRepas.getText()), NomRepas.getText(), Integer.parseInt(PrixRepas.getText()), heureRepas.getValue().toString());
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("REMARQUE");
        alert.setContentText("action reussi");
        alert.show();
	}
    
    @FXML
    private void verification(ActionEvent e) throws SQLException, ClassNotFoundException {
        if (e.getTarget() == this.btnValider1){
            switch (this.btnValider1.getId()){
            case "btnEnr11": 
                this.ajouterCommander(e);
                break;
            case "btnSup11": 
                this.suprimerCommander();
                break;
            case "btnMod11": 
                this.modifierCommander();
                break;
            }
        }
        else if (e.getTarget() == this.btnValider2){
            switch (this.btnValider2.getId()){
                case "btnEnr21": 
                    this.ajouterRepas();
                    break;
                case "btnSup21": 
                    this.suprimerRepas();
                    break;
                case "btnMod21": 
                    this.modifierRepas();
                    break;
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("action annuller");
            alert.show();
        }
        this.affichageCommander();
		this.affichageRepas();
		this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);
    }
    
    @FXML
    private void activeAddCommander() throws SQLException, ClassNotFoundException{
        Commander com = (Commander) daocommander.findAll().get(daocommander.findAll().size()-1);
        NumCommande.setText(String.valueOf(com.getNumCommander()+1));
        tarifCommande.setText("0");
        clientsCommande.setText("");
        txtNomClientCommande.setText("");
        reglementCommande.setText("");
        QtrepasCommande.setText("");
        tableview.getItems().clear();
        dateCommande.setValue(LocalDate.now());
        repasCommande.getSelectionModel().select(0);
        this.btnValider1.setId("btnEnr11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }
	
	@FXML
	private void activeAddRepas() throws SQLException, ClassNotFoundException{
		Repas rep = (Repas) daorepas.findAll().get(daocommander.findAll().size()-1);
		NumRepas.setText(String.valueOf(rep.getNumRepas()+1));
		NomRepas.setText("");
		PrixRepas.setText("");
		heureRepas.getSelectionModel().select(0);
		this.btnValider2.setId("btnEnr21");
        this.btnValider2.setVisible(true);
        this.btnAnnuler2.setVisible(true);
	}

    @FXML
    private void activeSupCommander() throws SQLException, ClassNotFoundException{
        this.btnValider1.setId("btnSup11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }
	
	
	@FXML
    private void activeSupRepas() throws SQLException, ClassNotFoundException{
        this.btnValider2.setId("btnSup21");
        this.btnValider2.setVisible(true);
        this.btnAnnuler2.setVisible(true);
    }
	@FXML
    private void activeModCommander() throws SQLException, ClassNotFoundException{
        this.btnValider1.setId("btnMod11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }
    
	@FXML
    private void activeModRepas() throws SQLException, ClassNotFoundException{
        this.btnValider2.setId("btnMod21");
        this.btnValider2.setVisible(true);
        this.btnAnnuler2.setVisible(true);
    }
	
    public void affTextFieldCommander(int i) throws ClassNotFoundException, SQLException{
        Commander com = this.daocommander.find(i);
        this.NumCommande.setText(String.valueOf(com.getNumCommander()));
        this.tarifCommande.setText(String.valueOf(com.getTarifCommander()));
        this.dateCommande.setValue(LocalDate.parse(com.getDateCommander()));
        this.reglementCommande.setText(String.valueOf(com.getNumReglement()));
        this.heureRepas.getSelectionModel().select(com.getDateCommander());
        this.clientsCommande.setText(String.valueOf(com.getNumClient()));
        this.txtNomClientCommande.setText(daoclient.find(Integer.parseInt(clientsCommande.getText())).get(0).getNomClient());
        this.affichageRepasCommander();
    }
    
    public void affTextFieldRepas(int i) throws ClassNotFoundException, SQLException{
        Repas rep = this.daorepas.find(i);
        this.NumRepas.setText(String.valueOf(rep.getNumRepas()));
        this.NomRepas.setText(rep.getNomRepas());
        this.PrixRepas.setText(String.valueOf(rep.getPrixRepas()));
        this.heureRepas.getSelectionModel().select(rep.getHeureRepas());
    }
    
    public void changeNumRepas() throws SQLException, ClassNotFoundException{
        if(!repasCommande.getSelectionModel().isEmpty()){
            lBrepasCommande.setText(String.valueOf(daorepas.findByName(repasCommande.getValue().toString()).getNumRepas()));
            QtrepasCommande.setText("");
            tableview.getItems().forEach(e->{
                if( e.getNumRepas().equals(lBrepasCommande.getText())){
                    QtrepasCommande.setText(e.getQtAjouter());
                }
            });
        }
    }
    
    
    public void onClickMouseTableview(){
        int nbRow = tableview.getSelectionModel().getSelectedIndex();
        lBrepasCommande.setText(tableview.getItems().get(nbRow).getNumRepas());
        repasCommande.setValue(tableview.getItems().get(nbRow).getNomRepas());
        QtrepasCommande.setText(tableview.getItems().get(nbRow).getQtAjouter());
    }
    
    
    
    @FXML private void autoCompletionReservationClient() throws ClassNotFoundException, SQLException{
		clientsCommande.setText(controlechamp.numberOnly(clientsCommande.getText()));
		int positionFin = clientsCommande.getText().length();
        if ( clientsCommande.getCaretPosition() != positionFin ) {
            clientsCommande.positionCaret(positionFin);
        }
		if(!"".equals(clientsCommande.getText())){
            ArrayList<Clients> cl = daoclient.find(Integer.parseInt(clientsCommande.getText()));
			if(cl.size() != 0){
				this.clientsCommande.setStyle("");
				this.txtNomClientCommande.setStyle("");
            txtNomClientCommande.setText(daoclient.find(Integer.parseInt(clientsCommande.getText())).get(0).getNomClient());
			}else{
				this.clientsCommande.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
				this.txtNomClientCommande.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
			}
        }
    }
    
    @FXML private void ajouterClient() throws SQLException, ClassNotFoundException{
        DaoClients daoclients = new DaoClients();
        if(!("".equals(txtNomClientCommande.getText()))){
            daoclients.add(txtNomClientCommande.getText(), "", "", "", "", "",String.valueOf(LocalDate.now().getYear()));
        }
        ArrayList<Clients> cli= daoclients.findAll();
        clientsCommande.setText(String.valueOf(cli.get(cli.size()-1).getNumClient()));
        txtNomClientCommande.setText(cli.get(cli.size()-1).getNomClient());
    }
    
    
    @FXML 
    private void ajouterReglement() throws SQLException, ClassNotFoundException{
        DaoReglements daoreglement = new DaoReglements();
        daoreglement.add();
        ArrayList<Reglements> regl= daoreglement.findAll();
        reglementCommande.setText(String.valueOf(regl.get(regl.size()-1).getNumReglement()));
    }
    
    public void addRepasCommander() throws SQLException, ClassNotFoundException{
        boolean bool = true;
        for (AjouterT item : tableview.getItems()) {
            if(item.getNomRepas().equals(repasCommande.getValue())){
                bool = true;
				tableview.getItems().remove(item);
            }
        }        
        if(bool){
            if(!QtrepasCommande.getText().equals("")){
                this.affichageRepasCommander2(lBrepasCommande.getText(), repasCommande.getValue().toString(), QtrepasCommande.getText());
                tarifCommande.setText(String.valueOf(Integer.parseInt(tarifCommande.getText())+ daorepas.find(Integer.parseInt(lBrepasCommande.getText())).getPrixRepas()*Integer.parseInt(QtrepasCommande.getText())));
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("repas deja exister");
            alert.show();
        }
        bool = true;
        this.affichageCommander();
    }
    
    public void rmRepasCommander() throws SQLException, ClassNotFoundException{
        if(tableview.getItems().size()>1){
            int rrow = tableview.getSelectionModel().getSelectedIndex();
            tableview.getItems().remove(rrow);
			tarifCommande.setText(String.valueOf(Integer.parseInt(tarifCommande.getText())- daorepas.find(Integer.parseInt(lBrepasCommande.getText())).getPrixRepas()*Integer.parseInt(QtrepasCommande.getText())));
        }else{
            this.affichageCommander();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("champ exiger non vide");
            alert.show();
        }  
    }
	
	public void modRepasCommander() throws SQLException, ClassNotFoundException{
        int rrow = tableview.getSelectionModel().getSelectedIndex();
        tableview.getItems().remove(rrow);
		boolean bool = true;
        for (AjouterT item : tableview.getItems()) {
            if(item.getNomRepas().equals(repasCommande.getValue())){
                bool = true;
				tableview.getItems().remove(item);
            }
        }        
        if(bool){
            if(!QtrepasCommande.getText().equals("")){
                this.affichageRepasCommander2(lBrepasCommande.getText(), repasCommande.getValue().toString(), QtrepasCommande.getText());
                tarifCommande.setText(String.valueOf(Integer.parseInt(tarifCommande.getText())+ daorepas.find(Integer.parseInt(lBrepasCommande.getText())).getPrixRepas()*Integer.parseInt(QtrepasCommande.getText())));
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("repas deja exister");
            alert.show();
        }
        bool = true;
        this.affichageCommander();
	}
	
	@FXML 
    private void aideReglement() throws ClassNotFoundException, SQLException{
		reglementCommande.setText(controlechamp.numberOnly(reglementCommande.getText()));
		int positionFin = reglementCommande.getText().length();
        if ( reglementCommande.getCaretPosition() != positionFin ) {
            reglementCommande.positionCaret(positionFin);
        }
		if(!reglementCommande.getText().equals("")){
			ArrayList<Reglements> reg = daoreglements.find(Integer.parseInt(reglementCommande.getText()));
			if(!reg.isEmpty()){
				this.reglementCommande.setStyle("");
			}else{
				this.reglementCommande.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
			}
		}
    }
	
	@FXML
	public void upperCaseNomRepas(){
		NomRepas.setText(NomRepas.getText().toUpperCase());
		int positionFin = NomRepas.getText().length();
        if ( NomRepas.getCaretPosition() != positionFin ) {
            NomRepas.positionCaret(positionFin);
        }
	}
	
	@FXML private void controleChampsPrixRepas() throws InterruptedException{
		PrixRepas.setText(controlechamp.numberOnly(PrixRepas.getText()));
		int positionFin = PrixRepas.getText().length();
        if ( PrixRepas.getCaretPosition() != positionFin ) {
            PrixRepas.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsTarif() throws InterruptedException{
		tarifCommande.setText(controlechamp.numberOnly(tarifCommande.getText()));
		int positionFin = tarifCommande.getText().length();
        if ( tarifCommande.getCaretPosition() != positionFin ) {
            tarifCommande.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsNumRep() throws InterruptedException{
		lBrepasCommande.setText(controlechamp.numberOnly(lBrepasCommande.getText()));
		int positionFin = lBrepasCommande.getText().length();
        if ( lBrepasCommande.getCaretPosition() != positionFin ) {
            lBrepasCommande.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsQtRepas() throws InterruptedException{
		QtrepasCommande.setText(controlechamp.numberOnly(QtrepasCommande.getText()));
		int positionFin = QtrepasCommande.getText().length();
        if ( QtrepasCommande.getCaretPosition() != positionFin ) {
            QtrepasCommande.positionCaret(positionFin);
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Repas> rep = daorepas.findAll();
            for(int h=0; h<rep.size(); h++){
                repasCommande.getItems().add(rep.get(h).getNomRepas());
            }           
//            this.updateMontantRedevable();
            
            Repas repa = (Repas) daorepas.findAll().get(daorepas.findAll().size()-1);
            Commander comm = (Commander) daocommander.findAll().get(daocommander.findAll().size()-1);
            this.affTextFieldCommander(comm.getNumCommander());
            this.affTextFieldRepas(repa.getNumRepas());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RepasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        search.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){
                search.setText("");
                this.affichageCommander();
                this.affichageRepas();
            }
        });
        this.affichageCommander();
        this.affichageRepas();

        ObservableList<String> heureRep = FXCollections.observableArrayList();
        heureRep.addAll("OTHER","PETIT DEJEUNER","DEJEUNER","DINER");
        heureRepas.setItems(heureRep);
        heureRepas.getSelectionModel().select(0);
        lBrepasCommande.setText(tableview.getItems().get(0).getNumRepas());
        repasCommande.setValue(tableview.getItems().get(0).getNomRepas());
        QtrepasCommande.setText(tableview.getItems().get(0).getQtAjouter());
        
        //    head header in tableView
    
        tableview.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newValue) -> {
            Pane header = (Pane)tableview.lookup("TableHeaderRow");
            if (header.isVisible()){
                header.setMaxHeight(0);
                header.setMinHeight(0);
                header.setPrefHeight(0);
                header.setVisible(false);
            }
        });
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }  
    

}
