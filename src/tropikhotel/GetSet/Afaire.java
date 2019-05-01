package tropikhotel.GetSet;

public class Afaire
{
  private int NumAfaire;
  private String DescriptionAfaire;
  private String EtatAfaire;
  
  public Afaire(int NumAfaire, String DescriptionAfaire, String EtatAfaire)
  {
    this.NumAfaire = NumAfaire;
    this.DescriptionAfaire = DescriptionAfaire;
    this.EtatAfaire = EtatAfaire;
  }
  
  public Afaire() {}
  
  public int getNumAfaire()
  {
    return this.NumAfaire;
  }
  
  public void setNumAfaire(int NumAfaire)
  {
    this.NumAfaire = NumAfaire;
  }
  
  public String getDescriptionAfaire()
  {
    return this.DescriptionAfaire;
  }
  
  public void setDescriptionAfaire(String DescriptionAfaire)
  {
    this.DescriptionAfaire = DescriptionAfaire;
  }
  
  public String getEtatAfaire()
  {
    return this.EtatAfaire;
  }
  
  public void setEtatAfaire(String EtatAfaire)
  {
    this.EtatAfaire = EtatAfaire;
  }
}
