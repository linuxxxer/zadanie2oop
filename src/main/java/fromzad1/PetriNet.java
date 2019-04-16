package fromzad1;

import java.util.*;
import fromzad1.objekts.*;
import fromzad1.myexceptions.*;

public class PetriNet {

    private Map<Long, Miesto> miestoMap = new HashMap<Long, Miesto>();
    private Map<Long, Prechod> prechodMap = new HashMap<Long, Prechod>();
    private Map<Long, Hrana> hranaMap = new HashMap<Long, Hrana>();

    /*public void defaultnyPetriSiet(){

        for (int p = 0; p < 5; p++){
            vlozPrechod(p+1, "p"+(p+1), 0, 0);
        }

        for (int m = 0; m < 7; m++){
            vlozMiesto(m+1, "m"+(m+1), 0, 0);
        }
        miestoMap.get(3).setToken(1);
        miestoMap.get(4).setToken(0);
        miestoMap.get(5).setToken(5);

        vlozHrana(1, miestoMap.get(2),  prechodMap.get(3),1, 0, 0);
        vlozHrana(2, prechodMap.get(3), miestoMap.get(1), 5, 0, 0);
        vlozHrana(3, miestoMap.get(3),  prechodMap.get(4),1, 0, 0);
        vlozHrana(4, prechodMap.get(4), miestoMap.get(3), 2, 0, 0);
        vlozHrana(5, miestoMap.get(4),  prechodMap.get(5),1, 0, 0);
        vlozHrana(6, prechodMap.get(5), miestoMap.get(6), 1, 0, 0);
        vlozHrana(7, prechodMap.get(5), miestoMap.get(7), 1, 0, 0);
        vlozReset(8, miestoMap.get(5), prechodMap.get(5), 1, 0, 0);
    }*/

//    metoda, ktora pusti prechod podla cisla id
//    ak nevie pustit prechod, vypise, ktory prechod nebol pustitelny
    public void pustiPrechod(int id) {
        try {
            prechodMap.get(id).pustitPrechod();
        } catch (ExceptionFire exceptionFire) {
            exceptionFire.printStackTrace();
        }
    }

//    metody na manpulaciu so sietou
//    potrebne hlavne pre testovanie
    public void vlozHrana(Hrana hrana){
            hranaMap.put(hrana.getID(), hrana);
    }
    public void vlozReset(HranaReset reset){
            hranaMap.put(reset.getID(), reset);
    }
    public void vlozPrechod(Prechod prechod/*int id, String name, int x, int y*/){
        prechodMap.put(prechod.getID(), prechod);
    }
    public void vlozMiesto(Miesto miesto/*int id, String name, int x, int y*/){
        miestoMap.put(miesto.getID(), miesto);
    }

//    metody vracaju dany objekt podla id
    public Objekt getObjekt(long id){
        if (miestoMap.containsKey(id)){
            return miestoMap.get(id);
        }
        else if (prechodMap.containsKey(id)){
            return prechodMap.get(id);
        }
        else if (hranaMap.containsKey(id)){
            return hranaMap.get(id);
        }
        else
            return null;
    }

    public Objekt getMiesto(int id){
        return miestoMap.get(id);
    }
    public Objekt getPrechod(int id){
        return prechodMap.get(id);
    }
    public Objekt getHrana(int id){
        return hranaMap.get(id);
    }

    public Map<Long, Miesto> getMiestoMap() {
        return miestoMap;
    }

    public Map<Long, Prechod> getPrechodMap() {
        return prechodMap;
    }

    public Map<Long, Hrana> getHranaMap() {
        return hranaMap;
    }

    //    metoda zmazMiesto vymaze miesto z mapu miestoMap a vymaze vsetky hrany spojene s tym prechodom
    public void zmazMiesto(int id){
        List<Objekt> hrany = miestoMap.get(id).getKam();
        zmazatNezbytocneHrany(hrany);
        hrany = miestoMap.get(id).getOdkial();
        zmazatNezbytocneHrany(hrany);
        miestoMap.remove(id);
    }

//    metoda zmazPrechod vymaze prechod z mapu prechodMap a vymaze vsetky hrany spojene s tym prechodom
    public void zmazPrechod(int id){
        List<Objekt> hrany = prechodMap.get(id).getOdkial();
        zmazatNezbytocneHrany(hrany);
        hrany = prechodMap.get(id).getKam();
        zmazatNezbytocneHrany(hrany);
        prechodMap.remove(id);
    }

    private void zmazatNezbytocneHrany(List<Objekt> hrany){
                for (Objekt hrana : hrany) {
                    zmazHrana((int)hrana.getID());
                }
    }

    private void zmazHrana(int id){
        hranaMap.remove(id);
    }
}
