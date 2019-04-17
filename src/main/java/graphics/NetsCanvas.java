package graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;

public class NetsCanvas extends Canvas implements MouseListener {

    private List<Drawable> drawableList = new LinkedList<>();

    public NetsCanvas(){
        addMouseListener(this);
    }

    public void load(List<Drawable> drawables){
        this.drawableList = drawables;
    }


    @Override
    public void paint(Graphics g) {
        for (Drawable drawable : drawableList){
            drawable.draw((Graphics2D) g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Drawable drawable : drawableList){
            if (drawable.contains(e.getX(), e.getY())){
                drawable.onClick(e);
            }
        }
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}