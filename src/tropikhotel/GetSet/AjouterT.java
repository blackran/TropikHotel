package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class AjouterT{
    private SimpleStringProperty NumRepas;
    private SimpleStringProperty NomRepas;
    private SimpleStringProperty QtAjouter;

    public AjouterT(String NumRepas, String NomRepas, String QtAjouter){
      this.NumRepas = new SimpleStringProperty(NumRepas);
      this.NomRepas = new SimpleStringProperty(NomRepas);
      this.QtAjouter = new SimpleStringProperty(QtAjouter);
    }

    public String getNumRepas(){
      return this.NumRepas.get();
    }

    public void setNumRepas(String NumRepas){
      this.NumRepas = new SimpleStringProperty(NumRepas);
    }

    public String getNomRepas(){
      return this.NomRepas.get();
    }

    public void setNomRepas(String NomRepas){
      this.NomRepas = new SimpleStringProperty(NomRepas);
    }
    
    public String getQtAjouter(){
      return this.QtAjouter.get();
    }

    public void setQtAjouter(String QtAjouter){
      this.QtAjouter = new SimpleStringProperty(QtAjouter);
    }
}
