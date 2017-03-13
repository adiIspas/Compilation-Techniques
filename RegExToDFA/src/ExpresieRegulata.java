import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class ExpresieRegulata {
    private String expresie;
    private String alfabet;
    private String expresieScanare;

    public ExpresieRegulata(String expresie, String alfabet){
        this.expresie = expresie;
        this.alfabet = alfabet;
        this.expresieScanare = "(" + this.expresie + ").#";
    }

    public void setExpresie(String expresie) {
        this.expresie = expresie;
    }

    public String getAlfabet() {
        return alfabet;
    }

    public void setAlfabet(String alfabet) {
        this.alfabet = alfabet;
    }

    public String getExpresieScanare() {
        return expresieScanare;
    }

    private int getNumarCaractereAlfabet(){
        int numarCaractere = 0;
        for(int i = 0; i < expresieScanare.length(); i++){
            if(alfabet.indexOf(expresieScanare.charAt(i)) != -1){
                numarCaractere++;
            }
        }

        return numarCaractere;
    }

    public Stack<Integer> construiestePozitii(){
        Stack<Integer> pozitii = new Stack<>();

        for(int i = getNumarCaractereAlfabet(); i >= 1; i--){
            pozitii.push(i);
        }

        return pozitii;
    }
}
