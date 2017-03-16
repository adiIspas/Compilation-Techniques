/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class RuleazaAplicatie {
    public static void main(String []args) {

//        ExpresieRegulata regEx = new ExpresieRegulata(".|ab#","ab#", '^');
//        ExpresieRegulata regEx = new ExpresieRegulata("..*|.ab.ba*|.aa.ba#","ab#", '^');
//        ExpresieRegulata regEx = new ExpresieRegulata("....*|ababb#","ab#", '^');
        ExpresieRegulata regEx = new ExpresieRegulata("....*|ababb#","ab#", '^');
        Arbore arbore = new Arbore();

        System.out.print("\n------- Start -------\n\n");
        arbore.construiesteArbore(regEx);

        arbore.calculeazaFunctii(arbore.getRadacina());
        AFD afd = new AFD(regEx.getAlfabet());
        afd.construiesteAFD(arbore);

        System.out.println(afd);
        System.out.print("\n------- Done -------\n");

    }
}