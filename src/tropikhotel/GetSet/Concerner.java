package tropikhotel.GetSet;

public class Concerner {
    private int NumReservation;
    private String NomChambre;
    private int ReductionConcerner;
    
    public Concerner(int NumReservation, String NomChambre, int ReductionConcerner){
        this.NumReservation = NumReservation;
        this.NomChambre = NomChambre;
        this.ReductionConcerner = ReductionConcerner;
    }

    public Concerner() {}

    public int getNumReservation() {
        return this.NumReservation;
    }

    public void setNumReservation(int NumReservation) {
        this.NumReservation = NumReservation;
    }

    public String getNomChambre() {
        return this.NomChambre;
    }

    public void setNomChambre(String NomChambre) {
        this.NomChambre = NomChambre;
    }

    public int getReductionConcerner() {
        return ReductionConcerner;
    }

    public void setReductionConcerner(int ReductionConcerner) {
        this.ReductionConcerner = ReductionConcerner;
    }
}
