/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

import Desktop.ControleChamp;
import Desktop.ConverTypeCateg;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tropikhotel.Con;
import tropikhotel.DAO.DaoCategories;
import tropikhotel.DAO.DaoChambres;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoCommander;
import tropikhotel.DAO.DaoConcerner;
import tropikhotel.DAO.DaoReglements;
import tropikhotel.DAO.DaoReserver;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.DAO.DaoTypes;
import tropikhotel.GetSet.Categories;
import tropikhotel.GetSet.Chambres;
import tropikhotel.GetSet.ChambresT;
import tropikhotel.GetSet.Clients;
import tropikhotel.GetSet.Commander;
import tropikhotel.GetSet.Concerner;
import tropikhotel.GetSet.ConcernerT;
import tropikhotel.GetSet.Reglements;
import tropikhotel.GetSet.ReglementsT;
import tropikhotel.GetSet.ReservationsT;
import tropikhotel.GetSet.Reserver;
import tropikhotel.GetSet.Types;

/**
 *
 * @author robot
 */
public class ControlerReservations implements Initializable {
    
    @FXML
    private Button btnSup1, btnEnr1, btnMod1, btnRes1, btnValider1, btnAnnuler1;
    @FXML
    private Button btnSup2, btnEnr2, btnMod2, btnRes2, btnValider2, btnAnnuler2;
    @FXML
    private Button btnSup3, btnEnr3, btnMod3, btnRes3, btnValider3, btnAnnuler3;

    @FXML private TitledPane panesEdit;
    @FXML private JFXTextField txtNomClientReservation, txtNbJourReservation, txtNumClientReservation, txtReglementReservation,txtMontantRecuReglements,MontantRedevableReglement,MontantAjouter,ReductionReservation;
    @FXML private JFXDatePicker txtDateDebutReservation, txtDateFinReservation, txtDateDebutDisponible, txtDateFinDisponible;
    @FXML private JFXCheckBox ChReglerReservation;
    @FXML private JFXComboBox txtChambreReservation;
    @FXML private Label lbResponsableReservation,stock,stock1,stockReglement,lbNumReservation,lbNumReglement,stockNumResponsable;
    @FXML private JFXToggleButton tgBtReglement,tgBtReglements;
    @FXML private JFXListView listChambreReservations;
    @FXML private Button btnValider, btnAnnuler;
    
    @FXML private TableView<ReservationsT> tableReservation;
    @FXML private TableColumn<ReservationsT,String> CNumReservation;
    @FXML private TableColumn<ReservationsT,String> CDateDebutReservation;
    @FXML private TableColumn<ReservationsT,String> CDateFinReservation;
    @FXML private TableColumn<ReservationsT,String> CClientReservation;
    
    @FXML private TableView<ReglementsT> tableReglement;
    @FXML private TableColumn<ReglementsT,String> CNumReglement;
    @FXML private TableColumn<ReglementsT,String> CEtatReglement;
    @FXML private TableColumn<ReglementsT,String> CMontantReglement;
    
    @FXML private TableView<ChambresT> tableChambres;
    @FXML private TableColumn<ChambresT, String> CNomChambre;
    @FXML private TableColumn<ChambresT, String> CTelChambre;
    @FXML private TableColumn<ChambresT, String> CEtageChambre;
    @FXML private TableColumn<ChambresT, String> CChauffeauChambre;
    @FXML private TableColumn<ChambresT, String> CPrixChambre;
    @FXML private TableColumn<ChambresT, String> CNumCategorie;
    @FXML private TableColumn<ChambresT, String> CNumType;
    
    @FXML private TableView<ConcernerT> tableview;
    @FXML private TableColumn<ConcernerT,String> CNumChambreRes;
    @FXML private TableColumn<ConcernerT,String> CReductionRes;
    
    
    @FXML
    private JFXComboBox txtNumCategorie;
    @FXML
    private JFXComboBox txtNumType;
    @FXML
    private JFXListView ReservationReglement;
    @FXML
    private TextField search;
    
    @FXML
    private JFXTabPane jfxtabpane;
    
    @FXML
    private TextArea conditionReservation;
    
   
    DaoClients daoclient = new DaoClients();
    DaoReglements daoreglements = new DaoReglements();
    DaoReserver daoreserver = new DaoReserver();
    DaoConcerner daoconcerner = new DaoConcerner();
    DaoChambres daochambres = new DaoChambres();
    DaoResponsables daoresponsables = new DaoResponsables();
    DaoCommander daocommander = new DaoCommander();
	ControleChamp controlechamp = new ControleChamp();
	ConverTypeCateg convertypecateg = new ConverTypeCateg();
    
    @FXML
    private boolean isInteraction(LocalDate dateDebRech1, LocalDate dateFinRech1, LocalDate dateDeb2, LocalDate dateFin2){
        boolean val = false;
//        true si il y a une interaction entre le deux premier date et le deux dernier date
        if(!dateFinRech1.equals(LocalDate.MAX)){
//            dateFin1 != 0
            if(DAYS.between(dateDebRech1,dateDeb2)>0){
                val = DAYS.between(dateDeb2,dateFinRech1)>=0;
            }else{
                val = DAYS.between(dateDebRech1,dateFin2)>=0;
            }  
        }else{
//            dateFin1 == 0 et dateDeb2 < dateDeb1 et dateDeb1 < dateFin2    
            val = DAYS.between(dateDeb2, dateDebRech1)>=0 && DAYS.between(dateDebRech1, dateFin2)>=0; 
        }
        return val;
    }
    
