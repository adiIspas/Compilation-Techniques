/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

        ExpresieRegulata regEx = new ExpresieRegulata("(a|bb)*ba","ab");

        Arbore arbore = new Arbore(regEx);

        arbore.ConstruiesteArbore();

        System.out.print("Done");
    }
}
