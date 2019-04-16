package graphics;

import com.sun.xml.internal.ws.wsdl.writer.document.Import;
import fromzad1.PetriNet;
import zad2.sk.stuba.fei.oop.PetriNetTransformer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class NetsFrame extends Frame {
    final JFileChooser chooseFile = new JFileChooser();
//    private Frame frame = new Frame();
    public NetsFrame(){
        super("Zadanie 2");
        this.setSize(900, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Panel panel = new Panel();

        final Button button = new Button("Open");

        button.addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "XML");
            chooseFile.setFileFilter(filter);
            int returnVal = chooseFile.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION){
                PetriNetTransformer petriNetTransformer = new PetriNetTransformer();
                petriNetTransformer.loadFromXML(chooseFile.getSelectedFile().getAbsolutePath());
            }
        });


//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int retVal = chooseFile.showOpenDialog(button);
//                if (retVal == JFileChooser.APPROVE_OPTION){
//                    String file = chooseFile.getSelectedFile().getAbsolutePath();
//                    System.out.println("Path to file:\t" + file);
//                    PetriNetTransformer petriNetTransformer = new PetriNetTransformer();
//                    petriNetTransformer.loadFromXML(file);
//                }
//            }
//        });

        panel.add(button);
        this.add(panel, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

    public static void main (String[] args){
        NetsFrame netsFrame=new NetsFrame();
//        netsFrame.makeWindowBitch();
//        netsFrame.setVisible(true);
    }
}
