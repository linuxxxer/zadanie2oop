package fromzad1.objekts;

public class Miesto
        extends Objekt {

    private int pocetTokenov = 0;

    public Miesto(String name, long id) {
        super(name, id);
    }

    @Override
    public void resetToken(){
        this.pocetTokenov = 0;
    }

    @Override
    public void setToken(int pocet){
        this.pocetTokenov += pocet;
    }

    @Override
    public int getPocetTokenov() {
        return pocetTokenov;
    }

}
