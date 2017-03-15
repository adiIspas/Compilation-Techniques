import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Adrian Ispas on 15.03.2017.
 */
public class AFD {
    private HashMap<HashSet<Integer>, Boolean> stari;
    private String alfabet;
    private HashMap<String, HashMap<String,Character>> tranzitii;
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
        if(stareInitiala.toString().contains("#")){
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
                HashSet<Character> alfabetStareCurenta = new HashSet<>();

                for (Integer pozitie:stareCurentaCoada) {
                    if (frunzeArbore.get(pozitie).equals(element)) {
                        alfabetStareCurenta.add(frunzeArbore.get(pozitie));
                        if (pozitie != frunzeArbore.size())
                            stareaCurentaCompusa.addAll(tabelFollowPos.get(pozitie));
                    }
                }

                System.out.println(alfabetStareCurenta + " " + stareaCurentaCompusa);
                if(!stari.containsKey(stareaCurentaCompusa)){
                    stari.put(stareaCurentaCompusa,false);

                    if(alfabetStareCurenta.toString().contains("#")){
                        stariFinale.add(stareaCurentaCompusa.toString());
                    }
                }

//                HashMap<String,Character> tranzitieCatre = new HashMap<>();
//                tranzitieCatre.put(stareaCurentaCompusa.toString(),element);
//                tranzitii.put(stareCurentaCoada.toString(),tranzitieCatre);
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
