package zad2.sk.stuba.fei.oop;

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
            Place place1 = new Place(place.getLabel(),
                                        place.getId(),
                                        place.getX(),
                                        place.getY());
            petriNet.addPlace(place1);
        }
        for (zad2.sk.stuba.fei.oop.generated.Transition transition : document.getTransition()){
            fromzad1.objekts.Transition transition1 = new fromzad1.objekts.Transition(transition.getLabel(),
                                            transition.getId(),
                                            transition.getX(),
                                            transition.getY());
            petriNet.addTransition(transition1);
        }

        /* TODO
         * the same for arcs
         * need to be careful with the types of the objekts!!
         */
        for (zad2.sk.stuba.fei.oop.generated.Arc arc: document.getArc()){
            if (arc.getType() == ArcType.REGULAR){
                try {
                    fromzad1.objekts.Arc arc1 = new fromzad1.objekts.Arc(petriNet.getObjekt(arc.getSourceId()),
                                                petriNet.getObjekt(arc.getDestinationId()),
                                                arc.getMultiplicity(),
                                                arc.getId());
                    petriNet.addArc(arc1);
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.ExceptionObjectTypesCannotMatch();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.ExceptionNegativValue();
                }
            }
            else if (arc.getType() == ArcType.RESET) {
                try {
                    fromzad1.objekts.ArcReset helperArc = new ArcReset(petriNet.getObjekt(arc.getSourceId()),
                            petriNet.getObjekt(arc.getDestinationId()),
                            arc.getMultiplicity(),
                            arc.getId());
                    petriNet.addResetArc(helperArc);
                } catch (ExceptionWrongObjectType exceptionWrongObjectType) {
                    exceptionWrongObjectType.ExceptionObjectTypesCannotMatch();
                } catch (ExceptionCannotResolveValue exceptionCannotResolveValue) {
                    exceptionCannotResolveValue.ExceptionNegativValue();
                }
            }
        }
        return petriNet;
    }

    public void loadFromXML(String pathToFile){

        File file = new File(pathToFile);

        try {
            InputStream resource = ClassLoader.getSystemResourceAsStream(file.getName());
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Document document = (Document) unmarshaller.unmarshal(resource);

            System.out.println("Number of places:" + document.getPlace().size());
            System.out.println("Number of tranistions:" + document.getTransition().size());
            System.out.println("Number of arcs:" + document.getArc().size());

            PetriNet petriNet;
            PetriNetTransformer trans = new PetriNetTransformer();
            petriNet = trans.transform(document);

        } catch (
        JAXBException e) {
            e.printStackTrace();
        }

    }

}
