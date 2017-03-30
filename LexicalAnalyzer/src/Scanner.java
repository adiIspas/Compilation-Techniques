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
    private char[] sourceCode;
    private HashMap<String, Integer> tokensTable;
    private HashMap<String, Integer> tokensTypes;
    private DFA dfa;
    private HashSet<String> keywords;
    private Integer tokenIndex;

    public Scanner(char[] sourceCode) throws IOException {
        this.scannerPosition = 0;
        this.sourceCode = sourceCode;
        this.tokensTable = new HashMap<>();
        this.dfa = new DFA();
        this.tokensTypes = new HashMap<>();
        this.tokenIndex = 1;
        this.keywords = new HashSet<>();

        tokensTable.put("Success", 0);
        tokensTable.put("Error", -1);
        // Read types of tokens
        String FILENAME = "input\\tokens_types.txt";
        String currentLine;
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        while ((currentLine = br.readLine()) != null) {
            String[] splited = currentLine.split("\\s+");
            String state = splited[0];
            Integer typeOfToken = Integer.parseInt(splited[1]);

            tokensTypes.put(state, typeOfToken);
        }

        // Read keywords
        FILENAME = "input\\keywords_c.txt";

        br = new BufferedReader(new FileReader(FILENAME));
        while ((currentLine = br.readLine()) != null) {
            keywords.add(currentLine);
        }
    }

    public Token getToken() {
        Token token = new Token(tokensTypes.get("OK"),0);
        Boolean done = false;

        while(!done) {
            String currentState = dfa.getInitialState();
            String currentTokenValue = "";
            Character currentStatus = 'c';

            while (currentStatus.equals('c')) {
                if (scannerPosition >= sourceCode.length) {
                    currentStatus = 'b';

                    if (dfa.getFinalStates().contains(currentState) || currentState.equals(dfa.getInitialState()))
                        currentStatus = 's';
                } else {
                    Character currentCharacter = sourceCode[scannerPosition];

                    String nextState = dfa.getTransition(currentState, currentCharacter);

                    if (!nextState.equals("-1")) {
                        currentTokenValue += currentCharacter.toString();
                        currentState = nextState;
                        scannerPosition++;
                    } else {
                        currentStatus = 'b';
                        if (dfa.getFinalStates().contains(currentState) && scannerPosition < sourceCode.length) {
                            currentStatus = 's';
                        }
                        else if (dfa.getFinalStates().contains(currentState) && scannerPosition >= sourceCode.length) {
                            currentStatus = 's';
                        }
                        else if (!dfa.getFinalStates().contains(currentState) && currentStatus.equals('b')) {
                            currentStatus = 'e';
                        }
                    }
                }
            }

            if (currentStatus.equals('s')) {
                if (tokensTypes.get(currentState) == 2) {
                    done = false;
                    continue;
                }
                if(currentTokenValue.equals(""))
                    return token;

                if (tokensTypes.get(currentState).equals(1) && keywords.contains(currentTokenValue)) {

                    if (tokensTable.containsKey(currentTokenValue)) {
                        Integer tempTokenIndex = tokensTable.get(currentTokenValue);
                        token = new Token(tokensTypes.get("Keyword"), tempTokenIndex);
//                        tokensTable.put(currentTokenValue, tempTokenIndex);
                    }
                    else {

                        token = new Token(tokensTypes.get("Keyword"), tokenIndex);
                        tokensTable.put(currentTokenValue, tokenIndex);
                        tokenIndex++;
                    }

                    done = true;
                } else {

                    if (tokensTable.containsKey(currentTokenValue)) {
                        Integer tempTokenIndex = tokensTable.get(currentTokenValue);
                        token = new Token(tokensTypes.get(currentState), tempTokenIndex);
//                        tokensTable.put(currentTokenValue, tempTokenIndex);
                    }
                    else {

                        token = new Token(tokensTypes.get(currentState), tokenIndex);
                        tokensTable.put(currentTokenValue, tokenIndex);
                        tokenIndex++;
                    }

                    done = true;
                }
            } else if (currentStatus.equals('b') || currentStatus.equals('e')) {
                token = new Token(tokensTypes.get("Error"), -1);
                done = true;
            }
        }

        return token;
    }

    public String getTokenByIndex(Integer index){
        if(tokensTable.containsValue(index)){
            HashSet<String> keySet = new HashSet<>();
            keySet.addAll(tokensTable.keySet());

            for (String tokenValue:keySet) {
                if (tokensTable.get(tokenValue).equals(index)) {
                    return tokenValue;
                }
            }
        }

        return "-1";
    }

    public String getDefByTypeIndex(Integer index){
        if(tokensTypes.containsValue(index)){
            HashSet<String> keySet = new HashSet<>();
            keySet.addAll(tokensTypes.keySet());

            for (String tokenValue:keySet) {
                if (tokensTypes.get(tokenValue).equals(index)) {
                    return tokenValue;
                }
            }
        }

        return "-1";
    }

    public HashMap<String, Integer> getTokensTable(){
        return this.tokensTable;
    }
}