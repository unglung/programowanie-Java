import javax.swing.*;
import java.awt.event.*;

public class Main extends JPanel implements MouseListener, MouseMotionListener {

    public Main() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Kliknięto w punkcie: " + e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Wciśnięto przycisk myszy w punkcie: " + e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Zwolniono przycisk myszy w punkcie: " + e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Kursor wszedł w obszar komponentu.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Kursor opuścił obszar komponentu.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Przeciąganie w punkcie: " + e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Ruch kursora w punkcie: " + e.getPoint());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Obsługa zdarzeń myszy");
        Main panel = new Main();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
