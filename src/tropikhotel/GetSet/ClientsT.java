package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class ClientsT
{
  private SimpleStringProperty NumClient;
  private SimpleStringProperty NomClient;
  private SimpleStringProperty TelClient;
  
  public ClientsT(String NumClient, String NomClient, String TelClient)
  {
    this.NumClient = new SimpleStringProperty(NumClient);
    this.NomClient = new SimpleStringProperty(NomClient);
    this.TelClient = new SimpleStringProperty(TelClient);
  }
  
  public String getNumClient()
  {
    return this.NumClient.get();
  }
  
  public String getNomClient()
  {
    return this.NomClient.get();
  }
  
  public String getTelClient()
  {
    return this.TelClient.get();
  }
  
  public void setNumClient(String NumClient)
  {
    this.NumClient = new SimpleStringProperty(NumClient);
  }
  
  public void setNomClient(String NomClient)
  {
    this.NomClient = new SimpleStringProperty(NomClient);
  }
  
  public void setTelClient(String TelClient)
  {
    this.TelClient = new SimpleStringProperty(TelClient);
  }
}
