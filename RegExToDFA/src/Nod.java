import java.util.List;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class Nod {
    private Character valoare;
    private Nod stanga;
    private Nod dreapta;

    public Nod(Character valoare, Nod stanga, Nod dreapta){
        this.valoare = valoare;
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public Character getValoare() {
        return valoare;
    }

    public void setValoare(Character valoare) {
        this.valoare = valoare;
    }

    public Nod getStanga() {
        return stanga;
    }

    public void setStanga(Nod stanga) {
        this.stanga = stanga;
    }

    public Nod getDreapta() {
        return dreapta;
    }

    public void setDreapta(Nod dreapta) {
        this.dreapta = dreapta;
    }

    @Override
    public String toString() {
        return "Nod{" +
                "valoare=" + valoare +
                ", stanga=" + stanga +
                ", dreapta=" + dreapta +
                '}';
    }
}
