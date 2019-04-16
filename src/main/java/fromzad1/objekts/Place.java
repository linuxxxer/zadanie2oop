package fromzad1.objekts;

public class Place
        extends Objekt {

    private int TokenNumber = 0;

    public Place(String name, long id) {
        super(name, id);
    }

    @Override
    public void resetToken(){
        this.TokenNumber = 0;
    }

    @Override
    public void setToken(int number){
        this.TokenNumber += number;
    }

    @Override
    public int getTokenNumber() {
        return TokenNumber;
    }

}
