package fromzad1;

import java.util.*;
import fromzad1.objekts.*;
import fromzad1.myexceptions.*;

public class PetriNet {

    private Map<Long, Place> placeMap = new HashMap<Long, Place>();
    private Map<Long, Transition> transitionMap = new HashMap<Long, Transition>();
    private Map<Long, Arc> arcMap = new HashMap<Long, Arc>();

    /*public void defaultnyPetriSiet(){

        for (int p = 0; p < 5; p++){
            addTransition(p+1, "p"+(p+1), 0, 0);
        }

        for (int m = 0; m < 7; m++){
            addPlace(m+1, "m"+(m+1), 0, 0);
        }
        placeMap.get(3).setToken(1);
        placeMap.get(4).setToken(0);
        placeMap.get(5).setToken(5);

        addArc(1, placeMap.get(2),  transitionMap.get(3),1, 0, 0);
        addArc(2, transitionMap.get(3), placeMap.get(1), 5, 0, 0);
        addArc(3, placeMap.get(3),  transitionMap.get(4),1, 0, 0);
        addArc(4, transitionMap.get(4), placeMap.get(3), 2, 0, 0);
        addArc(5, placeMap.get(4),  transitionMap.get(5),1, 0, 0);
        addArc(6, transitionMap.get(5), placeMap.get(6), 1, 0, 0);
        addArc(7, transitionMap.get(5), placeMap.get(7), 1, 0, 0);
        addResetArc(8, placeMap.get(5), transitionMap.get(5), 1, 0, 0);
    }*/

//    metoda, ktora pusti prechod podla cisla id
//    ak nevie pustit prechod, vypise, ktory prechod nebol pustitelny
    public void pustiPrechod(int id) {
        try {
            transitionMap.get(id).fireTransition();
        } catch (ExceptionFire exceptionFire) {
            exceptionFire.printStackTrace();
        }
    }

/*
 * Metoda na kontrolu, ci bol petrinet nacitane dobre z xml fileu
 */
    public void printTransitions(){
        for (Transition transition : transitionMap.values()){
            System.out.println(
                    transition.getID() + " " + transition.getName()
            );
        }
    }


//    metody na manpulaciu so sietou
//    potrebne hlavne pre testovanie
    public void addArc(Arc arc){
            arcMap.put(arc.getID(), arc);
    }
    public void addResetArc(ArcReset reset){
            arcMap.put(reset.getID(), reset);
    }
    public void addTransition(Transition transition/*int id, String name, int x, int y*/){
        transitionMap.put(transition.getID(), transition);
    }
    public void addPlace(Place place/*int id, String name, int x, int y*/){
        placeMap.put(place.getID(), place);
    }

//    metody vracaju dany objekt podla id
    public Objekt getObjekt(long id){
        if (placeMap.containsKey(id)){
            return placeMap.get(id);
        }
        else if (transitionMap.containsKey(id)){
            return transitionMap.get(id);
        }
        else if (arcMap.containsKey(id)){
            return arcMap.get(id);
        }
        else
            return null;
    }

    public Objekt getPlace(int id){
        return placeMap.get(id);
    }
    public Objekt getTransition(int id){
        return transitionMap.get(id);
    }
    public Objekt getArc(int id){
        return arcMap.get(id);
    }

    public Map<Long, Place> getPlaceMap() {
        return placeMap;
    }

    public Map<Long, Transition> getTransitionMap() {
        return transitionMap;
    }

    public Map<Long, Arc> getArcMap() {
        return arcMap;
    }

    //    metoda removePlace vymaze miesto z mapu placeMap a vymaze vsetky hrany spojene s tym prechodom
    public void removePlace(long id){
        List<Objekt> arcs = placeMap.get(id).getToWhere();
        removeUnnecessaryArcs(arcs);
        arcs = placeMap.get(id).getFromWhere();
        removeUnnecessaryArcs(arcs);
        placeMap.remove(id);
    }

//    metoda removeTransition vymaze prechod z mapu transitionMap a vymaze vsetky hrany spojene s tym prechodom
    public void removeTransition(long id){
        List<Objekt> arcs = transitionMap.get(id).getFromWhere();
        removeUnnecessaryArcs(arcs);
        arcs = transitionMap.get(id).getToWhere();
        removeUnnecessaryArcs(arcs);
        transitionMap.remove(id);
    }

    private void removeUnnecessaryArcs(List<Objekt> arcs){
                for (Objekt arc : arcs) {
                    removeArc(arc.getID());
                }
    }

    private void removeArc(long id){
        arcMap.remove(id);
    }
}
