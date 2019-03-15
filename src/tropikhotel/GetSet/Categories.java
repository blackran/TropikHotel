package tropikhotel.GetSet;

public class Categories
{
  private int NumCategorie;
  private String DescriptionCategorie;
  
  public Categories(int NumCategorie, String DescriptionCategorie)
  {
    this.NumCategorie = NumCategorie;
    this.DescriptionCategorie = DescriptionCategorie;
  }
  
  public Categories() {}
  
  public void setNumCategorie(int NumCategorie)
  {
    this.NumCategorie = NumCategorie;
  }
  
  public void setDescriptionCategorie(String DescriptionCategorie)
  {
    this.DescriptionCategorie = DescriptionCategorie;
  }
  
  public int getNumCategorie()
  {
    return this.NumCategorie;
  }
  
  public String getDescriptionCategorie()
  {
    return this.DescriptionCategorie;
  }
}
