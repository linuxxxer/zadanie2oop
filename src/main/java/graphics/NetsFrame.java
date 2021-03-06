package graphics;

import fromzad1.PetriNet;
import zad2.sk.stuba.fei.oop.DrawableTransformer;
import zad2.sk.stuba.fei.oop.PetriNetTransformer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * Trieda na implementovanie Frame-u.
 * Ma za ulohu zvladat otvorenie JFileChooseru pomocou tlacidla 'openButton'
 * Riadi transformacie
 */
public class NetsFrame extends Frame implements ActionListener{

    final private JFileChooser jf = new JFileChooser();

    private NetsCanvas canvas;
    private PetriNet petriNet;

    public NetsFrame() throws HeadlessException {
        super("Zadanie 2");

        setVisible(true);
        setSize(800, 600);
        setLayout(new BorderLayout());

        Button openButton = new Button("Open");

        this.add(openButton, BorderLayout.PAGE_END);

        canvas = new NetsCanvas();
        this.add(canvas, BorderLayout.CENTER);

        openButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "XML");
        jf.setFileFilter(filter);
        int returnVal = jf.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION){

            PetriNetTransformer petriNetTransformer = new PetriNetTransformer();
            petriNet = petriNetTransformer.transformFromXML(jf.getSelectedFile().getAbsolutePath());

            DrawableTransformer drawableTransformer = new DrawableTransformer(petriNet);
            canvas.load(drawableTransformer.transformFromXML(jf.getSelectedFile().getAbsolutePath()));

            canvas.repaint();
        }
    }
}