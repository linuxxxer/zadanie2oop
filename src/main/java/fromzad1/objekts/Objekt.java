package fromzad1.objekts;

import java.util.*;

public abstract class Objekt {
    private String name;    // meno prislusneho obejktu
    private long ID;        // ID prislusneho objektu
    private int x = 0, y = 0;
/*
*   List odkial - dva moznosti  - u hran - objekt (prechod/miesto) kde zacina hrana
*                               - u miest/prechodov - zoznam hran, ktore idu do daneho objektu
*   List kam    - dva moznosti  - u hran - objekt (prechod/miesto) kde konci hrana
*                               / u miest/prechodov - zoznam hran, ktore idu z daneho objektu
*/
    private List<Objekt> odkial = new ArrayList<Objekt>();
    private List<Objekt> kam = new ArrayList<Objekt>();

    // metoda prida objekt do zoznamu odkial
    void addIn(Objekt toAdd){
        odkial.add(toAdd);
    }

    // metoda prida objekt do zoznamu kam
    void addOut(Objekt toAdd){
        kam.add(toAdd);
    }

    // konstruktor
    public Objekt(String name, long id, int x, int y) {
        this.name = name;
        this.ID = id;
        this.x = x;
        this.y = y;
    }

    public long getID() {
        return ID;
    }

    // metoda getOdkial() a getKam() vrati zoznam odkial/kam
    public List<Objekt> getOdkial() {
        return this.odkial;
    }

    public List<Objekt> getKam() {
        return this.kam;
    }

    public String getName(){
        return this.name;
    }

    // metody vyuzivane u miest
    public int getPocetTokenov(){
        return 0;
    }

    public void setToken(int pocet){ }

    public void resetToken(){ }

    // metody vyuzivane u hran
    public int getNasobnost(){
        return 0;
    }

}
