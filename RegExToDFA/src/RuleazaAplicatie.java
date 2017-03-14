import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

//        ExpresieRegulata regEx = new ExpresieRegulata(".|ab#","ab#");
        ExpresieRegulata regEx = new ExpresieRegulata("..*|.ab.ba*|.aa.ba#","ab#");
//        ExpresieRegulata regEx = new ExpresieRegulata("..*|a.bb*|b.a.ab#","ab#");
        Arbore arbore = new Arbore();
        arbore.construiesteArbore(regEx);

        System.out.println(arbore);
        System.out.print("\n------- Done -------\n");
    }
}
