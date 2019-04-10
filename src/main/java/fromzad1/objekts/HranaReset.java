package fromzad1.objekts;

import myexceptions.ExceptionCannotResolveValue;
import myexceptions.ExceptionWrongObjectType;

// Hrana reset je odvodeny z classu Hrana, len osetruje, ci objekt odkial hrana vychadza je Miesto
public class HranaReset
        extends Hrana{


    public HranaReset(Objekt odkialIn, Objekt kamIn, int nasobnostIn, long id)
            throws ExceptionWrongObjectType, ExceptionCannotResolveValue {
        super(odkialIn,kamIn,nasobnostIn, id);
        if (odkialIn.getClass() == Prechod.class){
            throw new  ExceptionWrongObjectType();
        }
    }
}
