import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fill(new Rectangle2D.Double(50, 50, 100, 100));

        g2d.setColor(Color.BLUE);
        g2d.fill(new Ellipse2D.Double(75, 75, 50, 50));
    }
}

public class Main extends JFrame {
    public Main() {
        super("Rysowanie");
        add(new MyPanel());
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
