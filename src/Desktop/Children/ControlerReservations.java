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
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tropikhotel.Con;
import tropikhotel.DAO.DaoCategories;
import tropikhotel.DAO.DaoChambres;
import tropikhotel.DAO.DaoClients;
import tropikhotel.DAO.DaoConcerner;
import tropikhotel.DAO.DaoReglements;
import tropikhotel.DAO.DaoReserver;
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
   
    
    DaoReserver res = new DaoReserver();
    DaoReglements reg = new DaoReglements();
    DaoConcerner daoconcerner = new DaoConcerner();
    DaoChambres daochambres = new DaoChambres();
    
    @FXML
    private boolean isInteraction(LocalDate dateDeb1, LocalDate dateFin1, LocalDate dateDeb2, LocalDate dateFin2){
        boolean val;
        if(!dateFin1.toString().equals("")){
            if(DAYS.between(dateDeb1,dateDeb2)<0){
                val = DAYS.between(dateDeb2,dateFin1)<0;
            }else{
                val = DAYS.between(dateDeb1,dateFin2)<0;
            }  
        }else{
            val = DAYS.between(dateFin2, dateDeb1)<0; 
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
    private ObservableList<ChambresT> getChambres(String dateDeb, String dateFin, String categorie, String type) throws ClassNotFoundException, SQLException {
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql;
        if("Tous".equals(categorie) && "Tous".equals(type)){
            sql = "select * from CHAMBRES";
        }else{
            if("Tous".equals(type)){
               sql = "select * from CHAMBRES where NumCategorie="+categorie; 
            }else{
                sql = "select * from CHAMBRES where NumCategorie="+categorie+" and NumType="+type; 
            }
        }
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        ObservableList<ChambresT> cha = FXCollections.observableArrayList();
        while (rs.next()) {
          cha.add(new ChambresT(rs.getString("NomChambre"),rs.getString("TelChambre"),rs.getString("EtageChambre"),rs.getString("ChauffeauChambre"), String.valueOf(rs.getInt("PrixChambre")), String.valueOf(rs.getInt("NumCategorie")), String.valueOf(rs.getInt("NumType"))));
        }
        return cha;
    }
    
    @FXML public void affichageChambre(String dateDeb, String dateFin,String categorie, String type) throws ClassNotFoundException, SQLException {
        this.CNomChambre.setCellValueFactory(new PropertyValueFactory("NomChambre"));
        this.CTelChambre.setCellValueFactory(new PropertyValueFactory("TelChambre"));
        this.CEtageChambre.setCellValueFactory(new PropertyValueFactory("EtageChambre"));
        this.CChauffeauChambre.setCellValueFactory(new PropertyValueFactory("PrixChambre"));
        this.CPrixChambre.setCellValueFactory(new PropertyValueFactory("PrixChambre"));
        this.CNumCategorie.setCellValueFactory(new PropertyValueFactory("NumCategorie"));
        this.CNumType.setCellValueFactory(new PropertyValueFactory("NumType"));
          this.tableChambres.setItems(getChambres(dateDeb, dateFin, categorie, type));
    }
    @FXML public void onClickSeach() throws ClassNotFoundException, SQLException{
        LocalDate dateDeb = txtDateDebutDisponible.getValue();
        LocalDate dateFin = txtDateFinDisponible.getValue();
        String categorie = txtNumCategorie.getValue().toString();
        String type = txtNumType.getValue().toString();
        DaoReserver daoreserver = new DaoReserver();
        ArrayList<Reserver> rese =daoreserver.findAll();
        for(int i=0; i<rese.size();i++){
            System.out.println("Num"+rese.get(i).getNumReservation()+" "+this.isInteraction(LocalDate.parse(rese.get(i).getDateDebutReservation()),LocalDate.parse(rese.get(i).getDateFinReservation()),dateDeb,dateFin));
        }
//        System.out.println("Categorie "+txtNumCategorie.getValue().toString());
//        System.out.println("Type "+txtNumType.getValue().toString());
    }
   
    private ObservableList<ReservationsT> getReservations() throws ClassNotFoundException, SQLException{
        Con c = new Con();
        Connection cn = c.conn();
        Statement st = cn.createStatement();
        String sql = "select * from RESERVER";
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
        String sql = "select * from REGLEMENTS";
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
    }
    @FXML public void onMouseClickedListViewReservation() throws ClassNotFoundException, SQLException{
        int nbRow = listChambreReservations.getSelectionModel().getSelectedIndex();
        stock1.setText(String.valueOf(listChambreReservations.getItems().get(nbRow)));
    }
    @FXML public void onMouseClickedTableauReglement() throws ClassNotFoundException, SQLException{
        int nbRow = tableReglement.getSelectionModel().getSelectedIndex();
        stockReglement.setText(tableReglement.getItems().get(nbRow).getNumReglement());
        this.affTextFieldReglement(Integer.parseInt(tableReglement.getItems().get(nbRow).getNumReglement()));
    }
    
    public void affTextFieldReservation(int i) throws ClassNotFoundException, SQLException{
        Reserver re = res.find(i);
        tgBtReglement.setSelected("1".equals(re.getEtatReservation()));
        lbNumReservation.setText(String.valueOf(re.getNumReservation()));
        txtDateDebutReservation.setValue(LocalDate.parse(re.getDateDebutReservation()));
        txtDateFinReservation.setValue(LocalDate.parse(re.getDateFinReservation()));
        txtNbJourReservation.setText(String.valueOf(re.getNbJourReservation()));
        txtNumClientReservation.setText(String.valueOf(re.getNumClient()));
        txtReglementReservation.setText(String.valueOf(re.getNumReglement()));
        lbResponsableReservation.setText(String.valueOf(re.getNumResponsable()));
        this.affChambreReservation();
        this.autoCompletionReservationClient();
    }
    public void resetAllReservation() throws ClassNotFoundException, SQLException{
        tgBtReglement.setSelected(false);
        lbNumReservation.setText("7");
        txtDateDebutReservation.setValue(null);
        txtDateFinReservation.setValue(null);
        txtNbJourReservation.setText("");
        txtNumClientReservation.setText("");
        txtNomClientReservation.setText("");
        txtReglementReservation.setText("");
        lbResponsableReservation.setText("");
        this.affChambreReservation();
    }
    public void affTextFieldReglement(int i) throws ClassNotFoundException, SQLException{
        Reglements re = reg.find(i);


        lbNumReglement.setText(String.valueOf(re.getNumReglement()));
        tgBtReglements.setSelected("1".equals(re.getEtatReglement()));
        MontantRedevableReglement.setText(String.valueOf(this.montantRedevable()-reg.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement()));
        txtMontantRecuReglements.setText(String.valueOf(re.getMontantReglement()));
    }
    
    @FXML private void updatePayment() throws SQLException, ClassNotFoundException{
        int lastMotant = reg.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement();
        String eta = "";
//        if(MontantAjouter.getText().length()!=0){
//            if(MontantAjouter.getText().equals(MontantRedevableReglement.getText())){
//                eta = "regler";
//                reg.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantAjouter.getText())+lastMotant);
//            }else
//            if(Integer.parseInt(MontantAjouter.getText()) > Integer.parseInt(MontantRedevableReglement.getText())){
//                eta = "regler";
//                reg.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantRedevableReglement.getText())+lastMotant);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setHeaderText("REMARQUE");
//                alert.setContentText("donne "+String.valueOf(Integer.parseInt(MontantAjouter.getText()) - Integer.parseInt(MontantRedevableReglement.getText()))+" au client");
//                alert.show();
//            }else{
//                reg.mod(Integer.parseInt(lbNumReglement.getText()), eta , Integer.parseInt(MontantAjouter.getText())+lastMotant);
//            }
//        }
        MontantAjouter.setText("");
        this.affTextFieldReglement(Integer.parseInt(lbNumReglement.getText()));
        this.afficheReglements();
    }
    @FXML private void updatePaymentMinus() throws SQLException, ClassNotFoundException{
        int lastMotant = reg.find(Integer.parseInt(lbNumReglement.getText())).getMontantReglement();
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
//            daoclients.add(txtNomClientReservation.getText(), "", "", "", "", "");
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
    @FXML private void ajouterReservation() throws SQLException, ClassNotFoundException{
        this.resetAllReservation();
        if(!listChambreReservations.getItems().isEmpty()){
            DaoReserver daoreserver = new DaoReserver();
            daoreserver.add(String.valueOf(txtDateDebutReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), String.valueOf(txtDateFinReservation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),Integer.parseInt(txtNbJourReservation.getText()),tgBtReglement.isSelected()?"1":"0", Integer.parseInt(txtNumClientReservation.getText()),Integer.parseInt(lbResponsableReservation.getText()), Integer.parseInt(txtReglementReservation.getText()));
            this.afficheReservations();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("ajouter plus de chambre");
            alert.show();
        }
    }
    @FXML private void autoCompletionReservationNb(ActionEvent e){
        LocalDate fin = txtDateFinReservation.getValue();
        LocalDate begin = txtDateDebutReservation.getValue();
        //LocalDate begint = LocalDate.now();
        if(!"".equals(txtDateDebutReservation.getValue().toString())){
            if(!"".equals(txtDateFinReservation.getValue().toString())){
                long period = DAYS.between(begin, fin);
                txtNbJourReservation.setText(String.valueOf(period));
            }
            if(DAYS.between(begin, fin)<0){
                txtDateFinReservation.setValue(begin);
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
        if(daoconcerner.find(Integer.parseInt(lbNumReservation.getText()), txtChambreReservation.getValue().toString()).isEmpty()){
            daoconcerner.add(Integer.parseInt(lbNumReservation.getText()), txtChambreReservation.getValue().toString());
            this.affChambreReservation();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("chambre deja ajouter");
            alert.show();
        }
    }
    @FXML public void rmChambreReservation() throws SQLException, ClassNotFoundException{
        if(listChambreReservations.getItems().size()>1){
            daoconcerner.remove(Integer.parseInt(lbNumReservation.getText()), stock1.getText());
            this.affChambreReservation();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("REMARQUE");
            alert.setContentText("action refuser");
            alert.show();
        }
    }
    @FXML public void affChambreReservation() throws ClassNotFoundException, SQLException{
        ObservableList <String> listChambre = FXCollections.observableArrayList();
        ArrayList<Concerner> Con = daoconcerner.find(Integer.parseInt(lbNumReservation.getText()));
        for(int i=0;i<Con.size();i++){
            listChambre.add(Con.get(i).getNomChambre());
        }
        listChambreReservations.setItems(listChambre);
    }
    
    
    
    //calcul de Montant redevable par les clients
    @FXML public int montantRedevable() throws SQLException, ClassNotFoundException{
        System.out.println("_______________________________________________________________________");
        ArrayList<Reserver> rese = res.searchOne(0,lbNumReglement.getText() , "NumReglement");
        int TotalTousRes = 0;
        for(int i=0;i<rese.size();i++){
           int nbJourRes = rese.get(i).getNbJourReservation();
           System.out.println("N° Reservation: "+rese.get(i).getNumReservation());
           System.out.println("Nombre jour: "+rese.get(i).getNbJourReservation());
           ArrayList<Concerner> Con = daoconcerner.find(rese.get(i).getNumReservation());
           int totalChaqueRes=0;
           int prixChaqueChambre=0;
           for(int j=0;j<Con.size();j++){
               int e = daochambres.find(Con.get(j).getNomChambre()).getPrixChambre();
               prixChaqueChambre = nbJourRes*e;
                System.out.println("Nom Chambre: "+Con.get(j).getNomChambre());
                System.out.println("Son Prix: "+daochambres.find(Con.get(j).getNomChambre()).getPrixChambre());
                totalChaqueRes+=prixChaqueChambre;
           }
           TotalTousRes+=totalChaqueRes;
           System.out.println("total ==>"+totalChaqueRes);
           System.out.println("**********************************************************");
        }
        System.out.println("Total finale = "+TotalTousRes);
        return TotalTousRes;
    }   
    
    @FXML private void OnScrolleReglement() throws SQLException, ClassNotFoundException{
        ArrayList<Reglements> regl = reg.findAll();
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
            ArrayList<Reserver> re = res.findAll();
            if(!re.isEmpty()){
                this.afficheReservations();
                //this.affTextFieldReservation(re.get(0).getNumReservation());
            }
    }
    @FXML public void getT(String s){
        this.stockNumResponsable.setText(s);
    }
    
    @FXML
    private void activeSupReservation(){
        
    }
    @FXML
    private void activeEnrReservation(){
        
    }
    @FXML
    private void activeModReservation(){
        
    }
    @FXML
    private void resetReservation(){
        
    }
    
    @FXML 
    private void aideReglement(){
        
    }
    @FXML
    private void verification(){
        
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
    private void onMouseClickBtnFacture() throws IOException, ClassNotFoundException, SQLException{     
        Stage stage = new Stage();
        stage.setResizable(false);
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Desktop/Children/FXMLFacture.fxml"));
        Parent root = (Parent)Loader.load();
        Scene scene = new Scene(root);
        FXMLFactureController disp = (FXMLFactureController)Loader.getController();
        disp.setT(stockReglement.getText());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.OnScrolleReglement();
            this.afficheReglements();
            this.OnScrolleReservation();
//            btnValider.setDisable(false);
//            btnAnnuler.setDisable(false);
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
