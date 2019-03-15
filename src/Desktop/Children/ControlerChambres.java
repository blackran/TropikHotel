package Desktop.Children;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import tropikhotel.Con;
import tropikhotel.DAO.DaoCategories;
import tropikhotel.DAO.DaoChambres;
import tropikhotel.DAO.DaoTypes;
import tropikhotel.GetSet.CategorieT;
import tropikhotel.GetSet.Categories;
import tropikhotel.GetSet.Chambres;
import tropikhotel.GetSet.ChambresT;
import tropikhotel.GetSet.TypeT;
import tropikhotel.GetSet.Types;

public class ControlerChambres
  implements Initializable
{
  @FXML
  private Label stockChambre;
  @FXML
  private Label stockCategorie;
  @FXML
  private Label stockType;
  @FXML
  private Label txtNumCategorie1;
  @FXML
  private Label lbNumType;
  @FXML
  private JFXTextField txtNomChambre;
  @FXML
  private JFXTextField txtTelChambre;
  @FXML
  private JFXTextField txtNomType;
  @FXML
  private JFXTextField txtPrixChambre;
  @FXML
  private JFXComboBox txtEtageChambre;
  @FXML
  private JFXComboBox txtNumCategorie;
  @FXML
  private JFXComboBox txtNumType;
  @FXML
  private JFXComboBox txtChauffeauChambre;
  @FXML
  private JFXRadioButton rbOui;
  @FXML
  private JFXRadioButton rbNon;
  @FXML
  private TextArea txtDescriptionCategorie;
  @FXML
  private TextArea txtDescriptionType;
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
  @FXML
  private TableView<ChambresT> tableChambres;
  @FXML
  private TableColumn<ChambresT, String> CNomChambre;
  @FXML
  private TableColumn<ChambresT, String> CNumCategorie;
  @FXML
  private TableColumn<ChambresT, String> CNumType;
  @FXML
  private TableView<CategorieT> tableCategorie;
  @FXML
  private TableColumn<CategorieT, String> CNumCategorie1;
  @FXML
  private TableColumn<CategorieT, String> CDescriptionCategorie;
  @FXML
  private TableView<TypeT> tableType;
  @FXML
  private TableColumn<TypeT, String> CNumType1;
  @FXML
  private TableColumn<TypeT, String> CNomType;
  DaoChambres chambres = new DaoChambres();
  DaoCategories categories = new DaoCategories();
  DaoTypes types = new DaoTypes();
  
  private ObservableList<ChambresT> getChambres() throws ClassNotFoundException, SQLException {
    Con c = new Con();
    Connection cn = c.conn();
    Statement st = cn.createStatement();
    String sql = "select * from CHAMBRES";
    ResultSet rs = st.executeQuery(sql);
    ObservableList<ChambresT> cha = FXCollections.observableArrayList();
    while (rs.next()) {
      cha.add(new ChambresT(rs.getString("NomChambre"),rs.getString("TelChambre"),rs.getString("EtageChambre"),rs.getString("ChauffeauChambre"), String.valueOf(rs.getInt("PrixChambre")), String.valueOf(rs.getInt("NumCategorie")), String.valueOf(rs.getInt("NumType"))));
    }
    return cha;
  }
  
  private ObservableList<CategorieT> getCategories()
    throws ClassNotFoundException, SQLException
  {
    Con c = new Con();
    Connection cn = c.conn();
    Statement st = cn.createStatement();
    String sql = "select * from CATEGORIES";
    ResultSet rs = st.executeQuery(sql);
    ObservableList<CategorieT> cat = FXCollections.observableArrayList();
    while (rs.next()) {
      cat.add(new CategorieT(String.valueOf(rs.getInt("NumCategorie")), rs.getString("DescriptionCategorie")));
    }
    return cat;
  }
  
  private ObservableList<TypeT> getTypes()
    throws ClassNotFoundException, SQLException
  {
    Con c = new Con();
    Connection cn = c.conn();
    Statement st = cn.createStatement();
    String sql = "select * from TYPES";
    ResultSet rs = st.executeQuery(sql);
    ObservableList<TypeT> typ = FXCollections.observableArrayList();
    while (rs.next()) {
      typ.add(new TypeT(String.valueOf(rs.getInt("NumType")), rs.getString("NomType")));
    }
    return typ;
  }
  
  @FXML
  public void affTextFieldChambre(String i)
    throws ClassNotFoundException, SQLException
  {
    Chambres cha = this.chambres.find(i);
    this.txtNomChambre.setText(cha.getNomChambre());
    this.txtTelChambre.setText(cha.getTelChambre());
    this.txtEtageChambre.getSelectionModel().select(cha.getEtageChambre());
    this.txtChauffeauChambre.getSelectionModel().select(cha.getChauffeauChambre());
    this.txtPrixChambre.setText(String.valueOf(cha.getPrixChambre()));
    this.txtNumCategorie.getSelectionModel().select(String.valueOf(cha.getNumCategorie()));
    this.txtNumType.getSelectionModel().select(String.valueOf(cha.getNumType()));
  }
  
  @FXML
  public void affTextFieldCategorie(int i)
    throws ClassNotFoundException, SQLException
  {
    Categories cat = this.categories.find(i);
    this.txtNumCategorie1.setText(String.valueOf(cat.getNumCategorie()));
    this.txtDescriptionCategorie.setText(cat.getDescriptionCategorie());
  }
  
  @FXML
  public void affTextFieldType(int i)
    throws ClassNotFoundException, SQLException
  {
    Types typ = this.types.find(i);
    this.lbNumType.setText(String.valueOf(typ.getNumType()));
    this.txtNomType.setText(typ.getNomType());
    this.txtDescriptionType.setText(typ.getDescriptionType());
  }
  
  @FXML
  public void onMouseClickedTableauChambre()
    throws ClassNotFoundException, SQLException
  {
    int nbRow = this.tableChambres.getSelectionModel().getSelectedIndex();
    this.stockChambre.setText(((ChambresT)this.tableChambres.getItems().get(nbRow)).getNomChambre());
    affTextFieldChambre(((ChambresT)this.tableChambres.getItems().get(nbRow)).getNomChambre());
  }
  
  @FXML
  public void onMouseClickedTableauCategorie()
    throws ClassNotFoundException, SQLException
  {
    int nbRow = this.tableCategorie.getSelectionModel().getSelectedIndex();
    this.stockCategorie.setText(((CategorieT)this.tableCategorie.getItems().get(nbRow)).getNumCategorie());
    affTextFieldCategorie(Integer.parseInt(((CategorieT)this.tableCategorie.getItems().get(nbRow)).getNumCategorie()));
  }
  
  @FXML
  public void onMouseClickedTableauType()
    throws ClassNotFoundException, SQLException
  {
    int nbRow = this.tableType.getSelectionModel().getSelectedIndex();
    this.stockType.setText(((TypeT)this.tableType.getItems().get(nbRow)).getNumType());
    affTextFieldType(Integer.parseInt(((TypeT)this.tableType.getItems().get(nbRow)).getNumType()));
  }
  
  @FXML public void affichageChambre() {
    this.CNomChambre.setCellValueFactory(new PropertyValueFactory("NomChambre"));
    this.CNumCategorie.setCellValueFactory(new PropertyValueFactory("NumCategorie"));
    this.CNumType.setCellValueFactory(new PropertyValueFactory("NumType"));
    try
    {
      this.tableChambres.setItems(getChambres());
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      Logger.getLogger(ControlerChambres.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @FXML
  public void affichageCategorie()
  {
    this.CNumCategorie1.setCellValueFactory(new PropertyValueFactory("NumCategorie"));
    this.CDescriptionCategorie.setCellValueFactory(new PropertyValueFactory("DescriptionCategorie"));
    try
    {
      this.tableCategorie.setItems(getCategories());
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      Logger.getLogger(ControlerChambres.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @FXML
  public void affichageType()
  {
    this.CNumType1.setCellValueFactory(new PropertyValueFactory("NumType"));
    this.CNomType.setCellValueFactory(new PropertyValueFactory("NomType"));
    try
    {
      this.tableType.setItems(getTypes());
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      Logger.getLogger(ControlerChambres.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @FXML
  public void modifierChambre()
    throws SQLException, ClassNotFoundException
  {
    this.chambres.mod(this.txtNomChambre.getText(), this.txtTelChambre.getText(), this.txtEtageChambre.getValue().toString(), this.txtChauffeauChambre.getValue().toString(), Integer.parseInt(this.txtPrixChambre.getText()), Integer.parseInt(this.txtNumCategorie.getValue().toString()), Integer.parseInt(this.txtNumType.getValue().toString()));
    affichageChambre();
    this.btnValider1.setVisible(false);
    this.btnAnnuler1.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  public void modifierCategorie()
    throws SQLException, ClassNotFoundException
  {
    this.categories.mod(Integer.parseInt(this.txtNumCategorie.getValue().toString()), this.txtDescriptionCategorie.getText());
    affichageCategorie();
    this.btnValider2.setVisible(false);
    this.btnAnnuler2.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void suprimerChambre()
    throws SQLException, ClassNotFoundException
  {
    this.chambres.remove(this.stockChambre.getText());
    affichageChambre();
    ArrayList<Chambres> cha = this.chambres.findAll();
    affTextFieldChambre(((Chambres)cha.get(0)).getNomChambre());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void suprimerCategorie()
    throws SQLException, ClassNotFoundException
  {
    System.out.println("suprimer ici");
    this.categories.remove(Integer.parseInt(this.stockCategorie.getText()));
    affichageCategorie();
    ArrayList<Categories> Cat = this.categories.findAll();
    this.txtNumCategorie1.setText(String.valueOf(((Categories)Cat.get(Cat.size() - 1)).getNumCategorie()));
    affTextFieldCategorie(((Categories)Cat.get(Cat.size() - 1)).getNumCategorie());
    this.btnValider2.setVisible(false);
    this.btnAnnuler2.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void ajouterChambre()
    throws SQLException, ClassNotFoundException
  {
    System.out.println("ca fonctione");
    this.chambres.add(this.txtNomChambre.getText(), this.txtTelChambre.getText(), this.txtEtageChambre.getValue().toString(), this.txtChauffeauChambre.getValue().toString(), Integer.parseInt(this.txtPrixChambre.getText()), Integer.parseInt(this.txtNumCategorie.getValue().toString()), Integer.parseInt(this.txtNumType.getValue().toString()));
    affichageChambre();
    this.btnValider1.setVisible(false);
    this.btnAnnuler1.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void ajouterCategorie()
    throws SQLException, ClassNotFoundException
  {
    this.categories.add(Integer.parseInt(this.txtNumCategorie.getValue().toString()), this.txtDescriptionCategorie.getText());
    affichageCategorie();
    this.btnValider2.setVisible(false);
    this.btnAnnuler2.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void ajouterType()
    throws SQLException, ClassNotFoundException
  {
    this.types.add(Integer.parseInt(this.lbNumType.getText()), this.txtNomType.getText(), this.txtDescriptionType.getText());
    affichageType();
    this.btnValider3.setVisible(false);
    this.btnAnnuler3.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void suprimerType()
    throws SQLException, ClassNotFoundException
  {
    System.out.println("suprimer ici");
    this.types.remove(Integer.parseInt(this.lbNumType.getText()));
    affichageType();
    this.btnValider3.setVisible(false);
    this.btnAnnuler3.setVisible(false);
    ArrayList<Types> type = this.types.findAll();
    this.lbNumType.setText(String.valueOf(((Types)type.get(type.size() - 1)).getNumType()));
    affTextFieldType(((Types)type.get(type.size() - 1)).getNumType());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  public void modifierType()
    throws SQLException, ClassNotFoundException
  {
    this.types.mod(Integer.parseInt(this.lbNumType.getText()), this.txtNomType.getText(), this.txtDescriptionType.getText());
    affichageType();
    this.btnValider3.setVisible(false);
    this.btnAnnuler3.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("REMARQUE");
    alert.setContentText("action reussi");
    alert.show();
  }
  
  @FXML
  private void verification(ActionEvent e)
    throws SQLException, ClassNotFoundException
  {
    System.out.println(e.getTarget() == this.btnValider2);
    if (e.getTarget() == this.btnValider1)
    {
      switch (this.btnValider1.getId())
      {
      case "btnEnr11": 
        System.out.println("arrive ici");
        ajouterChambre();
        break;
      case "btnSup11": 
        System.out.println("arrive ici");
        suprimerChambre();
        break;
      case "btnMod11": 
        modifierChambre();
        break;
      }
    }
    else if (e.getTarget() == this.btnValider2)
    {
      switch (this.btnValider2.getId())
      {
      case "btnEnr21": 
        ajouterCategorie();
        break;
      case "btnSup22": 
        suprimerCategorie();
        break;
      case "btnMod23": 
        modifierCategorie();
        break;
      }
    }
    else if (e.getTarget() == this.btnValider3)
    {
      switch (this.btnValider3.getId())
      {
      case "btnEnr31": 
        ajouterType();
        break;
      case "btnSup32": 
        suprimerType();
        break;
      case "btnMod33": 
        modifierType();
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
    this.btnValider2.setId("");
  }
  
  @FXML
  public void activeEnrChambre()
    throws SQLException, ClassNotFoundException
  {
    this.txtNomChambre.setText("");
    this.txtTelChambre.setText("");
    this.txtEtageChambre.getItems().get(0);
    this.rbOui.setSelected(false);
    this.rbNon.setSelected(true);
    this.txtPrixChambre.setText("");
    this.txtChauffeauChambre.getItems().get(0);
    this.txtNumCategorie.getItems().get(0);
    this.txtNumType.getItems().get(0);
    
    this.btnValider1.setId("btnEnr11");
    this.btnValider1.setVisible(true);
    this.btnAnnuler1.setVisible(true);
    System.out.println("actionReservation");
  }
  
  @FXML
  public void activeSupChambre()
    throws SQLException, ClassNotFoundException
  {
    this.btnValider1.setId("btnSup11");
    this.btnValider1.setVisible(true);
    this.btnAnnuler1.setVisible(true);
  }
  
  @FXML
  public void activeModChambre()
    throws SQLException, ClassNotFoundException
  {
    this.btnValider1.setId("btnMod11");
    this.btnValider1.setVisible(true);
    this.btnAnnuler1.setVisible(true);
  }
  
  @FXML
  public void activeEnrCategorie()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Categories> Cat = this.categories.findAll();
    this.txtNumCategorie1.setText(String.valueOf(((Categories)Cat.get(Cat.size() - 1)).getNumCategorie() + 1));
    this.txtDescriptionCategorie.setText("");
    
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
  public void activeModCategorie()
    throws SQLException, ClassNotFoundException
  {
    this.btnValider2.setId("btnMod23");
    this.btnValider2.setVisible(true);
    this.btnAnnuler2.setVisible(true);
  }
  
  @FXML
  public void activeEnrTypes()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Types> type = this.types.findAll();
    this.lbNumType.setText(String.valueOf(((Types)type.get(type.size() - 1)).getNumType() + 1));
    this.txtNomType.setText("");
    this.txtDescriptionType.setText("");
    
    this.btnValider3.setId("btnEnr31");
    this.btnValider3.setVisible(true);
    this.btnAnnuler3.setVisible(true);
  }
  
  @FXML
  public void activeSupTypes()
    throws SQLException, ClassNotFoundException
  {
    this.btnValider3.setId("btnSup32");
    this.btnValider3.setVisible(true);
    this.btnAnnuler3.setVisible(true);
  }
  
  @FXML
  public void activeModTypes()
    throws SQLException, ClassNotFoundException
  {
    this.btnValider3.setId("btnMod33");
    this.btnValider3.setVisible(true);
    this.btnAnnuler3.setVisible(true);
  }
  
  public void initialize(URL location, ResourceBundle resources)
  {
    try
    {
      this.stockChambre.setVisible(false);
      affichageChambre();
      affichageCategorie();
      affichageType();
      ObservableList<String> listEtage = FXCollections.observableArrayList();
      listEtage.addAll(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" });
      ObservableList<String> listCategorie = FXCollections.observableArrayList();
      DaoCategories daoCategories = new DaoCategories();
      ArrayList<Categories> categorieChambre = daoCategories.findAll();
      for (int i = 0; i < categorieChambre.size(); i++) {
        listCategorie.add(String.valueOf(((Categories)categorieChambre.get(i)).getNumCategorie()));
      }
      ObservableList<String> listType = FXCollections.observableArrayList();
      DaoTypes daoTypes = new DaoTypes();
      ArrayList<Types> type = daoTypes.findAll();
      for (int i = 0; i < type.size(); i++) {
        listType.add(String.valueOf(((Types)type.get(i)).getNumType()));
      }
      ObservableList<String> chauffeau = FXCollections.observableArrayList();
      chauffeau.addAll(new String[] { "GAZ", "ELECTRIQUE" });
      this.txtChauffeauChambre.setItems(chauffeau);
      this.txtEtageChambre.setItems(listEtage);
      this.txtNumCategorie.setItems(listCategorie);
      this.txtNumType.setItems(listType);
      
      ArrayList<Chambres> cha = this.chambres.findAll();
      affTextFieldChambre(((Chambres)cha.get(0)).getNomChambre());
      
      ArrayList<Categories> cat = this.categories.findAll();
      affTextFieldCategorie(((Categories)cat.get(0)).getNumCategorie());
      
      affTextFieldType(((Types)type.get(0)).getNumType());
      
      this.btnValider1.setVisible(false);
      this.btnAnnuler1.setVisible(false);
      this.btnValider2.setVisible(false);
      this.btnAnnuler2.setVisible(false);
      this.btnValider3.setVisible(false);
      this.btnAnnuler3.setVisible(false);
    }
    catch (SQLException|ClassNotFoundException ex)
    {
      Logger.getLogger(ControlerChambres.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
