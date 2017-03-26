import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class DFA {
    private HashSet<Integer> states;
    private Integer initialState;
    private HashSet<Integer> finalStates;
    private HashMap<Integer, HashMap<Character, Integer>> transitions;

    public DFA() throws IOException {
        String FILENAME = "input\\dfa_c.txt";
        finalStates = new HashSet<>();
        transitions = new HashMap<>();

        BufferedReader br = null;
        FileReader fr = null;
        fr = new FileReader(FILENAME);

        String currentLine;
        br = new BufferedReader(new FileReader(FILENAME));

        int lineNumber = 0;
        while((currentLine = br.readLine()) != null){
            if(lineNumber == 0){
                initialState = Integer.parseInt(currentLine);
            }
            else if(lineNumber == 1){
                String[] splited = currentLine.split("\\s+");

                for(int i = 0; i < splited.length; i++){
                    finalStates.add(Integer.parseInt(splited[i]));
                }
            }
            else{
                HashMap<Character, Integer> transitionTo = new HashMap();
                String[] splited = currentLine.split("\\s+");

                Integer initialState = Integer.parseInt(splited[0]);
                Integer finalState = Integer.parseInt(splited[2]);
                String characters = splited[1];

                for(int i = 0; i < characters.length(); i++){
                    transitionTo.put(characters.charAt(i),finalState);
                }

                if(transitions.containsKey(initialState)){
                    HashMap<Character, Integer> currentTransitions = transitions.get(initialState);
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

    public Integer getTransition(Integer state, Character character){

        HashMap<Character,Integer> transitionTo = null;
        Integer transitionState = null;

        if(transitions.containsKey(state)){
            transitionTo = transitions.get(state);

            if(transitionTo.containsKey(character))
                transitionState = transitionTo.get(character);
        }

        return (transitionState != null) ? transitionState : -1;
    }

    public Integer getInitialState() {
        return initialState;
    }

    public HashSet<Integer> getFinalStates() {
        return finalStates;
    }
}