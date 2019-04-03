/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop.Children;

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
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tropikhotel.Con;
import tropikhotel.DAO.DaoCategories;
import tropikhotel.DAO.DaoChambres;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoConcerner;
import tropikhotel.DAO.DaoReglements;
import tropikhotel.DAO.DaoReserver;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.DAO.DaoTypes;
import tropikhotel.GetSet.Categories;
import tropikhotel.GetSet.Chambres;
import tropikhotel.GetSet.ChambresT;
import tropikhotel.GetSet.Clients;
import tropikhotel.GetSet.Concerner;
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
    private Button btnSup2;
    @FXML
    private Button btnEnr2;
    @FXML
    private Button btnMod2;
    @FXML
    private Button btnValider2;
    @FXML
    private Button btnAnnuler2;
    @FXML
    private Button btnRes2;
    @FXML
    private Button btnSup3;
    @FXML
    private Button btnEnr3;
    @FXML
    private Button btnMod3;
    @FXML
    private Button btnValider3;
    @FXML
    private Button btnAnnuler3;
    @FXML
    private Button btnRes3;

    @FXML private TitledPane panesEdit;
    @FXML private JFXTextField txtNomClientReservation, txtNbJourReservation, txtNumClientReservation, txtReglementReservation,txtMontantRecuReglements,MontantRedevableReglement,MontantAjouter;
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
        DaoReserver daoreserver = new DaoReserver();
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
        
        String categorie = txtNumCategorie.getValue().toString();
        String type = txtNumType.getValue().toString();
                
        String suit = "";
        ArrayList<Reserver> rese =daoreserver.findAll();
        for(int i=0; i<rese.size();i++){
            if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dateDeb,dateFin)){
//                si il y a une interaction entre le deux date
                ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                if(i!=1){
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
            }
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
        stock.setText(tableReservation.getItems().get(nbRow).getNumReservation());
        this.affTextFieldReservation(Integer.parseInt(tableReservation.getItems().get(nbRow).getNumReservation()));
        this.btnValider1.setVisible(false);
        this.btnAnnuler1.setVisible(false);
    }
    @FXML public void onMouseClickedListViewReservation() throws ClassNotFoundException, SQLException{
        int nbRow = listChambreReservations.getSelectionModel().getSelectedIndex();
        stock1.setText(String.valueOf(listChambreReservations.getItems().get(nbRow)));
        txtChambreReservation.setValue(stock1.getText());
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
        this.affChambreReservation();
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
        this.affChambreReservation();
    }
    public void affTextFieldReglement(int i) throws ClassNotFoundException, SQLException{
        Reglements re = daoreglements.find(i);

        lbNumReglement.setText(String.valueOf(re.getNumReglement()));
        tgBtReglements.setSelected("payer".equals(re.getEtatReglement()));
        MontantRedevableReglement.setText(String.valueOf(this.montantRedevable()-daoreglements.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement()));
        txtMontantRecuReglements.setText(String.valueOf(re.getMontantReglement()));
        ArrayList<Reserver> rese = daoreserver.findReglement(Integer.parseInt(lbNumReglement.getText()));
        ReservationReglement.getItems().clear();
        for(int j=0 ; j<rese.size() ; j++){
            ReservationReglement.getItems().add("Reservation N° "+rese.get(j).getNumReservation());
        }
    }
    
    @FXML private void updatePayment() throws SQLException, ClassNotFoundException{
        int lastMotant = daoreglements.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement();
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
        int lastMotant = daoreglements.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement();
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
        if(!listChambreReservations.getItems().isEmpty()){
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
            for(int i=0; i<rese.size();i++){
                if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dtDeb,dtFin)){
    //                si il y a une interaction entre le deux date
                    ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                    if(i!=1){
                        suit+= " or ";
                    }
                    for(int j=0; j < Con.size();j++){
                        Concerner Conn = Con.get(j);
                        if(j==0){
                            suit+= "NomChambre='"+Conn.getNomChambre()+"'";
                        }else{
                            suit+= " or NomChambre='"+Conn.getNomChambre()+"'";
                        }
                    } 
                }
            }
            ObservableList<Chambres> cha = this.getAllChambresNonDispo(suit);
            String NameChambreList = "";
            ObservableList chambres = listChambreReservations.getItems();
            for(int o=0;o<cha.size();o++){
                for(int w=0;w<chambres.size();w++){;
                    if(cha.get(o).getNomChambre().equals(chambres.get(w).toString())){
                        test = false;
                        NameChambreList += chambres.get(w).toString()+" ";
                    }
                }
            }
                    
        
            if(test){
                daoreserver.add(String.valueOf(txtDateDebutReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), dateFin ,nbJour, conditionReservation.getText(), "0", Integer.parseInt(txtNumClientReservation.getText()),Integer.parseInt(lbResponsableReservation.getText()), Integer.parseInt(txtReglementReservation.getText()));
                listChambreReservations.getItems().forEach(e->{
                    try {
                        daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), e.toString());
                        daoreglements.modEtats(Integer.parseInt(txtReglementReservation.getText()), "");
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
                });
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
        LocalDate begin = txtDateDebutReservation.getValue();
        if(!"".equals(txtDateDebutReservation.getValue().toString())){
            if(!"".equals(txtNbJourReservation.getText())){
                txtDateFinReservation.setValue(begin.plusDays(Integer.parseInt(txtNbJourReservation.getText())));
                int positionFin = txtNbJourReservation.getText().length();
                if ( txtNbJourReservation.getCaretPosition() != positionFin ) {
                    txtNbJourReservation.positionCaret(positionFin);
                }
            }
        }
    }
    @FXML private void autoCompletionReservationClient() throws ClassNotFoundException, SQLException{
        if(!"".equals(txtNumClientReservation.getText())){
            DaoClients cli = new DaoClients();
            Clients cl = cli.find(Integer.parseInt(txtNumClientReservation.getText()));
            txtNomClientReservation.setText(cl.getNomClient());
        }
    }
    //add and rm chambres
    @FXML public void addChambreReservation() throws SQLException, ClassNotFoundException{
        if(listChambreReservations.getItems().indexOf(txtChambreReservation.getValue())<0){
            listChambreReservations.getItems().add(txtChambreReservation.getValue());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("chambre deja ajouter");
            alert.show();
        }

//        if(daoconcerner.find(Integer.parseInt(lbNumReservation.getText()), txtChambreReservation.getValue().toString()).isEmpty()){
//            daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), txtChambreReservation.getValue().toString());
//            this.affChambreReservation();
//        }
    }
    @FXML public void rmChambreReservation() throws SQLException, ClassNotFoundException{
        if(listChambreReservations.getItems().indexOf(txtChambreReservation.getValue())>=0){
            listChambreReservations.getItems().remove(txtChambreReservation.getValue());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("action refuser");
            alert.show();
        }
//        if(listChambreReservations.getItems().size()>1){
//            daoconcerner.remove(Integer.parseInt(lbNumReservation.getText()), stock1.getText());
//            this.affChambreReservation();
//        }
    }
    @FXML public void affChambreReservation() throws ClassNotFoundException, SQLException{
        ObservableList <String> listChambre = FXCollections.observableArrayList();
        ArrayList<Concerner> Con = daoconcerner.find(Integer.parseInt(lbNumReservation.getText()!=""?lbNumReservation.getText():"1"));
        for(int i=0;i<Con.size();i++){
            listChambre.add(Con.get(i).getNomChambre());
        }
        listChambreReservations.setItems(listChambre);
    }
    
    
    
    //calcul de Montant redevable par les clients
    @FXML public int montantRedevable() throws SQLException, ClassNotFoundException{
        ArrayList<Reserver> rese = daoreserver.searchOne(0,lbNumReglement.getText() , "NumReglement");
        int TotalTousRes = 0;
        for(int i=0;i<rese.size();i++){
           int nbJourRes = rese.get(i).getNbJourReservation();
           ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
           int totalChaqueRes=0;
           int prixChaqueChambre=0;
           for(int j=0;j<Con.size();j++){
               int e = daochambres.find(Con.get(j).getNomChambre()).getPrixChambre();
               prixChaqueChambre = nbJourRes*e;
                totalChaqueRes+=prixChaqueChambre;
           }
           TotalTousRes+=totalChaqueRes;
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
        this.listChambreReservations.getItems().clear();
        this.lbResponsableReservation.setText(stockNumResponsable.getText());

        this.btnValider1.setId("btnEnr11");
        this.btnValider1.setVisible(true);
        this.btnAnnuler1.setVisible(true);
    }
    @FXML
    private void resetReservation(){
        
    }
    
    @FXML 
    private void aideReglement(){
        
    }
    @FXML
    private void print(){
        
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
            ObservableList ob = FXCollections.observableArrayList();
            ArrayList<Reserver> rese = daoreserver.findReglement(Integer.parseInt(lbNumReglement.getText()));
            rese.forEach(e->{
                try {
                    ob.add(daoresponsables.find(e.getNumResponsable()).getNomResponsable()+" "+daoresponsables.find(e.getNumResponsable()).getPrenomResponsable());
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            disp.setT(stockReglement.getText(),daoreglements.find(Integer.parseInt(stockReglement.getText())).getMontantReglement(), ob);
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
      }
      else if (e.getTarget() == this.btnValider2)
      {
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
            this.lbNumReservation.setText("");
            this.txtDateDebutReservation.setValue(null);
            this.txtDateFinReservation.setValue(null);
            this.txtNbJourReservation.setText("");
            this.conditionReservation.setText("");
            this.txtNumClientReservation.setText("");
            this.txtNomClientReservation.setText("");
            this.txtReglementReservation.setText("");
            this.txtChambreReservation.getItems().get(0);
            this.listChambreReservations.getItems().clear();
            this.lbResponsableReservation.setText(stockNumResponsable.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("action reussi");
            alert.show();
        }
    }

    private void modifierReservation() throws SQLException, ClassNotFoundException {
        String dateFin = String.valueOf(LocalDate.parse("9999-12-31"));
        int nbJour = 0;
        String NomChambre = "";
        if(!listChambreReservations.getItems().isEmpty()){
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
            for(int i=0; i<rese.size();i++){
                if(this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dtDeb,dtFin)){
    //                si il y a une interaction entre le deux date
                    ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
                    if(i!=0 && !Con.isEmpty()){
                        suit+= " or ";
                    }
                    for(int j=0; j < Con.size();j++){
                        boolean chamb = false;
                        Concerner Conn = Con.get(j);
                        for(int h=0;h<listChambreReservations.getItems().size();h++){
                            if(Conn.getNomChambre() == listChambreReservations.getItems().get(h)){
                               chamb = true; 
                            }
                        }
                        String pay = chamb ?Conn.getNomChambre():"bla bla bla";
                        System.out.println(pay);
                        if(j==0){
                            suit+= "NomChambre='"+ pay +"'";
                        }else{
                            suit+= " or NomChambre='"+ pay +"'";
                        }
                    } 
                }
            }
            ObservableList<Chambres> cha = this.getAllChambresNonDispo(suit);
            String NameChambreList = "";
            ObservableList chambres = listChambreReservations.getItems();
            for(int o=0;o<cha.size();o++){
                for(int w=0;w<chambres.size();w++){;
                    if(cha.get(o).getNomChambre().equals(chambres.get(w).toString())){
                        test = false;
                        NameChambreList += chambres.get(w).toString()+" ";
                    }
                }
            }
                    
        
            if(test){
                daoreserver.mod(Integer.parseInt(lbNumReservation.getText()), String.valueOf(txtDateDebutReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), dateFin, nbJour, conditionReservation.getText(), tgBtReglement.isSelected()?"1":"0", Integer.parseInt(txtNumClientReservation.getText()), Integer.parseInt(lbResponsableReservation.getText()), Integer.parseInt(txtReglementReservation.getText()));
                listChambreReservations.getItems().forEach(e->{
                    try {
                        ArrayList<Concerner> Con = daoconcerner.find(Integer.parseInt(lbNumReservation.getText()));
                        
                        Con.forEach(h->{
                            try {
                                daoconcerner.remove(Integer.parseInt(lbNumReservation.getText()), h.getNomChambre());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        listChambreReservations.getItems().forEach(i->{
                            try {
                                daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), i.toString());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        daoreglements.modEtats(Integer.parseInt(txtReglementReservation.getText()), "");
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
                });
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            
            DaoCategories daoCategories = new DaoCategories();
            ObservableList<String> listCategorie = FXCollections.observableArrayList();
            ArrayList<Categories> categorieChambre = daoCategories.findAll();
            listCategorie.add("Tous");
            for (int i = 0; i < categorieChambre.size(); i++) {
                listCategorie.add(String.valueOf(((Categories)categorieChambre.get(i)).getNumCategorie()));
            }
            
            ObservableList<String> listType = FXCollections.observableArrayList();
            DaoTypes daoTypes = new DaoTypes();
            ArrayList<Types> type = daoTypes.findAll();
            listType.add("Tous");
            for (int i = 0; i < type.size(); i++) {
                listType.add(String.valueOf(((Types)type.get(i)).getNumType()));
            }
            
            this.txtNumCategorie.setItems(listCategorie);
            this.txtNumType.setItems(listType);
            
            this.txtNumCategorie.getSelectionModel().select(0);
            this.txtNumType.getSelectionModel().select(0);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControlerReservations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
