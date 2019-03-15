package tropikhotel.GetSet;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class RecupNodeNotif
{
  ObservableList<Node> node;
  
  public RecupNodeNotif(ObservableList<Node> node)
  {
    this.node = node;
  }
  
  public RecupNodeNotif() {}
  
  public ObservableList<Node> getNode()
  {
    return this.node;
  }
  
  public void setNode(ObservableList<Node> node)
  {
    this.node = node;
  }
}
