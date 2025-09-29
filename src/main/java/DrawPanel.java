import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int FLOOR_HEIGHT = 50;
    private Timer timer;

    private Turtle turtle;
    private Background background;
    private int mouseX;
    private int mouseY;

    public DrawPanel(final int panelWidth, final int panelHeight, final int timerDelay) {
        this.PANEL_WIDTH = panelWidth;
        this.PANEL_HEIGHT = panelHeight;
        this.timer = new Timer(timerDelay, this);
        this.background = new Background(0, 0, panelWidth, panelHeight);
        this.turtle = new Turtle(PANEL_WIDTH / 3, 400);
        mouseX = PANEL_WIDTH / 2;
        mouseY = PANEL_WIDTH / 6;

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseY = e.getY();
            }
        });

        timer.start();
    }

    @Override
    public void paintComponent(final Graphics gr) {
        super.paintComponent(gr);
        background.draw(gr);
        turtle.draw(gr);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        turtle.update(mouseX, mouseY);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
