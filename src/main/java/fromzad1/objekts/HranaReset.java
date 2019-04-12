package fromzad1.objekts;

import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;

// Hrana reset je odvodeny z classu Hrana, len osetruje, ci objekt odkial hrana vychadza je Miesto
public class HranaReset
        extends Hrana{


    public HranaReset(Objekt odkialIn, Objekt kamIn, int nasobnostIn, long id, int x, int y)
            throws ExceptionWrongObjectType, ExceptionCannotResolveValue {
        super(odkialIn,kamIn,nasobnostIn, id, x, y);
        if (odkialIn.getClass() == Prechod.class){
            throw new  ExceptionWrongObjectType();
        }
    }
}
