import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class Arbore {
    private Nod radacina;

    public Arbore(){
        this.radacina = null;
    }

    public void construiesteArbore(ExpresieRegulata regEx){
        String expresie = regEx.getExpresieScanare();
        String alfabet  = regEx.getAlfabet();

        for(int i = 0; i < expresie.length(); i++){
            Character elementCurent = expresie.charAt(i);

            if(alfabet.indexOf(elementCurent) != -1){
                if(radacina == null){
                    radacina = new Nod(elementCurent, null, null);
                }
                else if(radacina.getStanga() == null){
                    radacina.setStanga(new Nod(elementCurent,null,null));
                }
                else if(radacina.getDreapta() == null){
                    radacina.setDreapta(new Nod(elementCurent, null, null));
                }
            }
            else if(elementCurent.equals('.') || elementCurent.equals('|') || elementCurent.equals('*')) {
                Nod radacinaVeche = radacina;
                radacina = new Nod(elementCurent, radacinaVeche, null);
            }
        }
    }

    public Nod getRadacina() {
        return radacina;
    }

    public void setRadacina(Nod radacina) {
        this.radacina = radacina;
    }

    @Override
    public String toString() {
        return "Arbore{" +
                "radacina=" + radacina +
                '}';
    }
}
