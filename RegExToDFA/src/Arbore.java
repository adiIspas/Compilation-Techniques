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

    public Nod getRadacina() {
        return radacina;
    }

    public void setRadacina(Nod radacina) {
        this.radacina = radacina;
    }

    public void construiesteArbore(ExpresieRegulata regEx){
        String expresie = regEx.getExpresie();
        String alfabet  = regEx.getAlfabet();
        int numarCaractere = regEx.getNumarCaractereAlfabetDinExpresie();
        Stack<Nod> construireArboreExpresie = new Stack<>();

        for(int i = expresie.length() - 1; i >= 0; i--){
            Character caracterCurent = expresie.charAt(i);

            if(alfabet.indexOf(caracterCurent.toString()) != -1){
                Nod nodCurent = new Nod(caracterCurent, null, null, numarCaractere,
                        false, new LinkedList<Integer>(), new LinkedList<Integer>(), new LinkedList<Integer>());
                numarCaractere--;
                construireArboreExpresie.push(nodCurent);
            }
            else if(caracterCurent.equals('|') || caracterCurent.equals('.')){
                Nod stanga = construireArboreExpresie.pop();
                Nod dreapta = construireArboreExpresie.pop();
                Nod nodCurent = new Nod(caracterCurent, stanga, dreapta, -1,
                        false, new LinkedList<Integer>(), new LinkedList<Integer>(), new LinkedList<Integer>());
                construireArboreExpresie.push(nodCurent);
            }
            else if(caracterCurent.equals('*')){
                Nod stanga = construireArboreExpresie.pop();
                Nod nodCurent = new Nod(caracterCurent, stanga, null, -1,
                        false, new LinkedList<Integer>(), new LinkedList<Integer>(), new LinkedList<Integer>());
                construireArboreExpresie.push(nodCurent);
            }
        }

        System.out.println("Dimensiune stiva " + construireArboreExpresie.size());

        radacina = construireArboreExpresie.pop();
        while(!construireArboreExpresie.empty()){
            System.out.println(construireArboreExpresie.pop());
        }
    }

    public void parcuregerePostordine(Nod radacina, Stack<Integer> pozitii){

        if (radacina == null)
            return;

        if(radacina.getStanga() == null && radacina.getDreapta() == null){
            radacina.setPozitie(pozitii.pop());
            radacina.setNullable(false);
            System.out.print(radacina.getValoare() + " - " + radacina.getPozitie() + " \n");
        }

        if(radacina.getValoare().equals('*')){
            radacina.setNullable(true);
        }

        if(radacina.getValoare().equals('|')){
            radacina.setNullable(radacina.getStanga().isNullable() || radacina.getDreapta().isNullable());
        }

        if(radacina.getValoare().equals('.')){
            radacina.setNullable(radacina.getStanga().isNullable() && radacina.getDreapta().isNullable());
        }

        parcuregerePostordine(radacina.getStanga(), pozitii);
        parcuregerePostordine(radacina.getDreapta(), pozitii);
    }

    @Override
    public String toString() {
        return "Arbore{" +
                "radacina=" + radacina +
                '}';
    }
}
