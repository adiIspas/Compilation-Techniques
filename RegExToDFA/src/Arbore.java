import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class Arbore {
    private Nod radacina;

    public Arbore(){
        this.radacina = null;
    }

    public Nod getRadacina() {
        return radacina;
    }

    public void setRadacina(Nod radacina) {
        this.radacina = radacina;
    }

    public void construiesteArbore(ExpresieRegulata regEx){
        String expresie = regEx.getExpresie();
        String alfabet  = regEx.getAlfabet();
        Character lambda = regEx.getLambda();

        Stack<Nod> construireArboreExpresie = new Stack<>();
        int numarCaractere = regEx.getNumarCaractereAlfabetDinExpresie();

        for(int i = expresie.length() - 1; i >= 0; i--){
            Character caracterCurent = expresie.charAt(i);

            if(alfabet.indexOf(caracterCurent.toString()) != -1 || caracterCurent.equals(lambda)){
                boolean nullable = caracterCurent.equals(lambda);

                HashSet<Integer> firstLastBeginPos = new HashSet<>();
                firstLastBeginPos.add(numarCaractere);

                Nod nodCurent = new Nod(caracterCurent, null, null, numarCaractere,
                        nullable, firstLastBeginPos, firstLastBeginPos, new HashSet<Integer>());
                numarCaractere--;
                construireArboreExpresie.push(nodCurent);
            }
            else if(caracterCurent.equals('|') || caracterCurent.equals('.')){
                Nod stanga = construireArboreExpresie.pop();
                Nod dreapta = construireArboreExpresie.pop();
                Nod nodCurent = new Nod(caracterCurent, stanga, dreapta, -1,
                        false, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>());
                construireArboreExpresie.push(nodCurent);
            }
            else if(caracterCurent.equals('*')){
                Nod stanga = construireArboreExpresie.pop();
                Nod nodCurent = new Nod(caracterCurent, stanga, null, -1,
                        false, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>());
                construireArboreExpresie.push(nodCurent);
            }
        }

        radacina = construireArboreExpresie.pop();
    }

    public static void calculeazaFunctii(Nod radacina){
        if(radacina.getStanga() != null){
            calculeazaFunctii(radacina.getStanga());
        }
        if(radacina.getDreapta() != null){
            calculeazaFunctii(radacina.getDreapta());
        }

        switch (radacina.getValoare()){
            case '|':{
                radacina.addFirstPos(radacina.getDreapta().getFirstPos());
                radacina.addFirstPos(radacina.getStanga().getFirstPos());

                radacina.addLastPos(radacina.getDreapta().getLastPos());
                radacina.addLastPos(radacina.getStanga().getLastPos());

                radacina.setNullable(radacina.getDreapta().isNullable() || radacina.getStanga().isNullable());
            } break;
            case '.':{
                if(radacina.getStanga().isNullable()){
                    radacina.addFirstPos(radacina.getDreapta().getFirstPos());
                    radacina.addFirstPos(radacina.getStanga().getFirstPos());
                }
                else{
                    radacina.addFirstPos(radacina.getStanga().getFirstPos());
                    }

                if(radacina.getDreapta().isNullable()){
                    radacina.addLastPos(radacina.getStanga().getLastPos());
                    radacina.addLastPos(radacina.getDreapta().getLastPos());
                }
                else{
                    radacina.addLastPos(radacina.getDreapta().getLastPos());
                }

                radacina.setNullable(radacina.getDreapta().isNullable() && radacina.getStanga().isNullable());
            } break;
            case '*':{
                radacina.addFirstPos(radacina.getStanga().getFirstPos());
                radacina.addLastPos(radacina.getStanga().getLastPos());
                radacina.setNullable(true);
            } break;
            default: {}
        }

            System.out.println("Nod => " + radacina.getValoare() + " Pozitie => " + radacina.getPozitie() +
                    " Nullable => " + radacina.isNullable() + " FirstPos => " + radacina.getFirstPos() +
                    " LastPos => " + radacina.getLastPos());
    }

    @Override
    public String toString() {
        return "Arbore{" +
                "radacina=" + radacina +
                '}';
    }
}
