package tropikhotel.GetSet;

public class Afaire
{
  private int NumAfaire;
  private String DescriptionAfaire;
  private String HeureAfaire;
  
  public Afaire(int NumAfaire, String DescriptionAfaire, String HeureAfaire)
  {
    this.NumAfaire = NumAfaire;
    this.DescriptionAfaire = DescriptionAfaire;
    this.HeureAfaire = HeureAfaire;
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
  
  public String getHeureAfaire()
  {
    return this.HeureAfaire;
  }
  
  public void setHeureAfaire(String HeureAfaire)
  {
    this.HeureAfaire = HeureAfaire;
  }
}
