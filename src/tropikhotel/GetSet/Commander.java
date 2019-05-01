package tropikhotel.GetSet;

public class Commander {
    private int NumCommander;
    private int TarifCommander;
    private String DateCommander;
    private int NumClient;
    private int NumReglement;

    public Commander(int NumCommander, int TarifCommander, String DateCommander, int NumClient, int NumReglement){
        this.NumCommander = NumCommander;
        this.TarifCommander = TarifCommander;
        this.DateCommander = DateCommander;
        this.NumClient = NumClient;
        this.NumReglement = NumReglement;
    }

    public Commander(){   
    }
  
    public int getTarifCommander(){
      return this.TarifCommander;
    }

    public void setTarifCommander(int TarifCommander)
    {
      this.TarifCommander = TarifCommander;
    }

    public String getDateCommander()
    {
      return this.DateCommander;
    }

    public void setDateCommander(String DateCommander)
    {
      this.DateCommander = DateCommander;
    }

    public int getNumCommander()
    {
      return this.NumCommander;
    }

    public int getNumClient()
    {
      return this.NumClient;
    }

    public int getNumReglement(){
        return this.NumReglement;
    }

    public void setNumCommander(int NumCommander) {
        this.NumCommander = NumCommander;
    }

    public void setNumClient(int NumClient) {
        this.NumClient = NumClient;
    }

    public void setNumReglement(int NumReglement) {
        this.NumReglement = NumReglement;
    }
}
