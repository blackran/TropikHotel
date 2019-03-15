package tropikhotel;

import Desktop.FXMLLoadingController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tropikhotel.DAO.DaoResponsables;
import tropikhotel.GetSet.Responsables;

public class ControlerTropikhotel
  implements Initializable
{
  @FXML
  private JFXTextField PseudoLogin;
  @FXML
  private JFXPasswordField PasswordLogin;
  @FXML
  private Button BtnLogin;
  @FXML
  private AnchorPane principal;
  @FXML
  private JFXSpinner jfxspinner;
  
  @FXML
  public void log()
    throws IOException
  {
    try
    {
      DaoResponsables resp = new DaoResponsables();
      ArrayList<Responsables> respArr = resp.searchAll(this.PseudoLogin.getText());
      if (!respArr.isEmpty())
      {
        this.PasswordLogin.setStyle("");
        for (int i = 0; i < respArr.size(); i++) {
          if ((((Responsables)respArr.get(i)).getPseudoResponsable().equals(this.PseudoLogin.getText())) && (((Responsables)respArr.get(i)).getPasswordResponsable().equals(this.PasswordLogin.getText())))
          {
            this.PasswordLogin.setStyle("");
            this.principal.getChildren().clear();
            Stage stage = (Stage)this.principal.getScene().getWindow();
            stage.setResizable(false);
            Scene scene = this.principal.getScene();
            scene.getStylesheets().clear();
            scene.setFill(Color.TRANSPARENT);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/Desktop/FXMLLoading.fxml"));
            Parent root = (Parent)Loader.load();
            this.principal.getChildren().add(root);
            FXMLLoadingController disp = (FXMLLoadingController)Loader.getController();
            disp.setT(String.valueOf(((Responsables)respArr.get(i)).getNumResponsable()));
          }
          else
          {
            this.PasswordLogin.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
            this.PseudoLogin.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
          }
        }
      }
      else
      {
        this.PseudoLogin.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
        this.PasswordLogin.setStyle("-jfx-focus-color:red;-jfx-unfocus-color:red");
      }
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      Logger.getLogger(ControlerTropikhotel.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @FXML
  public void onClickLogin()
    throws IOException
  {
    log();
  }
  
  @FXML
  public void onClicBas(KeyEvent e)
  {
    if ("DOWN".equals(String.valueOf(e.getCode()))) {
      this.PasswordLogin.requestFocus();
    }
  }
  
  @FXML
  public void onClicHaut(KeyEvent e)
  {
    if ("UP".equals(String.valueOf(e.getCode()))) {
      this.PseudoLogin.requestFocus();
    }
  }
  
  @FXML
  public void onClickCancel()
  {
    Platform.exit();
    System.exit(0);
  }
  
  @FXML
  public void onClickEnter(KeyEvent e)
  {
    if (e.getCode().equals(KeyCode.ENTER)) {
      try
      {
        log();
      }
      catch (IOException ex)
      {
        Logger.getLogger(ControlerTropikhotel.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else if ("UP".equals(String.valueOf(e.getCode()))) {
      this.PseudoLogin.requestFocus();
    }
  }
  
  public void initialize(URL location, ResourceBundle resources) {}
}
