package fromzad1.objekts;

import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;

public class Arc
        extends Objekt{

    private int multiplicity;

//      pri objekte Arc meno je vzdy nastavene na "". Je to len formalne
    public Arc(Objekt fromWhere, Objekt toWhere, int multiplicity, long id) throws ExceptionWrongObjectType, ExceptionCannotResolveValue {
        super("", id);
        if (toWhere.getClass() == fromWhere.getClass()) {
            throw new ExceptionWrongObjectType();
        }
        if (multiplicity <= 0){
            throw new ExceptionCannotResolveValue();
        }
//      pridavanie parametrov na ich prislusne miesto
        addIn(fromWhere);
        addOut(toWhere);
        fromWhere.addOut(this);
        toWhere.addIn(this);
        this.multiplicity = multiplicity;
    }

    @Override
    public int getMultiplicity() {
        return multiplicity;
    }
}
