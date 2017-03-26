import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class Scanner {
    private Integer scannerPosition;
    private String  sourceCode;
    private HashSet<Token> tokensTable;
    private DFA dfa;

    public Scanner(String sourceCode) throws IOException {
        this.scannerPosition = 0;
        this.sourceCode      = sourceCode;
        this.tokensTable     = new HashSet<>();
        this.dfa = new DFA();
    }

    public Token getToken(){
       Boolean finish = false;

       Integer currentState = dfa.getInitialState();
       String currentTokenValue = "";
       Token token = null;

       while(!finish && scannerPosition < sourceCode.length()){
            Character currentCharacter = sourceCode.charAt(scannerPosition);

            if(currentCharacter.equals(' '))
                currentCharacter = '~';

            Integer nextState = dfa.getTransition(currentState,currentCharacter);

            if(nextState != -1){
                currentTokenValue += currentCharacter.toString();
                currentState = nextState;
                scannerPosition++;
            }
            else{
                if(dfa.getFinalStates().contains(currentState)){
                    token = new Token(currentState,currentTokenValue);
                }
                finish = true;
            }
       }

       return token;
    }

    public Integer getScannerPosition() {
        return scannerPosition;
    }
}
