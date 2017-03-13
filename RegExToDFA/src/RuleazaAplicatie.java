/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

        ExpresieRegulata regEx = new ExpresieRegulata("a*.(a|b)","ab");
        Arbore arbore = new Arbore();
        arbore.construiesteArbore(regEx);


        System.out.println(arbore);
        System.out.print("\n------- Done -------\n");
    }
}
