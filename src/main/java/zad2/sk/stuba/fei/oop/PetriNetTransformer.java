package zad2.sk.stuba.fei.oop;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import fromzad1.PetriNet;
import fromzad1.myexceptions.ExceptionCannotResolveValue;
import fromzad1.myexceptions.ExceptionWrongObjectType;
import fromzad1.objekts.ArcReset;
import fromzad1.objekts.Place;
import zad2.sk.stuba.fei.oop.generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/*
* Class to copy the petri net structure
* from the generated classes to
* my classes
*/
public class PetriNetTransformer extends Document {

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
            petriNet.vlozPrechod(prechod);
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
                    petriNet.vlozHrana(helperArc);
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.printStackTrace();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.printStackTrace();
                }
            }
            else if (arc.getType() == ArcType.RESET) {
                try {
                    fromzad1.objekts.ArcReset helperArc = new ArcReset(petriNet.getObjekt(arc.getSourceId()),
                            petriNet.getObjekt(arc.getDestinationId()),
                            arc.getMultiplicity(),
                            arc.getId());
                    petriNet.vlozReset(helperArc);
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.printStackTrace();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.printStackTrace();
                }
            }
        }


        return petriNet;
    }

    public void loadFromXML(String pathToFile){

        File file = new File(pathToFile);



        try {
            InputStream resource = /*LoadDocument.documentF(1, ) */ClassLoader.getSystemResourceAsStream(file.getName());
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Document document = (Document) unmarshaller.unmarshal(resource);

            System.out.println("Number of places:" + document.getPlace().size());
            System.out.println("Number of tranistions:" + document.getTransition().size());
            System.out.println("Number of arcs:" + document.getArc().size());

            PetriNet petriNet;
            PetriNetTransformer trans = new PetriNetTransformer();

            petriNet = trans.transform(document);
//            NetsFrame frame = new NetsFrame();

    } catch (
    JAXBException e) {
        e.printStackTrace();
    }

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
