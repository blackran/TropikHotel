package tropikhotel.GetSet;

public class Commander
{
  private int NumCommander;
  private int TarifCommander;
  private String DateCommander;
  private int NumClient;
  private int NumRepas;
  
  public Commander(int NumCommander, int TarifCommander, String DateCommander, int NumClient, int NumRepas)
  {
    this.NumCommander = NumCommander;
    this.TarifCommander = TarifCommander;
    this.DateCommander = DateCommander;
    this.NumClient = NumClient;
    this.NumRepas = NumRepas;
  }
  
  public Commander()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public int getTarifCommander()
  {
    return this.TarifCommander;
  }
  
  public void setTarifCommander(int TarifCommander)
  {
    this.TarifCommander = TarifCommander;
  }
  
  public String getDateCommander()
  {
    return this.DateCommander;
  }
  
  public void setDateCommander(String DateCommander)
  {
    this.DateCommander = DateCommander;
  }
  
  public int getNumCommander()
  {
    return this.NumCommander;
  }
  
  public int getNumClient()
  {
    return this.NumClient;
  }
  
  public int getNumRepas()
  {
    return this.NumRepas;
  }
  
  public void setNumCommander(int NumCommander)
  {
    this.NumCommander = NumCommander;
  }
  
  public void setNumClient(int NumClient)
  {
    this.NumClient = NumClient;
  }
  
  public void setNumRepas(int NumRepas)
  {
    this.NumRepas = NumRepas;
  }
}