    @FXML
    private void NumChambreDisponibles(LocalDate dateDeb, LocalDate dateFin) throws SQLException, ClassNotFoundException{
        ArrayList<Reserver> rese =daoreserver.findAll();
        for(int i=0; i<rese.size();i++){
            if(rese.get(i).getDateFinReservation().isEmpty()){
              if(DAYS.between(LocalDate.parse(rese.get(i).getDateFinReservation()),dateFin)<0){
                  
              }  
            }
            rese.get(i).getDateDebutReservation();
            
        }
    }
    private ObservableList<ChambresT> getChambres(String Suit, String categorie, String type) throws ClassNotFoundException, SQLException {
		Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from CHAMBRES";
        if("Tous".equals(categorie) && "Tous".equals(type)){
            if(!"".equals(Suit)){
                sql+=" where "+Suit;
            }
        }else{
			System.out.println(categorie +" "+type);
            if("Tous".equals(type)){
               sql += " where NumCategorie="+categorie; 
            }else
            if("Tous".equals(categorie)){
                sql += " where NumType="+type;
            }else{
                sql += " where NumCategorie="+categorie+" and NumType="+type; 
            }
            sql += " and " +Suit;
        }
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        ObservableList<ChambresT> cha = FXCollections.observableArrayList();
        while (rs.next()) {
            cha.add(new ChambresT(rs.getString("NomChambre"),rs.getString("TelChambre"),rs.getString("EtageChambre"),rs.getString("ChauffeauChambre"), String.valueOf(rs.getInt("PrixChambre")), String.valueOf(rs.getInt("NumCategorie")), String.valueOf(rs.getInt("NumType"))));
        }
        return cha;
    }
    
    
    
    private ObservableList<Chambres> getAllChambresNonDispo(String Suit) throws ClassNotFoundException, SQLException {
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = null;
        if(!"".equals(Suit)){
            sql = "select * from CHAMBRES where "+Suit;
        }else{
            sql = "select * from CHAMBRES where NomChambre='!blackran'";
        }
		System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        ObservableList<Chambres> cha = FXCollections.observableArrayList();
        while (rs.next()) {
          cha.add(new Chambres(rs.getString("NomChambre"),rs.getString("TelChambre"),rs.getString("EtageChambre"),rs.getString("ChauffeauChambre"), rs.getInt("PrixChambre"), rs.getInt("NumCategorie"), rs.getInt("NumType")));
        }
        return cha;
    }
  
    
//    private ObservableList<Chambres> listNameChambre() throws ClassNotFoundException, SQLException{
//        ArrayList<Concerner> conc = daoconcerner.findNomChambre(Integer.parseInt(lbNumReservation.getText()));
//        ObservableList<Chambres> cham= FXCollections.observableArrayList();
//        conc.forEach(e->{
//            try {
//                cham.add(daochambres.find(e.getNomChambre()));
//            } catch (ClassNotFoundException | SQLException ex) {
//                Logger.getLogger(RepasController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        
//        return cham;
//    }
    
    
    private ObservableList<ConcernerT> getChaCon() throws ClassNotFoundException, SQLException {
//        ObservableList<Chambres> cha = this.listNameChambre();
        Con c = new Con();
        Connection cn = c.conn();
        Statement sta = cn.createStatement();
        String sql = ("select * from CONCERNER where NumReservation =" + lbNumReservation.getText());
        ResultSet rs = sta.executeQuery(sql);
        ObservableList<ConcernerT> con = FXCollections.observableArrayList();
        int i = 0;
        while (rs.next()) {
            con.add(new ConcernerT(rs.getString("NomChambre") , String.valueOf(rs.getInt("ReductionConcerner"))));
            i++;
        }
        return con;
    }
    
    @FXML public void affichageChambreCon() throws ClassNotFoundException, SQLException {
        this.CNumChambreRes.setCellValueFactory(new PropertyValueFactory("NomChambre"));
        this.CReductionRes.setCellValueFactory(new PropertyValueFactory("ReductionConcerner"));
        
          this.tableview.setItems(getChaCon());
    }
    
    public ObservableList<ConcernerT> stockage(String NomChambre, String ReductionConcerner){
        ObservableList<ConcernerT> sto = FXCollections.observableArrayList();
        this.tableview.getItems().forEach(e->{
            sto.add(e);
        });
        sto.add(new ConcernerT(NomChambre ,ReductionConcerner));
        return sto;
    }
    
    
    
    @FXML public void affichageChambre(String suit, String categorie, String type) throws ClassNotFoundException, SQLException {
        this.CNomChambre.setCellValueFactory(new PropertyValueFactory("NomChambre"));
        this.CTelChambre.setCellValueFactory(new PropertyValueFactory("TelChambre"));
        this.CEtageChambre.setCellValueFactory(new PropertyValueFactory("EtageChambre"));
        this.CChauffeauChambre.setCellValueFactory(new PropertyValueFactory("ChauffeauChambre"));
        this.CPrixChambre.setCellValueFactory(new PropertyValueFactory("PrixChambre"));
        this.CNumCategorie.setCellValueFactory(new PropertyValueFactory("NumCategorie"));
        this.CNumType.setCellValueFactory(new PropertyValueFactory("NumType"));
          this.tableChambres.setItems(getChambres(suit, categorie, type));
    }
    
    
    
    @FXML public void onClickSeach() throws ClassNotFoundException, SQLException{
        LocalDate dateDeb = txtDateDebutDisponible.getValue();
/////// Si le date fin n'exist pas
        LocalDate dateFin = LocalDate.MAX;
        if(!txtDateFinDisponible.getEditor().getText().isEmpty()){
            dateFin = txtDateFinDisponible.getValue();
        }
		String categorie;
		String type;
		
		if(!txtNumCategorie.getValue().toString().equals("Tous")){
			categorie = String.valueOf(convertypecateg.NomCatToNumcat(txtNumCategorie.getValue().toString()));
		}else{
			categorie = txtNumCategorie.getValue().toString();
		}
		if(!txtNumType.getValue().toString().equals("Tous")){
			type = String.valueOf(convertypecateg.NomTypeToNumType(txtNumType.getValue().toString()));
		}else{
			type = txtNumType.getValue().toString();
		}
        
                
        String suit = "";
		boolean one = false;
        ArrayList<Reserver> rese =daoreserver.findAll();
        for(int i=0; i<rese.size();i++){
            if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dateDeb,dateFin)){
//                si il y a une interaction entre le deux date
                ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                if(one && Con.size()>0){
                    suit+= " and ";
                }
                for(int j=0; j < Con.size();j++){
                    Concerner Conn = Con.get(j);
                    if(j==0){
                        suit+= "NOT NomChambre='"+Conn.getNomChambre()+"'";
                    }else{
                        suit+= " and NOT NomChambre='"+Conn.getNomChambre()+"'";
                    }
                }
				one=true;
            }
        }
		one = false;
		System.out.println(suit.substring(0,4).toString()+"  and");
