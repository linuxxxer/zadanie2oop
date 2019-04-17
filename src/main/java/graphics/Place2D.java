package graphics;

import fromzad1.objekts.Place;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Place2D extends Ellipse2D.Float implements Drawable {

    private Place place;

    public Place2D(int x, int y, Place place){
        super(x, y, 40, 40);
        this.place = place;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(this);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(this);
        graphics2D.drawString( "" + place.getTokenNumber(), (int)getCenterX()-5, (int)getCenterY()+5);
        graphics2D.drawString(place.getName(), (int)getX(), (int)getCenterY()+40);
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            // add token when left mouse button pressed
            place.setToken(1);
        }else if (e.getButton() == MouseEvent.BUTTON3){
            // remove token when right mouse button pressed
            place.setToken(-1);
        }else if (e.getButton() == MouseEvent.BUTTON2){
            place.resetToken();
        }
    }
}