import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Adrian Ispas on 15.03.2017.
 */
public class AFD {
    private HashMap<HashSet<Integer>, Boolean> stari;
    private String alfabet;
    private HashMap<String, Character> tranzitii;
    private HashSet<Integer> stareInitiala;
    private HashSet<String> stariFinale;

    public AFD(String alfabet){
        this.stari = new HashMap<>();
        this.alfabet = alfabet;
        this.tranzitii = new HashMap<>();
        this.stareInitiala = null;
        this.stariFinale = new HashSet<>();
    }

    public void construiesteAFD(Arbore arboreExpresie){
        HashMap<Integer, HashSet<Integer>> tabelFollowPos = arboreExpresie.getTabelFollowPos();
        HashMap<Integer, Character> frunzeArbore = arboreExpresie.getFrunze();

        stareInitiala = arboreExpresie.getRadacina().getFirstPos();

        boolean found = true;

        stari.put(stareInitiala,false);
        String nodStareFinala1 = "" + frunzeArbore.size();
        if(stareInitiala.toString().contains(nodStareFinala1)){
            stariFinale.add(stareInitiala.toString());
        }

        while(found){
            HashSet<Integer> stareCurentaCoada = new HashSet<>();
            found = false;
            for (HashSet<Integer> stare:stari.keySet()) {
                if(!stari.get(stare).booleanValue()){
                    found = true;
                    stari.put(stare,true);
                    stareCurentaCoada = stare;
                    break;
                }
            }

            if(!found){
                break;
            }

            for(Character element:alfabet.toCharArray()){
                HashSet<Integer> stareaCurentaCompusa = new HashSet<>();

                for (Integer pozitie:stareCurentaCoada) {
                    if (frunzeArbore.get(pozitie).equals(element)) {
                        if (tabelFollowPos.get(pozitie) != null)
                            stareaCurentaCompusa.addAll(tabelFollowPos.get(pozitie));
                    }
                }

                if(!stari.containsKey(stareaCurentaCompusa) && stareaCurentaCompusa.size() > 0){
                    stari.put(stareaCurentaCompusa,false);

                    String nodStareFinala = "" + frunzeArbore.size();
                    if(stareaCurentaCompusa.toString().contains(nodStareFinala)){
                        stariFinale.add(stareaCurentaCompusa.toString());
                    }
                }

                if(stareaCurentaCompusa.size() > 0)
                    tranzitii.put(stareCurentaCoada.toString() + " => " + stareaCurentaCompusa.toString(), element);
            }
        }
    }

    @Override
    public String toString() {

        String formatareRezultat = "\t\tAFD \n";
        formatareRezultat += "Stare initiala = " + stareInitiala + "\n";
        formatareRezultat += "Stari = " + stari + "\n";
        formatareRezultat += "Stari finale = " + stariFinale + "\n";
        formatareRezultat += "Tranzitii = " + tranzitii;

        return formatareRezultat;
    }
}