//		System.out.println(suit.substring(4, suit.length()));
		
		if(suit.substring(0,4).toString().equals(" and")){
			suit = suit.substring(4, suit.length());
		}
        this.affichageChambre(suit, categorie, type);
    }
   
    private ObservableList<ReservationsT> getReservations() throws ClassNotFoundException, SQLException{
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from RESERVER order by NumReservation DESC";
        ResultSet rs = st.executeQuery(sql);
        ObservableList<ReservationsT> rese = FXCollections.observableArrayList();
        while (rs.next()){
          rese.add(new ReservationsT(String.valueOf(rs.getInt("NumReservation")),rs.getString("DateDebutReservation"),rs.getString("DateFinReservation"),String.valueOf(rs.getString("NumClient"))));
        }
        return rese;
    }
    private ObservableList<ReglementsT> getReglements() throws ClassNotFoundException, SQLException{
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from REGLEMENTS order by NumReglement DESC";
        ResultSet rs = st.executeQuery(sql);
        ObservableList<ReglementsT> regl = FXCollections.observableArrayList();
        while (rs.next()){
          regl.add(new ReglementsT(String.valueOf(rs.getInt("NumReglement")),rs.getString("EtatReglement"),String.valueOf(rs.getString("MontantReglement"))));
        }
        return regl;
    }
    private void afficheReservations(){
        CNumReservation.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        CDateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("DateDebutReservation"));
        CDateFinReservation.setCellValueFactory(new PropertyValueFactory<>("DateFinReservation"));
        CClientReservation.setCellValueFactory(new PropertyValueFactory<>("NumClient"));

        try {
            tableReservation.setItems(getReservations());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void afficheReservationsS(String id){
        CNumReservation.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        CDateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("DateDebutReservation"));
        CDateFinReservation.setCellValueFactory(new PropertyValueFactory<>("DateFinReservation"));
        CClientReservation.setCellValueFactory(new PropertyValueFactory<>("NumClient"));

        try {
            tableReservation.setItems(daoreserver.searchOneT(id));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void afficheReglementsS(String id) throws ClassNotFoundException, SQLException{
        CNumReglement.setCellValueFactory(new PropertyValueFactory<>("NumReglement"));
        CEtatReglement.setCellValueFactory(new PropertyValueFactory<>("EtatReglement"));
        CMontantReglement.setCellValueFactory(new PropertyValueFactory<>("MontantReglement"));

        tableReglement.setItems(daoreglements.searchOneT(id));
    }
    private void afficheReglements(){
        CNumReglement.setCellValueFactory(new PropertyValueFactory<>("NumReglement"));
        CEtatReglement.setCellValueFactory(new PropertyValueFactory<>("EtatReglement"));
        CMontantReglement.setCellValueFactory(new PropertyValueFactory<>("MontantReglement"));

        try {
            tableReglement.setItems(getReglements());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void onMouseClickedTableauReservation() throws ClassNotFoundException, SQLException{
        int nbRow = tableReservation.getSelectionModel().getSelectedIndex();
//        stock.setText(tableReservation.getItems().get(nbRow).getNumReservation());
        this.affTextFieldReservation(Integer.parseInt(tableReservation.getItems().get(nbRow).getNumReservation()));
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
        this.affichageChambreCon();
    }
    @FXML public void onMouseClickedListViewReservation() throws ClassNotFoundException, SQLException{
        int nbRow = tableview.getSelectionModel().getSelectedIndex();
        txtChambreReservation.getSelectionModel().select(tableview.getSelectionModel().getSelectedItem().getNomChambre());
        ReductionReservation.setText(tableview.getSelectionModel().getSelectedItem().getReductionConcerner());
    }
    
    @FXML public void onMouseClickedTableauReglement() throws ClassNotFoundException, SQLException{
        int nbRow = tableReglement.getSelectionModel().getSelectedIndex();
        stockReglement.setText(tableReglement.getItems().get(nbRow).getNumReglement());
        this.affTextFieldReglement(Integer.parseInt(tableReglement.getItems().get(nbRow).getNumReglement()));
    }
    
    public void affTextFieldReservation(int i) throws ClassNotFoundException, SQLException{
        Reserver re = daoreserver.find(i);
        tgBtReglement.setSelected("1".equals(re.getEtatReservation()));
        lbNumReservation.setText(String.valueOf(re.getNumReservation()));
        txtDateDebutReservation.setValue(LocalDate.parse(re.getDateDebutReservation()));
        txtDateFinReservation.setValue(LocalDate.parse(re.getDateFinReservation()));
        txtNbJourReservation.setText(String.valueOf(re.getNbJourReservation()));
        conditionReservation.setText(re.getConditionReservation());
        txtNumClientReservation.setText(String.valueOf(re.getNumClient()));
        txtReglementReservation.setText(String.valueOf(re.getNumReglement()));
        lbResponsableReservation.setText(String.valueOf(re.getNumResponsable()));
        this.affichageChambreCon();
        this.autoCompletionReservationClient();
    }
    public void resetAllReservation() throws ClassNotFoundException, SQLException{
        tgBtReglement.setSelected(false);
        lbNumReservation.setText("");
        txtDateDebutReservation.setValue(null);
        txtDateFinReservation.setValue(null);
        txtNbJourReservation.setText("");
        conditionReservation.setText("");
        txtNumClientReservation.setText("");
        txtNomClientReservation.setText("");
        txtReglementReservation.setText("");
        lbResponsableReservation.setText("");
        this.affichageChambreCon();
    }
    public void affTextFieldReglement(int i) throws ClassNotFoundException, SQLException{
        Reglements re = daoreglements.find(i).get(0);

        lbNumReglement.setText(String.valueOf(re.getNumReglement()));
        tgBtReglements.setSelected("payer".equals(re.getEtatReglement()));
        MontantRedevableReglement.setText(String.valueOf(this.montantRedevable()-daoreglements.find(Integer.parseInt(lbNumReglement.getText())).get(0).getMontantReglement()));
        txtMontantRecuReglements.setText(String.valueOf(re.getMontantReglement()));
        ArrayList<Reserver> rese = daoreserver.findReglement(Integer.parseInt(lbNumReglement.getText()));
        ReservationReglement.getItems().clear();
        for(int j=0 ; j<rese.size() ; j++){
            ReservationReglement.getItems().add("Reservation N° "+rese.get(j).getNumReservation());
        }
        
        ArrayList<Commander> comm = daoreserver.findCommander(Integer.parseInt(lbNumReglement.getText()));
        for(int h=0 ; h<comm.size() ; h++){
            ReservationReglement.getItems().add("Commande N° "+comm.get(h).getNumCommander());
        }
    }
    
    @FXML private void updatePayment() throws SQLException, ClassNotFoundException{
        int lastMotant = daoreglements.find(Integer.parseInt(lbNumReglement.getText())).get(0).getMontantReglement();
        String eta = "";
        if(MontantAjouter.getText().length()!=0){
            if(MontantAjouter.getText().equals(MontantRedevableReglement.getText())){
                eta = "payer";
                daoreglements.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantAjouter.getText())+lastMotant,String.valueOf(LocalDate.now().getYear()));
                daoreserver.find(lastMotant);
                ArrayList<Reserver> rese = daoreserver.findReglement(Integer.parseInt(lbNumReglement.getText()));
                rese.forEach(e->{
                    try {
                        daoreserver.modEtat(e.getNumReservation(), "1");
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }else
            if(Integer.parseInt(MontantAjouter.getText()) > Integer.parseInt(MontantRedevableReglement.getText())){
                eta = "payer";
                daoreglements.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantRedevableReglement.getText())+lastMotant,String.valueOf(LocalDate.now().getYear()));
                ArrayList<Reserver> rese = daoreserver.findReglement(Integer.parseInt(lbNumReglement.getText()));
                rese.forEach(e->{
                    try {
                        daoreserver.modEtat(e.getNumReservation(), "1");
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("REMARQUE");
                alert.setContentText("donne "+String.valueOf(Integer.parseInt(MontantAjouter.getText()) - Integer.parseInt(MontantRedevableReglement.getText()))+" au client");
                alert.show();
            }else{
                daoreglements.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantAjouter.getText())+lastMotant,String.valueOf(LocalDate.now().getYear()));
            }
        }
        MontantAjouter.setText("");
        this.affTextFieldReglement(Integer.parseInt(lbNumReglement.getText()));
        this.afficheReglements();
    }
    @FXML private void updatePaymentMinus() throws SQLException, ClassNotFoundException{
        int lastMotant = daoreglements.find(Integer.parseInt(lbNumReglement.getText())).get(0).getMontantReglement();
        if(MontantAjouter.getText().length()!=0){
            if(Integer.parseInt(MontantAjouter.getText()) <= lastMotant){
//                reg.mod(Integer.parseInt(lbNumReglement.getText()), "non regler", lastMotant - Integer.parseInt(MontantAjouter.getText()));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("REMARQUE");
                alert.setContentText("operation refuser");
                alert.show();
            }
        }
        MontantAjouter.setText("");
        this.affTextFieldReglement(Integer.parseInt(lbNumReglement.getText()));
        this.afficheReglements();
    }

    @FXML private void ajouterClient() throws SQLException, ClassNotFoundException{
        DaoClients daoclients = new DaoClients();
        if(!("".equals(txtNomClientReservation.getText()))){
            daoclients.add(txtNomClientReservation.getText(), "", "", "", "", "",String.valueOf(LocalDate.now().getYear()));
        }
        ArrayList<Clients> cli= daoclients.findAll();
        txtNumClientReservation.setText(String.valueOf(cli.get(cli.size()-1).getNumClient()));
        txtNomClientReservation.setText(cli.get(cli.size()-1).getNomClient());
    }
    
    @FXML private void ajouterReglement() throws SQLException, ClassNotFoundException{
        DaoReglements daoreglement = new DaoReglements();
        daoreglement.add();
        ArrayList<Reglements> regl= daoreglement.findAll();
        txtReglementReservation.setText(String.valueOf(regl.get(regl.size()-1).getNumReglement()));
    }
    
    @FXML private void lienReglement() throws SQLException, ClassNotFoundException{
        if(!txtReglementReservation.getText().equals("")){
            jfxtabpane.getSelectionModel().select(1);
            this.affTextFieldReglement(Integer.parseInt(txtReglementReservation.getText()));
        }  
    }
    private boolean test = true;
    @FXML private void ajouterReservation(ActionEvent h) throws SQLException, ClassNotFoundException{
        String dateFin = String.valueOf(LocalDate.parse("9999-12-31"));
        int nbJour = 0;
        if(!tableview.getItems().isEmpty()){
            if(!txtDateFinReservation.getEditor().getText().isEmpty()){
                dateFin = String.valueOf(txtDateFinReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                nbJour = Integer.parseInt(txtNbJourReservation.getText());
            }
            
            LocalDate dtDeb = txtDateDebutReservation.getValue();
    /////// Si le date fin n'exist pas
            LocalDate dtFin = LocalDate.MAX;
            if(!txtDateFinReservation.getEditor().getText().isEmpty()){
                dtFin = txtDateFinReservation.getValue();
            }

            String suit = "";
            ArrayList<Reserver> rese =daoreserver.findAll();
			boolean one = false;
            for(int i=0; i<rese.size();i++){
                if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dtDeb,dtFin)){
    //                si il y a une interaction entre le deux date
                    ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                    if(one && Con.size()>0){
						System.out.println(" or ");
                        suit+= " or ";
                    }
                    for(int j=0; j < Con.size();j++){
                        Concerner Conn = Con.get(j);
                        if(j==0){
                            suit+= "NomChambre='"+Conn.getNomChambre()+"'";
							System.out.println("NomChambre='");
                        }else{
                            suit+= " or NomChambre='"+Conn.getNomChambre()+"'";
							System.out.println("or NomChambre='");
                        }
                    }
					one = true;
                }
            }
			one = false;

            ObservableList<Chambres> cha = this.getAllChambresNonDispo(suit);
            String NameChambreList = "";
            ObservableList chambres = null;
                
            
            for(int o=0;o<cha.size();o++){
                for(int w=0;w<tableview.getItems().size();w++){
                    if(cha.get(o).getNomChambre().equals(tableview.getItems().get(w).getNomChambre())){
                 
                        test = false;
                        NameChambreList += tableview.getItems().get(w).getNomChambre()+" ";
                    }
                }
            }

            if(test){
                daoreserver.add(String.valueOf(txtDateDebutReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), dateFin ,nbJour, conditionReservation.getText(), "0", Integer.parseInt(txtNumClientReservation.getText()),Integer.parseInt(lbResponsableReservation.getText()), Integer.parseInt(txtReglementReservation.getText()));
                
                 for(int y=0 ; y<tableview.getItems().size() ; y++ ){
                     try {
                        daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), tableview.getItems().get(y).getNomChambre(), Integer.parseInt(tableview.getItems().get(y).getReductionConcerner()));
//                        daoreglements.modEtats(Integer.parseInt(txtReglementReservation.getText()), "");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("REMARQUE");
                        alert.setContentText("action reussi");
                        alert.show();
                        this.afficheReservations();
                        this.resetAllReservation();
                        this.btnValider1.setVisible(false);
                        this.btnAnnuler1.setVisible(false);
                        
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("REMARQUE");
                alert.setContentText(NameChambreList+"\ndeja reserver entre cette date");
                alert.show(); 
                test= true;
            }      
            
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("ajouter plus de chambre");
            alert.show();
//            ajouter une evenement on click avec une fonction verification()
        }
    }
    
    
    @FXML private void setNullTextfield(){
        int positionFin = ReductionReservation.getText().length();
        if ( ReductionReservation.getCaretPosition() != positionFin ) {
            ReductionReservation.positionCaret(positionFin);
        }
    }
    
    @FXML private void autoCompletionReservationNb(ActionEvent e){
        LocalDate fin = txtDateFinReservation.getValue();
        LocalDate begin = txtDateDebutReservation.getValue();
        //LocalDate begint = LocalDate.now();
        if(!txtDateDebutReservation.getEditor().getText().isEmpty()){
            if(!txtDateFinReservation.getEditor().getText().isEmpty()){
                txtNbJourReservation.setText(String.valueOf(DAYS.between(begin, fin)));
            }
            if(DAYS.between(begin, fin)<=0){
                txtDateFinReservation.setValue(begin.plusDays(1));
            }
        }
        if(!txtDateDebutReservation.getEditor().getText().isEmpty()){
            if(DAYS.between(begin, fin)<=0){
                txtDateFinReservation.setValue(begin.plusDays(1)); 
            }
            if(txtDateFinReservation.getEditor().getText().isEmpty()){
                txtNbJourReservation.setText(String.valueOf(DAYS.between(begin, fin)));
            }
        }
    }
    @FXML private void verificationDateDebutReservation(){
        LocalDate now = LocalDate.now();
        LocalDate begin = txtDateDebutReservation.getValue();
        LocalDate fin = txtDateFinReservation.getValue();
        if(DAYS.between(now,begin)<0){
            txtDateDebutReservation.setValue(now);
        }
        if(!"".equals(txtDateDebutReservation.getValue().toString())){
            if(DAYS.between(begin, fin)<0){
                txtDateFinReservation.setValue(begin);
            }
        }
    }
    @FXML private void autoCompletionReservationDt() throws InterruptedException{
		txtNbJourReservation.setText(controlechamp.numberOnly(txtNbJourReservation.getText()));
		int positionFin = txtNbJourReservation.getText().length();
        if ( txtNbJourReservation.getCaretPosition() != positionFin ) {
            txtNbJourReservation.positionCaret(positionFin);
        }
        LocalDate begin = txtDateDebutReservation.getValue();
        if(!"".equals(txtDateDebutReservation.getValue().toString())){
            if(!"".equals(txtNbJourReservation.getText())){
                txtDateFinReservation.setValue(begin.plusDays(Integer.parseInt(txtNbJourReservation.getText())));
                if ( txtNbJourReservation.getCaretPosition() != positionFin ) {
                    txtNbJourReservation.positionCaret(positionFin);
                }
            }
        }
    }
	
	@FXML private void controleChampsReduction() throws InterruptedException{
		ReductionReservation.setText(controlechamp.numberOnly(ReductionReservation.getText()));
		int positionFin = ReductionReservation.getText().length();
        if ( ReductionReservation.getCaretPosition() != positionFin ) {
            ReductionReservation.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsMotant() throws InterruptedException{
		MontantAjouter.setText(controlechamp.numberOnly(MontantAjouter.getText()));
		int positionFin = MontantAjouter.getText().length();
        if ( MontantAjouter.getCaretPosition() != positionFin ) {
            MontantAjouter.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsRedevable() throws InterruptedException{
		MontantRedevableReglement.setText(controlechamp.numberOnly(MontantRedevableReglement.getText()));
		int positionFin = MontantRedevableReglement.getText().length();
        if ( MontantRedevableReglement.getCaretPosition() != positionFin ) {
            MontantRedevableReglement.positionCaret(positionFin);
        }
    }
	
	@FXML private void controleChampsRegler() throws InterruptedException{
		txtMontantRecuReglements.setText(controlechamp.numberOnly(txtMontantRecuReglements.getText()));
		int positionFin = txtMontantRecuReglements.getText().length();
        if ( txtMontantRecuReglements.getCaretPosition() != positionFin ) {
            txtMontantRecuReglements.positionCaret(positionFin);
        }
    }
	
	
	
    @FXML private void autoCompletionReservationClient() throws ClassNotFoundException, SQLException{
		txtNumClientReservation.setText(controlechamp.numberOnly(txtNumClientReservation.getText()));
		int positionFin = txtNumClientReservation.getText().length();
        if ( txtNumClientReservation.getCaretPosition() != positionFin ) {
            txtNumClientReservation.positionCaret(positionFin);
        }
        if(!"".equals(txtNumClientReservation.getText())){
            ArrayList<Clients> cl = daoclient.find(Integer.parseInt(txtNumClientReservation.getText()));
			if(cl.size() != 0){
				this.txtNumClientReservation.setStyle("");
				this.txtNomClientReservation.setStyle("");
				txtNomClientReservation.setText(cl.get(0).getNomClient());
			}else{
				this.txtNumClientReservation.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
				this.txtNomClientReservation.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
			}
        }
    }
    

    
    
    
    //calcul de Montant redevable par les clients
    @FXML public int montantRedevable() throws SQLException, ClassNotFoundException{
//        total de Prix redevable avec la reservation
        ArrayList<Reserver> rese = daoreserver.searchOne(0,lbNumReglement.getText() , "NumReglement");
        int TotalTousRes = 0;
        for(int i=0;i<rese.size();i++){
           int nbJourRes = rese.get(i).getNbJourReservation();
           ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
           int totalChaqueRes=0;
           int prixChaqueChambre=0;
           for(int j=0;j<Con.size();j++){
               int e = daochambres.find(Con.get(j).getNomChambre()).getPrixChambre();
               int r = Con.get(j).getReductionConcerner();
//               vignette = 1000 Ar
               prixChaqueChambre = (nbJourRes*(e-r-1000))+1000;
               totalChaqueRes+=prixChaqueChambre;
           }
           TotalTousRes+=totalChaqueRes;
        }
        
//        total de Prix redevable de Commande
        ArrayList<Commander> comm = daocommander.findAllByReglement(Integer.parseInt(lbNumReglement.getText()));
        for(int h=0;h<comm.size();h++){
            TotalTousRes += comm.get(h).getTarifCommander();
        }
        
        return TotalTousRes;
    }   
    
    @FXML private void OnScrolleReglement() throws SQLException, ClassNotFoundException{
        ArrayList<Reglements> regl = daoreglements.findAll();
            if(!regl.isEmpty()){
                this.afficheReglements();
                this.affTextFieldReglement(regl.get(0).getNumReglement());
                this.stockReglement.setText(String.valueOf(regl.get(0).getNumReglement()));
            }
    }
    @FXML private void OnScrolleReservation() throws SQLException, ClassNotFoundException{
        ObservableList <String> listChambre = FXCollections.observableArrayList();
            ArrayList <Chambres> cha = daochambres.findAll();
            for(int i= 0;i<cha.size();i++){
                listChambre.add(String.valueOf(cha.get(i).getNomChambre()));
            }
            txtChambreReservation.setItems(listChambre);
            txtChambreReservation.getSelectionModel().select(cha.get(0).getNomChambre());
            ArrayList<Reserver> re = daoreserver.findAll();
            if(!re.isEmpty()){
                this.afficheReservations();
                //this.affTextFieldReservation(re.get(0).getNumReservation());
            }
    }
    @FXML public void getT(String s){
        this.stockNumResponsable.setText(s);
    }
    
    @FXML 
    private void aideReglement() throws ClassNotFoundException, SQLException{
		txtReglementReservation.setText(controlechamp.numberOnly(txtReglementReservation.getText()));
		int positionFin = txtReglementReservation.getText().length();
        if ( txtReglementReservation.getCaretPosition() != positionFin ) {
            txtReglementReservation.positionCaret(positionFin);
        }
		
		if(!txtReglementReservation.getText().equals("")){
			ArrayList<Reglements> reg = daoreglements.find(Integer.parseInt(txtReglementReservation.getText()));
			if(reg.size() != 0){
				this.txtReglementReservation.setStyle("");
			}else{
				this.txtReglementReservation.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
			}
		}
    }
	
    @FXML
    private void onChangeDtDebutDisponible(){
        if(DAYS.between(LocalDate.now(), txtDateDebutDisponible.getValue())<0){
            txtDateDebutDisponible.setValue(LocalDate.now());
        }
    }
    @FXML
    private void onChangeDtFinDisponible(){
        if(DAYS.between(txtDateDebutDisponible.getValue(),txtDateFinDisponible.getValue())>0){
            txtDateDebutDisponible.setValue(txtDateDebutDisponible.getValue().plusDays(1));
        }
    }
    @FXML
    private void onMouseClickBtnFacture() throws IOException, ClassNotFoundException, SQLException{
        if(MontantRedevableReglement.getText().equals("0")){
            Stage stage = new Stage();
            stage.setResizable(false);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/Desktop/Children/FXMLFacture.fxml"));
            Parent root = (Parent)Loader.load();
            Scene scene = new Scene(root);
            FXMLFactureController disp = (FXMLFactureController)Loader.getController();
            disp.setT(stockReglement.getText(),daoreglements.find(Integer.parseInt(stockReglement.getText())).get(0).getMontantReglement(), stockNumResponsable.getText());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    
    @FXML
    private void verification(ActionEvent e) throws SQLException, ClassNotFoundException {
		if (e.getTarget() == this.btnValider1){
			switch (this.btnValider1.getId()){
			case "btnEnr11": 
			  this.ajouterReservation(e);
			  break;
			case "btnSup11": 
			  suprimerReservation();
			  break;
			case "btnMod11": 
			  modifierReservation();
			  break;
			}
		}else if (e.getTarget() == this.btnValider2) {
			switch (this.btnValider2.getId())
			{
			case "btnEnr21": 
			  this.ajouterReglement();
			  break;
			case "btnSup22": 
			  suprimerReglement();
			  break;
			case "btnMod23": 
				modifierReglement();
				break;
			}
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("REMARQUE");
			alert.setContentText("action annuller");
			alert.show();
		}
		this.afficheReservations();
		this.btnValider2.setVisible(false);
		this.btnAnnuler2.setVisible(false);
    }
    
    @FXML
    private void activeEnrReservation() throws SQLException, ClassNotFoundException{
        ArrayList<Reserver> rese = daoreserver.findAll();
        this.lbNumReservation.setText(String.valueOf(rese.get(rese.size()-1).getNumReservation()+1));
        this.txtDateDebutReservation.setValue(LocalDate.now());
        this.txtDateFinReservation.setValue(null);
        this.txtNbJourReservation.setText("");
        this.conditionReservation.setText("");
        this.txtNumClientReservation.setText("");
        this.txtNomClientReservation.setText("");
        this.txtReglementReservation.setText("");
        this.txtChambreReservation.getItems().get(0);
        this.tableview.getItems().clear();
        this.lbResponsableReservation.setText(stockNumResponsable.getText());

        this.btnValider1.setId("btnEnr11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }

    @FXML
    private void activeSupReservation() throws SQLException, ClassNotFoundException{
        this.btnValider1.setId("btnSup11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
 
    }

    @FXML
    public void activeModReservation() throws SQLException, ClassNotFoundException {
      this.btnValider1.setId("btnMod11");
      this.btnValider1.setVisible(true);
      this.btnAnnuler1.setVisible(true);
    }

    @FXML
    public void activeEnrCategorie() throws SQLException, ClassNotFoundException {
//      ArrayList<Categories> Cat = this.categories.findAll();
//      this.txtNumCategorie1.setText(String.valueOf(((Categories)Cat.get(Cat.size() - 1)).getNumCategorie() + 1));
//      this.txtDescriptionCategorie.setText("");

      this.btnValider2.setId("btnEnr21");
      this.btnValider2.setVisible(true);
      this.btnAnnuler2.setVisible(true);
    }

    @FXML
    public void activeSupCategorie()
      throws SQLException, ClassNotFoundException
    {
      this.btnValider2.setId("btnSup22");
      this.btnValider2.setVisible(true);
      this.btnAnnuler2.setVisible(true);
    }

    @FXML
    public void activeModCategorie() throws SQLException, ClassNotFoundException{
      this.btnValider2.setId("btnMod23");
      this.btnValider2.setVisible(true);
      this.btnAnnuler2.setVisible(true);
    }
    
    private void suprimerReservation() throws SQLException, ClassNotFoundException {
        if(!lbNumReservation.getText().equals("")){
            daoreserver.remove(Integer.parseInt(lbNumReservation.getText()));
			Reserver rese = (Reserver) daoreserver.findAll().get(daoreserver.findAll().size()-1);
            this.affTextFieldReservation(rese.getNumReservation());
			this.btnValider1.setVisible(false);
            this.btnAnnuler1.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("action reussi");
            alert.show();
        }
    }

    private void modifierReservation() throws SQLException, ClassNotFoundException {
		String dateFin = String.valueOf(LocalDate.parse("9999-12-31"));
        int nbJour = 0;
        if(!tableview.getItems().isEmpty()){
            if(!txtDateFinReservation.getEditor().getText().isEmpty()){
                dateFin = String.valueOf(txtDateFinReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                nbJour = Integer.parseInt(txtNbJourReservation.getText());
            }
            
            LocalDate dtDeb = txtDateDebutReservation.getValue();
    /////// Si le date fin n'exist pas
            LocalDate dtFin = LocalDate.MAX;
            if(!txtDateFinReservation.getEditor().getText().isEmpty()){
                dtFin = txtDateFinReservation.getValue();
            }
			

            String suit = "";
            ArrayList<Reserver> rese =daoreserver.findAll();
			boolean one = false;
            for(int i=0; i<rese.size();i++){
                if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dtDeb,dtFin)){
    //                si il y a une interaction entre le deux date
                    ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                    if(one && Con.size()>0){
						System.out.println(" or ");
                        suit+= " or ";
                    }
                    for(int j=0; j < Con.size();j++){
                        Concerner Conn = Con.get(j);
                        if(j==0){
                            suit+= "NomChambre='"+Conn.getNomChambre()+"'";
							System.out.println("NomChambre='");
                        }else{
                            suit+= " or NomChambre='"+Conn.getNomChambre()+"'";
							System.out.println("or NomChambre='");
                        }
                    }
					one = true;
					System.out.println("nouveau for");
					System.out.println(one);
                }
            }
			one = false;

            ObservableList<Chambres> cha = this.getAllChambresNonDispo(suit);
            String NameChambreList = "";
            ObservableList chambres = null;
                
            
            for(int o=0;o<cha.size();o++){
                for(int w=0;w<tableview.getItems().size();w++){
                    if(cha.get(o).getNomChambre().equals(tableview.getItems().get(w).getNomChambre())){
                 
                        test = false;
                        NameChambreList += tableview.getItems().get(w).getNomChambre()+" ";
                    }
                }
            }

            if(true){
                daoreserver.mod(Integer.parseInt(lbNumReservation.getText()), String.valueOf(txtDateDebutReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), dateFin, nbJour, conditionReservation.getText(), tgBtReglement.isSelected()?"1":"0", Integer.parseInt(txtNumClientReservation.getText()), Integer.parseInt(lbResponsableReservation.getText()), Integer.parseInt(txtReglementReservation.getText()));
                ArrayList<Concerner> Con = daoconcerner.find(Integer.parseInt(lbNumReservation.getText()));
                Con.forEach(h->{
                    try {
                        daoconcerner.remove(Integer.parseInt(lbNumReservation.getText()), h.getNomChambre());
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
				
                for(int y=0 ; y<tableview.getItems().size() ; y++ ){
                    try {
                        daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), tableview.getItems().get(y).getNomChambre(), Integer.parseInt(tableview.getItems().get(y).getReductionConcerner()));
                        daoreglements.modEtats(Integer.parseInt(txtReglementReservation.getText()), "");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("REMARQUE");
                        alert.setContentText("action reussi");
                        alert.show();
                        this.afficheReservations();
                        this.btnValider1.setVisible(false);
                        this.btnAnnuler1.setVisible(false);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("REMARQUE");
                alert.setContentText(NameChambreList+"\ndeja reserver entre cette date");
                alert.show(); 
                test= true;
            }      
            
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("ajouter plus de chambre");
            alert.show();
//            ajouter une evenement on click avec une fonction verification()
        }
    }

    private void suprimerReglement() throws SQLException, ClassNotFoundException {
        if(!lbNumReglement.getText().equals("")){
            daoreglements.remove(Integer.parseInt(lbNumReglement.getText()));
        }
    }

    private void modifierReglement() {
//		tsy tokony hovaina ny vola ao aminy reglement
    }
    
    
    public void search() throws SQLException, ClassNotFoundException{
        if(!"".equals(search.getText())){
            this.afficheReservationsS(search.getText());
            this.afficheReglementsS(search.getText());
        }else{
            this.afficheReservations();
            this.afficheReglements();
        }
        
    }
    
    public void addChambreReservation() throws SQLException, ClassNotFoundException{
        boolean bool = true;
        for (ConcernerT item : tableview.getItems()) {
            if(item.getNomChambre().equals(txtChambreReservation.getValue())){
                bool = false;
            }
        }        
        if(bool){
            this.affichageRepasCommander2(txtChambreReservation.getValue().toString(), ReductionReservation.getText());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("chambre deja exister");
            alert.show();
        }
        bool = true;
//        this.affichageRepasCommander();
        this.afficheReservations();
    }
     
    public void rmChambreReservation() throws SQLException, ClassNotFoundException{
        if(tableview.getItems().size()>1){
            int rrow = tableview.getSelectionModel().getSelectedIndex();
            tableview.getItems().remove(rrow);
        }else{
            this.afficheReservations();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("champ exiger non vide");
            alert.show();
        }  
    }
	
	public void modChambreReservation() throws SQLException, ClassNotFoundException{
        int rrow = tableview.getSelectionModel().getSelectedIndex();
		tableview.getItems().remove(rrow);
		boolean bool = true;
        for (ConcernerT item : tableview.getItems()) {
            if(item.getNomChambre().equals(txtChambreReservation.getValue())){
                bool = false;
            }
        }        
        if(bool){
            this.affichageRepasCommander2(txtChambreReservation.getValue().toString(), ReductionReservation.getText());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("chambre deja exister");
            alert.show();
        }
        bool = true;
//        this.affichageRepasCommander();
        this.afficheReservations();
    }
     
    @FXML
    public void affichageRepasCommander2(String Nomchambre, String Reduction) throws ClassNotFoundException, SQLException{
        this.CNumChambreRes.setCellValueFactory(new PropertyValueFactory("NomChambre"));
        this.CReductionRes.setCellValueFactory(new PropertyValueFactory("ReductionConcerner"));
        
        this.tableview.setItems(this.stockage(Nomchambre, Reduction));
        this.btnValider2.setVisible(false);
        this.btnAnnuler2.setVisible(false);   
    }
	
	public void resetReservation() throws ClassNotFoundException, SQLException{
		this.resetAllReservation();
	}
	
	@FXML
	public void KeyOnlyNumber(){
		
	}
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {     
        search.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){
                search.setText("");
                this.afficheReservations();
                this.afficheReglements();
            }
        });
        try {
            this.OnScrolleReglement();
            this.afficheReglements();
            this.OnScrolleReservation();
            this.btnValider1.setVisible(false);
            this.btnAnnuler1.setVisible(false);
            this.btnValider2.setVisible(false);
            this.btnAnnuler2.setVisible(false);
            txtDateDebutDisponible.setValue(LocalDate.now());
            Reserver rese = (Reserver) daoreserver.findAll().get(daoreserver.findAll().size()-1);
            this.affTextFieldReservation(rese.getNumReservation());
            
            DaoCategories daoCategories = new DaoCategories();
            ObservableList<String> listCategorie = FXCollections.observableArrayList();
            ArrayList<Categories> categorieChambre = daoCategories.findAll();
            listCategorie.add("Tous");
            for (int i = 0; i < categorieChambre.size(); i++) {
                listCategorie.add(String.valueOf(((Categories)categorieChambre.get(i)).getDescriptionCategorie()));
            }
            
            ObservableList<String> listType = FXCollections.observableArrayList();
            DaoTypes daoTypes = new DaoTypes();
            ArrayList<Types> type = daoTypes.findAll();
            listType.add("Tous");
            for (int i = 0; i < type.size(); i++) {
                listType.add(String.valueOf(((Types)type.get(i)).getNomType()));
            }
            
            this.txtNumCategorie.setItems(listCategorie);
            this.txtNumType.setItems(listType);
            
            this.txtNumCategorie.getSelectionModel().select(0);
            this.txtNumType.getSelectionModel().select(0);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
            tableview.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newValue) -> {
            Pane header = (Pane)tableview.lookup("TableHeaderRow");
            if(header.isVisible()){
                header.setMaxHeight(0);
                header.setMinHeight(0);
                header.setPrefHeight(0);
                header.setVisible(false);
            }
        });
		txtDateDebutReservation.setChronology(Chronology.ofLocale(Locale.FRENCH));
    }
}
