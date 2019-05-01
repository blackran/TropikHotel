package tropikhotel.GetSet;

public class Ajouter{
    private int NumRepas;
    private int NumCommander;
    private int QtAjouter;

    public Ajouter(int NumRepas, int NumCommander, int QtAjouter) {
        this.NumRepas = NumRepas;
        this.NumCommander = NumCommander;
        this.QtAjouter = QtAjouter;
    }

    public Ajouter() {
    }

    public int getNumRepas() {
        return NumRepas;
    }

    public void setNumRepas(int NumRepas) {
        this.NumRepas = NumRepas;
    }

    public int getNumCommander() {
        return NumCommander;
    }

    public void setNumCommander(int NumCommander) {
        this.NumCommander = NumCommander;
    }

    public int getQtAjouter() {
        return QtAjouter;
    }

    public void setQtAjouter(int QtAjouter) {
        this.QtAjouter = QtAjouter;
    }
}
