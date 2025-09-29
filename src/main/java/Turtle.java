import TurtleParts.BaseBody;
import TurtleParts.Leg;
import lombok.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Setter
@Getter

public class Turtle {

    private int x;
    private int y;

    private int bodyWidth = 150;
    private int bodyHeight = 100;
    private boolean isWalking = false;
    private boolean headUpping = false;
    private static final double SPEED = 3;
    private static final int padding = 30;
    private boolean isFacingRight = true;


    private Leg[] legs;
    private BaseBody baseBody;

    private int activeLeg = 0;
    private boolean legIsMovingUp = true;
    private final int MAX_LIFT_HEIGHT = 10;
    private int headUpp;



    public Turtle(int x, int y) {
        this.x = x;
        this.y = y;
        this.legs = new Leg[2];
        legs[0] = new Leg(bodyWidth / 8 * 3, bodyHeight, bodyWidth / 4, bodyHeight / 3);
        legs[1] = new Leg(bodyWidth - bodyWidth / 8, bodyHeight, bodyWidth / 4, bodyHeight / 3);
        this.baseBody = new BaseBody(bodyWidth, bodyHeight, padding);
    }


    public void draw(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        AffineTransform oldTransform = g.getTransform();
        g.translate(this.x, this.y);
        if (!isFacingRight) {
            g.scale(-1, 1);
            g.translate(-bodyWidth, 0);
        }

        baseBody.draw(g, 0, 0);

        for (Leg leg : legs) {
            leg.draw(g);
        }
        g.setTransform(oldTransform);
    }

    public void update(int targetX, int targetY) {
        double centerX = this.x + bodyWidth / 2.0;
        double distanceX = targetX - centerX;

        double centerY = this.y + bodyHeight / 2.0;
        double distanceY = centerY - targetY;

        if(Math.abs(distanceX) < 300 && Math.abs(distanceY) < 200){
            headUpping = true;
            if (distanceY > 0 && headUpp < 10){
                headUpp += 1;
                getBaseBody().setHeadY(getBaseBody().getHeadY() + 1);
            }
            else if (headUpp > 0){
                headUpp -= 1;
                getBaseBody().setHeadY(getBaseBody().getHeadY() - 1);
            }

        }

        if (Math.abs(distanceX) > 150) {
            isWalking = true;
            if (distanceX > 0) {
                this.x += SPEED;
                isFacingRight = true;
            } else {
                this.x -= SPEED;
                isFacingRight = false;
            }
            animateLegs();
        } else {
            for (Leg leg : legs) {
                leg.reset();
            }
            activeLeg = 0;
            legIsMovingUp = true;
        }

    }

    private void animateLegs() {
        Leg leg = legs[activeLeg];

        if (legIsMovingUp) {
            leg.setY(leg.getY() - 1);
            if (leg.getY() <= leg.getBaseY() - MAX_LIFT_HEIGHT) {
                legIsMovingUp = false;
            }
        } else {
            leg.setY(leg.getY() + 1);
            if (leg.getY() >= leg.getBaseY()) {
                leg.reset();
                legIsMovingUp = true;
                activeLeg = (activeLeg + 1) % 2;
            }
        }

        Leg inactiveLeg = legs[(activeLeg + 1) % 2];
        inactiveLeg.reset();
    }

}
