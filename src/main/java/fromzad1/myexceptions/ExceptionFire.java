package fromzad1.myexceptions;

import fromzad1.objekts.Objekt;

public class ExceptionFire
        extends Exception {

    public void ExceptionCannotFire(Objekt objekt){
        System.out.println("Prechod " + objekt.getName() + " nie je pustitelny.");
    }
}
