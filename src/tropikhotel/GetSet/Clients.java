package tropikhotel.GetSet;

public class Clients
{
  private int NumClient;
  private String NomClient;
  private String AddressClient;
  private String CpClient;
  private String PaysClient;
  private String TelClient;
  private String EmailClient;
  private String AnneeCreClient;
  
  public Clients(int NumClient, String NomClient, String AddressClient, String CpClient, String PaysClient, String TelClient, String EmailClient, String AnneeCreClient)
  {
    this.NumClient = NumClient;
    this.NomClient = NomClient;
    this.AddressClient = AddressClient;
    this.CpClient = CpClient;
    this.PaysClient = PaysClient;
    this.TelClient = TelClient;
    this.EmailClient = EmailClient;
    this.AnneeCreClient = AnneeCreClient;
  }
  
  public Clients()
  {
    this.NumClient = 0;
  }
  
  public int getNumClient()
  {
    return this.NumClient;
  }
  
  public void setNumClient(int NumClient)
  {
    this.NumClient = NumClient;
  }
  
  public String getNomClient()
  {
    return this.NomClient;
  }
  
  public void setNomClient(String NomClient)
  {
    this.NomClient = NomClient;
  }
  
  public String getAddressClient()
  {
    return this.AddressClient;
  }
  
  public void setAddressClient(String AddressClient)
  {
    this.AddressClient = AddressClient;
  }
  
  public String getCpClient()
  {
    return this.CpClient;
  }
  
  public void setCpClient(String CpClient)
  {
    this.CpClient = CpClient;
  }
  
  public String getPaysClient()
  {
    return this.PaysClient;
  }
  
  public void setPaysClient(String PaysClient)
  {
    this.PaysClient = PaysClient;
  }
  
  public String getTelClient()
  {
    return this.TelClient;
  }
  
  public void setTelClient(String TelClient)
  {
    this.TelClient = TelClient;
  }
  
  public String getEmailClient()
  {
    return this.EmailClient;
  }
  
  public void setEmailClient(String EmailClient)
  {
    this.EmailClient = EmailClient;
  }
  
  public String getAnneeCreClient()
  {
    return this.AnneeCreClient;
  }
  
  public void setAnneeCreClient(String AnneeCreClient)
  {
    this.AnneeCreClient = AnneeCreClient;
  }
}
