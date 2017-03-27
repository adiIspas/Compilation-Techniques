import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class Scanner {
    private Integer scannerPosition;
    private String sourceCode;
    private HashSet<Token> tokensTable;
    private HashMap<String, Integer> tokensTypes;
    private DFA dfa;
    private HashSet<String> keywords;

    public Scanner(String sourceCode) throws IOException {
        this.scannerPosition = 0;
        this.sourceCode = sourceCode;
        this.tokensTable = new HashSet<>();
        this.dfa = new DFA();
        this.tokensTypes = new HashMap<>();

        this.keywords = new HashSet<>();

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
        Boolean done = false;
        Token token = new Token(0, "success");

        while (!done) {
            String currentState = dfa.getInitialState();
            String currentTokenValue = "";
            Character currentStatus = 'c';

            while (currentStatus.equals('c')) {
                if (scannerPosition >= sourceCode.length()) {
                    currentStatus = 'b';

                    if (dfa.getFinalStates().contains(currentState) || currentState.equals(dfa.getInitialState()))
                        currentStatus = 's';
                } else {
                    Character currentCharacter = sourceCode.charAt(scannerPosition);

//                    if (currentCharacter.equals(' ')){
//                        scannerPosition++;
//                        currentState = dfa.getInitialState();
//                        currentTokenValue = "";
//                        currentStatus = 'c';
//                        continue;
//                    }

                    String nextState = dfa.getTransition(currentState, currentCharacter);

                    if (!nextState.equals("-1")) {
                        currentTokenValue += currentCharacter.toString();
                        currentState = nextState;
                        scannerPosition++;
                    } else {
                        if (dfa.getFinalStates().contains(currentState)) {
                            currentStatus = 's';
                        } else {
                            currentStatus = 'e';
                        }
                    }
                }
            }

            if (currentStatus.equals('s')) {
                if (currentTokenValue.equals("")) {
                    done = true;
                } else {
                        if (tokensTypes.get(currentState) == 1 && keywords.contains(currentTokenValue)){
                            token = new Token(7, currentTokenValue);
                            tokensTable.add(token);
                            done = true;
                        }
                        else {
                            token = new Token(tokensTypes.get(currentState), currentTokenValue);
                            tokensTable.add(token);
                            done = true;
                        }
                }
            } else {
                done = true;
                token = new Token(-1, "error at position " + scannerPosition);
            }
        }

        return token;
    }

    public HashSet<Token> getTokensTable() {
        return tokensTable;
    }
}