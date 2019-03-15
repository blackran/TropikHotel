package Desktop.Notification;

import Desktop.ControlerDesktop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class SearchController
  implements Initializable
{
  @FXML
  private AnchorPane readSearch;
  
  public void initialize(URL url, ResourceBundle rb)
  {
    try
    {
      setT();
    }
    catch (IOException ex)
    {
      Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @FXML
  public void setT()
    throws IOException
  {
    FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Desktop/FXMLDesktop.fxml"));
    
    Loader.setRoot(Loader.load());
    
    ControlerDesktop disp = (ControlerDesktop)Loader.getController();
    disp.getNotificationChildren(this.readSearch.getChildrenUnmodifiable());
  }
}
