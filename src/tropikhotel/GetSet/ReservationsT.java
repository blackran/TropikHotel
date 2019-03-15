package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class ReservationsT
{
  private SimpleStringProperty NumReservation;
  private SimpleStringProperty DateDebutReservation;
  private SimpleStringProperty DateFinReservation;
  private SimpleStringProperty NumClient;
  
  public ReservationsT(String NumReservation, String DateDebutReservation, String DateFinReservation, String NumClient)
  {
    this.NumReservation = new SimpleStringProperty(NumReservation);
    this.DateDebutReservation = new SimpleStringProperty(DateDebutReservation);
    this.DateFinReservation = new SimpleStringProperty(DateFinReservation);
    this.NumClient = new SimpleStringProperty(NumClient);
  }
  
  public String getNumReservation()
  {
    return this.NumReservation.get();
  }
  
  public void setNumReservation(String NumReservation)
  {
    this.NumReservation = new SimpleStringProperty(NumReservation);
  }
  
  public String getDateDebutReservation()
  {
    return this.DateDebutReservation.get();
  }
  
  public void setDateDebutReservation(String DateDebutReservation)
  {
    this.DateDebutReservation = new SimpleStringProperty(DateDebutReservation);
  }
  
  public String getDateFinReservation()
  {
    return this.DateFinReservation.get();
  }
  
  public void setDateFinReservation(String DateFinReservation)
  {
    this.DateFinReservation = new SimpleStringProperty(DateFinReservation);
  }
  
  public String getNumClient()
  {
    return this.NumClient.get();
  }
  
  public void setNumClient(String NumClient)
  {
    this.NumClient = new SimpleStringProperty(NumClient);
  }
}
