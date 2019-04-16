package zad2.sk.stuba.fei.oop;

import fromzad1.PetriNet;
import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;
import fromzad1.objekts.Hrana;
import fromzad1.objekts.HranaReset;
import fromzad1.objekts.Miesto;
import fromzad1.objekts.Prechod;
import zad2.sk.stuba.fei.oop.generated.*;

import java.util.List;
import java.util.Map;

/*
* Class to copy the petri net structure
* from the generated classes to
* my classes
*/
public class CopyFromGenerated  {

    public PetriNet transform(Document document){
        PetriNet petriNet = new PetriNet();

        for (Place place : document.getPlace()){
            Miesto miesto = new Miesto(place.getLabel(),
                                        place.getId(),
                                        place.getX(),
                                        place.getY());
            petriNet.vlozMiesto(miesto);
        }
        for (Transition transition : document.getTransition()){
            Prechod prechod = new Prechod(transition.getLabel(),
                                            transition.getId(),
                                            transition.getX(),
                                            transition.getY());
        }

        /* TODO
         * the same for arcs
         * need to be careful with the types of the objekts!!
         */
        for (Arc arc: document.getArc()){
            if (arc.getType() == ArcType.REGULAR){
                try {
                    Hrana helperHrana = new Hrana(petriNet.getObjekt(arc.getSourceId()),
                                                petriNet.getObjekt(arc.getDestinationId()),
                                                arc.getMultiplicity(),
                                                arc.getId());
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.printStackTrace();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.printStackTrace();
                }
            }
            else if (arc.getType() == ArcType.RESET) {
                try {
                    Hrana helperHrana = new HranaReset(petriNet.getObjekt(arc.getSourceId()),
                            petriNet.getObjekt(arc.getDestinationId()),
                            arc.getMultiplicity(),
                            arc.getId());
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.printStackTrace();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.printStackTrace();
                }
            }
        }


        return petriNet;
    }


/*
    public void copyAll(Document doc, PetriNet petriNet){
        copyArc(doc, doc.getArc(), petriNet);
        copyPlace(doc.getPlace(), petriNet);
        copyTransition(doc.getTransition(), petriNet);

    }

    private void copyArc(Document doc, List<Arc> arcs, PetriNet petriNet){         // hrana
        for (Arc arc : arcs){
            if (doc.getPlace().get(arc.getSourceId()) != null){
                if (arc.getType() == ArcType.REGULAR){

                }
            }
        }
    }

    private void copyPlace(List<Place> places, PetriNet petriNet){       // miesto
        for (Place place : places){
            Miesto helperPlace = new Miesto(place.getLabel(), place.getId(), place.getX(), place.getY());
//            petriNet.vlozMiesto();
        }
    }

    private void copyTransition(List<Transition> transitions, PetriNet petriNet){  // prechod

    }*/

}
