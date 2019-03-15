package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class ChambresT{
    private SimpleStringProperty NomChambre;
    private SimpleStringProperty TelChambre;
    private SimpleStringProperty EtageChambre;
    private SimpleStringProperty ChauffeauChambre;
    private SimpleStringProperty PrixChambre;
    private SimpleStringProperty NumCategorie;
    private SimpleStringProperty NumType;
  
public ChambresT(String NomChambre, String TelChambre, String EtageChambre, String ChauffeauChambre, String PrixChambre, String NumCategorie, String NumType) {
    this.NomChambre = new SimpleStringProperty(NomChambre);
    this.TelChambre = new SimpleStringProperty(TelChambre);
    this.EtageChambre = new SimpleStringProperty(EtageChambre);
    this.ChauffeauChambre = new SimpleStringProperty(ChauffeauChambre);
    this.PrixChambre = new SimpleStringProperty(PrixChambre);
    this.NumCategorie = new SimpleStringProperty(NumCategorie);
    this.NumType = new SimpleStringProperty(NumType);
}
  
  public String getNomChambre()
  {
    return this.NomChambre.get();
  }
  
  public void setNomChambre(String NomChambre)
  {
    this.NomChambre = new SimpleStringProperty(NomChambre);
  }
  
  public String getTelChambre()
  {
    return this.TelChambre.get();
  }
  
  public void setTelChambre(String TelChambre)
  {
    this.TelChambre = new SimpleStringProperty(TelChambre);
  }
  public String getEtageChambre()
  {
    return this.EtageChambre.get();
  }
  
  public void setEtageChambre(String EtageChambre)
  {
    this.EtageChambre = new SimpleStringProperty(EtageChambre);
  }
  public String getChauffeauChambre()
  {
    return this.ChauffeauChambre.get();
  }
  
  public void setChauffeauChambre(String ChauffeauChambre)
  {
    this.ChauffeauChambre = new SimpleStringProperty(ChauffeauChambre);
  }
  public String getPrixChambre()
  {
    return this.PrixChambre.get();
  }
  
  public void setPrixChambre(String PrixChambre)
  {
    this.PrixChambre = new SimpleStringProperty(PrixChambre);
  }
  public String getNumCategorie()
  {
    return this.NumCategorie.get();
  }
  
  public void setNumCategorie(String NumCategorie)
  {
    this.NumCategorie = new SimpleStringProperty(NumCategorie);
  }
  public String getNumType()
  {
    return this.NumType.get();
  }
  
  public void setNumType(String NumType)
  {
    this.NumType = new SimpleStringProperty(NumType);
  }
  
}
