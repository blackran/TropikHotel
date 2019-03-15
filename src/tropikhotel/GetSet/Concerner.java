package tropikhotel.GetSet;

public class Concerner
{
  private int NumReservation;
  private String NomChambre;
  
  public Concerner() {}
  
  public int getNumReservation()
  {
    return this.NumReservation;
  }
  
  public void setNumReservation(int NumReservation)
  {
    this.NumReservation = NumReservation;
  }
  
  public String getNomChambre()
  {
    return this.NomChambre;
  }
  
  public void setNomChambre(String NomChambre)
  {
    this.NomChambre = NomChambre;
  }
  
  public Concerner(int NumReservation, String NomChambre)
  {
    this.NumReservation = NumReservation;
    this.NomChambre = NomChambre;
  }
}
