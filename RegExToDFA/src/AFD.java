import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Adrian Ispas on 15.03.2017.
 */
public class AFD {
    private HashMap<String, Boolean> stari;
    private String alfabet;
    private HashMap<String, Character> tranzitii;
    private String stareInitiala;
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

        stareInitiala = arboreExpresie.getRadacina().getFirstPos().toString();
        boolean found = true;

        stari.put(stareInitiala,false);
        if(stareInitiala.contains("#")){
            stariFinale.add(stareInitiala);
        }

        while(found){
            found = false;
            for (String stare:stari.keySet()) {
                if(stari.get(stare).booleanValue() == false){
                    found = true;
                    stari.put(stare,true);
                }
            }

            if(found == false){
                break;
            }

            HashSet<Integer> stareaCurenta = new HashSet<>();
            for(Character element:alfabet.toCharArray()) {
                for(int i = 1; i < frunzeArbore.size(); i++){
                    if(frunzeArbore.get(i).equals(element)){
                        stareaCurenta.addAll(tabelFollowPos.get(i));
                    }
                }

                if(!stari.containsKey(stareaCurenta.toString())){
                    stari.put(stareaCurenta.toString(),false);
                    if(stareaCurenta.toString().contains("#")){
                        stariFinale.add(stareaCurenta.toString());
                    }
                }

                tranzitii.put(stareaCurenta.toString(),element);
            }
        }
    }

    @Override
    public String toString() {
        return "AFD{" +
                "stari=" + stari +
                ", alfabet='" + alfabet + '\'' +
                ", tranzitii=" + tranzitii +
                ", stareInitiala='" + stareInitiala + '\'' +
                ", stariFinale=" + stariFinale +
                '}';
    }
}
