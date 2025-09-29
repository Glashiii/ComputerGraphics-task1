package TurtleParts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

@Getter
@Setter
public class BaseBody {
    private final int STROKE_WIDTH = 10;
    private int width;
    private int height;
    private int basePadding;

    private final String mainGreen = "#2e4736";
    private final String darkGreen = "#1f2922";
    private final String mainBeige = "#ab9a7d";
    private static int headY;


    public BaseBody(int width, int height, int basePadding) {
        this.width = width;
        this.height = height;
        this.basePadding = basePadding;
    }

    public static int getHeadY() {
        return headY;
    }

    public static void setHeadY(int headY) {
        BaseBody.headY = headY;
    }

    public void draw(Graphics2D g2d, int x, int y) {

        int xWithPadding = x+basePadding;

        g2d.setStroke(new BasicStroke(STROKE_WIDTH));

        g2d.setColor(Color.decode(darkGreen));
        g2d.draw(createMainBodyShape(xWithPadding, y));
        g2d.draw(createMainHeadShape(xWithPadding, y-headY));
        // tail
        int x1 = x, y1 = (int) (y + getHeight()*0.8);
        int x2 = xWithPadding, y2 = (int) (y + getHeight()*0.8);
        int x3 = xWithPadding-5, y3 = y + getHeight();

        g2d.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3},3);
        g2d.setColor(Color.decode(mainGreen));
        g2d.setStroke(new BasicStroke(1));

        Point2D center = new Point2D.Float((float) (x + getWidth() /1.8), y + (float) getHeight() /3);
        float radius = (float) (getHeight() * 1.1);
        float[] fractions = {0.0f, 1.0f};

        Color[] colors = {Color.decode("#538c3e"), Color.decode(mainGreen)};

        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, fractions, colors);

        g2d.setPaint(rgp);
        g2d.fill(createMainBodyShape(xWithPadding, y));
        g2d.setColor(Color.decode(mainBeige));
        g2d.fill(createLowerBodyShape(xWithPadding, y));

        g2d.setColor(Color.decode(mainGreen));
        g2d.fill(createMainHeadShape(xWithPadding, y-headY));
        g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3},3);



    }


    private Shape createMainBodyShape(int x, int y) {
        Path2D.Double bodyPath = new Path2D.Double();

        int cornerRadius = getWidth() / 3;
        int downY = y + getHeight();
        int rightX = x + getWidth();

        // left bottom corner
        bodyPath.moveTo(x, downY);

        bodyPath.lineTo(x + getWidth() * 0.1, downY - cornerRadius);
        bodyPath.quadTo(x + (double) getWidth() / 5, y, x + (double) getWidth() / 2, y);
        bodyPath.quadTo(rightX - (double) getWidth() / 5, y, rightX, downY - cornerRadius);
        bodyPath.quadTo(rightX + getWidth() * 0.05, downY - cornerRadius + getHeight() * 0.05, rightX + getWidth() * 0.1,
                downY - cornerRadius + getHeight() * 0.1);
        bodyPath.lineTo(rightX + getWidth() * 0.1, downY);
        bodyPath.quadTo(rightX * 0.95, downY + getHeight() * 0.15,  x +(double) getWidth() / 2, downY + getHeight() * 0.2);
        bodyPath.quadTo(x * 1.05, downY + getHeight() * 0.15, x, downY);

        bodyPath.closePath();

        return bodyPath;
    }

    private Shape createLowerBodyShape(int x, int y) {
        Path2D.Double bodyPath = new Path2D.Double();

        int cornerRadius = getWidth() / 3;
        int downY = y + getHeight();
        int rightX = x + getWidth();

        // left bottom corner
        bodyPath.moveTo(x, downY);

        bodyPath.lineTo((x + (getWidth() * 0.1)/3), (double) (downY - cornerRadius/3) );
        bodyPath.lineTo(rightX + getWidth() * 0.1, (double) (downY - cornerRadius/3) );
        bodyPath.lineTo(rightX + getWidth() * 0.1, downY);
        bodyPath.quadTo(rightX * 0.95, downY + getHeight() * 0.15,  x +(double) getWidth() / 2, downY + getHeight() * 0.2);
        bodyPath.quadTo(x * 1.05, downY + getHeight() * 0.15, x, downY);

        bodyPath.closePath();

        return bodyPath;
    }

    private Shape createMainHeadShape(int x, int y) {
        Path2D.Double headPath = new Path2D.Double();


        int leftX = (int) (x + getWidth() * 1.1);
        int rightX = (int) (x + getWidth() * 1.6);
        int headWidth = rightX - leftX;

        int downY = (int) (y + getHeight());
        int upperY = (int) (y + getHeight() * 0.2);
        int headHeight = downY - upperY;


        headPath.moveTo(leftX, downY);
        headPath.lineTo(leftX, downY - (double) headHeight /2);
        headPath.lineTo(leftX + headWidth/1.5, downY - (double) headHeight);
        headPath.lineTo(leftX + headWidth/1.2, downY - (double) headHeight);

        headPath.quadTo(leftX + headWidth, downY - (double) headHeight, leftX + headWidth,
                downY - (double) headHeight/1.3);
        headPath.quadTo(leftX + headWidth, downY - (double) headHeight/1.5, leftX + headWidth * 0.9,
                downY - (double) headHeight/1.8);

        headPath.closePath();
        return headPath;
    }


}
