package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class CommanderT
{
  private SimpleStringProperty NumCommander;
  private SimpleStringProperty TarifCommander;
  private SimpleStringProperty NumClient;
  private SimpleStringProperty NumReglement;
  
  public CommanderT(String NumCommander, String TarifCommander, String NumClient, String NumReglement)
  {
    this.NumCommander = new SimpleStringProperty(NumCommander);
    this.TarifCommander = new SimpleStringProperty(TarifCommander);
    this.NumClient = new SimpleStringProperty(NumClient);
    this.NumReglement = new SimpleStringProperty(NumReglement);
  }
  
  public String getNumCommander()
  {
    return this.NumCommander.get();
  }
  
  public void setNumCommander(String NumCommander)
  {
    this.NumCommander = new SimpleStringProperty(NumCommander);
  }
  
  public String getTarifCommander()
  {
    return this.TarifCommander.get();
  }
  
  public void setTarifCommander(String TarifCommander)
  {
    this.TarifCommander = new SimpleStringProperty(TarifCommander);
  }
  
  public String getNumClient()
  {
    return this.NumClient.get();
  }
  
  public void setNumClient(String NumClient)
  {
    this.NumClient = new SimpleStringProperty(NumClient);
  }
  
  public String getNumReglement()
  {
    return this.NumReglement.get();
  }
  
  public void setNumReglement(String NumReglement)
  {
    this.NumReglement = new SimpleStringProperty(NumReglement);
  }
}
