package fromzad1.objekts;

import fromzad1.myexceptions.ExceptionFire;

import java.util.List;

public class Prechod
        extends Objekt {

    public Prechod(String name, long id, int x, int y) {
        super(name, id, x, y);
    }
/*
 *  Overenie pustitelnosti prechodu, potom, ak je to pustitelny, pustenie prechodu
 */
    public void pustitPrechod() throws ExceptionFire{
        this.testujPustitelnost();
        this.pustiPrechod();


    }
/*
 *          overenie, ci je nasobnost vacsia ako pocet tokenov v mieste, odkial vychadza
 *          ak je vacsia, tak vyhodi vynimku ExceptionFire() -- prechod nie je pustitelny
 */
    private void testujPustitelnost() throws ExceptionFire{
        List<Objekt> hrany = this.getOdkial();
        for (Objekt hrana : hrany){
            if (hrana.getNasobnost() > (hrana.getOdkial().get(0)).getPocetTokenov()){
                throw new ExceptionFire();
            }
        }
    }
/*
 *  Pustenie prechodu
 *  predpokladame, ze pred pustenim sa osetrilo pustitelnost prechodu pomocou
 *  funkcie testujPustitelnost()
 */
    private void pustiPrechod(){
        List<Objekt> hranyDo = this.getOdkial();
        List<Objekt> hranyZ  = this.getKam();
        Objekt temp;
        int pocetTokenov;
        for (Objekt hrana : hranyDo){
            temp = hrana.getOdkial().get(0);
            if (hrana.getClass() == HranaReset.class){
                temp.resetToken();
                continue;
            }
            pocetTokenov = hrana.getNasobnost();
            temp.setToken(-pocetTokenov);
        }
        for (Objekt hrana : hranyZ) {
            temp = hrana.getKam().get(0);
            pocetTokenov = hrana.getNasobnost();
            temp.setToken(pocetTokenov);
        }
    }
}