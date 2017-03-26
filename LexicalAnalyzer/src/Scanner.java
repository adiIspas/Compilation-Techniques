import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class Scanner {
    private Integer scannerPosition;
    private String  sourceCode;
    private HashSet<Token> tokensTable;
    private HashMap<Integer, Integer> tokensTypes;
    private DFA dfa;

    public Scanner(String sourceCode) throws IOException {
        this.scannerPosition = 0;
        this.sourceCode      = sourceCode;
        this.tokensTable     = new HashSet<>();
        this.dfa             = new DFA();
        this.tokensTypes     = new HashMap<>();

        String FILENAME = "input\\tokens_types.txt";
        BufferedReader br = null;
        FileReader fr = null;
        fr = new FileReader(FILENAME);

        String currentLine;
        br = new BufferedReader(new FileReader(FILENAME));

        while((currentLine = br.readLine()) != null) {
            String[] splited = currentLine.split("\\s+");
            Integer state = Integer.parseInt(splited[0]);
            Integer typeOfToken = Integer.parseInt(splited[1]);

            tokensTypes.put(state, typeOfToken);
        }
    }

    public Token getToken(){
       Integer currentState = dfa.getInitialState();
       String currentTokenValue = "";
       Character currentStatus = 'c';
       Token token = new Token(0,"succes");

       while(currentStatus.equals('c')){
           if(scannerPosition >= sourceCode.length()){
               currentStatus = 'b';

               if(dfa.getFinalStates().contains(currentState) || currentState.equals(dfa.getInitialState()))
                   currentStatus = 's';
           }
           else{
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
                       token = new Token(tokensTypes.get(currentState),currentTokenValue);
                       currentStatus = 's';
                   }
                   else{
                       currentStatus = 'e';
                   }
                }
           }
       }

       if(currentStatus.equals('s')){
           if(currentTokenValue.equals("")){
               token = new Token(0,"succes");
               return token;
           }
           else{
               token = new Token(tokensTypes.get(currentState),currentTokenValue);
               tokensTable.add(token);
               return token;
           }
       }
       else
           return new Token(-1, "error " + currentTokenValue);
    }
}
