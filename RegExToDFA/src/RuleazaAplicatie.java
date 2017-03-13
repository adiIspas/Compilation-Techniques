import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

        ExpresieRegulata regEx = new ExpresieRegulata("(a|b)*.a","ab#");
        Arbore arbore = new Arbore();
        arbore.construiesteArbore(regEx);

        Stack<Integer> pozitii = regEx.construiestePozitii();

        arbore.parcuregerePostordine(arbore.getRadacina(), pozitii);
        System.out.println(arbore);
        System.out.print("\n------- Done -------\n");
    }
}
