import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class DFA {
    private String initialState;
    private HashSet<String> finalStates;
    private HashMap<String, HashMap<Character, String>> transitions;

    public DFA() throws IOException {
        String FILENAME = "input\\dfa_c.txt";
        finalStates = new HashSet<>();
        transitions = new HashMap<>();

        String currentLine;
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        int lineNumber = 0;
        while((currentLine = br.readLine()) != null){
            if(lineNumber == 0){
                initialState = currentLine;
            }
            else if(lineNumber == 1){
                String[] splitLine = currentLine.split("\\s+");

                for(int i = 0; i < splitLine.length; i++){
                    finalStates.add(splitLine[i]);
                }
            }
            else{
                HashMap<Character, String> transitionTo = new HashMap<>();
                String[] splitLine = currentLine.split("\\s+");

                String initialState = splitLine[0];
                String characters = splitLine[1];
                String finalState = splitLine[2];

                if (characters.length() >= 2) {
                    switch (characters) {
                        case "\\u00A0" : {
                            transitionTo.put('\u00A0',finalState);
                        }
                        case "\\r" : {
                            transitionTo.put('\r',finalState);
                        }
                        case "\\n" : {
                            transitionTo.put('\n',finalState);
                        }
                        case "\\t" : {
                            transitionTo.put('\t',finalState);
                        }
                        case "\\f" : {
                            transitionTo.put('\f',finalState);
                        }
                        case "\\v" : {

                        }
                        default : {}
                    }
                }
                else {
                    transitionTo.put(characters.charAt(0),finalState);
                }

                if(transitions.containsKey(initialState)){
                    HashMap<Character, String> currentTransitions = transitions.get(initialState);
                    currentTransitions.putAll(transitionTo);
                    transitions.put(initialState,currentTransitions);
                }
                else{
                    transitions.put(initialState,transitionTo);
                }
            }

            lineNumber++;
        }
    }

    public String getTransition(String state, Character character){

        HashMap<Character,String> transitionTo = null;
        String transitionState = null;

        if(transitions.containsKey(state)){
            transitionTo = transitions.get(state);

            if(transitionTo.containsKey(character))
                transitionState = transitionTo.get(character);
        }

        return (transitionState != null) ? transitionState : "-1";
    }

    public String getInitialState() {
        return initialState;
    }

    public HashSet<String> getFinalStates() {
        return finalStates;
    }
}