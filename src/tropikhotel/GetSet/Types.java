package tropikhotel.GetSet;

public class Types
{
  private int NumType;
  private String NomType;
  private String DescriptionType;
  
  public Types(int NumType, String NomType, String DescriptionType)
  {
    this.NumType = NumType;
    this.NomType = NomType;
    this.DescriptionType = DescriptionType;
  }
  
  public String getDescriptionType()
  {
    return this.DescriptionType;
  }
  
  public void setDescriptionType(String DescriptionType)
  {
    this.DescriptionType = DescriptionType;
  }
  
  public Types() {}
  
  public int getNumType()
  {
    return this.NumType;
  }
  
  public void setNumType(int NumType)
  {
    this.NumType = NumType;
  }
  
  public String getNomType()
  {
    return this.NomType;
  }
  
  public void setNomType(String NomType)
  {
    this.NomType = NomType;
  }
}
