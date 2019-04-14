package zad2.sk.stuba.fei.oop;

import fromzad1.PetriNet;
import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;
import fromzad1.objekts.ArcReset;
import fromzad1.objekts.Place;
import zad2.sk.stuba.fei.oop.generated.*;

/*
* Class to copy the petri net structure
* from the generated classes to
* my classes
*/
public class CopyFromGenerated  {

    public PetriNet transform(Document document){
        PetriNet petriNet = new PetriNet();

        for (zad2.sk.stuba.fei.oop.generated.Place place : document.getPlace()){
            Place miesto = new Place(place.getLabel(),
                                        place.getId(),
                                        place.getX(),
                                        place.getY());
            petriNet.vlozMiesto(miesto);
        }
        for (zad2.sk.stuba.fei.oop.generated.Transition transition : document.getTransition()){
            fromzad1.objekts.Transition prechod = new fromzad1.objekts.Transition(transition.getLabel(),
                                            transition.getId(),
                                            transition.getX(),
                                            transition.getY());
        }

        /* TODO
         * the same for arcs
         * need to be careful with the types of the objekts!!
         */
        for (zad2.sk.stuba.fei.oop.generated.Arc arc: document.getArc()){
            if (arc.getType() == ArcType.REGULAR){
                try {
                    fromzad1.objekts.Arc helperArc = new fromzad1.objekts.Arc(petriNet.getObjekt(arc.getSourceId()),
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
                    fromzad1.objekts.Arc helperArc = new ArcReset(petriNet.getObjekt(arc.getSourceId()),
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
            Place helperPlace = new Place(place.getLabel(), place.getId(), place.getX(), place.getY());
//            petriNet.vlozMiesto();
        }
    }

    private void copyTransition(List<Transition> transitions, PetriNet petriNet){  // prechod

    }*/

}
