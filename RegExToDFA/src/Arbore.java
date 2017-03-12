import java.util.Stack;

/**
 * Created by Adrian Ispas on 12.03.2017.
 */
public class Arbore {

    private ExpresieRegulata regEx;
    Stack<Character> stiva = new Stack<>();

    public Arbore(ExpresieRegulata regEx){
        this.regEx = regEx;
    }

    public void ConstruiesteArbore(){
        String expresie = regEx.getExpresieScanare();
        for(int i = 0; i < expresie.length(); i++){
            Character elementCurent = expresie.charAt(i);

            if(!elementCurent.equals(')')){
              stiva.push(elementCurent);
            }
            else if(elementCurent.equals(')')){
                String factor = "";

                while(!elementCurent.equals('(')){
                    factor += elementCurent;
                    stiva.pop();
                }

                System.out.println(factor);
            }
        }
    }
}
