package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class CategorieT
{
  private SimpleStringProperty NumCategorie;
  private SimpleStringProperty DescriptionCategorie;
  
  public CategorieT(String NumCategorie, String DescriptionCategorie)
  {
    this.NumCategorie = new SimpleStringProperty(NumCategorie);
    this.DescriptionCategorie = new SimpleStringProperty(DescriptionCategorie);
  }
  
  public String getNumCategorie()
  {
    return this.NumCategorie.get();
  }
  
  public void setNumCategorie(String NumCategorie)
  {
    this.NumCategorie = new SimpleStringProperty(NumCategorie);
  }
  
  public String getDescriptionCategorie()
  {
    return this.DescriptionCategorie.get();
  }
  
  public void setDescriptionCategorie(String DescriptionCategorie)
  {
    this.DescriptionCategorie = new SimpleStringProperty(DescriptionCategorie);
  }
}
