import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("turtle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final int windowWidth = 800;
            final int windowHeight = 600;
            DrawPanel panel = new DrawPanel(windowWidth, windowHeight, 16);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}