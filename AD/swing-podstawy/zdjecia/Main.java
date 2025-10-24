import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ObrazPanel extends JPanel {
    private BufferedImage image;

    public ObrazPanel() {
        super();
        File imageFile = new File("java.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}

public class Main extends JFrame {
    public Main() {
        super("Program obrazkowy");
        JPanel obrazPanel = new ObrazPanel();
        add(obrazPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
