package fromzad1.objekts;

public class Place
        extends Objekt {

    private int pocetTokenov = 0;

    public Place(String name, long id, int x, int y) {
        super(name, id, x, y);
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
