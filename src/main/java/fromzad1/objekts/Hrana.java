package fromzad1.objekts;

import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;

public class Hrana
        extends Objekt{

    private int nasobnost;

//      pri objekte Hrana meno je vzdy nastavene na "". Je to len formalne
    public Hrana(Objekt odkialIn, Objekt kamIn, int nasobnostIn, long id) throws ExceptionWrongObjectType, ExceptionCannotResolveValue {
        super("", id);
        if (kamIn.getClass() == odkialIn.getClass()) {
            throw new ExceptionWrongObjectType();
        }
        if (nasobnostIn <= 0){
            throw new ExceptionCannotResolveValue();
        }
//      pridavanie parametrov na ich prislusne miesto
        addIn(odkialIn);
        addOut(kamIn);
        odkialIn.addOut(this);
        kamIn.addIn(this);
        this.nasobnost = nasobnostIn;
    }

    @Override
    public int getNasobnost() {
        return nasobnost;
    }
}
