package fromzad1.objekts;

import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;

// Arc reset je odvodeny z classu Arc, len osetruje, ci objekt odkial hrana vychadza je Place
public class ArcReset
        extends Arc {


    public ArcReset(Objekt odkialIn, Objekt kamIn, int nasobnostIn, long id)
            throws ExceptionWrongObjectType, ExceptionCannotResolveValue {
        super(odkialIn,kamIn,nasobnostIn, id);
        if (odkialIn.getClass() == Transition.class){
            throw new  ExceptionWrongObjectType();
        }
    }
}
