package Desktop.Children;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

public class FXMLeditResponsableControler implements Initializable
{
  @FXML
  private ComboBox cbUserResponsables;
  @FXML
  private AnchorPane principaleResp;
  @FXML
  private ImageView imageText;
  @FXML
  private Label dtNumResponsable;
  @FXML
  private Label lbImgUrlDetail;
  @FXML
  private JFXTextField txtNomDetail;
  @FXML
  private JFXTextField txtPrenomDetail;
  @FXML
  private JFXTextField txtPseudoDetail;
  @FXML
  private JFXTextField txtPassDetail;
  @FXML
  private JFXTextField txtAddressDetail;
  @FXML
  private JFXTextField txtTelDetail;
  @FXML
  private Pane movePane;
  DaoResponsables daoresponsables = new DaoResponsables();
  
  public void initialize(URL url, ResourceBundle rb)
  {
    this.cbUserResponsables.getItems().addAll(new Object[] { "SUPERS", "USERS" });
    this.lbImgUrlDetail.setVisible(false);
  }
  
  @FXML
  public void afficheDetail(String e)
    throws ClassNotFoundException, SQLException
  {
    if (Integer.parseInt(e) < this.daoresponsables.find(this.daoresponsables.findAll().size()).getNumResponsable() + 1)
    {
      Responsables resp = this.daoresponsables.find(Integer.parseInt(e));
      Image image = new Image("file:" + resp.getImageUrlResponsable());
      this.imageText.setImage(image);
      
      this.lbImgUrlDetail.setText(resp.getImageUrlResponsable());
      this.txtNomDetail.setText(resp.getNomResponsable());
      this.txtPrenomDetail.setText(resp.getPrenomResponsable());
      this.txtPseudoDetail.setText(resp.getPseudoResponsable());
      this.txtPassDetail.setText(resp.getPasswordResponsable());
      this.cbUserResponsables.getSelectionModel().select(resp.getDroitResponsable());
      this.txtAddressDetail.setText(resp.getAddressResponsable());
      this.txtTelDetail.setText(resp.getTelResponsable());
    }
    else
    {
      Responsables resp = this.daoresponsables.find(Integer.parseInt(e));
      Image image = new Image("/Desktop/images/User.jpg");
      this.imageText.setImage(image);
      
      this.lbImgUrlDetail.setText("/Desktop/images/User.jpg");
      this.txtNomDetail.setText("");
      this.txtPrenomDetail.setText("");
      this.txtPseudoDetail.setText("");
      this.txtPassDetail.setText("");
      this.cbUserResponsables.getSelectionModel().selectFirst();
      this.txtAddressDetail.setText("");
      this.txtTelDetail.setText("");
    }
  }
  
  @FXML
  public void onClickQuit()
  {
    Stage stage = (Stage)this.principaleResp.getScene().getWindow();
    stage.close();
  }
  
  @FXML
  public void filesSources()
    throws MalformedURLException
  {
    Stage stage = (Stage)this.principaleResp.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");
    File file = fileChooser.showOpenDialog(stage);
    if (file != null)
    {
      Image image = new Image(file.toURI().toString());
      this.imageText.setImage(image);
      this.lbImgUrlDetail.setText(file.toURI().toString().substring(5, file.toURI().toString().length()));
    }
  }
  
  @FXML
  public void setT(String e)
    throws ClassNotFoundException, SQLException
  {
    this.dtNumResponsable.setText(e);
    afficheDetail(e);
  }
  
  private double xOffset = 0.0D;
  private double yOffset = 0.0D;
  
  public void OnMousePressed(MouseEvent event)
  {
    this.xOffset = event.getSceneX();
    this.yOffset = event.getSceneY();
  }
  
  public void OnMouseDragged(MouseEvent event)
  {
    Stage stage = (Stage)this.principaleResp.getScene().getWindow();
    stage.setX(event.getScreenX() - this.xOffset);
    stage.setY(event.getScreenY() - this.yOffset);
  }
  
  @FXML
  public void ajouterResponsable()
    throws SQLException, ClassNotFoundException
  {
    if (Integer.parseInt(this.dtNumResponsable.getText()) < this.daoresponsables.find(this.daoresponsables.findAll().size()).getNumResponsable() + 1)
    {
      this.daoresponsables.mod(Integer.parseInt(this.dtNumResponsable.getText()), this.txtNomDetail.getText(), this.txtPseudoDetail.getText(), this.txtPassDetail.getText(), this.txtPrenomDetail.getText(), this.txtAddressDetail.getText(), this.txtTelDetail.getText(), this.cbUserResponsables.getValue().toString(), this.lbImgUrlDetail.getText());
      System.out.println("voici Num RESPONSABLE: " + this.daoresponsables.find(Integer.parseInt(this.dtNumResponsable.getText())).getPrenomResponsable());
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("AFFIRMATION");
      alert.setContentText("Modification  reussi");
      alert.show();
    }
    else
    {
      this.daoresponsables.add(Integer.parseInt(this.dtNumResponsable.getText()), this.txtNomDetail.getText(), this.txtPseudoDetail.getText(), this.txtPassDetail.getText(), this.txtPrenomDetail.getText(), this.txtAddressDetail.getText(), this.txtTelDetail.getText(), (String)this.cbUserResponsables.getValue(), this.lbImgUrlDetail.getText());
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("AFFIRMATION");
      alert.setContentText("ajout  reussi");
      alert.show();
    }
  }
}
