package TurtleParts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Leg {
    private final int STROKE_WIDTH = 8;
    private int x;
    private int y;
    private int width;
    private int height;
    private int baseY = y;

    public Leg(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.baseY = y;
    }
    public void draw(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setColor(Color.decode("#1f2922"));
        g2d.drawRoundRect(x,y,width,height,5,5);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.decode("#2e4736"));
        g2d.fillRoundRect(x,y,width,height,5,5);
    }

    public void reset() {
        this.y = baseY;
    }
}
