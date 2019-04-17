package graphics;

import fromzad1.myexceptions.ExceptionFire;
import fromzad1.objekts.Transition;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Float implements Drawable {

    private Transition transition;

    public Transition2D(int x, int y, Transition transition){
        super(x, y, 40, 40);
        this.transition = transition;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        try {
            if (this.transition.testFireAbility()){
                graphics2D.setColor(Color.GREEN);
            } else {
                graphics2D.setColor(Color.RED);
            }
//            graphics2D.setColor(Color.WHITE);
        } catch (ExceptionFire exceptionFire) {
            exceptionFire.printStackTrace();
        }
        graphics2D.fill(this);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(this);
        graphics2D.drawString(transition.getName(), (int)this.getX(), (int)this.getCenterY()+40);
    }

    @Override
    public void onClick(MouseEvent e) {
        try {
            if (this.transition.testFireAbility()){
                this.transition.fireTransition();
            }

        } catch (ExceptionFire exceptionFire) {
            exceptionFire.printStackTrace();
        }
    }
}
