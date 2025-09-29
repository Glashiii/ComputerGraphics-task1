import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Background {
    private int x;
    private int y;

    private int width;
    private int height;

    public Background(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;
        GradientPaint skyGradient = new GradientPaint(0, 0, Color.decode("#5788c9"), 0, (float) (height*0.4),
                Color.decode("#a0cfde"));
        g2d.setPaint(skyGradient);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.decode("#6e8c61"));
        g2d.fillRect(x, (int) (height*0.4), width, (int) (height*0.6));
    }
}
