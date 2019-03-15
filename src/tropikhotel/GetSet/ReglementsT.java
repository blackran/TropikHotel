package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class ReglementsT
{
  private SimpleStringProperty NumReglement;
  private SimpleStringProperty EtatReglement;
  private SimpleStringProperty MontantReglement;
  
  public ReglementsT(String NumReglement, String EtatReglement, String MontantReglement)
  {
    this.NumReglement = new SimpleStringProperty(NumReglement);
    this.EtatReglement = new SimpleStringProperty(EtatReglement);
    this.MontantReglement = new SimpleStringProperty(MontantReglement);
  }
  
  public String getNumReglement()
  {
    return this.NumReglement.get();
  }
  
  public void setNumReglement(String NumReglement)
  {
    this.NumReglement = new SimpleStringProperty(NumReglement);
  }
  
  public String getEtatReglement()
  {
    return this.EtatReglement.get();
  }
  
  public void setEtatReglement(String EtatReglement)
  {
    this.EtatReglement = new SimpleStringProperty(EtatReglement);
  }
  
  public String getMontantReglement()
  {
    return this.MontantReglement.get();
  }
  
  public void setMontantReglement(String MontantReglement)
  {
    this.MontantReglement = new SimpleStringProperty(MontantReglement);
  }
}
