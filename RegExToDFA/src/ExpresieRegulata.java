/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class ExpresieRegulata {
    private String expresie;
    private String alfabet;
    private Character lambda;

    public ExpresieRegulata(String expresie, String alfabet, Character lambda){
        this.expresie = expresie;
        this.alfabet = alfabet;
        this.lambda = lambda;
    }

    public String getExpresie() {
        return expresie;
    }

    public String getAlfabet() {
        return alfabet;
    }

    public Character getLambda() {
        return lambda;
    }

    public int getNumarCaractereAlfabetDinExpresie(){
        int numarCaractere = 0;
        for(int i = 0; i < expresie.length(); i++){
            if(alfabet.indexOf(expresie.charAt(i)) != -1){
                numarCaractere++;
            }
        }

        return numarCaractere;
    }
}