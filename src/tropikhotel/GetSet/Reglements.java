package tropikhotel.GetSet;

public class Reglements
{
  private int NumReglement;
  private String EtatReglement;
  private int MontantReglement;
  private String AnneReglement;
  
  public Reglements(int NumReglement, String EtatReglement, int MontantReglement, String AnneReglement)
  {
    this.NumReglement = NumReglement;
    this.EtatReglement = EtatReglement;
    this.MontantReglement = MontantReglement;
    this.AnneReglement = AnneReglement;
  }
  
  public Reglements()
  {
    this.NumReglement = 0;
    this.EtatReglement = "non regler";
    this.MontantReglement = 0;
    this.AnneReglement = "";
  }
  
  public int getNumReglement()
  {
    return this.NumReglement;
  }
  
  public void setNumReglement(int NumReglement)
  {
    this.NumReglement = NumReglement;
  }
  
  public String getEtatReglement()
  {
    return this.EtatReglement;
  }
  
  public void setEtatReglement(String EtatReglement)
  {
    this.EtatReglement = EtatReglement;
  }
  
  public int getMontantReglement()
  {
    return this.MontantReglement;
  }
  
  public void setMontantReglement(int MontantReglement)
  {
    this.MontantReglement = MontantReglement;
  }
  
  public String getAnneReglement()
  {
    return this.AnneReglement;
  }
  
  public void setAnneReglement(String AnneReglement)
  {
    this.AnneReglement = AnneReglement;
  }
}
