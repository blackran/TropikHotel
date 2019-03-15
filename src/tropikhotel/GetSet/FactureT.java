package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class FactureT
{
  private SimpleStringProperty NumFacture;
  private SimpleStringProperty DateFacture;
  private SimpleStringProperty MontantFacture;
  private SimpleStringProperty NumReglement;
  private SimpleStringProperty NumClient;
  
  public FactureT(String NumFacture, String DateFacture, String MontantFacture, String NumReglement, String NumClient)
  {
    this.NumFacture = new SimpleStringProperty(NumFacture);
    this.DateFacture = new SimpleStringProperty(DateFacture);
    this.MontantFacture = new SimpleStringProperty(MontantFacture);
    this.NumReglement = new SimpleStringProperty(NumReglement);
    this.NumClient = new SimpleStringProperty(NumClient);
  }
  
  public String getNumFacture()
  {
    return this.NumFacture.get();
  }
  
  public void setNumFacture(String NumFacture)
  {
    this.NumFacture = new SimpleStringProperty(NumFacture);
  }
  
  public String getDateFacture()
  {
    return this.DateFacture.get();
  }
  
  public void setDateFacture(String DateFacture)
  {
    this.DateFacture = new SimpleStringProperty(DateFacture);
  }
  
  public String getMontantFacture()
  {
    return this.MontantFacture.get();
  }
  
  public void setMontantFacture(String MontantFacture)
  {
    this.MontantFacture = new SimpleStringProperty(MontantFacture);
  }
  
  public String getNumReglement()
  {
    return this.NumReglement.get();
  }
  
  public void setNumReglement(String NumReglement)
  {
    this.NumReglement = new SimpleStringProperty(NumReglement);
  }
  
  public String getNumClient()
  {
    return this.NumClient.get();
  }
  
  public void setNumClient(String NumClient)
  {
    this.NumClient = new SimpleStringProperty(NumClient);
  }
}
