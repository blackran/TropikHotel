package tropikhotel.GetSet;

public class Factures
{
  private int NumFacture;
  private String DateFacture;
  private int MontantFacture;
  private int NumReglement;
  private int NumClient;
  
  public Factures(int NumFacture, String DateFacture, int MontantFacture, int NumReglement, int NumClient)
  {
    this.NumFacture = NumFacture;
    this.DateFacture = DateFacture;
    this.MontantFacture = MontantFacture;
    this.NumReglement = NumReglement;
    this.NumClient = NumClient;
  }
  
  public Factures()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public int getNumFacture()
  {
    return this.NumFacture;
  }
  
  public void setNumFacture(int NumFacture)
  {
    this.NumFacture = NumFacture;
  }
  
  public String getDateFacture()
  {
    return this.DateFacture;
  }
  
  public void setDateFacture(String DateFacture)
  {
    this.DateFacture = DateFacture;
  }
  
  public int getMontantFacture()
  {
    return this.MontantFacture;
  }
  
  public void setMontantFacture(int MontantFacture)
  {
    this.MontantFacture = MontantFacture;
  }
  
  public int getNumReglement()
  {
    return this.NumReglement;
  }
  
  public void setNumReglement(int NumReglement)
  {
    this.NumReglement = NumReglement;
  }
  
  public int getNumClient()
  {
    return this.NumClient;
  }
  
  public void setNumClient(int NumClient)
  {
    this.NumClient = NumClient;
  }
}
