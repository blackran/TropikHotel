package tropikhotel.GetSet;

public class Chambres
{
  private String NomChambre;
  private String TelChambre;
  private String EtageChambre;
  private String ChauffeauChambre;
  private int PrixChambre;
  private int NumCategorie;
  private int NumType;
  
  public Chambres(String NomChambre, String TelChambre, String EtageChambre, String ChauffeauChambre, int PrixChambre, int NumCategorie, int NumType)
  {
    this.NomChambre = NomChambre;
    this.TelChambre = TelChambre;
    this.EtageChambre = EtageChambre;
    this.ChauffeauChambre = ChauffeauChambre;
    this.PrixChambre = PrixChambre;
    this.NumCategorie = NumCategorie;
    this.NumType = NumType;
  }
  
  public Chambres() {}
  
  public String getNomChambre()
  {
    return this.NomChambre;
  }
  
  public String getTelChambre()
  {
    return this.TelChambre;
  }
  
  public String getEtageChambre()
  {
    return this.EtageChambre;
  }
  
  public int getNumCategorie()
  {
    return this.NumCategorie;
  }
  
  public int getNumType()
  {
    return this.NumType;
  }
  
  public void setNomChambre(String NomChambre)
  {
    this.NomChambre = NomChambre;
  }
  
  public void setTelChambre(String TelChambre)
  {
    this.TelChambre = TelChambre;
  }
  
  public void setEtageChambre(String EtageChambre)
  {
    this.EtageChambre = EtageChambre;
  }
  
  public int getPrixChambre()
  {
    return this.PrixChambre;
  }
  
  public void setPrixChambre(int PrixChambre)
  {
    this.PrixChambre = PrixChambre;
  }
  
  public void setNumCategorie(int NumCategorie)
  {
    this.NumCategorie = NumCategorie;
  }
  
  public void setNumType(int NumType)
  {
    this.NumType = NumType;
  }
  
  public String getChauffeauChambre()
  {
    return this.ChauffeauChambre;
  }
  
  public void setChauffeauChambre(String ChauffeauChambre)
  {
    this.ChauffeauChambre = ChauffeauChambre;
  }
}
