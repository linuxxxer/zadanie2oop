package graphics;

import fromzad1.myexceptions.ExceptionFire;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Drawable extends Shape {

    void draw(Graphics2D graphics2D);

//    boolean contains(int x, int y);

    void onClick(MouseEvent e);

}
