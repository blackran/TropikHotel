package tropikhotel.GetSet;

public class Responsables
{
  private int NumResponsable;
  private String NomResponsable;
  private String PseudoResponsable;
  private String PasswordResponsable;
  private String PrenomResponsable;
  private String AddressResponsable;
  private String TelResponsable;
  private String DroitResponsable;
  private String ImageUrlResponsable;
  
  public Responsables(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable, String PrenomResponsable, String AddressResponsable, String TelResponsable, String DroitResponsable, String ImageUrlResponsable)
  {
    this.NumResponsable = NumResponsable;
    this.NomResponsable = NomResponsable;
    this.PseudoResponsable = PseudoResponsable;
    this.PasswordResponsable = PasswordResponsable;
    this.PrenomResponsable = PrenomResponsable;
    this.AddressResponsable = AddressResponsable;
    this.TelResponsable = TelResponsable;
    this.DroitResponsable = DroitResponsable;
    this.ImageUrlResponsable = ImageUrlResponsable;
  }
  
  public Responsables() {}
  
  public int getNumResponsable()
  {
    return this.NumResponsable;
  }
  
  public void setNumResponsable(int NumResponsable)
  {
    this.NumResponsable = NumResponsable;
  }
  
  public String getNomResponsable()
  {
    return this.NomResponsable;
  }
  
  public void setNomResponsable(String NomResponsable)
  {
    this.NomResponsable = NomResponsable;
  }
  
  public String getPseudoResponsable()
  {
    return this.PseudoResponsable;
  }
  
  public void setPseudoResponsable(String PseudoResponsable)
  {
    this.PseudoResponsable = PseudoResponsable;
  }
  
  public String getPasswordResponsable()
  {
    return this.PasswordResponsable;
  }
  
  public void setPasswordResponsable(String PasswordResponsable)
  {
    this.PasswordResponsable = PasswordResponsable;
  }
  
  public String getPrenomResponsable()
  {
    return this.PrenomResponsable;
  }
  
  public void setPrenomResponsable(String PrenomResponsable)
  {
    this.PrenomResponsable = PrenomResponsable;
  }
  
  public String getAddressResponsable()
  {
    return this.AddressResponsable;
  }
  
  public void setAddressResponsable(String AddressResponsable)
  {
    this.AddressResponsable = AddressResponsable;
  }
  
  public String getTelResponsable()
  {
    return this.TelResponsable;
  }
  
  public void setTelResponsable(String TelResponsable)
  {
    this.TelResponsable = TelResponsable;
  }
  
  public String getDroitResponsable()
  {
    return this.DroitResponsable;
  }
  
  public void setDroitResponsable(String DroitResponsable)
  {
    this.DroitResponsable = DroitResponsable;
  }
  
  public String getImageUrlResponsable()
  {
    return this.ImageUrlResponsable;
  }
  
  public void setImageUrlResponsable(String ImageUrlResponsable)
  {
    this.ImageUrlResponsable = ImageUrlResponsable;
  }
}
