package tropikhotel;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TropikHotel
  extends Application
{
  public Scene stageLogin()
    throws IOException
  {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("FXML.fxml"));
    Scene scene = new Scene(root, 870.0D, 598.0D);
    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    return scene;
  }
  
  public void start(Stage primaryStage)
    throws IOException
  {
    Scene scene = stageLogin();
    
    primaryStage.setResizable(false);
    primaryStage.setFullScreen(false);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }
}
