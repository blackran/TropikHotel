package tropikhotel.GetSet;

public class Reserver
{
  private int NumReservation;
  private String DateDebutReservation;
  private String DateFinReservation;
  private int NbJourReservation;
  private String EtatReservation;
  private int NumClient;
  private int NumResponsable;
  private int NumReglement;
  
  public Reserver(int NumReservation, String DateDebutReservation, String DateFinReservation, int NbJourReservation, String EtatReservation, int NumClient, int NumResponsable, int NumReglement)
  {
    this.NumReservation = NumReservation;
    this.DateDebutReservation = DateDebutReservation;
    this.DateFinReservation = DateFinReservation;
    this.NbJourReservation = NbJourReservation;
    this.EtatReservation = EtatReservation;
    this.NumClient = NumClient;
    this.NumResponsable = NumResponsable;
    this.NumReglement = NumReglement;
  }
  
  public Reserver()
  {
    this.NumReservation = 0;
  }
  
  public int getNumReservation()
  {
    return this.NumReservation;
  }
  
  public void setNumReservation(int NumReservation)
  {
    this.NumReservation = NumReservation;
  }
  
  public String getDateDebutReservation()
  {
    return this.DateDebutReservation;
  }
  
  public void setDateDebutReservation(String DateDebutReservation)
  {
    this.DateDebutReservation = DateDebutReservation;
  }
  
  public String getDateFinReservation()
  {
    return this.DateFinReservation;
  }
  
  public void setDateFinReservation(String DateFinReservation)
  {
    this.DateFinReservation = DateFinReservation;
  }
  
  public int getNbJourReservation()
  {
    return this.NbJourReservation;
  }
  
  public void setNbJourReservation(int NbJourReservation)
  {
    this.NbJourReservation = NbJourReservation;
  }
  
  public String getEtatReservation()
  {
    return this.EtatReservation;
  }
  
  public void setEtatReservation(String EtatReservation)
  {
    this.EtatReservation = EtatReservation;
  }
  
  public int getNumClient()
  {
    return this.NumClient;
  }
  
  public void setNumClient(int NumClient)
  {
    this.NumClient = NumClient;
  }
  
  public int getNumResponsable()
  {
    return this.NumResponsable;
  }
  
  public void setNumResponsable(int NumResponsable)
  {
    this.NumResponsable = NumResponsable;
  }
  
  public int getNumReglement()
  {
    return this.NumReglement;
  }
  
  public void setNumReglement(int NumReglement)
  {
    this.NumReglement = NumReglement;
  }
}
