package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class RepasT
{
    private SimpleStringProperty NumRepas;
    private SimpleStringProperty NomRepas;
    private SimpleStringProperty Heure;
    private SimpleStringProperty PrixRepas;

    public RepasT(String NumRepas, String NomRepas, String Heure, String PrixRepas){
        this.NumRepas = new SimpleStringProperty(NumRepas);
        this.NomRepas = new SimpleStringProperty(NomRepas);
        this.Heure = new SimpleStringProperty(Heure);
        this.PrixRepas = new SimpleStringProperty(PrixRepas);
    }

    public String getNumRepas()
    {
      return this.NumRepas.get();
    }

    public void setNumRepas(String NumRepas)
    {
      this.NumRepas = new SimpleStringProperty(NumRepas);
    }

    public String getNomRepas()
    {
      return this.NomRepas.get();
    }

    public void setNomRepas(String NomRepas)
    {
      this.NomRepas = new SimpleStringProperty(NomRepas);
    }

    public String getHeure()
    {
      return this.Heure.get();
    }

    public void setHeure(String Heure)
    {
      this.Heure = new SimpleStringProperty(Heure);
    }

    public String getPrixRepas()
    {
      return this.PrixRepas.get();
    }

    public void setPrixRepas(String PrixRepas)
    {
      this.PrixRepas = new SimpleStringProperty(PrixRepas);
    }
}
