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
        this.expresieScanare = "(" + this.expresie + ")#";
    }

    public String getExpresie() {
        return expresie;
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
}
