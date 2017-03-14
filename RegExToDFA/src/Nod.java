import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class Nod {
    private Character valoare;
    private Nod stanga;
    private Nod dreapta;
    private int pozitie;
    private boolean nullable;
    private HashSet<Integer> firstPos = new HashSet<>();
    private HashSet<Integer> lastPos = new HashSet<>();
    private HashSet<Integer> followPos = new HashSet<>();

    public Nod(Character valoare, Nod stanga, Nod dreapta, int pozitie, boolean nullable, HashSet<Integer> firstPos, HashSet<Integer> lastPos, HashSet<Integer> followPos){
        this.valoare = valoare;
        this.stanga = stanga;
        this.dreapta = dreapta;
        this.pozitie = pozitie;
        this.nullable = nullable;
        this.firstPos = firstPos;
        this.lastPos = lastPos;
        this.followPos = followPos;
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

    public int getPozitie() {
        return pozitie;
    }

    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public HashSet<Integer> getFirstPos() {
        return firstPos;
    }

    public void addFirstPos(HashSet<Integer>  firstPos) {
        this.firstPos.addAll(firstPos);
    }

    public HashSet<Integer> getLastPos() {
        return lastPos;
    }

    public void addLastPos(HashSet<Integer>  lastPos) {
        this.lastPos.addAll(lastPos);
    }

    public HashSet<Integer> getFollowPos() {
        return followPos;
    }

    public void addFollowPos(HashSet<Integer>  followPos) {
        this.followPos.addAll(followPos);
    }

    @Override
    public String toString() {
        return "Nod{" +
                "valoare=" + valoare +
                ", stanga=" + stanga +
                ", dreapta=" + dreapta +
                ", pozitie= " + pozitie +
                ", nullable= " + nullable +
                '}';
    }
}
