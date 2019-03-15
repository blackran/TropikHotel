package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class CommanderT
{
  private SimpleStringProperty NumCommander;
  private SimpleStringProperty TarifCommander;
  private SimpleStringProperty NumClient;
  private SimpleStringProperty NumRepas;
  
  public CommanderT(String NumCommander, String TarifCommander, String NumClient, String NumRepas)
  {
    this.NumCommander = new SimpleStringProperty(NumCommander);
    this.TarifCommander = new SimpleStringProperty(TarifCommander);
    this.NumClient = new SimpleStringProperty(NumClient);
    this.NumRepas = new SimpleStringProperty(NumRepas);
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
  
  public String getNumRepas()
  {
    return this.NumRepas.get();
  }
  
  public void setNumRepas(String NumRepas)
  {
    this.NumRepas = new SimpleStringProperty(NumRepas);
  }
}
