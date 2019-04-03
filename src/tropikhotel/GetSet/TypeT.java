package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class TypeT{
    private SimpleStringProperty NumType;
    private SimpleStringProperty NomType;

    public TypeT(String NumType, String NomType){
      this.NumType = new SimpleStringProperty(NumType);
      this.NomType = new SimpleStringProperty(NomType);
    }

    public String getNumType(){
      return this.NumType.get();
    }

    public void setNumType(String NumType){
      this.NumType = new SimpleStringProperty(NumType);
    }

    public String getNomType(){
      return this.NomType.get();
    }

    public void setNomType(String NomType){
      this.NomType = new SimpleStringProperty(NomType);
    }
}
