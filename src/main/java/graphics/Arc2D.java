package graphics;

import fromzad1.objekts.Arc;
import fromzad1.objekts.ArcReset;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Arc2D extends Line2D.Float implements Drawable{
    private long ID;
    private final int ARR_SIZE = 8;
    private Arc arc;
    private final int width = 2;
    public Arc2D(int sourceX, int sourceY, int destX, int destY, Arc arc){
        super(sourceX, sourceY, destX, destY);
        this.arc = arc;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(width));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("" + arc.getMultiplicity(), middleX(getX2(), getX1()), middleY(getY2(), getY1()));
        graphics2D.setColor(Color.BLACK);
        if (arc.getClass() == Arc.class) {
            drawArrow(graphics2D, (int)this.getX1(), (int)this.getY1(), (int)this.getX2(), (int)this.getY2(), false);
        } else if (arc.getClass() == ArcReset.class){
            drawArrow(graphics2D, (int)this.getX1(), (int)this.getY1(), (int)this.getX2(), (int)this.getY2(), true);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
    }

    private int middleX(double x_1, double x_2){
        double middle_x = ((x_2 - x_1) * 0.5) + x_1;
        return (int)middle_x;
    }

    private int middleY(double y_1, double y_2){
        double middle_y = ((y_2 - y_1) * 0.5) + y_1;
        return (int)middle_y;
    }

    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2, boolean reset) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        if (reset){
            g.fillPolygon(new int[] {(len*3)/4-ARR_SIZE, ((len*3)/4)-2*ARR_SIZE, ((len*3)/4)-2*ARR_SIZE, (len*3)/4-ARR_SIZE},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
        g.fillPolygon(new int[] {(len*3)/4, ((len*3)/4)-ARR_SIZE, ((len*3)/4)-ARR_SIZE, (len*3)/4},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}