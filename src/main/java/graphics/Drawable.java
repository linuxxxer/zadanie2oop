package graphics;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Drawable extends Shape {

    void draw(Graphics2D graphics2D);

    void onClick(MouseEvent e);
}