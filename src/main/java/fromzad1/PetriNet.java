package fromzad1;

import java.util.*;
import fromzad1.objekts.*;
import fromzad1.myexceptions.*;

public class PetriNet {

    private Map<Integer, Miesto> miestoMap = new HashMap<Integer, Miesto>();
    private Map<Integer, Prechod> prechodMap = new HashMap<Integer, Prechod>();
    private Map<Integer, Hrana> hranaMap = new HashMap<Integer, Hrana>();

    public void defaultnyPetriSiet(){

        for (int p = 0; p < 5; p++){
            vlozPrechod(p+1, "p"+(p+1));
        }

        for (int m = 0; m < 7; m++){
            vlozMiesto(m+1, "m"+(m+1));
        }
        miestoMap.get(3).setToken(1);
        miestoMap.get(4).setToken(0);
        miestoMap.get(5).setToken(5);

        vlozHrana(1, miestoMap.get(2),  prechodMap.get(3),1);
        vlozHrana(2, prechodMap.get(3), miestoMap.get(1), 5);
        vlozHrana(3, miestoMap.get(3),  prechodMap.get(4),1);
        vlozHrana(4, prechodMap.get(4), miestoMap.get(3), 2);
        vlozHrana(5, miestoMap.get(4),  prechodMap.get(5),1);
        vlozHrana(6, prechodMap.get(5), miestoMap.get(6), 1);
        vlozHrana(7, prechodMap.get(5), miestoMap.get(7), 1);
        vlozReset(8, miestoMap.get(5), prechodMap.get(5), 1);
    }

//    metoda, ktora pusti prechod podla cisla id
//    ak nevie pustit prechod, vypise, ktory prechod nebol pustitelny
    public void pustiPrechod(int id) {
        prechodMap.get(id).pustitPrechod();
    }

//    metody na manpulaciu so sietou
//    potrebne hlavne pre testovanie
    public void vlozHrana(int id, Objekt odkial, Objekt kam, int nasobnost){
        hranaMap.put(id, new Hrana(odkial, kam, nasobnost, id));
    }
    public void vlozReset(int id, Objekt odkial, Objekt kam, int nasobnost){
        hranaMap.put(id, new HranaReset(odkial, kam, nasobnost, id));
    }
    public void vlozPrechod(int id, String name){
        prechodMap.put(id, new Prechod(name, id));
    }
    public void vlozMiesto(int id, String name){
        miestoMap.put(id, new Miesto(name,id));
    }

//    metody vracaju dany objekt podla id
    public Objekt getMiesto(int id){
        return miestoMap.get(id);
    }
    public Objekt getPrechod(int id){
        return prechodMap.get(id);
    }
    public Objekt getHrana(int id){
        return hranaMap.get(id);
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
