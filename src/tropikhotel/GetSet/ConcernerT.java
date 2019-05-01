package tropikhotel.GetSet;

import javafx.beans.property.SimpleStringProperty;

public class ConcernerT {
    private SimpleStringProperty NomChambre;
    private SimpleStringProperty ReductionConcerner;
    
    public ConcernerT(String NomChambre, String ReductionConcerner){
        this.NomChambre = new SimpleStringProperty(NomChambre);
        this.ReductionConcerner = new SimpleStringProperty(ReductionConcerner);
    }

    public ConcernerT() {}

    public String getNomChambre() {
        return this.NomChambre.get();
    }

    public void setNomChambre(String NomChambre) {
        this.NomChambre = new SimpleStringProperty(NomChambre);
    }

    public String getReductionConcerner() {
        return this.ReductionConcerner.get();
    }

    public void setReductionConcerner(String ReductionConcerner) {
        this.ReductionConcerner = new SimpleStringProperty(ReductionConcerner);
    }
}
