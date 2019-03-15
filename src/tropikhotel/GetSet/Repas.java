package tropikhotel.GetSet;

public class Repas
{
  private int NumRepas;
  private String NomRepas;
  private int PrixRepas;
  private String HeureRepas;
  
  public Repas(int NumRepas, String NomRepas, int PrixRepas, String HeureRepas)
  {
    this.NumRepas = NumRepas;
    this.NomRepas = NomRepas;
    this.PrixRepas = PrixRepas;
    this.HeureRepas = HeureRepas;
  }
  
  public Repas()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public int getNumRepas()
  {
    return this.NumRepas;
  }
  
  public void setNumRepas(int NumRepas)
  {
    this.NumRepas = NumRepas;
  }
  
  public String getNomRepas()
  {
    return this.NomRepas;
  }
  
  public void setNomRepas(String NomRepas)
  {
    this.NomRepas = NomRepas;
  }
  
  public int getPrixRepas()
  {
    return this.PrixRepas;
  }
  
  public void setPrixRepas(int PrixRepas)
  {
    this.PrixRepas = PrixRepas;
  }
  
  public String getHeureRepas()
  {
    return this.HeureRepas;
  }
  
  public void setHeureRepas(String HeureRepas)
  {
    this.HeureRepas = HeureRepas;
  }
}
