package graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Arc2D extends Line2D.Float implements Drawable{
    private long ID;

    public Arc2D(int sourceX, int sourceY, int destX, int destY){
        super(sourceX, sourceY, destX, destY);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this);
    }

    @Override
    public void onClick(MouseEvent e) {

    }
}
