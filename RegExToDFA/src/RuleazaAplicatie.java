import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

//        ExpresieRegulata regEx = new ExpresieRegulata(".|ab#","ab#", '^');
//        ExpresieRegulata regEx = new ExpresieRegulata("..*|.ab.ba*|.aa.ba#","ab#", '^');
        ExpresieRegulata regEx = new ExpresieRegulata("....*|ababb#","ab#", '^');

        Arbore arbore = new Arbore();
        arbore.construiesteArbore(regEx);

        arbore.calculeazaFunctii(arbore.getRadacina());

        System.out.print("\n------- Done1! -------\n");
        AFD afd = new AFD(regEx.getAlfabet());
        afd.construiesteAFD(arbore);
        System.out.println(afd);

        System.out.print("\n------- Done1! -------\n");

    }
}
